/*
 * Class: EmotionRecord
 *
 * Version: 1.0
 *
 * Date: October 3rd, 2018
 *
 * Description:
 *      Class for each emotion record to contain its id, type, dateTime, comment and how those
 *      items can be retrieved by outside classes
 * Rationale:
 *      This class allows for emotion records to be created and used from the outside will important
 *      elements of presentation and storage can be handled within. Specifically the presentation
 *      of the ISO format can be handled within and so all other classes do not need to worry about
 *      the handling of that.
 * Outstanding Issues:
 *      This class feels relatively sparse, with the simplicity of each emotion record it does not
 *      really hide too much, furthermore in the future the presentation of the ISO format should
 *      be handled by a class related to dateTime rather than the broader record class
 */

package com.example.gobran.gobran_feelsbook;

import java.io.Serializable;
import java.util.Calendar;

public class EmotionRecord implements Serializable {
    // Data items held by the emotion record
    private int id;
    private Emotion emotion;
    private Calendar dateTime;
    private String comment;

    // Constructor for the record wherein these values are set
    public EmotionRecord(int setId, Emotion setEmotion, Calendar setDateTime, String setComment) {
        this.id = setId;
        this.emotion = setEmotion;
        this.dateTime = setDateTime;
        this.comment = setComment;
    }

    // Retrieves the id value for the record
    public int getId() { return id; }
    // Retrieves the emotion value for the record
    public Emotion getEmotion() {
        return emotion;
    }
    // Retrieves the dateTime for the record
    public Calendar getDateTime() {
        return dateTime;
    }
    // Retrieves an ISO8601 string representation of the emotions dateTime
    public String getDateTimeISO() {
        return String.format("%04d-%02d-%02dT%02d:%02d:%02d",dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH)+1,dateTime.get(Calendar.DAY_OF_MONTH),dateTime.get(Calendar.HOUR_OF_DAY),dateTime.get(Calendar.MINUTE),dateTime.get(Calendar.SECOND));
    }
    // Retrieves the comment set for the emotion
    public String getComment() {
        return comment;
    }

    // Sets a new comment for the emotion,
    public void setComment(String newComment) { this.comment = newComment;}
}
