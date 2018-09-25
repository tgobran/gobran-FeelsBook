package com.example.gobran.gobran_feelsbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HistoryActivity extends AppCompatActivity {
    private FeelsBookApp app;
    private ListView emotionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        emotionsList = (ListView) findViewById(R.id.history_list);

        app = (FeelsBookApp)getApplication();

        loadHistory();
    }

    private void loadHistory() {
        ArrayAdapter<EmotionRecord> historyAdapter = new ArrayAdapter<EmotionRecord>(this,R.layout.emotion_view, app.getHistory().getRecords());
        emotionsList.setAdapter(historyAdapter);
    }


}
