package wmu.datamatching;

import java.util.ArrayList;

public class MatchMain {

    public static void main(String[] args) {

        long start = System.nanoTime();

        DataLoader loader = new DataLoader();
        loader.loadDataFromCSV("./data/contact_master.csv",
                "./data/contact_match.csv");
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

        RecordMatcher matcher = new RecordMatcher(master, match, fieldsToCompare, 0.1);
        matcher.printRun(true);
        matcher.run();

        long end = System.nanoTime();
        double time =  ( (end-start) / 1000000000.0 );

        if (time > 120) {
            time = time / 60;
            System.out.println("\nTime taken: " + time + " minutes\n");
        } else {
            System.out.println("\nTime taken: " + time + " seconds\n");
        }



//        for (int i = 0; i < master.getContactList().size(); i++) {
//            master.getContactList().get(i).printTop();
//        }



    }

}
