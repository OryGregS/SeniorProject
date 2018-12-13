package wmu.datamatching;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TestDataLoader {

    private final String masterPath = "./data/contact_master.csv";
    private final String matchPath = "./data/contact_match.csv";


    private long startTime, estimatedTime;
    private double seconds;


    @Test
    public void loaderDataTest(){
        DataLoader dataLoader = new DataLoader();
        assertNotNull(dataLoader);
    }

    @Test
    public void loadDataFromCSVTest() {
        DataLoader loader = new DataLoader();
        loader.loadDataFromCSV(masterPath, matchPath);
        assertNotNull(loader.getMasterSet());
        assertNotNull(loader.getMatchSet());

    }

    @Test
    public void readMasterTime(){
        DataLoader loader = new DataLoader();
        loader.loadDataFromCSV(masterPath, matchPath);
        startTime = System.nanoTime();
        loader.readMasterCSV(masterPath);
        estimatedTime = System.nanoTime() - startTime;
        seconds = ((double)estimatedTime / 1000000000.0);
        System.out.println("\nTime to read Master data: " + seconds + " seconds");
    }

    @Test
    public void readMatchTime(){
        DataLoader loader = new DataLoader();
        startTime = System.nanoTime();
        loader.readMatchCSV(matchPath);
        estimatedTime = System.nanoTime() - startTime;
        seconds = ((double)estimatedTime / 1000000000.0);
        System.out.println("\nTime to read Matching data: " + seconds + " seconds");
    }

}


//    Time getMasterSetTimeTest(): 0.60691053 seconds
//
//    Time getMatchSetTimeTest(): 0.355667105 seconds