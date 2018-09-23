package com.example.gobran.gobran_feelsbook;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class EmotionCount {
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor prefEditor;

    public EmotionCount(Context context) {
        sharedPref = context.getSharedPreferences(context.getString(R.string.countPreferencesFileKey), Context.MODE_PRIVATE);
        prefEditor = sharedPref.edit();
        if(!sharedPref.getBoolean(context.getString(R.string.countCreationData),false)) {
            prefEditor.putBoolean(context.getString(R.string.countCreationData), true);
            for(EmotionType type: EmotionType.values()) {
                prefEditor.putInt(type.getStringForm(),0);
            }
            prefEditor.apply();
        }
    }

    public void updateCount(EmotionType emotion, int quantity) {
        int currCount =  sharedPref.getInt(emotion.getStringForm(),0);
        prefEditor.putInt(emotion.getStringForm(), currCount + quantity);
        prefEditor.apply();
    }

    public int retrieveCount(EmotionType emotion) {
        return sharedPref.getInt(emotion.getStringForm(),0);
    }

}
