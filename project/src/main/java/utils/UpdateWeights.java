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
 * Developed by Gregory Smith & Axel Solano. Last modified 1/26/19 3:48 PM.
 * Copyright (c) 2019. All rights reserved.
 */

package utils;

import data.CSVReader;
import indexing.Indexer;
import matching.Matcher;
import matching.Weights;
import setup.Init;

import java.util.Scanner;

public class UpdateWeights {

    private Weights best_altWeights;
    private Weights best_defaultWeights;

    public static void main(String args[]) {

        new UpdateWeights().testSample(1200, "metaphone");

    }

    @SuppressWarnings("Duplicates")
    public void testSample(int amount, String idx) {

        int i, j;
        long totalStart, parseDataEnd, matchTimeEnd = 0;
        boolean print = false;

        double bestPercentFound = 0;

        double[] email_weights = new double[10];

        int emailSz = email_weights.length;
        double val = .30;

        for (i = 0; i < emailSz; i++) {
            val = Math.floor(val * 1000) / 1000;
            email_weights[i] = val;
            val += .01;
        }


        for (i = 0; i < amount; i++) {

            for (j = 0; j < emailSz; j++) {


                //---------------------------RUN TRAIN SET-----------------------------------

                Performance measure = new Performance();

                Init init = new Init();

                Weights weights1 = new Weights("./config/weights/");
                Weights weights2 = new Weights("./config/weights/");

                weights1.initialize("weights1.json");
                weights2.initialize("weights2.json");

                weights1.getRandomWeights(email_weights[i], false);
                weights2.getRandomWeights(email_weights[i], true);

                Indexer indexer = init.getIndexer();
                CSVReader csvReader = init.getCsvReader();
                Matcher matcher = new Matcher(weights1, weights2);
                totalStart = System.nanoTime();

                // Read data, process it, and index it
                csvReader.readMaster("contact_master.csv");
                csvReader.readMatch("contact_match.csv", false);
                csvReader.readMatch("contact_match_alt.csv", true);

                parseDataEnd = System.nanoTime();

                matcher.run(indexer);

                matchTimeEnd = System.nanoTime();

                measure.setParseDataTime(totalStart, parseDataEnd);
                measure.setMatcherTime(parseDataEnd, matchTimeEnd);
                measure.setTotalRunTime(totalStart, matchTimeEnd);
                measure.measure(indexer, matcher);

                double temp = measure.getPercentFound();

                if (temp > bestPercentFound) {
                    measure.printResults();
                    bestPercentFound = temp;
                }


            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nStore highest weights in ./config/weights/test/ ?\n");
        String writeJSON = scanner.nextLine();
        if (writeJSON.equalsIgnoreCase("y") ||
                writeJSON.equalsIgnoreCase("yes")) {

            this.best_altWeights.writeJSON();
            this.best_defaultWeights.writeJSON();

        }
    }
}

