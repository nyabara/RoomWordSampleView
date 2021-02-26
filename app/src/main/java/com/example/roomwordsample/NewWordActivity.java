package com.example.roomwordsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class NewWordActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    public static final String EXTRA_NUMBER= "com.example.android.wordlistsql.NUMBER";

    private EditText mEditWordView;
    private EditText number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        mEditWordView = findViewById(R.id.edit_word);
        number=findViewById(R.id.edit_number);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditWordView.getText())&&TextUtils.isEmpty(number.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String word = mEditWordView.getText().toString();
                String num=number.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY, word);
                replyIntent.putExtra(EXTRA_NUMBER,num);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}