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
 * Developed by Gregory Smith & Axel Solano. Last modified 1/26/19 2: PM.
 * Copyright (c) 19. All rights reserved.
 */

package utils;

import data.MasterContact;
import indexing.Indexer;
import matching.Matcher;

import java.util.ArrayList;

public class CalcPerformance {

    double totalRunTime = 0;
    double parseDataTime = 0;
    double matcherTime = 0;
    int identifiedMatchCount = 0;
    double percentFound = 0;
    long knownMatchCount = 0;
    long masterSize = 0;
    long matchSize = 0;
    int totalUnknownCount = 0;
    int numComparisons = 0;
    long maxComparisons = 0;
    double percentReduced = 0;
    ArrayList<MasterContact> masterContacts;

    public void measure(Indexer indexer, Matcher matcher) {

        this.masterContacts = indexer.getAllMasterContacts();

        this.masterSize = indexer.getMasterSize();
        this.matchSize = indexer.getMatchSize();


        for (int i = 0; i < masterSize; i++) {
            findMatches(this.masterContacts.get(i));
        }

        this.percentFound = ((double) this.identifiedMatchCount / (double) this.knownMatchCount) * 100;
        this.numComparisons = matcher.getNumComparisons();

        this.maxComparisons = this.masterSize * this.matchSize;
        this.percentReduced = (1.0 - ((double) this.numComparisons / (double) maxComparisons)) * 100;

    }

    private void findMatches(MasterContact contact) {

        this.knownMatchCount += contact.getKnownMatches();
        this.identifiedMatchCount += contact.getIdentifiedMatchCount();
        this.totalUnknownCount += contact.getUnknownMatches();

    }

    public void setTotalRunTime(long startTime, long endTime) {
        this.totalRunTime = calcTime(startTime, endTime);
    }

    public void setParseDataTime(long startTime, long endTime) {
        this.parseDataTime = calcTime(startTime, endTime);
    }

    public void setMatcherTime(long startTime, long endTime) {
        this.matcherTime = calcTime(startTime, endTime);
    }

    private double calcTime(long startTime, long endTime) {
        return ((endTime - startTime) / 1000000000.0);
    }

    public double getPercentFound() {
        return this.percentFound;
    }

}
