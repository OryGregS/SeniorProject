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

import dataholder.Contact;
import dataholder.MasterContact;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import utils.Weights;
import utils.weightNames;

/**
 * CompareRecord performs our calculation of similarity (confidence).
 */
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

    /**
     * Constructor to get necessary info for matching.
     *
     * @param weights       Weights object.
     * @param masterContact MasterContact object.
     * @param matchContact  Contact object.
     */
    CompareRecord(Weights weights, MasterContact masterContact, Contact matchContact) {

        this.masterContact = masterContact;
        this.matchContact = matchContact;
        this.weights = weights;
        loadWeights();

    }

    /**
     * Loads the weights from Weights object (passed in constructor).
     */
    private void loadWeights() {

        this.LastNameWeight = this.weights.getWeight(weightNames.LAST_NAME.name());
        this.MiddleNameWeight = this.weights.getWeight(weightNames.MIDDLE_NAME.name());
        this.FirstNameWeight = this.weights.getWeight(weightNames.FIRST_NAME.name());
        this.FirmNameWeight = this.weights.getWeight(weightNames.FIRM_NAME.name());
        this.OfficeNameWeight = this.weights.getWeight(weightNames.OFFICE_NAME.name());
        this.EmailWeight = this.weights.getWeight(weightNames.EMAIL.name());
        this.PhoneWeight = this.weights.getWeight(weightNames.PHONE.name());
        this.AddressWeight = this.weights.getWeight(weightNames.ADDRESS.name());
        this.CityWeight = this.weights.getWeight(weightNames.CITY.name());
        this.StateWeight = this.weights.getWeight(weightNames.STATE.name());
        this.ZipWeight = this.weights.getWeight(weightNames.ZIP.name());
        this.CountryWeight = this.weights.getWeight(weightNames.COUNTRY.name());

    }

    /**
     * Gets a field's data from the MasterContact and Contact object,
     * and the field's weight. Then calls levenRatio to get our ratio
     * of similarity between the two strings. This then multiplies that
     * ratio by the field's weight.
     *
     * @param field Data in a column for a record.
     * @return Weighted level of similarity between the MasterContact and Contact's data.
     */
    double similarity(String field) {

        double matchConfidence;
        double weight = 1.0;
        field = field.toLowerCase();

        String masterData = "";
        String matchData = "";

        switch (field) {
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

        matchConfidence = levenRatio(masterData, matchData);
        return matchConfidence * weight;

    }

    /**
     * Checks if the MasterContact and Contact are a known match.
     *
     * @return True if CRDNumber matches. False if not (or empty).
     */
    boolean CRD() {

        String masterCRD = this.masterContact.getCRDNumber();
        String matchCRD = this.matchContact.getCRDNumber();

        boolean nonEmptyCRD = (checkCRDNotEmpty(masterCRD) && checkCRDNotEmpty(matchCRD));

        if (nonEmptyCRD) {

            return masterCRD.equalsIgnoreCase(matchCRD);

        }

        return false;

    }

    /**
     * Calls FuzzyWuzzy's 'ratio' function. This returns us with
     * a simple ratio of similarity between the two Strings.
     *
     * @param masterData Data-field from MasterContact object.
     * @param matchData  Data-field from Contact object.
     * @return Ratio of similarity (casted to double).
     */
    double levenRatio(String masterData, String matchData) {
        // FuzzySearch Ratio returns an integer.
        // We cast it to double for more accuracy & for calculations.
        return (double) FuzzySearch.ratio(masterData.toUpperCase(), matchData.toUpperCase());
    }

    /**
     * Checks if CRDNumber is an empty string.
     *
     * @param CRDNum Unique data-field per contact.
     * @return True if not empty. False if empty.
     */
    private boolean checkCRDNotEmpty(String CRDNum) {
        return !CRDNum.equals("");
    }

    /**
     * Attempts to split email addresses at the @ symbol. If
     * the two emails have the same domain (i.e gmail.com), then
     * it we only compare the similarity between the handle (i.e john.doe).
     *
     * @param masterEmail MasterContact's email.
     * @param matchEmail  Contact's email.
     * @return Array of strings. This is used in the switch-case in this
     * class's similarity function.
     */
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
