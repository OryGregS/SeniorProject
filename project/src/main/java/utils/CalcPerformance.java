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

import dataholder.Contact;
import dataholder.MasterContact;
import indexing.Indexer;
import matching.Matcher;

import java.util.ArrayList;

/**
 * CalcPerformance contains holds data related to accuracy and number of matches.
 */
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
    ArrayList<Contact> matchContacts;

    /**
     * Uses Indexer and Matcher objects (after matching is completed) to
     * calculate the program's performance and accuracy.
     *
     * @param indexer Indexer object (after matching).
     * @param matcher Matcher object (after matching).
     */
    public void measure(Indexer indexer, Matcher matcher) {

        this.masterContacts = indexer.getAllMasterContacts();
        this.matchContacts = indexer.getAllMatchContacts();


        this.masterSize = indexer.getMasterSize();
        this.matchSize = indexer.getMatchSize();

        for (int i = 0; i < masterSize; i++) {

            //MasterContact masterContact = masterContacts.get(i);
            findMatches(masterContacts.get(i));
            /*
            this.knownMatchCount += matchContacts
                    .parallelStream()
                    .filter(contact -> trueMatch(masterContact, contact))
                    .count(); */

        }

        this.percentFound = ((double) this.identifiedMatchCount / (double) this.knownMatchCount) * 100;
        this.numComparisons = matcher.getNumComparisons();

        this.maxComparisons = this.masterSize * this.matchSize;
        this.percentReduced = (1.0 - ((double) this.numComparisons / (double) maxComparisons)) * 100;

    }

    /*
    private boolean trueMatch(MasterContact masterContact, Contact matchContact) {

        String masterCRD = masterContact.getCRDNumber();
        String matchCRD = matchContact.getCRDNumber();

        return ( (!masterCRD.equals("") && !matchCRD.equals("") ) && masterCRD.equalsIgnoreCase(matchCRD));

    }
    */

    /**
     * Counts the number of known matches a MasterContact has (CRD number match),
     * the number of those known matches that were stored in a MasterContact's
     * top list, and the number of unknown matches that were identified
     * (one of or both CRD numbers missing).
     *
     * @param masterContact MasterContact object.
     */
    private void findMatches(MasterContact masterContact) {

        this.knownMatchCount += masterContact.getKnownMatches();
        this.identifiedMatchCount += masterContact.getIdentifiedMatchCount();
        this.totalUnknownCount += masterContact.getUnknownMatches();

    }

    /**
     * Total time the program ran (reading data, preprocessing it, and matching).
     *
     * @param startTime Time the program started.
     * @param endTime   Time the program ended.
     */
    public void setTotalRunTime(long startTime, long endTime) {
        this.totalRunTime = calcTime(startTime, endTime);
    }

    /**
     * Time taken to read and preprocess data.
     *
     * @param startTime Time reading and preprocessing data started.
     * @param endTime   Time reading and preprocessing data ended.
     */
    public void setParseDataTime(long startTime, long endTime) {
        this.parseDataTime = calcTime(startTime, endTime);
    }

    /**
     * Time taken to match the matching data sets to the master.
     *
     * @param startTime Time matching started.
     * @param endTime   Time matching ended.
     */
    public void setMatcherTime(long startTime, long endTime) {
        this.matcherTime = calcTime(startTime, endTime);
    }

    /**
     * Calculates time by subtracting the starting time from the ending time.
     * Then divides by 1,000,000 to turn nanoseconds (long) into seconds (double).
     *
     * @param startTime Starting time.
     * @param endTime   Ending time.
     * @return Time spent in seconds.
     */
    private double calcTime(long startTime, long endTime) {
        return ((endTime - startTime) / 1000000000.0);
    }

    /**
     * Gets the percentage of identified known contacts in
     * relation to total known contacts. (IdentifiedKnown / TotalKnown).
     *
     * @return Ratio of known matches identified (between 0-100).
     */
    public double getPercentFound() {
        return this.percentFound;
    }

}
