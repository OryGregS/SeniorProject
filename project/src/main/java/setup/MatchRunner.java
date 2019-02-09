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

import processing.CSVInputException;
import utils.Performance;

import java.io.File;

/**
 * MatchRunner is the main "driver" of the program. We extend Init to
 * access DataMatching.properties and also access the initialized objects.
 */
public class MatchRunner extends Init {

    private Performance performance;

    /**
     * Call our parent class's constructor to get properties and initialize object's states.
     */
    public MatchRunner() {
        super();
    }

    /**
     * Match a master record to itself. This calls an extra flag in
     *
     * @param masterFileName File name of master record (CSV) in masterPath.
     */
    @SuppressWarnings("Duplicates")
    public void masterToMaster(String masterFileName) {

        this.performance = new Performance();

        long parseDataTimeStart = System.nanoTime();

        try {

            this.csvReader.readMaster(masterFileName, this.topMatchesListSize);
            this.csvReader.setMatchPath(this.masterPath);
            this.csvReader.readMatch(masterFileName);

        } catch (CSVInputException e) {
            e.printStackTrace();
            System.exit(1);
        }

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

    /**
     * Match the master record against a single matching record.
     *
     * @param masterFileName File name of master record (CSV) in masterPath.
     * @param matchFileName  File name of matching record (CSV) in matchPath.
     */
    @SuppressWarnings("Duplicates")
    public void masterToMatch(String masterFileName, String matchFileName) {

        this.performance = new Performance();

        long parseDataTimeStart = System.nanoTime();

        try {

            this.csvReader.readMaster(masterFileName, this.topMatchesListSize);
            this.csvReader.readMatch(matchFileName);

        } catch (CSVInputException e) {
            e.printStackTrace();
            System.exit(1);
        }

        long parseDataTimeEnd = System.nanoTime();

        this.matcher.runMatcher(this.indexer);

        long matchDataTimeEnd = System.nanoTime();

        measure(parseDataTimeStart, parseDataTimeEnd, matchDataTimeEnd);

        if (this.printTopMatches)
            performance.printMatches();

        performance.printResults();

    }

    /**
     * Match the master record against multiple matching records.
     * This function grabs all CSV files that are in the matchPath
     * and matches them all as a single large record.
     *
     * @param masterFileName File name of master record (CSV) in masterPath.
     */
    @SuppressWarnings("Duplicates")
    public void masterToMatches(String masterFileName) {

        this.performance = new Performance();

        long parseDataTimeStart = System.nanoTime();

        try {

            this.csvReader.readMaster(masterFileName, this.topMatchesListSize);
            readAllMatchFiles();

        } catch (CSVInputException e) {
            e.printStackTrace();
            System.exit(1);
        }

        long parseDataTimeEnd = System.nanoTime();

        this.matcher.runMatcher(this.indexer);

        long matchDataTimeEnd = System.nanoTime();

        measure(parseDataTimeStart, parseDataTimeEnd, matchDataTimeEnd);

        if (this.printTopMatches)
            performance.printMatches();

        performance.printResults();

    }

    /**
     * Function to set performance measurement times.
     *
     * @param parseDataTimeStart Time reading and processing data started.
     * @param parseDataTimeEnd   Time reading and processing data ended and matching began.
     * @param matchDataTimeEnd   Time matching ended.
     */
    private void measure(long parseDataTimeStart, long parseDataTimeEnd, long matchDataTimeEnd) {
        this.performance.setParseDataTime(parseDataTimeStart, parseDataTimeEnd);
        this.performance.setMatcherTime(parseDataTimeEnd, matchDataTimeEnd);
        this.performance.setTotalRunTime(parseDataTimeStart, matchDataTimeEnd);
        this.performance.measure(this.indexer, this.matcher);
    }

    /**
     * Grabs all CSV files in the matchPath (set in DataMatching.properties).
     *
     * @throws CSVInputException Error finding or reading a CSV file.
     */
    void readAllMatchFiles() throws CSVInputException {

        File matchFolder = new File(this.matchPath);

        File[] matchFiles = matchFolder.listFiles();

        if (matchFiles != null) {

            int numMatchFiles = matchFiles.length;

            for (int i = 0; i < numMatchFiles; i++) {

                if (matchFiles[i].isFile()) {
                    // load all files in matches directory
                    try {
                        this.csvReader.readMatch(matchFiles[i].getName());
                    } catch (CSVInputException e) {
                        e.printStackTrace();
                        System.exit(1);
                    }

                }
            }
        } else {

            throw new CSVInputException("Error reading CSV files in path: " + this.matchPath);

        }

    }
}
