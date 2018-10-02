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

public class EmotionCountAdapter extends ArrayAdapter<Integer> {
    public EmotionCountAdapter(Context context, ArrayList<Integer> emotionCounts) {
        super(context, 0, emotionCounts);
    }

    @Override
    public @NonNull View getView(int position, View convertView,@NonNull ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_emotion_count,parent,false);
        }

        int count = getItem(position);

        TextView emotionView = convertView.findViewById(R.id.emotionCountView_EmotionView);
        TextView countView = convertView.findViewById(R.id.emotionCountView_CountView);
        emotionView.setText(Emotion.values()[position].getString());
        emotionView.setTextColor(ContextCompat.getColor(getContext(), Emotion.values()[position].getColor()));
        countView.setText(Integer.toString(count));

        return convertView;
    }
}
