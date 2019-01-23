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

import static org.junit.Assert.assertNotNull;

public class TestDataLoader {

    private final String masterPath = "./data/contact_master.csv";
    private final String matchPath = "./data/contact_match.csv";
    private String indexMethod = "dblmp";

    private long startTime, estimatedTime;
    private double seconds;



    @Test
    public void loaderDataTest(){
        Indexer indexer = new Indexer();
        DataLoader dataLoader = new DataLoader(indexer, this.indexMethod);
        assertNotNull(dataLoader);
    }

    @Test
    public void loadDataFromCSVTest() {
        Indexer indexer = new Indexer();
        DataLoader loader = new DataLoader(indexer, this.indexMethod);
        loader.loadDataFromCSV(masterPath, matchPath, false);
        assertNotNull(loader.getMasterSet());
        assertNotNull(loader.getMatchSet());

    }

    @Test
    public void readMasterTime() {
        Indexer indexer = new Indexer();
        DataLoader loader = new DataLoader(indexer, this.indexMethod);
        loader.loadDataFromCSV(masterPath, matchPath, false);
        startTime = System.nanoTime();
        loader.readMasterCSV(masterPath);
        estimatedTime = System.nanoTime() - startTime;
        seconds = ((double)estimatedTime / 1000000000.0);
        System.out.println("\nTime to read Master data: " + seconds + " seconds");
    }

    @Test
    public void readMatchTime() {
        Indexer indexer = new Indexer();
        DataLoader loader = new DataLoader(indexer, this.indexMethod);
        startTime = System.nanoTime();
        loader.readMatchCSV(matchPath, false);
        estimatedTime = System.nanoTime() - startTime;
        seconds = ((double)estimatedTime / 1000000000.0);
        System.out.println("\nTime to read Matching data: " + seconds + " seconds");
    }

}


//    Time getMasterSetTimeTest(): 0.60691053 seconds
//
//    Time getMatchSetTimeTest(): 0.355667105 seconds