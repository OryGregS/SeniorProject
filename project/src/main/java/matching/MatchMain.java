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

import data.DataLoader;
import data.PerformanceMeasure;
import indexing.Indexer;
import java.util.concurrent.TimeUnit;


public class MatchMain {

    // MASTERPATH - "./data/contact_master.csv"
    // MATCHPATH - "./data/contact_match.csv"

    public static void main(String[] args) {

        DataLoader loader;
        Indexer indexer;
        RecordMatcher matcher;
        long totalStart, parseDataEnd, matchTimeEnd = 0;


        String indexMethod = "rsoundex";
        boolean print = false;

        //---------------------------RUN TRAIN SET-----------------------------------
        PerformanceMeasure trainMeasure = new PerformanceMeasure();
        indexer = new Indexer();

        totalStart = System.nanoTime();

        loader = new DataLoader(indexer, indexMethod);
        loader.loadDataFromCSV("./data/contact_master.csv",
                "./data/contact_match.csv", false);

        parseDataEnd = System.nanoTime();

        matcher = new RecordMatcher(indexer, false);
        matcher.printRun(print);
        matcher.run("ratio");

        matchTimeEnd = System.nanoTime();

        trainMeasure.setParseDataTime(totalStart, parseDataEnd);
        trainMeasure.setMatcherTime(parseDataEnd, matchTimeEnd);
        trainMeasure.setTotalRunTime(totalStart, matchTimeEnd);
        trainMeasure.measureAccuracy(indexer);
        //trainMeasure.printResults("\t");
        //trainMeasure.resultsToFile("contact_match");

        System.out.println();
        System.out.println("Done matching contact_match.csv.");

        try {

            TimeUnit.SECONDS.sleep(2);
            System.out.println();
            System.out.println("Matching contact_match_alt.csv...");
            TimeUnit.SECONDS.sleep(2);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //---------------------------RUN VALIDATION SET----------------------------------


        PerformanceMeasure valMeasure = new PerformanceMeasure();
        indexer = new Indexer();

        totalStart = System.nanoTime();

        loader = new DataLoader(indexer, indexMethod);

        loader.loadDataFromCSV("./data/contact_master.csv",
                "./data/contact_match_alt.csv", true);

        parseDataEnd = System.nanoTime();

        matcher = new RecordMatcher(indexer, false);
        matcher.printRun(print);
        matcher.run("ratio");

        matchTimeEnd = System.nanoTime();

        System.out.println();
        System.out.println("Done matching contact_match_alt.csv.");

        valMeasure.setParseDataTime(totalStart, parseDataEnd);
        valMeasure.setMatcherTime(parseDataEnd, matchTimeEnd);
        valMeasure.setTotalRunTime(totalStart, matchTimeEnd);
        valMeasure.measureAccuracy(indexer);
        //valMeasure.resultsToFile("contact_match_alt");

        //------------------------------PRINT RESULTS---------------------------------------
        System.out.println("\nMatch Data Results:");
        trainMeasure.printResults("\t");


        System.out.println("\nAlternate Match Data Results:");
        valMeasure.printResults("\t");



    }

}
