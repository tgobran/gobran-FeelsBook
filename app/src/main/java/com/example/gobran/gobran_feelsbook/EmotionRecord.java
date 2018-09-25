package com.example.gobran.gobran_feelsbook;

import java.util.Date;

public class EmotionRecord {
    private int id;
    private EmotionType emotion;
    private Date date;
    private String comment;

    public EmotionRecord(int identifier, EmotionType type, Date setDate, String setComment) {
        this.id = identifier;
        this.emotion = type;
        this.date = setDate;
        this.comment = setComment;
    }

    public int getId() { return id;}

    public EmotionType getEmotion() {
        return emotion;
    }

    public String getDate() {
        return date.toString();
    }

    public String getComment() {
        return comment;
    }

    public String toString() {
        return emotion.getStringForm() + "  " + date.toString() + "\n" + comment;
    }
}
