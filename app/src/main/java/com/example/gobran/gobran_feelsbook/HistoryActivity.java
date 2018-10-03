package com.example.gobran.gobran_feelsbook;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class HistoryActivity extends AppCompatActivity {
    private EmotionRecordAdapter emotionRecordAdapter;

    private long lastClickWatcher = -2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        EmotionManagerController emotionManagerController = FeelsBookApp.getEmotionManagerController();

        ListView emotionRecordList = findViewById(R.id.historyActivity_HistoryListView);
        emotionRecordAdapter = new EmotionRecordAdapter(this,emotionManagerController.getEmotionRecords());
        emotionRecordAdapter.setNotifyOnChange(true);
        emotionRecordList.setAdapter(emotionRecordAdapter);

        emotionRecordList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (SystemClock.elapsedRealtime() - lastClickWatcher > 2000) {
                    viewRecord((EmotionRecord) parent.getItemAtPosition(position));
                    lastClickWatcher = SystemClock.elapsedRealtime();
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        emotionRecordAdapter.notifyDataSetChanged();
    }

    private void viewRecord(EmotionRecord emotionRecord) {
        Intent intent = new Intent(this, ViewEmotionRecordActivity.class);
        intent.putExtra(this.getString(R.string.viewEmotionRecordActivity_EmotionRecordIntent),emotionRecord);
        startActivity(intent);
    }
}
