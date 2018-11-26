package wmu.datamatching;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ParseMasterTest {

    ParseMaster master = new ParseMaster();
    String masterPath = "./data/clean/master.csv";

    @Test
    public void readCSVTest() { assertTrue(master.readCSV(masterPath)); }

    @Test
    public void headTest() { assertTrue(master.head()); }

}
