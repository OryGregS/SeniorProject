package wmu.datamatching;

import java.util.ArrayList;

public interface iParseData {

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

    boolean readCSV(String filePath);

    boolean head();

}
