package com.example.gobran.gobran_feelsbook;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

public class EmotionManager {
    private RecordFile recordFile;

    private ArrayList<EmotionRecord> records;
    private Integer[] counts;

    public EmotionManager(File filePath) {
        recordFile = new RecordFile(filePath);
        records = recordFile.readRecords();
        if (records == null) {
            records = new ArrayList<EmotionRecord>();
        }

        counts = new Integer[EmotionType.values().length];
        for(int i = 0; i < EmotionType.values().length; i++) {
            counts[i] = 0;
        }

        for (EmotionRecord record: records) {
            counts[record.getEmotion().toId()]++;
        }
    }

    public Integer[] getCounts() {
        return counts;
    }
    public ArrayList<EmotionRecord> getRecords() {
        return records;
    }

    public void addRecord(EmotionType type, Calendar dateTime, String comment) {
        EmotionRecord record = new EmotionRecord(type, dateTime, comment);
        int index = 0;
        while(records.size() > index && dateTime.compareTo(records.get(index).getDateTime()) < 0) {
            index++;
        }
        records.add(index,record);
        recordFile.writeRecords(records);
        counts[type.toId()]++;
    }

    public void deleteRecord(int index) {
        counts[records.get(index).getEmotion().toId()]--;
        records.remove(index);
        recordFile.writeRecords(records);
    }

}
