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

import data.DataLoader;
import data.MasterSet;
import data.MatchSet;
import data.PerformanceMeasure;

import java.util.concurrent.TimeUnit;



public class MatchMain {

    // MASTERPATH - "./data/contact_master.csv"
    // MATCHPATH - "./data/contact_match.csv"

    public static void main(String[] args) {

//        MatchMaker mm = new MatchMaker();
//        //mm.compareMasterToMaster("./data/contact_master.csv");
//        mm.compareMasterToOther("./data/contact_master.csv", "./data/contact_match.csv");

        DataLoader loader;
        MasterSet master;
        MatchSet match;
        RecordMatcher matcher;
        long totalStart, parseDataEnd, matchTimeEnd = 0;

        //---------------------------RUN TRAIN SET-----------------------------------
        PerformanceMeasure trainMeasure = new PerformanceMeasure();

        totalStart = System.nanoTime();

        loader = new DataLoader();
        loader.loadDataFromCSV("./data/contact_master.csv",
                "./data/contact_match.csv", false);

        master = loader.getMasterSet();
        match = loader.getMatchSet();

        parseDataEnd = System.nanoTime();

        matcher = new RecordMatcher(master, match, false, 1.0);
        matcher.printRun(true);
        matcher.run("ratio");

        matchTimeEnd = System.nanoTime();

        trainMeasure.setParseDataTime(totalStart, parseDataEnd);
        trainMeasure.setMatcherTime(parseDataEnd, matchTimeEnd);
        trainMeasure.setTotalRunTime(totalStart, matchTimeEnd);
        trainMeasure.measureAccuracy(master.getContactList());

        System.out.println();
        System.out.println("Done matching contact_match.csv.");

        try {

            TimeUnit.SECONDS.sleep(2);
            System.out.println();
            System.out.println("Matching AA_CONTACT_MATCH.csv...");
            TimeUnit.SECONDS.sleep(2);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //---------------------------RUN VALIDATION SET----------------------------------


        PerformanceMeasure valMeasure = new PerformanceMeasure();

        totalStart = System.nanoTime();

        loader = new DataLoader();

        loader.loadDataFromCSV("./data/contact_master.csv",
                "./data/AA_CONTACT_MATCH.csv", true);

        master = loader.getMasterSet();
        match = loader.getMatchSet();

        parseDataEnd = System.nanoTime();

        matcher = new RecordMatcher(master, match, false, 1.0);
        matcher.printRun(true);
        matcher.run("ratio");

        matchTimeEnd = System.nanoTime();

        System.out.println();
        System.out.println("Done matching AA_CONTACT_MATCH.csv.");

        valMeasure.setParseDataTime(totalStart, parseDataEnd);
        valMeasure.setMatcherTime(parseDataEnd, matchTimeEnd);
        valMeasure.setTotalRunTime(totalStart, matchTimeEnd);
        valMeasure.measureAccuracy(master.getContactList());

        //------------------------------PRINT RESULTS---------------------------------------
        System.out.println("\nMatch Data Results:");
        trainMeasure.printResults("\t");

        System.out.println("\nAlternate Match Data Results:");
        valMeasure.printResults("\t");
        //trainMeasure.resultsToFile();

    }

}
