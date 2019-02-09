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
 * Developed by Gregory Smith & Axel Solano. Last modified 2/8/19 10:12 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package processing;

import dataholder.Contact;
import dataholder.MasterContact;
import indexing.Indexer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;

/**
 * CSVReader reads in data from CSV files and stores the records in groups.
 */
public class CSVReader {

    private String masterPath;
    private String matchPath;
    private Indexer indexer;
    private Preprocessor processor;
    private long masterCount = 0;
    private long matchCount = 0;

    /**
     * Initialize CSV reader with path to files and indexer object
     *
     * @param masterPath Path where master record (CSV) lives.
     * @param matchPath  Path where matching records (CSV) live.
     * @param indexer    Indexer object to separate records into groups
     */
    public CSVReader(String masterPath, String matchPath, Indexer indexer) {

        this.masterPath = masterPath;
        this.matchPath = matchPath;
        this.indexer = indexer;
        processor = new Preprocessor();

    }

    public void setMatchPath(String matchPath) {
        this.matchPath = matchPath;
    }

    /**
     * Gets the number of rows in matching CSV's.
     *
     * @return Number of rows being matched.
     */
    public long getMatchCount() {
        return matchCount;
    }

    /**
     * Gets the number of rows in master CSV.
     *
     * @return Number of rows in master CSV.
     */
    public long getMasterCount() {
        return masterCount;
    }

    /**
     * Reads the master CSV file.
     *
     * @param fileName Name of file.
     * @throws CSVInputException Error finding or reading a CSV file.
     */
    @SuppressWarnings("Duplicates")
    public void readMaster(String fileName) throws CSVInputException {

        try {

            Reader reader = new FileReader(this.masterPath + fileName);
            CSVParser csv = new CSVParser(reader, CSVFormat.DEFAULT
                    .withIgnoreSurroundingSpaces()
                    .withFirstRecordAsHeader()
                    .withHeader(Headers.class));

            for (CSVRecord obs : csv) {

                MasterContact contact = new MasterContact();
                setFields(contact, obs);
                findGroup(contact);
                masterCount++;

            }
        } catch (IOException e) {
            throw new CSVInputException(this.masterPath, fileName);
        }
    }

    /**
     * Reads the master CSV file. Throws CSVInputException.
     *
     * @param fileName           Name of file
     * @param topMatchesListSize Specifier for amount of top matches to hold.
     * @throws CSVInputException Error finding or reading a CSV file.
     */
    @SuppressWarnings("Duplicates")
    public void readMaster(String fileName, int topMatchesListSize) throws CSVInputException {

        try {
            Reader reader = new FileReader(this.masterPath + fileName);
            CSVParser csv = new CSVParser(reader, CSVFormat.DEFAULT
                    .withIgnoreSurroundingSpaces()
                    .withFirstRecordAsHeader()
                    .withHeader(Headers.class));

            for (CSVRecord obs : csv) {

                MasterContact contact = new MasterContact(topMatchesListSize);
                setFields(contact, obs);
                findGroup(contact);
                masterCount++;

            }
        } catch (IOException e) {
            throw new CSVInputException(this.masterPath, fileName);
        }

    }

    /**
     * Reads a CSV file to be matched to master. Throws CSVInputException.
     *
     * @param fileName Name of file.
     * @throws CSVInputException Error finding or reading a CSV file.
     */
    public void readMatch(String fileName) throws CSVInputException {

        try {
            Reader reader = new FileReader(this.matchPath + fileName);
            CSVParser csv = new CSVParser(reader, CSVFormat.DEFAULT
                    .withIgnoreSurroundingSpaces()
                    .withFirstRecordAsHeader()
                    .withHeader(Headers.class));

            for (CSVRecord obs : csv) {

                Contact contact = new Contact();
                setFields(contact, obs);
                findGroup(contact);
                matchCount++;

            }
        } catch (IOException e) {
            throw new CSVInputException(this.matchPath, fileName);
        }
    }

    /**
     * Indexes and stores a matching record in a group.
     *
     * @param contact Matching contact datafrom matching CSV(s).
     */
    private void findGroup(Contact contact) {
        this.indexer.indexContact(contact);
    }

    /**
     * Indexes and stores a master record in a group.
     *
     * @param masterContact Master contact data from master CSV.
     */
    private void findGroup(MasterContact masterContact) {
        this.indexer.indexMaster(masterContact);
    }

    /**
     * Stores a row of master data into MasterContact object
     *
     * @param masterContact Data holder for master records.
     * @param obs           CSVRecord object. (Each CSVRecord object is a row).
     */
    @SuppressWarnings("Duplicates")
    private void setFields(MasterContact masterContact, CSVRecord obs) {

        masterContact.setLastName(processor.prep(obs.get(Headers.LAST_NAME)));
        masterContact.setMiddleName(processor.prep(obs.get(Headers.MIDDLE_NAME)));
        masterContact.setFirstName(processor.prep(obs.get(Headers.FIRST_NAME)));
        masterContact.setFirmName(processor.prep(obs.get(Headers.FIRM_NAME)));
        masterContact.setOfficeName(processor.prep(obs.get(Headers.OFFICE_NAME)));
        masterContact.setEmail(processor.checkNULL(obs.get(Headers.EMAIL_ADDRESS)));
        masterContact.setBusinessPhone(processor.checkNULL(obs.get(Headers.BUSINESS_PHONE)));

        String address1 = obs.get(Headers.ADDRESS_LINE_1);
        String address2 = obs.get(Headers.ADDRESS_LINE_2);
        String address = processor.handleAddress(address1, address2);

        masterContact.setAddress(address);
        masterContact.setCity(processor.prep(obs.get(Headers.CITY)));
        masterContact.setStateProvince(processor.prep(obs.get(Headers.STATE_PROVINCE)));
        masterContact.setZip(processor.prep(obs.get(Headers.POSTAL_CODE_1)));
        obs.get(Headers.POSTAL_CODE_2);
        // skip additional zipcodes
        masterContact.setCountryID(processor.prep(obs.get(Headers.COUNTRY_ID)));
        masterContact.setCRDNumber(processor.prep(obs.get(Headers.CRD_NUMBER)));
        masterContact.setContactID(processor.prep(obs.get(Headers.CONTACT_ID)));
    }

    /**
     * Stores a row of master data into MasterContact object
     *
     * @param contact Data holder for master records.
     * @param obs     CSVRecord object. (Each CSVRecord object is a row).
     */
    @SuppressWarnings("Duplicates")
    private void setFields(Contact contact, CSVRecord obs) {

        contact.setLastName(processor.prep(obs.get(Headers.LAST_NAME)));
        contact.setMiddleName(processor.prep(obs.get(Headers.MIDDLE_NAME)));
        contact.setFirstName(processor.prep(obs.get(Headers.FIRST_NAME)));
        contact.setFirmName(processor.prep(obs.get(Headers.FIRM_NAME)));
        contact.setOfficeName(processor.prep(obs.get(Headers.OFFICE_NAME)));
        contact.setEmail(processor.checkNULL(obs.get(Headers.EMAIL_ADDRESS)));
        contact.setBusinessPhone(processor.checkNULL(obs.get(Headers.BUSINESS_PHONE)));

        String address1 = obs.get(Headers.ADDRESS_LINE_1);
        String address2 = obs.get(Headers.ADDRESS_LINE_2);
        String address = processor.handleAddress(address1, address2);

        contact.setAddress(address);
        contact.setCity(processor.prep(obs.get(Headers.CITY)));
        contact.setStateProvince(processor.prep(obs.get(Headers.STATE_PROVINCE)));
        contact.setZip(processor.prep(obs.get(Headers.POSTAL_CODE_1)));
        obs.get(Headers.POSTAL_CODE_2);
        // skip additional zipcodes
        contact.setCountryID(processor.prep(obs.get(Headers.COUNTRY_ID)));
        contact.setCRDNumber(processor.prep(obs.get(Headers.CRD_NUMBER)));
        contact.setContactID(processor.prep(obs.get(Headers.CONTACT_ID)));
    }

    /**
     * Defines the fields in our CSV files.
     */
    private enum Headers {
        LAST_NAME, MIDDLE_NAME, FIRST_NAME, FIRM_NAME, OFFICE_NAME, EMAIL_ADDRESS,
        BUSINESS_PHONE, ADDRESS_LINE_1, ADDRESS_LINE_2, CITY, STATE_PROVINCE,
        POSTAL_CODE_1, POSTAL_CODE_2, COUNTRY_ID, CRD_NUMBER, CONTACT_ID
    }

}
