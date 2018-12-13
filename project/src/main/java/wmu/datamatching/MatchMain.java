package wmu.datamatching;


import me.xdrop.fuzzywuzzy.FuzzySearch;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class MatchMain {

    public static void main(String[] args) {

        LoaderData loader = new LoaderData();
        loader.loadMasterSet();
        loader.loadMatchSet();
        MasterSet master = loader.getMasterSet();
        MatchSet match = loader.getMatchSet();

        ArrayList<Integer> fieldsToCompare = new ArrayList<>();
        fieldsToCompare.add(0); // last name
        fieldsToCompare.add(1); // middle name
        fieldsToCompare.add(2); // first name
        fieldsToCompare.add(5); // email
        fieldsToCompare.add(6); // phone
        fieldsToCompare.add(7); // address 1
        fieldsToCompare.add(8); // address 2
        fieldsToCompare.add(9); // city
        fieldsToCompare.add(10); // state
        fieldsToCompare.add(11); // zip1
        fieldsToCompare.add(12); // zip2

        CompareContacts cc = new CompareContacts(master, match, fieldsToCompare);
        cc.compareSets();

//        for (int i = 0; i < master.getContactList().size(); i++) {
//            master.getContactList().get(i).printTop();
//        }



    }

}
