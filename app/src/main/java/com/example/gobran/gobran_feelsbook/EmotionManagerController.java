package com.example.gobran.gobran_feelsbook;

import java.util.ArrayList;
import java.util.Date;

public class EmotionManagerController {
    private EmotionManager emotionManager = null;

    public EmotionManagerController(EmotionManager em) {
        this.emotionManager = em;
    }

    public ArrayList<EmotionRecord> getRecords() {
        return emotionManager.getRecords();
    }

    public Integer[] getCounts() {
        return emotionManager.getCounts();
    }

    public void addRecord(EmotionType type, Date date, String comment) {
        emotionManager.addRecord(type,date,comment);
    }
    public void editRecord(int index, EmotionType type, Date date, String comment) {
        emotionManager.deleteRecord(index);
        emotionManager.addRecord(type,date,comment);
    }
}
