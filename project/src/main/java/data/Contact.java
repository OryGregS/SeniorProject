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
 * Developed by Gregory Smith & Axel Solano. Last modified 08/01/19 5:59 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package data;

import java.util.ArrayList;

public class Contact {

    private String LastName;
    private String MiddleName;
    private String FirstName;
    private String FirmName;
    private String OfficeName;
    private String Email;
    private String BusinessPhone;
    private String Address;
    private String City;
    private String StateProvince;
    private String Zip1;
    private String Zip2;
    private String CountryID;
    private String CRDNumber;
    private String ContactID;

    /**
     * Default constructor to initialize fields
     */
    public Contact() {
        this.LastName = null;
        this.MiddleName = null;
        this.FirstName = null;
        this.FirmName = null;
        this.OfficeName = null;
        this.Email = null;
        this.BusinessPhone = null;
        this.Address = null;
        this.City = null;
        this.StateProvince = null;
        this.Zip1 = null;
        this.Zip2 = null;
        this.CountryID = null;
        this.CRDNumber = null;
        this.ContactID = null;
    }



    public void setLastName(String last) {
        LastName = last;
    }

    public void setMiddleName(String middle) {
        MiddleName = middle;
    }

    public void setFirstName(String first) {
        FirstName = first;
    }

    public void setFirmName(String firmName) {
        FirmName = firmName;
    }

    public void setOfficeName(String officeName) {
        OfficeName = officeName;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setBusinessPhone(String businessPhone) {
        BusinessPhone = businessPhone;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setStateProvince(String stateProvince) {
        StateProvince = stateProvince;
    }

    public void setZip1(String zip1) {
        Zip1 = zip1;
    }

    public void setZip2(String zip2) {
        Zip2 = zip2;
    }

    public void setCountryID(String countryID) {
        CountryID = countryID;
    }

    public void setCRDNumber(String CRDNumber) {
        this.CRDNumber = CRDNumber;
    }

    public void setContactID(String contactID) {
        ContactID = contactID;
    }

    public String getLastName() {
        return LastName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getFirmName() {
        return FirmName;
    }

    public String getOfficeName() {
        return OfficeName;
    }

    public String getEmail() {
        return Email;
    }

    public String getBusinessPhone() {
        return BusinessPhone;
    }

    public String getAddress() {
        return Address;
    }

    public String getCity() {
        return City;
    }

    public String getStateProvince() {
        return StateProvince;
    }

    public String getZip1() {
        return Zip1;
    }

    public String getZip2() {
        return Zip2;
    }

    public String getCountryID() {
        return CountryID;
    }

    public String getCRDNumber() {
        return CRDNumber;
    }

    public String getContactID() {
        return ContactID;
    }

    public ArrayList<String> getFieldList() {
        ArrayList<String> contactFields = new ArrayList<>();

        contactFields.add(this.LastName);
        contactFields.add(this.MiddleName);
        contactFields.add(this.FirstName);
        contactFields.add(this.FirmName);
        contactFields.add(this.OfficeName);
        contactFields.add(this.Email);
        contactFields.add(this.BusinessPhone);
        contactFields.add(this.Address);
        contactFields.add(this.City);
        contactFields.add(this.StateProvince);
        contactFields.add(this.Zip1);
        contactFields.add(this.Zip2);
        contactFields.add(this.CountryID);
        contactFields.add(this.CRDNumber);
        contactFields.add(this.ContactID);

        return contactFields;
    }


    public void printAll() {
        System.out.printf("%-40s %-40s %-40s %-40s %-40s %-40s " +
                "%-40s %-40s %-40s %-40s %-40s %-40s %-40s %-40s %-40s\n",
                LastName, MiddleName, FirstName, FirmName, OfficeName,
                Email, BusinessPhone, Address, City, StateProvince,
                Zip1, Zip2, CountryID, CRDNumber, ContactID);
    }
}
