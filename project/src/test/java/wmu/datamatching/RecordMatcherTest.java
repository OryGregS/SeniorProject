package wmu.datamatching;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class RecordMatcherTest {


    @Test
    public void testFuzzyStrCmp() {

        RecordMatcher matcher = new RecordMatcher();

        // Tests we expect to get accurate ratio results

        assertEquals(String.valueOf(matcher.fuzzyStrCmp("", "",
                "ratio")), String.valueOf(0));
        assertEquals(String.valueOf(matcher.fuzzyStrCmp("abcd", "xyzi",
                "ratio")), String.valueOf(0));
        assertEquals(String.valueOf(matcher.fuzzyStrCmp("Greg", "Greg",
                "ratio")), String.valueOf(100));
        assertEquals(String.valueOf(matcher.fuzzyStrCmp("Axel", "AxelJ",
                "ratio")), String.valueOf(89));
        assertEquals(String.valueOf(matcher.fuzzyStrCmp("noah", "naoh",
                "ratio")), String.valueOf(75));

        assertEquals(String.valueOf(matcher.fuzzyStrCmp("noah", "naoh",
                "ratioo")), String.valueOf(-1));

    }

    @Test
    public void testCheckMethod() {

        RecordMatcher matcher = new RecordMatcher();

        assertTrue(matcher.checkMethod("ratio"));
        assertTrue(matcher.checkMethod("PaRtIaLratio"));
        assertTrue(matcher.checkMethod("TOKENSORTratio"));
        assertTrue(matcher.checkMethod("tokenSortPartialratio"));
        assertTrue(matcher.checkMethod("tokenSETRatio"));
        assertTrue(matcher.checkMethod("TOKENSETPARTIALratio"));
        assertTrue(matcher.checkMethod("weightedRatio"));

        assertFalse(matcher.checkMethod("ratioo"));
        assertFalse(matcher.checkMethod("tockensortratio"));
        assertFalse(matcher.checkMethod("weightratio"));

    }

    @Test
    public void testCompareFields() {

        RecordMatcher matcher = new RecordMatcher();

        MasterSet master = new MasterSet();
        MatchSet match = new MatchSet();
        master.readCSV("./data/contact_master.csv");
        match.readCSV("./data/contact_match.csv");

        Contact c1 = master.getContactList().get(0);
        Contact c2 = match.getContactList().get(0);

        matcher.compareFields(c1.getFieldList(), c2.getFieldList(), "ratio");
        int sum = matcher.getSum();
        assertEquals(sum, 502);
    }


}
