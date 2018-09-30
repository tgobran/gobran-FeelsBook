package com.example.gobran.gobran_feelsbook;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements CommentWriterFragment.OnCommentSetListener {
    private EmotionManagerController emotionManagerController;

    private EmotionType typeToAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emotionManagerController= ((FeelsBookApp)getApplication()).getEmotionManagerController();

        Button loveButton = findViewById(R.id.mainActivity_LoveButton);
        Button joyButton = findViewById(R.id.mainActivity_JoyButton);
        Button surpriseButton = findViewById(R.id.mainActivity_SurpriseButton);
        Button angerButton = findViewById(R.id.mainActivity_AngerButton);
        Button sadnessButton = findViewById(R.id.mainActivity_SadnessButton);
        Button fearButton = findViewById(R.id.mainActivity_FearButton);

        Button statisticsButton = findViewById(R.id.mainActivity_StatisticsButton);
        Button historyButton = findViewById(R.id.mainActivity_HistoryButton);

        loveButton.setTextColor(ContextCompat.getColor(this,EmotionType.LOVE.toColor()));
        loveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEmotion(EmotionType.LOVE,"");
            }
        });
        loveButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                typeToAdd = EmotionType.LOVE;
                getComment();
                return true;
            }
        });

        joyButton.setTextColor(ContextCompat.getColor(this,EmotionType.JOY.toColor()));
        joyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEmotion(EmotionType.JOY,"");
            }
        });
        joyButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                typeToAdd = EmotionType.JOY;
                getComment();
                return true;
            }
        });

        surpriseButton.setTextColor(ContextCompat.getColor(this,EmotionType.SURPRISE.toColor()));
        surpriseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEmotion(EmotionType.SURPRISE,"");
            }
        });
        surpriseButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                typeToAdd = EmotionType.SURPRISE;
                getComment();
                return true;
            }
        });

        angerButton.setTextColor(ContextCompat.getColor(this,EmotionType.ANGER.toColor()));
        angerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEmotion(EmotionType.ANGER,"");
            }
        });
        angerButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                typeToAdd = EmotionType.ANGER;
                getComment();
                return true;
            }
        });

        sadnessButton.setTextColor(ContextCompat.getColor(this,EmotionType.SADNESS.toColor()));
        sadnessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEmotion(EmotionType.SADNESS,"");
            }
        });
        sadnessButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                typeToAdd = EmotionType.SADNESS;
                getComment();
                return true;
            }
        });

        fearButton.setTextColor(ContextCompat.getColor(this,EmotionType.FEAR.toColor()));
        fearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEmotion(EmotionType.FEAR,"");
            }
        });
        fearButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                typeToAdd = EmotionType.FEAR;
                getComment();
                return true;
            }
        });

        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), HistoryActivity.class);
                startActivity(intent);
            }
        });


        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), StatisticsActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addEmotion(EmotionType type, String comment) {
        emotionManagerController.addRecord(type, Calendar.getInstance(), comment);
        Toast.makeText(this, "Registered " + type.toString(), Toast.LENGTH_SHORT).show();
    }


    public void getComment() {
        DialogFragment newFragment = new CommentWriterFragment();
        newFragment.show(getSupportFragmentManager(),"commentWriter");
    }
    public void onCommentSet(String comment) {
        if(comment != null) {
            addEmotion(typeToAdd,comment);
        }
    }
}
