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
 * Developed by Gregory Smith & Axel Solano. Last modified 2/8/19 10:12 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package processing;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * AddressHandler is used by Preprocessor to standardize address fields.
 */
class AddressHandler {

    private Map<String, String> abbrevs;
    private String str;
    private int countOfAbbrevs = 0;

    /**
     * Initializes HashMap field and reads the JSON file into the HashMap.
     */
    AddressHandler() {

        abbrevs = new HashMap<>();
        readJSON("./config/processing/abbreviations.json");

    }

    void setStr(String str) {
        this.str = str;
    }

    String standardize() {

        if (str.isEmpty() || str.equalsIgnoreCase("") || str == null) {

            return null;

        }

        return standardize(this.str);
    }

    Map<String, String> getAbbrevs() {
        return new HashMap<>(this.abbrevs); // to keep it private
    }

    int getCountOfAbbrevs() {
        return this.countOfAbbrevs;
    }

    boolean checkKeyExists() {
        if (str.isEmpty() || str.equalsIgnoreCase("") || str == null) {
            return false;
        }
        return this.checkKeyExists(this.str);
    }


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

    private void readJSON(String fileName) {

        File file = new File(fileName);

        try {

            String content = FileUtils.readFileToString(file, "utf-8");
            JSONObject jo = new JSONObject(content);

            for (Iterator keys = jo.keys(); keys.hasNext(); ) {

                String key = keys.next().toString();
                String value = jo.getString(key);

                //System.out.printf("\nKEY: %s | Value: %s\n", key, value);
                abbrevs.put(key, value);
                countOfAbbrevs++;

            }

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}
