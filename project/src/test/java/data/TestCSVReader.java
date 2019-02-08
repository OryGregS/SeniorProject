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

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class TestCSVReader {

    private String masterPath = "./data/sampledata/";
    private String matchPath = "./data/sampledata/matches/";

    @Test
    public void testReadFromCSV() {
        Indexer indexer = new Indexer("metaphone");
        CSVReader csvReader = new CSVReader(this.masterPath,
                this.matchPath, indexer);
        assertTrue(csvReader.readMaster("contact_master.csv"));
        assertTrue(csvReader.readMatch("contact_match.csv"));
        assertTrue(csvReader.readMatch("contact_match_alt.csv"));
        assertEquals(21450, csvReader.getMasterCount());
        assertEquals(49625, csvReader.getMatchCount());


    }

}
