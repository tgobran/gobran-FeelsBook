package com.example.gobran.gobran_feelsbook;

public enum Emotion {
    LOVE     (0,"Love",     R.color.love),
    JOY      (1,"Joy",      R.color.joy),
    SURPRISE (2,"Surprise", R.color.surprise),
    ANGER    (3,"Anger",    R.color.anger),
    SADNESS  (4,"Sadness",  R.color.sadness),
    FEAR     (5,"Fear",     R.color.fear);

    private final int emotionId;
    private final String emotionString;
    private final int emotionColor;

    Emotion(int id, String string, int color) {
        this.emotionId = id;
        this.emotionString = string;
        this.emotionColor = color;
    }

    public Integer getId() { return emotionId; }
    public String getString() { return emotionString; }
    public Integer getColor() { return emotionColor; }
}
