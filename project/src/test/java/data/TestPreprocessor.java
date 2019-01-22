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
    public void testCombineFields() {
        String address1 = "123 North St. ";
        String address2 = " Ste 123 ";
        String combined = "123 North St. Ste 123";
        assertTrue(processor.combineFields(address1, address2).equals(combined));

        address1 = "          123     NorthSt.";
        address2 = "ste 123           ";
        assertTrue(processor.combineFields(address1, address2).equals(combined));
    }

    @Test
    public void testRemovePunctuation() {

        String data1 = "Mr.";
        String data2 = ".Mr......";
        String data3 = ",Mr,";
        String data4 = ",,,,,,,Mr,,,,,";
        String data5 = "!!!Mr!!!";
        String expected = "Mr";

        String result1 = processor.removePunctuation(data1);
        String result2 = processor.removePunctuation(data2);
        String result3 = processor.removePunctuation(data3);
        String result4 = processor.removePunctuation(data4);
        String result5 = processor.removePunctuation(data5);

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
        System.out.println(result5);

        assertTrue(result1.equals(expected));
        assertTrue(result2.equals(expected));
        assertTrue(result3.equals(expected));
        assertTrue(result4.equals(expected));
        assertTrue(result5.equals(expected));

    }

    @Test
    public void testPrep() {

        String data1 = "Kevin.";
        String data2 = "null";
        String data3 = "nulll";
        String data4 = "null.Kevin.";

        String expected1 = "Kevin";
        String expected2 = "";
        String expected3 = "nulll";
        String expected4 = "nullKevin";

        String result1 = processor.prep(data1);
        String result2 = processor.prep(data2);
        String result3 = processor.prep(data3);
        String result4 = processor.prep(data4);

        assertTrue(result1.equals(expected1));
        assertTrue(result2.equals(expected2));
        assertTrue(result3.equals(expected3));
        assertTrue(result4.equals(expected4));

    }

    @Test
    public void testDict() {

        String address1 = "123 S Walnut St";
        String expected1 = "123 SOUTH WALNUT STREET";
        String result1 = processor.handleAddress(address1);

        System.out.println(result1);

    }

}
