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

package dataholder;

/**
 * Generic dataholder holder for Contact dataholder. Extended by MasterContact.
 */
public class Contact {

    String LastName;
    String MiddleName;
    String FirstName;
    String FirmName;
    String OfficeName;
    String Email;
    String BusinessPhone;
    String Address;
    String City;
    String StateProvince;
    String Zip;
    String CountryID;
    String CRDNumber;
    String ContactID;

    /***
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
        this.Zip = null;
        this.CountryID = null;
        this.CRDNumber = null;
        this.ContactID = null;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String last) {
        LastName = last;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middle) {
        MiddleName = middle;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String first) {
        FirstName = first;
    }

    public String getFirmName() {
        return FirmName;
    }

    public void setFirmName(String firmName) {
        FirmName = firmName;
    }

    public String getOfficeName() {
        return OfficeName;
    }

    public void setOfficeName(String officeName) {
        OfficeName = officeName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getBusinessPhone() {
        return BusinessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        BusinessPhone = businessPhone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getStateProvince() {
        return StateProvince;
    }

    public void setStateProvince(String stateProvince) {
        StateProvince = stateProvince;
    }

    public String getZip() {
        return Zip;
    }

    public void setZip(String zip) {
        Zip = zip;
    }

    public String getCountryID() {
        return CountryID;
    }

    public void setCountryID(String countryID) {
        CountryID = countryID;
    }

    public String getCRDNumber() {
        return CRDNumber;
    }

    public void setCRDNumber(String CRDNumber) {
        this.CRDNumber = CRDNumber;
    }

    public String getContactID() {
        return ContactID;
    }

    public void setContactID(String contactID) {
        ContactID = contactID;
    }

    public void printAll() {
        System.out.printf("%-40s %-40s %-40s %-40s %-40s %-40s " +
                        "%-40s %-70s %-40s %-40s %-40s %-40s %-40s %-40s\n",
                LastName, MiddleName, FirstName, FirmName, OfficeName,
                Email, BusinessPhone, Address, City, StateProvince,
                Zip, CountryID, CRDNumber, ContactID);

    }

    public void printRelevant() {
        System.out.printf("%-40s %-40s %-40s %-40s %-40s %-70s %-40s %-40s %-40s %-40s\n",
                LastName, MiddleName, FirstName, Email, BusinessPhone,
                Address, City, StateProvince, Zip, CRDNumber);
    }
}
