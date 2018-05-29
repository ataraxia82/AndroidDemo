package com.example.cn.androiddemo.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cn.androiddemo.R;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

    private ViewPager viewPager;

    public void startMainActivity(View view) {
        SharedPreferences sp = getSharedPreferences("welcome_xml", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("welcome", true).commit();

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        viewPager = (ViewPager) findViewById(R.id.vp);
        //添加引导图片
        int[] resId = new int[]{R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};

        final List<View> ilist = new ArrayList<View>();
        for(int i=0; i<resId.length;i++){
            ImageView iv = new ImageView(this);
            iv.setImageResource(resId[i]);
            iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
            ilist.add(iv);
        }

        //last one
        ilist.add(View.inflate(this, R.layout.start_main, null));

        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return ilist.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View iv = (View) ilist.get(position);
                container.addView(iv);
                return iv;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });
    }
}
