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
 * Developed by Gregory Smith & Axel Solano. Last modified 2/9/19 10:33 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package processing;

public class CSVInputException extends Throwable {

    public CSVInputException(String path, String fileName) {
        this(String.format("\nError reading CSV file: %s%s\n", path, fileName));
    }

    public CSVInputException(String message) {
        super(message);
    }

}
