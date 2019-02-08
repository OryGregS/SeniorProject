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

/**
 * Matcher is where everything comes together. Data from MasterContacts and Contacts are compared.
 */
public class Matcher {


    private ArrayList<Group> contactGroups;
    private ArrayList<Group> partnerships;
    private ArrayList<Group> houseAccounts;
    private Weights weights1;
    private Weights weights2;
    private boolean matchMasterToMaster;
    private double threshold;
    private int numComparisons;

    /**
     * Initializes Matcher with the set of weights to use for measuring our confidence of a match.
     *
     * @param weights1
     *          Default weights (with email).
     * @param weights2
     *          Alternate weights (without email).
     */
    public Matcher(Weights weights1, Weights weights2) {

        this.weights1 = weights1;
        this.weights2 = weights2;
        this.matchMasterToMaster = false;
        this.threshold = 70.0;
        this.numComparisons = 0;

    }

    /**
     * Sets the level of confidence of storing a match.
     * Default value is 70.
     *
     * @param threshold
     *          Double value between 0 - 100.
     */
    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    /**
     * Flag for de-duplicating master to master.
     * Default value is false.
     */
    public void matchMasterToMaster() {
        this.matchMasterToMaster = true;
    }

    /**
     * Loops through the datasets to calculate the similarity between
     * the contacts in Master and the contacts in the dataset to match
     *
     * @param indexer
     *          Indexer object (to get Groups of similar records).
     */
    public void runMatcher(Indexer indexer) {

        this.contactGroups = indexer.getIndividuals();
        this.partnerships = indexer.getPartnerships();
        this.houseAccounts = indexer.getHouseAccounts();

        runGroups(this.contactGroups);
        runGroups(this.partnerships);
        runGroups(this.houseAccounts);

    }

    /**
     * Gets the ArrayLists for MasterContacts and Contacts, calculates the
     * number of comparisons it will make, and compares each MasterContact
     * to each Contact utilizing a parallel stream.
     *
     * @param groups
     *          ArrayList of Group objects.
     */
    private void runGroups(ArrayList<Group> groups) {

        int i;
        int size;

        size = groups.size();

        // loop over groups
        for (i = 0; i < size; i++) {

            Group group = groups.get(i);
            ArrayList<MasterContact> masterContacts = group.getMasterContacts();
            ArrayList<Contact> matchContacts = group.getMatchContacts();

            numComparisons += masterContacts.size() * matchContacts.size();


            masterContacts.parallelStream().forEach(masterContact ->
                    compare(masterContact, matchContacts));

        }
    }

    /**
     * Compares a MasterContact to each Contact in its Group.
     *
     * @param masterContact
     *          MasterContact object.
     * @param matchSet
     *          ArrayList of Contact objects from the MasterContact's group.
     */
    private void compare(MasterContact masterContact, ArrayList<Contact> matchSet) {

        int j;
        double confidence;
        long matchSetSize = matchSet.size();
        // for each contact in the dataset to match
        for (j = 0; j < matchSetSize; j++) {

            // get contact from matching dataset
            Contact matchContact = matchSet.get(j);

            String masterContactID = masterContact.getContactID();
            String matchContactID = matchContact.getContactID();

            // why?
            if (matchMasterToMaster && masterContactID.equalsIgnoreCase(matchContactID)) {

                // skip comparing the same contact

            } else {

                confidence = 0.0;

                CompareRecord cmp;

                // why?

                if (masterContact.getEmail().equalsIgnoreCase("") ||
                        matchContact.getEmail().equalsIgnoreCase("")) {

                    cmp = new CompareRecord(weights2, masterContact, matchContact);

                } else {

                    cmp = new CompareRecord(weights1, masterContact, matchContact);

                }

                //numComparisons++;


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

    /**
     * Gets the number of comparisons made.
     *
     * @return
     *          Total number of comparisons made.
     */
    public int getNumComparisons() {
        return this.numComparisons;
    }

    /**
     * Checks if a match with a high confidence was unknown
     * (CRD number missing for the MasterContact or the matching Contact.
     *
     * @param masterContact
     *          MasterContact object.
     * @param matchContact
     *          Contact object.
     */
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

