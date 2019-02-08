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
 * Developed by Gregory Smith & Axel Solano. Last modified 1/22/19 10:38 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package indexing;

import dataholder.Contact;
import dataholder.MasterContact;

import java.util.HashMap;
import java.util.Map;

/**
 * BlockMap holds our map for Indexing the dataholder.
 */
class BlockMap {

    private Map<String, Group> groups;

    /**
     * Initializes Map field to a new HashMap.
     */
    BlockMap() {

        this.groups = new HashMap<>();

    }

    /**
     * Adds a MasterContact to a Group object.
     *
     * @param key     Phonetically encoded dataholder.
     * @param contact MasterContact object.
     */
    void putMaster(String key, MasterContact contact) {

        if (checkExists(key)) {

            groups.get(key).addMasterContact(contact);

        } else {

            Group newGroup = new Group();
            newGroup.addMasterContact(contact);
            groups.put(key, newGroup);

        }
    }

    /**
     * Adds a matching Contact to a Group object.
     *
     * @param key     Phonetically encoded dataholder.
     * @param contact Contact object.
     */
    void putContact(String key, Contact contact) {

        if (checkExists(key)) {

            groups.get(key).addContact(contact);

        } else {

            Group newGroup = new Group();
            newGroup.addContact(contact);
            groups.put(key, newGroup);

        }
    }

    /**
     * Checks if a key exists in the Map.
     *
     * @param key Phonetically encoded dataholder.
     * @return True or false.
     */
    private boolean checkExists(String key) {
        return groups.containsKey(key);
    }

    /**
     * Returns the Map field.
     *
     * @return Map containing phonetically encoded dataholder as keys and Group objects as values.
     */
    Map<String, Group> getGroups() {
        return groups;
    }
}
