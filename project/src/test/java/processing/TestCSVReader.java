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
 * Developed by Gregory Smith & Axel Solano. Last modified 2/8/19 10:12 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package processing;

import indexing.Indexer;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TestCSVReader {

    private String masterPath = "./data/sampledata/";
    private String matchPath = "./data/sampledata/matches/";

    @Test
    @SuppressWarnings("Duplicates")
    public void testReadFromCSV() {
        Indexer indexer = new Indexer("metaphone");
        CSVReader csvReader = new CSVReader(this.masterPath,
                this.matchPath, indexer);
        try {
            csvReader.readMaster("contact_master.csv");
            csvReader.readMatch("contact_match.csv");
            csvReader.readMatch("contact_match_alt.csv");
        } catch (CSVInputException e) {
            e.printStackTrace();
            System.exit(1);
        }
        assertEquals(21450, csvReader.getMasterCount());
        assertEquals(49625, csvReader.getMatchCount());


    }

}
