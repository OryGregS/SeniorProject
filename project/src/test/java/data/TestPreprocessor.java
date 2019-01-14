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
 * Developed by Gregory Smith & Axel Solano. Last modified 08/01/19 6:48 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package data;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestPreprocessor {

    private Preprocessor processor = new Preprocessor();

    @Test
    public void testCheckNull() {
        assertTrue(processor.checkNULL("NULL").equals(""));
    }

    @Test
    public void testCombineAddress() {
        String address1 = "123 North St. ";
        String address2 = " Ste 123 ";
        String combined = "123 North St. Ste 123";
        assertTrue(processor.combineAddress(address1, address2).equals(combined));

        address1 = "          123     NorthSt.";
        address2 = "ste 123           ";
        assertTrue(processor.combineAddress(address1, address2).equals(combined));
    }
}
