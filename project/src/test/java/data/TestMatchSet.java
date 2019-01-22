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
 * Developed by Gregory Smith & Axel Solano. Last modified 08/01/19 6:07 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package data;

import indexing.Indexer;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class TestMatchSet {

    private Indexer indexer = new Indexer();
    private MatchSet match = new MatchSet(indexer);
    private String matchPath = "./data/contact_match.csv";
    private String altPath = "./data/contact_match_alt.csv";

    @Test
    public void readCSVTest() { assertTrue(match.readCSV(matchPath, false, true)); }

    @Test
    public void readAltCSVTest() {
        match.readCSV(altPath, true, true);
    }

}
