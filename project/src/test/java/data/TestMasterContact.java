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
 * Developed by Gregory Smith & Axel Solano. Last modified 1/28/19 1:44 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package data;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class TestMasterContact {

    private MasterContact master;

    public TestMasterContact() {
        master = new MasterContact();
    }

    @Test
    public void testIdentifiedMatches() {

        assertEquals(0, master.getIdentifiedMatchCount());

        for (int i = 0; i < 1000; i++) {
            master.addIdentifiedMatch();
        }

        assertEquals(1000, master.getIdentifiedMatchCount());

    }

    @Test
    public void testUnknownMatches() {

        assertEquals(0, master.getUnknownMatches());

        for (int i = 0; i < 1241; i++) {
            master.addUnknownMatch();
        }

        assertEquals(1241, master.getUnknownMatches());

    }

    @Test
    public void testKnownMatches() {

        assertEquals(0, master.getKnownMatches());

        for (int i = 0; i < 1241; i++) {
            master.addKnownMatch();
        }

        assertEquals(1241, master.getKnownMatches());

    }

}
