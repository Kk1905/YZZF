package com.example.administrator.yzzf.Util;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/12 0012.
 */

public class ActivityStack {
    private List<AppCompatActivity> activityList = new LinkedList();
    private static ActivityStack instance;

    private ActivityStack() {
    }

    //单例模式中获取唯一的ExitApplication实例
    public static ActivityStack getInstance() {
        if (null == instance) {
            instance = new ActivityStack();
        }
        return instance;

    }

    //添加Activity到容器中
    public void addActivity(AppCompatActivity activity) {
        activityList.add(activity);
    }

    //遍历所有Activity并finish
    public void exit() {

        for (AppCompatActivity activity : activityList) {
            activity.finish();
        }

        System.exit(0);

    }
}
