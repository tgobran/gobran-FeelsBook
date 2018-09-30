package com.example.gobran.gobran_feelsbook;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CountAdapter extends ArrayAdapter<Integer> {
    public CountAdapter(Context context, ArrayList<Integer> counts) {
        super(context, 0, counts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int count = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.count_view,parent,false);
        }

        TextView emotionView = convertView.findViewById(R.id.countView_EmotionView);
        TextView countView = convertView.findViewById(R.id.countView_CountView);

        emotionView.setText(EmotionType.values()[position].toString()+":");
        emotionView.setTextColor(ContextCompat.getColor(getContext(),EmotionType.values()[position].toColor()));
        countView.setText(Integer.toString(count));

        return convertView;
    }
}
