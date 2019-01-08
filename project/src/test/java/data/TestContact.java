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
 * Developed by Gregory Smith & Axel Solano. Last modified 08/01/19 6:31 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package data;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestContact {

    private Contact contact;
    private String last;
    private String middle;
    private String first;
    private String phone;
    private String firm;
    private String office;
    private String email;
    private String address;
    private String city;
    private String state;
    private String zip1;
    private String zip2;
    private String country;
    private String CRDnum;
    private String contactID;

    public TestContact() {

        contact = new Contact();
        last = "Doe";
        middle = "Q";
        first = "John";
        phone = "(999) 999-9999";
        firm = "TestFirm";
        office = "TestOffice";
        email = "TestEmail@test.com";
        address = "TestAddress";
        city = "TestCity";
        state = "MI";
        zip1 = "TestZip1";
        zip2 = "TestZip2";
        country = "US";
        CRDnum = "123456";
        contactID = "1111111";

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
        contact.setZip1(zip1);
        contact.setZip2(zip2);
        contact.setCountryID(country);
        contact.setCRDNumber(CRDnum);
        contact.setContactID(contactID);

    }

    @Test
    public void TestData() {
        assertTrue(contact.getLastName().equals(this.last));
        assertTrue(contact.getMiddleName().equals(this.middle));
        assertTrue(contact.getFirstName().equals(this.first));
        assertTrue(contact.getBusinessPhone().equals(this.phone));
        assertTrue(contact.getFirmName().equals(this.firm));
        assertTrue(contact.getOfficeName().equals(this.office));
        assertTrue(contact.getEmail().equals(this.email));
        assertTrue(contact.getAddress().equals(this.address));
        assertTrue(contact.getCity().equals(this.city));
        assertTrue(contact.getStateProvince().equals(this.state));
        assertTrue(contact.getZip1().equals(this.zip1));
        assertTrue(contact.getZip2().equals(this.zip2));
        assertTrue(contact.getCountryID().equals(this.country));
        assertTrue(contact.getCRDNumber().equals(this.CRDnum));
        assertTrue(contact.getContactID().equals(this.contactID));

    }

}
