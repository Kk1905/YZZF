package com.example.administrator.yzzf.Activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.yzzf.CustomView.ImageCycleView;
import com.example.administrator.yzzf.R;


/**
 * Created by Administrator on 2017/3/24 0024.
 */

public class ShangPinActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageCycleView mImageCycleView;
    private EditText mEditText;
    private int num;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shangpin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        TextView title = (TextView) findViewById(R.id.login_toolbar_title);
        title.setText(R.string.shangpinxiangqing);
        mImageCycleView = (ImageCycleView) findViewById(R.id.shangpin_image_cycle_view);
        mImageCycleView.pushImageCycle();
        findViewById(R.id.toolbar_up).setOnClickListener(this);
        findViewById(R.id.lunbo_title).setVisibility(View.GONE);
        findViewById(R.id.lunbo_framelayout).setBackground(null);
        mEditText = (EditText) findViewById(R.id.shangpin_num);
        findViewById(R.id.shangpin_image_jia).setOnClickListener(this);
        findViewById(R.id.shangpin_image_jian).setOnClickListener(this);
        findViewById(R.id.shangpin_lijigoumai).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shangpin_lijigoumai:
                Intent intent = new Intent(this, TiJiaoDingDanActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtra("info", bundle);
                startActivity(intent);
                break;
            case R.id.toolbar_up:
                finish();
                break;
            case R.id.shangpin_image_jia:
                num = Integer.parseInt(mEditText.getText().toString());
                mEditText.setText(++num + "");
                break;
            case R.id.shangpin_image_jian:
                num = Integer.parseInt(mEditText.getText().toString());
                if (num > 0) {
                    mEditText.setText(--num + "");
                } else {
                    mEditText.setText(0 + "");
                }
        }
    }
}
