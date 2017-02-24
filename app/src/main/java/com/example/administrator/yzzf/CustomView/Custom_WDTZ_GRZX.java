package com.example.administrator.yzzf.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.yzzf.R;

/**
 * Created by Administrator on 2017/2/24 0024.
 */

public class Custom_WDTZ_GRZX extends LinearLayout {
    private ImageView mImageView_user;
    private TextView mTextView_user;
    private TextView mTextView_time;
    private TextView mTextView_num;
    private TextView mTextView_content;
    private ImageButton mImageButton_shanchu;

    public ImageView getImageView_user() {
        return mImageView_user;
    }

    public TextView getTextView_user() {
        return mTextView_user;
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

    public ImageView getImageView_shanchu() {
        return mImageButton_shanchu;
    }

    public Custom_WDTZ_GRZX(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_item_wdtz, this, true);
        mImageView_user = (ImageView) findViewById(R.id.custom_item_wdtz_user);
        mImageButton_shanchu = (ImageButton) findViewById(R.id.custom_item_wdtz_shanchu);
        mTextView_user = (TextView) findViewById(R.id.custom_item_wdtz_username);
        mTextView_time = (TextView) findViewById(R.id.custom_item_wdtz_time);
        mTextView_num = (TextView) findViewById(R.id.custom_item_wdtz_num);
        mTextView_content = (TextView) findViewById(R.id.custom_item_wdtz_content);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Custom_WDTZ_GRZX);
        int user_image_id = typedArray.getResourceId(R.styleable.Custom_WDTZ_GRZX_user_image_src, R.drawable.grzx_user);
        mImageView_user.setImageResource(user_image_id);
        String user_name = typedArray.getString(R.styleable.Custom_WDTZ_GRZX_user_name);
        mTextView_user.setText(user_name);
        String time = typedArray.getString(R.styleable.Custom_WDTZ_GRZX_time);
        mTextView_time.setText(time);
        String num = typedArray.getString(R.styleable.Custom_WDTZ_GRZX_num);
        mTextView_num.setText(num);
        String content = typedArray.getString(R.styleable.Custom_WDTZ_GRZX_content);
        mTextView_content.setText(content);
        typedArray.recycle();
    }
}
