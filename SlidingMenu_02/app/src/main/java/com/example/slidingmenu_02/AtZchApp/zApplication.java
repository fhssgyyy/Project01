package com.example.slidingmenu_02.AtZchApp;

import android.app.Application;

import org.xutils.x;

/**
 * Created by 张成豪 on 2017/3/10.
 */

public class zApplication extends Application {
    @Override
    public void onCreate() {
        //初始化xutils
        x.Ext.init(this);
        //关闭debug模式提高性能
        x.Ext.setDebug(false);
    }
}
