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
import org.apache.commons.codec.language.*;

import java.util.ArrayList;

public class Indexer {

    private BlockMap contactGroups;
    private BlockMap partnerships;
    private ArrayList<MasterContact> allMasterContacts;
    private ArrayList<Contact> allMatchContacts;

    public Indexer() {
        contactGroups = new BlockMap();
        partnerships = new BlockMap();
        allMasterContacts = new ArrayList<>();
        allMatchContacts = new ArrayList<>();
    }

    public ArrayList<Group> getContactGroups() {
        return new ArrayList<>(contactGroups.getGroups().values());
    }

    public ArrayList<Group> getPartnerships() {
        return new ArrayList<>(partnerships.getGroups().values());
    }

    public ArrayList<MasterContact> getAllMasterContacts() {
        return this.allMasterContacts;
    }

    /*public ArrayList<Contact> getAllMatchContacts() {
        return this.allMatchContacts;
    }*/

    public int getMasterSize() {
        return this.allMasterContacts.size();
    }

    public int getMatchSize() {
        return this.allMatchContacts.size();
    }


    public void index(MasterContact contact, String method) {

        String key;
        String lastName = contact.getLastName();

        if (checkPartnership(lastName)) {

            key = contact.getZip();
            partnerships.putForMasterContact(key, contact);

        } else {

            key = encode(lastName, method);
            contactGroups.putForMasterContact(key, contact);

        }

        allMasterContacts.add(contact);

    }


    public void index(Contact contact, String method) {

        String key;
        String lastName = contact.getLastName();

        if (checkPartnership(lastName)) {

            key = contact.getZip();
            partnerships.putForContacts(key, contact);

        } else {

            key = encode(lastName, method);
            contactGroups.putForContacts(key, contact);

        }

        allMatchContacts.add(contact);

    }

    private boolean checkPartnership(String name) {
        return name.contains("/");
    }

    private String encode(String data, String encodeMethod) {

        encodeMethod = encodeMethod.toLowerCase();

        switch (encodeMethod) {

            case "nysiis":
                Nysiis nysiis = new Nysiis();
                return nysiis.nysiis(data);

            case "doublemetaphone":
                DoubleMetaphone dmp = new DoubleMetaphone();
                return dmp.doubleMetaphone(data);

            case "metaphone":
                Metaphone mp = new Metaphone();
                return mp.metaphone(data);

            case "soundex":
                Soundex soundex = new Soundex();
                return soundex.soundex(data);

            case "rsoundex":
                RefinedSoundex rsoundex = new RefinedSoundex();
                return rsoundex.encode(data);

            case "dmsoundex":
                DaitchMokotoffSoundex dmsoundex = new DaitchMokotoffSoundex();
                return dmsoundex.encode(data);

            default:
                return data;

        }
    }

}
