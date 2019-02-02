

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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestTopMatches {

    public void initTopMatchesClass(){
        TopMatches topMatches = new TopMatches();
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

    public Contact newContact(){
        Contact contact = new Contact();
        String last = "Doe";
        String middle = "Q";
        String first = "John";
        String phone = "(999) 999-9999";
        String firm = "TestFirm";
        String office = "TestOffice";
        String email = "TestEmail@test.com";
        String address = "TestAddress";
        String city = "TestCity";
        String state = "MI";
        String zip = "TestZip";
        String country = "US";
        String CRDnum = "123456";
        String contactID = "1111111";

        contact.setLastName(last);
        contact.setMiddleName(middle);
        contact.setFirstName(first);
        contact.setBusinessPhone(phone);
        contact.setFirmName(firm);
        contact.setOfficeName(office);
        contact.setEmail(email);
        contact.setAddress(address);
        contact.setCity(city);
        contact.setStateProvince(state);
        contact.setZip(zip);
        contact.setCountryID(country);
        contact.setCRDNumber(CRDnum);
        contact.setContactID(contactID);

        return contact;
    }

    @Test
    public void testAddMatchToNotFull(){

    }

    @Test
    public void testAddMatch(){

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
