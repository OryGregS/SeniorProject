package wmu.datamatching;

import java.util.*;

public class MasterContact extends Contact {


    private int MAX_MATCH_SIZE;
    private int MAX_LOC;
    private ArrayList<Integer> topConfidence;
    private ArrayList<Contact> topContact;

    /**
     * Default constructor to initialize fields
     * based on their default values
     */
    public MasterContact() {
        this.MAX_MATCH_SIZE = 5;
        this.MAX_LOC = this.MAX_MATCH_SIZE - 1;
        this.topConfidence = new ArrayList<>(MAX_MATCH_SIZE);
        this.topContact = new ArrayList<>(MAX_MATCH_SIZE);
    }

    /**
     * Constructor to initialize fields based
     * on a specified max list size
     * @param size
     */
    public MasterContact(int size) {
        this.MAX_MATCH_SIZE = size;
        this.MAX_LOC = this.MAX_MATCH_SIZE - 1;
        this.topConfidence = new ArrayList<>(MAX_MATCH_SIZE);
        this.topContact = new ArrayList<>(MAX_MATCH_SIZE);
    }

    /**
     * This method compares new matches with those already in
     * the list and checks if the new match should be added.
     * @param contactID - new match's contactID
     * @param confidence - new match's level of confidence of similarity
     */
    public void setMatch(Contact contactID, int confidence) {

        boolean full = atCapacity();

        if (!full)
            addMatchToNotFull(contactID, confidence);

        else if (full && checkMatch(confidence))
            addMatchToFull(contactID, confidence);

    }

    /**
     * Gets the contact list for
     * a master contact
     * @return - list of most likely match's contactID's
     */
    public ArrayList<Contact> getTopContacts() {
        return this.topContact;
    }

    /**
     * Gets the confidence list for
     * a master contact
     * @return - list of most likely match's level of confidence
     */
    public ArrayList<Integer> getTopConfidence() {
        return this.topConfidence;
    }

    /**
     * Resets the maximum list sizes to
     * the specified size
     * @param size - new maximum list size
     */
    public void set_MAX_MATCH_SIZE(int size) {
        this.MAX_MATCH_SIZE = size;
        this.MAX_LOC = size - 1;
        this.topConfidence = new ArrayList<>(size);
        this.topContact = new ArrayList<>(size);

    }

    /**
     * Gets the specified maximum list size
     * @return - maximum list size
     */
    public int get_MAX_MATCH_SIZE() {
        return this.MAX_MATCH_SIZE;
    }

    private void printDiv() {
        System.out.print("-----------------------------------" +
                "----------------------------------------------" +
                "----------------------------------------------");
        System.out.print("-----------------------------------" +
                "----------------------------------------------" +
                "----------------------------------------------");
        System.out.print("-----------------------------------" +
                "----------------------------------------------" +
                "----------------------------------------------");
        System.out.print("-----------------------------------" +
                "----------------------------------------------" +
                "----------------------------------------------");
        System.out.print("-----------------------------------" +
                "----------------------------------------------" +
                "----------------------------------------------");
        System.out.print("-----------------------------------" +
                "----------------------------------------------" +
                "----------------------------------------------\n");
    }

    /**
     * Prints the contactID and level of
     * confidence for a MasterContact.
     */
    public void printTop() {
        
        printDiv();
        System.out.print("MasterContact: \t");
        this.printAll();
        printDiv();
        for (int i = 0; i < topConfidence.size(); i++) {
            System.out.print("\nConfidence: " + topConfidence.get(i) + " | ");
            this.topContact.get(i).printAll();
        }
        System.out.println();
    }

    /**
     * Checks if a match is greater than or equal to
     * the last (least likely) match in the lists.
     * @param confidence - new match's level of confidence of similarity
     * @return
     */
    private boolean checkMatch(int confidence) {

        if (confidence >= this.topConfidence.get(this.MAX_LOC))
            return true;
        else
            return false;

    }

    /**
     * Removes a match from the lists at
     * the specified location
     * @param index - location in lists to remove
     */
    private void removeMatch(int index) {
        this.topContact.remove(index);
        this.topConfidence.remove(index);
    }

    /**
     * Adds a match to the end of the lists.
     * @param contact - new match's data
     * @param confidence - new match's level of confidence of similarity
     */
    private void addMatch(Contact contact, int confidence) {
        this.topContact.add(contact);
        this.topConfidence.add(confidence);
    }

    /**
     * Adds a match to the lists at the specified location.
     * @param index - Location to add match
     * @param contact - new match's data
     * @param confidence - new match's level of confidence of similarity
     */
    private void addMatch(int index, Contact contact, int confidence) {
        this.topContact.add(index, contact);
        this.topConfidence.add(index, confidence);
    }

    /**
     * Tests if the lists are empty.
     * @return - True if lists are empty | False if not
     */
    private boolean isEmpty() {
        boolean empty = false;
        if (topConfidence.isEmpty() && topContact.isEmpty())
            empty =  true;

        return empty;
    }

    /**
     * Tests if the lists are
     * at their max size.
     * @return - True if at capacity | False if not
     */
    private boolean atCapacity() {

        boolean atCap;

        if (this.topConfidence.size() == this.MAX_MATCH_SIZE)
            atCap = true;
        else
            atCap = false;

        return atCap;
    }

    /**
     * Removes the last (least likely) match in the lists, then adds
     * the new match to the lists at the specified index.
     * @param index - location to add new match to
     * @param contact - new match's data
     * @param confidence - new match's level of confidence of similarity
     */
    private void replaceMatch(int index, Contact contact, int confidence) {
        removeMatch(this.MAX_LOC);
        addMatch(index, contact, confidence);
    }

    /**
     * Logic to add a potential match to the lists when they're
     * at capacity.
     * @param contact - new match's data
     * @param confidence - new match's level of confidence of similarity
     */
    private void addMatchToFull(Contact contact, int confidence) {

        // loop through the matches with lowest confidence first
        for (int i = this.MAX_LOC; i >= 0 ; i--) {

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
     * @param contact - new match's data
     * @param confidence - new match's level of confidence of similarity
     */
    private void addMatchToNotFull(Contact contact, int confidence) {

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

