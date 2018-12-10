package wmu.datamatching;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ParseMatch {

    ArrayList<Contact> match = new ArrayList<>();

    public boolean readCSV(String filePath) {

        try {

            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            CSVParser csv = new CSVParser(reader, CSVFormat.DEFAULT);

            for (CSVRecord obs : csv) {
                Contact contact = new Contact();

                contact.setLastName(checkNULL(obs.get(0)));
                contact.setMiddleName(checkNULL(obs.get(1)));
                contact.setFirstName(checkNULL(obs.get(2)));
                contact.setFirmName(checkNULL(obs.get(3)));
                contact.setOfficeName(checkNULL(obs.get(4)));
                contact.setEmail(checkNULL(obs.get(5)));
                contact.setBusinessPhone(checkNULL(obs.get(6)));
                contact.setAddress1(checkNULL(obs.get(7)));
                contact.setAddress2(checkNULL(obs.get(8)));
                contact.setCity(checkNULL(obs.get(9)));
                contact.setStateProvince(checkNULL(obs.get(10)));
                contact.setZip1(checkNULL(obs.get(11)));
                contact.setZip2(checkNULL(obs.get(12)));
                contact.setCountryID(checkNULL(obs.get(13)));
                contact.setCRDNumber(checkNULL(obs.get(14)));
                contact.setContactID(checkNULL(obs.get(15)));

                match.add(contact);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private String checkNULL(String data) {

        if (data.equals("NULL")) {
            return "";
        } else {
            return data;
        }

    }

    public boolean head() {

        System.out.println("\n-----MATCH-----\n");
        for (int i = 0; i < 6; i++) {
            match.get(i).printAll();
            System.out.println();
        }
        System.out.println();

        return true;
    }

}
