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

import java.util.ArrayList;

/**
 * Preprocessor cleans and standardizes dataholder as it's being read in by CSVReader.
 */
class Preprocessor {

    private AddressHandler addressHandler;

    /**
     * Default constructor to initialize AddressHandler.
     */
    Preprocessor() {
        addressHandler = new AddressHandler(); // reading JSON file
    }

    /**
     * Removes ALL whitespace from dataholder. If given multiple words, then joins the string separated by 1 whitespace.
     *
     * @param data String dataholder from Contact or MasterContact CSV.
     * @return New string with standardized whitespace.
     */
    String trimData(String data) {

        String[] temp = data.split(" ");
        ArrayList<String> newTemp = new ArrayList<>();
        String tempString;

        int i;
        int length = temp.length;
        for (i = 0; i < length; i++) {

            tempString = temp[i];
            temp[i] = tempString.trim();

            if (!tempString.equals("") && !tempString.equals(" ") &&
                    !tempString.equals("\n") && !tempString.equals("\t")) {

                // do nothing
                newTemp.add(temp[i]);


            }

        }

        return String.join(" ", newTemp);

    }

    /**
     * Checks if dataholder is null and checks if dataholder is dataholder is empty.
     * If dataholder isn't empty, then removes unnecessary punctuation.
     * Finally, we return a String that has had all unnecessary whitespace removed.
     *
     * @param data String dataholder from Contact or MasterContact CSV.
     * @return New standardized string.
     */
    String prep(String data) {

        String newData = checkNULL(data);

        if (!data.equalsIgnoreCase("")) {

            newData = removePunctuation(newData);

        }

        return trimData(newData);

    }

    /**
     * Checks if dataholder is an invalid character.
     *
     * @param data The string at the row,col of the dataset.
     * @return Empty string if "NULL", otherwise the original string
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
     * Removes punctuation from strings such as | . |  ! | , | - | ~ |
     *
     * @param data String from a record.
     * @return String without punctuation.
     */
    String removePunctuation(String data) {

        String newData = data;
        newData = newData.replace(".", "").trim();
        newData = newData.replace(",", "").trim();
        newData = newData.replace("!", "").trim();
        newData = newData.replace("'", "").trim();
        newData = newData.replace("`", "").trim();
        newData = newData.replace("~", "").trim();

        int i;
        int sz;
        String[] temp;
        if (newData.contains("-")) {

            temp = newData.split("-");
            sz = temp.length;

            for (i = 0; i < sz; i++) {

                temp[i] = temp[i].trim();

            }

            newData = String.join(" ", temp);

        }

        return newData;

    }

    /**
     * Combines related dataholder from two fields.
     *
     * @param field1 First related field dataholder.
     * @param field2 Second related field dataholder.
     * @return One string with combined dataholder. Separated by 1 whitespace.
     */
    String combineFields(String field1, String field2) {

        String combined = prep(field1);
        combined += " " + prep(field2);
        combined = combined.trim();
        return combined;

    }

    /**
     * Standardizes address fields.
     *
     * @param address1 Data from first Address field.
     * @param address2 Data from second Address field.
     * @return String consisting of one combined and standardized address.
     */
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

    /**
     * Returns the AddressHandler object.
     *
     * @return AddressHandler object.
     */
    public AddressHandler getAddressHandler() {
        return this.addressHandler;
    }

}
