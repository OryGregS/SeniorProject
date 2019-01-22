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
 * Developed by Gregory Smith & Axel Solano. Last modified 08/01/19 5:35 AM.
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
import java.util.ArrayList;

public class MatchSet {

    private Indexer indexer;

    public MatchSet(Indexer indexer) {
        this.indexer = indexer;
    }

    public boolean readCSV(String filePath, boolean alternate, boolean skipHeader, int scale) {

        Preprocessor processor = new Preprocessor();

        try {
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            CSVParser csv = new CSVParser(reader, CSVFormat.DEFAULT.withIgnoreSurroundingSpaces());

            int i;

            for (CSVRecord obs : csv) {

                if (skipHeader) {

                    skipHeader = false;

                } else {

                    for (i = 0; i < scale; i++) {
                        Contact contact = new Contact();

                        contact.setLastName(processor.prep(obs.get(0)));
                        contact.setMiddleName(processor.prep(obs.get(1)));
                        contact.setFirstName(processor.prep(obs.get(2)));
                        contact.setFirmName(processor.prep(obs.get(3)));
                        contact.setOfficeName(processor.prep(obs.get(4)));
                        contact.setEmail(processor.checkNULL(obs.get(5)));
                        contact.setBusinessPhone(processor.prep(obs.get(6)));

                        String address1 = processor.prep(obs.get(7));
                        String address2 = processor.prep(obs.get(8));

                        String combinedAddress = processor.combineFields(address1, address2);
                        String address = processor.handleAddress(combinedAddress);
                        contact.setAddress(address);

                        contact.setCity(processor.prep(obs.get(9)));
                        contact.setStateProvince(processor.prep(obs.get(10)));
                        contact.setZip(processor.prep(obs.get(11)));

                        if (alternate) {

                            contact.setCountryID(processor.prep(obs.get(12)));
                            contact.setCRDNumber(processor.prep(obs.get(13)));
                            contact.setContactID(processor.prep(obs.get(14)));

                        } else {

                            contact.setCountryID(processor.prep(obs.get(13)));
                            contact.setCRDNumber(processor.prep(obs.get(14)));
                            contact.setContactID(processor.prep(obs.get(15)));

                        }

                        indexer.index(contact);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    /**
     * Reads the matching dataset
     *
     * @param filePath - path to file
     * @return - true on success
     */
    public boolean readCSV(String filePath, boolean alternate, boolean skipHeader) {

        Preprocessor processor = new Preprocessor();

        try {
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            CSVParser csv = new CSVParser(reader, CSVFormat.DEFAULT.withIgnoreSurroundingSpaces());

            for (CSVRecord obs : csv) {

                if (skipHeader) {

                    skipHeader = false;

                } else {

                    Contact contact = new Contact();

                    contact.setLastName(processor.prep(obs.get(0)));
                    contact.setMiddleName(processor.prep(obs.get(1)));
                    contact.setFirstName(processor.prep(obs.get(2)));
                    contact.setFirmName(processor.prep(obs.get(3)));
                    contact.setOfficeName(processor.prep(obs.get(4)));
                    contact.setEmail(processor.checkNULL(obs.get(5)));
                    contact.setBusinessPhone(processor.prep(obs.get(6)));

                    String address1 = processor.prep(obs.get(7));
                    String address2 = processor.prep(obs.get(8));

                    String combinedAddress = processor.combineFields(address1, address2);
                    String address = processor.handleAddress(combinedAddress);
                    contact.setAddress(address);

                    contact.setCity(processor.prep(obs.get(9)));
                    contact.setStateProvince(processor.prep(obs.get(10)));
                    contact.setZip(processor.prep(obs.get(11)));

                    if (alternate) {

                        contact.setCountryID(processor.prep(obs.get(12)));
                        contact.setCRDNumber(processor.prep(obs.get(13)));
                        contact.setContactID(processor.prep(obs.get(14)));

                    } else {

                        contact.setCountryID(processor.prep(obs.get(13)));
                        contact.setCRDNumber(processor.prep(obs.get(14)));
                        contact.setContactID(processor.prep(obs.get(15)));

                    }

                    indexer.index(contact);

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}