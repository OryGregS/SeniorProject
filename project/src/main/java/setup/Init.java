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

    final String FILE = "./config/DataMatching.properties";

    String masterPath;
    String matchPath;
    String weightsPath;
    String indexMethod;
    double threshold;
    int topMatchesListSize;
    boolean printTopMatches;

    Properties props;
    Indexer indexer;
    CSVReader csvReader;
    Matcher matcher;
    Weights weights1;
    Weights weights2;

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

            this.masterPath = props.getProperty("masterPath", "./data/sampledata/");
            this.matchPath = props.getProperty("matchPath", "./data/sampledata/matches/");
            this.weightsPath = props.getProperty("weightsPath", "./config/weights/");
            this.indexMethod = props.getProperty("indexMethod", "metaphone");
            this.threshold = Double.valueOf(props.getProperty("threshold", "70"));
            this.topMatchesListSize = Integer.valueOf(props.getProperty("topMatchesListSize", "10"));
            this.printTopMatches = Boolean.valueOf(props.getProperty("printTopMatches", "true"));

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

    public String getMasterPath() {
        return masterPath;
    }

    public String getMatchPath() {
        return matchPath;
    }

    public String getWeightsPath() {
        return weightsPath;
    }

    public String getIndexMethod() {
        return indexMethod;
    }

    public double getThreshold() {
        return threshold;
    }

    public int getTopMatchesListSize() {
        return topMatchesListSize;
    }

    public boolean isPrintTopMatches() {
        return printTopMatches;
    }

}
