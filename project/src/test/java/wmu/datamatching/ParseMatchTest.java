package wmu.datamatching;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ParseMatchTest {

    ParseMatch match = new ParseMatch();
    String matchPath = "./data/clean/master.csv";

    @Test
    public void readCSVTest() { assertTrue(match.readCSV(matchPath)); }

    @Test
    public void headTest() { assertTrue(match.head()); }

}
