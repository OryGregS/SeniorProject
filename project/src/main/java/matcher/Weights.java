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

package matcher;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.*;

public class Weights {

    private class IllegalValuesException extends Exception {
        private double sumValue;
        private final double MAX_WEIGHT_SUM = 1.0;

        public IllegalValuesException(double sum) {
            this.sumValue = sum;
        }

        public void printMessage() {
            if (sumValue > MAX_WEIGHT_SUM)
                System.out.println("Weights less than 1.0!");
            else if (sumValue < 1.0)
                System.out.println("Weights greater than 1.0!");
            System.out.println("Weights must add up to equal 1.0 for proper functionality.");
            System.out.println("Please re-adjust the weights accordingly.");
        }
    }

    private double LastNameWeight;
    private double MiddleNameWeight;
    private double FirstNameWeight;
    private double FirmNameWeight;
    private double OfficeNameWeight;
    private double EmailWeight;
    private double PhoneWeight;
    private double AddressWeight;
    private double CityWeight;
    private double StateWeight;
    private double ZipWeight;
    private double CountryWeight;

    private double sum;
    public Weights(boolean altWeights) {

        sum = 0.0;

        if (altWeights) {

            initialize("./config/weights/alternateWeights.json");

        } else {

            initialize("./config/weights/defaultWeights.json");

        }



    }

    public double getLastNameWeight() {
        return LastNameWeight;
    }

    public void setLastNameWeight(double lastNameWeight) {
        LastNameWeight = lastNameWeight;
    }

    public double getMiddleNameWeight() {
        return MiddleNameWeight;
    }

    public void setMiddleNameWeight(double middleNameWeight) {
        MiddleNameWeight = middleNameWeight;
    }

    public double getFirstNameWeight() {
        return FirstNameWeight;
    }

    public void setFirstNameWeight(double firstNameWeight) {
        FirstNameWeight = firstNameWeight;
    }

    public double getFirmNameWeight() {
        return FirmNameWeight;
    }

    public void setFirmNameWeight(double firmNameWeight) {
        FirmNameWeight = firmNameWeight;
    }

    public double getOfficeNameWeight() {
        return OfficeNameWeight;
    }

    public void setOfficeNameWeight(double officeNameWeight) {
        OfficeNameWeight = officeNameWeight;
    }

    public double getEmailWeight() {
        return EmailWeight;
    }

    public void setEmailWeight(double emailWeight) {
        EmailWeight = emailWeight;
    }

    public double getPhoneWeight() {
        return PhoneWeight;
    }

    public void setPhoneWeight(double phoneWeight) {
        PhoneWeight = phoneWeight;
    }

    public double getAddressWeight() {
        return AddressWeight;
    }

    public void setAddressWeight(double addressWeight) {
        AddressWeight = addressWeight;
    }

    public double getCityWeight() {
        return CityWeight;
    }

    public void setCityWeight(double cityWeight) {
        CityWeight = cityWeight;
    }

    public double getStateWeight() {
        return StateWeight;
    }

    public void setStateWeight(double stateWeight) {
        StateWeight = stateWeight;
    }

    public double getZipWeight() {
        return ZipWeight;
    }

    public void setZip1Weight(double zipWeight) {
        ZipWeight = zipWeight;
    }

    public double getCountryWeight() {
        return CountryWeight;
    }

    public void setCountryWeight(double countryWeight) {
        CountryWeight = countryWeight;
    }

    private void checkWeightSum() {
        try {

            sumWeights(LastNameWeight);
            sumWeights(MiddleNameWeight);
            sumWeights(FirstNameWeight);
            sumWeights(FirmNameWeight);
            sumWeights(OfficeNameWeight);
            sumWeights(EmailWeight);
            sumWeights(PhoneWeight);
            sumWeights(AddressWeight);
            sumWeights(CityWeight);
            sumWeights(StateWeight);
            sumWeights(ZipWeight);
            sumWeights(CountryWeight);

            if (sum != 1.0) {
                throw new IllegalValuesException(sum);
            }
        } catch (IllegalValuesException e) {
            e.printMessage();
            System.exit(-1);
        }

    }

    private void sumWeights(double value) {
        sum += value;
    }

    private void initialize(String fileName) {

        if (checkJSONExists(fileName)) {
            readJSON(fileName);
        } else {
            setDefaults();
            writeJSON(fileName);
        }

    }

    private void setDefaults() {

        LastNameWeight = 0.259;
        MiddleNameWeight = 0.05;
        FirstNameWeight = 0.15;
        FirmNameWeight = 0;
        OfficeNameWeight = 0;
        EmailWeight = 0.35;
        PhoneWeight = 0.05;
        AddressWeight = 0.06;
        CityWeight = 0.05;
        StateWeight = 0.02;
        ZipWeight = 0.011;
        CountryWeight = 0;

        checkWeightSum();

    }

    private void setAlternate() {

        LastNameWeight = 0.25;
        MiddleNameWeight = 0.1;
        FirstNameWeight = 0.25;
        FirmNameWeight = 0;
        OfficeNameWeight = 0;
        EmailWeight = 0;
        PhoneWeight = 0.075;
        AddressWeight = 0.175;
        CityWeight = 0.05;
        StateWeight = 0.05;
        ZipWeight = 0.05;
        CountryWeight = 0;

        checkWeightSum();

    }

    private boolean checkJSONExists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    private void writeJSON(String fileName) {
        JSONObject jo = new JSONObject();
        jo.put("LastNameWeight", LastNameWeight);
        jo.put("MiddleNameWeight", MiddleNameWeight);
        jo.put("FirstNameWeight", FirstNameWeight);
        jo.put("FirmNameWeight", FirmNameWeight);
        jo.put("OfficeNameWeight", OfficeNameWeight);
        jo.put("EmailWeight", EmailWeight);
        jo.put("PhoneWeight", PhoneWeight);
        jo.put("AddressWeight", AddressWeight);
        jo.put("CityWeight", CityWeight);
        jo.put("StateWeight", StateWeight);
        jo.put("ZipWeight", ZipWeight);
        jo.put("CountryWeight", CountryWeight);

        try {

            PrintWriter pw = new PrintWriter(fileName);
            pw.write(jo.toString(4));
            pw.flush();
            pw.close();

        } catch (FileNotFoundException e) {

            System.out.println("Failed to write " + fileName);

        }
    }

    private void readJSON(String fileName) {

        File file = new File(fileName);

        try {

            String content = FileUtils.readFileToString(file, "utf-8");
            JSONObject jo = new JSONObject(content);

            LastNameWeight = jo.getDouble("LastNameWeight");
            MiddleNameWeight = jo.getDouble("MiddleNameWeight");
            FirstNameWeight = jo.getDouble("FirstNameWeight");
            FirmNameWeight = jo.getDouble("FirmNameWeight");
            OfficeNameWeight = jo.getDouble("OfficeNameWeight");
            EmailWeight = jo.getDouble("EmailWeight");
            PhoneWeight = jo.getDouble("PhoneWeight");
            AddressWeight = jo.getDouble("AddressWeight");
            CityWeight = jo.getDouble("CityWeight");
            StateWeight = jo.getDouble("StateWeight");
            ZipWeight = jo.getDouble("ZipWeight");
            CountryWeight = jo.getDouble("CountryWeight");

        } catch (IOException e) {

            setDefaults();
            writeJSON(fileName);

        }

        //System.out.println("Read JSON file!");

    }

}
