package com.example.gobran.gobran_feelsbook;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class EmotionButtonAdapter extends ArrayAdapter<EmotionType> {

    public EmotionButtonAdapter(Context context, ArrayList<EmotionType> counts) {
        super(context, 0, counts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        EmotionType type = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.emotion_button_view,parent,false);
        }

        TextView emotionButton = convertView.findViewById(R.id.emotionButtonView_EmotionButtonView);

        emotionButton.setText(type.toString());
        emotionButton.setTextColor(ContextCompat.getColor(getContext(),type.toColor()));

        return convertView;
    }
}
