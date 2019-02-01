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

    public void print(String input, String expected){
        System.out.println("|" + input + "|" +" , "+  "|" + expected + "|");
    }

    @Test
    public void testTrimData(){
        Preprocessor processor = new Preprocessor();
        String data1 = "123   W   Michigan   Ave";
        String expected = "123 W Michigan Ave";

        String result1 = processor.trimData(data1);
        print(data1, expected);
        assertTrue(result1.equalsIgnoreCase(expected));

    }

    @Test
    public void testPrep() {
        Preprocessor processor = new Preprocessor();
        String data1 = "Kevin";
        String data2 = "null";
        String data3 = "nulll";
        String data4 = "`null.Kevin.|Axel,-Greg'yo~LMAO!-Pi";

        String expected1 = "Kevin";
        String expected2 = "";
        String expected3 = "nulll";
        String expected4 = "nullKevin|Axel GregyoLMAO Pi";

        String result1 = processor.prep(data1);
        String result2 = processor.prep(data2);
        String result3 = processor.prep(data3);
        String result4 = processor.prep(data4);

        print(data1, result1);
        assertTrue(result1.equalsIgnoreCase(expected1));
        print(data2, result2);
        assertTrue(result2.equalsIgnoreCase(expected2));
        print(data3, result3);
        assertTrue(result3.equalsIgnoreCase(expected3));
        print(data4, result4);
        assertTrue(result4.equalsIgnoreCase(expected4));

    }

    @Test
    public void testCheckNull() {
        assertTrue(processor.checkNULL("NULL").equals(""));
        assertTrue(processor.checkNULL("null").equals(""));
    }

    @Test
    public void testRemovePunctuation() {
        String expected = "Mr";
        String expected2 = "Miss Miss";

        String data1 = "Mr.~!";
        String data2 = ".Mr......'!!~";
        String data3 = ",Mr`,";
        String data4 = ",,,,,,,Mr,,,,!,";
        String data5 = "!!!Miss!!!-!~~Miss~~";

        String result1 = processor.removePunctuation(data1);
        String result2 = processor.removePunctuation(data2);
        String result3 = processor.removePunctuation(data3);
        String result4 = processor.removePunctuation(data4);
        String result5 = processor.removePunctuation(data5);

        print(data1, result1);
        assertTrue(result1.equalsIgnoreCase(expected));
        print(data2, result2);
        assertTrue(result2.equalsIgnoreCase(expected));
        print(data3, result3);
        assertTrue(result3.equalsIgnoreCase(expected));
        print(data4, result4);
        assertTrue(result4.equalsIgnoreCase(expected));
        print(data5, result5);
        assertTrue(result5.equalsIgnoreCase(expected2));

    }

    @Test
    public void testCombineFields() {
        String address1 = "123 North St ! ! ~ ' . ,";
        String address2 = " Ste 123 ";
        String combined = "123 North St Ste 123";

        String result = processor.combineFields(address1, address2);
        print(address1 + " " + address2, result);
        assertTrue(result.equals(combined));

        address1 = "          123            North - St.";
        address2 = "Ste 123           ....! ! ~ ' . ,.";
        result = processor.combineFields(address1, address2);
        print(address1 + " " + address2, result);
        assertTrue(result.equals(combined));
    }

    @Test
    public void testHandleAddress() {

        String test1 = "123 North st ste";
        String test2 = "123 ";
        String combined1 = "123 NORTH STREET SUITE 123";

        String test3 = "15351 sw pkwy ";
        String test4 = "fl 1";
        String combined2 = "15351 SOUTHWEST PARKWAY FLOOR 1";

        assertTrue(processor.handleAddress(test1, test2).equals(combined1));
        assertTrue(processor.handleAddress(test3, test4).equals(combined2));

    }





    @Test
    public void testDict() {

        String address1 = "123 S Walnut St";
        String address2 = "fl pkwy 15";
        String expected1 = "123 SOUTH WALNUT STREET FLOOR PARKWAY 15";
        String result1 = processor.handleAddress(address1, address2);
        assertTrue(result1.equals(expected1));

    }

}
