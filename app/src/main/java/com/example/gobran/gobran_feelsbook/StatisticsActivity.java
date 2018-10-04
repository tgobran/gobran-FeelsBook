/*
 * Class: Statistics Activity
 *
 * Version: 1.0
 *
 * Date: October 3rd, 2018
 *
 * Description:
 *      The activity in charge of presenting the statistics view of the app, it shows the various
 *      emotion counts for each emotion.
 * Rationale:
 *      This view presents the counts for each emotion to the user, by using a listView it allows
 *      later expandable amounts of emotions should further app development move in that direction.
 * Outstanding Issues:
 *      The view is relatively sparse now, but this issue can be remedied in the future by adding
 *      more emotions and including more data that could be presented for each emotions usage
 */


package com.example.gobran.gobran_feelsbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class StatisticsActivity extends AppCompatActivity {

    // Holds the ArrayAdapter used to hold the counts for presentation in its listView
    private EmotionCountAdapter emotionCountAdapter;

    // Establishes the basic UI for the activity and builds the listView of counts
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        // Using the controller retrieves the ArrayList of emotion counts and creates an adapter
        // for them that will display them in the statistic activities listView
        ListView emotionCountList = findViewById(R.id.statisticsActivity_EmotionCountsList);
        emotionCountAdapter = new EmotionCountAdapter(this,FeelsBookApp.getEmotionManagerController().getEmotionCounts());
        emotionCountList.setAdapter(emotionCountAdapter);
    }

    // Refreshes the count adapter on resume so that any changes made in other activities are present
    @Override
    protected void onResume() {
        super.onResume();
        emotionCountAdapter.notifyDataSetChanged();
    }
}
