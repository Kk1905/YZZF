package com.example.administrator.yzzf.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.administrator.yzzf.Util.Constant;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getSharedPreferences(Constant.YZZF_APP, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        //判断如果是第一次进入APP，那么改isFirstIn=false
        if (preferences.getBoolean("isFirstIn", true)) {
            Intent intent = new Intent(SplashActivity.this, StartLoadingActivity.class);
            startActivity(intent);

            editor.putBoolean("isFirstIn", false);
            editor.commit();
        } else {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        }



    }
}
