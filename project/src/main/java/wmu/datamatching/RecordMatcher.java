package wmu.datamatching;

import java.util.ArrayList;

public class RecordMatcher {


    private ArrayList<MasterContact> masterSet;
    private ArrayList<Contact> matchSet;
    private ArrayList<Integer> fieldsToCompare;
    private double subset;
    private boolean print;

    /**
     * Initializes the RecordMatcher object with a master set,
     * a match set, and the fields to compare between records.
     * @param master - Master dataset
     * @param match - dataset to match to master
     * @param fieldsToCompare - list of fields to compare between records
     */
    public RecordMatcher(MasterSet master, MatchSet match, ArrayList<Integer> fieldsToCompare) {

        this.masterSet = master.getContactList();
        this.matchSet = match.getContactList();
        this.fieldsToCompare = fieldsToCompare;
        this.subset = 1.0;
        this.print = true;

    }

    /**
     * Initializes the RecordMatcher object with a master set,
     * a match set, the fields to compare between records, and
     * allows a subset of the datasets to be used.
     * @param master - Master dataset
     * @param match - dataset to match to master
     * @param fieldsToCompare - list of fields to compare between records
     * @param subset - value to specify what percentage of the data should be used
     */
    public RecordMatcher(MasterSet master, MatchSet match, ArrayList<Integer> fieldsToCompare, double subset) {

        this.masterSet = master.getContactList();
        this.matchSet = match.getContactList();
        this.fieldsToCompare = fieldsToCompare;
        this.subset = subset;
        this.print = true;

    }

    /**
     * Prints the top matches for each
     * master contact after all comparisons
     * @param print
     */
    public void printRun(boolean print) {
        this.print = print;
    }

    /**
     * Loops through the datasets to calculate
     * the similarity between the contacts in Master
     * and the contacts in the dataset to match
     */
    public void run() {

        CalcSim calcSim = new CalcSim();

        for (MasterContact masterContact: masterSet) {

            for (Contact matchContact: matchSet) {


                int confidence = calcSim.compareFields(masterContact, matchContact,
                        "ratio", fieldsToCompare);


                masterContact.setMatch(matchContact, confidence);

            }

            if (print)
                masterContact.printTop();
        }
    }
}

