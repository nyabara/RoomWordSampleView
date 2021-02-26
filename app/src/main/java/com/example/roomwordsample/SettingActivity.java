package com.example.roomwordsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {
    TextView textDisplay;
    EditText mEditText;
    Button mSave, mApply;
    Switch mSwitch;
    public static final String SHARED_PREFES="com.example.roomwordsample.sharedPrefes";
    public static final String TEXT="com.example.roomwordsample.text";
    public static final String SWITCH1="com.example.roomwordsample.switch1";
    private String text;
    private boolean switchOnOff;
    private WordViewModel mWordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        textDisplay=findViewById(R.id.textView1);
        mEditText=findViewById(R.id.editText1);
        mSave=findViewById(R.id.save);
        mApply=findViewById(R.id.apply);
        mSwitch=findViewById(R.id.switchButton);
        mWordViewModel=new ViewModelProvider.AndroidViewModelFactory(this.getApplication()).create(WordViewModel.class);

        mApply.setOnClickListener(view ->{
            textDisplay.setText(mEditText.getText().toString());
        });
        mSave.setOnClickListener(view -> {
            if(mSwitch.isChecked()){
                mWordViewModel.appyWork();
            }
            saveData();
        });
        loadData();
        UpdateViews();
    }

    private void saveData() {
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFES,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(TEXT,textDisplay.getText().toString());
        editor.putBoolean(SWITCH1,mSwitch.isChecked());
        editor.apply();
    }
    public void loadData(){
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFES,MODE_PRIVATE);
       text=sharedPreferences.getString(TEXT,"");
       switchOnOff=sharedPreferences.getBoolean(SWITCH1,false);
       if (mSwitch.isChecked()){
           //showNotificationReminder();
       }

    }

    private void showNotificationReminder() {
        mWordViewModel.appyWork();
    }

    public void UpdateViews(){
        textDisplay.setText(text);
        mSwitch.setChecked(switchOnOff);
    }
}