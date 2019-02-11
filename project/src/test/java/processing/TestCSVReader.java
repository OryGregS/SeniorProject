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

import dataholder.Contact;
import dataholder.MasterContact;
import indexing.Indexer;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class TestCSVReader {

    private Indexer testIndex = new Indexer("lastname", "metaphone");

    @Test
    @SuppressWarnings("Duplicates")
    public void testReadFromCSV() {
        String testDataPath = "./src/test/.testFiles/";
        CSVReader csvReader = new CSVReader(testDataPath,
                testDataPath, testIndex);
        try {
            csvReader.readMaster("test_master.csv");
            csvReader.readMatch("test_match.csv");
        } catch (CSVInputException e) {
            e.printStackTrace();
            System.exit(1);
        }
        assertEquals(10, csvReader.getMasterCount());
        assertEquals(29585, csvReader.getMatchCount());

    }

    public Indexer getTestIndex() {
        return this.testIndex;
    }

    public ArrayList<MasterContact> getTestMasterList() {
        return this.testIndex.getAllMasterContacts();
    }

    public ArrayList<Contact> getTestMatchList() {
        return this.testIndex.getAllMatchContacts();
    }

}
