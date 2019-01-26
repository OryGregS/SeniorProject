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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Preprocessor {

    private class AddressHandler {

        private Map<String, String> abbrevs;

        public AddressHandler() {
            abbrevs = new HashMap<>();
            readJSON("./config/data/abbreviations.json");
        }

        public String standardize(String data) {

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

                for ( Iterator keys = jo.keys(); keys.hasNext(); ) {

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

    private AddressHandler addressHandler;

    public Preprocessor() {
        addressHandler = new AddressHandler();
    }

    public String prep(String data) {

        String newData = checkNULL(data);

        if (!data.equalsIgnoreCase("")) {

            newData = removePunctuation(newData);

        }

        return newData;

    }

    /***
     *
     * @param data - The string at the row,col of the dataset
     * @return - Empty string if "NULL", otherwise the original string
     */
    protected String checkNULL(String data) {

        if (data.equalsIgnoreCase("NULL")) {

            return "";

        } else {

            return data;

        }

    }

    /**
     * Removes punctuation from strings such as (. , !)
     * @param data
     * @return
     */
    protected String removePunctuation(String data) {

        String newData = data;
        newData = newData.replace(".", "").trim();
        newData = newData.replace(",", "").trim();
        newData = newData.replace("!", "").trim();

        if (newData.contains("-")) {

            String[] temp = newData.split("-");

            int i;
            int sz = temp.length;
            for (i = 0; i < sz; i++) {

                temp[i] = temp[i].trim();

            }

            newData = String.join(" ", temp);

        }

        String[] temp = newData.split(" ");

        int i;
        int sz = temp.length;
        for (i = 0; i < sz; i++) {
            temp[i] = temp[i].trim();
        }

        return newData;

    }

    protected String combineFields(String field1, String field2) {

        String combined = field1.trim();
        combined += " " + field2;
        combined = combined.trim();
        combined = combined.trim();
        return combined;

    }

    protected String handleAddress(String address) {

        String[] temp = address.split(" ");

        int i;
        int sz = temp.length;
        for (i = 0; i < sz; i++) {
            temp[i] = addressHandler.standardize(temp[i]);
        }

        return String.join(" ", temp);

    }



}
