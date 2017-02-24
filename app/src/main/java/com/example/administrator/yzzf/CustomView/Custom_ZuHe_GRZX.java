package com.example.administrator.yzzf.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.yzzf.R;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static com.example.administrator.yzzf.R.string.grzx;

/**
 * Created by Administrator on 2017/2/24 0024.
 */

public class Custom_ZuHe_GRZX extends LinearLayout {
    private ImageView mImageView;
    private TextView mTextView01;
    private TextView mTextView02;
    private Button mButton;
    private LinearLayout mLinearLayout;
    public Custom_ZuHe_GRZX(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_zuhe_grzx, this, true);
        mImageView = (ImageView) findViewById(R.id.custom_grzx_zuhe_image);
        mTextView01 = (TextView) findViewById(R.id.custom_grzx_zuhe_text01);
        mTextView02 = (TextView) findViewById(R.id.custom_grzx_zuhe_text02);
        mButton = (Button) findViewById(R.id.custom_grzx_zuhe_button);
        mLinearLayout = (LinearLayout) findViewById(R.id.custom_zuhe_linearLayout);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Custom_ZuHe_GRZX);
        int srcId = typedArray.getResourceId(R.styleable.Custom_ZuHe_GRZX_user_src, R.drawable.grzx_user);
        mImageView.setImageResource(srcId);
        String text01 = typedArray.getString(R.styleable.Custom_ZuHe_GRZX_text1);
        mTextView01.setText(text01);
        String text02 = typedArray.getString(R.styleable.Custom_ZuHe_GRZX_text2);
        mTextView02.setText(text02);
        boolean isvisible = typedArray.getBoolean(R.styleable.Custom_ZuHe_GRZX_button_isvisible, false);
        if (isvisible) {
            mButton.setVisibility(VISIBLE);
        } else {
            mButton.setVisibility(INVISIBLE);
        }
        int backgroundId = typedArray.getResourceId(R.styleable.Custom_ZuHe_GRZX_my_background, R.color.toolbar_background);
        mLinearLayout.setBackgroundResource(backgroundId);
        typedArray.recycle();
    }
}
