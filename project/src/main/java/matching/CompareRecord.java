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

class CompareRecord {

    private Contact contact;
    private MasterContact masterContact;
    private Fuzzy calc;
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

    CompareRecord(Weights weights, MasterContact masterContact, Contact contact) {

        this.masterContact = masterContact;
        this.contact = contact;
        this.calc = new Fuzzy();
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

    double similarity(String name, String compareMethod) {

        double matchConfidence;
        double weight = 1.0;
        name = name.toLowerCase();

        String masterData = null;
        String matchData = null;

        switch (name) {
            case "last":
                masterData = this.masterContact.getLastName();
                matchData = this.contact.getLastName();
                weight = this.LastNameWeight;
                break;
            case "middle":
                masterData = this.masterContact.getMiddleName();
                matchData = this.contact.getMiddleName();
                weight = this.MiddleNameWeight;
                break;
            case "first":
                masterData = this.masterContact.getFirstName();
                matchData = this.contact.getFirstName();
                weight = this.FirstNameWeight;
                break;
            case "firm":
                masterData = this.masterContact.getFirmName();
                matchData = this.contact.getFirmName();
                weight = this.FirmNameWeight;
                break;
            case "office":
                masterData = this.masterContact.getOfficeName();
                matchData = this.contact.getOfficeName();
                weight = this.OfficeNameWeight;
                break;
            case "email":
                // If both emails have the same domain, then
                // ignore the domain. For example, if given
                // john.doe@email.com & jon.doe@email.com,
                // we will then compare "john.doe" to "jon.doe".

                // Given john.doe@email.com & jon.doe@domain.com,
                // then we will compare "john.doe@email.com" to
                // "jon.doe@domain.com".
                String masterEmail = this.masterContact.getEmail();
                String matchEmail = this.contact.getEmail();

                if (!masterEmail.equalsIgnoreCase("") &&
                        !matchEmail.equalsIgnoreCase("")) {

                    try {

                        String[] masterTemp = masterEmail.split("@");
                        String[] matchTemp = matchEmail.split("@");

                        if (masterTemp[1].equalsIgnoreCase(matchTemp[1])) {

                            masterData = masterTemp[0];
                            matchData = matchTemp[0];

                        } else {

                            masterData = masterEmail;
                            matchData = matchEmail;

                        }

                    } catch (ArrayIndexOutOfBoundsException e) {
                        masterData = masterEmail;
                        matchData = matchEmail;
                    }

                } else {
                    masterData = masterEmail;
                    matchData = matchEmail;
                }

                weight = this.EmailWeight;
                break;
            case "phone":
                masterData = this.masterContact.getBusinessPhone();
                matchData = this.contact.getBusinessPhone();
                weight = this.PhoneWeight;
                break;
            case "address":
                masterData = this.masterContact.getAddress();
                matchData = this.contact.getAddress();
                weight = this.AddressWeight;
                break;
            case "city":
                masterData = this.masterContact.getCity();
                matchData = this.contact.getCity();
                weight = this.CityWeight;
                break;
            case "state":
                masterData = this.masterContact.getStateProvince();
                matchData = this.contact.getStateProvince();
                weight = this.StateWeight;
                break;
            case "zip":
                masterData = this.masterContact.getZip();
                matchData = this.contact.getZip();
                weight = this.ZipWeight;
                break;
            case "country":
                masterData = this.masterContact.getCountryID();
                matchData = this.contact.getCountryID();
                weight = this.CountryWeight;
                break;
        }

        matchConfidence = (double) calc.fuzzyStrCmp(masterData, matchData, compareMethod);
        return matchConfidence * weight;

    }

    boolean CRD() {

        String masterCRD = this.masterContact.getCRDNumber();
        String matchCRD = this.contact.getCRDNumber();

        boolean nonEmptyCRD = (checkCRDNotEmpty(masterCRD) && checkCRDNotEmpty(matchCRD));

        if (nonEmptyCRD) {

            return masterCRD.equalsIgnoreCase(matchCRD);

        }

        return false;

    }

    private boolean checkCRDNotEmpty(String CRDNum) {
        return !CRDNum.equals("");
    }

}
