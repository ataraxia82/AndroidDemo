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

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.widget.Toast;
import android.util.Log;
import com.loopj.android.image.SmartImageView;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 事件处理
        setContentView(R.layout.activity_main);

        listView = (ListView) this.findViewById(R.id.listdata);

        // 配置访问后台请求的参数信息
        RequestParams params = new RequestParams("http://www.jxy-edu.com/ajaxServlet");
        // 发送get请求
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override  // 如果请求成功则请求结果在result中
            public void onSuccess(String result) {
                //Log.i("zp","result:" + result);
                // 用来显示请求的结果  json --> List
                Gson gson =new Gson();
                final  List<Person> perList =gson.fromJson(result, new TypeToken<List<Person>>(){}.getType());

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

//                        TextView v_name = (TextView) view.findViewById(R.id.text_name);
                        SmartImageView v_name = (SmartImageView) view.findViewById(R.id.image_name);
                        TextView v_tel = (TextView) view.findViewById(R.id.text_phone);
                        TextView v_salary = (TextView) view.findViewById(R.id.text_salary);

//                        v_name.setText(person.name);
                        v_name.setImageUrl(person.name); //显示图片
                        v_tel.setText(person.tel);
                        v_salary.setText(person.salary + "");

                        return view;
                    }
                });

            }

            @Override  // 如果请求失败.则.......
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override   // 请求取消
            public void onCancelled(CancelledException cex) {
                Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
            }

            @Override  // 无论成功,还是失败,都称为请求完成.....
            public void onFinished() {

            }
        });

//        final List<Person> perList = new ArrayList<Person>();
//        for(int i=0; i<=10; i++) {
//            perList.add(new Person("姓名"+i,"13567899" + i, 200000+""));
//        }
//        listView.setAdapter(new BaseAdapter() {
//            @Override
//            public int getCount() {
//                return perList.size();
//            }
//
//            @Override
//            public Object getItem(int position) {
//                return perList.get(position);
//            }
//
//            @Override
//            public long getItemId(int position) {
//                return position;
//            }
//
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                Person person = perList.get(position);
//
//                View view = null;
//                if(convertView==null) {
//                    view = View.inflate(MainActivity.this, R.layout.list_item_relative, null);
//                } else {
//                    view = convertView;
//                }
//
//                TextView v_name = (TextView) view.findViewById(R.id.text_name);
//                TextView v_phone = (TextView) view.findViewById(R.id.text_phone);
//                TextView v_salary = (TextView) view.findViewById(R.id.text_salary);
//
//                v_name.setText(person.name);
//                v_phone.setText(person.phone);
//                v_salary.setText(person.salary);
//
//                return view;
//            }
//        });

    }
}
