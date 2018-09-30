package com.example.gobran.gobran_feelsbook;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EnumMap;
import java.util.Map;

public class EmotionManager {
    private RecordFile recordFile;

    private ArrayList<EmotionRecord> records;
    private ArrayList<Integer> counts;

    public EmotionManager(File filePath) {
        recordFile = new RecordFile(filePath);
        records = recordFile.readRecords();
        if (records == null) {
            records = new ArrayList<EmotionRecord>();
        }

        counts = new ArrayList<Integer>();
        for(int i = 0; i < EmotionType.values().length; i++) {
            counts.add(0);
        }
        for (EmotionRecord record: records) {
            counts.set(record.getEmotion().toId(),counts.get(record.getEmotion().toId())+1);

        }
    }

    public ArrayList<Integer> getCounts() {
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
        counts.set(type.toId(),counts.get(type.toId())+1);
    }

    public void deleteRecord(int index) {
        counts.set(records.get(index).getEmotion().toId(),counts.get(records.get(index).getEmotion().toId())-1);
        records.remove(index);
        recordFile.writeRecords(records);
    }

}
