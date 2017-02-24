package com.example.administrator.yzzf.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.administrator.yzzf.CustomView.Custom_ZNX_GRZX;
import com.example.administrator.yzzf.R;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by Administrator on 2017/2/24 0024.
 */

public class ZNXActivity extends AppCompatActivity implements View.OnClickListener {
    private Dialog mDialog;
    private Custom_ZNX_GRZX mCustom_znx_grzx01;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhanneixin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_zhanneixin);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        mCustom_znx_grzx01 = (Custom_ZNX_GRZX) findViewById(R.id.znx_duihua01);
        mCustom_znx_grzx01.setOnClickListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void showDialog() {
        Log.d("ZNXActivity", "------------------------------------------->showDialog");
        if (mDialog == null) {
            mDialog = new Dialog(ZNXActivity.this, R.style.custom_dialog);
            mDialog.setCanceledOnTouchOutside(true);
//            mDialog = new AlertDialog.Builder(ZNXActivity.this, R.style.custom_dialog)
//                    .setCancelable(true)
//                    .create();
            //获取对话框窗口，并设置参数
            Window window = mDialog.getWindow();
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

            //为对话框窗口设置大小参数
            window.setAttributes(layoutParams);
            window.setGravity(Gravity.BOTTOM);
            //为对话框设置自定义布局
            mDialog.setContentView(R.layout.custom_znx_dialog);
            //获取对话框布局里面的控件对象,并设置监听事件
            mDialog.findViewById(R.id.xnx_dialog_huifu).setOnClickListener(this);
            mDialog.findViewById(R.id.xnx_dialog_shanchu).setOnClickListener(this);
            mDialog.findViewById(R.id.xnx_dialog_chakan).setOnClickListener(this);
            mDialog.findViewById(R.id.xnx_dialog_quxiao).setOnClickListener(this);
        }
        mDialog.show();
    }

    private void dismiss() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.znx_duihua01:
                showDialog();
                break;
            case R.id.xnx_dialog_huifu:
                break;
            case R.id.xnx_dialog_shanchu:
                break;
            case R.id.xnx_dialog_chakan:
                break;
            case R.id.xnx_dialog_quxiao:
                dismiss();
                break;
        }
    }
}

