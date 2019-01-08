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
 * Developed by Gregory Smith & Axel Solano. Last modified 08/01/19 5:34 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package wmu.datamatching;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


public class TestMatchSet {

    MatchSet match = new MatchSet();
    String matchPath = "./data/contact_match.csv";

    @Test
    public void readCSVTest() { assertTrue(match.readCSV(matchPath)); }

    @Test
    public void headTest() {
        match.readCSV(matchPath);
    }

    @Test
    public void testCheckNull() {
        assertTrue(match.checkNULL("NULL").equals(""));
    }

}
