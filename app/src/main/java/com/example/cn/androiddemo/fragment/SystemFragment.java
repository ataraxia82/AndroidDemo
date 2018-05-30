package com.example.cn.androiddemo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cn.androiddemo.Activity.MainActivity;
import com.example.cn.androiddemo.Model.Person;
import com.example.cn.androiddemo.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.image.SmartImageView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SystemFragment extends Fragment {


    public SystemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_system, container, false);

        final ListView listView = (ListView) v.findViewById(R.id.listdata);

        // 配置访问后台请求的参数信息
        RequestParams params = new RequestParams("http://www.jxy-edu.com/ajaxServlet");
        // 发送get请求
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override  // 如果请求成功则请求结果在result中
            public void onSuccess(String result) {
                //Log.i("zp","result:" + result);
                // 用来显示请求的结果  json --> List

                Gson gson =new Gson();
                final List<Person> perList =gson.fromJson(result, new TypeToken<List<Person>>(){}.getType());
                Log.i("zp", "perList size : "+ perList.size());
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
                            view = View.inflate(getActivity(), R.layout.list_item_relative, null);
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



        return v;
    }

}
