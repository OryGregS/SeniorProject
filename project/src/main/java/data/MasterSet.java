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

                contact.setLastName( processor.checkNULL(obs.get(0)) );
                contact.setMiddleName( processor.checkNULL(obs.get(1)) );
                contact.setFirstName( processor.checkNULL(obs.get(2)) );
                contact.setFirmName( processor.checkNULL(obs.get(3)) );
                contact.setOfficeName( processor.checkNULL(obs.get(4)) );
                contact.setEmail( processor.checkNULL(obs.get(5)) );
                contact.setBusinessPhone( processor.checkNULL(obs.get(6)) );

                String address1 = processor.checkNULL(obs.get(7));
                String address2 = processor.checkNULL(obs.get(8));

                contact.setAddress( processor.combineAddress(address1, address2) );
                contact.setCity( processor.checkNULL(obs.get(9)) );
                contact.setStateProvince( processor.checkNULL(obs.get(10)) );
                contact.setZip1( processor.checkNULL(obs.get(11)) );
                contact.setZip2( processor.checkNULL(obs.get(12)) );
                contact.setCountryID( processor.checkNULL(obs.get(13)) );
                contact.setCRDNumber( processor.checkNULL(obs.get(14)) );
                contact.setContactID( processor.checkNULL(obs.get(15)) );

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