package com.example.gobran.gobran_feelsbook;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class RecordAdapter extends ArrayAdapter<EmotionRecord> {
    public RecordAdapter(Context context, ArrayList<EmotionRecord> records) {
        super(context, 0, records);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EmotionRecord record = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.record_view,parent,false);
        }

        TextView emotionView = convertView.findViewById(R.id.recordView_EmotionView);
        TextView dateView = convertView.findViewById(R.id.recordView_DateView);
        TextView commentView = convertView.findViewById(R.id.recordView_CommentView);

        emotionView.setTextColor(ContextCompat.getColor(getContext(),record.getEmotion().toColor()));
        emotionView.setText(record.getEmotion().toString());
        dateView.setText(record.getDateTimeISO());
        if(record.getComment() == "") {
            commentView.setTextSize(0);
        }
        commentView.setText(record.getComment());

        return convertView;
    }
}
