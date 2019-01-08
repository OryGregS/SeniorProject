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
 * Developed by Gregory Smith & Axel Solano. Last modified 08/01/19 6:33 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package data;

public class Preprocessor {

    /***
     *
     * @param data - The string at the row,col of the dataset
     * @return - Empty string if "NULL", otherwise the original string
     */
    public String checkNULL(String data) {

        if (data.equals("NULL")) {
            return "";
        } else {
            return data;
        }

    }

    public String combineAddress(String address1, String address2) {
        String combinedAddress = address1.trim() + " " + address2.trim();
        combinedAddress = combinedAddress.trim();
        return combinedAddress;
    }

}
