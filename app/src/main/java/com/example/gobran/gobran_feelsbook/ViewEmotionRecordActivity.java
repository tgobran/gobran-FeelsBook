/*
 * Class: ViewEmotionRecordActivity
 *
 * Version: 1.0
 *
 * Date: October 3rd, 2018
 *
 * Description:
 *      The activity in charge of presenting an emotion record to the user, with options to edit
 *      it, or delete the record if the user so desires
 * Rationale:
 *      Placing the full presentation, editing and deletion of a record in a separate view allows
 *      greater focus to be present in regards to user activities. It also decrease the roles of
 *      the other activities and allows a common ui element to be accessed from wherever an emotion
 *      record can be viewed from.
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class ViewEmotionRecordActivity extends AppCompatActivity implements DatePickerFragment.OnNewDateSetListener, TimePickerFragment.OnNewTimeSetListener, CommentWriterFragment.OnCommentSetListener {
    // Grabs the controller for the emotionManager to be used when editing the record
    private EmotionManagerController emotionManagerController = FeelsBookApp.getEmotionManagerController();

    // Holds the emotion record that was opened
    private EmotionRecord emotionRecord;

    // The button click count value, used to ensure no double deletion of the viewed record
    private long lastClickWatcher = -2000;

    // Establishes the basic UI for the activity, setting all of the record information
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_emotion_record);

        // Retrieves the record that was sent with the call to view an emotion record
        Intent intent = getIntent();
        emotionRecord = (EmotionRecord)intent.getSerializableExtra(this.getString(R.string.viewEmotionRecordActivity_EmotionRecordIntent));

        // Sets the edit date button to open a DatePickerFragment with arguments for the current
        // record date
        Button editDateButton = findViewById(R.id.viewEmotionRecordActivity_EditDateButton);
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

        // Sets the edit time button to open a TimePickerFragment with arguments for the current
        // record time
        Button editTimeButton = findViewById(R.id.viewEmotionRecordActivity_EditTimeButton);
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

        // Sets the edit comment button to open a commentWriterFragment with an argument with the
        // current record comment
        Button editCommentButton = findViewById(R.id.viewEmotionRecordActivity_EditCommentButton);
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

        // Sets the delete button to call for the deletion of the record being viewed, a counter
        // is present to place any double clicking of the button which could cause errors
        Button deleteButton = findViewById(R.id.viewEmotionRecordActivity_DeleteRecordButton);
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

    // Handles updating the record data presented by the view, called at the beginning and on any
    // changes made to the record. Presents the emotion, dateTime ISO8601 format and the comment
    private void update() {
        TextView emotionView = findViewById(R.id.viewEmotionRecordActivity_EmotionView);
        TextView dateView = findViewById(R.id.viewEmotionRecordActivity_DateView);
        TextView commentView = findViewById(R.id.viewEmotionRecordActivity_CommentView);

        emotionView.setTextColor(ContextCompat.getColor(this,emotionRecord.getEmotion().getColor()));
        emotionView.setText(emotionRecord.getEmotion().getString());
        dateView.setText(emotionRecord.getDateTimeISO());
        commentView.setText(emotionRecord.getComment());
    }

    // Called by the DatePickerFragment after a date is set, changes the records set date and
    // notifies the view that a change has been made
    public void onNewDateSet(int newYear, int newMonth, int newDay) {
        emotionRecord.getDateTime().set(Calendar.YEAR,newYear);
        emotionRecord.getDateTime().set(Calendar.MONTH,newMonth);
        emotionRecord.getDateTime().set(Calendar.DAY_OF_MONTH,newDay);
        saveEmotionRecordChanges("Date");
    }
    // Called by the TimePickerFragment after a time is set, changes the records set time and
    // notifies the view that a change has been made
    public void onNewTimeSet(int newHour, int newMinute, int newSecond) {
        emotionRecord.getDateTime().set(Calendar.HOUR_OF_DAY,newHour);
        emotionRecord.getDateTime().set(Calendar.MINUTE,newMinute);
        emotionRecord.getDateTime().set(Calendar.SECOND,newSecond);
        saveEmotionRecordChanges("Time");
    }
    // Called by the CommentWriterFragment after a comment is set, changes the records set comment
    // and notifies the view that a change has been made
    public void onCommentSet(String newComment) {
        if (newComment != null) {
            emotionRecord.setComment(newComment);
            saveEmotionRecordChanges("Comment");
        }
    }
    // Handles any changes being made by editing the record through the emotion manager controller,
    // notifying the user of the change and then updating the view of the record
    private void saveEmotionRecordChanges(String changeType) {
        emotionManagerController.editEmotionRecord(emotionRecord.getId(),emotionRecord.getEmotion(),emotionRecord.getDateTime(),emotionRecord.getComment());
        Toast.makeText(this, changeType+" Saved", Toast.LENGTH_SHORT).show();
        update();
    }

    // Handles the deletion of the viewed record through the emotion manager controller, then notifies
    // the user of the change and ends the current viewing activity.
    private void onEmotionRecordDelete() {
        emotionManagerController.deleteEmotionRecord(emotionRecord.getId());
        Toast.makeText(this,"Record Deleted",Toast.LENGTH_SHORT).show();
        finish();
    }
}
