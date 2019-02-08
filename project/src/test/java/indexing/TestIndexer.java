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
import org.apache.commons.codec.language.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestIndexer {

    private String encodeMethod = "metaphone";

    @Test
    public void testGetContactGroups() {
        Indexer indexer = new Indexer(encodeMethod);
        assertNotNull(indexer.getIndividuals());
    }

    @Test
    public void testGetPartnerships() {
        Indexer indexer = new Indexer(encodeMethod);
        assertNotNull(indexer.getPartnerships());
    }

    @Test
    public void testGetHouseAccounts() {
        Indexer indexer = new Indexer(encodeMethod);
        assertNotNull(indexer.getHouseAccounts());

    }

    @Test
    public void testGetAllMasterContacts() {
        Indexer indexer = new Indexer(encodeMethod);
        assertNotNull(indexer.getAllMasterContacts());
    }

    @Test
    public void testGetMasterSize() {
        Indexer indexer = new Indexer(encodeMethod);
        assertEquals(0, indexer.getMasterSize());
        indexer.getAllMasterContacts().add(0, new MasterContact());
        assertEquals(1, indexer.getMasterSize());
    }

    @Test
    public void testGetMatchSize() {
        Indexer indexer = new Indexer(encodeMethod);
        assertEquals(0, indexer.getMatchSize());
        indexer.getAllMatchContacts().add(0, new Contact());
        assertEquals(1, indexer.getMatchSize());
    }

    @Test
    public void testIndexForMasterContact() {
        MasterContact masterContact1 = new MasterContact();
        masterContact1.setLastName("Illesca");
        masterContact1.setFirstName("asdf");
        masterContact1.setZip("12345");

        Indexer indexer1 = new Indexer(encodeMethod);
        indexer1.indexMaster(masterContact1);
        String resultForContactGroups = indexer1.getIndividuals().get(0).getMasterContacts().get(0).getLastName();
        assertEquals("Illesca", resultForContactGroups);
        assertEquals(masterContact1, indexer1.getAllMasterContacts().get(0));


        MasterContact masterContact2 = new MasterContact();
        masterContact2.setLastName("Illesca/Messi");
        masterContact2.setFirstName("asdf");
        masterContact2.setZip("12345");

        Indexer indexer2 = new Indexer(encodeMethod);
        indexer2.indexMaster(masterContact2);
        String resultForPartnerships = indexer2.getPartnerships().get(0).getMasterContacts().get(0).getZip();
        assertEquals("12345", resultForPartnerships);
        assertEquals(masterContact2, indexer2.getAllMasterContacts().get(0));

    }

    @Test
    public void testIndexForContact() {
        Contact contact1 = new Contact();
        contact1.setLastName("Illesca");
        contact1.setFirstName("asdf");
        contact1.setZip("12345");

        Indexer indexer1 = new Indexer(encodeMethod);
        indexer1.indexContact(contact1);
        String resultForContactGroups = indexer1.getIndividuals().get(0).getMatchContacts().get(0).getLastName();
        assertEquals("Illesca", resultForContactGroups);
        assertEquals(contact1, indexer1.getAllMatchContacts().get(0));


        Contact contact2 = new MasterContact();
        contact2.setLastName("Illesca/Messi");
        contact2.setFirstName("asdf");
        contact2.setZip("12345");

        Indexer indexer2 = new Indexer(encodeMethod);
        indexer2.indexContact(contact2);
        String resultForPartnerships = indexer2.getPartnerships().get(0).getMatchContacts().get(0).getZip();
        assertEquals("12345", resultForPartnerships);
        assertEquals(contact2, indexer2.getAllMatchContacts().get(0));


    }

    @Test
    public void testCheckPartnership() {
        Indexer indexer = new Indexer(encodeMethod);
        String name = "Alex/Grant/Jack";
        assertTrue(indexer.checkPartnership(name));

    }

    @Test
    public void testCheckHouse() {
        Indexer indexer = new Indexer(encodeMethod);
        String firstName = "house";
        assertTrue(indexer.checkHouse(firstName));
    }

    @Test
    public void testEncode() {
        String data = "Illesca";
        Nysiis nysiis = new Nysiis();
        DoubleMetaphone dmp = new DoubleMetaphone();
        Metaphone mp = new Metaphone();
        Soundex soundex = new Soundex();
        RefinedSoundex rsoundex = new RefinedSoundex();
        DaitchMokotoffSoundex dmsoundex = new DaitchMokotoffSoundex();

        assertEquals("ILASC", nysiis.nysiis(data));
        assertEquals("ALSK", dmp.doubleMetaphone(data));
        assertEquals("ILSK", mp.metaphone(data));
        assertEquals("I420", soundex.soundex(data));
        assertEquals("I07030", rsoundex.encode(data));
        assertEquals("084000", dmsoundex.encode(data));

    }

    public void print(String str) {
        System.out.println(str);
    }

}
