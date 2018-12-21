package wmu.datamatching;


import java.util.ArrayList;

public class MatchMaker {


    public void compareMasterToMaster(String masterPath, ArrayList<Integer> fieldsToCompare) {
        DataLoader loader = new DataLoader();
        loader.loadDataFromCSV(masterPath, masterPath);
        MasterSet master = loader.getMasterSet();
        MatchSet match_master = loader.getMatchSet();

        RecordMatcher matcher = new RecordMatcher(master, match_master, fieldsToCompare, true);
        matcher.printRun(true);
        matcher.run();

    }

    public void compareMasterToOther(String masterPath, String matchPath, ArrayList<Integer> fieldsToCompare) {
        DataLoader loader = new DataLoader();
        loader.loadDataFromCSV(masterPath, matchPath);
        MasterSet master = loader.getMasterSet();
        MatchSet match = loader.getMatchSet();

        RecordMatcher matcher = new RecordMatcher(master, match, fieldsToCompare, false);
        matcher.printRun(true);
        matcher.run();
    }
}
