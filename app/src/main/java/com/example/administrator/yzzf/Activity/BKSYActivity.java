package com.example.administrator.yzzf.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.administrator.yzzf.R;

/**
 * Created by Administrator on 2017/2/28 0028.
 */

public class BKSYActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bksy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_bksy);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        toolbar.findViewById(R.id.toolbar_bksy_sousuo).setOnClickListener(this);
        findViewById(R.id.bksy_imageview_fatie).setOnClickListener(this);
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
            case R.id.toolbar_bksy_sousuo:
                Intent intent = new Intent(this, SouSuoActivity.class);
                startActivity(intent);
                break;
            case R.id.bksy_imageview_fatie:
                Intent intent1 = new Intent(this, FaTieActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
