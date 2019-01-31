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
 * Developed by Gregory Smith & Axel Solano. Last modified 1/29/19 4:42 PM.
 * Copyright (c) 2019. All rights reserved.
 */

package data;

import java.util.ArrayList;

class TopMatches {

    private int MAX_MATCH_SIZE;
    private int MAX_LOC;
    private ArrayList<Double> topConfidence;
    private ArrayList<Contact> topContact;

    /**
     * Default constructor to initialize fields
     * based on their default values
     */
    TopMatches() {
        this.MAX_MATCH_SIZE = 25;
        this.MAX_LOC = this.MAX_MATCH_SIZE - 1;
        this.topConfidence = new ArrayList<>(MAX_MATCH_SIZE);
        this.topContact = new ArrayList<>(MAX_MATCH_SIZE);
    }

    /**
     * Gets the contact list for
     * a master contact
     *
     * @return - list of most likely match's contactID's
     */
    ArrayList<Contact> getTopContacts() {
        return this.topContact;
    }

    /**
     * Gets the confidence list for
     * a master contact
     *
     * @return - list of most likely match's level of confidence
     */
    ArrayList<Double> getTopConfidence() {
        return this.topConfidence;
    }

    /**
     * This method compares new matches with those already in
     * the list and checks if the new match should be added.
     *
     * @param contact    - new match's data
     * @param confidence - new match's level of confidence of similarity
     */
    void setMatch(Contact contact, double confidence) {

        boolean full = atCapacity();

        if (!full)
            addMatchToNotFull(contact, confidence);

        else if (checkMatch(confidence))
            addMatchToFull(contact, confidence);

    }

    /**
     * Checks if a match is greater than or equal to
     * the last (least likely) match in the lists.
     *
     * @param confidence - new match's level of confidence of similarity
     * @return
     */
    private boolean checkMatch(double confidence) {

        return confidence >= this.topConfidence.get(this.MAX_LOC);

    }

    /**
     * Removes a match from the lists at
     * the specified location
     *
     * @param index - location in lists to remove
     */
    private void removeMatch(int index) {
        this.topContact.remove(index);
        this.topConfidence.remove(index);
    }

    /**
     * Adds a match to the end of the lists.
     *
     * @param contact    - new match's data
     * @param confidence - new match's level of confidence of similarity
     */
    private void addMatch(Contact contact, double confidence) {
        this.topContact.add(contact);
        this.topConfidence.add(confidence);
    }

    /**
     * Adds a match to the lists at the specified location.
     *
     * @param index      - Location to add match
     * @param contact    - new match's data
     * @param confidence - new match's level of confidence of similarity
     */
    private void addMatch(int index, Contact contact, double confidence) {
        this.topContact.add(index, contact);
        this.topConfidence.add(index, confidence);
    }

    /**
     * Tests if the lists are empty.
     *
     * @return - True if lists are empty | False if not
     */
    private boolean isEmpty() {
        boolean empty = false;
        if (topConfidence.isEmpty() && topContact.isEmpty())
            empty = true;

        return empty;
    }

    /**
     * Tests if the lists are
     * at their max size.
     *
     * @return - True if at capacity | False if not
     */
    private boolean atCapacity() {

        return this.topConfidence.size() == this.MAX_MATCH_SIZE;

    }

    /**
     * Removes the last (least likely) match in the lists, then adds
     * the new match to the lists at the specified index.
     *
     * @param index      - location to add new match to
     * @param contact    - new match's data
     * @param confidence - new match's level of confidence of similarity
     */
    private void replaceMatch(int index, Contact contact, double confidence) {
        removeMatch(this.MAX_LOC);
        addMatch(index, contact, confidence);
    }

    /**
     * Logic to add a potential match to the lists when they're
     * at capacity.
     *
     * @param contact    - new match's data
     * @param confidence - new match's level of confidence of similarity
     */
    private void addMatchToFull(Contact contact, double confidence) {

        // loop through the matches with lowest confidence first
        for (int i = this.MAX_LOC; i >= 0; i--) {

            // if potential match's confidence is greater than
            // the current location
            if (confidence > topConfidence.get(i)) {

                // if potential match's confidence is greater than
                // the match with highest confidence (at location 0)
                if (i == 0) {
                    // remove least likely match and add
                    // new match to location 0
                    replaceMatch(i, contact, confidence);
                    break;
                }
            }
            // if potential match's confidence is equal to
            // a top match's confidence
            else if (confidence == topConfidence.get(i)) {

                // if last observation
                if (i == this.MAX_LOC) {
                    // replace last match
                    replaceMatch(i, contact, confidence);
                    break;
                    // if NOT last observation
                } else {
                    // remove last observation and
                    // add new match to location behind
                    // top match with equal confidence
                    replaceMatch(i + 1, contact, confidence);
                    break;
                }
            }
            // if potential match's confidence is less
            // than the current top match's location
            else if (confidence < topConfidence.get(i)) {
                // remove least likely top match and add
                // new match behind current match
                replaceMatch(i + 1, contact, confidence);
                break;
            }
        }
    }

    /**
     * Adds a match when the top matches list is not full.
     *
     * @param contact    - new match's data
     * @param confidence - new match's level of confidence of similarity
     */
    private void addMatchToNotFull(Contact contact, double confidence) {

        // if list is empty add match to the lists
        if (isEmpty()) {
            addMatch(contact, confidence);
        }
        // if list is not empty
        else {
            boolean lesserThan = true;
            for (int i = 0; i < topConfidence.size(); i++) {

                // if new match's confidence is greater than the
                // top match at the current location
                if (confidence > topConfidence.get(i)) {

                    // add match at that location
                    addMatch(i, contact, confidence);
                    lesserThan = false;
                    break;
                }
            }
            // if new match's confidence is less than those
            // already in the list
            if (lesserThan) {
                // add match to the end of the list
                addMatch(contact, confidence);
            }
        }
    }

}
