package com.example.gobran.gobran_feelsbook;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.EnumMap;

public class EmotionManagerController {
    private EmotionManager emotionManager;

    public EmotionManagerController(EmotionManager em) {
        this.emotionManager = em;
    }

    public ArrayList<EmotionRecord> getRecords() {
        return emotionManager.getRecords();
    }

    public ArrayList<Integer> getCounts() {
        return emotionManager.getCounts();
    }

    public void addRecord(EmotionType type, Calendar dateTime, String comment) {
        emotionManager.addRecord(type,dateTime,comment);
    }
    public void editRecord(int index, EmotionType type, Calendar dateTime, String comment) {
        emotionManager.deleteRecord(index);
        emotionManager.addRecord(type,dateTime,comment);
    }
    public void deleteRecord(int index) {
        emotionManager.deleteRecord(index);
    }
}
