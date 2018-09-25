package com.example.gobran.gobran_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddCommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);
    }

    public void saveComment(View view) {
        Intent intent = new Intent();
        EditText editText = findViewById(R.id.addComment_EditComment);
        intent.putExtra(this.getString(R.string.addCommentResultKey),editText.getText().toString());
        setResult(RESULT_OK,intent);
        finish();
    }
}
