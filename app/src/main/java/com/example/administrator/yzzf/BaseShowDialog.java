package com.example.administrator.yzzf;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Administrator on 2017/2/27 0027.
 */

public class BaseShowDialog {
    protected AlertDialog mAlertDialog;
    protected Activity mActivity;

    public BaseShowDialog(Activity activity) {
        mActivity = activity;
    }

    public void showDialog(int layoutId) {

        if (mAlertDialog == null) {
            View view = LayoutInflater.from(mActivity).inflate(layoutId, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity, R.style.custom_dialog);
            mAlertDialog = builder.setView(view).setCancelable(true).create();
            //获取对话框窗口，并设置参数
            Window window = mAlertDialog.getWindow();
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

            //为对话框窗口设置大小参数
            window.setAttributes(layoutParams);
            window.setGravity(Gravity.BOTTOM);
            //为对话框设置自定义布局
            mAlertDialog.setContentView(view);
        }
        mAlertDialog.show();
        //获取对话框布局里面的控件对象,并设置监听事件
    }

    public void dismiss() {
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }
    }
}
