package wmu.datamatching;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ParseMasterTest {

    ParseMaster master = new ParseMaster();
    String masterPath = "./data/contact_master.csv";

    @Test
    public void readCSVTest() { assertTrue(master.readCSV(masterPath)); }

    @Test
    public void headTest() {
        master.readCSV(masterPath);
        assertTrue(master.head());
    }

    @Test
    public void testCheckNull() {
        assertTrue(master.checkNULL("NULL").equals(""));
    }

}
