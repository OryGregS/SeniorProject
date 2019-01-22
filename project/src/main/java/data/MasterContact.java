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

import java.util.*;

public class MasterContact extends Contact {

    private ArrayList<Contact> exactMatches;
    private TopMatches topMatches;

    /**
     * Default constructor to initialize fields
     * based on their default values
     */
    public MasterContact() {
        this.exactMatches = new ArrayList<>();
        this.topMatches = new TopMatches();
    }

    /**
     * Adds a contact to the list of matches where the unique identifier matches.
     * @param contact - Contact data for a match
     */
    public void addKnownMatch(Contact contact) {
        this.exactMatches.add(contact);
    }

    /**
     * Gets the list of matches where the unique identifier matches.
     * @return - ArrayList of contact data
     */
    public ArrayList<Contact> getExactMatchList() {
        return this.exactMatches;
    }

    /**
     * This method attempts to add the potential contact in the
     * lists of the TopMatches object.
     * @param contact - new match's data
     * @param confidence - new match's level of confidence of similarity
     */
    public void setMatch(Contact contact, double confidence) {
        this.topMatches.setMatch(contact, confidence);
    }


    /**
     * Gets the list contact data of the master-contact's most likely matches
     * in the TopMatches object.
     * @return - ArrayList of contact data
     */
    public ArrayList<Contact> getTopContacts() {
        return this.topMatches.getTopContacts();
    }

    /**
     * Gets the list of confidences for each contact in the top contacts
     * list in the TopMatches object.
     * @return - ArrayList of double values
     */
    public ArrayList<Double> getTopConfidence() {
        return this.topMatches.getTopConfidence();
    }


}

