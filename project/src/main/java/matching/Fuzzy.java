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
 * Developed by Gregory Smith & Axel Solano. Last modified 08/01/19 5:35 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package matching;

import me.xdrop.fuzzywuzzy.FuzzySearch;


/**
 * Class to compute similarity between strings
 */
public class Fuzzy {


    /**
     * Ensures a method is valid.
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

        String data1 = record1.toUpperCase();
        String data2 = record2.toUpperCase();

        // return the requested
        switch (compareMethod) {
            case "ratio":
                return FuzzySearch.ratio(data1, data2);
            case "partialratio":
                return FuzzySearch.partialRatio(data1, data2);
            case "tokensortratio":
                return FuzzySearch.tokenSortRatio(data1, data2);
            case "tokensortpartialratio":
                return FuzzySearch.tokenSortPartialRatio(data1, data2);
            case "tokensetratio":
                return FuzzySearch.tokenSetRatio(data1, data2);
            case "tokensetpartialratio":
                return FuzzySearch.tokenSetPartialRatio(data1, data2);
            case "weightedratio":
                return FuzzySearch.weightedRatio(data1, data2);

        }

        // should never reach here
        return -2;

    }
}
