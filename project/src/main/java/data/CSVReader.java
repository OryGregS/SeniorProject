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
 * Developed by Gregory Smith & Axel Solano. Last modified 1/26/19 3:06 PM.
 * Copyright (c) 2019. All rights reserved.
 */

package data;

import indexing.Indexer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

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
     * @param masterPath
     *          Path where master record (CSV) lives.
     * @param matchPath
     *          Path where matching records (CSV) live.
     * @param indexer
     *          Indexer object to separate records into groups
     */
    public CSVReader(String masterPath, String matchPath, Indexer indexer) {

        this.masterPath = masterPath;
        this.matchPath = matchPath;
        this.indexer = indexer;
        processor = new Preprocessor();

    }

    /**
     * Gets the number of rows in matching CSV's.
     *
     * @return
     *          Number of rows being matched.
     */
    public long getMatchCount() {
        return matchCount;
    }

    /**
     * Gets the number of rows in master CSV.
     *
     * @return
     *          Number of rows in master CSV.
     */
    public long getMasterCount() {
        return masterCount;
    }

    /**
     * Reads the master CSV file.
     *
     * @param fileName
     *          Name of file
     * @return
     *          True upon reading file successfully, false if not.
     */
    public boolean readMaster(String fileName) {

        boolean skipHeader = true;

        try {

            Reader reader = Files.newBufferedReader(Paths.get(this.masterPath + fileName));
            CSVParser csv = new CSVParser(reader, CSVFormat.DEFAULT.withIgnoreSurroundingSpaces());

            for (CSVRecord obs : csv) {

                if (skipHeader) {

                    skipHeader = false;

                } else {

                    MasterContact contact = new MasterContact();

                    setFieldsForMasterContact(contact, obs);

                    findGroup(contact);

                    masterCount++;

                }

            }

        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            return false;
        }

        return true;

    }

    /**
     * Reads a CSV file to be matched to master.
     *
     * @param fileName
     *          Name of file.
     * @param alt
     *          True if missing 2nd zipcode, false if not.
     * @return
     *          True upon reading file successfully, false if not.
     */
    public boolean readMatch(String fileName, boolean alt) {

        boolean skipHeader = true;

        try {
            Reader reader = Files.newBufferedReader(Paths.get(this.matchPath + fileName));
            CSVParser csv = new CSVParser(reader, CSVFormat.DEFAULT.withIgnoreSurroundingSpaces());

            for (CSVRecord obs : csv) {

                if (skipHeader) {

                    skipHeader = false;

                } else {

                    Contact contact = new Contact();

                    setFieldsForContact(contact, obs, alt);

                    findGroup(contact);

                    matchCount++;

                }
            }

        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            return false;
        }

        return true;

    }

    /**
     * Indexes and stores a matching record in a group.
     *
     * @param contact
     *          Matching contact data from matching CSV(s).
     */
    private void findGroup(Contact contact) {
        this.indexer.indexContact(contact);
    }

    /**
     * Indexes and stores a master record in a group.
     *
     * @param masterContact
     *          Master contact data from master CSV.
     */
    private void findGroup(MasterContact masterContact) {
        this.indexer.indexMaster(masterContact);
    }

    /**
     * Stores a row of matching data into Contact object.
     *
     * @param contact
     *          Data holder for matching contacts.
     * @param obs
     *          CSVRecord object. (Each CSVRecord object is a row).
     * @param alt
     *          True if missing 2nd zipcode, false if not.
     */
    @SuppressWarnings("Duplicates")
    private void setFieldsForContact(Contact contact, CSVRecord obs, boolean alt) {

        contact.setLastName(processor.prep(obs.get(0)));
        contact.setMiddleName(processor.prep(obs.get(1)));
        contact.setFirstName(processor.prep(obs.get(2)));
        contact.setFirmName(processor.prep(obs.get(3)));
        contact.setOfficeName(processor.prep(obs.get(4)));
        contact.setEmail(processor.checkNULL(obs.get(5)));
        contact.setBusinessPhone(processor.checkNULL(obs.get(6)));

        String address1 = obs.get(7);
        String address2 = obs.get(8);
        String address = processor.handleAddress(address1, address2);

        contact.setAddress(address);
        contact.setCity(processor.prep(obs.get(9)));
        contact.setStateProvince(processor.prep(obs.get(10)));
        contact.setZip(processor.prep(obs.get(11)));

        if (alt) {

            contact.setCountryID(processor.prep(obs.get(12)));
            contact.setCRDNumber(processor.prep(obs.get(13)));
            contact.setContactID(processor.prep(obs.get(14)));

        } else {

            contact.setCountryID(processor.prep(obs.get(13)));
            contact.setCRDNumber(processor.prep(obs.get(14)));
            contact.setContactID(processor.prep(obs.get(15)));

        }

    }

    /**
     * Stores a row of master data into MasterContact object
     *
     * @param masterContact
     *          Data holder for master records.
     * @param obs
     *          CSVRecord object. (Each CSVRecord object is a row).
     */
    @SuppressWarnings("Duplicates")
    private void setFieldsForMasterContact(MasterContact masterContact, CSVRecord obs) {

        masterContact.setLastName(processor.prep(obs.get(0)));
        masterContact.setMiddleName(processor.prep(obs.get(1)));
        masterContact.setFirstName(processor.prep(obs.get(2)));
        masterContact.setFirmName(processor.prep(obs.get(3)));
        masterContact.setOfficeName(processor.prep(obs.get(4)));
        masterContact.setEmail(processor.checkNULL(obs.get(5)));
        masterContact.setBusinessPhone(processor.checkNULL(obs.get(6)));

        String address1 = obs.get(7);
        String address2 = obs.get(8);
        String address = processor.handleAddress(address1, address2);

        masterContact.setAddress(address);
        masterContact.setCity(processor.prep(obs.get(9)));
        masterContact.setStateProvince(processor.prep(obs.get(10)));
        masterContact.setZip(processor.prep(obs.get(11)));
        // skip additional zipcodes
        masterContact.setCountryID(processor.prep(obs.get(13)));
        masterContact.setCRDNumber(processor.prep(obs.get(14)));
        masterContact.setContactID(processor.prep(obs.get(15)));
    }

}
