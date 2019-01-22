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

package matcher;

import data.Contact;
import data.MasterContact;

public class CompareRecord {

    private Contact contact;
    private MasterContact masterContact;
    private Fuzzy calc;
    private Weights weights;

    public CompareRecord(Weights weights, MasterContact masterContact, Contact contact) {

        this.masterContact = masterContact;
        this.contact = contact;
        this.calc = new Fuzzy();
        this.weights = weights;

    }

    public double similarity(String name, String compareMethod) {

        double matchConfidence = 0.0;
        double weight = 1.0;
        name = name.toLowerCase();

        String masterData = null;
        String matchData = null;

        switch (name) {
            case "last":
                masterData = this.masterContact.getLastName();
                matchData = this.contact.getLastName();
                weight = this.weights.getLastNameWeight();
                break;
            case "middle":
                masterData = this.masterContact.getMiddleName();
                matchData = this.contact.getMiddleName();
                weight = this.weights.getMiddleNameWeight();
                break;
            case "first":
                masterData = this.masterContact.getFirstName();
                matchData = this.contact.getFirstName();
                weight = this.weights.getFirstNameWeight();
                break;
            case "firm":
                masterData = this.masterContact.getFirmName();
                matchData = this.contact.getFirmName();
                weight = this.weights.getFirmNameWeight();
                break;
            case "office":
                masterData = this.masterContact.getOfficeName();
                matchData = this.contact.getOfficeName();
                weight = this.weights.getOfficeNameWeight();
                break;
            case "email":
                // If both emails have the same domain, then
                // ignore the domain. For example, if given
                // john.doe@email.com & jon.doe@email.com,
                // we will then compare "john.doe" to "jon.doe".

                // Given john.doe@email.com & jon.doe@domain.com,
                // then we will compare the "john.doe@email.com"
                // to "jon.doe@domain.com".
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

                weight = this.weights.getEmailWeight();
                break;
            case "phone":
                masterData = this.masterContact.getBusinessPhone();
                matchData = this.contact.getBusinessPhone();
                weight = this.weights.getPhoneWeight();
                break;
            case "address":
                masterData = this.masterContact.getAddress();
                matchData = this.contact.getAddress();
                weight = this.weights.getAddressWeight();
                break;
            case "city":
                masterData = this.masterContact.getCity();
                matchData = this.contact.getCity();
                weight = this.weights.getCityWeight();
                break;
            case "state":
                masterData = this.masterContact.getStateProvince();
                matchData = this.contact.getStateProvince();
                weight = this.weights.getStateWeight();
                break;
            case "zip":
                masterData = this.masterContact.getZip();
                matchData = this.contact.getZip();
                weight = this.weights.getZipWeight();
                break;
            case "country":
                masterData = this.masterContact.getCountryID();
                matchData = this.contact.getCountryID();
                weight = this.weights.getCountryWeight();
                break;
        }

        matchConfidence = (double) calc.fuzzyStrCmp(masterData, matchData, compareMethod);
        return matchConfidence * weight;

    }

    public boolean CRD() {

        String masterCRD = this.masterContact.getCRDNumber();
        String matchCRD = this.contact.getCRDNumber();

        boolean nonEmptyCRD = (checkCRDNotEmpty(masterCRD) && checkCRDNotEmpty(matchCRD));

        if (nonEmptyCRD) {

            if (masterCRD.equalsIgnoreCase(matchCRD)) {
                return true;
            }

        }

        return false;

    }

    private boolean checkCRDNotEmpty(String CRDNum) {
        if (!CRDNum.equals("")) {
            return true;
        }
        return false;
    }

//    public boolean checkGroupedNames(String name, String masterData) {
//
//
//        // here we handle grouped names
//        if (name.equalsIgnoreCase("last") ||
//                name.equalsIgnoreCase("middle") ||
//                name.equalsIgnoreCase("first")) {
//
//            // if there are separators in the name, then use token-set ratio
//            if (masterData.contains("/") || masterData.contains("|") || masterData.contains("\\") ||
//                    masterData.contains(",") || masterData.contains(";")) {
//                return true;
//            }
//        }
//        return false;
//    }
}
