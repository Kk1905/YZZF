package com.example.administrator.yzzf.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.RadioGroup;

import com.example.administrator.yzzf.R;
import com.example.administrator.yzzf.Util.ActivityStack;
import com.example.administrator.yzzf.Util.MyApplication;

/**
 * Created by Administrator on 2017/3/30 0030.
 */

public class TestActivity02 extends AppCompatActivity {
    private WebView mWebView;
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test02);
        url = getIntent().getStringExtra("url");
        mWebView = (WebView) findViewById(R.id.test02_web_view);
        mWebView.loadUrl(url);

        ActivityStack.getInstance().addActivity(this);
    }
}
