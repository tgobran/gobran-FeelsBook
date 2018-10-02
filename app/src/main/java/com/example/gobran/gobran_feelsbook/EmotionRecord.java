package com.example.gobran.gobran_feelsbook;

import java.io.Serializable;
import java.util.Calendar;

public class EmotionRecord implements Serializable {
    private int id;
    private Emotion emotion;
    private Calendar dateTime;
    private String comment;

    public EmotionRecord(int setId, Emotion setEmotion, Calendar setDateTime, String setComment) {
        this.id = setId;
        this.emotion = setEmotion;
        this.dateTime = setDateTime;
        this.comment = setComment;
    }

    public int getId() { return id; }
    public Emotion getEmotion() {
        return emotion;
    }
    public Calendar getDateTime() {
        return dateTime;
    }
    public String getDateTimeISO() {
        return String.format("%04d-%02d-%02dT%02d:%02d:%02d",dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH)+1,dateTime.get(Calendar.DAY_OF_MONTH),dateTime.get(Calendar.HOUR_OF_DAY),dateTime.get(Calendar.MINUTE),dateTime.get(Calendar.SECOND));
    }
    public String getComment() {
        return comment;
    }

    public void setComment(String newComment) { this.comment = newComment;}
}
