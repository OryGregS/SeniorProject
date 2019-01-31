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
 * Developed by Gregory Smith & Axel Solano. Last modified 12/01/19 4:27 PM.
 * Copyright (c) 2019. All rights reserved.
 */

package matching;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class Weights {

    private Map<String, Double> weights;
    private String FILEPATH;
    private String FILENAME;

    public Weights(String filePath) {
        this.FILEPATH = filePath;
        this.weights = new HashMap<>();
    }

    public double getWeight(String key) {

        double weight = 0.0;

        if (keyExists(key)) {

            weight = this.weights.get(key);

        }

        return weight;

    }

    public void initialize(String fileName) {

        readJSON(fileName);

    }

    private boolean keyExists(String key) {
        return weights.containsKey(key);
    }

    private void checkWeightSum() {
        try {
            weightSum();
        } catch (IllegalValuesException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    private void weightSum() throws IllegalValuesException {

        double sum = 0.0;

        for (Iterator values = weights.values().iterator(); values.hasNext(); ) {

            sum += (double) values.next();

        }

        double scale = Math.pow(10, 6);
        sum = Math.round(sum * scale) / scale;


        if (sum != 1.0) {

            String message = String.format("\nError reading weights from %s.\n" +
                    "\nExpected 1.0 but got %.16f. " +
                    "\nWeights must be equal to 1.0 for proper functionality. " +
                    "\nPlease adjust the weights in the config/weights directory.", this.FILENAME, sum);

            throw new IllegalValuesException(message);

        }

    }

    private boolean checkJSONExists() {
        File file = new File(this.FILEPATH + this.FILENAME);
        return file.exists();
    }

    public void writeJSON() {

        JSONObject jo = new JSONObject();

        Iterator kvPairs = weights.entrySet().iterator();

        while (kvPairs.hasNext()) {

            Map.Entry pair = (Map.Entry) kvPairs.next();

            String key = pair.getKey().toString();
            Double value = (double) pair.getValue();

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

    private boolean readJSON(String fileName) {

        this.FILENAME = fileName;

        File file = new File(this.FILEPATH + this.FILENAME);

        try {

            String content = FileUtils.readFileToString(file, "utf-8");
            JSONObject jo = new JSONObject(content);

            for (Iterator keys = jo.keys(); keys.hasNext(); ) {

                String key = keys.next().toString();
                double value = jo.getDouble(key);

                weights.put(key, value);

            }

            checkWeightSum();
            return true;

        } catch (IOException e) {

            e.printStackTrace();
            System.out.printf("\nFailed to read Weights file: %s%s\n", this.FILEPATH, this.FILEPATH);


        }
        return false;
    }

    public void printWeights(String formatter) {

        weights.forEach((key, value) -> System.out.println(formatter + "\"" + key + "\": " + value));

    }

    public void sample(double emailWeight, boolean alternate) {

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

    private int sumSample(int[] intList) {

        int sum = 0;

        int i;
        for (i = 0; i < intList.length; i++) {

            sum += intList[i];

        }

        return sum;

    }

    private class IllegalValuesException extends Exception {

        IllegalValuesException(String message) {
            super(message);
        }
    }

}
