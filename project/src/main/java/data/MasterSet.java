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

import indexing.Indexer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MasterSet {

    private Indexer indexer;

    public MasterSet(Indexer indexer) {

        this.indexer = indexer;

    }


    /**
     * Reads the master
     *
     * @param filePath
     * @return
     */
    public boolean readCSV(String filePath, boolean skipHeader) {

        Preprocessor processor = new Preprocessor();

        try {

            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            CSVParser csv = new CSVParser(reader, CSVFormat.DEFAULT.withIgnoreSurroundingSpaces());

            for (CSVRecord obs : csv) {

                if (skipHeader) {

                    skipHeader = false;

                } else {

                    MasterContact contact = new MasterContact();

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

                    //String zip1 = processor.checkNULL(obs.get(11));
                    //String zip2 = processor.checkNULL(obs.get(12));
                    //contact.setZip( processor.combineFields(zip1, zip2) );

                    contact.setZip(processor.prep(obs.get(11)));
                    // skip additional zipcodes
                    contact.setCountryID(processor.prep(obs.get(13)));
                    contact.setCRDNumber(processor.prep(obs.get(14)));
                    contact.setContactID(processor.prep(obs.get(15)));

                    //ContactList.add(contact);
                    indexer.index(contact);

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean readCSV(String filePath, boolean skipHeader, int scale) {
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

                        MasterContact contact = new MasterContact();

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

                        //String zip1 = processor.checkNULL(obs.get(11));
                        //String zip2 = processor.checkNULL(obs.get(12));
                        //contact.setZip( processor.combineFields(zip1, zip2) );

                        contact.setZip(processor.prep(obs.get(11)));
                        // skip additional zipcodes
                        contact.setCountryID(processor.prep(obs.get(13)));
                        contact.setCRDNumber(processor.prep(obs.get(14)));
                        contact.setContactID(processor.prep(obs.get(15)));

                        //ContactList.add(contact);
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

}