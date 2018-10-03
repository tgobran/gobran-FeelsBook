/*
 * Class: HistoryActivity
 *
 * Version: 1.0
 *
 * Date: October 3rd, 2018
 *
 * Description:
 *      The activity in charge of presenting the emotion history of the application to the user
 * Rationale:
 *      Presenting the history in a separate activity allows for a more detailed presentation to
 *      the user and a clear design overall
 * Outstanding Issues:
 *      No real issues are present
 */

package com.example.gobran.gobran_feelsbook;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class HistoryActivity extends AppCompatActivity {

    // Holds the ArrayAdapter used to hold the records for presentation in its listView
    private EmotionRecordAdapter emotionRecordAdapter;

    // The button click count value, used to ensure no double opening of the same record
    private long lastClickWatcher = -2000;

    // Establishes the basic UI for the activity and builds the listView of records
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Accesses the controller for the emotion manager
        EmotionManagerController emotionManagerController = FeelsBookApp.getEmotionManagerController();

        // Using the controller retrieves the ArrayList of emotionRecords and creates an adapter
        // for them that will display them in the history activities listView
        ListView emotionRecordList = findViewById(R.id.historyActivity_HistoryListView);
        emotionRecordAdapter = new EmotionRecordAdapter(this,emotionManagerController.getEmotionRecords());
        emotionRecordList.setAdapter(emotionRecordAdapter);

        // Sets a click listener for each record to open up a separate view activity for the record
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

    // Refreshes the record adapter on resume so that any changes made in other activities are present
    @Override
    public void onResume() {
        super.onResume();
        emotionRecordAdapter.notifyDataSetChanged();
    }

    // Opens up an individual view record activity for the given emotion record, with the record sent
    private void viewRecord(EmotionRecord emotionRecord) {
        Intent intent = new Intent(this, ViewEmotionRecordActivity.class);
        intent.putExtra(this.getString(R.string.viewEmotionRecordActivity_EmotionRecordIntent),emotionRecord);
        startActivity(intent);
    }
}
