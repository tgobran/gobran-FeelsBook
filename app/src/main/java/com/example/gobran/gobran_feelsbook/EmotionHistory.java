package com.example.gobran.gobran_feelsbook;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;

public class EmotionHistory {
    private EmotionCounter counter;
    private Context context;

    private PrimitiveData primitiveData;
    private RecordData recordData;

    private int nextId;

    private ArrayList<EmotionRecord> records;

    public EmotionHistory(Context ctx) {
        context = ctx;

        primitiveData = new PrimitiveData(ctx);
        recordData = new RecordData(ctx);
        counter = new EmotionCounter(primitiveData);

        records = recordData.readRecords();
        nextId = primitiveData.getData(context.getString(R.string.prefsNextId));
    }

    public ArrayList<Integer> getCounts() {
        ArrayList<Integer> counts = new ArrayList<Integer>();
        for(EmotionType type: EmotionType.values()) {
            counts.add(counter.retrieveCount(type));
        }
        return counts;
    }

    public ArrayList<EmotionRecord> getRecords() {
        return records;
    }

    public void addRecord(EmotionType type, Date date, String comment) {
        EmotionRecord record = new EmotionRecord(nextId, type, date, comment);
        records.add(record);
        recordData.writeRecord(record);
        counter.updateCount(type,1);
        primitiveData.saveData(context.getString(R.string.prefsFileKey),nextId);
    }

    public void editRecord(int id, EmotionType type, Date date, String comment) {
    }

    public void deleteRecord(int id) {
    }

}
