package wmu.datamatching;

import java.util.*;

public class MasterContact extends Contact {

    private int MAX_MATCH_SIZE = 5;
    private ArrayList<Integer> topConfidence = new ArrayList<>(MAX_MATCH_SIZE);
    private ArrayList<String> topContactID = new ArrayList<>(MAX_MATCH_SIZE);


    private boolean checkMatch(int newMatch, int topMatch) {

        if (newMatch >= topMatch) {
            return true;
        }

        return false;

    }

    private int getIndex() {

        return 0;

    }

    public void setMatch(String contactID, int confidenceSum) {

        if (topContactID.isEmpty()) {

            topContactID.add(contactID);
            topConfidence.add(confidenceSum);

        }

        else if (topContactID.size() != MAX_MATCH_SIZE) {
            boolean lesserThan = true;
            for (int i = 0; i < topConfidence.size(); i++) {

                if (confidenceSum > topConfidence.get(i)) {
                    topContactID.add(i, contactID);
                    topConfidence.add(i, confidenceSum);
                    lesserThan = false;
                    break;
                }
            }
            if (lesserThan) {
                topContactID.add(contactID);
                topConfidence.add(confidenceSum);
            }

        }

        else {

            int MAX = MAX_MATCH_SIZE - 1;

            if (checkMatch(confidenceSum, topConfidence.get(MAX))) {

                for (int i = MAX; i >= 0 ; i--) {

                    if (confidenceSum > topConfidence.get(i)) {

                        if (i == 0) {
                            topConfidence.remove(MAX);
                            topContactID.remove(MAX);

                            topConfidence.add(0, confidenceSum);
                            topContactID.add(0, contactID);
                            break;
                        }
                    }
                    else if (confidenceSum == topConfidence.get(i)) {

                        if (i == MAX) {

                            topConfidence.remove(MAX);
                            topContactID.remove(MAX);

                            topConfidence.add(i, confidenceSum);
                            topContactID.add(i, contactID);
                            break;

                        } else {

                            topConfidence.remove(MAX);
                            topContactID.remove(MAX);

                            topConfidence.add(i + 1, confidenceSum);
                            topContactID.add(i + 1, contactID);
                            break;

                        }
                    }
                    else if (confidenceSum < topConfidence.get(i)) {
                        topConfidence.remove(MAX);
                        topContactID.remove(MAX);

                        topConfidence.add(i + 1, confidenceSum);
                        topContactID.add(i + 1, contactID);
                        break;
                    }
                }
            }
        }
    }

    public ArrayList<String> getTopContactID() {
        return this.topContactID;
    }

    public ArrayList<Integer> getTopConfidence() {
        return this.topConfidence;
    }

    public void set_MAX_MATCH_SIZE(int size) {
        this.MAX_MATCH_SIZE = size;
        topConfidence = new ArrayList<>(size);
        topContactID = new ArrayList<>(size);
    }

    public int get_MAX_MATCH_SIZE() {
        return this.MAX_MATCH_SIZE;
    }

    public void printTop() {
        System.out.println(this.FirstName + " - TOP " + MAX_MATCH_SIZE + " MATCHES");
        for (int i = 0; i < topConfidence.size(); i++) {
            System.out.println("\t" + i + ": " + this.topContactID.get(i) + " | Confidence: " + topConfidence.get(i));
        }
        System.out.println();
    }

}

