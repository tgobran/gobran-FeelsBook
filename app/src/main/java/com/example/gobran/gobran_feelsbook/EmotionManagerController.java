package com.example.gobran.gobran_feelsbook;

import java.util.ArrayList;
import java.util.Calendar;

public class EmotionManagerController {
    private EmotionManager emotionManager;

    public EmotionManagerController(EmotionManager em) {
        this.emotionManager = em;
    }

    public ArrayList<EmotionRecord> getEmotionRecords() { return emotionManager.getEmotionRecords(); }
    public ArrayList<Integer> getEmotionCounts() {
        return emotionManager.getEmotionCounts();
    }
    public EmotionRecord getLastCreatedEmotionRecord() { return emotionManager.getLastCreatedEmotionRecord(); }

    public void addEmotionRecord(Emotion emotion, Calendar dateTime, String comment) {
        emotionManager.addEmotionRecord(emotion,dateTime,comment);
    }
    public void editEmotionRecord(int recordId, Emotion emotion, Calendar dateTime, String comment) {
        emotionManager.editEmotionRecord(recordId,emotion,dateTime,comment);
    }
    public void deleteEmotionRecord(int recordId) {
        emotionManager.deleteEmotionRecord(recordId);
    }
}
