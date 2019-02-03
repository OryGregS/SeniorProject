/*
 * ~~~ Version Control Standard ~~~
 *
 * Create a separate branch for implementing new features.
 *
 * *** BEFORE COMMITTING ****
 *
 * ---> Run all unit tests to ensure functionality
 * ---> Run maven's clean command (mvn clean or use the tool in IDE)  to remove unnecessary files
 * ---> Merge the branch into master once the feature is complete with unit tests.
 *
 * ~~~ Copyright ~~~
 *
 * Developed by Gregory Smith & Axel Solano. Last modified 08/01/19 5:35 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package matching;

import data.Contact;
import data.MasterContact;
import indexing.Group;
import indexing.Indexer;

import java.util.ArrayList;

public class Matcher {


    private ArrayList<Group> contactGroups;
    private ArrayList<Group> partnerships;
    private Weights weights1;
    private Weights weights2;
    private boolean matchMasterToMaster = false;
    private double threshold;
    private int numComparisons;

    public Matcher(Weights weights1, Weights weights2) {

        this.weights1 = weights1;
        this.weights2 = weights2;
        this.matchMasterToMaster = false;
        this.threshold = 70.0;
        this.numComparisons = 0;

    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public void matchMasterToMaster() {
        this.matchMasterToMaster = true;
    }

    /**
     * Loops through the datasets to calculate
     * the similarity between the contacts in Master
     * and the contacts in the dataset to match
     */
    public void run(Indexer indexer) {

        this.contactGroups = indexer.getContactGroups();
        this.partnerships = indexer.getPartnerships();

        runGroups(this.contactGroups);
        runGroups(this.partnerships);

    }

    private void runGroups(ArrayList<Group> groups) {

        int i;
        int size;

        size = groups.size();

        // loop over groups
        for (i = 0; i < size; i++) {

            Group group = groups.get(i);
            ArrayList<MasterContact> masterContacts = group.getMasterContacts();
            ArrayList<Contact> matchContacts = group.getMatchContacts();


            masterContacts.parallelStream().forEach(masterContact ->
                    compare(masterContact, matchContacts));

        }
    }

    /**
     * Compares a mastercontact against each contant in the matching set
     *
     * @param masterContact - contact data from the master record
     */
    private void compare(MasterContact masterContact, ArrayList<Contact> matchSet) {

        //String compareMethod = "ratio";

        int j;
        double confidence;
        long matchSetSize = matchSet.size();
        // for each contact in the dataset to match
        for (j = 0; j < matchSetSize; j++) {

            // get contact from matching dataset
            Contact matchContact = matchSet.get(j);

            String masterContactID = masterContact.getContactID();
            String matchContactID = matchContact.getContactID();

            if (matchMasterToMaster && masterContactID.equalsIgnoreCase(matchContactID)) {

                // skip comparing the same contact

            } else {

                confidence = 0.0;

                CompareRecord cmp;

                if (masterContact.getEmail().equalsIgnoreCase("") ||
                        matchContact.getEmail().equalsIgnoreCase("")) {

                    cmp = new CompareRecord(weights2, masterContact, matchContact);

                } else {

                    cmp = new CompareRecord(weights1, masterContact, matchContact);

                }

                numComparisons++;


                boolean exactMatch = cmp.CRD();

                if (exactMatch) {
                    masterContact.addKnownMatch();
                }


                confidence += cmp.similarity("last");
                confidence += cmp.similarity("middle");
                confidence += cmp.similarity("first");
                confidence += cmp.similarity("firm");
                confidence += cmp.similarity("office");
                confidence += cmp.similarity("email");
                confidence += cmp.similarity("phone");
                confidence += cmp.similarity("address");
                confidence += cmp.similarity("city");
                confidence += cmp.similarity("state");
                confidence += cmp.similarity("zip");
                confidence += cmp.similarity("country");


                if (confidence >= this.threshold) {

                    if (exactMatch)
                        masterContact.addIdentifiedMatch();

                    masterContact.setMatch(matchContact, confidence);

                    if (confidence >= 90)
                        checkUnknown(masterContact, matchContact);
                }
            }
        }
    }

    public int getNumComparisons() {
        return this.numComparisons;
    }

    private void checkUnknown(MasterContact masterContact, Contact matchContact) {

        String masterCRD = masterContact.getCRDNumber();
        String matchCRD = matchContact.getCRDNumber();

        if (masterCRD.equalsIgnoreCase("") &&
                !matchCRD.equalsIgnoreCase("")) {

            masterContact.addUnknownMatch();

        } else if (!masterCRD.equalsIgnoreCase("") &&
                matchCRD.equalsIgnoreCase("")) {

            masterContact.addUnknownMatch();

        } else if (masterCRD.equalsIgnoreCase("") &&
                matchCRD.equalsIgnoreCase("")) {

            masterContact.addUnknownMatch();

        }

    }

}

