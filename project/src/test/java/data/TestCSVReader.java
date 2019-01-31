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
 * Developed by Gregory Smith & Axel Solano. Last modified 1/28/19 1:27 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package data;

import indexing.Indexer;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertFalse;

public class TestCSVReader {

    private String masterPath = "./data/contact_master.csv";
    private String matchPath = "./data/contact_match.csv";
    private String altMatchPath = "./data/contact_match_alt.csv";
    private Indexer indexer;
    private String idxMethod;

    public TestCSVReader() {
        this.indexer = new Indexer();
        this.idxMethod = "metaphone";
    }

    @Test
    public void testReadFromCSV() {

        CSVReader csv = new CSVReader("./data/sampledata/",
                "./data/sampledata/matches/", indexer, idxMethod);
        assertTrue(csv.readMaster(masterPath));
        assertTrue(csv.readMatch(matchPath, false));
        assertTrue(csv.readMatch(altMatchPath, true));
        assertFalse(csv.readMatch(altMatchPath, false));
        assertFalse(csv.readMatch("asdfdsa.csv", true));
        assertFalse(csv.readMaster("asdfdsa.csv"));

    }

}
