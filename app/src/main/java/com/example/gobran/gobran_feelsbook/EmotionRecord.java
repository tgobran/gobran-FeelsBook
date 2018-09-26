package com.example.gobran.gobran_feelsbook;

import java.io.Serializable;
import java.util.Date;

public class EmotionRecord implements Serializable {
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

    public String getDate() {
        return date.toString();
    }

    public String getComment() {
        return comment;
    }

    public String toString() {
        String format = emotion.toString() + "  " + date.toString();
        if (comment != "") {
            return format + "\n" + comment;
        }
        return format;
    }
}
