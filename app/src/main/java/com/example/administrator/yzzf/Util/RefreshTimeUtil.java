package com.example.administrator.yzzf.Util;

import android.annotation.TargetApi;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.Date;

/**
 * 用于记录上次刷新时间的工具
 * Created by Administrator on 2017/3/10 0010.
 * @author Likeke
 */

public class RefreshTimeUtil {
    //根据界面的Type来设置刷新时间
    @TargetApi(Build.VERSION_CODES.N)
    public static void setRefreshTime(Context context, String type) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        PreferencesUtil.write(context, type, dateFormat.format(new Date()));
    }

    //根据界面的Type来显示上次的刷新时间
    public static String  getRefreshTime(Context context, String type) {
        String refreshTime = PreferencesUtil.readString(context, type);
        if (TextUtils.isEmpty(refreshTime)) {
            Toast.makeText(context, "好笨，我忘了~~", Toast.LENGTH_SHORT).show();
        }
        return refreshTime;
    }
}
