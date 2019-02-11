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
 * Developed by Gregory Smith & Axel Solano. Last modified 1/27/19 1:55 PM.
 * Copyright (c) 2019. All rights reserved.
 */

package utils;

import dataholder.Contact;
import dataholder.MasterContact;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Performance is where information gets printed to stdout. Subclass of CalcPerformance.
 */
public class Performance extends CalcPerformance {

    /**
     * Writes performance output to a file.
     *
     * @param fileName Name of file for outputting results.
     *                 (The current time is appended to the file name).
     */
    public void resultsToFile(String fileName) {


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();


        String currentDate = dateFormat.format(date);
        String currentTime = timeFormat.format(date);

        String PATH = "./out/performance/" + currentDate;
        String extension = ".txt";

        File subDir = new File(PATH);
        boolean createdDir = subDir.mkdir();
        boolean dirExists = subDir.exists();

        if (createdDir || dirExists) {

            PATH += "/";
            // ./out/performance/19-01-01/fileName_10:00.txt
            String FILENAME = PATH + fileName + "_" + currentTime + extension;

            try {

                FileWriter fileWriter = new FileWriter(FILENAME);
                PrintWriter writer = new PrintWriter(fileWriter);

                writer.printf("Master Size: %,49d\n", this.masterSize);
                writer.printf("Match Size: %,50d\n", this.matchSize);
                writer.println();
                writer.printf("Total # of comparisons: %,38d\n", this.numComparisons);
                writer.printf("Max # of comparisons: %,40d\n", this.maxComparisons);
                writer.printf("Reduced comparisons by: %38.1f%%\n", this.percentReduced);
                writer.println();
                writer.printf("Identified Known Matches: %,36d\n", this.identifiedMatchCount);
                writer.printf("Total Known Matches: %,41d\n", this.knownMatchCount);
                writer.printf("Unknown matches found (confidence >= 90): %,20d\n", this.totalUnknownCount);
                writer.println();
                writer.printf("Known matches correctly identified %,26.2f%%\n", percentFound);
                writer.println();
                writer.printf("Time taken to parse dataholder: %37s", timeToStr(this.parseDataTime));
                writer.printf("Time taken to match dataholder: %37s", timeToStr(this.matcherTime));
                writer.println();
                writer.printf("Total time taken: %45s", timeToStr(this.totalRunTime));

            } catch (IOException e) {

                e.printStackTrace();

            }

        } else {

            System.out.println();
            System.out.println("Failed to create subdirectory.");
            System.out.println();

        }
    }

    /**
     * Public method to print performance results (with dividers).
     *
     * @param indexMethod Field to use for determining record similarity.
     * @param encoder     Phonetic encoding method used.
     */
    public void printResults(String indexMethod, String encoder) {

        resultDiv();
        printInfo(indexMethod, encoder);
        resultDiv();

    }

    /**
     * Prints performance results.
     *
     * @param indexMethod Field to use for determining record similarity.
     * @param encoder     Phonetic encoding method used.
     */
    private void printInfo(String indexMethod, String encoder) {
        System.out.println();
        System.out.println("Results: ");
        System.out.println();
        System.out.printf("\tMaster Size: %,49d\n", this.masterSize);
        System.out.printf("\tMatch Size: %,50d\n", this.matchSize);
        System.out.println();
        System.out.printf("\tIndex Method: %48s\n", indexMethod);
        System.out.printf("\tEncode Method: %47s\n", encoder);
        System.out.println();
        System.out.printf("\tTotal # of comparisons: %,38d\n", this.numComparisons);
        System.out.printf("\tMax # of comparisons: %,40d\n", this.maxComparisons);
        System.out.printf("\tReduced comparisons by: %37.2f%%\n", this.percentReduced);
        System.out.println();

        // Known matches is 0 for matching a master data set against itself.
        // This is because CRD numbers don't repeat in the Master record.
        // Thus, we omit this information as it doesn't make sense in this case.
        if (this.knownMatchCount != 0) {

            System.out.printf("\tIdentified Known Matches: %,36d\n", this.identifiedMatchCount);
            System.out.printf("\tTotal Known Matches: %,41d\n", this.knownMatchCount);
            System.out.printf("\tKnown matches correctly identified %,26.2f%%\n", percentFound);
            System.out.println();

        }

        System.out.printf("\tUnknown matches found (confidence >= 90): %,20d\n", this.totalUnknownCount);
        System.out.println();
        System.out.printf("\tTime taken to parse data: %37s", timeToStr(this.parseDataTime));
        System.out.printf("\tTime taken to match data: %37s", timeToStr(this.matcherTime));
        System.out.println();
        System.out.printf("\tTotal time taken: %45s", timeToStr(this.totalRunTime));
    }

    /**
     * Formats time values for easier understanding. A time over 60 seconds
     * is converted to minutes, while a time under 60 seconds is kept in seconds.
     *
     * @param time Time value to format (double).
     * @return String value of the time.
     */
    private String timeToStr(double time) {

        String timeString;

        if (time > 60) {

            time = time / 60;
            timeString = String.format("%.2f m\n", time);

        } else {

            timeString = String.format("%.2f s\n", time);

        }

        return timeString;

    }

    /**
     * Loops through each MasterContact for printing.
     */
    public void printMatches() {

        this.masterContacts.forEach(this::printMasterContact);

    }

    /**
     * Gets the top matching Contacts and their confidence for printing.
     *
     * @param masterContact MasterContact object.
     */
    private void printMasterContact(MasterContact masterContact) {

        ArrayList<Double> confidence = masterContact.getTopConfidence();
        ArrayList<Contact> contacts = masterContact.getTopContacts();
        printMatches(masterContact, confidence, contacts);

    }

    /**
     * Prints the MasterContact data and top matching Contacts and their confidence.
     *
     * @param master     MasterContact object.
     * @param confidence ArrayList of confidence values (Double).
     * @param contacts   ArrayList of matching Contacts.
     */
    private void printMatches(MasterContact master, ArrayList<Double> confidence, ArrayList<Contact> contacts) {

        matchDiv();
        System.out.print("MasterContact: \t\t ");
        master.printAll();
        matchDiv();

        int listSize = confidence.size();
        for (int i = 0; i < listSize; i++) {

            System.out.printf("\nConfidence: %6.2f | ", confidence.get(i));
            contacts.get(i).printAll();

        }

        System.out.println();

    }

    /**
     * Divider for printing results.
     */
    private void resultDiv() {
        System.out.println("\n-------------------------------------------------------------------");
    }

    /**
     * Divider for printing top matches.
     */
    private void matchDiv() {
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
                "----------------------------\n");
    }

}
