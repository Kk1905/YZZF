package com.example.administrator.yzzf.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.ActivityStack;
import com.example.administrator.yzzf.Util.MyApplication;

/**
 * Created by Administrator on 2017/2/24 0024.
 */

public class XTXXActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xtxx);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_xtxx);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        findViewById(R.id.custom_xtxx_guanyuwomen).setOnClickListener(this);
        findViewById(R.id.custom_xtxx_banben).setOnClickListener(this);
        findViewById(R.id.custom_xtxx_xitonggengxin).setOnClickListener(this);
        findViewById(R.id.custom_xtxx_tuichu).setOnClickListener(this);

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
        switch (v.getId()) {
            case R.id.custom_xtxx_guanyuwomen:
                Toast.makeText(XTXXActivity.this, R.string.xtxx_about, Toast.LENGTH_SHORT).show();
                break;
            case R.id.custom_xtxx_banben:
                Toast.makeText(XTXXActivity.this, R.string.xtxx_banben, Toast.LENGTH_SHORT).show();
                break;
            case R.id.custom_xtxx_xitonggengxin:
                Toast.makeText(XTXXActivity.this, R.string.xtxx_gengxin, Toast.LENGTH_SHORT).show();
                break;
            case R.id.custom_xtxx_tuichu:
                AlertDialog dialog = new AlertDialog.Builder(XTXXActivity.this)
                        .setCancelable(true)
                        .setMessage(R.string.xtxx_exit)
                        .setNegativeButton(R.string.quxiao, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton(R.string.queding, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(final DialogInterface dialog, int which) {
                                dialog.dismiss();
                                ActivityStack.getInstance().exit();
                            }
                        }).create();
                dialog.show();
                break;
        }
    }
}
