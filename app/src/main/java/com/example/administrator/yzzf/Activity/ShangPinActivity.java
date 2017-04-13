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
import android.widget.Toast;

import com.example.administrator.yzzf.CustomView.ImageCycleView;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.ActivityStack;
import com.example.administrator.yzzf.Util.MyApplication;


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
        num = Integer.parseInt(mEditText.getText().toString());

        ActivityStack.getInstance().addActivity(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shangpin_lijigoumai:
                Intent intent = new Intent(this, TiJiaoDingDanActivity.class);
                if (!mEditText.getText().toString().equals("")&& num > 0) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("num", num);
                    bundle.putInt("danjia", 199);
                    bundle.putInt("yunfei",5);
                    intent.putExtra("info", bundle);
                    startActivity(intent);
                } else
                    Toast.makeText(ShangPinActivity.this, "请先确认购买数量", Toast.LENGTH_SHORT).show();

                break;
            case R.id.toolbar_up:
                finish();
                break;
            case R.id.shangpin_image_jia:
                if (mEditText.getText().toString().equals("")) {
                    num = 0;
                } else {
                    num = Integer.parseInt(mEditText.getText().toString());
                }
                mEditText.clearFocus();
                mEditText.setText(++num + "");
                break;
            case R.id.shangpin_image_jian:
                num = Integer.parseInt(mEditText.getText().toString());
                if (num > 0) {
                    mEditText.setText(--num + "");
                } else {
                    mEditText.setText(0 + "");
                }
                mEditText.clearFocus();
        }
    }
}
