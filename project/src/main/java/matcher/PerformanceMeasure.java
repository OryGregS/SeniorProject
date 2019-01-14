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
 * Developed by Gregory Smith & Axel Solano. Last modified 13/01/19 4:40 PM.
 * Copyright (c) 2019. All rights reserved.
 */

package matcher;

import data.Contact;
import data.MasterContact;

import java.util.ArrayList;

public class PerformanceMeasure {

    private long startTime;
    private long endTime;
    private int identifiedMatchCount;
    private double identifiedMatchConfidenceSum;
    private long exactMatchCount;
    private ArrayList<MasterContact> masterContacts;

    public PerformanceMeasure(ArrayList<MasterContact> masterContacts) {
        this.identifiedMatchConfidenceSum = 0.0;
        this.exactMatchCount = 0;
        this.masterContacts = masterContacts;
    }

    public void startTimer() {
        this.startTime = System.nanoTime();
    }

    public void endTimer() {
        this.endTime = System.nanoTime();
    }

    private void benchmark() {
        int i;
        int stop = masterContacts.size();
        for (i = 0; i < stop; i++) {
            MasterContact masterContact = masterContacts.get(i);

            // list of contacts that had a matching CRD number to a contact in the master set.
            ArrayList<Contact> knownMatches = masterContact.getExactMatchList();

            int j;
            int knownMatchSize = knownMatches.size();


            this.exactMatchCount += knownMatchSize;

            ArrayList<Contact> topContacts = masterContact.getTopContacts();
            ArrayList<Double> topConfidence = masterContact.getTopConfidence();

            int topContactsSize = topContacts.size();
            for (j=0; j < topContactsSize; j++) {
                Contact contact = topContacts.get(j);

                if (knownMatches.contains(contact)) {
                    // Here we add the confidence value of a match if the program
                    // correctly identified a known match. The goal is to get this sum
                    // as close as possible to exactMatchSum.
                    this.identifiedMatchConfidenceSum += topConfidence.get(j);
                    this.identifiedMatchCount++;
                }
            }
        }
    }

    public void printPerformanceResults() {
        benchmark();

        // Here we multiply the size of the list by 100.
        // This is because our measure of confidence is on a scale
        // of 0 to 100. (100 being a perfect match). If the CRD
        // numbers match, we should expect a confidence of 100.

        // This will leave us with a total confidence sum that
        // our fuzzy matching should be close to.
        long exactMatchConfidenceSum = this.exactMatchCount * 100;
        double percentCorrect = ((double)this.identifiedMatchCount / this.exactMatchCount) * 100;

        System.out.println();
        System.out.println("Identified Known Matches " + this.identifiedMatchCount);
        System.out.println("Total Known Matches: " + this.exactMatchCount);
        System.out.println();
        System.out.printf("Identified Known Match Confidence Sum: %.2f%n", this.identifiedMatchConfidenceSum);
        System.out.println("Total Known Match Confidence Sum: " + exactMatchConfidenceSum);
        System.out.println();
        System.out.printf("Correctly identified %.2f%% of exact matches.%n", percentCorrect);
        System.out.printf("Confidence Score: %.1f%n",
                (this.identifiedMatchConfidenceSum / exactMatchConfidenceSum) * 100);

        double time = calcTime();
        if (time > 120) {
            time = time / 60;
            System.out.println("\nTime taken: " + time + " minutes\n");
        } else {
            System.out.println("\nTime taken: " + time + " seconds\n");
        }
    }

    private double calcTime() {
        return ( (this.endTime-this.startTime) / 1000000000.0 );
    }
}
