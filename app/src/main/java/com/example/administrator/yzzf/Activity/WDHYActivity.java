package com.example.administrator.yzzf.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.administrator.yzzf.CustomView.Custom_ZuHe_GRZX;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.Show_ZNX_Xiexin_Dialog;

/**
 * Created by Administrator on 2017/2/24 0024.
 */

public class WDHYActivity extends AppCompatActivity implements View.OnClickListener {
    //    ImageView mImageView;
//    TextView mTextView01;
//    TextView mTextView02;
//    Button mButton;
    Show_ZNX_Xiexin_Dialog mShow_znx_xiexin_dialog = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wdhy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_wdhy);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Custom_ZuHe_GRZX custom_zuHe_grzx01 = (Custom_ZuHe_GRZX) findViewById(R.id.custom_grzx_zuhe01);
        custom_zuHe_grzx01.setOnClickListener(custom_zuHe_grzx01.getButton(), this);
        custom_zuHe_grzx01.setOnClickListener(custom_zuHe_grzx01.getImageView(), this);
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
        switch (v.getId()) {
            case R.id.custom_grzx_zuhe_image:
                Intent intent01 = new Intent(WDHYActivity.this, HaoYouZiLiaoActivity.class);
                startActivity(intent01);
                break;
            case R.id.custom_grzx_zuhe_button:
//                Intent intent02 = new Intent(WDHYActivity.this, ZNXAllActivity.class);
//                startActivity(intent02);
                if (mShow_znx_xiexin_dialog == null) {
                    mShow_znx_xiexin_dialog = new Show_ZNX_Xiexin_Dialog(WDHYActivity.this);
                }
                mShow_znx_xiexin_dialog.showDialog(R.layout.custom_znx_xiexin_dialog);
                break;
        }
    }
}
