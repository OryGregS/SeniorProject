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

    private double FullNameWeight;
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

    public Weights() {

        initializeDefaults();

    }

    public double getFullNameWeight() {
        return FullNameWeight;
    }

    public void setFullNameWeight(double fullNameWeight) {
        FullNameWeight = fullNameWeight;
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

    private void checkWeightSum() {
        double sum = 0.0;
        try {
            sum += LastNameWeight;
            sum += MiddleNameWeight;
            sum += FirstNameWeight;
            sum += FirmNameWeight;
            sum += OfficeNameWeight;
            sum += EmailWeight;
            sum += PhoneWeight;
            sum += AddressWeight;
            sum += CityWeight;
            sum += StateWeight;
            sum += Zip1Weight;
            sum += Zip2Weight;
            sum += CountryWeight;


            if (sum != 1.0) {
                throw new IllegalValuesException(sum);
            }
        } catch (IllegalValuesException e) {
            e.printMessage();
            System.exit(-1);
        }

    }

    private void initializeDefaults() {
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
}
