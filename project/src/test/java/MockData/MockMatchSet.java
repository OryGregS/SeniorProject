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
 * Developed by Gregory Smith & Axel Solano. Last modified 13/01/19 10:25 PM.
 * Copyright (c) 2019. All rights reserved.
 */

package MockData;

import dataholder.Contact;

import java.util.ArrayList;

public class MockMatchSet {

    public ArrayList<Contact> newMasterContact() {
        Contact contact1 = new Contact();
        contact1.setLastName("Hilkens");
        contact1.setMiddleName("W");
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

    public ArrayList<Contact> newContact() {
        Contact contact1 = new Contact();
        contact1.setLastName("Hilkens");
        contact1.setMiddleName("");
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
        contact2.setMiddleName("");
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
}
