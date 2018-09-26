package com.example.gobran.gobran_feelsbook;

import android.app.Application;
import android.content.Context;

import java.io.File;

public class FeelsBookApp extends Application {
    private static EmotionManager emotionManager = null;
    private static EmotionManagerController emotionManagerController = null;
    private static File appFile;

    @Override
    public void onCreate() {
        super.onCreate();
        appFile = getFilesDir();
        getEmotionManager();
    }

    static EmotionManager getEmotionManager() {
        if(emotionManager == null) {
            emotionManager = new EmotionManager(appFile);
        }
        return emotionManager;
    }

    public static EmotionManagerController getEmotionManagerController() {
        if(emotionManagerController == null) {
            emotionManagerController = new EmotionManagerController(getEmotionManager());
        }
        return emotionManagerController;
    }
}
