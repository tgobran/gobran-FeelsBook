/*
 * Class: MainActivity
 *
 * Version: 1.0
 *
 * Date: October 3rd, 2018
 *
 * Description:
 *      The activity in charge of presenting the main view of the app, it presents the buttons for
 *      recording new emotions, viewing history or counts and viewing the last registered emotion
 * Rationale:
 *      The main menu presents the main functionality of the app, that being registering emotions,
 *      as well as allowing for quick changes to the previous registered emotion and easy access
 *      to the counts of emotions and the history of emotion records.
 *      Emotions buttons being handled by an adapter allow this menu to grow and change with any
 *      future developments
 * Outstanding Issues:
 *      No real issues are present
 */

package com.example.gobran.gobran_feelsbook;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements CommentWriterFragment.OnCommentSetListener {
    // Grabs the controller for the emotionManager to be used when registering emotions
    private EmotionManagerController emotionManagerController = FeelsBookApp.getEmotionManagerController();

    // The type of emotion that will be added, used when adding an emotion with a comment being set
    private Emotion emotionToAdd;

    // The button click count value, used to ensure no double opening of the last viewed record
    private long lastClickWatcher = -2000;

    // Establishes the basic UI for the activity and builds the gridView of emotion choices
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sets the listeners for the buttons to the History and Statistics activities
        Button statisticsButton = findViewById(R.id.mainActivity_StatisticsButton);
        Button historyButton = findViewById(R.id.mainActivity_HistoryButton);
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), HistoryActivity.class);
                startActivity(intent);
            }
        });
        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), StatisticsActivity.class);
                startActivity(intent);
            }
        });

        // Creates the emotion button gridView, creating one for each item in the enumeration
        // using the related adapter
        GridView emotionButtonGrid = findViewById(R.id.mainActivity_EmotionButtonsGrid);
        ArrayList<Emotion> emotionsArrayList = new ArrayList<Emotion>();
        for(int i = 0; i < Emotion.values().length; i++) {
            emotionsArrayList.add(Emotion.values()[i]);
        }
        EmotionButtonAdapter emotionButtonAdapter = new EmotionButtonAdapter(this,emotionsArrayList);
        emotionButtonGrid.setAdapter(emotionButtonAdapter);

        // Sets each button in the gridView to register an emotion on click, if a short click then
        // no comment is register, otherwise on a long click a comment is asked for first
        emotionButtonGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                addEmotion(Emotion.values()[position],"");
            }
        });
        emotionButtonGrid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                emotionToAdd = Emotion.values()[position];
                getComment();
                return true;
            }
        });

        // Sets the button to access the last viewed emotion, a counter is established to prevent
        // double clicks on the button which could cause crashes if the user deletes the emotion
        Button viewLastEmotionButton = findViewById(R.id.mainActivity_ViewLastEmotionButton);
        viewLastEmotionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - lastClickWatcher > 2000) {
                    viewLastEmotionRecord();
                    lastClickWatcher = SystemClock.elapsedRealtime();
                }
            }
        });
    }

    // Sets the view last created emotion button to update on resume so that it will show if one
    // still exists after changes in other activities.
    @Override
    public void onResume() {
        super.onResume();
        updateViewLastEmotionButton();
    }

    // Method called by the emotion buttons, adds a new record to the manager, tells the user
    // and updates the last created emotion button to show one exists if not set
    private void addEmotion(Emotion emotion, String comment) {
        emotionManagerController.addEmotionRecord(emotion, Calendar.getInstance(), comment);
        updateViewLastEmotionButton();
        Toast.makeText(this, "Registered " + emotion.getString(), Toast.LENGTH_SHORT).show();
    }
    // Called on a long click of an emotion button, creates a commentWriterFragment to retrieve a
    // user entered comment
    private void getComment() {
        DialogFragment newCommentFragment = new CommentWriterFragment();
        newCommentFragment.show(getSupportFragmentManager(),"commentWriter");
    }
    // Method called by the commentWriterFragment, takes the comment and creates an emotion with the
    // already registered type
    public void onCommentSet(String comment) {
        addEmotion(emotionToAdd,comment);
    }

    // Updates the view last created emotion button, making it clickable and introducing a more
    // welcoming color if so, otherwise disabling the button
    private void updateViewLastEmotionButton() {
        int emotions = emotionManagerController.getEmotionRecords().size();
        Button viewLastEmotionButton = findViewById(R.id.mainActivity_ViewLastEmotionButton);
        if (emotions > 0) {
            viewLastEmotionButton.setTextColor(ContextCompat.getColor(this,R.color.viewLastEmotionPresent));
            viewLastEmotionButton.setClickable(true);
        }
        else {
            viewLastEmotionButton.setTextColor(ContextCompat.getColor(this,R.color.viewLastEmotionNone));
            viewLastEmotionButton.setClickable(false);
        }
    }
    // Method called by the view last created emotion button, opens up a viewRecord activity for the
    // given record to be viewed/changed by the user
    private void viewLastEmotionRecord() {
        Intent intent = new Intent(this, ViewEmotionRecordActivity.class);
        intent.putExtra(this.getString(R.string.viewEmotionRecordActivity_EmotionRecordIntent), emotionManagerController.getLastCreatedEmotionRecord());
        startActivity(intent);
    }
}
