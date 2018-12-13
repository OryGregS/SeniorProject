package wmu.datamatching;

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

    protected void readMasterCSV(String pathToMasterCSV) {
        masterSet.readCSV(pathToMasterCSV);
    }

    protected void readMatchCSV(String pathToMatchCSV) {
        matchSet.readCSV(pathToMatchCSV);
    }

    /**
     * Loads datasets from CSV files
     * @param pathToMasterCSV - path to the Master dataset
     * @param pathToMatchCSV - path to the dataset to match
     */
    public void loadDataFromCSV(String pathToMasterCSV, String pathToMatchCSV) {
        readMasterCSV(pathToMasterCSV);
        readMatchCSV(pathToMatchCSV);
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
