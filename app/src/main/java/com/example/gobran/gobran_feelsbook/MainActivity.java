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
    private EmotionManagerController emotionManagerController = FeelsBookApp.getEmotionManagerController();

    private Emotion emotionToAdd;

    private long lastClickWatcher = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        GridView emotionButtonGrid = findViewById(R.id.mainActivity_EmotionButtonsGrid);
        ArrayList<Emotion> emotionsArrayList = new ArrayList<Emotion>();
        for(int i = 0; i < Emotion.values().length; i++) {
            emotionsArrayList.add(Emotion.values()[i]);
        }
        EmotionButtonAdapter emotionButtonAdapter = new EmotionButtonAdapter(this,emotionsArrayList);
        emotionButtonGrid.setAdapter(emotionButtonAdapter);
        emotionButtonGrid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                emotionToAdd = Emotion.values()[position];
                getComment();
                return true;
            }
        });
        emotionButtonGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                addEmotion(Emotion.values()[position],"");
            }
        });

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

    @Override
    public void onResume() {
        super.onResume();
        updateViewLastEmotionButton();
    }

    private void addEmotion(Emotion emotion, String comment) {
        emotionManagerController.addEmotionRecord(emotion, Calendar.getInstance(), comment);
        updateViewLastEmotionButton();
        Toast.makeText(this, "Registered " + emotion.getString(), Toast.LENGTH_SHORT).show();
    }
    private void getComment() {
        DialogFragment newCommentFragment = new CommentWriterFragment();
        newCommentFragment.show(getSupportFragmentManager(),"commentWriter");
    }
    public void onCommentSet(String comment) {
        if(comment != null) {
            addEmotion(emotionToAdd,comment);
        }
    }

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
    private void viewLastEmotionRecord() {
        Intent intent = new Intent(this, ViewEmotionRecordActivity.class);
        intent.putExtra(this.getString(R.string.viewEmotionRecordActivity_EmotionRecordIntent), emotionManagerController.getLastCreatedEmotionRecord());
        startActivity(intent);
    }
}
