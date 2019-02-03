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
 * Developed by Gregory Smith & Axel Solano. Last modified 1/28/19 3:08 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package indexing;

import data.Contact;
import data.MasterContact;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestGroup {

    @Test
    public void testAddContact(){
        Group group = new Group();
        Contact contact = new Contact();
        group.addContact(contact);
        assertEquals(contact,group.getMatchContacts().get(0));

    }

    @Test
    public void testAddMasterContact(){
        Group group = new Group();
        MasterContact masterContact = new MasterContact();
        group.addMasterContact(masterContact);
        assertEquals(masterContact,group.getMasterContacts().get(0));

    }

    @Test
    public void testGetMasterContacts(){
        Group group = new Group();
        assertNotNull(group.getMasterContacts());

    }

    @Test
    public void testGetMatchContactss(){
        Group group = new Group();
        assertNotNull(group.getMatchContacts());

    }



}
