package com.example.gobran.gobran_feelsbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class StatisticsActivity extends AppCompatActivity {
    private FeelsBookApp app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        app = (FeelsBookApp)getApplicationContext();
    }

    @Override
    protected void  onStart() {
        super.onStart();
        readStatistics();
    }

    private void readStatistics() {
        ArrayList<Integer> counts = app.getHistory().getCounts();
        TextView textView = findViewById(R.id.statistics_LoveCount);
        textView.setText(String.format("%d",counts.get(EmotionType.LOVE.getNumId())));
        textView = findViewById(R.id.statistics_JoyCount);
        textView.setText(String.format("%d",counts.get(EmotionType.JOY.getNumId())));
        textView = findViewById(R.id.statistics_SurpriseCount);
        textView.setText(String.format("%d",counts.get(EmotionType.SURPRISE.getNumId())));
        textView = findViewById(R.id.statistics_AngerCount);
        textView.setText(String.format("%d",counts.get(EmotionType.ANGER.getNumId())));
        textView = findViewById(R.id.statistics_SadnessCount);
        textView.setText(String.format("%d",counts.get(EmotionType.SADNESS.getNumId())));
        textView = findViewById(R.id.statistics_FearCount);
        textView.setText(String.format("%d",counts.get(EmotionType.FEAR.getNumId())));

    }
}
