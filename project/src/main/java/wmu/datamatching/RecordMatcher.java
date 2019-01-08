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

package wmu.datamatching;

import java.util.ArrayList;

public class RecordMatcher {


    private ArrayList<MasterContact> masterSet;
    private ArrayList<Contact> matchSet;
    private ArrayList<Integer> fieldsToCompare;
    private double subset;
    private boolean matchMasterToMaster;
    private boolean print;

    /**
     * Initializes the RecordMatcher object with a master set,
     * a match set, and the fields to compare between records.
     * @param master - Master dataset
     * @param match - dataset to match to master
     * @param fieldsToCompare - list of fields to compare between records
     */
    public RecordMatcher(MasterSet master, MatchSet match, ArrayList<Integer> fieldsToCompare,
                         boolean masterToMaster) {

        this.masterSet = master.getContactList();
        this.matchSet = match.getContactList();
        this.fieldsToCompare = fieldsToCompare;
        this.subset = 1.0;
        this.matchMasterToMaster = masterToMaster;
        this.print = true;

    }

    /**
     * Initializes the RecordMatcher object with a master set,
     * a match set, the fields to compare between records, and
     * allows a subset of the datasets to be used.
     * @param master - Master dataset
     * @param match - dataset to match to master
     * @param fieldsToCompare - list of fields to compare between records
     * @param subset - value to specify what percentage of the data should be used
     */
    public RecordMatcher(MasterSet master, MatchSet match, ArrayList<Integer> fieldsToCompare,
                         boolean masterToMaster, double subset) {

        this.masterSet = master.getContactList();
        this.matchSet = match.getContactList();
        this.fieldsToCompare = fieldsToCompare;
        this.subset = subset;
        this.matchMasterToMaster = masterToMaster;
        this.print = true;

    }

    /**
     * Prints the top matches for each
     * master contact after all comparisons
     * @param print
     */
    public void printRun(boolean print) {
        this.print = print;
    }

    /**
     * Loops through the datasets to calculate
     * the similarity between the contacts in Master
     * and the contacts in the dataset to match
     */
    public void run() {

        CalcSim calcSim = new CalcSim();

        // for each contact in the master dataset
        for (int i = 0; i < masterSet.size(); i++) {
            // get contact from master dataset
            MasterContact masterContact = masterSet.get(i);

            // for each contact in the dataset to match
            for (int j = 0; j < matchSet.size(); j++) {

                // if comparing the master record against
                // the master record - skip the same contact
                if (this.matchMasterToMaster && i == j)
                    continue;

                // get contact from matching dataset
                Contact matchContact = matchSet.get(j);

                // compare contacts and get confidence of a match
                int confidence = calcSim.compareFields(masterContact, matchContact,
                        "ratio", fieldsToCompare);

                // cutoff - we don't want to try to store anything
                // less than or equal to 60
                if (confidence >= 60)

                    masterContact.setMatch(matchContact, confidence);

            }
            if (print)
                printTop(masterContact);
        }
    }


    private void printDiv() {
        System.out.print("-----------------------------------" +
                "----------------------------------------------" +
                "----------------------------------------------");
        System.out.print("-----------------------------------" +
                "----------------------------------------------" +
                "----------------------------------------------");
        System.out.print("-----------------------------------" +
                "----------------------------------------------" +
                "----------------------------------------------");
        System.out.print("-----------------------------------" +
                "----------------------------------------------" +
                "----------------------------------------------");
        System.out.print("-----------------------------------" +
                "----------------------------------------------" +
                "----------------------------------------------");
        System.out.print("-----------------------------------" +
                "----------------------------------------------" +
                "----------------------------------------------\n");
    }

    /**
     * Prints the contactID and level of
     * confidence for a MasterContact.
     */
    public void printTop(MasterContact master) {
        ArrayList<Integer> confidence = master.getTopConfidence();
        ArrayList<Contact> contacts = master.getTopContacts();
        printDiv();
        System.out.print("MasterContact: \t");
        master.printAll();
        printDiv();
        for (int i = 0; i < confidence.size(); i++) {
            System.out.print("\nConfidence: " + confidence.get(i) + " | ");
            contacts.get(i).printAll();
        }
        System.out.println();
    }

}

