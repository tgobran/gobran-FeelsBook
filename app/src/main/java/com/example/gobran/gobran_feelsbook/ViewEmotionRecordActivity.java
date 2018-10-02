package com.example.gobran.gobran_feelsbook;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class ViewEmotionRecordActivity extends AppCompatActivity implements DatePickerFragment.OnNewDateSetListener, TimePickerFragment.OnNewTimeSetListener, CommentWriterFragment.OnCommentSetListener {
    private EmotionManagerController emotionManagerController;

    private EmotionRecord emotionRecord;

    private long lastClickWatcher = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_emotion_record);

        emotionManagerController = FeelsBookApp.getEmotionManagerController();

        Intent intent = getIntent();
        emotionRecord = (EmotionRecord)intent.getSerializableExtra(this.getString(R.string.viewEmotionRecordActivity_EmotionRecordIntent));

        Button editDateButton = findViewById(R.id.viewEmotionRecordActivity_EditDateButton);
        Button editTimeButton = findViewById(R.id.viewEmotionRecordActivity_EditTimeButton);
        Button editCommentButton = findViewById(R.id.viewEmotionRecordActivity_EditCommentButton);
        Button deleteButton = findViewById(R.id.viewEmotionRecordActivity_DeleteRecordButton);

        editDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                Bundle dateData = new Bundle();
                dateData.putInt(getApplicationContext().getString(R.string.datePickerFragment_YearArgument),emotionRecord.getDateTime().get(Calendar.YEAR));
                dateData.putInt(getApplicationContext().getString(R.string.datePickerFragment_MonthArgument),emotionRecord.getDateTime().get(Calendar.MONTH));
                dateData.putInt(getApplicationContext().getString(R.string.datePickerFragment_DayArgument),emotionRecord.getDateTime().get(Calendar.DAY_OF_MONTH));
                newFragment.setArguments(dateData);
                newFragment.show(getSupportFragmentManager(),"datePicker");
            }
        });

        editTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                Bundle timeData = new Bundle();
                timeData.putInt(getApplicationContext().getString(R.string.timePickerFragment_HourArgument),emotionRecord.getDateTime().get(Calendar.HOUR_OF_DAY));
                timeData.putInt(getApplicationContext().getString(R.string.timePickerFragment_MinuteArgument),emotionRecord.getDateTime().get(Calendar.MINUTE));
                timeData.putInt(getApplicationContext().getString(R.string.timePickerFragment_SecondArgument),emotionRecord.getDateTime().get(Calendar.SECOND));
                newFragment.setArguments(timeData);
                newFragment.show(getSupportFragmentManager(),"timePicker");
            }
        });

        editCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new CommentWriterFragment();
                Bundle commentData = new Bundle();
                commentData.putString(getApplicationContext().getString(R.string.commentWriterFragment_CommentArgument),emotionRecord.getComment());
                newFragment.setArguments(commentData);
                newFragment.show(getSupportFragmentManager(),"commentWriter");
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - lastClickWatcher > 2000) {
                    onEmotionRecordDelete();
                    lastClickWatcher = SystemClock.elapsedRealtime();
                }
            }
        });

        update();
    }

    private void update() {
        TextView emotionView = findViewById(R.id.viewEmotionRecordActivity_EmotionView);
        TextView dateView = findViewById(R.id.viewEmotionRecordActivity_DateView);
        TextView commentView = findViewById(R.id.viewEmotionRecordActivity_CommentView);

        emotionView.setTextColor(ContextCompat.getColor(this,emotionRecord.getEmotion().getColor()));
        emotionView.setText(emotionRecord.getEmotion().getString());
        dateView.setText(emotionRecord.getDateTimeISO());
        commentView.setText(emotionRecord.getComment());
    }

    public void onNewDateSet(int newYear, int newMonth, int newDay) {
        emotionRecord.getDateTime().set(Calendar.YEAR,newYear);
        emotionRecord.getDateTime().set(Calendar.MONTH,newMonth);
        emotionRecord.getDateTime().set(Calendar.DAY_OF_MONTH,newDay);
        saveEmotionRecordChanges("Date");
    }
    public void onNewTimeSet(int newHour, int newMinute, int newSecond) {
        emotionRecord.getDateTime().set(Calendar.HOUR_OF_DAY,newHour);
        emotionRecord.getDateTime().set(Calendar.MINUTE,newMinute);
        emotionRecord.getDateTime().set(Calendar.SECOND,newSecond);
        saveEmotionRecordChanges("Time");
    }
    public void onCommentSet(String newComment) {
        if (newComment != null) {
            emotionRecord.setComment(newComment);
            saveEmotionRecordChanges("Comment");
        }
    }
    private void saveEmotionRecordChanges(String changeType) {
        emotionManagerController.editEmotionRecord(emotionRecord.getId(),emotionRecord.getEmotion(),emotionRecord.getDateTime(),emotionRecord.getComment());
        Toast.makeText(this, changeType+" Saved", Toast.LENGTH_SHORT).show();
        update();
    }

    private void onEmotionRecordDelete() {
        emotionManagerController.deleteEmotionRecord(emotionRecord.getId());
        Toast.makeText(this,"Record Deleted",Toast.LENGTH_SHORT).show();
        finish();
    }
}
