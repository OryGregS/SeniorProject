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

public class RecordMatcher {


    private ArrayList<Group> contactGroups;
    private ArrayList<Group> partnerships;
    private Weights weights1;
    private Weights weights2;
    private boolean matchMasterToMaster;
    private double threshold = 70.0;
    private int numComparisons = 0;


    public RecordMatcher(Indexer indexer, boolean masterToMaster) {

        this.contactGroups = indexer.getContactGroups();
        this.partnerships = indexer.getPartnerships();
        this.matchMasterToMaster = masterToMaster;
        this.weights1 = new Weights(false);
        this.weights2 = new Weights(true);

    }

    public RecordMatcher(Indexer indexer, Weights weights1,
                         Weights weights2, boolean masterToMaster) {

        this.contactGroups = indexer.getContactGroups();
        this.partnerships = indexer.getPartnerships();
        this.matchMasterToMaster = masterToMaster;
        this.weights1 = weights1;
        this.weights2 = weights2;

    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    /**
     * Loops through the datasets to calculate
     * the similarity between the contacts in Master
     * and the contacts in the dataset to match
     *
     * @param compareMethod - what string comparison metric to use
     */
    public void run(String compareMethod) {

        runGroups(this.contactGroups, compareMethod);
        runGroups(this.partnerships, compareMethod);

    }

    private void runGroups(ArrayList<Group> groups, String compareMethod) {

        int i;
        int size;

        size = groups.size();

        // loop over groups
        for (i = 0; i < size; i++) {

            Group group = groups.get(i);
            ArrayList<MasterContact> masterContacts = group.getMasterContacts();
            ArrayList<Contact> matchContacts = group.getMatchContacts();


            masterContacts.parallelStream().forEach(masterContact ->
                    compare(masterContact, matchContacts, compareMethod));

        }
    }

    /**
     * Compares a mastercontact against each contant in the matching set
     *
     * @param masterContact - contact data from the master record
     * @param compareMethod - string comparison metric to use
     */
    private void compare(MasterContact masterContact, ArrayList<Contact> matchSet, String compareMethod) {

        //String compareMethod = "ratio";

        int j;
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

                double confidence = 0.0;

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


                confidence += cmp.similarity("last", compareMethod);
                confidence += cmp.similarity("middle", compareMethod);
                confidence += cmp.similarity("first", compareMethod);
                confidence += cmp.similarity("firm", compareMethod);
                confidence += cmp.similarity("office", compareMethod);
                confidence += cmp.similarity("email", compareMethod);
                confidence += cmp.similarity("phone", compareMethod);
                confidence += cmp.similarity("address", compareMethod);
                confidence += cmp.similarity("city", compareMethod);
                confidence += cmp.similarity("state", compareMethod);
                confidence += cmp.similarity("zip", compareMethod);
                confidence += cmp.similarity("country", compareMethod);

                if (confidence >= 90) {

                    if (masterContact.getCRDNumber().equalsIgnoreCase("") &&
                            !matchContact.getCRDNumber().equalsIgnoreCase("")) {

                        masterContact.incrementUnknownMatchCount();

                    } else if (!masterContact.getCRDNumber().equalsIgnoreCase("") &&
                            matchContact.getCRDNumber().equalsIgnoreCase("")) {

                        masterContact.incrementUnknownMatchCount();

                    } else if (masterContact.getCRDNumber().equalsIgnoreCase("") &&
                            matchContact.getCRDNumber().equalsIgnoreCase("")) {

                        masterContact.incrementUnknownMatchCount();

                    }

                }

                if (confidence >= this.threshold) {

                    if (exactMatch)
                        masterContact.addIdentifiedMatch();

                    masterContact.setMatch(matchContact, confidence);

                }
            }
        }
    }

    public int getNumComparisons() {
        return this.numComparisons;
    }

}

