package wmu.datamatching;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


public class TestMatchRecord {

    MatchRecord match = new MatchRecord();
    String matchPath = "./data/contact_match.csv";

    @Test
    public void readCSVTest() { assertTrue(match.readCSV(matchPath)); }

    @Test
    public void headTest() {
        match.readCSV(matchPath);
    }

    @Test
    public void testCheckNull() {
        assertTrue(match.checkNULL("NULL").equals(""));
    }

}
