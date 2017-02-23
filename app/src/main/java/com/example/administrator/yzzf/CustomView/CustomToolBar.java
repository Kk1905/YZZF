package com.example.administrator.yzzf.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.administrator.yzzf.R;

/**
 * Created by Administrator on 2017/2/23 0023.
 */

public class CustomToolBar extends Toolbar {
    private TextView mTextView;

    public TextView getTextView() {
        return mTextView;
    }

    public CustomToolBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_toolbar, this, true);
        mTextView = (TextView) findViewById(R.id.toolbar_custom_title);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomToolBar);
        String title = typedArray.getString(R.styleable.CustomToolBar_customtitle);
        mTextView.setText(title);
    }
}
