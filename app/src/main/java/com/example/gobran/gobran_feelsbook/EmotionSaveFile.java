/*
 * Class: EmotionSaveFile
 *
 * Version: 1.0
 *
 * Date: October 3rd, 2018
 *
 * Description:
 *      Class that handles all interactions with the android file system, both reading and writing
 *      the record data.
 * Rationale:
 *      This class allows others to not have to deal with the actual method in which records are
 *      saved. As a result it separates concerns so that a new saving system could be implemented
 *      and the rest of the model wouldn't need any changes so long as the methods functioned the
 *      same as they did before.
 * Outstanding Issues:
 *      The cost of saving or loading all records as the only option seems high, preferably in the
 *      future better saving wherein individual records could be saved would be the goal
 */

package com.example.gobran.gobran_feelsbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class EmotionSaveFile {
    // The data for the where reading and writing data should be done to
    private static final String RECORD_FILENAME = "emotionSaveFile.sav";
    private File appFilePath;

    public EmotionSaveFile(File path) {
        this.appFilePath = path;
    }

    // Reads the emotion records saved at the location and returns an ArrayList of them
    @SuppressWarnings("unchecked")
    public ArrayList<EmotionRecord> readEmotionRecords() {
        ArrayList<EmotionRecord> emotionRecords = null;

        // Attempts to read any present emotion records file, reads it as the entire arrayList
        // being saved as a serializable ArrayList so that only one object must be read
        try {
            FileInputStream fis = new FileInputStream(new File(appFilePath, RECORD_FILENAME));
            ObjectInputStream ois = new ObjectInputStream(fis);

            emotionRecords = (ArrayList<EmotionRecord>) ois.readObject();

            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
          emotionRecords = new ArrayList<EmotionRecord>();
        } catch(Exception e) {
            throw new RuntimeException("Error reading records");
        }

        return emotionRecords;
    }

    // Writes the emotion records provided over any records stored already in the file
    public void writeEmotionRecords(ArrayList<EmotionRecord> emotionRecords) {

        // Attempts to write the ArrayList as a single serializable object in the file so that
        // it can easily be retrieved at a later date.
        try {
            FileOutputStream fos = new FileOutputStream(new File(appFilePath,RECORD_FILENAME));
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(emotionRecords);

            oos.close();
            fos.close();

        } catch(Exception e) {
            throw new RuntimeException("Error writing records");
        }
    }
}
