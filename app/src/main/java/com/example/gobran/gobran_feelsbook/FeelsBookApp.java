package com.example.gobran.gobran_feelsbook;

import android.app.Application;
import java.io.File;

public class FeelsBookApp extends Application {
    private static EmotionManager emotionManager = null;
    private static EmotionManagerController emotionManagerController = null;
    private static File appFileDir;

    @Override
    public void onCreate() {
        super.onCreate();
        appFileDir = getFilesDir();
        getEmotionManager();
    }

    static EmotionManager getEmotionManager() {
        if(emotionManager == null) {
            emotionManager = new EmotionManager(appFileDir);
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