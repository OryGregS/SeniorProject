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

public class CSVReader {

    private Indexer indexer;
    private String indexMethod;
    private Preprocessor processor;

    public CSVReader(Indexer indexer, String indexMethod) {

        this.indexer = indexer;
        this.indexMethod = indexMethod;
        processor = new Preprocessor();

    }

    public boolean readMaster(String filePath) {

        boolean skipHeader = true;

        try {

            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            CSVParser csv = new CSVParser(reader, CSVFormat.DEFAULT.withIgnoreSurroundingSpaces());

            for (CSVRecord obs : csv) {

                if (skipHeader) {

                    skipHeader = false;

                } else {

                    MasterContact contact = new MasterContact();

                    setFields(contact, obs);

                    findGroup(contact);

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    public boolean readMatch(String filePath, boolean alt) {

        boolean skipHeader = true;

        try {
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            CSVParser csv = new CSVParser(reader, CSVFormat.DEFAULT.withIgnoreSurroundingSpaces());

            for (CSVRecord obs : csv) {

                if (skipHeader) {

                    skipHeader = false;

                } else {

                    Contact contact = new Contact();

                    setFields(contact, obs, alt);

                    findGroup(contact);

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    private void findGroup(Contact contact) {
        this.indexer.index(contact, this.indexMethod);
    }

    private void findGroup(MasterContact contact) {
        this.indexer.index(contact, this.indexMethod);
    }

    @SuppressWarnings("Duplicates")
    private void setFields(Contact contact, CSVRecord obs, boolean alt) {

        contact.setLastName(processor.prep(obs.get(0)));
        contact.setMiddleName(processor.prep(obs.get(1)));
        contact.setFirstName(processor.prep(obs.get(2)));
        contact.setFirmName(processor.prep(obs.get(3)));
        contact.setOfficeName(processor.prep(obs.get(4)));
        contact.setEmail(processor.checkNULL(obs.get(5)));
        contact.setBusinessPhone(processor.checkNULL(obs.get(6)));

        String address1 = processor.prep(obs.get(7));
        String address2 = processor.prep(obs.get(8));

        String combinedAddress = processor.combineFields(address1, address2);
        String address = processor.handleAddress(combinedAddress);
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

    @SuppressWarnings("Duplicates")
    private void setFields(MasterContact contact, CSVRecord obs) {

        contact.setLastName(processor.prep(obs.get(0)));
        contact.setMiddleName(processor.prep(obs.get(1)));
        contact.setFirstName(processor.prep(obs.get(2)));
        contact.setFirmName(processor.prep(obs.get(3)));
        contact.setOfficeName(processor.prep(obs.get(4)));
        contact.setEmail(processor.checkNULL(obs.get(5)));
        contact.setBusinessPhone(processor.checkNULL(obs.get(6)));

        String address1 = processor.prep(obs.get(7));
        String address2 = processor.prep(obs.get(8));

        String combinedAddress = processor.combineFields(address1, address2);
        String address = processor.handleAddress(combinedAddress);
        contact.setAddress(address);

        contact.setCity(processor.prep(obs.get(9)));
        contact.setStateProvince(processor.prep(obs.get(10)));

        contact.setZip(processor.prep(obs.get(11)));
        // skip additional zipcodes
        contact.setCountryID(processor.prep(obs.get(13)));
        contact.setCRDNumber(processor.prep(obs.get(14)));
        contact.setContactID(processor.prep(obs.get(15)));
    }

}
