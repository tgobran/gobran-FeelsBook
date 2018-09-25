package com.example.gobran.gobran_feelsbook;

import android.content.Context;
import android.content.SharedPreferences;

public class PrimitiveData {
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor prefEditor;

    public PrimitiveData(Context ctx) {

        sharedPref = ctx.getSharedPreferences(ctx.getString(R.string.prefsFileKey), Context.MODE_PRIVATE);
        prefEditor =  sharedPref.edit();
    }

    public void saveData(String key, int value) {
        prefEditor.putInt(key, value);
        prefEditor.apply();
    }

    public int getData(String key) {
        return sharedPref.getInt(key,0);
    }
}
