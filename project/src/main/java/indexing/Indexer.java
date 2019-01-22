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
 * Developed by Gregory Smith & Axel Solano. Last modified 1/22/19 11:03 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package indexing;

import data.Contact;
import data.MasterContact;
import org.apache.commons.codec.language.DoubleMetaphone;

import java.util.ArrayList;

public class Indexer {

    private BlockMap contactGroups;
    private BlockMap partnerships;
    private DoubleMetaphone encoder;
    private ArrayList<MasterContact> allMasterContacts;
    private ArrayList<Contact> allMatchContacts;

    public Indexer() {
        contactGroups = new BlockMap();
        partnerships = new BlockMap();
        encoder = new DoubleMetaphone();
        allMasterContacts = new ArrayList<>();
        allMatchContacts = new ArrayList<>();
    }

    public ArrayList<Group> getContactGroups() {
        return new ArrayList<>( contactGroups.getGroups().values() );
    }

    public ArrayList<Group> getPartnerships() {
        return new ArrayList<>( partnerships.getGroups().values() );
    }

    public ArrayList<MasterContact> getAllMasterContacts() {
        return this.allMasterContacts;
    }

    public ArrayList<Contact> getAllMatchContacts() {
        return this.allMatchContacts;
    }

    @SuppressWarnings("Duplicates")
    public void index(MasterContact contact) {

        String key;

        if ( checkPartnership(contact.getLastName()) ) {

            key = contact.getZip();
            partnerships.put(key, contact);

        } else {

            key = encode(contact.getLastName());
            contactGroups.put(key, contact);

        }
        allMasterContacts.add(contact);
    }

    @SuppressWarnings("Duplicates")
    public void index(Contact contact) {

        String key;

        if ( checkPartnership(contact.getLastName()) ) {

            key = contact.getZip();
            partnerships.put(key, contact);

        } else {

            key = encode(contact.getLastName());
            contactGroups.put(key, contact);

        }

        allMatchContacts.add(contact);

    }

    private boolean checkPartnership(String name) {
        return name.contains("/");
    }

    public String encode(String data) {
        return encoder.doubleMetaphone(data);

    }

    public String encodeAlt(String data) {
        return encoder.doubleMetaphone(data, true);
    }


}
