package com.example.gobran.gobran_feelsbook;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;

public class EmotionHistory {
    private ArrayList<EmotionRecord> emotionRecords = new ArrayList<EmotionRecord>();

    private FileOutputStream fileOutStream;
    private FileInputStream  fileInStream;
    private Context context;

    public EmotionHistory(Context ctx) {
        context = ctx;
    }

    private void readHistoryFile() {
        try {
            fileInStream = context.openFileInput(context.getString(R.string.historyFile));
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInStream));
            String line = reader.readLine();

            while(line != null) {
                String[] lineParts = line.split(context.getString(R.string.fileSeparator));
                EmotionType type = EmotionType.values()[Integer.getInteger(lineParts[1])];
                Date date = new Date();
                date.setTime(Long.getLong(lineParts[2]));
                EmotionRecord newRecord = new EmotionRecord(type, date, lineParts[3]);

                line = reader.readLine();
            }
            reader.close();
            fileInStream.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void writeToHistoryFile(EmotionRecord record) {
        try {
            fileOutStream = context.openFileOutput(context.getString(R.string.historyFile), Context.MODE_PRIVATE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fileOutStream));

            int i = 0;
            String line = "";

            line += record.getEmotion().getStringForm() + context.getString(R.string.fileSeparator) + record.getDate() + context.getString(R.string.fileSeparator) + record.getComment() + "\n";
            writer.write(line,0,line.length());
            i++;

            writer.close();
            fileOutStream.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void editHistoryFile() {

    }

    private void deleteFromHistory() {

    }

    public void addRecord(EmotionType type, Date date, String comment) {
        EmotionRecord record = new EmotionRecord(type,date,comment);
        emotionRecords.add(record);
        writeToHistoryFile(record);
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
