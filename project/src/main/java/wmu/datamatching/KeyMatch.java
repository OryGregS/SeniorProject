package wmu.datamatching;

import java.util.ArrayList;

public class KeyMatch {
    public int masterIndex;
    public int matchIndex;
    public int maxValue;
    public String contactID;
    public ArrayList<Integer> top5matchesIndexes;
    public ArrayList<Integer> top5matchesMax;

    public KeyMatch(int masterIndex, int matchIndex, int maxValue, String contactID, ArrayList<Integer> top5matchesIndexes, ArrayList<Integer> top5matchesMax) {
        this.masterIndex = masterIndex;
        this.contactID = contactID;
        this.matchIndex = matchIndex;
        this.maxValue = maxValue;
        this.top5matchesIndexes = top5matchesIndexes;
        this.top5matchesMax = top5matchesMax;
    }

    public KeyMatch(int masterIndex, int matchIndex, int maxValue, String contactID) {
        this.masterIndex = masterIndex;
        this.contactID = contactID;
        this.matchIndex = matchIndex;
        this.maxValue = maxValue;

    }

    public KeyMatch(KeyMatch o) {
        this.masterIndex = o.masterIndex;
        this.contactID = o.contactID;
        this.matchIndex = o.matchIndex;
        this.maxValue = o.maxValue;
    }
}
