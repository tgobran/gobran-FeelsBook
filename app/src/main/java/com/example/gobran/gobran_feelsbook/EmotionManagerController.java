/*
 * Class: EmotionManagerController
 *
 * Version: 1.0
 *
 * Date: October 3rd, 2018
 *
 * Description:
 *      Controller class for the emotion manager, allows layer of abstraction between the GUI and
 *      the actual model when changes are made.
 * Rationale:
 *      This class provides a barrier between the EmotionManager and any changes or requests that
 *      might be made by a GUI. As a result the underlying model could be heavily changed or more
 *      protections could be made and the GUI would not be broken, the opposite applies as well
 * Outstanding Issues:
 *      The getters within this class means it does not perform entirely just as a controller and
 *      in the future proper implementation of views within the model would be important
 */

package com.example.gobran.gobran_feelsbook;

import java.util.ArrayList;
import java.util.Calendar;

public class EmotionManagerController {
    private EmotionManager emotionManager;

    // Creates the controller with a corresponding emotionManager to handle
    public EmotionManagerController(EmotionManager em) {
        this.emotionManager = em;
    }

    // Retrieves the emotion records for presentation by the GUI
    public ArrayList<EmotionRecord> getEmotionRecords() { return emotionManager.getEmotionRecords(); }
    // Retrieves the emotion counts for presentation by the GUI
    public ArrayList<Integer> getEmotionCounts() {
        return emotionManager.getEmotionCounts();
    }
    // Retrieves the last created emotion for presentation by the GUI
    public EmotionRecord getLastCreatedEmotionRecord() { return emotionManager.getLastCreatedEmotionRecord(); }

    // Provides a barrier for adding emotion records between the GUI and the model
    public void addEmotionRecord(Emotion emotion, Calendar dateTime, String comment) {
        emotionManager.addEmotionRecord(emotion,dateTime,comment);
    }
    // Provides a barrier for editing emotion records between the GUI and the model
    public void editEmotionRecord(int recordId, Emotion emotion, Calendar dateTime, String comment) {
        emotionManager.editEmotionRecord(recordId,emotion,dateTime,comment);
    }
    // Provides a barrier for deleting emotion records between the GUI and the model
    public void deleteEmotionRecord(int recordId) {
        emotionManager.deleteEmotionRecord(recordId);
    }
}
