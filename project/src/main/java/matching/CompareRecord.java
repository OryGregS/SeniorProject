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
 * Developed by Gregory Smith & Axel Solano. Last modified 12/01/19 3:57 PM.
 * Copyright (c) 2019. All rights reserved.
 */

package matching;

import data.Contact;
import data.MasterContact;
import me.xdrop.fuzzywuzzy.FuzzySearch;

class CompareRecord {

    private Contact matchContact;
    private MasterContact masterContact;
    private Weights weights;

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

    CompareRecord(Weights weights, MasterContact masterContact, Contact matchContact) {

        this.masterContact = masterContact;
        this.matchContact = matchContact;
        this.weights = weights;
        loadWeights();

    }

    private void loadWeights() {

        this.LastNameWeight = this.weights.getWeight("LastName");
        this.MiddleNameWeight = this.weights.getWeight("MiddleName");
        this.FirstNameWeight = this.weights.getWeight("FirstName");
        this.FirmNameWeight = this.weights.getWeight("FirmName");
        this.OfficeNameWeight = this.weights.getWeight("OfficeName");
        this.EmailWeight = this.weights.getWeight("Email");
        this.PhoneWeight = this.weights.getWeight("Phone");
        this.AddressWeight = this.weights.getWeight("Address");
        this.CityWeight = this.weights.getWeight("City");
        this.StateWeight = this.weights.getWeight("State");
        this.ZipWeight = this.weights.getWeight("Zip");
        this.CountryWeight = this.weights.getWeight("Country");

    }

    double similarity(String name) {

        double matchConfidence;
        double weight = 1.0;
        name = name.toLowerCase();

        String masterData = "";
        String matchData = "";

        switch (name) {
            case "last":
                masterData = this.masterContact.getLastName();
                matchData = this.matchContact.getLastName();
                weight = this.LastNameWeight;
                break;
            case "middle":
                masterData = this.masterContact.getMiddleName();
                matchData = this.matchContact.getMiddleName();
                weight = this.MiddleNameWeight;
                break;
            case "first":
                masterData = this.masterContact.getFirstName();
                matchData = this.matchContact.getFirstName();
                weight = this.FirstNameWeight;
                break;
            case "firm":
                masterData = this.masterContact.getFirmName();
                matchData = this.matchContact.getFirmName();
                weight = this.FirmNameWeight;
                break;
            case "office":
                masterData = this.masterContact.getOfficeName();
                matchData = this.matchContact.getOfficeName();
                weight = this.OfficeNameWeight;
                break;
            case "email":
                String[] emails = handleEmail(this.masterContact.getEmail(), this.matchContact.getEmail());
                masterData = emails[0];
                matchData = emails[1];
                weight = this.EmailWeight;
                break;
            case "phone":
                masterData = this.masterContact.getBusinessPhone();
                matchData = this.matchContact.getBusinessPhone();
                weight = this.PhoneWeight;
                break;
            case "address":
                masterData = this.masterContact.getAddress();
                matchData = this.matchContact.getAddress();
                weight = this.AddressWeight;
                break;
            case "city":
                masterData = this.masterContact.getCity();
                matchData = this.matchContact.getCity();
                weight = this.CityWeight;
                break;
            case "state":
                masterData = this.masterContact.getStateProvince();
                matchData = this.matchContact.getStateProvince();
                weight = this.StateWeight;
                break;
            case "zip":
                masterData = this.masterContact.getZip();
                matchData = this.matchContact.getZip();
                weight = this.ZipWeight;
                break;
            case "country":
                masterData = this.masterContact.getCountryID();
                matchData = this.matchContact.getCountryID();
                weight = this.CountryWeight;
                break;
        }

        matchConfidence = cmpSimilarity(masterData, matchData);
        return matchConfidence * weight;

    }

    boolean CRD() {

        String masterCRD = this.masterContact.getCRDNumber();
        String matchCRD = this.matchContact.getCRDNumber();

        boolean nonEmptyCRD = (checkCRDNotEmpty(masterCRD) && checkCRDNotEmpty(matchCRD));

        if (nonEmptyCRD) {

            return masterCRD.equalsIgnoreCase(matchCRD);

        }

        return false;

    }

    double cmpSimilarity(String record1, String record2) {
        // FuzzySearch Ratio returns us an int
        // We cast it to double for calculations & more accuracy
        return (double) FuzzySearch.ratio(record1.toUpperCase(), record2.toUpperCase());
    }

    private boolean checkCRDNotEmpty(String CRDNum) {
        return !CRDNum.equals("");
    }

    private String[] handleEmail(String masterEmail, String matchEmail) {

        String[] emails = new String[2];

        // If both emails have the same domain, then
        // ignore the domain. For example, if given
        // john.doe@email.com & jon.doe@email.com,
        // we will then compare "john.doe" to "jon.doe".

        // Given john.doe@email.com & jon.doe@domain.com,
        // then we will compare "john.doe@email.com" to
        // "jon.doe@domain.com".

        if (!masterEmail.equalsIgnoreCase("") &&
                !matchEmail.equalsIgnoreCase("")) {

            try {

                String[] masterTemp = masterEmail.split("@");
                String[] matchTemp = matchEmail.split("@");

                if (masterTemp[1].equalsIgnoreCase(matchTemp[1])) {

                    emails[0] = masterTemp[0];
                    emails[1] = matchTemp[0];

                } else {

                    emails[0] = masterEmail;
                    emails[1] = matchEmail;

                }

            } catch (ArrayIndexOutOfBoundsException e) {

                emails[0] = masterEmail;
                emails[1] = matchEmail;

            }

        } else {

            emails[0] = masterEmail;
            emails[1] = matchEmail;
        }

        return emails;

    }

}
