package com.example.administrator.yzzf.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yzzf.R;

/**
 * Created by Administrator on 2017/2/19 0019.
 */

public class XiangQingActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView mImageView_fenxiang;
    Dialog mDialog;
    AlertDialog alertDialog;
//    ImageView pengyouquan, weixin, qq, qq_kongjian, weibo, shuaxin, fuzhi_lianjie, jubao;
//    TextView button_quxiao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_xiangqing);
        toolbar.showOverflowMenu();
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        actionBar.setDisplayHomeAsUpEnabled(true);

        mImageView_fenxiang = (ImageView) findViewById(R.id.pinglun_fenxiang);
        EditText editText = (EditText) findViewById(R.id.woyao_edittext);
//        editText.clearFocus();
        mImageView_fenxiang.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_dingwei:
                return true;
            case R.id.menu_login:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pinglun_fenxiang:
//                Toast.makeText(this, "点击分享按钮", Toast.LENGTH_SHORT).show();
                showDialog();
                break;
            case R.id.xiangqing_dialog_quxiao:
                Toast.makeText(this, "点击分享按钮", Toast.LENGTH_SHORT).show();
                dismissDialog();
                break;
            case R.id.xiangqing_dialog_weixin_pengyouquan:
                break;
            case R.id.xiangqing_dialog_weixin:
                break;
            case R.id.xiangqing_dialog_qq:
                break;
            case R.id.xiangqing_dialog_qqkongjian:
                break;
            case R.id.xiangqing_dialog_weibo:
                break;
            case R.id.xiangqing_dialog_shuaxin:
                break;
            case R.id.xiangqing_dialog_fuzhilianjie:
                break;
            case R.id.xiangqing_dialog_jubao:
                break;
        }
    }

    private void showDialog() {

        if (alertDialog == null) {
            View view = LayoutInflater.from(XiangQingActivity.this).inflate(R.layout.custom_xiangqing_fenxiang_dialog, null);
//            mDialog = new Dialog(XiangQingActivity.this, R.style.custom_dialog);
//            mDialog.setCanceledOnTouchOutside(true);
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.custom_dialog);

            alertDialog=builder.setView(R.layout.custom_xiangqing_fenxiang_dialog).setCancelable(true).create();
            Window window = alertDialog.getWindow();
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

            window.setAttributes(layoutParams);
            window.setGravity(Gravity.BOTTOM);
            alertDialog.setContentView(R.layout.custom_xiangqing_fenxiang_dialog);
            view.findViewById(R.id.xiangqing_dialog_weixin_pengyouquan).setOnClickListener(this);
            view.findViewById(R.id.xiangqing_dialog_weixin).setOnClickListener(this);
            view.findViewById(R.id.xiangqing_dialog_qq).setOnClickListener(this);
            view.findViewById(R.id.xiangqing_dialog_weixin_pengyouquan).setOnClickListener(this);
            view.findViewById(R.id.xiangqing_dialog_weibo).setOnClickListener(this);
            view.findViewById(R.id.xiangqing_dialog_shuaxin).setOnClickListener(this);
            view.findViewById(R.id.xiangqing_dialog_fuzhilianjie).setOnClickListener(this);
            view.findViewById(R.id.xiangqing_dialog_jubao).setOnClickListener(this);
            view.findViewById(R.id.xiangqing_dialog_quxiao).setOnClickListener(this);
        }
        alertDialog.show();
    }

    private void dismissDialog() {
        Toast.makeText(this, "点击分享按钮", Toast.LENGTH_SHORT).show();
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }
}
