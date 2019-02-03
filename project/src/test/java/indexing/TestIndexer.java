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

    @Test
    public void testGetContactGroups(){
        Indexer indexer = new Indexer();
        assertNotNull(indexer.getContactGroups());
    }

    @Test
    public void testGetPartnerships(){
        Indexer indexer = new Indexer();
        assertNotNull(indexer.getPartnerships());
    }

    @Test
    public void testGetAllMasterContacts(){
        Indexer indexer = new Indexer();
        assertNotNull(indexer.getAllMasterContacts());
    }

    @Test
    public void testGetMasterSize(){
        Indexer indexer = new Indexer();
        assertEquals(0,indexer.getMasterSize());
        indexer.getAllMasterContacts().add(0,new MasterContact());
        assertEquals(1,indexer.getMasterSize());
    }

    @Test
    public void testGetMatchSize(){
        Indexer indexer = new Indexer();
        assertEquals(0,indexer.getMatchSize());
        indexer.getAllMatchContacts().add(0, new Contact());
        assertEquals(1,indexer.getMatchSize());
    }

    @Test
    public void testIndexForMasterContact(){
        MasterContact masterContact1 = new MasterContact();
        masterContact1.setLastName("Illesca");

        Indexer indexer1 = new Indexer();
        indexer1.indexForMasterContact(masterContact1, "metaphone");
        String resultForContactGroups = indexer1.getContactGroups().get(0).getMasterContacts().get(0).getLastName();
        assertEquals("Illesca", resultForContactGroups );
        assertEquals(masterContact1, indexer1.getAllMasterContacts().get(0));


        MasterContact masterContact2 = new MasterContact();
        masterContact2.setLastName("Illesca/Messi");
        masterContact2.setZip("123");

        Indexer indexer2 = new Indexer();
        indexer2.indexForMasterContact(masterContact2, "metaphone");
        String resultForPartnerships = indexer2.getPartnerships().get(0).getMasterContacts().get(0).getZip();
        assertEquals("123", resultForPartnerships );
        assertEquals(masterContact2, indexer2.getAllMasterContacts().get(0));

    }

    @Test
    public void testIndexForContact(){
        Contact contact1 = new Contact();
        contact1.setLastName("Illesca");

        Indexer indexer1 = new Indexer();
        indexer1.indexForContact(contact1, "metaphone");
        String resultForContactGroups = indexer1.getContactGroups().get(0).getMatchContacts().get(0).getLastName();
        assertEquals("Illesca", resultForContactGroups );
        assertEquals(contact1, indexer1.getAllMatchContacts().get(0));


        Contact contact2 = new MasterContact();
        contact2.setLastName("Illesca/Messi");
        contact2.setZip("123");

        Indexer indexer2 = new Indexer();
        indexer2.indexForContact(contact2, "metaphone");
        String resultForPartnerships = indexer2.getPartnerships().get(0).getMatchContacts().get(0).getZip();
        assertEquals("123", resultForPartnerships );
        assertEquals(contact2, indexer2.getAllMatchContacts().get(0));


    }

    @Test
    public void tesTCheckPartnership(){
        String name = "Alex/Grant/Jack";
        assertTrue(name.contains("/"));

    }

    @Test
    public void testEncode(){
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

    public void print(String str){
        System.out.println(str);
    }

}
