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

import data.Contact;
import data.MasterContact;

import java.util.HashMap;
import java.util.Map;

class BlockMap {

    private Map<String, Group> groups;

    BlockMap() {

        this.groups = new HashMap<>();

    }

    @SuppressWarnings("Duplicates")
   void put(String key, MasterContact contact) {

        if (checkExists(key)) {

            groups.get(key).addContact(contact);

        } else {

            Group newGroup = new Group();
            newGroup.addContact(contact);
            groups.put(key, newGroup);

        }
    }

    @SuppressWarnings("Duplicates")
    void put(String key, Contact contact) {

        if (checkExists(key)) {

            groups.get(key).addContact(contact);

        } else {

            Group newGroup = new Group();
            newGroup.addContact(contact);
            groups.put(key, newGroup);

        }
    }

    private boolean checkExists(String key) {
        return groups.containsKey(key);
    }

    Map<String, Group> getGroups() {
        return groups;
    }
}
