/*
 * Class: EmotionManager
 *
 * Version: 1.0
 *
 * Date: October 3rd, 2018
 *
 * Description:
 *      The main manager class for the emotion recording model, it handles the maintenance and
 *      retrieval of the emotion records and their counts as well as the addition, editing, and
 *      deletion of new emotions.
 * Rationale:
 *      This class allows a common entry point for operations performed on the emotion recording
 *      system. Any controller elements will retrieve information from this in order to edit any
 *      changes from the model and almost all view data is retrieved through this.
 *      With this design this class can handle the higher level maintenance of the records will
 *      accessing other classes that perform the specific operations. As such its main concerns
 *      can function as presenting an maintaining all records at the current moment, with their data
 *      held within and their saving/loading from files being handled by other classes that are
 *      interacted with.
 * Outstanding Issues:
 *      The large scope of the class and the multiple different things it handles present slight
 *      concerns, moving forward breaking this class up so that counting and the emotion records
 *      can be better hidden would be one goal. Specifically how the records are passed to GUI
 *      elements feels to intrusive.
 */

package com.example.gobran.gobran_feelsbook;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

public class EmotionManager {
    // The save manager that the records will be read/written to
    private EmotionSaveFile emotionSaveFile;

    // The collections of emotion records and counts for each emotion type
    private ArrayList<EmotionRecord> emotionRecords;
    private ArrayList<Integer> emotionCounts;

    // An id kept so that newly created emotions will have unique id's
    private int lastEmotionId;

    // The constructor for the manager, the filepath for the application is required to build the
    // save manager.
    public EmotionManager(File filePath) {

        // Handles the creation of the save manager and the reading in of any existing saved record
        // data for the application
        emotionSaveFile = new EmotionSaveFile(filePath);
        emotionRecords = emotionSaveFile.readEmotionRecords();
        if (emotionRecords == null) {
            emotionRecords = new ArrayList<EmotionRecord>();
        }

        // The creation of the list of counts for each emotion type, counts are based on any
        // saved emotions found.
        emotionCounts = new ArrayList<Integer>();
        for(int i = 0; i < Emotion.values().length; i++) {
            emotionCounts.add(0);
        }
        for (EmotionRecord emotionRecord: emotionRecords) {
            emotionCounts.set(emotionRecord.getEmotion().getId(),emotionCounts.get(emotionRecord.getEmotion().getId())+1);
        }

        // The last id variable is calculated to be up to date with the saved records found
        calculateLastEmotionId();
    }

    // Returns the emotion counts ArrayList for presentation within views - ArrayAdapters
    public ArrayList<Integer> getEmotionCounts() {
        return emotionCounts;
    }
    // Returns the emotion records ArrayList for presenting records within views - ArrayAdapters
    public ArrayList<EmotionRecord> getEmotionRecords() {
        return emotionRecords;
    }

    // Handles the adding of another emotion record by the user, receiving the type, dateTime it
    // has and any comment. Creates the actual record with a specific id and places the record
    // into the record ArrayList in order of date, then immediately saves the changes to the file
    public void addEmotionRecord(Emotion emotion, Calendar dateTime, String comment) {
        lastEmotionId++;
        emotionCounts.set(emotion.getId(),emotionCounts.get(emotion.getId())+1);
        emotionRecords.add(calculateRecordIndex(dateTime),new EmotionRecord(lastEmotionId,emotion,dateTime,comment));
        emotionSaveFile.writeEmotionRecords(emotionRecords);

    }
    // Handles the editing of an emotion record by the user, sets the record specified by the id to
    // now have the given emotion type, dateTime and comment. To do this its index is calculated
    // by its id and it is removed from the records ArrayList before placed back with the changes saved
    public void editEmotionRecord(int recordId, Emotion emotion, Calendar dateTime, String comment) {
        emotionRecords.remove(getRecordIndex(recordId));
        emotionRecords.add(calculateRecordIndex(dateTime),new EmotionRecord(recordId,emotion,dateTime,comment));
        emotionSaveFile.writeEmotionRecords(emotionRecords);
    }
    // Handles the deletion of an emotion record by the user, finds the given records index by the
    // provided id and proceeds to remove the record, decrement the emotion counts and save the
    // changes to the file. If it has the previous last id set, then that value is recalculated
    public void deleteEmotionRecord(int recordId) {
        int recordIndex = getRecordIndex(recordId);
        emotionCounts.set(emotionRecords.get(recordIndex).getEmotion().getId(),emotionCounts.get(emotionRecords.get(recordIndex).getEmotion().getId())-1);
        emotionRecords.remove(recordIndex);
        if(lastEmotionId == recordId) {
            calculateLastEmotionId();
        }
        emotionSaveFile.writeEmotionRecords(emotionRecords);
    }

    // Retrieves an emotion records index in the records ArrayList by using the provided id
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
    // Calculates an emotions position in the records ArrayList by using its dateTime in relation
    // to other records
    private int calculateRecordIndex(Calendar dateTime) {
        int recordIndex = 0;
        while(emotionRecords.size() > recordIndex && dateTime.compareTo(emotionRecords.get(recordIndex).getDateTime()) < 0) {
            recordIndex++;
        }
        return recordIndex;
    }
    // Handles calculation of the last what the last emotion id that still exists is, this is used
    // to retrieve previously created emotions when their dateTime might be manipulated
    private void calculateLastEmotionId() {
        lastEmotionId = 0;
        for (EmotionRecord emotionRecord: emotionRecords) {
            if (lastEmotionId < emotionRecord.getId()) {
                lastEmotionId = emotionRecord.getId();
            }
        }
    }
    // Retrieves the last emotion record created based on its id
    public EmotionRecord getLastCreatedEmotionRecord() {
        int recordIndex = getRecordIndex(lastEmotionId);
        if (recordIndex != -1) {
            return emotionRecords.get(recordIndex);
        }
        return null;
    }
}
