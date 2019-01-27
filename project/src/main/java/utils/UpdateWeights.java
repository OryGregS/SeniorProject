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
import matching.RecordMatcher;
import matching.Weights;

import java.util.Scanner;

public class UpdateWeights {

    private Weights best_altWeights;
    private Weights best_defaultWeights;

    public static void main(String args[]) {

        new UpdateWeights().testSample(1000);

    }

    @SuppressWarnings("Duplicates")
    public void testSample(int amount) {

        int i, j;
        String idx = "rsoundex";
        String cmp = "ratio";
        long totalStart, parseDataEnd, matchTimeEnd = 0;
        boolean print = false;

        double bestPercentFound = 0;

        CSVReader csv;
        Indexer indexer;
        RecordMatcher matcher;

        double[] email_weights = new double[25];

        int emailSz = email_weights.length;
        double val = .20;

        for (i = 0; i < emailSz; i++) {
            val = Math.floor(val * 1000) / 1000;
            email_weights[i] = val;
            val += .01;
        }


        for (i = 0; i < amount; i++) {

            for (j = 0; j < emailSz; j++) {


                //---------------------------RUN TRAIN SET-----------------------------------
                CalcPerformance trainMeasure = new CalcPerformance();
                Weights weights1 = new Weights(true, false);
                Weights weights2 = new Weights(true, true);

                weights1.sample(email_weights[j]);
                weights2.sample(email_weights[j]);

                indexer = new Indexer();

                totalStart = System.nanoTime();

                csv = new CSVReader(indexer, idx);

                csv.readMaster("./data/contact_master.csv");
                csv.readMatch("./data/contact_match.csv", false);
                csv.readMatch("./data/contact_match_alt.csv", true);

                parseDataEnd = System.nanoTime();

                matcher = new RecordMatcher(indexer, weights1, weights2, false);
                matcher.run(cmp);

                matchTimeEnd = System.nanoTime();

                trainMeasure.setParseDataTime(totalStart, parseDataEnd);
                trainMeasure.setMatcherTime(parseDataEnd, matchTimeEnd);
                trainMeasure.setTotalRunTime(totalStart, matchTimeEnd);
                trainMeasure.measure(indexer, matcher);
                Performance performance = new Performance();
                double tempPercent = trainMeasure.getPercentFound();

                if (tempPercent > bestPercentFound) {
                    bestPercentFound = tempPercent;
                    System.out.println();
                    System.out.println("Run: " + (i + 1) + " - " + bestPercentFound + ":\n");
                    System.out.println("\tDefault:");
                    weights1.printWeights("\t\t");
                    System.out.println();
                    System.out.println("\tAlt:");
                    weights2.printWeights("\t\t");
                    System.out.println();
                    performance.printResults();
                    this.best_altWeights = weights2;
                    this.best_defaultWeights = weights1;
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

