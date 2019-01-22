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
 * Developed by Gregory Smith & Axel Solano. Last modified 08/01/19 5:59 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package data;

public class DataLoader {

    private MasterSet masterSet;
    private MatchSet matchSet;

    /**
     * Initializes the dataset objects
     */
    public DataLoader() {
        masterSet = new MasterSet();
        matchSet = new MatchSet();
    }

    /**
     * Loads datasets from database queries.
     * ** NOT FINISHED **
     */
    public void loadDataFromSQL() {

    }

    public void readMasterCSV(String pathToMasterCSV) {
        masterSet.readCSV(pathToMasterCSV);
    }

    public void readMatchCSV(String pathToMatchCSV, boolean alternate) {
        matchSet.readCSV(pathToMatchCSV, alternate);
    }

    /**
     * Loads datasets from CSV files
     * @param pathToMasterCSV - path to the Master dataset
     * @param pathToMatchCSV - path to the dataset to match
     */
    public void loadDataFromCSV(String pathToMasterCSV, String pathToMatchCSV, boolean alternate) {
        readMasterCSV(pathToMasterCSV);
        readMatchCSV(pathToMatchCSV, alternate);
    }

    /**
     * Function to return the master records
     * @return masterSet object which is the collection of master records
     */
    public MasterSet getMasterSet() {
        return masterSet;
    }

    /**
     * Function to return the records to be matched
     * @return matchSet object which is the collection of records to match
     */
    public MatchSet getMatchSet() {
        return matchSet;
    }



}
