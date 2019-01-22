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

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MasterSet {

    private long numRows;
    private final int numCols = 15;
    private MasterContact header;
    private ArrayList<MasterContact> ContactList;

    public MasterSet() {
        numRows = 0;
        ContactList = new ArrayList<>();
    }


    /**
     * Reads the master
     * @param filePath
     * @return
     */
    public boolean readCSV(String filePath) {

        Preprocessor processor = new Preprocessor();

        try {
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            CSVParser csv = new CSVParser(reader, CSVFormat.DEFAULT.withIgnoreSurroundingSpaces());

            for (CSVRecord obs : csv) {
                MasterContact contact = new MasterContact();

                contact.setLastName( processor.prep(obs.get(0)) );
                contact.setMiddleName( processor.prep(obs.get(1)) );
                contact.setFirstName( processor.prep(obs.get(2)) );
                contact.setFirmName( processor.prep(obs.get(3)) );
                contact.setOfficeName( processor.prep(obs.get(4)) );
                contact.setEmail( processor.checkNULL(obs.get(5)) );
                contact.setBusinessPhone( processor.prep(obs.get(6)) );

                String address1 = processor.prep(obs.get(7));
                String address2 = processor.prep(obs.get(8));

                String combinedAddress = processor.combineFields(address1, address2);
                String address = processor.handleAddress(combinedAddress);
                contact.setAddress( address );

                contact.setCity( processor.prep(obs.get(9)) );
                contact.setStateProvince( processor.prep(obs.get(10)) );

                //String zip1 = processor.checkNULL(obs.get(11));
                //String zip2 = processor.checkNULL(obs.get(12));
                //contact.setZip( processor.combineFields(zip1, zip2) );

                contact.setZip( processor.prep(obs.get(11)) );
                // skip additional zipcodes
                contact.setCountryID( processor.prep(obs.get(13)) );
                contact.setCRDNumber( processor.prep(obs.get(14)) );
                contact.setContactID( processor.prep(obs.get(15)) );

                ContactList.add(contact);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        handleHeader();
        return true;
    }

    private void handleHeader() {
        header = ContactList.get(0);
        ContactList.remove(0);
    }

    public Contact getHeader() {
        return this.header;
    }

    public long getNumRows(){
        return this.numRows;
    }

    public int getNumCols(){
        return this.numCols;
    }

    /**
     * Method to pass the ArrayList of contacts to other classes
     * @return - The ArrayList of contacts for the master dataset
     */
    public ArrayList<MasterContact> getContactList() {
        return ContactList;
    }

    /***
     *
     * @return
     */
    public void head() {

        System.out.println("\n-----MATCH-----\n");
        header.printAll();
        System.out.println();
        for (int i = 0; i < 6; i++) {
            ContactList.get(i).printAll();

        }
        System.out.println();
    }

    public void head(int capacity) {

        System.out.println("\n-----MATCH-----\n");
        header.printAll();
        System.out.println();
        for (int i = 0; i < capacity; i++) {
            ContactList.get(i).printAll();

        }
        System.out.println();
    }

}