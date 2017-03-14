package com.example.administrator.yzzf.Util;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * 检查当前手机网络的工具
 * Created by Administrator on 2017/3/10 0010.
 *
 * @author Likeke
 */

public class NetUtil {
    //判断手机是否有任何网络连接
    public static boolean checkNet(Context context) {
        if (isWiFiConnected(context) ==false&&isMobileConnected(context)==false) {
            return false;
        }
        return true;
    }

    //判断手机是否wifi连接
    public static boolean isWiFiConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (info != null && info.isConnected()) {
            return true;
        }
        return false;
    }

    //判断手机是否移动信号连接
    public static boolean isMobileConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (info != null && info.isConnected()) {
            return true;
        }
        return false;
    }
}
