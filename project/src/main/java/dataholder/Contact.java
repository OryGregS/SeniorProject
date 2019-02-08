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

    /**
     * Gets a contact's last name.
     *
     * @return Contact's last name.
     */
    public String getLastName() {
        return LastName;
    }

    /**
     * Sets a contact's last name.
     *
     * @param lastName Contact's last name.
     */
    public void setLastName(String lastName) {
        LastName = lastName;
    }

    /**
     * Gets a contact's middle name.
     *
     * @return Contact's middle name.
     */
    public String getMiddleName() {
        return MiddleName;
    }

    /**
     * Sets a contact's middle name.
     *
     * @param middleName Contact's middle name.
     */
    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    /**
     * Gets a contact's first name.
     *
     * @return Contact's first name.
     */
    public String getFirstName() {
        return FirstName;
    }

    /**
     * Sets a contact's first name.
     *
     * @param firstName Contact's first name.
     */
    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    /**
     * Gets a contact's firm name.
     *
     * @return Contact's firm name.
     */
    public String getFirmName() {
        return FirmName;
    }

    /**
     * Sets a contact's firm name.
     *
     * @param firmName Contact's firm name.
     */
    public void setFirmName(String firmName) {
        FirmName = firmName;
    }

    /**
     * Gets a contact's office name.
     *
     * @return Contact's office name.
     */
    public String getOfficeName() {
        return OfficeName;
    }

    /**
     * Sets a contact's office name.
     *
     * @param officeName Contact's office name.
     */
    public void setOfficeName(String officeName) {
        OfficeName = officeName;
    }

    /**
     * Gets a contact's email address.
     *
     * @return Contact's email address.
     */
    public String getEmail() {
        return Email;
    }

    /**
     * Sets a contact's email address.
     *
     * @param email Contact's email address.
     */
    public void setEmail(String email) {
        Email = email;
    }

    /**
     * Gets a contact's business phone number.
     *
     * @return Contact's business phone number.
     */
    public String getBusinessPhone() {
        return BusinessPhone;
    }

    /**
     * Sets a contact's business phone number.
     *
     * @param businessPhone Contact's business phone number.
     */
    public void setBusinessPhone(String businessPhone) {
        BusinessPhone = businessPhone;
    }

    /**
     * Gets a contact's address.
     *
     * @return Contact's address.
     */
    public String getAddress() {
        return Address;
    }

    /**
     * Sets a contact's address.
     *
     * @param address Contact's address.
     */
    public void setAddress(String address) {
        Address = address;
    }

    /**
     * Gets a contact's city.
     *
     * @return Contact's city.
     */
    public String getCity() {
        return City;
    }

    /**
     * Sets a contact's city.
     *
     * @param city Contact's city.
     */
    public void setCity(String city) {
        City = city;
    }

    /**
     * Gets a contact's state/province.
     *
     * @return Contact's state/province.
     */
    public String getStateProvince() {
        return StateProvince;
    }

    /**
     * Sets a contact's state/province.
     *
     * @param stateProvince Contact's state/province.
     */
    public void setStateProvince(String stateProvince) {
        StateProvince = stateProvince;
    }

    /**
     * Gets a contact's postal code (zip code).
     *
     * @return Contact's postal code.
     */
    public String getZip() {
        return Zip;
    }

    /**
     * Sets a contact's postal code (zip code).
     *
     * @param zip Contact's postal code.
     */
    public void setZip(String zip) {
        Zip = zip;
    }

    /**
     * Gets a contact's country ID.
     *
     * @return Contact's country ID.
     */
    public String getCountryID() {
        return CountryID;
    }

    /**
     * Sets a contact's country ID.
     *
     * @param countryID Contact's country ID.
     */
    public void setCountryID(String countryID) {
        CountryID = countryID;
    }

    /**
     * Gets a contact's CRD number.
     *
     * @return Contact's CRD number.
     */
    public String getCRDNumber() {
        return CRDNumber;
    }

    /**
     * Sets a contact's CRD number.
     *
     * @param CRDNumber Contact's CRD number.
     */
    public void setCRDNumber(String CRDNumber) {
        this.CRDNumber = CRDNumber;
    }

    /**
     * Gets a contact's contact ID.
     *
     * @return Contact's contact ID.
     */
    public String getContactID() {
        return ContactID;
    }

    /**
     * Sets a contact's contact ID.
     *
     * @param contactID Contact's contact ID.
     */
    public void setContactID(String contactID) {
        ContactID = contactID;
    }

    /**
     * Prints all contact data with well-spaced left-justified format.
     */
    public void printAll() {
        System.out.printf("%-40s %-40s %-40s %-40s %-40s %-40s " +
                        "%-40s %-70s %-40s %-40s %-40s %-40s %-40s %-40s\n",
                LastName, MiddleName, FirstName, FirmName, OfficeName,
                Email, BusinessPhone, Address, City, StateProvince,
                Zip, CountryID, CRDNumber, ContactID);

    }
}
