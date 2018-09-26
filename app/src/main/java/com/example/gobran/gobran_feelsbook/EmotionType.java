package com.example.gobran.gobran_feelsbook;

public enum EmotionType {
    LOVE     (0,"Love"),
    JOY      (1,"Joy"),
    SURPRISE (2,"Surprise"),
    ANGER    (3,"Anger"),
    SADNESS  (4,"Sadness"),
    FEAR     (5,"Fear");

    private final int numId;
    private final String stringForm;

    EmotionType(int id, String stringRep) {
        this.numId = id;
        this.stringForm = stringRep;
    }

    public int toId() {return numId;}

    public String toString() {
        return stringForm;
    }
}
