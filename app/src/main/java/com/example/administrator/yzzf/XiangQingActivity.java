package com.example.administrator.yzzf;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.EditText;

/**
 * Created by Administrator on 2017/2/19 0019.
 */

public class XiangQingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_xiangqing);
        toolbar.showOverflowMenu();
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        actionBar.setDisplayHomeAsUpEnabled(true);

        EditText editText = (EditText) findViewById(R.id.woyao_edittext);
        editText.clearFocus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
