package wmu.datamatching;

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

    }

}
