package com.example.gobran.gobran_feelsbook;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HistoryActivity extends AppCompatActivity {
    private ListView emotionsList;
    private EmotionManagerController emotionManagerController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        emotionsList = (ListView) findViewById(R.id.history_list);
        emotionManagerController= ((FeelsBookApp)getApplication()).getEmotionManagerController();
    }

    @Override
    protected void onStart() {
        super.onStart();
        update();
    }

    private void update() {
        ArrayAdapter<EmotionRecord> historyAdapter = new ArrayAdapter<EmotionRecord>(this,R.layout.emotion_view, emotionManagerController.getRecords());
        emotionsList.setAdapter(historyAdapter);
    }


}
