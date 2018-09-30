package com.example.gobran.gobran_feelsbook;

public enum EmotionType {
    LOVE     (0,"Love", R.color.love),
    JOY      (1,"Joy", R.color.joy),
    SURPRISE (2,"Surprise", R.color.surprise),
    ANGER    (3,"Anger", R.color.anger),
    SADNESS  (4,"Sadness", R.color.sadness),
    FEAR     (5,"Fear", R.color.fear);

    private final int id;
    private final String stringForm;
    private final int color;

    EmotionType(int idRep, String stringRep, int colorRep) {
        this.id = idRep;
        this.stringForm = stringRep;
        this.color = colorRep;
    }

    public Integer toId() { return id; }
    public String toString() { return stringForm; }
    public Integer toColor() { return color; }
}
