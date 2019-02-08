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
 * Developed by Gregory Smith & Axel Solano. Last modified 1/22/19 10:29 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package indexing;

import dataholder.Contact;
import dataholder.MasterContact;

import java.util.ArrayList;

/**
 * Data holder for similar records.
 */
public class Group {

    private ArrayList<MasterContact> masterContacts;
    private ArrayList<Contact> matchContacts;

    /**
     * Default constructor to initialize our ArrayList fields.
     */
    public Group() {

        masterContacts = new ArrayList<>();
        matchContacts = new ArrayList<>();

    }

    /**
     * Adds a matching contact to its respective list.
     *
     * @param contact Contact object.
     */
    public void addContact(Contact contact) {
        this.matchContacts.add(contact);
    }

    /**
     * Adds a master contact to its respective list.
     *
     * @param contact MasterContact object.
     */
    public void addMasterContact(MasterContact contact) {
        this.masterContacts.add(contact);
    }

    /**
     * Gets the list of MasterContacts in the group.
     *
     * @return ArrayList of MasterContact objects.
     */
    public ArrayList<MasterContact> getMasterContacts() {
        return masterContacts;
    }

    /**
     * Gets the list of Contacts in the group.
     *
     * @return ArrayList of Contact objects.
     */
    public ArrayList<Contact> getMatchContacts() {
        return matchContacts;
    }

}
