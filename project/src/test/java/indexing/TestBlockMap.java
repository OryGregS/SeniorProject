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

public class TestBlockMap {

    @Test
    public void testPutForMasterContact() {
        BlockMap blockMap = new BlockMap();
        MasterContact masterContact1 = new MasterContact();
        MasterContact masterContact2 = new MasterContact();
        String key1 = "one";
        String key2 = "one";

        blockMap.putMaster(key1, masterContact1);
        assertTrue(blockMap.getGroups().containsKey(key1));

        blockMap.putMaster(key2, masterContact2);
        assertTrue(blockMap.getGroups().containsKey(key2));

        assertEquals(2, blockMap.getGroups().get(key1).getMasterContacts().size());
        assertEquals(2, blockMap.getGroups().get(key2).getMasterContacts().size());

    }

    @Test
    public void testPutForContacts() {
        BlockMap blockMap = new BlockMap();
        Contact contact1 = new Contact();
        Contact contact2 = new Contact();
        String key1 = "1";
        String key2 = "1";

        blockMap.putContact(key1, contact1);
        assertTrue(blockMap.getGroups().containsKey(key1));

        blockMap.putContact(key2, contact2);
        assertTrue(blockMap.getGroups().containsKey(key2));

        assertEquals(2, blockMap.getGroups().get(key1).getMatchContacts().size());
        assertEquals(2, blockMap.getGroups().get(key2).getMatchContacts().size());

    }

    @Test
    public void testCheckExists() {
        BlockMap blockMap = new BlockMap();
        Contact contact1 = new Contact();
        Contact contact2 = new Contact();
        MasterContact masterContact1 = new MasterContact();
        MasterContact masterContact2 = new MasterContact();
        blockMap.putContact("1", contact1);
        blockMap.putContact("2", contact2);
        blockMap.putContact("2", masterContact2);
        blockMap.putContact("1", masterContact1);

        assertTrue(blockMap.getGroups().containsKey("1"));
        assertTrue(blockMap.getGroups().containsKey("2"));
        assertTrue(!blockMap.getGroups().containsKey("7"));

    }

    @Test
    public void testGetGroups() {
        BlockMap blockMap = new BlockMap();
        assertNotNull(blockMap.getGroups());

    }
}
