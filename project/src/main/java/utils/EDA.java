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
 * Developed by Gregory Smith & Axel Solano. Last modified 2/4/19 12:12 PM.
 * Copyright (c) 2019. All rights reserved.
 */

package utils;
/*
import data.Contact;
import matching.KeyMatch;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import java.util.ArrayList;
import java.util.HashMap;
*/

public class EDA {
/*

!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
!!!!!!!!!!!!!!! START COMMENT BLOCK FOR MAIN !!!!!!!!!!!!!!!!!!
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    public void main() {
        DataLoader loaderData = new DataLoader();
        loaderData.loadMasterSet();
        loaderData.loadMatchSet();

        MasterSet master = loaderData.getMasterSet();
        MatchSet match = loaderData.getMatchSet();

        master.head(7);
        match.head(7);

        ArrayList <Contact> matchList = match.getContactList();
        ArrayList <Contact> masterList = master.getContactList();
        System.out.println("\nMatch\nMatch List Details: ");
        checkEqualCountryID(matchList);
        checkEqualFirmName(matchList);
        checkEqualOfficeName(matchList);
        checkEqualContactID(matchList);
        checkEqualCRD(matchList);
        System.out.printf("Match -> Number of columns: %d\n", match.getNumCols());
        System.out.printf("Match -> Number of rows: %d\n", match.getNumRows());

        System.out.println("\nMaster\nMaster List Details: ");
        checkEqualCountryID(masterList);
        checkEqualFirmName(masterList);
        checkEqualOfficeName(masterList);
        checkEqualContactID(masterList);
        checkEqualCRD(masterList);
        System.out.printf("Master -> Number of columns: %d\n", master.getNumCols());
        System.out.printf("Master -> Number of rows: %d\n", master.getNumRows());

        System.out.println();
        System.out.println();

//        HashMap<KeyMatch,Integer> matchMap = findMatch(masterList,matchList);
////        showMatch(masterList,matchList,matchMap);

        ArrayList<KeyMatch> matchMapList = findMatchV2(masterList,matchList);
        showMatchV2(masterList,matchList,matchMapList);


        System.out.println();
        System.out.println();
        checkEqualCRD(match.getContactList());
        System.out.println();
        System.out.println();
        checkEqualCRD(master.getContactList());
        System.out.println();
        System.out.println();

        checkEqualCRDNotEmpty(match.getContactList(), countEmptyCRDsReturnWithCRDs(match.getContactList(), "Match"));

        checkEqualCRDNotEmpty(master.getContactList(), countEmptyCRDsReturnWithCRDs(master.getContactList(), "Master") );

        System.out.println();
        System.out.println();
        countEmptyCRDsReturnWithNoCRDs(match.getContactList(), "Match");
        countEmptyCRDsReturnWithNoCRDs(master.getContactList(), "Master");

        System.out.println();
        System.out.println();
        ListEmptyCRDs(master.getContactList(), "Master");

        System.out.println();
        System.out.println();
        ListEmptyCRDs(match.getContactList(), "Match");
        System.out.println();
        System.out.println();

        //checkOnlyByCRD(master.getContactList(), ListEmptyCRDs(match.getContactList()(), "Match"));
        //checkOnlyByCRDv2(master.getContactList(), ListEmptyCRDs(match.getContactList()(), "Match"));


        System.out.println("\nDONE");


    }

    public void checkEqualCountryID(ArrayList<Contact> list) {
        System.out.println("CountryID:");
        int i;
        int len = list.size();
        String key;
        HashMap<String, Integer> countries = new HashMap<>();
        for (i = 0; i < len; i++) {
            key = list.get(i).getCountryID();
            if (countries.containsKey(key) && key.equals("") == false) {
                countries.replace(key, countries.get(key) + 1);
            } else {
                countries.put(key, 1);
            }
        }
        for (HashMap.Entry<String, Integer> entry : countries.entrySet())
            System.out.printf("Key =  |%s| , Value = |%d| / |%d| -> %f%% \n", entry.getKey(), entry.getValue(), len, ((double) entry.getValue() / (double) len) * 100.0);
        System.out.println();
    }

    public void checkEqualFirmName(ArrayList<Contact> list) {
        System.out.println("Firm Name:");
        int i;
        int len = list.size();
        String key;
        HashMap<String, Integer> firms = new HashMap<>();
        for (i = 0; i < len; i++) {
            key = list.get(i).getFirmName();
            if (firms.containsKey(key) && key.equals("") == false) {
                firms.replace(key, firms.get(key) + 1);
            } else {
                firms.put(key, 1);
            }
        }
        for (HashMap.Entry<String, Integer> entry : firms.entrySet())
            System.out.printf("Key =  |%s| , Value = |%d| / |%d| -> %f%% \n", entry.getKey(), entry.getValue(), len, ((double) entry.getValue() / (double) len) * 100.0);

        System.out.println();
    }

    public void checkEqualOfficeName(ArrayList<Contact> list) {
        System.out.println("Office Name:");
        int i;
        int len = list.size();
        String key;
        HashMap<String, Integer> office = new HashMap<>();
        for (i = 0; i < len; i++) {
            key = list.get(i).getOfficeName();
            if (office.containsKey(key) && key.equals("") == false) {
                office.replace(key, office.get(key) + 1);
            } else {
                office.put(key, 1);
            }
        }
        for (HashMap.Entry<String, Integer> entry : office.entrySet())
            System.out.printf("Key =  |%s| , Value = |%d| / |%d| -> %f%% \n", entry.getKey(), entry.getValue(), len, ((double) entry.getValue() / (double) len) * 100.0);
        System.out.println();
    }

    public void checkEqualContactID(ArrayList<Contact> list) {
        System.out.println("Contact ID:");
        int i;
        int len = list.size();
        String key;
        HashMap<String, Integer> contactIDs = new HashMap<>();
        for (i = 0; i < len; i++) {
            key = list.get(i).getContactID();
            if (contactIDs.containsKey(key) && key.equals("") == false) {
                contactIDs.replace(key, contactIDs.get(key) + 1);
            } else {
                contactIDs.put(key, 1);
            }
        }
        int countEmpty = 0;
        for (HashMap.Entry<String, Integer> entry : contactIDs.entrySet()) {
            if (entry.getKey().equals("")) {
//                System.out.printf("::::Key =  |%s| , Value = |%d| / |%d| -> %f%% \n",entry.getKey(), entry.getValue(), len , ((double) entry.getValue()/ (double) len) * 100.0 );
                countEmpty++;
            }
//            System.out.printf("Key =  |%s| , Value = |%d| / |%d| -> %f%% \n", entry.getKey(), entry.getValue(), len, ((double) entry.getValue() / (double) len) * 100.0);
        }
        System.out.printf("The count is : %d / %d -> %f%%\n", contactIDs.size(), len, ((double) contactIDs.size() / (double) len) * 100.0);
        System.out.printf("The empty count is : %d / %d -> %f%%\n", countEmpty, len, ((double) countEmpty / (double) len) * 100.0);
        System.out.println();

    }

    public void checkEqualCRD(ArrayList<Contact> list) {
        System.out.println("CRD:");
        int i;
        int len = list.size();
        String key;
        HashMap<String, Integer> crds = new HashMap<>();
        for (i = 1; i < len; i++) {
            key = list.get(i).getCRDNumber();
            if (crds.containsKey(key) && key.equals("") == false) {
                if (crds.get(key) != 1) {
                    System.out.println(crds.get(key));
                }
                crds.replace(key, crds.get(key) + 1);
            } else {
                crds.put(key, 1);
            }
        }
        int countGt1 = 0;
        int countEmptyCrds = 0;
        for (HashMap.Entry<String, Integer> entry : crds.entrySet()) {
            if (entry.getValue() != 1) {
//                System.out.printf(":::Key =  |%s| , Value = |%d| / |%d| -> %f%% \n",entry.getKey(), entry.getValue(), len , ((double) entry.getValue()/ (double) len) * 100.0 );
                countGt1++;
            }
            if (entry.getKey().equals("")) {
//                System.out.printf("::::Key =  |%s| , Value = |%d| / |%d| -> %f%% \n",entry.getKey(), entry.getValue(), len , ((double) entry.getValue()/ (double) len) * 100.0 );
                countEmptyCrds++;
            }

        }
        System.out.printf("The repetition count is : %d / %d -> %f%%\n", countGt1, len, ((double) countGt1 / (double) len) * 100.0);
        System.out.printf("The empty count is : %d / %d -> %f%%\n", countEmptyCrds, len, ((double) countEmptyCrds / (double) len) * 100.0);
        System.out.println();
    }


    public HashMap<KeyMatch, Integer> findMatch(ArrayList<Contact> master, ArrayList<Contact> match) {
        int i;
        int j;
        int lenOfMatch = match.size();
        int lenOfMaster = master.size();
        KeyMatch key = new KeyMatch(0, 0, 0, "");
        KeyMatch finalKey = new KeyMatch(key);
        String maxKey = "";
        int max = 0;
        Contact tempMatch;
        Contact tempMaster;
        HashMap<KeyMatch, Integer> matchMap = new HashMap<>();
        String keyTemp;
        int ratios;
        for (i = 0; i < lenOfMatch * 0.01; i++) {
            for (j = 0; j < lenOfMaster * 0.01; j++) {
                tempMatch = match.get(i);

                tempMaster = master.get(j);

                keyTemp = "matchIndex:" + String.valueOf(i) + " || " + "masterIndex:" + String.valueOf(j) + " || ContactID: " + tempMaster.getContactID();

                ratios = 0;
                ratios += FuzzySearch.ratio(tempMatch.getLastName(), tempMaster.getLastName());
                ratios += FuzzySearch.ratio(tempMatch.getMiddleName(), tempMaster.getMiddleName());
                ratios += FuzzySearch.ratio(tempMatch.getFirstName(), tempMaster.getFirstName());
                ratios += FuzzySearch.ratio(tempMatch.getFirmName(), tempMaster.getFirmName());
                ratios += FuzzySearch.ratio(tempMatch.getOfficeName(), tempMaster.getOfficeName());
                ratios += FuzzySearch.ratio(tempMatch.getEmail(), tempMaster.getEmail());
                ratios += FuzzySearch.ratio(tempMatch.getBusinessPhone(), tempMaster.getBusinessPhone());
                ratios += FuzzySearch.ratio(tempMatch.getAddress(), tempMaster.getAddress());
                ratios += FuzzySearch.ratio(tempMatch.getCity(), tempMaster.getCity());
                ratios += FuzzySearch.ratio(tempMatch.getStateProvince(), tempMaster.getStateProvince());
                ratios += FuzzySearch.ratio(tempMatch.getZip(), tempMaster.getZip());
                ratios += FuzzySearch.ratio(tempMatch.getCountryID(), tempMaster.getCountryID());
                ratios += FuzzySearch.ratio(tempMatch.getCRDNumber(), tempMaster.getCRDNumber());


                ratios /= 15.0;
                if (max < ratios) {
                    max = ratios;
                    key = new KeyMatch(j, i, max, tempMaster.getContactID());
                    matchMap.put(key, ratios);
                    maxKey = keyTemp;
                    finalKey = key;
//                    System.out.println(keyTemp + " => "+matchMap.get(key));
                }
            }
//            System.out.println(maxKey + " => "+matchMap.get(finalKey));
            max = 0;
        }
        return matchMap;
    }

    public void showMatch(ArrayList<Contact> master, ArrayList<Contact> match, HashMap<KeyMatch, Integer> matchMap) {
        System.out.println();
        KeyMatch temp;
        for (HashMap.Entry<KeyMatch, Integer> entry : matchMap.entrySet()) {
            temp = entry.getKey();
            System.out.printf("Match Index ->  %d ", temp.matchIndex);
            match.get(temp.matchIndex).printAll();
            System.out.printf("Master Index -> %d ", temp.masterIndex);
            master.get(temp.masterIndex).printAll();
            System.out.println("Max -> " + temp.maxValue);
            System.out.println();
            System.out.println();
        }
        System.out.println();
    }

    public ArrayList<KeyMatch> findMatchV2(ArrayList<Contact> master, ArrayList<Contact> match) {
        int i;
        int j;
        int lenOfMatch = match.size();
        int lenOfMaster = master.size();
        KeyMatch key;
//        KeyMatch finalKey;
        String maxKey = "";
        int max = 0;
        Contact tempMatch;
        Contact tempMaster = null;
        ArrayList<KeyMatch> matchMap = new ArrayList<>();
        ArrayList<Integer> top5Matches = null;
        ArrayList<Integer> top5MatchesMax = null;
        String keyTemp;
        int ratios;
        for (i = 0; i < lenOfMaster * 0.1; i++) {
            max = 0;
            top5Matches = new ArrayList<>();
            top5MatchesMax = new ArrayList<>();
            tempMaster = master.get(i);
//            System.out.println("i->"+i);
            for (j = 0; j < lenOfMatch * 0.1; j++) {
                tempMatch = match.get(j);

                ratios = 0;
                ratios += FuzzySearch.ratio(tempMatch.getLastName(), tempMaster.getLastName());
                ratios += FuzzySearch.ratio(tempMatch.getMiddleName(), tempMaster.getMiddleName());
                ratios += FuzzySearch.ratio(tempMatch.getFirstName(), tempMaster.getFirstName());
                ratios += FuzzySearch.ratio(tempMatch.getFirmName(), tempMaster.getFirmName());
                ratios += FuzzySearch.ratio(tempMatch.getOfficeName(), tempMaster.getOfficeName());
                ratios += FuzzySearch.ratio(tempMatch.getEmail(), tempMaster.getEmail());
                ratios += FuzzySearch.ratio(tempMatch.getBusinessPhone(), tempMaster.getBusinessPhone());
                ratios += FuzzySearch.ratio(tempMatch.getAddress(), tempMaster.getAddress());
                ratios += FuzzySearch.ratio(tempMatch.getCity(), tempMaster.getCity());
                ratios += FuzzySearch.ratio(tempMatch.getStateProvince(), tempMaster.getStateProvince());
                ratios += FuzzySearch.ratio(tempMatch.getZip(), tempMaster.getZip());
                ratios += FuzzySearch.ratio(tempMatch.getCountryID(), tempMaster.getCountryID());
                ratios += FuzzySearch.ratio(tempMatch.getCRDNumber(), tempMaster.getCRDNumber());

                ratios /= 15.0;
                if (max < ratios) {
                    max = ratios;
//                    System.out.println("    j->"+j + " max->" + max);
                    checkCapacity(top5Matches, top5MatchesMax, j, max);
                }
            }
            key = new KeyMatch(i, j, max, tempMaster.getContactID(), top5Matches, top5MatchesMax);
            for (int k = 0; k < top5Matches.size(); k++) {
//                System.out.print(" i=" + top5Matches.get(k) + " max=" + top5MatchesMax.get(k));
            }

            matchMap.add(key);
//            System.out.println("\n--------");
        }
        return matchMap;
    }

    public void checkCapacity(ArrayList<Integer> list, ArrayList<Integer> list2, int j, int max) {
        int i = 0;
        if (list.size() == 5) {
            for (i = 0; i < 4; i++) {
                list.set(i, list.get(i + 1));
                list2.set(i, list2.get(i + 1));
            }
            list.set(4, j);
            list2.set(4, max);
        } else {
            list.add(j);
            list2.add(max);
//            System.out.println("to add index value: " + j);
//            System.out.println("to add max value: " + max);
        }
    }

    public void showMatchV2(ArrayList<Contact> master, ArrayList<Contact> match, ArrayList<KeyMatch> matchMapList) {
        System.out.println();
        ArrayList<Integer> top5;
        int i;
        int j;
        int top5Index;
        int top5Max;
        int len = matchMapList.size();
        int sizeUpTo5;
        KeyMatch temp = null;
        for (i = 0; i < len; i++) {
            temp = matchMapList.get(i);
            sizeUpTo5 = temp.top5matchesIndexes.size();
            for (j = 0; j < sizeUpTo5; j++) {
                top5Index = temp.top5matchesIndexes.get(j);
                top5Max = temp.top5matchesMax.get(j);
                System.out.printf("Match Index ->  %d | Max -> %d | ", top5Index, top5Max);
                match.get(top5Index).printAll();
            }
//            System.out.printf("Match Index ->  %d ", temp.matchIndex);
//            match.get(temp.matchIndex).printAll();
            System.out.printf("Master Index -> %d | Max -> %d | ", temp.masterIndex, temp.maxValue);
            master.get(temp.masterIndex).printAll();
            System.out.println();

        }
        System.out.println();
    }


    public ArrayList<Contact> ListEmptyCRDs(ArrayList<Contact> list, String type) {
        ArrayList<Contact> listNew = new ArrayList<Contact>();
        ArrayList<Integer> withNoCRD = new ArrayList<Integer>();
        int i;
        int count = 0;
        int len = list.size();
        // i = 1 to avoid header
        for (i = 1; i < len; i++) {
            if (list.get(i).getCRDNumber().equals("")) {
                count++;
                withNoCRD.add(i);
            }
        }
        len = len - 1;
        System.out.printf("%s => The count is : %d / %d, %f\n", type, count, len, ((double) count / (double) len) * 100.0);
        for (i = 0; i < count; i++) {
            listNew.add(list.get(withNoCRD.get(i)));
        }
        System.out.printf("%s => The count is : %d / %d, %f\n", type, listNew.size(), len, ((double) listNew.size() / (double) len) * 100.0);
        return listNew;
    }


    public HashMap<String, Integer> checkOnlyByCRDv2(ArrayList<Contact> master, ArrayList<Contact> match) {
        ArrayList<Integer> sumOfRatios = new ArrayList<Integer>();
        int i;
        int j;
        int lenOfMatch = match.size();
        int lenOfMaster = master.size();
        String key = "";
        int max = 0;
        Contact tempMatch;
        Contact tempMaster;
        HashMap<String, Integer> matchMap = new HashMap<>();
        String maxKey = "";
        //ArrayList<Integer> ratios;// = new ArrayList<Integer>();
        int ratios = 0;
        ArrayList<Integer> ratios_temp = new ArrayList<Integer>();
        for (i = 0; i < lenOfMatch * 0.01; i++) {
            for (j = 0; j < lenOfMaster * 0.01; j++) {
                tempMatch = match.get(i);

                tempMaster = master.get(j);
                // iter of match : iter of master ID masterContactID
                key = "matchIndex:" + String.valueOf(i) + " || " + "masterIndex:" + String.valueOf(j) + " || ContactID: " + tempMaster.getContactID();
                ratios = 0;
                ratios = FuzzySearch.ratio(tempMatch.getLastName(), tempMaster.getLastName());
                System.out.printf("Last Name: %5d | ", ratios);
                ratios = FuzzySearch.ratio(tempMatch.getMiddleName(), tempMaster.getMiddleName());
                System.out.printf("Middle Name: %5d | ", ratios);
                ratios = FuzzySearch.ratio(tempMatch.getFirstName(), tempMaster.getFirstName());
                System.out.printf("First Name: %5d | ", ratios);
                ratios = FuzzySearch.ratio(tempMatch.getEmail(), tempMaster.getEmail());
                System.out.printf("Email: %5d | ", ratios);
                ratios = FuzzySearch.ratio(tempMatch.getBusinessPhone(), tempMaster.getBusinessPhone());
                System.out.printf("Phone: %5d | ", ratios);
                ratios = FuzzySearch.ratio(tempMatch.getCity(), tempMaster.getCity());
                System.out.printf("City: %5d | ", ratios);
                ratios = FuzzySearch.ratio(tempMatch.getStateProvince(), tempMaster.getStateProvince());
                System.out.printf("State: %5d | ", ratios);
                ratios = FuzzySearch.ratio(tempMatch.getZip(), tempMaster.getZip());
                System.out.printf("Zip: %5d | ", ratios);
                ratios = FuzzySearch.ratio(tempMatch.getCountryID(), tempMaster.getCountryID());
                System.out.printf("Phone: %5d | ", ratios);
                System.out.println();
                //ratios = ratios/10;


//                if (max < ratios){
//                    max = ratios;
//                    matchMap.put(key, ratios);
//                    maxKey = key;
////                    System.out.println(key + " => "+matchMap.get(key));
//
//                }
            }
            //System.out.println(maxKey + " => "+matchMap.get(maxKey));
            //max = 0;

            // print or save to have visual presentation
            // ratio for each field again
            // split the key
            //FuzzySearch.ratio(match.get(matchIndex).getCountryID(), master.get(masterIndex).getCountryID());
            //FuzzySearch.ratio(match.get(matchIndex).getStateProvince(), master.get(masterIndex).getStateProvince());
            //FuzzySearch.ratio(match.get(matchIndex).getZip2(), master.get(masterIndex).getZip2());
            //
            //786.951677656 seconds
        }
//        System.out.println(matchMap);
        return matchMap;
    }

    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    !!!!!!!!!!!!!!! END COMMENT BLOCK FOR MAIN !!!!!!!!!!!!!!!!!!
    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! */


    /***
     *
     * @param master is the complete master
     * @param match it can be full match, but right now for testing is a list with no empty CRDs
     * @return
     */

    /*
    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    !!!!!!!!!!!!!!! START COMMENT BLOCK FOR METHODS !!!!!!!!!!!!!!!!!!
    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    public HashMap<String, ArrayList<Integer>> checkOnlyByCRD(ArrayList<Contact> master, ArrayList<Contact> match) {
        ArrayList<Integer> sumOfRatios = new ArrayList<Integer>();
        int i;
        int j;
        int lenOfMatch = match.size();
        int lenOfMaster = master.size();
        String key;
        Contact tempMatch;
        Contact tempMaster;
        HashMap<String, ArrayList<Integer>> matchMap = new HashMap<String, ArrayList<Integer>>();
//        ArrayList<Integer> lastName = new ArrayList<Integer>();
//        ArrayList<Integer> MiddleName = new ArrayList<Integer>();
//        ArrayList<Integer> FirstName = new ArrayList<Integer>();
//        ArrayList<Integer> emailAddres = new ArrayList<Integer>();
        ArrayList<Integer> ratios;// = new ArrayList<Integer>();
        ArrayList<Integer> ratios_temp = new ArrayList<Integer>();
        for (i = 0; i < lenOfMatch * 0.05; i++) {
            for (j = 0; j < lenOfMaster * 0.05; j++) {
                tempMatch = match.get(i);

//                This is for part 2 because there is repetion of keys
//                and we need to check with ratios_temp which ratio is best, but we need criteria
//                if (matchMap.containsKey(key)){
//
//                    matchMap.replace(key, ratios);
//                }else {

                tempMaster = master.get(j);

                key = String.valueOf(i) + ":" + String.valueOf(j) + "ID" + tempMaster.getContactID();

                ratios = new ArrayList<Integer>();
                ratios.add(FuzzySearch.ratio(tempMatch.getLastName(), tempMaster.getLastName()));
                ratios.add(FuzzySearch.ratio(tempMatch.getMiddleName(), tempMaster.getMiddleName()));
                ratios.add(FuzzySearch.ratio(tempMatch.getFirstName(), tempMaster.getFirstName()));
                ratios.add(FuzzySearch.ratio(tempMatch.getEmail(), tempMaster.getEmail()));
                ratios.add(FuzzySearch.ratio(tempMatch.getBusinessPhone(), tempMaster.getBusinessPhone()));
                ratios.add(FuzzySearch.ratio(tempMatch.getCity(), tempMaster.getCity()));
                ratios.add(FuzzySearch.ratio(tempMatch.getStateProvince(), tempMaster.getStateProvince()));
                ratios.add(FuzzySearch.ratio(tempMatch.getZip(), tempMaster.getZip()));
                ratios.add(FuzzySearch.ratio(tempMatch.getCountryID(), tempMaster.getCountryID()));

                // max = sum of current ratios
                // compare it
                // max final
                // 21450
                // we remove the match so we have 21449


                matchMap.put(key, ratios);
                System.out.println(key + " => " + matchMap.get(key));
//                }

            }

        }
//        System.out.println(matchMap);
        return matchMap;
    }


    public ArrayList<Integer> countEmptyCRDsReturnWithCRDs(ArrayList<Contact> list, String type) {
        int i;
        int count = 0;
        int len = list.size();
        ArrayList<Integer> withCRD = new ArrayList<Integer>();
        for (i = 0; i < len; i++) {
            if (list.get(i).getCRDNumber().equals("")) {
                count++;
            } else {
                withCRD.add(i);
            }
        }

        System.out.printf("The count is : %d / %d, %f for %s\n", count, len, ((double) count / (double) len) * 100.0, type);
        return withCRD;
    }

    public ArrayList<Integer> countEmptyCRDsReturnWithNoCRDs(ArrayList<Contact> list, String type) {
        int i;
        int count = 0;
        int len = list.size();
        ArrayList<Integer> withNoCRD = new ArrayList<Integer>();
        for (i = 0; i < len; i++) {
            if (list.get(i).getCRDNumber().equals("")) {
                count++;
                withNoCRD.add(i);
            }
        }

        System.out.printf("The count is : %d / %d, %f for %s\n", count, len, ((double) count / (double) len) * 100.0, type);
        return withNoCRD;
    }

    public void checkEqualCRDNotEmpty(ArrayList<Contact> list, ArrayList<Integer> index) {
        int i;
        int len = index.size();
        String key;
        HashMap<String, Integer> crds = new HashMap<>();
        for (i = 0; i < len; i++) {
            key = list.get(index.get(i)).getCRDNumber();
            if (crds.containsKey(key)) {
                crds.replace(key, crds.get(key) + 1);
            } else {
                crds.put(key, 1);
            }
        }
        len = len - 1;
        for (HashMap.Entry<String, Integer> entry : crds.entrySet()) {
            if (entry.getValue() > 1) {
//                System.out.printf("Key =  | %s | , Value = %d / %d, %f \n", entry.getKey(), entry.getValue(), len, ((double) entry.getValue() / (double) len) * 100.0);
            }
        }
//        System.out.printf("The count is : %d / %d, %f for %s\n", count, len, ((double) count/ (double) len) * 100.0, toCompare);

    }

!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
!!!!!!!!!!!!!!! END COMMENT BLOCK FOR METHODS !!!!!!!!!!!!!!!!!!
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! */
}
