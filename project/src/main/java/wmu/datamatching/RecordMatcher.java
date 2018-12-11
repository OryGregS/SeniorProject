package wmu.datamatching;

import me.xdrop.fuzzywuzzy.FuzzySearch;

import java.util.ArrayList;

/**
 * Class to compute similarity between strings
 */
public class RecordMatcher {

    /**
     * Private field that gets populated in compareFields()
     */
    private int confidenceSum = 0;

    /**
     * Generic class to allow flexibility with FuzzySearch
     * @param record1 - String from first record to be compared
     * @param record2 - String from second record to be compared
     * @param compareMethod - Method used for comparison.
     *
     *                      Legal values (not case-sensitive):
     *                      "ratio", "partialRatio", "tokenSortRatio",
     *                      "tokenSortPartialRatio", "tokenSetRatio",
     *                      "tokenSetPartialRatio", "weightedRatio".
     * @return
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
     * Method to compare all fields for contacts with the same method
     * @param contact1 - ArrayList of all contact's data for a set
     * @param contact2 - ArrayList of all contact's data for another set
     * @param compareMethod - Method of comparison to use
     */
    public void compareFields(ArrayList<String> contact1,
                              ArrayList<String> contact2,
                              String compareMethod) {

        int field;

        for (field = 0; field < contact1.size() && contact1.size() == contact2.size(); field++) {


            this.confidenceSum += fuzzyStrCmp(contact1.get(field), contact2.get(field), compareMethod);


        }

    }

    /**
     * Overloaded method to compare specific fields for contacts with the same method
     * @param contact1 - ArrayList of all contact's data for a set
     * @param contact2 - ArrayList of all contact's data for another set
     * @param fieldsToCheck - List of fields to use for comparison
     * @param compareMethod - Method of comparison to use
     */
    public void compareFields(ArrayList<String> contact1,
                              ArrayList<String> contact2,
                              String compareMethod,
                              ArrayList<Integer> fieldsToCheck) {

        int field;

        for (field = 0; field < contact1.size() && contact1.size() == contact2.size(); field++) {

            if (fieldsToCheck.contains(field)) {
                this.confidenceSum += fuzzyStrCmp(contact1.get(field), contact2.get(field), compareMethod);
            }

        }

    }

    /**
     * Ensures a method is valid
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
     * Returns the class field confidenceSum
     * @return
     */
    public int getSum() {
        return this.confidenceSum;
    }



}
