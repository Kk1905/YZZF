package com.example.administrator.yzzf.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.yzzf.R;

/**
 * Created by Administrator on 2017/2/24 0024.
 */

public class Custom_ZNX_GRZX extends LinearLayout {
    private ImageView mImageView;
    private TextView mTextView_username;
    private TextView mTextView_time;
    private TextView mTextView_num;
    private TextView mTextView_content;

    public ImageView getImageView() {
        return mImageView;
    }

    public TextView getTextView_username() {
        return mTextView_username;
    }

    public TextView getTextView_time() {
        return mTextView_time;
    }

    public TextView getTextView_num() {
        return mTextView_num;
    }

    public TextView getTextView_content() {
        return mTextView_content;
    }

    public void setImageView(int imageView_src) {
        mImageView.setImageResource(imageView_src);
    }

    public void setTextView_username(String textView_username) {
        mTextView_username.setText(textView_username);
    }

    public void setTextView_time(String textView_time) {
        mTextView_time.setText(textView_time);
    }

    public void setTextView_num(String textView_num) {
        mTextView_num.setText(textView_num);
    }

    public void setTextView_content(String textView_content) {
        mTextView_content.setText(textView_content);
    }

    public Custom_ZNX_GRZX(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_zhanneixin_duihua, this, true);
        mImageView = (ImageView) findViewById(R.id.custom_zhanneixin_duihua_userimage);
        mTextView_username = (TextView) findViewById(R.id.custom_zhanneixin_duihua_username);
        mTextView_time = (TextView) findViewById(R.id.custom_zhanneixin_duihua_time);
        mTextView_num = (TextView) findViewById(R.id.custom_zhanneixin_duihua_num);
        mTextView_content = (TextView) findViewById(R.id.custom_zhanneixin_duihua_content);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Custom_ZNX_GRZX);
        int userImageId = typedArray.getResourceId(R.styleable.Custom_ZNX_GRZX_znx_user_src, R.drawable.grzx_user);
        mImageView.setImageResource(userImageId);
        String username = typedArray.getString(R.styleable.Custom_ZNX_GRZX_znx_username);
        mTextView_username.setText(username);
        String time = typedArray.getString(R.styleable.Custom_ZNX_GRZX_znx_time);
        mTextView_time.setText(time);
        String num = typedArray.getString(R.styleable.Custom_ZNX_GRZX_znx_num);
        mTextView_num.setText(num);
        String content = typedArray.getString(R.styleable.Custom_ZNX_GRZX_znx_content);
        mTextView_content.setText(content);
        typedArray.recycle();
    }

}
