package com.example.gobran.gobran_feelsbook;

import android.app.Application;

public class FeelsBookApp extends Application {

    private EmotionHistory history;

    @Override
    public void onCreate() {
        super.onCreate();
        history = new EmotionHistory(this);
    }

    public EmotionHistory getHistory() {
        return history;
    }

}
