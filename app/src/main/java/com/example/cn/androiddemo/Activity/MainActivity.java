package com.example.cn.androiddemo.Activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cn.androiddemo.Model.Person;
import com.example.cn.androiddemo.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 事件处理
        setContentView(R.layout.activity_main);

        listView = (ListView) this.findViewById(R.id.listdata);

        final List<Person> perList = new ArrayList<Person>();
        for(int i=0; i<=10; i++) {
            perList.add(new Person("姓名"+i,"13567899" + i, 200000+""));
        }
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return perList.size();
            }

            @Override
            public Object getItem(int position) {
                return perList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Person person = perList.get(position);

                View view = null;
                if(convertView==null) {
                    view = View.inflate(MainActivity.this, R.layout.list_item_relative, null);
                } else {
                    view = convertView;
                }

                TextView v_name = (TextView) view.findViewById(R.id.text_name);
                TextView v_phone = (TextView) view.findViewById(R.id.text_phone);
                TextView v_salary = (TextView) view.findViewById(R.id.text_salary);

                v_name.setText(person.name);
                v_phone.setText(person.phone);
                v_salary.setText(person.salary);

                return view;
            }
        });

    }
}
