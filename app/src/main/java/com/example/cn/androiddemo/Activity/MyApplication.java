package com.example.cn.androiddemo.Activity;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Administrator on 2018/5/28.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true); // 是否输出debug日志, 开启debug会影响性能.
    }
}
