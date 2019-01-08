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
 * Developed by Gregory Smith & Axel Solano. Last modified 08/01/19 5:35 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package matcher;

import java.util.ArrayList;

public class MatchMain {

    // MASTERPATH - "./data/contact_master.csv"
    // MATCHPATH - "./data/contact_match.csv"

    public static void main(String[] args) {

        long start = System.nanoTime();

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

        MatchMaker mm = new MatchMaker();
        //mm.compareMasterToMaster("./data/contact_master.csv", fieldsToCompare);
        mm.compareMasterToOther("./data/contact_master.csv",
                "./data/contact_match.csv", fieldsToCompare);

        long end = System.nanoTime();
        double time =  ( (end-start) / 1000000000.0 );

        if (time > 120) {
            time = time / 60;
            System.out.println("\nTime taken: " + time + " minutes\n");
        } else {
            System.out.println("\nTime taken: " + time + " seconds\n");
        }



//        for (int i = 0; i < master.getContactList().size(); i++) {
//            master.getContactList().get(i).printTop();
//        }



    }

}
