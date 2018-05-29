package com.example.cn.androiddemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cn.androiddemo.Activity.MainActivity;
import com.example.cn.androiddemo.Activity.WelcomeActivity;

import java.util.Timer;
import java.util.TimerTask;

public class IndexActivity extends AppCompatActivity {


    @Override
    protected void onResume() {
        new Timer(true).schedule(new TimerTask() {
            @Override
            public void run() {
                SharedPreferences sp = getSharedPreferences("welcome_xml", MODE_PRIVATE);

                boolean wel = sp.getBoolean("welcome", false);

                if(wel){
                    Intent intent = new Intent(IndexActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(IndexActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                }
            }
        }, 2000);
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
    }
}
