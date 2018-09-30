package com.example.gobran.gobran_feelsbook;

import java.io.Serializable;
import java.util.Calendar;

public class EmotionRecord implements Serializable {
    private EmotionType emotionType;
    private Calendar dateTime;
    private String comment;

    public EmotionRecord(EmotionType setType, Calendar setDateTime, String setComment) {
        this.emotionType = setType;
        this.dateTime = setDateTime;
        this.comment = setComment;
    }

    public EmotionType getEmotion() {
        return emotionType;
    }

    public Calendar getDateTime() {
        return dateTime;
    }
    public String getDateTimeISO() {
        return String.format("%04d-%02d-%02dT%02d:%02d:%02d",dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH),dateTime.get(Calendar.DAY_OF_MONTH),dateTime.get(Calendar.HOUR_OF_DAY),dateTime.get(Calendar.MINUTE),dateTime.get(Calendar.SECOND));
    }

    public String getComment() {
        return comment;
    }

}
