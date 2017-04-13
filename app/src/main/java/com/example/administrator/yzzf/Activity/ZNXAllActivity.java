package com.example.administrator.yzzf.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.administrator.yzzf.CustomView.Custom_ZNX_GRZX;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.ActivityStack;
import com.example.administrator.yzzf.Util.MyApplication;

/**
 * Created by Administrator on 2017/2/27 0027.
 */

public class ZNXAllActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhanneixin_haoyou);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_zhanneixin);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        Custom_ZNX_GRZX custom_znx_grzx01 = (Custom_ZNX_GRZX) findViewById(R.id.znx_haoyou_duihua01);
        custom_znx_grzx01.setOnClickListener(this);

        ActivityStack.getInstance().addActivity(this);
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

    @Override
    public void onClick(View v) {

    }
}
