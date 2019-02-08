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
 * Developed by Gregory Smith & Axel Solano. Last modified 08/01/19 5:35 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package matching;

import data.CSVReader;
import indexing.Indexer;
import setup.Init;
import setup.MatchRunner;
import utils.Performance;


public class MatchMain {

    // MASTERPATH - "./data/contact_master.csv"
    // MATCHPATH - "./data/contact_match.csv"

    public static void main(String[] args) {

        MatchRunner runner = new MatchRunner();
        for (int i = 0; i < 50; i++) {

            //runner.masterToMaster("contact_master.csv");
            //runner.masterToMatch("contact_master.csv", "contact_match.csv");
            runner.masterToMatches("contact_master.csv");
            runner = new MatchRunner();
        }
    }
}
