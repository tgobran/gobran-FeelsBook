package com.example.gobran.gobran_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EmotionCount count;
    private EmotionHistory history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        history = new EmotionHistory(getApplicationContext());
    }

    @Override
    protected void onStart() {
        super.onStart();
        count = new EmotionCount(getApplicationContext());
    }

    public void accessStatistics(View view) {
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }
    public void accessHistory(View view) {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    public void addLove(View view) {
        history.addRecord(EmotionType.LOVE, new Date(),"");
        count.updateCount(EmotionType.LOVE,1);
    }
    public void addJoy(View view) {
        history.addRecord(EmotionType.JOY, new Date(),"");
        count.updateCount(EmotionType.JOY,1);
    }
    public void addSurprise(View view) {
        history.addRecord(EmotionType.SURPRISE, new Date(),"");
        count.updateCount(EmotionType.SURPRISE,1);
    }
    public void addAnger(View view) {
        history.addRecord(EmotionType.ANGER, new Date(),"");
        count.updateCount(EmotionType.ANGER,1);
    }
    public void addSadness(View view) {
        history.addRecord(EmotionType.SADNESS, new Date(),"");
        count.updateCount(EmotionType.SADNESS,1);
    }
    public void addFear(View view) {
        history.addRecord(EmotionType.FEAR, new Date(),"");
        count.updateCount(EmotionType.FEAR,1);
    }
}
