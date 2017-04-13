package com.example.administrator.yzzf.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.ActivityStack;
import com.example.administrator.yzzf.Util.MyApplication;

/**
 * Created by Administrator on 2017/2/22 0022.
 */

public class MeiRiJingXuanActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meirijingxuan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //设置actionbar启用home键，并将其作为返回键使用。有两种方式为其设置监听
            //这里选择为此activity设置父activity
//            actionBar.setDisplayHomeAsUpEnabled(true);
            //将默认标题隐藏
            actionBar.setDisplayShowTitleEnabled(false);
        }
        TextView title = (TextView) findViewById(R.id.login_toolbar_title);
        title.setText(R.string.meirijingxuan);
        findViewById(R.id.toolbar_up).setOnClickListener(this);
        View xinwenView = findViewById(R.id.item_recyclerview);
        xinwenView.setOnClickListener(this);

        ActivityStack.getInstance().addActivity(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_recyclerview:
                Intent intent = new Intent(MeiRiJingXuanActivity.this, XiangQingActivity.class);
                startActivity(intent);
                break;
            case R.id.toolbar_up:
                finish();
                break;
        }
    }
}
