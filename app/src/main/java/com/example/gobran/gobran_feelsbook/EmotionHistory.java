package com.example.gobran.gobran_feelsbook;

import java.util.ArrayList;
import java.util.Date;

public class EmotionHistory {
    private ArrayList<EmotionRecord> emotionRecords = new ArrayList<EmotionRecord>();

    public void addRecord(EmotionType type, Date date, String comment) {
        emotionRecords.add(new EmotionRecord(type, date, comment));
    }

    public void deleteRecord(Integer index) {
        if (index > -1 && index < emotionRecords.size()) {
            emotionRecords.remove(index);
        }
    }

    public EmotionRecord getRecord(Integer index) {
        if (index > -1  && index < emotionRecords.size()) {
            return emotionRecords.get(index);
        }
        return null;
    }
}
