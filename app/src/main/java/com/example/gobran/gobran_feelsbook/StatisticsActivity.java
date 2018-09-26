package com.example.gobran.gobran_feelsbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class StatisticsActivity extends AppCompatActivity {
    private EmotionManagerController emotionManagerController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        emotionManagerController= ((FeelsBookApp)getApplication()).getEmotionManagerController();
    }

    @Override
    protected void  onStart() {
        super.onStart();
        update();
    }

    private void update() {
        Integer[] counts = emotionManagerController.getCounts();
        TextView textView = findViewById(R.id.statistics_LoveCount);
        textView.setText(String.format("%d",counts[EmotionType.LOVE.toId()]));
        textView = findViewById(R.id.statistics_JoyCount);
        textView.setText(String.format("%d",counts[EmotionType.JOY.toId()]));
        textView = findViewById(R.id.statistics_SurpriseCount);
        textView.setText(String.format("%d",counts[EmotionType.SURPRISE.toId()]));
        textView = findViewById(R.id.statistics_AngerCount);
        textView.setText(String.format("%d",counts[EmotionType.ANGER.toId()]));
        textView = findViewById(R.id.statistics_SadnessCount);
        textView.setText(String.format("%d",counts[EmotionType.SADNESS.toId()]));
        textView = findViewById(R.id.statistics_FearCount);
        textView.setText(String.format("%d",counts[EmotionType.FEAR.toId()]));

    }
}
