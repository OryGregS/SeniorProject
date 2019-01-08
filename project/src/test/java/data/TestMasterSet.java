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

import static org.junit.Assert.assertTrue;

import data.MasterSet;
import org.junit.Test;

public class TestMasterSet {

    MasterSet master = new MasterSet();
    String masterPath = "./data/contact_master.csv";

    @Test
    public void readCSVTest() { assertTrue(master.readCSV(masterPath)); }

    @Test
    public void headTest() {
        master.readCSV(masterPath);
    }

}
