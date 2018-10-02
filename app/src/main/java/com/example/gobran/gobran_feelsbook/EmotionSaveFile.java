package com.example.gobran.gobran_feelsbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class EmotionSaveFile {
    private static final String RECORD_FILENAME = "emotionSaveFile.sav";
    private File appFilePath;

    public EmotionSaveFile(File path) {
        this.appFilePath = path;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<EmotionRecord> readEmotionRecords() {
        ArrayList<EmotionRecord> emotionRecords = null;
        try {
            FileInputStream fis = new FileInputStream(new File(appFilePath,RECORD_FILENAME));
            ObjectInputStream ois = new ObjectInputStream(fis);

            emotionRecords = (ArrayList<EmotionRecord>)ois.readObject();

            ois.close();
            fis.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return emotionRecords;
    }

    public void writeEmotionRecords(ArrayList<EmotionRecord> emotionRecords) {
        try {
            FileOutputStream fos = new FileOutputStream(new File(appFilePath,RECORD_FILENAME));
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(emotionRecords);

            oos.close();
            fos.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
