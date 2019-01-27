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
import utils.Performance;


public class MatchMain {

    // MASTERPATH - "./data/contact_master.csv"
    // MATCHPATH - "./data/contact_match.csv"

    public static void main(String[] args) {


        runBoth("metaphone", "ratio", false);
        //runBothSep("metaphone", "ratio", false);


    }

    @SuppressWarnings("Duplicates")
    public static void runBoth(String idxMethod, String cmpMethod, boolean printMatches) {


        Weights weights1 = new Weights(false);
        Weights weights2 = new Weights(true);

        long totalStart, parseDataEnd, matchTimeEnd;

        CSVReader csv;
        Indexer indexer;
        RecordMatcher matcher;

        //---------------------------RUN TRAIN SET-----------------------------------
        Performance measure = new Performance();
        indexer = new Indexer();

        totalStart = System.nanoTime();

        csv = new CSVReader(indexer, idxMethod);

        csv.readMaster("./data/contact_master.csv");
        csv.readMatch("./data/contact_match.csv", false);
        csv.readMatch("./data/contact_match_alt.csv", true);

        parseDataEnd = System.nanoTime();

        matcher = new RecordMatcher(indexer, weights1, weights2, false);
        matcher.setThreshold(70);
        matcher.run(cmpMethod);

        matchTimeEnd = System.nanoTime();

        measure.setParseDataTime(totalStart, parseDataEnd);
        measure.setMatcherTime(parseDataEnd, matchTimeEnd);
        measure.setTotalRunTime(totalStart, matchTimeEnd);
        measure.measure(indexer, matcher);

        if (printMatches)
            measure.printMatches();

        measure.printResults();
        //trainMeasure.resultsToFile("contact_match");


    }

    public static void runBothSep(String idxMethod, String cmpMethod, boolean printMatches) {

        matchOne("./data/contact_match.csv", idxMethod, cmpMethod, false, printMatches);
        matchOne("./data/contact_match_alt.csv", idxMethod, cmpMethod, true, printMatches);

    }

    @SuppressWarnings("Duplicates")
    public static void matchOne(String matchFile, String idxMethod,
                                String cmpMethod, boolean alt, boolean printMatches) {

        Weights weights1 = new Weights(false);
        Weights weights2 = new Weights(true);

        long totalStart, parseDataEnd, matchTimeEnd;

        CSVReader csv;
        Indexer indexer;
        RecordMatcher matcher;

        //---------------------------RUN TRAIN SET-----------------------------------
        Performance measure = new Performance();
        indexer = new Indexer();

        totalStart = System.nanoTime();

        csv = new CSVReader(indexer, idxMethod);

        csv.readMaster("./data/contact_master.csv");
        csv.readMatch(matchFile, alt);

        parseDataEnd = System.nanoTime();

        matcher = new RecordMatcher(indexer, weights1, weights2, false);
        matcher.setThreshold(70);
        matcher.run(cmpMethod);

        matchTimeEnd = System.nanoTime();

        measure.setParseDataTime(totalStart, parseDataEnd);
        measure.setMatcherTime(parseDataEnd, matchTimeEnd);
        measure.setTotalRunTime(totalStart, matchTimeEnd);
        measure.measure(indexer, matcher);

        if (printMatches)
            measure.printMatches();

        measure.printResults(matchFile);

    }

}
