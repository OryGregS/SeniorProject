package wmu.datamatching;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ParseMatch implements iParseData {

    ArrayList<ArrayList> match = new ArrayList<>();

    ArrayList<String> LAST_NAME = new ArrayList<>();
    ArrayList<String> MIDDLE_NAME = new ArrayList<>();
    ArrayList<String> FIRST_NAME = new ArrayList<>();
    ArrayList<String> EMAIL_ADDRESS = new ArrayList<>();
    ArrayList<String> BUSINESS_PHONE = new ArrayList<>();
    ArrayList<String> ADDRESS = new ArrayList<>();
    ArrayList<String> CITY = new ArrayList<>();
    ArrayList<String> STATE_PROVINCE = new ArrayList<>();
    ArrayList<String> ZIP_1 = new ArrayList<>();
    ArrayList<String> ZIP_2 = new ArrayList<>();
    ArrayList<String> COUNTRY_ID = new ArrayList<>();
    ArrayList<String> CRD_NUMBER = new ArrayList<>();
    ArrayList<String> CONTACT_ID = new ArrayList<>();

    public ParseMatch() {
        this.match.add(LAST_NAME);
        this.match.add(MIDDLE_NAME);
        this.match.add(FIRST_NAME);
        this.match.add(EMAIL_ADDRESS);
        this.match.add(BUSINESS_PHONE);
        this.match.add(ADDRESS);
        this.match.add(CITY);
        this.match.add(STATE_PROVINCE);
        this.match.add(ZIP_1);
        this.match.add(ZIP_2);
        this.match.add(COUNTRY_ID);
        this.match.add(CRD_NUMBER);
        this.match.add(CONTACT_ID);
    }

    public boolean readCSV(String filePath) {

        try {

            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            CSVParser csv = new CSVParser(reader, CSVFormat.DEFAULT);

            for (CSVRecord obs : csv) {
                LAST_NAME.add(obs.get(0));
                MIDDLE_NAME.add(obs.get(1));
                FIRST_NAME.add(obs.get(2));
                EMAIL_ADDRESS.add(obs.get(3));
                BUSINESS_PHONE.add(obs.get(4));
                ADDRESS.add(obs.get(5));
                CITY.add(obs.get(6));
                STATE_PROVINCE.add(obs.get(7));
                ZIP_1.add(obs.get(8));
                ZIP_2.add(obs.get(9));
                COUNTRY_ID.add(obs.get(10));
                CRD_NUMBER.add(obs.get(11));
                CONTACT_ID.add(obs.get(12));
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean head() {

        System.out.println("\n-----MATCH-----\n");
        for (int i = 0; i < 6; i++) {

            for (int obs = 0; obs < this.match.size(); obs++){
                System.out.printf("%-40s ", this.match.get(obs).get(i));
            }
            System.out.println();
        }
        System.out.println();
        return true;
    }

}
