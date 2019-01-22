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
 * Developed by Gregory Smith & Axel Solano. Last modified 1/17/19 9:36 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package data;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PerformanceMeasure {

    private double totalRunTime;
    private double parseDataTime;
    private double matcherTime;
    private int identifiedMatchCount;
    private double identifiedMatchConfidenceSum;
    private long exactMatchCount;

    public PerformanceMeasure() {
        this.identifiedMatchConfidenceSum = 0.0;
        this.exactMatchCount = 0;
    }

    public void measureAccuracy(ArrayList<MasterContact> masterContacts) {
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

    public void resultsToFile() {

        String PATH = "./out/performance/";
        String title = "results_";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:");
        Date date = new Date();
        String currentDate = dateFormat.format(date);
        String extension = ".txt";

        String FILENAME = PATH + title + currentDate + extension;

        long knownMatchConfidenceSum = knownMatchConfidenceSum();
        double percentCorrect = percentCorrect();
        double confidenceScore = confidenceScore(knownMatchConfidenceSum);

        try {

            FileWriter fileWriter = new FileWriter(FILENAME);
            PrintWriter writer = new PrintWriter(fileWriter);

            writer.println("Identified Known Matches " + this.identifiedMatchCount);
            writer.println("Total Known Matches: " + this.exactMatchCount);
            writer.println();
            writer.printf("Identified Known Match Confidence Sum: %.2f%n", this.identifiedMatchConfidenceSum);
            writer.println("Total Known Match Confidence Sum: " + knownMatchConfidenceSum);
            writer.println();
            writer.printf("Correctly identified %.2f%% of known matches.%n", percentCorrect);
            writer.printf("Confidence Score: %.1f / 100%n", confidenceScore);

            writer.println();
            writer.printf("Time taken to parse data: %s", timeToStr(this.parseDataTime));
            writer.printf("Time taken to match data: %s", timeToStr(this.matcherTime));
            writer.println();
            writer.printf("Total time taken: %s", timeToStr(this.totalRunTime));

            writer.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public void printResults(String formatter) {

        // Here we multiply the size of the list by 100.
        // This is because our measure of confidence is on a scale
        // of 0 to 100. (100 being a perfect match). If the CRD
        // numbers match, we should expect a confidence of 100.

        // This will leave us with a total confidence sum that
        // our fuzzy matching should be close to.
        long knownMatchConfidenceSum = knownMatchConfidenceSum();
        double percentCorrect = percentCorrect();
        double confidenceScore = confidenceScore(knownMatchConfidenceSum);

        System.out.println();
        System.out.println(formatter + "Identified Known Matches " + this.identifiedMatchCount);
        System.out.println(formatter + "Total Known Matches: " + this.exactMatchCount);
        System.out.println();
        System.out.printf(formatter + "Identified Known Match Confidence Sum: %.2f%n",
                this.identifiedMatchConfidenceSum);
        System.out.println(formatter + "Total Known Match Confidence Sum: " + knownMatchConfidenceSum);
        System.out.println();
        System.out.printf(formatter + "Correctly identified %.2f%% of known matches.%n", percentCorrect);
        System.out.printf(formatter + "Confidence Score: %.1f / 100%n", confidenceScore);

        System.out.println();
        System.out.printf(formatter + "Time taken to parse data: %s", timeToStr(this.parseDataTime));
        System.out.printf(formatter + "Time taken to match data: %s", timeToStr(this.matcherTime));
        System.out.println();
        System.out.printf(formatter + "Total time taken: %s", timeToStr(this.totalRunTime));

    }

    private double confidenceScore(long knownMatchConfidenceSum) {
        return (this.identifiedMatchConfidenceSum / knownMatchConfidenceSum) * 100;
    }

    private long knownMatchConfidenceSum() {
        return this.exactMatchCount * 100;
    }

    private double percentCorrect() {
        return ((double) this.identifiedMatchCount / this.exactMatchCount) * 100;
    }

    public void setTotalRunTime(long startTime, long endTime) {
        this.totalRunTime = calcTime(startTime, endTime);
    }

    public void setParseDataTime(long startTime, long endTime) {
        this.parseDataTime = calcTime(startTime, endTime);
    }

    public void setMatcherTime(long startTime, long endTime) {
        this.matcherTime = calcTime(startTime, endTime);
    }

    private double calcTime(long startTime, long endTime) {
        return ( (endTime-startTime) / 1000000000.0 );
    }

    private String timeToStr(double time) {

        String timeString;

        if (time > 120) {

            time = time / 60;
            timeString = String.format("%.2f minutes\n", time);

        } else {

            timeString = String.format("%.2f seconds\n", time);

        }

        return timeString;

    }
}
