package com.example.gobran.gobran_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private EmotionManagerController emotionManagerController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emotionManagerController= ((FeelsBookApp)getApplication()).getEmotionManagerController();

        Button loveButton = findViewById(R.id.main_Love);
        Button joyButton = findViewById(R.id.main_Joy);
        Button surpriseButton = findViewById(R.id.main_Surprise);
        Button angerButton = findViewById(R.id.main_Anger);
        Button sadnessButton = findViewById(R.id.main_Sadness);
        Button fearButton = findViewById(R.id.main_Fear);

        loveButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                getComment(EmotionType.LOVE);
                return true;
            }
        });
        joyButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                getComment(EmotionType.JOY);
                return true;
            }
        });
        surpriseButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                getComment(EmotionType.SURPRISE);
                return true;
            }
        });
        angerButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                getComment(EmotionType.ANGER);
                return true;
            }
        });
        sadnessButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                getComment(EmotionType.SADNESS);
                return true;
            }
        });
        fearButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                getComment(EmotionType.FEAR);
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode <= EmotionType.FEAR.toId() && requestCode >= EmotionType.LOVE.toId()) {
            if (resultCode == RESULT_OK) {
                emotionManagerController.addRecord(EmotionType.values()[requestCode], new Date(),data.getStringExtra(this.getString(R.string.addCommentResultKey)));
            }
            else {
                emotionManagerController.addRecord(EmotionType.values()[requestCode], new Date(),"");
            }
        }
    }

    public void accessStatistics(View view) {
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }
    public void accessHistory(View view) {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    public void addLove(View view) {
        emotionManagerController.addRecord(EmotionType.LOVE, new Date(),"");
    }
    public void addJoy(View view) {
        emotionManagerController.addRecord(EmotionType.JOY, new Date(),"");
    }
    public void addSurprise(View view) {
        emotionManagerController.addRecord(EmotionType.SURPRISE, new Date(),"");
    }
    public void addAnger(View view) {
        emotionManagerController.addRecord(EmotionType.ANGER, new Date(),"");
    }
    public void addSadness(View view) {
        emotionManagerController.addRecord(EmotionType.SADNESS, new Date(),"");
    }
    public void addFear(View view) {
        emotionManagerController.addRecord(EmotionType.FEAR, new Date(),"");
    }

    public void getComment(EmotionType type) {
        Intent intent = new Intent(this, AddCommentActivity.class);
        startActivityForResult(intent,type.toId());
    }
}
