package com.example.gobran.gobran_feelsbook;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

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

    public void addRecord(EmotionType type, Date date, String comment) {
        EmotionRecord record = new EmotionRecord(type, date, comment);
        records.add(0,record);
        recordFile.writeRecords(records);
        counts[type.toId()]++;
    }

    public void deleteRecord(int index) {
        records.remove(index);
        recordFile.writeRecords(records);
    }

}
