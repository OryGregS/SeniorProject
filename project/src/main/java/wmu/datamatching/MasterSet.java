package wmu.datamatching;

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
    private Contact header;
    private ArrayList<Contact> ContactList;

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

        try {
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            CSVParser csv = new CSVParser(reader, CSVFormat.DEFAULT.withIgnoreSurroundingSpaces());

            for (CSVRecord obs : csv) {
                Contact contact = new Contact();

                contact.setLastName( checkNULL(obs.get(0)) );
                contact.setMiddleName( checkNULL(obs.get(1)) );
                contact.setFirstName( checkNULL(obs.get(2)) );
                contact.setFirmName( checkNULL(obs.get(3)) );
                contact.setOfficeName( checkNULL(obs.get(4)) );
                contact.setEmail( checkNULL(obs.get(5)) );
                contact.setBusinessPhone( checkNULL(obs.get(6)) );
                contact.setAddress1( checkNULL(obs.get(7)) );
                contact.setAddress2( checkNULL(obs.get(8)) );
                contact.setCity( checkNULL(obs.get(9)) );
                contact.setStateProvince( checkNULL(obs.get(10)) );
                contact.setZip1( checkNULL(obs.get(11)) );
                contact.setZip2( checkNULL(obs.get(12)) );
                contact.setCountryID( checkNULL(obs.get(13)) );
                contact.setCRDNumber( checkNULL(obs.get(14)) );
                contact.setContactID( checkNULL(obs.get(15)) );

                ContactList.add(contact);
                numRows++;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        handleHeader();
        return true;
    }

    /***
     *
     * @param data - The string at the row,col of the dataset
     * @return - Empty string if "NULL", otherwise the original string
     */
    public String checkNULL(String data) {

        if (data.equals("NULL")) {
            return "";
        } else {
            return data;
        }

    }

    private void handleHeader() {
        header = ContactList.get(0);
        ContactList.remove(0);
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
    public ArrayList<Contact> getContactList() {
        return ContactList;
    }

    public void head() {

        System.out.println("\n-----MASTER-----\n");
        header.printAll();
        System.out.println();
        for (int i = 0; i < 6; i++) {
            ContactList.get(i).printAll();

        }
        System.out.println();
    }

    public void head(int capacity) {

        System.out.println("\n-----MASTER-----\n");
        header.printAll();
        System.out.println();
        for (int i = 0; i < capacity; i++) {
            ContactList.get(i).printAll();

        }
        System.out.println();
    }

}