/*
 * ~~~ Version Control Standard ~~~
 *
 * Create a separate branch for implementing new features.
 *
 * *** BEFORE COMMITTING ****
 *
 * ---> Run all unit tests to ensure functionality
 * ---> Run maven's clean command (mvn clean or use the tool in IDE)  to remove unnecessary files
 * ---> Merge the branch into master once the feature is complete with unit tests.
 *
 * ~~~ Copyright ~~~
 *
 * Developed by Gregory Smith & Axel Solano. Last modified 08/01/19 6:04 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package matcher;

import data.Contact;
import data.MasterContact;
import data.MasterSet;
import data.MatchSet;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestCalcSim {


    @Test
    public void testFuzzyStrCmp() {

        CalcSim calc = new CalcSim();

        // Tests we expect to get accurate ratio results

        assertEquals(String.valueOf(calc.fuzzyStrCmp("", "",
                "ratio")), String.valueOf(0));
        assertEquals(String.valueOf(calc.fuzzyStrCmp("abcd", "xyzi",
                "ratio")), String.valueOf(0));
        assertEquals(String.valueOf(calc.fuzzyStrCmp("Greg", "Greg",
                "ratio")), String.valueOf(100));
        assertEquals(String.valueOf(calc.fuzzyStrCmp("Axel", "AXel",
                "ratio")), String.valueOf(75));
        assertEquals(String.valueOf(calc.fuzzyStrCmp("Axel", "Axel",
                "ratio")), String.valueOf(100));
        assertEquals(String.valueOf(calc.fuzzyStrCmp("Axel", "AxelJ",
                "ratio")), String.valueOf(89));
        assertEquals(String.valueOf(calc.fuzzyStrCmp("noah", "naoh",
                "ratio")), String.valueOf(75));

        assertEquals(String.valueOf(calc.fuzzyStrCmp("UBS Financial Services Inc", "UBS Financial Services Inc.",
                "ratio")), String.valueOf(98));

        assertEquals(String.valueOf(calc.fuzzyStrCmp("noah", "naoh",
                "ratioo")), String.valueOf(-1));
        assertEquals(String.valueOf(calc.fuzzyStrCmp("", "UY",
                "ratio")), String.valueOf(0));
        assertEquals(String.valueOf(calc.fuzzyStrCmp("", "GB",
                "ratio")), String.valueOf(0));
        assertEquals(String.valueOf(calc.fuzzyStrCmp("", "US",
                "ratio")), String.valueOf(0));
        assertEquals(String.valueOf(calc.fuzzyStrCmp("UY", "US",
                "ratio")), String.valueOf(50));
        assertEquals(String.valueOf(calc.fuzzyStrCmp("UY", "GB",
                "ratio")), String.valueOf(0));
        assertEquals(String.valueOf(calc.fuzzyStrCmp("GB", "US",
                "ratio")), String.valueOf(0));
        assertEquals(String.valueOf(calc.fuzzyStrCmp("UBS Global Asset Management", "UBS Financial Services Inc",
                "ratio")), String.valueOf(38));
        assertEquals(String.valueOf(calc.fuzzyStrCmp("Women Of Substance LLC", "UBS Financial Services Inc",
                "ratio")), String.valueOf(25));
        assertEquals(String.valueOf(calc.fuzzyStrCmp("UBS Financial Services Inc Puerto Ric", "UBS Financial Services Inc",
                "ratio")), String.valueOf(83));
        assertEquals(String.valueOf(calc.fuzzyStrCmp("UBS Securities LLC", "UBS Financial Services Inc",
                "ratio")), String.valueOf(50));



//        System.out.println("testFuzzyStrCmp() pass");

    }

    @Test
    public void testCheckMethod() {

        CalcSim calc = new CalcSim();

        assertTrue(calc.checkMethod("ratio"));
        assertTrue(calc.checkMethod("PaRtIaLratio"));
        assertTrue(calc.checkMethod("TOKENSORTratio"));
        assertTrue(calc.checkMethod("tokenSortPartialratio"));
        assertTrue(calc.checkMethod("tokenSETRatio"));
        assertTrue(calc.checkMethod("TOKENSETPARTIALratio"));
        assertTrue(calc.checkMethod("weightedRatio"));

        assertFalse(calc.checkMethod("ratioo"));
        assertFalse(calc.checkMethod("tockensortratio"));
        assertFalse(calc.checkMethod("weightratio"));

    }

    @Test
    public void testCompareFields() {

        CalcSim calc = new CalcSim();

        MasterSet master = new MasterSet();
        MatchSet match = new MatchSet();
        master.readCSV("./data/contact_master.csv");
        match.readCSV("./data/contact_match.csv");

        MasterContact c1 = master.getContactList().get(0);
        Contact c2 = match.getContactList().get(0);

        int confidence = calc.compareFields(c1, c2, "ratio");
        assertEquals(34, confidence);
    }


}
