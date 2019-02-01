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
 * Developed by Gregory Smith & Axel Solano. Last modified 08/01/19 6,48 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package data;


import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestPreprocessor {

    private Preprocessor processor = new Preprocessor();

    public void print(String input, String expected){
        System.out.println("|" + input + "|" +" , "+  "|" + expected + "|");
    }

    @Test
    public void testTrimData(){
        Preprocessor processor = new Preprocessor();
        String data1 = "1234   W   Michigan   Ave";
        String expected = "1234 W Michigan Ave";

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
    public void testStandardize(){
        String data1 = "North";
        String expected1 = "NORTH";

        processor.getAddressHandler().setStr(data1);
        String result1 = processor.getAddressHandler().standardize();

        print(data1,result1);
    }

    @Test
    public void testReadJSON(){
        Preprocessor processor1 = new Preprocessor();
        Map<String, String> abbrevs1 = processor1.getAddressHandler().getAbbrevs();
        int countOfAbbrevs1 = abbrevs1.size();
        int countOfAbbrevs1Loop = processor1.getAddressHandler().getCountOfAbbrevs();

        Preprocessor processor2 = new Preprocessor();
        Map<String, String> abbrevs2 = processor2.getAddressHandler().getAbbrevs();
        int countOfAbbrevs2 = abbrevs2.size();
        int countOfAbbrevs2Loop = processor2.getAddressHandler().getCountOfAbbrevs();

        assertEquals(countOfAbbrevs1, countOfAbbrevs1Loop);
        assertEquals(countOfAbbrevs2,countOfAbbrevs1Loop);
        assertEquals(countOfAbbrevs1, 38);
        assertEquals(countOfAbbrevs2,38);
        assertEquals(countOfAbbrevs1Loop, 38);
        assertEquals(countOfAbbrevs2Loop,38);

        assertEquals(abbrevs1,abbrevs2);

        Map<String, String> mapAbbrev = new HashMap<>();
        mapAbbrev.put("AVE", "AVENUE");
        mapAbbrev.put("BLVD", "BOULEVARD");
        mapAbbrev.put("CTR", "CENTER");
        mapAbbrev.put("CIR", "CIRCLE");
        mapAbbrev.put("CT", "COURT");
        mapAbbrev.put("DR", "DRIVE");
        mapAbbrev.put("EXPY", "EXPRESSWAY");
        mapAbbrev.put("HTS", "HEIGHTS");
        mapAbbrev.put("HWY", "HIGHWAY");
        mapAbbrev.put( "IS", "ISLAND");
        mapAbbrev.put("JCT", "JUNCTION");
        mapAbbrev.put("LK", "LAKE");
        mapAbbrev.put("LN", "LANE");
        mapAbbrev.put("MTN", "MOUNTAIN");
        mapAbbrev.put("PKWY", "PARKWAY");
        mapAbbrev.put("PL", "PLACE");
        mapAbbrev.put("PLZ", "PLAZA");
        mapAbbrev.put("RDG", "RIDGE");
        mapAbbrev.put("RD", "ROAD");
        mapAbbrev.put("SQ", "SQUARE");
        mapAbbrev.put("ST", "STREET");
        mapAbbrev.put("STA", "STATION");
        mapAbbrev.put("TER", "TERRACE");
        mapAbbrev.put("TRL", "TRAIL");
        mapAbbrev.put("TPKE", "TURNPIKE");
        mapAbbrev.put("VLY", "VALLEY");
        mapAbbrev.put("APT", "APARTMENT");
        mapAbbrev.put("RM", "ROOM");
        mapAbbrev.put("STE", "SUITE");
        mapAbbrev.put("FL", "FLOOR");
        mapAbbrev.put("N", "NORTH");
        mapAbbrev.put("E", "EAST");
        mapAbbrev.put("S", "SOUTH");
        mapAbbrev.put("W", "WEST");
        mapAbbrev.put("NE", "NORTHEAST");
        mapAbbrev.put("NW", "NORTHWEST");
        mapAbbrev.put("SE", "SOUTHEAST");
        mapAbbrev.put( "SW", "SOUTHWEST");


        assertEquals(mapAbbrev,abbrevs1);
        assertEquals(mapAbbrev,abbrevs2);





    }





}
