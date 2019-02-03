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

import data.CSVReader;
import indexing.Indexer;
import setup.Init;
import utils.Performance;


public class MatchMain {

    // MASTERPATH - "./data/contact_master.csv"
    // MATCHPATH - "./data/contact_match.csv"

    public static void main(String[] args) {


        runBoth(true);
        //runBothSep("metaphone", false);
        //System.out.println(System.getProperty("os.name"));


    }

    @SuppressWarnings("Duplicates")
    public static void runBoth(boolean printMatches) {

        long totalStart, parseDataEnd, matchTimeEnd;

        Performance measure = new Performance();

        Init init = new Init();

        Indexer indexer = init.getIndexer();
        CSVReader csvReader = init.getCsvReader();
        Matcher matcher = init.getMatcher();

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

        if (printMatches)
            measure.printMatches();

        measure.printResults();






//        Weights weights1 = new Weights("./config/weights/");
//        Weights weights2 = new Weights("./config/weights/");
//
//        weights1.initialize("weights1.json");
//        weights2.initialize("weights2.json");
//
//        long totalStart, parseDataEnd, matchTimeEnd;
//
//        CSVReader csv;
//        Indexer indexer;
//        Matcher matcher;
//
//        //---------------------------RUN TRAIN SET-----------------------------------
//        Performance measure = new Performance();
//        indexer = new Indexer();
//
//        totalStart = System.nanoTime();
//
//        csv = new CSVReader(indexer, idxMethod);
//
//        csv.readMaster("./data/sampledata/contact_master.csv");
//        csv.readMatch("./data/sampledata/matches/contact_match.csv", false);
//        csv.readMatch("./data/sampledata/matches/contact_match_alt.csv", true);
//
//        parseDataEnd = System.nanoTime();
//
//        matcher = new Matcher(indexer, weights1, weights2, false);
//        matcher.setThreshold(70);
//        matcher.run();
//
//        matchTimeEnd = System.nanoTime();
//
//        measure.setParseDataTime(totalStart, parseDataEnd);
//        measure.setMatcherTime(parseDataEnd, matchTimeEnd);
//        measure.setTotalRunTime(totalStart, matchTimeEnd);
//        measure.measure(indexer, matcher);
//
//        if (printMatches)
//            measure.printMatches();
//
//        measure.printResults();
        //trainMeasure.resultsToFile("contact_match");


    }

    public static void runBothSep(boolean printMatches) {

        matchOne("contact_match.csv",false, printMatches);
        matchOne("contact_match_alt.csv",true, printMatches);

    }

    @SuppressWarnings("Duplicates")
    public static void matchOne(String matchFile, boolean alt, boolean printMatches) {

        long totalStart, parseDataEnd, matchTimeEnd;

        Performance measure = new Performance();

        Init init = new Init();

        Indexer indexer = init.getIndexer();
        CSVReader csvReader = init.getCsvReader();
        Matcher matcher = init.getMatcher();

        totalStart = System.nanoTime();

        // Read data, process it, and index it
        csvReader.readMaster("contact_master.csv");
        csvReader.readMatch(matchFile, alt);

        parseDataEnd = System.nanoTime();

        matcher.run(indexer);

        matchTimeEnd = System.nanoTime();

        measure.setParseDataTime(totalStart, parseDataEnd);
        measure.setMatcherTime(parseDataEnd, matchTimeEnd);
        measure.setTotalRunTime(totalStart, matchTimeEnd);
        measure.measure(indexer, matcher);

        if (printMatches)
            measure.printMatches();

        measure.printResults();

    }

}
