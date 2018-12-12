package wmu.datamatching;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestLoaderData {

    MasterSet master = new MasterSet();
    String masterPath = "./data/contact_master.csv";

    @Test
    public void readCSVTest() { assertTrue(master.readCSV(masterPath)); }
}
