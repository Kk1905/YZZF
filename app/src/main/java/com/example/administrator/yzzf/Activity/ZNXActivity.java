package com.example.administrator.yzzf.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckedTextView;

import com.example.administrator.yzzf.Fragment.ZNX_YKG_Fragment;
import com.example.administrator.yzzf.Fragment.ZNX_WKG_Fragment;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.ActivityStack;
import com.example.administrator.yzzf.Util.MyApplication;

/**
 * Created by Administrator on 2017/2/24 0024.
 */

public class ZNXActivity extends AppCompatActivity implements View.OnClickListener {
    Fragment fragment;
    CheckedTextView mCheckedTextView_ykg;
    CheckedTextView mCheckedTextView_wkg;

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
        initView();

        mCheckedTextView_ykg = (CheckedTextView) findViewById(R.id.znx_ykg);
        mCheckedTextView_ykg.setOnClickListener(this);
        mCheckedTextView_wkg = (CheckedTextView) findViewById(R.id.znx_wkg);
        mCheckedTextView_wkg.setOnClickListener(this);

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

    private void initView() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragment = fragmentManager.findFragmentById(R.id.znx_container);
        if (fragment == null) {
            fragment = new ZNX_YKG_Fragment();
            fragmentManager.beginTransaction()
                    .add(R.id.znx_container, fragment)
                    .commit();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.znx_ykg:
                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.znx_container, new ZNX_YKG_Fragment()).commit();
                }
                mCheckedTextView_ykg.setChecked(true);
                mCheckedTextView_wkg.setChecked(false);
                break;
            case R.id.znx_wkg:
                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.znx_container, new ZNX_WKG_Fragment()).commit();
                }
                mCheckedTextView_ykg.setChecked(false);
                mCheckedTextView_wkg.setChecked(true);
                break;
//            case R.id.xnx_dialog_huifu:
//                break;
//            case R.id.xnx_dialog_shanchu:
//                break;
//            case R.id.xnx_dialog_chakan:
//                break;
//            case R.id.xnx_dialog_quxiao:
//                dismiss();
//                break;
        }
    }
}

