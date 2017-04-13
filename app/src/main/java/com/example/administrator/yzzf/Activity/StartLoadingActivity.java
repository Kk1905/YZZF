package com.example.administrator.yzzf.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.Constant;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class StartLoadingActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mImageView_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_loading);

        mImageView_button = (ImageView) findViewById(R.id.start_loading_button);
        mImageView_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_loading_button:
                Intent intent = new Intent(StartLoadingActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
