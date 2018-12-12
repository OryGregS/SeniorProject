package wmu.datamatching;

public class LoaderData {

    private String masterPath = "./data/contact_master.csv";
    private String matchPath = "./data/contact_match.csv";

//    private long startTime, estimatedTime;
//    private double seconds;

    private MasterSet masterSet;
    private MatchSet matchSet;


    protected void setMasterPath(String newMasterPath){
        masterPath = newMasterPath;
    }

    protected void setMatchPath(String newMatchPath){
        matchPath = newMatchPath;
    }

    public void loadMasterSet(){
        masterSet = new MasterSet();
        masterSet.readCSV(masterPath);
    }

    public void loadMatchSet(){
        matchSet = new MatchSet();
        matchSet.readCSV(matchPath);
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
