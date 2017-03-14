package com.example.administrator.yzzf.Util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 利用SharedPreferences对数据持久化的工具
 * Created by Administrator on 2017/3/10 0010.
 *
 * @author Likeke
 */

public class PreferencesUtil {
    //保存字符串类型的值
    public static void write(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.YZZF_APP, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key, value).commit();
    }

    public static void write(Context context, String key, int value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.YZZF_APP, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(key, value).commit();
    }

    public static void write(Context context, String key, Boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.YZZF_APP, Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(key, value).commit();
    }

    //读取字符串类型的值
    public static String readString(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.YZZF_APP, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }

    public static int readInt(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.YZZF_APP,
                Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, 0);
    }

    public static Boolean readBoolean(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.YZZF_APP,
                Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    //删除一项数据
    public static void remove(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.YZZF_APP,
                Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(key).commit();
    }
}
