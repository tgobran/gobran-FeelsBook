package com.example.gobran.gobran_feelsbook;

import android.content.Intent;
import android.icu.text.AlphabeticIndex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HistoryActivity extends AppCompatActivity {
    private RecordAdapter recordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        EmotionManagerController emotionManagerController = ((FeelsBookApp)getApplication()).getEmotionManagerController();

        ListView historyList = findViewById(R.id.historyActivity_HistoryListView);
        recordAdapter = new RecordAdapter(this,emotionManagerController.getRecords());
        historyList.setAdapter(recordAdapter);

        historyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                viewRecord((EmotionRecord)parent.getItemAtPosition(position),position);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        recordAdapter.notifyDataSetChanged();
    }

    private void viewRecord(EmotionRecord record, int index) {
        Intent intent = new Intent(this, ViewRecordActivity.class);
        intent.putExtra(this.getString(R.string.recordIntent),record);
        intent.putExtra(this.getString(R.string.indexIntent),index);
        startActivity(intent);
    }
}
