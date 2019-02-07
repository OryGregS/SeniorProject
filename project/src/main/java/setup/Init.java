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
 * Developed by Gregory Smith & Axel Solano. Last modified 1/29/19 4:49 PM.
 * Copyright (c) 2019. All rights reserved.
 */

package setup;

import data.CSVReader;
import indexing.Indexer;
import matching.Matcher;
import matching.Weights;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Init reads a .properties file for the project and initializes objects with the values.
 */
public class Init {

    private final String FILE = "./config/DataMatching.properties";
    private Properties props;

    private Indexer indexer;
    private CSVReader csvReader;
    private Matcher matcher;
    private Weights weights1;
    private Weights weights2;

    /**
     * Reads the properties file and initializes objects
     * with the values when this class is instantiated.
     */
    public Init() {
        getProperties();
        initialize();
    }

    /**
     * Reads the key-value pairs for default values for objects.
     * This file is located at ./config/DataMatching.properties
     */
    private void getProperties() {

        this.props = new Properties();

        try {

            props.load(new FileInputStream(this.FILE));

        } catch (IOException e) {

            e.printStackTrace();
            System.out.printf("\nFailed to read initial properties from %s", this.FILE);

        }

    }

    /**
     * Uses the key-value pairs from the properties
     * file to instantiate and initialize objects.
     */
    private void initialize() {

        String masterPath = props.getProperty("masterPath");
        String matchPath = props.getProperty("matchPath");
        String weightsPath = props.getProperty("weightsPath");
        String indexMethod = props.getProperty("indexMethod");
        double threshold = Double.valueOf(props.getProperty("threshold"));

        // Initialize Indexer's state
        this.indexer = new Indexer(indexMethod);
        // Initialize CSVReader's state
        this.csvReader = new CSVReader(masterPath, matchPath, indexer);

        // Initialize weights for Matcher
        this.weights1 = new Weights(weightsPath);
        this.weights2 = new Weights(weightsPath);

        this.weights1.initialize("weights1.json");
        this.weights2.initialize("weights2.json");

        // Initialize Matcher's state
        this.matcher = new Matcher(this.weights1, this.weights2);
        this.matcher.setThreshold(threshold);

    }

    /**
     * Gets the Indexer object.
     *
     * @return
     *          Initialized Indexer object.
     */
    public Indexer getIndexer() {
        return this.indexer;
    }

    /**
     * Gets the CSVReader object.
     *
     * @return
     *          Initialized CSVReader object.
     */
    public CSVReader getCsvReader() {
        return this.csvReader;
    }

    /**
     * Gets the Matcher object.
     *
     * @return
     *          Initialized Matcher object.
     */
    public Matcher getMatcher() {
        return this.matcher;
    }

}
