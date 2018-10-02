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

public class EmotionButtonAdapter extends ArrayAdapter<Emotion> {

    public EmotionButtonAdapter(Context context, ArrayList<Emotion> emotionsArrayList) {
        super(context, 0, emotionsArrayList);
    }

    @Override
    public @NonNull View getView(int position, View convertView,@NonNull ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_emotion_button,parent,false);
        }

        Emotion emotion = getItem(position);

        TextView emotionButton = convertView.findViewById(R.id.emotionButtonView_EmotionButtonView);
        emotionButton.setText(emotion.getString());
        emotionButton.setBackgroundColor(ContextCompat.getColor(getContext(), emotion.getColor()));

        return convertView;
    }
}
