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

package matcher;


import data.DataLoader;
import data.MasterSet;
import data.MatchSet;

import java.util.ArrayList;

public class MatchMaker {


    public void compareMasterToMaster(String masterPath, ArrayList<Integer> fieldsToCompare) {
        DataLoader loader = new DataLoader();
        loader.loadDataFromCSV(masterPath, masterPath);
        MasterSet master = loader.getMasterSet();
        MatchSet match_master = loader.getMatchSet();

        RecordMatcher matcher = new RecordMatcher(master, match_master, fieldsToCompare, true);
        matcher.printRun(true);
        matcher.run("weightedratio");

    }

    public void compareMasterToOther(String masterPath, String matchPath, ArrayList<Integer> fieldsToCompare) {
        DataLoader loader = new DataLoader();
        loader.loadDataFromCSV(masterPath, matchPath);
        MasterSet master = loader.getMasterSet();
        MatchSet match = loader.getMatchSet();

        RecordMatcher matcher = new RecordMatcher(master, match, fieldsToCompare, false);
        matcher.printRun(true);
        matcher.run("weightedratio");
    }
}
