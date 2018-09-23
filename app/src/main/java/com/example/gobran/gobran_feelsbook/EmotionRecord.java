package com.example.gobran.gobran_feelsbook;

import java.util.Date;

public class EmotionRecord {
    private EmotionType emotion;
    private Date date;
    private String comment;

    public EmotionRecord(EmotionType type, Date setDate, String setComment) {
        this.emotion = type;
        this.date = setDate;
        this.comment = setComment;
    }

    public EmotionType getEmotion() {
        return emotion;
    }
    public void setEmotion(EmotionType newType) {
        this.emotion = newType;
    }

    public String getDate() {
        return date.toString();
    }
    public void setDate(Date newDate) {
        this.date = newDate;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String newComment) {
        this.comment = newComment;
    }
}
