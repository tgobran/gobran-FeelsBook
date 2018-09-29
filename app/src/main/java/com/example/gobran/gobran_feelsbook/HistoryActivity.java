package com.example.gobran.gobran_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

        emotionsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                viewRecord((EmotionRecord)parent.getItemAtPosition(position),position);
            }
        });
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

    private void viewRecord(EmotionRecord record, int index) {
        Intent intent = new Intent(this, ViewRecordActivity.class);
        intent.putExtra(this.getString(R.string.recordIntent),record);
        intent.putExtra(this.getString(R.string.indexIntent),index);
        startActivity(intent);
    }
}
