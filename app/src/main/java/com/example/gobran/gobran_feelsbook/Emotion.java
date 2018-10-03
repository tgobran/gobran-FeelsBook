/*
 * Class: Emotion
 *
 * Version: 1.0
 *
 * Date: October 3rd, 2018
 *
 * Description:
 *      Enumeration that contains all supported emotions and their related data
 * Rationale:
 *      Given the set number of emotions requested it seemed sensible to create
 *      an enumeration, furthermore this also allows for common emotion properties
 *      such as an id form (for lists), and a String and Color form (for presentation).
 *      This implementation also allows for quick additions of more emotions to be
 *      made by simply adding another line in the enumeration
 * Outstanding Issues:
 *      If the app was to later allow user specified emotions this class would not work
 *      though a similar class in regards to methods and types of stored data could be
 *      utilized.
 */

package com.example.gobran.gobran_feelsbook;

public enum Emotion {
    LOVE     (0,"Love",     R.color.love),
    JOY      (1,"Joy",      R.color.joy),
    SURPRISE (2,"Surprise", R.color.surprise),
    ANGER    (3,"Anger",    R.color.anger),
    SADNESS  (4,"Sadness",  R.color.sadness),
    FEAR     (5,"Fear",     R.color.fear);

    // The related information required for emotion presentation and storage of counts
    private final int emotionId;
    private final String emotionString;
    private final int emotionColor;

    // How each emotion enumeration is created
    Emotion(int id, String string, int color) {
        this.emotionId = id;
        this.emotionString = string;
        this.emotionColor = color;
    }

    // Getter methods to access each of an emotions properties
    public Integer getId() { return emotionId; }
    public String getString() { return emotionString; }
    public Integer getColor() { return emotionColor; }
}
