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

package utils;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 * Weights reads in the values stored in json files in ./config/weights/.
 * These values are critical to our confidence of a match.
 */
public class Weights {

    private Map<String, Double> weights;
    private String FILEPATH;
    private String FILENAME;
    private int countWeights;

    /**
     * Gets the filePath where the weights are located and
     * initializes a HashMap to store the key-value pairs.
     *
     * @param filePath Folder to locate the weights files (default is ./config/weights/).
     */
    public Weights(String filePath) {
        this.FILEPATH = filePath;
        this.weights = new HashMap<>();
    }

    /**
     * Gets the HashMap of weights.
     *
     * @return A copy of the HashMap containing the weight key-value pairs.
     */
    public Map<String, Double> getWeights() {
        return new HashMap<>(this.weights);
    }

    /**
     * Gets a weight value for a specific key.
     *
     * @param key Key to lookup for its weight value.
     * @return Weight value (double).
     */
    public double getWeight(String key) {

        double weight = 0.0;

        if (keyExists(key)) {

            weight = this.weights.get(key);

        }

        return weight;

    }

    /**
     * Checks if the key exists in the HashMap.
     *
     * @param key Key to lookup.
     * @return True if HashMap contains the key. False if not.
     */
    private boolean keyExists(String key) {
        return weights.containsKey(key);
    }

    public void initialize(String fileName) {
        readJSON(fileName);
    }

    /**
     * Checks if the weights sum up to 1.
     *
     * @return True if sum of weights is 1. Exits program if not.
     */
    boolean checkWeightSum() {
        if (!weightSum()) {
            System.exit(-1);
        }

        return true;
    }

    /**
     * Calculation for checkWeightSum().
     *
     * @return True if sum of weights is 1. False if not.
     */
    private boolean weightSum() {

        double sum = 0.0;
        boolean weightsEqualToOne = true;
        for (Iterator values = weights.values().iterator(); values.hasNext(); ) {
            sum += (double) values.next();
        }

        double scale = Math.pow(10, 6);
        sum = Math.round(sum * scale) / scale;

        if (sum != 1.0) {
            weightsEqualToOne = false;
            String message = String.format("\nError reading weights from %s.\n" +
                    "\nExpected 1.0 but got %.16f. " +
                    "\nWeights must be equal to 1.0 for proper functionality. " +
                    "\nPlease adjust the weights in the config/weights directory.", this.FILENAME, sum);
            System.out.println(message);

        }

        return weightsEqualToOne;

    }

//    private boolean checkJSONExists() {
//        File file = new File(this.FILEPATH + this.FILENAME);
//        return file.exists();
//    }

    /**
     * Writes the current value of weights to a JSON file.
     */
    public void writeJSON() {

        JSONObject jo = new JSONObject();

        for (Map.Entry<String, Double> stringDoubleEntry : weights.entrySet()) {

            String key = ((Map.Entry) stringDoubleEntry).getKey().toString();
            Double value = (double) ((Map.Entry) stringDoubleEntry).getValue();

            jo.put(key, value);

        }

        try {

            FileWriter file = new FileWriter(this.FILENAME);
            file.write(jo.toString(4));
            file.flush();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    /**
     * Gets number of weights.
     *
     * @return Number of weights.
     */
    public int getCountWeights() {
        return this.countWeights;
    }

    /**
     * Reads a JSON file and stores the key-value pairs into the HashMap field.
     *
     * @param fileName Name of the weights JSON file.
     * @return True if successfully read and weights are equal to 1. False if not.
     */
    private boolean readJSON(String fileName) {

        this.FILENAME = fileName;
        double value;
        String key;
        countWeights = 0;

        File file = new File(this.FILEPATH + this.FILENAME);

        try {

            String content = FileUtils.readFileToString(file, "utf-8");
            JSONObject jo = new JSONObject(content);

            for (Iterator keys = jo.keys(); keys.hasNext(); ) {

                key = keys.next().toString();
                value = jo.getDouble(key);

                weights.put(key, value);
                countWeights++;

            }

            return checkWeightSum();

        } catch (IOException e) {

            e.printStackTrace();
            System.out.printf("\nFailed to read Weights file: %s%s\n", this.FILEPATH, this.FILEPATH);


        }
        return false;
    }

    /**
     * Prints out the key-value pairs for the weights.
     *
     * @param formatter Characters to add before printing (i.e \t or \n).
     */
    public void printWeights(String formatter) {

        weights.forEach((key, value) -> System.out.println(formatter + "\"" + key + "\": " + value));

    }

    // has to use initialize() before be used

    /**
     * Generates random sets of weights. Used in development for the purpose of identifying better weights.
     *
     * @param emailWeight Weight to give the email field (for weights1).
     * @param alternate   If the set of weights is default (with email), or alternate (without email).
     */
    @SuppressWarnings("Duplicates")
    public void getRandomWeights(double emailWeight, boolean alternate) {

        Random random = new Random();

        int sum;
        int[] intList;
        double[] randomWeights;

        if (alternate) {
            intList = new int[8];
            randomWeights = new double[8];
        } else {
            intList = new int[8];
            randomWeights = new double[8];
        }
        int i;

        for (i = 0; i < intList.length; i++) {
            // uniformly distributed numbers generated
            intList[i] = random.nextInt(100) + 1;
        }

        sum = sumSample(intList);

        if (alternate) {
            for (i = 0; i < intList.length; i++) {
                randomWeights[i] = ((double) intList[i] / (double) sum);
            }
        } else {
            for (i = 0; i < intList.length; i++) {
                randomWeights[i] = ((double) intList[i] / (double) sum) * (1 - emailWeight);
            }
        }

        if (alternate) {

            weights.replace("LastName", randomWeights[0]);
            weights.replace("MiddleName", randomWeights[1]);
            weights.replace("FirstName", randomWeights[2]);
            weights.put("FirmName", 0.00);
            weights.put("OfficeName", 0.00);
            weights.put("Email", 0.00);
            weights.replace("Phone", randomWeights[3]);
            weights.replace("Address", randomWeights[4]);
            weights.replace("City", randomWeights[5]);
            weights.replace("State", randomWeights[6]);
            weights.replace("Zip", randomWeights[7]);
            weights.put("Country", 0.00);


        } else {

            weights.replace("LastName", randomWeights[0]);
            weights.replace("MiddleName", randomWeights[1]);
            weights.replace("FirstName", randomWeights[2]);
            weights.put("FirmName", 0.00);
            weights.put("OfficeName", 0.00);
            weights.replace("Email", emailWeight);
            weights.replace("Phone", randomWeights[3]);
            weights.replace("Address", randomWeights[4]);
            weights.replace("City", randomWeights[5]);
            weights.replace("State", randomWeights[6]);
            weights.replace("Zip", randomWeights[7]);
            weights.put("Country", 0.00);

        }

        checkWeightSum();
    }

    /**
     * Sums an array of integers. Used in getRandomWeights() only.
     *
     * @param intList Array of integers.
     * @return Sum of all integers in the array.
     */
    private int sumSample(int[] intList) {

        int sum = 0;

        int i;
        for (i = 0; i < intList.length; i++) {

            sum += intList[i];

        }

        return sum;

    }

    /**
     * Custom exception. Thrown when weights don't sum to 1.
     */
    private class IllegalValuesException extends Exception {

        IllegalValuesException(String message) {
            super(message);
        }
    }

}
