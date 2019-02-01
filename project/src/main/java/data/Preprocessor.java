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

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.*;

class Preprocessor {

    private AddressHandler addressHandler;

    Preprocessor() {
        addressHandler = new AddressHandler();
    }

    String trimData(String data) {

        String[] temp = data.split(" ");
        ArrayList<String> newTemp = new ArrayList<>();
        String tempString;

        int i;
        int length = temp.length;
        for (i = 0; i < length; i++) {

            tempString = temp[i];
            temp[i] = tempString.trim();

//            if (tempString.equals("") || tempString.equals(" ") ||
//                    tempString.equals("\n") || tempString.equals("\t")) {

            if (!tempString.equals("") && !tempString.equals(" ") &&
                    !tempString.equals("\n") && !tempString.equals("\t")) {

                // do nothing
                newTemp.add(temp[i]);

            } else {

                newTemp.add(temp[i]);

            }

        }

        return String.join(" ", newTemp);

    }

    /***
     * check if data is null, then check is not empty, and check punctuation
     * @param data string
     * @return string
     */
    String prep(String data) {

        String newData = checkNULL(data);

        if (!data.equalsIgnoreCase("")) {

            newData = removePunctuation(newData);

        }

        return trimData(newData);

    }

    /***
     *
     * @param data - The string at the row,col of the dataset
     * @return - Empty string if "NULL", otherwise the original string
     */
    String checkNULL(String data) {

        if (data.equalsIgnoreCase("NULL") ||
                data.equalsIgnoreCase(" ") ||
                data.equalsIgnoreCase("\t") ||
                data.equalsIgnoreCase("\n")) {

            return "";

        } else {

            return data;

        }

    }

    /**
     * Removes punctuation from strings such as | . |  ! | , | - |
     *
     * @param data
     * @return string named data updated
     */
    String removePunctuation(String data) {

        String newData = data;
        newData = newData.replace(".", "").trim();
        newData = newData.replace(",", "").trim();
        newData = newData.replace("!", "").trim();
        newData = newData.replace("'", "").trim();
        newData = newData.replace("`", "").trim();
        newData = newData.replace("~", "").trim();

        if (newData.contains("-")) {

            String[] temp = newData.split("-");

            int i;
            int sz = temp.length;
            for (i = 0; i < sz; i++) {

                temp[i] = temp[i].trim();

            }

            newData = String.join(" ", temp);

        }

        return newData;

    }

    String combineFields(String field1, String field2) {

        String combined = prep(field1);
        combined += " " + prep(field2);
        combined = combined.trim();
        return combined;

    }

    String handleAddress(String address1, String address2) {

        address1 = prep(address1);
        address2 = prep(address2);
        String address = combineFields(address1, address2);

        String[] temp = address.split(" ");


        int i;
        int sz = temp.length;
        for (i = 0; i < sz; i++) {
            temp[i] = temp[i].trim();
            temp[i] = addressHandler.standardize(temp[i]);
        }

        return String.join(" ", temp);

    }

    private class AddressHandler {

        private Map<String, String> abbrevs;

        AddressHandler() {
            abbrevs = new HashMap<>();
            readJSON("./config/data/abbreviations.json");
        }

        /***
         * function to trim and uppercase
         * @param data
         * @return string data updated
         */
        String standardize(String data) {

            String newData = data;
            newData = newData.trim();
            newData = newData.toUpperCase();

            if (checkKeyExists(newData)) {

                newData = abbrevs.get(newData);

            }

            return newData;

        }


        private boolean checkKeyExists(String data) {
            return abbrevs.containsKey(data);
        }

        private void readJSON(String filename) {

            File file = new File(filename);

            try {

                String content = FileUtils.readFileToString(file, "utf-8");
                JSONObject jo = new JSONObject(content);

                for (Iterator keys = jo.keys(); keys.hasNext(); ) {

                    String key = keys.next().toString();
                    String value = jo.getString(key);

                    //System.out.printf("\nKEY: %s | Value: %s\n", key, value);
                    abbrevs.put(key, value);

                }

            } catch (IOException e) {

                e.printStackTrace();

            }
        }
    }


}
