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



    private static final String PATH_NAME = "./config/";
    private static final String FILE_NAME = "weights.json";


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
    private double Zip1Weight;
    private double Zip2Weight;
    private double CountryWeight;
    private double FullNameWeight;

    private double sum;
    public Weights() {

        initialize();

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

    public double getZip1Weight() {
        return Zip1Weight;
    }

    public void setZip1Weight(double zip1Weight) {
        Zip1Weight = zip1Weight;
    }

    public double getZip2Weight() {
        return Zip2Weight;
    }

    public void setZip2Weight(double zip2Weight) {
        Zip2Weight = zip2Weight;
    }

    public double getCountryWeight() {
        return CountryWeight;
    }

    public void setCountryWeight(double countryWeight) {
        CountryWeight = countryWeight;
    }

    public double getFullNameWeight() {
        return FullNameWeight;
    }

    public void setFullNameWeight(double fullNameWeight) {
        FullNameWeight = fullNameWeight;
    }

    private void checkWeightSum() {
        double sum = 0.0;
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
            sumWeights(Zip1Weight);
            sumWeights(Zip2Weight);
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
        sum = 0.0;
        sum += value;
    }

    private void initialize() {

        if (checkJSONExists()) {
            readJSON();
        } else {
            setDefaults();
            writeJSON();
        }

    }

    private void setDefaults() {

        LastNameWeight = 0.209;
        MiddleNameWeight = 0.05;
        FirstNameWeight = 0.1;
        FirmNameWeight = 0;
        OfficeNameWeight = 0;
        EmailWeight = 0.45;
        PhoneWeight = 0.05;
        AddressWeight = 0.06;
        CityWeight = 0.05;
        StateWeight = 0.02;
        Zip1Weight = 0.01;
        Zip2Weight = 0.001;
        CountryWeight = 0;
        FullNameWeight = LastNameWeight + MiddleNameWeight + FirstNameWeight;

        checkWeightSum();

    }

    private boolean checkJSONExists() {
        File file = new File(PATH_NAME + FILE_NAME);
        return file.exists();
    }

    private void writeJSON() {
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
        jo.put("Zip1Weight", Zip1Weight);
        jo.put("Zip2Weight", Zip2Weight);
        jo.put("CountryWeight", CountryWeight);

        try {

            PrintWriter pw = new PrintWriter(PATH_NAME + FILE_NAME);
            pw.write(jo.toString(4));
            pw.flush();
            pw.close();

        } catch (FileNotFoundException e) {

            System.out.println("Failed to write " + PATH_NAME + FILE_NAME);

        }
    }

    private void readJSON() {

        File file = new File(PATH_NAME + FILE_NAME);

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
            Zip1Weight = jo.getDouble("Zip1Weight");
            Zip2Weight = jo.getDouble("Zip2Weight");
            CountryWeight = jo.getDouble("CountryWeight");

        } catch (IOException e) {

            setDefaults();
            writeJSON();

        }

        //System.out.println("Read JSON file!");

    }

}
