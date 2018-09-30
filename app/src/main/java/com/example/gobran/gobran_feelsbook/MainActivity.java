package com.example.gobran.gobran_feelsbook;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements CommentWriterFragment.OnCommentSetListener {
    private EmotionManagerController emotionManagerController;

    private EmotionType typeToAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emotionManagerController= ((FeelsBookApp)getApplication()).getEmotionManagerController();

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


        GridView buttonGrid = findViewById(R.id.mainActivity_EmotionButtonsGrid);
        ArrayList<EmotionType> emotionRecordArrayList = new ArrayList<EmotionType>();
        for(int i = 0; i < EmotionType.values().length; i++) {
            emotionRecordArrayList.add(EmotionType.values()[i]);
        }
        EmotionButtonAdapter emotionButtonAdapter = new EmotionButtonAdapter(this,emotionRecordArrayList);
        buttonGrid.setAdapter(emotionButtonAdapter);

        buttonGrid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                typeToAdd = EmotionType.values()[position];
                getComment();
                return true;
            }
        });

        buttonGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                addEmotion(EmotionType.values()[position],"");
            }
        });
    }

    public void addEmotion(EmotionType type, String comment) {
        emotionManagerController.addRecord(type, Calendar.getInstance(), comment);
        Toast.makeText(this, "Registered " + type.toString(), Toast.LENGTH_SHORT).show();
    }

    public void getComment() {
        DialogFragment newFragment = new CommentWriterFragment();
        newFragment.show(getSupportFragmentManager(),"commentWriter");
    }
    public void onCommentSet(String comment) {
        if(comment != null) {
            addEmotion(typeToAdd,comment);
        }
    }
}
