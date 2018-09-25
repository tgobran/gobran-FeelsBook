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

public class RecordData {
    private FileOutputStream fileOutStream;
    private FileInputStream fileInStream;
    private Context context;

    public RecordData(Context ctx) {
        context = ctx;
    }

    public ArrayList<EmotionRecord> readRecords() {
        ArrayList<EmotionRecord> records = new ArrayList<EmotionRecord>();
        try {
            fileInStream = context.openFileInput(context.getString(R.string.historyFile));
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInStream));

            String line = reader.readLine();
            while(line != null) {
                records.add(readRecordLine(line));
                line = reader.readLine();
            }
            reader.close();
            fileInStream.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return records;
    }

    public void writeRecords(ArrayList<EmotionRecord> records) {
        try {
            fileOutStream = context.openFileOutput(context.getString(R.string.historyFile), Context.MODE_PRIVATE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fileOutStream));

            for(EmotionRecord record: records) {
                String line = createRecordLine(record);
                writer.write(line,0,line.length());
            }

            writer.close();
            fileOutStream.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void writeRecord(EmotionRecord record) {
        try {
            fileOutStream = context.openFileOutput(context.getString(R.string.historyFile), Context.MODE_PRIVATE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fileOutStream));

            String line = createRecordLine(record);
            writer.write(line,0,line.length());
            writer.close();
            fileOutStream.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private EmotionRecord readRecordLine(String line) {
        String[] lineParts = line.split(context.getString(R.string.fileSeparator));
        int id = Integer.getInteger(lineParts[0]);
        EmotionType type = EmotionType.values()[Integer.getInteger(lineParts[1])];
        Date date = new Date();
        date.setTime(Long.getLong(lineParts[2]));
        return new EmotionRecord(id,type,date,lineParts[3]);
    }
    private String createRecordLine(EmotionRecord record) {
        String line = record.getEmotion().getStringForm() + context.getString(R.string.fileSeparator) + record.getDate() + context.getString(R.string.fileSeparator) + record.getComment() + "\n";
        return line;
    }
}
