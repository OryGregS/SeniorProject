package wmu.datamatching;

public class MatchMain {

    public static void main(String[] args) {

        String masterpath = "./data/contact_master.csv";
        String matchpath = "./data/contact_match.csv";

        ParseMaster master = new ParseMaster();
        master.readCSV(masterpath);
        master.head();

        ParseMatch match = new ParseMatch();
        match.readCSV(matchpath);
        match.head();

    }

}
