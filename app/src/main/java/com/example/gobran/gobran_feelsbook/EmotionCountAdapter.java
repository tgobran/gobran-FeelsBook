/*
 * Class: EmotionCountAdapter
 *
 * Version: 1.0
 *
 * Date: October 3rd, 2018
 *
 * Description:
 *      Presents a custom ArrayAdapter for an ArrayList of integer counts of emotions so that they
  *     can be presented in custom designed views.
 * Rationale:
 *      Based on the idea of later adding more Emotions this array adapter allows the presentation
 *      of each emotions count to be customized with specific colors and other possible future
 *      elements. In this way the presentation of emotion counts can be handled en-masse, and it
 *      can be later adapted so as to present further details.
 * Outstanding Issues:
 *     The passing of only a count rather then the directly tied emotion produces some worries,
 *     with further future design a new non-enumeration Emotion class that handles its own counts
 *     might be the best option so that the presentation can be improved and riskier searching
 *     of the Emotion type for the specific emotion at each position can be avoided.
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

public class EmotionCountAdapter extends ArrayAdapter<Integer> {

    // Receives the arguments for an ArrayAdapter and passes them along to the actual ArrayAdapter
    public EmotionCountAdapter(Context context, ArrayList<Integer> emotionCounts) {
        super(context, 0, emotionCounts);
    }

    // Overrides the getView method by the ArrayAdapter so that a custom presentation can be made
    // for each Emotion count, with the specific emotion color and name being used.
    @Override
    public @NonNull View getView(int position, View convertView,@NonNull ViewGroup parent) {

        // Allows recycling of views for better performance, no click functionality for counts so
        // they are also disabled
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_emotion_count,parent,false);
            convertView.setEnabled(false);
        }

        // Finds the count for the given position in the adapter and the emotion connected to that
        // array position and sets its view to present the emotions string form with its color and
        // count alongside.
        int count = getItem(position);
        TextView emotionView = convertView.findViewById(R.id.emotionCountView_EmotionView);
        TextView countView = convertView.findViewById(R.id.emotionCountView_CountView);
        emotionView.setText(Emotion.values()[position].getString());
        emotionView.setTextColor(ContextCompat.getColor(getContext(), Emotion.values()[position].getColor()));
        countView.setText(Integer.toString(count));

        return convertView;
    }
}
