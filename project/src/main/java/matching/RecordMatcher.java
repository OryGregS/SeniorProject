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

import data.*;
import indexing.Group;
import indexing.Indexer;

import java.util.ArrayList;

public class RecordMatcher {


    private ArrayList<Group> contactGroups;
    private ArrayList<Group> partnerships;
    private Weights defaultWeights;
    private Weights alternateWeights;
    private boolean matchMasterToMaster;
    private boolean print;


    public RecordMatcher(Indexer indexer, boolean masterToMaster) {

        this.contactGroups = indexer.getContactGroups();
        this.partnerships = indexer.getPartnerships();
        this.matchMasterToMaster = masterToMaster;
        this.defaultWeights = new Weights(false);
        this.alternateWeights = new Weights(true);
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

            ArrayList<MasterContact> masterContacts = groups.get(i).getMasterContacts();
            ArrayList<Contact> matchContacts = groups.get(i).getMatchContacts();

            masterContacts.parallelStream().forEach(masterContact ->
                    compare(masterContact, matchContacts, compareMethod));

        }
    }

    /**
     * Compares a mastercontact against each contant in the matching set
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

                        cmp = new CompareRecord(alternateWeights, masterContact, matchContact);

                    } else {

                        cmp = new CompareRecord(defaultWeights, masterContact, matchContact);

                    }


                    boolean exactMatch = cmp.CRD();

                    if (exactMatch) {
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
                    confidence += cmp.similarity("zip", compareMethod);
                    confidence += cmp.similarity("country", compareMethod);

                    if (confidence >= 70) {
                        masterContact.setMatch(matchContact, confidence);
                    }
                }
            }

        if (print)
            printTop(masterContact, false);

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
    public void printTop(MasterContact master, boolean onlyPrintMatches) {
        ArrayList<Double> confidence = master.getTopConfidence();
        ArrayList<Contact> contacts = master.getTopContacts();

        if (onlyPrintMatches) {
            if (!contacts.isEmpty()) {
                printMatches(master, confidence, contacts);
            }
        } else {
            printMatches(master, confidence, contacts);
        }

    }

    private void printMatches(MasterContact master, ArrayList<Double> confidence, ArrayList<Contact> contacts) {

        printDiv();
        System.out.print("MasterContact: \t\t ");
        master.printRelevant();
        printDiv();

        int listSize = confidence.size();
        for (int i = 0; i < listSize; i++) {

            System.out.printf("\nConfidence: %6.2f | ", confidence.get(i));
            contacts.get(i).printRelevant();

        }

        System.out.println();

    }

}

