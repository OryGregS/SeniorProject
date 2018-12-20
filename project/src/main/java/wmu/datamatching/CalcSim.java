package wmu.datamatching;

import me.xdrop.fuzzywuzzy.FuzzySearch;

import java.util.ArrayList;

/**
 * Class to compute similarity between strings
 */
public class CalcSim {


    /**
     * Ensures a method is valid
     *
     * @param compareMethod - Method used for comparison.
     *
     *                      Legal values (not case-sensitive):
     *                      "ratio", "partialRatio", "tokenSortRatio",
     *                      "tokenSortPartialRatio", "tokenSetRatio",
     *                      "tokenSetPartialRatio", "weightedRatio".
     * @return True if valid; False if not
     */
    protected boolean checkMethod(String compareMethod) {

        compareMethod = compareMethod.toLowerCase();

        switch (compareMethod) {
            case "ratio":
                return true;
            case "partialratio":
                return true;
            case "tokensortratio":
                return true;
            case "tokensortpartialratio":
                return true;
            case "tokensetratio":
                return true;
            case "tokensetpartialratio":
                return true;
            case "weightedratio":
                return true;

        }
        return false;
    }

    /**
     * Generic class to allow flexibility with FuzzySearch
     *
     * @param record1       - String from first record to be compared
     * @param record2       - String from second record to be compared
     * @param compareMethod - Method used for comparison.
     *
     *                      Legal values (not case-sensitive):
     *                      "ratio", "partialRatio", "tokenSortRatio",
     *                      "tokenSortPartialRatio", "tokenSetRatio",
     *                      "tokenSetPartialRatio", "weightedRatio".
     * @return -1 if is invalid request of method, -2 if an error occurs, otherwise return the result of FuzzySearch methods
     */
    protected int fuzzyStrCmp(String record1, String record2, String compareMethod) {

        compareMethod = compareMethod.toLowerCase();

        if (!checkMethod(compareMethod)) {
            return -1;
        }

        // return the requested
        switch (compareMethod) {
            case "ratio":
                return FuzzySearch.ratio(record1, record2);
            case "partialratio":
                return FuzzySearch.partialRatio(record1, record2);
            case "tokensortratio":
                return FuzzySearch.tokenSortRatio(record1, record2);
            case "tokensortpartialratio":
                return FuzzySearch.tokenSortPartialRatio(record1, record2);
            case "tokensetratio":
                return FuzzySearch.tokenSetRatio(record1, record2);
            case "tokensetpartialratio":
                return FuzzySearch.tokenSetPartialRatio(record1, record2);
            case "weightedratio":
                return FuzzySearch.weightedRatio(record1, record2);

        }

        // should never reach here
        return -2;

    }

    /**
     * Method to compare all fields for contacts with the same method.
     * Checks CRD number first to avoid unnecessary computation.
     *
     * @param contact1      - MasterContact object
     * @param contact2      - Contact object
     * @param compareMethod - Method of comparison to use
     */
    public int compareFields(MasterContact contact1,
                             Contact contact2,
                             String compareMethod) {

        int field;
        int confidenceSum = 0;
        ArrayList<String> contact1Data = contact1.getFieldList();
        int numFieldsCompared = contact1Data.size();

        if (contact1.getCRDNumber().equalsIgnoreCase(contact2.getCRDNumber())) {

            confidenceSum = contact1.getFieldList().size() * 100;

        } else {

            ArrayList<String> contact2Data = contact2.getFieldList();

            for (field = 0; field < contact1Data.size() &&
                    contact1Data.size() == contact2Data.size(); field++) {
                confidenceSum += fuzzyStrCmp(contact1Data.get(field),
                        contact2Data.get(field), compareMethod);
            }

        }

        return calcConfidence(confidenceSum, numFieldsCompared);
    }

    /**
     * Overloaded method to compare specific fields for contacts with the same method.
     * Checks CRD number first to avoid unnecessary computation.
     *
     * @param contact1      - MasterContact object
     * @param contact2      - Contact object
     * @param fieldsToCheck - List of fields to use for comparison
     * @param compareMethod - Method of comparison to use
     * @return 0 if the fields checking is wrong, otherwise 1 as ok.
     */
    public int compareFields(MasterContact contact1, Contact contact2,
                             String compareMethod, ArrayList<Integer> fieldsToCheck) {

        int field;
        int confidenceSum = 0;
        int numFieldsCompared = fieldsToCheck.size();
        String c1CRD = contact1.getCRDNumber();
        String c2CRD = contact2.getCRDNumber();

        if (checkCRDNotEmpty(c1CRD) && checkCRDNotEmpty(c2CRD) &&
                c1CRD.equalsIgnoreCase(c2CRD)) {

            confidenceSum = numFieldsCompared * 100;

        } else {

            ArrayList<String> contact1Data = contact1.getFieldList();
            ArrayList<String> contact2Data = contact2.getFieldList();
            int len = contact1Data.size();

            for (field = 0; field < len; field++) {
                if (fieldsToCheck.contains(field))
                    confidenceSum += fuzzyStrCmp(contact1Data.get(field),
                            contact2Data.get(field), compareMethod);
            }

        }

        return calcConfidence(confidenceSum, numFieldsCompared);

    }

    private boolean checkCRDNotEmpty(String CRDNum) {
        if (!CRDNum.equals("")) {
            return true;
        }
        return false;
    }

    private int calcConfidence(int confidenceSum, int numFieldsCompared) {
        return confidenceSum / numFieldsCompared;
    }

//    /***
//     * Function to use internally to check the specified fiels for each row
//     * @param contact1 ArrayList of all contact's data for a set
//     * @param contact2 ArrayList of all contact's data for another set
//     * @param fieldsToCheck List of fields to use for comparison
//     * @return -1 if contact1 has different length from contact2, 0 if the fields does not exists, otherwise 1 as ok.
//     */
//    private int fieldsChecked(ArrayList<String> contact1, ArrayList<String> contact2, ArrayList<Integer> fieldsToCheck){
//        if (contact1.size() != contact2.size()){
//            return -1;
//        }
//        int field;
//        int len = contact1.size();
//        int isOk = 1;
//        for (field = 0; field < len ; field++) {
//            if (fieldsToCheck.contains(field)==false) {
//                isOk = 0;
//                break;
//            }
//        }
//
//        return isOk;
//    }
}
