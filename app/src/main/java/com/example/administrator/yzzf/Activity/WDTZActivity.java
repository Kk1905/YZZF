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

import com.example.administrator.yzzf.Fragment.WDTZ_WDHF_Fragment;
import com.example.administrator.yzzf.Fragment.WDTZ_WDTZ_Fragment;
import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.ActivityStack;
import com.example.administrator.yzzf.Util.MyApplication;

/**
 * Created by Administrator on 2017/2/24 0024.
 */

public class WDTZActivity extends AppCompatActivity implements View.OnClickListener {
    Fragment mFragment;
    CheckedTextView mCheckedTextView_wdtz;
    CheckedTextView mCheckedTextView_wdhf;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wdtz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_wdtz);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initView();
        mCheckedTextView_wdtz = (CheckedTextView) findViewById(R.id.wdtz_wdtz);
        mCheckedTextView_wdtz.setOnClickListener(this);
        mCheckedTextView_wdhf = (CheckedTextView) findViewById(R.id.wdtz_wdhf);
        mCheckedTextView_wdhf.setOnClickListener(this);

        ActivityStack.getInstance().addActivity(this);
    }

    private void initView() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        mFragment = fragmentManager.findFragmentById(R.id.wdtz_fragment_container);
        if (mFragment == null) {
            mFragment = new WDTZ_WDTZ_Fragment();
            fragmentManager.beginTransaction()
                    .add(R.id.wdtz_fragment_container, mFragment)
                    .commit();
        }
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
            case R.id.wdtz_wdtz:
                if (mFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.wdtz_fragment_container, new WDTZ_WDTZ_Fragment())
                            .commit();
                }
                mCheckedTextView_wdtz.setChecked(true);
                mCheckedTextView_wdhf.setChecked(false);
                break;
            case R.id.wdtz_wdhf:
                if (mFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.wdtz_fragment_container, new WDTZ_WDHF_Fragment())
                            .commit();
                }
                mCheckedTextView_wdtz.setChecked(false);
                mCheckedTextView_wdhf.setChecked(true);
                break;
        }
    }
}
