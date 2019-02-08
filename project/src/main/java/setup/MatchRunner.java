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
 * Developed by Gregory Smith & Axel Solano. Last modified 2/7/19 11:11 PM.
 * Copyright (c) 2019. All rights reserved.
 */

package setup;

import utils.Performance;

import java.io.File;

public class MatchRunner extends Init {

    private Performance performance;

    public MatchRunner() {
        super();
    }

    @SuppressWarnings("Duplicates")
    public void masterToMaster(String masterFileName) {

        this.performance = new Performance();

        long parseDataTimeStart = System.nanoTime();

        this.csvReader.readMaster(masterFileName, this.topMatchesListSize);
        this.csvReader.readMatch(masterFileName);

        long parseDataTimeEnd = System.nanoTime();

        // tell matcher to skip comparing the same record
        this.matcher.matchMasterToMaster();
        this.matcher.runMatcher(this.indexer);

        long matchDataTimeEnd = System.nanoTime();

        measure(parseDataTimeStart, parseDataTimeEnd, matchDataTimeEnd);

        if (this.printTopMatches)
            performance.printMatches();

        performance.printResults();

    }

    @SuppressWarnings("Duplicates")
    public void masterToMatch(String masterFileName, String matchFileName) {

        this.performance = new Performance();

        long parseDataTimeStart = System.nanoTime();

        this.csvReader.readMaster(masterFileName, this.topMatchesListSize);
        this.csvReader.readMatch(matchFileName);

        long parseDataTimeEnd = System.nanoTime();

        this.matcher.runMatcher(this.indexer);

        long matchDataTimeEnd = System.nanoTime();

        measure(parseDataTimeStart, parseDataTimeEnd, matchDataTimeEnd);

        if (this.printTopMatches)
            performance.printMatches();

        performance.printResults();

    }

    @SuppressWarnings("Duplicates")
    public void masterToMatches(String masterFileName) {

        this.performance = new Performance();

        long parseDataTimeStart = System.nanoTime();

        this.csvReader.readMaster(masterFileName, this.topMatchesListSize);
        readAllMatchFiles();

        long parseDataTimeEnd = System.nanoTime();

        this.matcher.runMatcher(this.indexer);

        long matchDataTimeEnd = System.nanoTime();

        measure(parseDataTimeStart, parseDataTimeEnd, matchDataTimeEnd);

        if (this.printTopMatches)
            performance.printMatches();

        performance.printResults();

    }

    private void measure(long parseDataTimeStart, long parseDataTimeEnd, long matchDataTimeEnd) {
        this.performance.setParseDataTime(parseDataTimeStart, parseDataTimeEnd);
        this.performance.setMatcherTime(parseDataTimeEnd, matchDataTimeEnd);
        this.performance.setTotalRunTime(parseDataTimeStart, matchDataTimeEnd);
        this.performance.measure(this.indexer, this.matcher);
    }

    void readAllMatchFiles() {

        File matchFolder = new File(this.matchPath);

        File[] matchFiles = matchFolder.listFiles();

        if (matchFiles != null) {

            int numMatchFiles = matchFiles.length;

            for (int i = 0; i < numMatchFiles; i++) {

                if (matchFiles[i].isFile()) {
                    // load all files in matches directory
                    this.csvReader.readMatch(matchFiles[i].getName());

                }
            }
        }
    }
}
