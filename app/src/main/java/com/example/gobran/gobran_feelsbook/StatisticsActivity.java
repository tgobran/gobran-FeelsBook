package com.example.gobran.gobran_feelsbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class StatisticsActivity extends AppCompatActivity {
    private EmotionCountAdapter emotionCountAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        ListView emotionCountList = findViewById(R.id.statisticsActivity_EmotionCountsList);
        emotionCountList.setEnabled(false);
        emotionCountAdapter = new EmotionCountAdapter(this,FeelsBookApp.getEmotionManagerController().getEmotionCounts());
        emotionCountList.setAdapter(emotionCountAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        emotionCountAdapter.notifyDataSetChanged();
    }
}
