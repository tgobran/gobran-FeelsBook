package com.example.gobran.gobran_feelsbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class StatisticsActivity extends AppCompatActivity {
    private CountAdapter countAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        EmotionManagerController emotionManagerController= ((FeelsBookApp)getApplication()).getEmotionManagerController();

        ListView countList = findViewById(R.id.statisticsActivity_EmotionCountsList);
        countList.setEnabled(false);
        countAdapter = new CountAdapter(this,emotionManagerController.getCounts());
        countList.setAdapter(countAdapter);

    }

    @Override
    protected void  onResume() {
        super.onResume();
        countAdapter.notifyDataSetChanged();
    }
}
