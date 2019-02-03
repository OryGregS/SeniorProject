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

import data.Contact;
import data.MasterContact;

import java.util.ArrayList;

public class Group {

    private ArrayList<MasterContact> masterContacts;
    private ArrayList<Contact> matchContacts;

    public Group() {

        masterContacts = new ArrayList<>();
        matchContacts = new ArrayList<>();

    }

    public void addContact(Contact contact) {
        this.matchContacts.add(contact);
    }

    public void addMasterContact(MasterContact contact) {
        this.masterContacts.add(contact);
    }

    public ArrayList<MasterContact> getMasterContacts() {
        return masterContacts;
    }
// make it private later
    public ArrayList<Contact> getMatchContacts() {
        return matchContacts;
    }

    public void printGroup() {

        int i;
        int size;

        size = masterContacts.size();

        for (i = 0; i < size; i++) {
            masterContacts.get(i).printAll();
        }

        size = matchContacts.size();

        for (i = 0; i < size; i++) {
            matchContacts.get(i).printAll();
        }


    }


}
