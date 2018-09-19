package com.example.gobran.gobran_feelsbook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = this.getSharedPreferences("com.example.gobran.gobran_feelsbook.preferencesFile", Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPrefEditor = sharedPref.edit();

        if(!sharedPref.getBoolean("createdPrefs",false)) {
            sharedPrefEditor.putBoolean("createdPrefs", true);
            sharedPrefEditor.putInt("loveCount", 0);
            sharedPrefEditor.putInt("joyCount", 0);
            sharedPrefEditor.putInt("supriseCount", 0);
            sharedPrefEditor.putInt("angerCount", 0);
            sharedPrefEditor.putInt("sadnessCount", 0);
            sharedPrefEditor.putInt("fearCount", 0);
        }
    }

    public void accessStatistics(View view) {
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }

    public void accessHistory(View view) {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }
}
