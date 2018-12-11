package wmu.datamatching;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestMasterSet {

    MasterSet master = new MasterSet();
    String masterPath = "./data/contact_master.csv";

    @Test
    public void readCSVTest() { assertTrue(master.readCSV(masterPath)); }

    @Test
    public void headTest() {
        master.readCSV(masterPath);
    }

    @Test
    public void testCheckNull() {
        assertTrue(master.checkNULL("NULL").equals(""));
    }

}
