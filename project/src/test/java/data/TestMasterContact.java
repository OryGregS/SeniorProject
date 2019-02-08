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
 * Developed by Gregory Smith & Axel Solano. Last modified 1/28/19 1:44 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package data;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestMasterContact {

    private MasterContact master;
    private Contact contact;

    public TestMasterContact() {
        master = new MasterContact();
        contact = new Contact();
    }

    @Test
    public void testIdentifiedMatches() {

        assertEquals(0, master.getIdentifiedMatchCount());

        for (int i = 0; i < 1000; i++) {
            master.addIdentifiedMatch();
        }

        assertEquals(1000, master.getIdentifiedMatchCount());

    }

    @Test
    public void testUnknownMatches() {

        assertEquals(0, master.getUnknownMatches());

        for (int i = 0; i < 1241; i++) {
            master.addUnknownMatch();
        }

        assertEquals(1241, master.getUnknownMatches());

    }

    @Test
    public void testKnownMatches() {

        assertEquals(0, master.getKnownMatches());

        for (int i = 0; i < 1241; i++) {
            master.addKnownMatch();
        }

        assertEquals(1241, master.getKnownMatches());

    }

    @Test
    public void testArrayListCapacity() {
        ArrayList<Integer> list1 = new ArrayList<>(11);
        assertEquals(0, list1.size());

        list1.add(7);
        assertEquals(1, list1.size());

        list1.remove(0);
        assertEquals(0, list1.size());


    }

    public void initContact() {
        contact = new Contact();
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
    }

    @Test
    public void testSetMatch() {
        TopMatches topMatches = new TopMatches(3);
        initContact();
        assertTrue(master.setMatch(contact, 77));
        assertFalse(master.setMatch(null, 101));
        assertFalse(master.setMatch(null, -2));

    }

    @Test
    public void testGetTopContacts() {
        TopMatches topMatches = new TopMatches(3);
        assertNotNull(topMatches.getTopContacts());
    }

    @Test
    public void testGetTopConfidence() {
        TopMatches topMatches = new TopMatches(3);
        assertNotNull(topMatches.getTopConfidence());

    }


}
