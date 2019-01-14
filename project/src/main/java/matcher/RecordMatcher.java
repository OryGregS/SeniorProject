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

package matcher;

import data.Contact;
import data.MasterContact;
import data.MasterSet;
import data.MatchSet;

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
     */
    public RecordMatcher(MasterSet master, MatchSet match,
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
     * @param subset - value to specify what percentage of the data should be used
     */
    public RecordMatcher(MasterSet master, MatchSet match,
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
    public void run(String compareMethod) {

        PerformanceMeasure measure = new PerformanceMeasure(masterSet);

        measure.startTimer();

        int i;
        long masterSetSize = masterSet.size();
        // for each contact in the master dataset
        for (i = 0; i < masterSetSize; i++) {
            // get contact from master dataset
            MasterContact masterContact = masterSet.get(i);

            int j;
            long matchSetSize = matchSet.size();
            // for each contact in the dataset to match
            for (j = 0; j < matchSetSize; j++) {

                // if comparing the master record against
                // the master record - skip the same contact
                if (this.matchMasterToMaster && i == j) {
                    // do nothing
                }

                else {


                    // get contact from matching dataset
                    Contact matchContact = matchSet.get(j);

                    double confidence = 0.0;
                    Weights weights = new Weights();
                    CompareRecord cmp = new CompareRecord(weights, masterContact, matchContact);

                    boolean exactMatch = cmp.CRD();

                    if (exactMatch) {
//                    confidence = 100.0;
//                    masterContact.setMatch(matchContact, confidence);
                        masterContact.addKnownMatch(matchContact);
                    }



                    confidence += cmp.similarity("last", compareMethod);
                    confidence += cmp.similarity("middle", compareMethod);
                    confidence += cmp.similarity("first", compareMethod);
                    //confidence += cmp.FullName(compareMethod);
                    confidence += cmp.similarity("firm", compareMethod);
                    confidence += cmp.similarity("office", compareMethod);
                    confidence += cmp.similarity("email", compareMethod);
                    confidence += cmp.similarity("phone", compareMethod);
                    confidence += cmp.similarity("address", compareMethod);
                    confidence += cmp.similarity("city", compareMethod);
                    confidence += cmp.similarity("state", compareMethod);
                    confidence += cmp.similarity("zip1", compareMethod);
                    confidence += cmp.similarity("zip2", compareMethod);
                    confidence += cmp.similarity("country", compareMethod);

                    if (confidence >= 70) {
                        masterContact.setMatch(matchContact, confidence);
                    }
                }
            }

            if (print)
                printTop(masterContact);

        }
        measure.endTimer();
        printDiv();
        measure.printPerformanceResults();
        printDiv();
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
        ArrayList<Double> confidence = master.getTopConfidence();
        ArrayList<Contact> contacts = master.getTopContacts();
        printDiv();
        System.out.print("MasterContact: \t\t ");
        master.printAll();
        printDiv();

        int listSize = confidence.size();
        for (int i = 0; i < listSize; i++) {
            System.out.printf("\nConfidence: %6.2f | ", confidence.get(i));
            contacts.get(i).printAll();
        }
        System.out.println();
    }

}

