

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
 * Developed by Gregory Smith & Axel Solano. Last modified 1/28/19 1:27 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package data;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class TestTopMatches {


    public void initTopMatchesClass(){
        TopMatches topMatches = new TopMatches();
    }

    @Test
    public void testGetTopContacts(){
        TopMatches topMatches1 = new TopMatches();
        assertTrue(topMatches1.getTopContacts() != null);
        TopMatches topMatches2 = new TopMatches(10);
        assertTrue(topMatches2.getTopContacts() != null);
    }

    @Test
    public void testGetTopConfidence(){
        TopMatches topMatches1 = new TopMatches();
        assertTrue(topMatches1.getTopConfidence() != null);
        TopMatches topMatches2 = new TopMatches(10);
        assertTrue(topMatches2.getTopConfidence() != null);
    }

    @Test
    public void testRemoveMatch(){
        TopMatches topMatches1 = new TopMatches();
        Contact contact1 = new Contact();
        Contact contact2 = new Contact();
        Contact contact3 = new Contact();
        int index = 1;

        ArrayList<Contact> contacts = topMatches1.getTopContacts();
        contacts.add(0, contact1);
        contacts.add(1, contact2);
        contacts.add(2, contact3);

        assertEquals(3, contacts.size());
        assertEquals(contact2, contacts.get(index));
        contacts.remove(index);
        assertEquals(2, contacts.size());
        assertNotEquals(contact1,contacts.get(index));// next index check
        assertEquals(contact3,contacts.get(index));

        double c1  = 71;
        double c2  = 81;
        double c3  = 91;
        ArrayList<Double> confidences = topMatches1.getTopConfidence();
        confidences.add(0,c1);
        confidences.add(1,c2);
        confidences.add(2,c3);

        assertEquals(3, confidences.size());
        assertEquals(c2,confidences.get(index),0.0000001);
        confidences.remove(index);
        assertEquals(2, confidences.size());
        assertNotEquals(c2,confidences.get(index));
        assertEquals(c3,confidences.get(index),0.0000001);

    }

    @Test
    public void testAddMatch(){
        TopMatches topMatches1 = new TopMatches();
        Contact contact1 = new Contact();
        Contact contact2 = new Contact();
        Contact contact3 = new Contact();

        ArrayList<Contact> contacts = topMatches1.getTopContacts();
        contacts.add(0, contact1);
        contacts.add(1, contact2);
        contacts.add(2, contact3);

        assertEquals(contact1, contacts.get(0));
        assertEquals(contact2, contacts.get(1));
        assertEquals(contact3,contacts.get(2));

        double c1  = 77;
        double c2  = 87;
        double c3  = 97;
        ArrayList<Double> confidences = topMatches1.getTopConfidence();
        confidences.add(0,c1);
        confidences.add(1,c2);
        confidences.add(2,c3);

        assertEquals(c1,confidences.get(0),0.0000001);
        assertEquals(c2,confidences.get(1),0.0000001);
        assertEquals(c3,confidences.get(2),0.0000001);

    }

    @Test
    public void testIsEmpty(){
        TopMatches topMatches1 = new TopMatches();
        ArrayList<Contact> contacts = topMatches1.getTopContacts();
        ArrayList<Double> confidences = topMatches1.getTopConfidence();

        assertTrue(contacts.isEmpty() && contacts.isEmpty());

        contacts.add(new Contact());
        confidences.add(77.0);
        assertTrue(!contacts.isEmpty() || !confidences.isEmpty());

        contacts.remove(0);
        assertTrue(!contacts.isEmpty() || !confidences.isEmpty());

        confidences.remove(0);
        assertTrue(contacts.isEmpty() && confidences.isEmpty());

    }

//    private void replaceMatch(int index, Contact contact, double confidence) {
//        removeMatch(this.MAX_LOC);
//        addMatch(index, contact, confidence);
//    }
    @Test
    public void testReplaceMatch(){
        TopMatches topMatches1 = new TopMatches(3);
        Contact contact1 = new Contact();
        Contact contact2 = new Contact();
        Contact contact3 = new Contact();


        ArrayList<Contact> contacts = topMatches1.getTopContacts();
        contacts.add(0, contact1);
        contacts.add(1, contact2);
        contacts.add(2, contact3);

        double c1  = 91;
        double c2  = 87;
        double c3  = 72;
        ArrayList<Double> confidences = topMatches1.getTopConfidence();
        confidences.add(0,c1);
        confidences.add(1,c2);
        confidences.add(2,c3);

//        System.out.println(confidences.get(0));
//        System.out.println(confidences.get(1));
//        System.out.println(confidences.get(2));
//        System.out.println();

        int maxLoc = 3 - 1;
        contacts.remove(maxLoc);
        confidences.remove(maxLoc);

        assertEquals(2, contacts.size());
        assertEquals(2, confidences.size());

        Contact contactNew1 = new Contact();
        double cNew1 = 95;
        contacts.add(0,contactNew1);
        confidences.add(0, cNew1);

        assertEquals(3, contacts.size());
        assertEquals(3, confidences.size());

//        System.out.println(confidences.get(0));
//        System.out.println(confidences.get(1));
//        System.out.println(confidences.get(2));


    }



    @Test
    public void testSetMatch(){

    }

    @Test
    public void testAtCapacity(){
        int num = 25;
        TopMatches topMatches = new TopMatches(num);
        int i;
        for ( i = 0; i < num; i++) {
            topMatches.getTopConfidence().add(77.0);
        }
        assertEquals(topMatches.getTopConfidence().size(), num);

    }

    public ArrayList<Contact> newContact(){
        Contact contact1 = new Contact();
        contact1.setLastName("Hilkens");
        contact1.setMiddleName("Will");
        contact1.setFirstName("Troy");
        contact1.setBusinessPhone("678 667 6667");
        contact1.setFirmName("Firm One");
        contact1.setOfficeName("Office 1");
        contact1.setEmail("troy.hilkens@gmail.com");
        contact1.setAddress("77 Frat Ave");
        contact1.setCity("Kalamazoo");
        contact1.setStateProvince("Michigan");
        contact1.setZip("13456");
        contact1.setCountryID("USA");
        contact1.setCRDNumber("1234567");
        contact1.setContactID("1");

        Contact contact2 = new Contact();
        contact2.setLastName("Hilkens");
        contact2.setMiddleName("Willian");
        contact2.setFirstName("Tyler");
        contact2.setBusinessPhone("2678 667 6666");
        contact2.setFirmName("Firm Two");
        contact2.setOfficeName("Office 2");
        contact2.setEmail("tyler.hilkens@gmail.com");
        contact2.setAddress("77 Frat Ave");
        contact2.setCity("Kalamazoo");
        contact2.setStateProvince("Michigan");
        contact2.setZip("13456");
        contact2.setCountryID("USA");
        contact2.setCRDNumber("1234568");
        contact2.setContactID("2");

        Contact contact3 = new Contact();
        contact3.setLastName("Robert");
        contact3.setMiddleName("John");
        contact3.setFirstName("Downey");
        contact3.setBusinessPhone("2678 667 7777");
        contact3.setFirmName("Firm 3");
        contact3.setOfficeName("Office 3");
        contact3.setEmail("robert.downey@gmail.com");
        contact3.setAddress("767 Spring Ave");
        contact3.setCity("Jackson");
        contact3.setStateProvince("Michigan");
        contact3.setZip("7878");
        contact3.setCountryID("USA");
        contact3.setCRDNumber("1234589");
        contact3.setContactID("3");

        Contact contact4 = new Contact();
        contact4.setLastName("Tom");
        contact4.setMiddleName("Stanley");
        contact4.setFirstName("Holland");
        contact4.setBusinessPhone("2678 111 7777");
        contact4.setFirmName("Firm 4");
        contact4.setOfficeName("Office 4");
        contact4.setEmail("tom.holland@gmail.com");
        contact4.setAddress("767 Winter Ave");
        contact4.setCity("Kinston");
        contact4.setStateProvince("Michigan");
        contact4.setZip("787899");
        contact4.setCountryID("USA");
        contact4.setCRDNumber("1234123");
        contact4.setContactID("4");

        Contact contact5 = new Contact();
        contact5.setLastName("Thomas");
        contact5.setMiddleName("Stanley");
        contact5.setFirstName("Holland");
        contact5.setBusinessPhone("2678 111 7777");
        contact5.setFirmName("Firm 4");
        contact5.setOfficeName("Office 4");
        contact5.setEmail("tom.holland@gmail.com");
        contact5.setAddress("767 Winter Ave");
        contact5.setCity("Kinston");
        contact5.setStateProvince("Michigan");
        contact5.setZip("787899");
        contact5.setCountryID("USA");
        contact5.setCRDNumber("1234123");
        contact5.setContactID("5");

        Contact contact6 = new Contact();
        contact6.setLastName("Robert");
        contact6.setMiddleName("");
        contact6.setFirstName("Downey Jr.");
        contact6.setBusinessPhone("2678 667 7777");
        contact6.setFirmName("Firm 4");
        contact6.setOfficeName("Office 4");
        contact6.setEmail("robert.downey.jr@gmail.com");
        contact6.setAddress("767 Spring Ave");
        contact6.setCity("Jackson");
        contact6.setStateProvince("Michigan");
        contact6.setZip("78789");
        contact6.setCountryID("USA");
        contact6.setCRDNumber("1234581");
        contact6.setContactID("6");


        ArrayList<Contact> listOfContacts = new ArrayList<>();
        listOfContacts.add(contact1);
        listOfContacts.add(contact2);
        listOfContacts.add(contact3);
        listOfContacts.add(contact4);
        listOfContacts.add(contact5);

        return listOfContacts;
    }

    @Test
    public void testAddMatchToNotFull(){
        Contact contact = newContact().get(0);
        TopMatches topMatches = new TopMatches();
        // Check if list is empty and being added first
        topMatches.setMatch(contact,77);
        assertEquals(topMatches.getTopContacts().size(),1);
        assertEquals(topMatches.getTopConfidence().size(),1);


    }



//    void setMatch(Contact contact, double confidence) {
//
//        boolean full = atCapacity();
//
//        if (!full)
//            addMatchToNotFull(contact, confidence);
//
//        else if (checkMatch(confidence))
//            addMatchToFull(contact, confidence);
//
//    }

    @Test
    public void isEmpty(){
        int num = 25;
        TopMatches topMatches = new TopMatches(num);
        assertTrue(topMatches.getTopConfidence().isEmpty());
        assertTrue(topMatches.getTopContacts().isEmpty());
    }






}
