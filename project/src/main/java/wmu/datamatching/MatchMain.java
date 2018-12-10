package wmu.datamatching;


import me.xdrop.fuzzywuzzy.FuzzySearch;

import java.util.ArrayList;
import java.util.HashMap;

public class MatchMain {

    public static void main(String[] args) {

        String masterPath = "./data/contact_master.csv";
        String matchPath = "./data/contact_match.csv";

        ParseMaster master = new ParseMaster();
        master.readCSV(masterPath);
        master.head();
        
        ParseMatch match = new ParseMatch();
        match.readCSV(matchPath);
        match.head();


        int r = FuzzySearch.ratio("", "");
        System.out.printf("-> r = %d\n", r);

        r = FuzzySearch.ratio("abcd", "qwer");
        System.out.printf("-> r = %d\n", r);


        r = FuzzySearch.ratio("greg", "greg");
        System.out.printf("-> r = %d\n", r);

        r = FuzzySearch.ratio("axel", "axel7");
        System.out.printf("-> r = %d\n", r);

        r = FuzzySearch.ratio("greg", "gerg");
        System.out.printf("-> r = %d\n", r);

        r = FuzzySearch.ratio("noah", "haon");
        System.out.printf("-> r = %d\n", r);

        System.out.println();
        checkEqualCountryID(match.getMatchList());
        System.out.println();
        checkEqualFirmName(match.getMatchList());
        System.out.println();
        checkEqualOfficeName(match.getMatchList());
        System.out.println();

        System.out.printf("Match -> Number of columns: %d\n", match.getNumCols());
        System.out.printf("Match -> Number of rows: %d\n", match.getNumRows());


        System.out.println();
        System.out.println();
        checkEqualCRD(match.getMatchList());
        System.out.println();
        System.out.println();
        checkEqualCRD(master.getMasterList());
        System.out.println();
        System.out.println();

        checkEqualCRDNotEmpty(match.getMatchList(), countEmptyCRDsReturnWithCRDs(match.getMatchList(), "Match"));

        checkEqualCRDNotEmpty(master.getMasterList(), countEmptyCRDsReturnWithCRDs(master.getMasterList(), "Master") );

        System.out.println();
        System.out.println();
        countEmptyCRDsReturnWithNoCRDs(match.getMatchList(), "Match");
        countEmptyCRDsReturnWithNoCRDs(master.getMasterList(), "Master");

        System.out.println();
        System.out.println();
        ListEmptyCRDs(master.getMasterList(), "Master");
        System.out.println();
        System.out.println();
        ListEmptyCRDs(match.getMatchList(), "Match");

//        checkOnlyByCRD();






    }

    public static ArrayList<Contact> ListEmptyCRDs(ArrayList<Contact> list, String  type){
        ArrayList<Contact> listNew = new ArrayList<Contact>();
        ArrayList<Integer> withNoCRD = new ArrayList<Integer>();
        int i;
        int count = 0;
        int len = list.size();
        // i = 1 to avoid header
        for (i=1; i<len; i++){
            if(list.get(i).getCRDNumber().equals("")){
                count++;
                withNoCRD.add(i);
            }
        }
        len = len - 1;
        System.out.printf("%s => The count is : %d / %d, %f\n", type, count, len, ((double) count/ (double) len) * 100.0);
        for (i=0; i<count; i++){
            listNew.add(list.get(withNoCRD.get(i)));
        }
        System.out.printf("%s => The count is : %d / %d, %f\n", type, listNew.size(), len, ((double) listNew.size()/ (double) len) * 100.0);
        return listNew;
    }




    public static HashMap<String,ArrayList<Integer>> checkOnlyByCRD(ArrayList<Contact> master, ArrayList<Contact> match){
        ArrayList<Integer> sumOfRatios = new ArrayList<Integer>();
        int i;
        int j;
        int lenOfMatch = match.size();
        int lenOfMaster = master.size();
        String key;
        Contact tempMatch;
        Contact tempMaster;
        HashMap<String,ArrayList<Integer>> matchMap = new HashMap<String,ArrayList<Integer>>();
//        ArrayList<Integer> lastName = new ArrayList<Integer>();
//        ArrayList<Integer> MiddleName = new ArrayList<Integer>();
//        ArrayList<Integer> FirstName = new ArrayList<Integer>();
//        ArrayList<Integer> emailAddres = new ArrayList<Integer>();
        ArrayList<Integer> ratios = new ArrayList<Integer>();
        ArrayList<Integer> ratios_temp = new ArrayList<Integer>();
        for(i=0; i<lenOfMatch; i++){
            for(j=0; j<lenOfMaster; j++){
                tempMatch = match.get(i);
                key = String.valueOf(i) + "crd" + String.valueOf(j);
//                This is for part 2 because there is repetion of keys
//                and we need to check with ratios_temp which ratio is best, but we need criteria
//                if (matchMap.containsKey(key)){
//
//                    matchMap.replace(key, ratios);
//                }else {
                    tempMaster = master.get(j);
                    ratios.add(FuzzySearch.ratio(tempMatch.getLastName(), tempMaster.getLastName()));
                    ratios.add(FuzzySearch.ratio(tempMatch.getMiddleName(), tempMaster.getMiddleName()));
                    ratios.add(FuzzySearch.ratio(tempMatch.getFirstName(), tempMaster.getFirstName()));
                    ratios.add(FuzzySearch.ratio(tempMatch.getEmail(), tempMaster.getEmail()));
                    ratios.add(FuzzySearch.ratio(tempMatch.getBusinessPhone(), tempMaster.getBusinessPhone()));
                    ratios.add(FuzzySearch.ratio(tempMatch.getCity(), tempMaster.getCity()));
                    ratios.add(FuzzySearch.ratio(tempMatch.getStateProvince(), tempMaster.getStateProvince()));
                    ratios.add(FuzzySearch.ratio(tempMatch.getZip1(), tempMaster.getZip1()));
                    ratios.add(FuzzySearch.ratio(tempMatch.getZip2(), tempMaster.getZip2()));
                    ratios.add(FuzzySearch.ratio(tempMatch.getCountryID(), tempMaster.getCountryID()));
                    matchMap.put(key, ratios);
//                }

            }

        }
        return matchMap;
    }



    public static ArrayList<Integer> countEmptyCRDsReturnWithCRDs(ArrayList<Contact> list, String type){
        int i;
        int count = 0;
        int len = list.size();
        ArrayList<Integer> withCRD = new ArrayList<Integer>();
        for (i=0; i<len; i++){
            if(list.get(i).getCRDNumber().equals("")){
                count++;
            }else{
                withCRD.add(i);
            }
        }

        System.out.printf("The count is : %d / %d, %f for %s\n", count, len, ((double) count/ (double) len) * 100.0,type);
        return withCRD;
    }

    public static ArrayList<Integer> countEmptyCRDsReturnWithNoCRDs(ArrayList<Contact> list, String type){
        int i;
        int count = 0;
        int len = list.size();
        ArrayList<Integer> withNoCRD = new ArrayList<Integer>();
        for (i=0; i<len; i++){
            if(list.get(i).getCRDNumber().equals("")){
                count++;
                withNoCRD.add(i);
            }
        }

        System.out.printf("The count is : %d / %d, %f for %s\n", count, len, ((double) count/ (double) len) * 100.0,type);
        return withNoCRD;
    }

    public static void checkEqualCRDNotEmpty(ArrayList<Contact> list, ArrayList<Integer> index){
        int i;
        int len = index.size();
        String key;
        HashMap<String, Integer> crds = new HashMap<>();
        for (i=0; i<len; i++){
            key = list.get(index.get(i)).getCRDNumber();
            if (crds.containsKey(key)){
                crds.replace(key, crds.get(key)+1);
            }else {
                crds.put(key, 1);
            }
        }
        len = len -1;
        for (HashMap.Entry<String,Integer> entry : crds.entrySet()){
            if (entry.getValue() > 1) {
//                System.out.printf("Key =  | %s | , Value = %d / %d, %f \n", entry.getKey(), entry.getValue(), len, ((double) entry.getValue() / (double) len) * 100.0);
            }
        }
//        System.out.printf("The count is : %d / %d, %f for %s\n", count, len, ((double) count/ (double) len) * 100.0, toCompare);

    }

    /***
     * this method help to check repetition of CRDs
     * @param list
     */
    public static void checkEqualCRD(ArrayList<Contact> list){
        int i;
        int len = list.size();
        String key;
        HashMap<String, Integer> crds = new HashMap<>();
        for (i=1; i<len; i++){
            key = list.get(i).getCRDNumber();
            if (crds.containsKey(key)){
                crds.replace(key, crds.get(key)+1);
            }else {
                crds.put(key, 1);
            }
        }
        len = len -1;
        for (HashMap.Entry<String,Integer> entry : crds.entrySet()){
            if (entry.getValue() > 1) {
//                System.out.printf("Key =  | %s | , Value = %d / %d, %f \n", entry.getKey(), entry.getValue(), len, ((double) entry.getValue() / (double) len) * 100.0);
            }
        }
//        System.out.printf("The count is : %d / %d, %f for %s\n", count, len, ((double) count/ (double) len) * 100.0, toCompare);

    }

    public static void checkEqualCountryID(ArrayList<Contact> list ){
        int i;
        int len = list.size();
        String key;
        HashMap<String, Integer> countries = new HashMap<>();
        for (i=1; i<len; i++){
            key = list.get(i).getCountryID();
            if (countries.containsKey(key)){
                countries.replace(key, countries.get(key)+1);
            }else {
                countries.put(key, 1);
            }
        }
        len = len -1;
        for (HashMap.Entry<String,Integer> entry : countries.entrySet())
            System.out.printf("Key =  | %s | , Value = %d / %d, %f \n",entry.getKey(), entry.getValue(),len , ((double) entry.getValue()/ (double) len) * 100.0 );

//        System.out.printf("The count is : %d / %d, %f for %s\n", count, len, ((double) count/ (double) len) * 100.0, toCompare);
//        System.out.println(countries);

    }

    public static void checkEqualFirmName(ArrayList<Contact> list){
        int i;
        int len = list.size();
        String key;
        HashMap<String, Integer> firms = new HashMap<>();
        for (i=1; i<len; i++){
            key = list.get(i).getFirmName();
            if (firms.containsKey(key)){
                firms.replace(key, firms.get(key)+1);
            }else {
                firms.put(key, 1);
            }
        }
        len = len -1;
        for (HashMap.Entry<String,Integer> entry : firms.entrySet())
            System.out.printf("Key =  | %s | , Value = %d / %d, %f \n",entry.getKey(), entry.getValue(),len , ((double) entry.getValue()/ (double) len) * 100.0 );
//        System.out.printf("The count is : %d / %d, %f for %s\n", count, len, ((double) count/ (double) len) * 100.0, toCompare);

    }

    public static void checkEqualOfficeName(ArrayList<Contact> list){
        int i;
        int len = list.size();
        String key;
        HashMap<String, Integer> office = new HashMap<>();
        for (i=1; i<len; i++){
            key = list.get(i).getOfficeName();
            if (office.containsKey(key)){
                office.replace(key, office.get(key)+1);
            }else {
                office.put(key, 1);
            }
        }
        len = len -1;
        for (HashMap.Entry<String,Integer> entry : office.entrySet())
            System.out.printf("Key =  | %s | , Value = %d / %d, %f \n",entry.getKey(), entry.getValue(),len , ((double) entry.getValue()/ (double) len) * 100.0 );
//        System.out.printf("The count is : %d / %d, %f for %s\n", count, len, ((double) count/ (double) len) * 100.0, toCompare);

    }

    // 3 4 7 8



}
