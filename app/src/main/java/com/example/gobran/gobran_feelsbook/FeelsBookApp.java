/*
 * Class: FeelsBookApp
 *
 * Version: 1.0
 *
 * Date: October 3rd, 2018
 *
 * Description:
 *      Override form of the main App class to allow for application lifetime for the emotionManager
 *      and controller associated with it.
 * Rationale:
 *      This class allows for an application lifetime presence for the emotionManager and controller
 *      as well as globally accessible static versions of these classes. This is beneficial as then
 *      the loading in of records and creation of counts is minimized within the App.
 *      Furthermore it allows each different GUI element to access the same controller and
 *      corresponding emotionManager object when the user makes changes
 * Outstanding Issues:
 *      It feels wrong to override the Application class just to accomplish these global objects,
 *      adapting a better approach for the controller and emotionManagers lifespan in the future
 *      is a goal.
 */

package com.example.gobran.gobran_feelsbook;

import android.app.Application;
import java.io.File;

public class FeelsBookApp extends Application {
    private static EmotionManager emotionManager = null;
    private static EmotionManagerController emotionManagerController = null;
    private static File appFileDir;

    // On Application creation, retreives the application file diretory and creates the emotionManager
    @Override
    public void onCreate() {
        super.onCreate();
        appFileDir = getFilesDir();
        getEmotionManager();
    }

    // Creates the emotionManager if none exists, otherwise returns the singleton
    static EmotionManager getEmotionManager() {
        if(emotionManager == null) {
            emotionManager = new EmotionManager(appFileDir);
        }
        return emotionManager;
    }

    // Creates the emotionManagerController if none exists and passes it the emotionManager,
    // otherwise just returns the singleton
    public static EmotionManagerController getEmotionManagerController() {
        if(emotionManagerController == null) {
            emotionManagerController = new EmotionManagerController(getEmotionManager());
        }
        return emotionManagerController;
    }
}