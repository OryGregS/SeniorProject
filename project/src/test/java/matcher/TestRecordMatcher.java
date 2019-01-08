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

import java.util.ArrayList;

import data.DataLoader;
import data.MasterSet;
import data.MatchSet;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestRecordMatcher {

    @Test
    public void TestOrder() {

        DataLoader loader = new DataLoader();
        loader.loadDataFromCSV("./data/contact_master.csv",
                "./data/contact_match.csv");
        MasterSet master = loader.getMasterSet();
        MatchSet match = loader.getMatchSet();

        ArrayList<Integer> fieldsToCompare = new ArrayList<>();
        fieldsToCompare.add(0); // last name
        fieldsToCompare.add(1); // middle name
        fieldsToCompare.add(2); // first name
        fieldsToCompare.add(5); // email
        fieldsToCompare.add(6); // phone
        fieldsToCompare.add(7); // address
        fieldsToCompare.add(8); // city
        fieldsToCompare.add(9); // state
        fieldsToCompare.add(10); // zip1
        fieldsToCompare.add(11); // zip2

        // BAD TEST - FIX

//        RecordMatcher matcher = new RecordMatcher(master, match, fieldsToCompare, false, 0.01);
//        matcher.printRun(false);
//        matcher.run("ratio");
//
//
//        int temp1, temp2;
//        for (int i = 0; i < master.getContactList().size(); i++) {
//            if (i == 0) {
//                ArrayList<Integer> testMatch = master.getContactList().get(i).getTopConfidence();
//                temp1 = testMatch.get(0);
//                for (int j = 1; j < testMatch.size(); j++) {
//                    temp2 = testMatch.get(j);
//                    assertTrue(temp1 >= temp2);
//                    temp1 = temp2;
//                }
//            }
//        }

    }

}
