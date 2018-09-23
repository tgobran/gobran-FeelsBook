package com.example.gobran.gobran_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class StatisticsActivity extends AppCompatActivity {
    private EmotionCount count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
    }

    @Override
    protected void  onStart() {
        super.onStart();
        readStatistics();
    }

    private void readStatistics() {
        count = new EmotionCount(getApplicationContext());
        TextView textView = findViewById(R.id.statistics_LoveCount);
        textView.setText(String.format("%d",count.retrieveCount(EmotionType.LOVE)));
        textView = findViewById(R.id.statistics_JoyCount);
        textView.setText(String.format("%d",count.retrieveCount(EmotionType.JOY)));
        textView = findViewById(R.id.statistics_SurpriseCount);
        textView.setText(String.format("%d",count.retrieveCount(EmotionType.SURPRISE)));
        textView = findViewById(R.id.statistics_AngerCount);
        textView.setText(String.format("%d",count.retrieveCount(EmotionType.ANGER)));
        textView = findViewById(R.id.statistics_SadnessCount);
        textView.setText(String.format("%d",count.retrieveCount(EmotionType.SADNESS)));
        textView = findViewById(R.id.statistics_FearCount);
        textView.setText(String.format("%d",count.retrieveCount(EmotionType.FEAR)));

    }
}
