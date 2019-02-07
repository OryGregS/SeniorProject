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

import data.Contact;
import data.MasterContact;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Performance extends CalcPerformance {

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
                writer.printf("Reduced comparisons by: %37.2f%%\n", this.percentReduced);
                writer.println();
                writer.printf("Identified Known Matches: %,36d\n", this.identifiedMatchCount);
                writer.printf("Total Known Matches: %,41d\n", this.knownMatchCount);
                writer.printf("Unknown matches found (confidence >= 90): %,20d\n", this.totalUnknownCount);
                writer.println();
                writer.printf("Known matches correctly identified %,26.2f%%\n", percentFound);
                writer.println();
                writer.printf("Time taken to parse data: %37s", timeToStr(this.parseDataTime));
                writer.printf("Time taken to match data: %37s", timeToStr(this.matcherTime));
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

    public void printResults() {

        resultDiv();
        printInfo();
        resultDiv();

    }

    public void printResults(String name) {

        resultDiv();
        System.out.println("\n" + name);
        printInfo();
        resultDiv();

    }

    private void printInfo() {
        System.out.println();
        System.out.println("Results: ");
        System.out.println();
        System.out.printf("\tMaster Size: %,49d\n", this.masterSize);
        System.out.printf("\tMatch Size: %,50d\n", this.matchSize);
        System.out.println();
        System.out.printf("\tTotal # of comparisons: %,38d\n", this.numComparisons);
        System.out.printf("\tMax # of comparisons: %,40d\n", this.maxComparisons);
        System.out.printf("\tReduced comparisons by: %37.2f%%\n", this.percentReduced);
        System.out.println();
        System.out.printf("\tIdentified Known Matches: %,36d\n", this.identifiedMatchCount);
        System.out.printf("\tTotal Known Matches: %,41d\n", this.knownMatchCount);
        System.out.printf("\tUnknown matches found (confidence >= 90): %,20d\n", this.totalUnknownCount);
        System.out.println();
        System.out.printf("\tKnown matches correctly identified %,26.2f%%\n", percentFound);
        System.out.println();
        System.out.printf("\tTime taken to parse data: %37s", timeToStr(this.parseDataTime));
        System.out.printf("\tTime taken to match data: %37s", timeToStr(this.matcherTime));
        System.out.println();
        System.out.printf("\tTotal time taken: %45s", timeToStr(this.totalRunTime));
    }

    private String timeToStr(double time) {

        String timeString;

        if (time > 120) {

            time = time / 60;
            timeString = String.format("%.2f m\n", time);

        } else {

            timeString = String.format("%.2f s\n", time);

        }

        return timeString;

    }

    public void printMatches() {

        this.masterContacts.forEach(this::printMasterContact);

    }

    private void printMasterContact(MasterContact master) {

        ArrayList<Double> confidence = master.getTopConfidence();
        ArrayList<Contact> contacts = master.getTopContacts();
        printMatches(master, confidence, contacts);

    }

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

    private void resultDiv() {
        System.out.println("\n-------------------------------------------------------------------");
    }

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
