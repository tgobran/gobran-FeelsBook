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
    public EmotionRecordAdapter(Context context, ArrayList<EmotionRecord> emotionRecords) {
        super(context, 0, emotionRecords);
    }

    @Override
    public @NonNull View getView(int position, View convertView,@NonNull ViewGroup parent) {
        EmotionRecord emotionRecord = getItem(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_emotion_record, parent, false);
        }
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
