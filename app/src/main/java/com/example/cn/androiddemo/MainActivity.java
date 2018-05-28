package com.example.cn.androiddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 事件处理
        setContentView(R.layout.activity_main);

        listView = (ListView) this.findViewById(R.id.listdata);

    }
}
