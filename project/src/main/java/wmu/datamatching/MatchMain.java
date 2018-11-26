package wmu.datamatching;

public class MatchMain {

    public static void main(String[] args) {

        String masterpath = "./data/clean/master.csv";
        String matchpath = "./data/clean/match.csv";

        ParseMaster master = new ParseMaster();
        master.readCSV(masterpath);
        master.head();

        ParseMatch match = new ParseMatch();
        match.readCSV(matchpath);
        match.head();

    }

}
