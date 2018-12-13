package wmu.datamatching;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TestLoaderData {

    private final String masterPath = "./data/contact_master.csv";
    private final String matchPath = "./data/contact_match.csv";


    private long startTime, estimatedTime;
    private double seconds;


    @Test
    public void loaderDataTest(){
        LoaderData loaderData = new LoaderData();
        assertNotNull(loaderData);
    }

    @Test
    public void getMasterSetTest(){
        LoaderData loaderData = new LoaderData();
        loaderData.loadMasterSet();
        assertNotNull(loaderData.getMasterSet());
    }

    @Test
    public void getMatchSetTest(){
        LoaderData loaderData = new LoaderData();
        loaderData.loadMatchSet();
        assertNotNull(loaderData.getMatchSet());
    }

    @Test
    public void getMasterSetTimeTest(){
        LoaderData loaderData = new LoaderData();
        startTime = System.nanoTime();
        loaderData.loadMasterSet();
        MasterSet masterSet = loaderData.getMasterSet();
        estimatedTime = System.nanoTime() - startTime;
        seconds = ((double)estimatedTime / 1000000000.0);
        System.out.println("\nTime getMasterSetTimeTest(): " + seconds + " seconds");
    }

    @Test
    public void getMatchSetTimeTest(){
        LoaderData loaderData = new LoaderData();
        startTime = System.nanoTime();
        loaderData.loadMatchSet();
        MatchSet matchSet = loaderData.getMatchSet();
        estimatedTime = System.nanoTime() - startTime;
        seconds = ((double)estimatedTime / 1000000000.0);
        System.out.println("\nTime getMatchSetTimeTest(): " + seconds + " seconds");
    }

}


//    Time getMasterSetTimeTest(): 0.60691053 seconds
//
//    Time getMatchSetTimeTest(): 0.355667105 seconds