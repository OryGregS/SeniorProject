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


import data.DataLoader;
import data.MasterSet;
import data.MatchSet;
import indexing.Indexer;

public class MatchMaker {

    private Indexer indexer;

    public MatchMaker(Indexer indexer) {
        this.indexer = indexer;
    }


    public void compareMasterToMaster(String masterPath) {

        DataLoader loader = new DataLoader(this.indexer);
        loader.loadDataFromCSV(masterPath, masterPath, false);
        MasterSet master = loader.getMasterSet();
        MatchSet match_master = loader.getMatchSet();

        RecordMatcher matcher = new RecordMatcher(this.indexer, true);
        matcher.printRun(true);
        matcher.run("ratio");

    }

    public void compareMasterToOther(String masterPath, String matchPath, boolean alternate) {

        DataLoader loader = new DataLoader(this.indexer);
        loader.loadDataFromCSV(masterPath, matchPath, alternate);
        MasterSet master = loader.getMasterSet();
        MatchSet match = loader.getMatchSet();

        RecordMatcher matcher = new RecordMatcher(this.indexer, false);
        matcher.printRun(true);
        matcher.run("ratio");

    }
}
