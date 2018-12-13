package wmu.datamatching;

import java.util.ArrayList;
import java.util.HashMap;

public class CompareContacts {


    protected ArrayList<MasterContact> masterSet;
    protected ArrayList<Contact> matchSet;
    protected ArrayList<Integer> fieldsToCompare;
    protected double subset;
    protected boolean print = true;

    public CompareContacts(MasterSet master, MatchSet match, ArrayList<Integer> fieldsToCompare) {

        this.masterSet = master.getContactList();
        this.matchSet = match.getContactList();
        this.fieldsToCompare = fieldsToCompare;
        this.subset = 1.0;

    }

    public CompareContacts(MasterSet master, MatchSet match, ArrayList<Integer> fieldsToCompare, double subset) {

        this.masterSet = master.getContactList();
        this.matchSet = match.getContactList();
        this.fieldsToCompare = fieldsToCompare;
        this.subset = subset;

    }

    public void setFieldsToCompare(ArrayList<Integer> fields) {
        this.fieldsToCompare = fields;
    }

    public void compareSets() {

        RecordMatcher matcher = new RecordMatcher();
        Contact tempMatch;
        MasterContact tempMaster;

        for (int masterContact = 0; masterContact < masterSet.size() * this.subset; masterContact++) {

            tempMaster = masterSet.get(masterContact);

            for (int matchContact = 0; matchContact < matchSet.size() * this.subset; matchContact++) {

                tempMatch = matchSet.get(matchContact);

                int confidence = matcher.compareFields(tempMatch.getFieldList(), tempMaster.getFieldList(),
                        "ratio", fieldsToCompare);

                confidence /= this.fieldsToCompare.size();


                //System.out.println("CONFIDENCE: " + confidence);

                tempMaster.setMatch(tempMatch.getContactID(), confidence);

            }

            if (print)
                tempMaster.printTop();
        }

    }
}

