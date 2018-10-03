/*
 * Class: EmotionRecordAdapter
 *
 * Version: 1.0
 *
 * Date: October 3rd, 2018
 *
 * Description:
 *      Presents a custom ArrayAdapter for an ArrayList of EmotionRecords so that a custom view
 *      presentation of each can be made
 * Rationale:
 *      This adapter allows for a customized presentation of views for each emotion record registered
 *      in a record ArrayList. This allows for a better presentation of the records beyond just
 *      strings that can later be built upon and changed by just editing this adapter.
 * Outstanding Issues:
 *     Few issues are prevalent with the current design due to its relative simplicity
 */

package com.example.gobran.gobran_feelsbook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class EmotionRecordAdapter extends ArrayAdapter<EmotionRecord> {

    // Receives the arguments for an ArrayAdapter and passes them along to the actual ArrayAdapter
    public EmotionRecordAdapter(Context context, ArrayList<EmotionRecord> emotionRecords) {
        super(context, 0, emotionRecords);
    }

    // Overrides the getView method by the ArrayAdapter so that a custom presentation can be made
    // for each emotion record with its emotion, date and comment being presented
    @Override
    public @NonNull View getView(int position, View convertView,@NonNull ViewGroup parent) {
        EmotionRecord emotionRecord = getItem(position);

        // Allows recycling of views for better performance
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_emotion_record, parent, false);
        }

        // Using the emotion record at the given position in the ArrayAdapter sets the views
        // different areas to properly present the records data
        TextView emotionView = convertView.findViewById(R.id.emotionRecordView_EmotionView);
        emotionView.setTextColor(ContextCompat.getColor(getContext(),emotionRecord.getEmotion().getColor()));
        emotionView.setText(emotionRecord.getEmotion().getString());
        TextView dateView = convertView.findViewById(R.id.emotionRecordView_DateView);
        dateView.setText(emotionRecord.getDateTimeISO());
        TextView commentView = convertView.findViewById(R.id.emotionRecordView_CommentView);
        commentView.setText(emotionRecord.getComment());

        return convertView;
    }
}
