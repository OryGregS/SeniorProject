package wmu.datamatching;


import me.xdrop.fuzzywuzzy.FuzzySearch;

import java.util.ArrayList;

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

        countEmptyCRDs(match.getMatchList(), "Match");

        countEmptyCRDs(master.getMasterList(), "Master");

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

        checkEqual(match.getMatchList(), "US");


    }

    public static void countEmptyCRDs(ArrayList<Contact> list, String type){
        int i;
        int count = 0;
        int len = list.size();
        for (i=0; i<len; i++){
            if(list.get(i).getCRDNumber().equals("")){
                count++;
            }
        }

        System.out.printf("The count is : %d / %d, %f for %s\n", count, len, ((double) count/ (double) len) * 100.0,type);
    }

    public static void checkEqual(ArrayList<Contact> list, String toCompare ){
        int i;
        int count = 0;
        int len = list.size();
        for (i=0; i<len; i++){
            if(list.get(i).getCountryID().equals(toCompare)){
                count++;
            }else {
                list.get(i).printAll();
                System.out.println();
            }
        }
        System.out.printf("The count is : %d / %d, %f for %s\n", count, len, ((double) count/ (double) len) * 100.0, toCompare);

    }

}
