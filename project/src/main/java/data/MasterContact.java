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
 * Developed by Gregory Smith & Axel Solano. Last modified 08/01/19 5:59 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package data;

import java.util.ArrayList;

public class MasterContact extends Contact {

    private int knownMatches;
    private TopMatches topMatches;
    private int unknownMatchCount;
    private int identifiedMatchCount;

    /**
     * Default constructor to initialize fields
     * based on their default values
     */
    public MasterContact() {
        this.topMatches = new TopMatches();
        unknownMatchCount = 0;
        identifiedMatchCount = 0;
    }

    public void addUnknownMatch() {
        this.unknownMatchCount++;
    }

    public void addIdentifiedMatch() {

        this.identifiedMatchCount++;

    }

    /**
     * Increments our counter for the number of exact matches
     * (unique identifiers are equal).
     */
    public void addKnownMatch() {
        this.knownMatches++;
    }

    /**
     * Gets the list of matches where the unique identifier matches.
     *
     * @return - ArrayList of contact data
     */
    public int getKnownMatches() {
        return this.knownMatches;
    }

    public int getUnknownMatches() {
        return this.unknownMatchCount;
    }

    public int getIdentifiedMatchCount() {
        return this.identifiedMatchCount;
    }

    /**
     * This method attempts to add the potential contact in the
     * lists of the TopMatches object.
     *
     * @param contact    - new match's data
     * @param confidence - new match's level of confidence of similarity
     */
    public boolean setMatch(Contact contact, double confidence) {
        if (confidence < 0.0 || confidence > 100){
            return false;
        }
        if (contact != null) {
            this.topMatches.setMatch(contact, confidence);


            return true;
        }
        return false;
    }
    //


    /**
     * Gets the list contact data of the master-contact's most likely matches
     * in the TopMatches object.
     *
     * @return - ArrayList of contact data
     */
    public ArrayList<Contact> getTopContacts() {
        return this.topMatches.getTopContacts();
    }

    /**
     * Gets the list of confidences for each contact in the top contacts
     * list in the TopMatches object.
     *
     * @return - ArrayList of double values
     */
    public ArrayList<Double> getTopConfidence() {
        return this.topMatches.getTopConfidence();
    }






}

