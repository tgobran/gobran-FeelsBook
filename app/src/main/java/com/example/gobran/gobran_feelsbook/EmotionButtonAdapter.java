/*
 * Class: EmotionButtonAdapter
 *
 * Version: 1.0
 *
 * Date: October 3rd, 2018
 *
 * Description:
 *      Presents a custom ArrayAdapter for an ArrayList of Emotions so that a button for
 *      recording each can be generated
 * Rationale:
 *      This adapter allows for a customized presentation of buttons for each emotion registered
 *      in Emotion. As a result this allows for an easier change of presentation for their buttons.
 *      Furthermore an ArrayAdapter was created for Emotions as this allows for the easier addition
 *      of further emotions as no ui-changes or additional listeners must be created as they will
 *      be handled by this adapter.
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

public class EmotionButtonAdapter extends ArrayAdapter<Emotion> {

    // Receives the arguments for an ArrayAdapter and passes them along to the actual ArrayAdapter
    public EmotionButtonAdapter(Context context, ArrayList<Emotion> emotionsArrayList) {
        super(context, 0, emotionsArrayList);
    }

    // Overrides the getView method by the ArrayAdapter so that a custom presentation can be made
    // for each Emotion button with the specific emotion color being used.
    @Override
    public @NonNull View getView(int position, View convertView,@NonNull ViewGroup parent) {

        // Allows recycling of views for better performance
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_emotion_button,parent,false);
        }

        // Finds the emotion for the given position in the adapter and sets its view to present
        // the emotions string form with its color used in the presentation
        Emotion emotion = getItem(position);
        TextView emotionButton = convertView.findViewById(R.id.emotionButtonView_EmotionButtonView);
        emotionButton.setText(emotion.getString());
        emotionButton.setBackgroundColor(ContextCompat.getColor(getContext(), emotion.getColor()));

        return convertView;
    }
}
