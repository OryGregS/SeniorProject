package wmu.datamatching;

public class LoaderData {

    private final String masterPath = "./data/contact_master.csv";
    private final String matchPath = "./data/contact_match.csv";
    private long startTime, estimatedTime;
    private double seconds;

    private MasterSet masterSet;
    private MatchSet matchSet;

    /***
     * Public method to load the data outside the class
     */
    public LoaderData(){
        loadData();
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

    /***
     * Private method to load the data and show timing to load
     */
    private void loadData(){
        startTime = System.nanoTime();

        masterSet = new MasterSet();
        masterSet.readCSV(masterPath);

        matchSet = new MatchSet();
        matchSet.readCSV(matchPath);

        estimatedTime = System.nanoTime() - startTime;
        seconds = ((double)estimatedTime / 1000000000.0);
        System.out.println("\nTime for reading contact_master.csv and contact_match.csv: " + seconds + " seconds");
    }

    /***
     * Method to test the time to load the data
     */
    public void testTimeRead(){
        long startTime = System.nanoTime();
        MasterSet masterSet = new MasterSet();
        masterSet.readCSV(masterPath);
        MatchSet matchSet = new MatchSet();
        matchSet.readCSV(matchPath);
        long estimatedTime = System.nanoTime() - startTime;
        double seconds = ((double)estimatedTime / 1000000000.0);
        System.out.println("Time testTimeRead(): " + seconds + " seconds");

    }



}
