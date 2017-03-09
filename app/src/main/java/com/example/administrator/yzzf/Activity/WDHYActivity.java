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

import com.example.administrator.yzzf.Fragment.WDHY_WDGZ_Fragment;
import com.example.administrator.yzzf.Fragment.WDHY_WDHY_Fragment;
import com.example.administrator.yzzf.R;

/**
 * Created by Administrator on 2017/2/24 0024.
 */

public class WDHYActivity extends AppCompatActivity implements View.OnClickListener {
    Fragment mFragment;
    CheckedTextView mCheckedTextView_wdhy;
    CheckedTextView mCheckedTextView_wdgz;

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
        initView();
        mCheckedTextView_wdhy = (CheckedTextView) findViewById(R.id.wdhy_wdhy);
        mCheckedTextView_wdhy.setOnClickListener(this);
        mCheckedTextView_wdgz = (CheckedTextView) findViewById(R.id.wdhy_wdgz);
        mCheckedTextView_wdgz.setOnClickListener(this);
    }

    private void initView() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        mFragment = fragmentManager.findFragmentById(R.id.WDHY_fragment_container);
        if (mFragment == null) {
            mFragment = new WDHY_WDHY_Fragment();
            fragmentManager.beginTransaction()
                    .add(R.id.WDHY_fragment_container, mFragment)
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
            case R.id.wdhy_wdhy:
                if (mFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.WDHY_fragment_container, new WDHY_WDHY_Fragment()).commit();
                }
                mCheckedTextView_wdhy.setChecked(true);
                mCheckedTextView_wdgz.setChecked(false);
                break;
            case R.id.wdhy_wdgz:
                if (mFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.WDHY_fragment_container, new WDHY_WDGZ_Fragment()).commit();
                }
                mCheckedTextView_wdhy.setChecked(false);
                mCheckedTextView_wdgz.setChecked(true);
                break;
        }
    }
}
