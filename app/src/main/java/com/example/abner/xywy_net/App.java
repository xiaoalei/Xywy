package com.example.abner.xywy_net;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Vibrator;
import android.view.Window;

import com.baidu.mapapi.SDKInitializer;
import com.example.abner.xywy_net.base.BaseActivity;
import com.example.abner.xywy_net.base.BaseFragment;
import com.example.abner.xywy_net.controller.activity.zxm.baidumap.LocationService;

/**
 * Created by Abner on 2017/6/9.
 */

public class App extends Application {
    public static BaseFragment lastFragment;
    public static BaseActivity activity;
    public LocationService locationService;
    public Vibrator mVibrator;
    public static App instance;

    public static Application getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        /***
         * 初始化定位sdk，建议在Application中创建
         */
        locationService = new LocationService(getApplicationContext());
        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());

    }
}
