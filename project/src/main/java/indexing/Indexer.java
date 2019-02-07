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

/**
 * Indexer phonetically encodes data, and pass the encoded data as a key to BlockMap.
 */
public class Indexer {

    private String encodeMethod;
    private BlockMap individuals;
    private BlockMap partnerships;
    private ArrayList<MasterContact> allMasterContacts;
    private ArrayList<Contact> allMatchContacts;

    /**
     * Constructor to get our method of indexing and initialize variables.
     *
     * @param encodeMethod
     *          Method to use for phonetic encoding. Acceptable values
     *          are (case-insensitive): nysiis, soundex, rsoundex, dmsoundex,
     *          metaphone, and doublemetaphone. This value is set in ./config/DataMatching.properties
     */
    public Indexer(String encodeMethod) {

        this.encodeMethod = encodeMethod;
        this.individuals = new BlockMap();
        this.partnerships = new BlockMap();
        this.allMasterContacts = new ArrayList<>();
        this.allMatchContacts = new ArrayList<>();

    }

    /**
     * Gets all groups of contacts (individual data).
     *
     * @return
     *          ArrayList of Group objects containing individual contact data.
     */
    public ArrayList<Group> getIndividuals() {
        return new ArrayList<>(individuals.getGroups().values());
    }

    /**
     * Gets all groups of partnerships (grouped contacts).
     *
     * @return
     *          ArrayList of Group objects containing partnership contact data.
     */
    public ArrayList<Group> getPartnerships() {
        return new ArrayList<>(partnerships.getGroups().values());
    }

    /**
     * Gets all master contacts.
     *
     * @return
     *          ArrayList of MasterContact objects.
     */
    public ArrayList<MasterContact> getAllMasterContacts() {
        return this.allMasterContacts;
    }

    /**
     * Gets all matching contacts.
     *
     * @return
     *          ArrayList of Contact objects.
     */
    public ArrayList<Contact> getAllMatchContacts() {
        return this.allMatchContacts;
    }

    /**
     * Gets size of allMasterContacts list.
     *
     * @return
     *          Number of MasterContacts in the list.
     */
    public int getMasterSize() {
        return this.allMasterContacts.size();
    }

    /**
     * Gets size of allMatchContacts list.
     *
     * @return
     *          Number of Contacts in the list.
     */
    public int getMatchSize() {
        return this.allMatchContacts.size();
    }

    /**
     * Phonetically encodes MasterContact data and adds it to a Group object.
     *
     * @param masterContact
     *          MasterContact object.
     */
    public void indexMaster(MasterContact masterContact) {

        String key;
        String lastName = masterContact.getLastName();

        if (checkPartnership(lastName)) {

            key = masterContact.getZip();
            partnerships.putMaster(key, masterContact);

        } else {

            key = encode(lastName);
            individuals.putMaster(key, masterContact);

        }

        allMasterContacts.add(masterContact);

    }

    /**
     * Phonetically encodes Contact data and adds it to a Group object.
     *
     * @param contact
     *          Contact object.
     */
    public void indexContact(Contact contact) {

        String key;
        String lastName = contact.getLastName();

        if (checkPartnership(lastName)) {

            key = contact.getZip();
            partnerships.putContact(key, contact);

        } else {

            key = encode(lastName);
            individuals.putContact(key, contact);

        }

        allMatchContacts.add(contact);

    }

    /**
     * Checks if a MasterContact or Contact is a partnership.
     *
     * @param name
     *          LastName of contact.
     * @return
     *          True if partnership. False if not.
     */
    private boolean checkPartnership(String name) {
        return name.contains("/");
    }

    /**
     * Ensures encoding method is valid. Invalid entries default to Metaphone.
     *
     * @param data
     *          Data to encode as key for Groups.
     * @return
     *          Phonetically encoded string.
     */
    private String encode(String data) {

        String encodeMethod = this.encodeMethod.toLowerCase();

        // setMaxCodeLen( number ); so output is longer and obtain more information
        // to be test later

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
                // the output is letters at the beginning and numbers

            case "rsoundex":
                RefinedSoundex rsoundex = new RefinedSoundex();
                return rsoundex.encode(data);
                // the output is letters at the beginning and numbers

            case "dmsoundex":
                DaitchMokotoffSoundex dmsoundex = new DaitchMokotoffSoundex();
                return dmsoundex.encode(data);
                // the output is numbers

            default:
                System.out.println("\nEncoding method not recognized. Defaulting to Metaphone.\n");
                Metaphone mpn = new Metaphone();
                return mpn.metaphone(data);

        }
    }

}
