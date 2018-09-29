package com.example.gobran.gobran_feelsbook;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class ViewRecordActivity extends AppCompatActivity implements DatePickerFragment.OnNewDateSetListener, TimePickerFragment.OnNewTimeSetListener {
    private int index;
    private EmotionType emotionType;
    private Calendar dateTime;
    private String comment;

    private EmotionManagerController emotionManagerController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record);

        emotionManagerController = ((FeelsBookApp)getApplication()).getEmotionManagerController();

        Intent intent = getIntent();
        EmotionRecord record = (EmotionRecord)intent.getSerializableExtra(this.getString(R.string.recordIntent));
        index = intent.getIntExtra(this.getString(R.string.indexIntent),0);
        emotionType = record.getEmotion();
        dateTime = record.getDateTime();
        comment = record.getComment();

        Button editDateButton = findViewById(R.id.viewRecord_EditDateButton);
        Button editTimeButton = findViewById(R.id.viewRecord_EditTimeButton);
        Button saveButton = findViewById(R.id.viewRecord_SaveRecordButton);
        Button deleteButton = findViewById(R.id.viewRecord_DeleteRecordButton);

        editDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(),"datePicker");
            }
        });

        editTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getSupportFragmentManager(),"timePicker");
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText commentView = findViewById(R.id.viewRecord_CommentView);
                comment = commentView.getText().toString();
                emotionManagerController.editRecord(index,emotionType,dateTime,comment);
                finish();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emotionManagerController.deleteRecord(index);
                finish();
            }
        });

        displayRecord();
    }

    private void displayRecord() {
        TextView emotionView = findViewById(R.id.viewRecord_EmotionView);
        TextView dateView = findViewById(R.id.viewRecord_DateView);
        EditText commentView = findViewById(R.id.viewRecord_CommentView);
        emotionView.setText(emotionType.toString());
        dateView.setText(String.format("%04d-%02d-%02dT%02d:%02d:%02d",dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH),dateTime.get(Calendar.DAY_OF_MONTH),dateTime.get(Calendar.HOUR_OF_DAY),dateTime.get(Calendar.MINUTE),dateTime.get(Calendar.SECOND)));
        commentView.setText(comment);
    }

    public void onNewDateSet(int year, int month, int day) {
        dateTime.set(Calendar.YEAR,year);
        dateTime.set(Calendar.MONTH,month);
        dateTime.set(Calendar.DAY_OF_MONTH,day);
        displayRecord();
    }

    public void onNewTimeSet(int hour, int minute, int second) {
        dateTime.set(Calendar.HOUR_OF_DAY,hour);
        dateTime.set(Calendar.MINUTE,minute);
        dateTime.set(Calendar.SECOND,second);
        displayRecord();
    }
}
