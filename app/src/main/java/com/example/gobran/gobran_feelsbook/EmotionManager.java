package com.example.gobran.gobran_feelsbook;

import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

public class EmotionManager {
    private EmotionSaveFile emotionSaveFile;

    private ArrayList<EmotionRecord> emotionRecords;
    private ArrayList<Integer> emotionCounts;

    private int lastEmotionId;

    public EmotionManager(File filePath) {
        emotionSaveFile = new EmotionSaveFile(filePath);
        emotionRecords = emotionSaveFile.readEmotionRecords();
        if (emotionRecords == null) {
            emotionRecords = new ArrayList<EmotionRecord>();
        }

        emotionCounts = new ArrayList<Integer>();
        for(int i = 0; i < Emotion.values().length; i++) {
            emotionCounts.add(0);
        }
        for (EmotionRecord emotionRecord: emotionRecords) {
            emotionCounts.set(emotionRecord.getEmotion().getId(),emotionCounts.get(emotionRecord.getEmotion().getId())+1);
        }
        recalculateLastEmotionId();
    }

    public ArrayList<Integer> getEmotionCounts() {
        return emotionCounts;
    }
    public ArrayList<EmotionRecord> getEmotionRecords() {
        return emotionRecords;
    }

    public void addEmotionRecord(Emotion emotion, Calendar dateTime, String comment) {
        lastEmotionId++;
        emotionCounts.set(emotion.getId(),emotionCounts.get(emotion.getId())+1);
        emotionRecords.add(calculateRecordIndex(dateTime),new EmotionRecord(lastEmotionId,emotion,dateTime,comment));
        emotionSaveFile.writeEmotionRecords(emotionRecords);

    }
    public void editEmotionRecord(int recordId, Emotion emotion, Calendar dateTime, String comment) {
        emotionRecords.remove(getRecordIndex(recordId));
        emotionRecords.add(calculateRecordIndex(dateTime),new EmotionRecord(recordId,emotion,dateTime,comment));
        emotionSaveFile.writeEmotionRecords(emotionRecords);
    }
    public void deleteEmotionRecord(int recordId) {
        int recordIndex = getRecordIndex(recordId);
        emotionCounts.set(emotionRecords.get(recordIndex).getEmotion().getId(),emotionCounts.get(emotionRecords.get(recordIndex).getEmotion().getId())-1);
        emotionRecords.remove(recordIndex);
        if(lastEmotionId == recordId) {
            recalculateLastEmotionId();
        }
        emotionSaveFile.writeEmotionRecords(emotionRecords);
    }

    private int getRecordIndex(int recordId) {
        int recordIndex = 0;
        while(emotionRecords.size() > recordIndex) {
            if(emotionRecords.get(recordIndex).getId() == recordId) {
                return recordIndex;
            }
            recordIndex++;
        }
        return -1;
    }
    private int calculateRecordIndex(Calendar dateTime) {
        int recordIndex = 0;
        while(emotionRecords.size() > recordIndex && dateTime.compareTo(emotionRecords.get(recordIndex).getDateTime()) < 0) {
            recordIndex++;
        }
        return recordIndex;
    }
    private void recalculateLastEmotionId() {
        lastEmotionId = 0;
        for (EmotionRecord emotionRecord: emotionRecords) {
            if (lastEmotionId < emotionRecord.getId()) {
                lastEmotionId = emotionRecord.getId();
            }
        }
    }
    public EmotionRecord getLastCreatedEmotionRecord() {
        int recordIndex = getRecordIndex(lastEmotionId);
        if (recordIndex != -1) {
            return emotionRecords.get(recordIndex);
        }
        return null;
    }
}
