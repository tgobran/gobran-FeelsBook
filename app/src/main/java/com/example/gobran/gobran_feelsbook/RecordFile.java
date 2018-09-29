package com.example.gobran.gobran_feelsbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class RecordFile {
    private static final String RECORD_FILENAME = "emotionRecordData.sav";
    private File appFilePath;

    public RecordFile(File path) {
        this.appFilePath = path;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<EmotionRecord> readRecords() {
        ArrayList<EmotionRecord> records = null;
        try {
            FileInputStream fis = new FileInputStream(new File(appFilePath,RECORD_FILENAME));
            ObjectInputStream ois = new ObjectInputStream(fis);

            records = (ArrayList<EmotionRecord>)ois.readObject();

            ois.close();
            fis.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return records;
    }

    public void writeRecords(ArrayList<EmotionRecord> records) {
        try {
            FileOutputStream fos = new FileOutputStream(new File(appFilePath,RECORD_FILENAME));
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(records);

            oos.close();
            fos.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
