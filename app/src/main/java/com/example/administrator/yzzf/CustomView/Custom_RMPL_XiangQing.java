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
 * Created by Administrator on 2017/2/27 0027.
 */

public class Custom_RMPL_XiangQing extends LinearLayout {
    private ImageView mImageView_user;
    private TextView mTextView_username;
    private TextView mTextView_time01;
    private TextView mTextView_num;
    private TextView mTextView_content;
    private ImageView mImageView_zan;
    private TextView mTextView_time02;
    private TextView mTextView_jubao;
    private LinearLayout ll;


    public ImageView getImageView_user() {
        return mImageView_user;
    }

    public TextView getTextView_username() {
        return mTextView_username;
    }

    public TextView getTextView_time01() {
        return mTextView_time01;
    }

    public TextView getTextView_num() {
        return mTextView_num;
    }

    public TextView getTextView_content() {
        return mTextView_content;
    }

    public ImageView getImageView_zan() {
        return mImageView_zan;
    }

    public TextView getTextView_time02() {
        return mTextView_time02;
    }

    public TextView getTextView_jubao() {
        return mTextView_jubao;
    }

    public LinearLayout getLl() {
        return ll;
    }

    public void setImageView_user(ImageView imageView_user) {
        mImageView_user = imageView_user;
    }

    public void setTextView_username(TextView textView_username) {
        mTextView_username = textView_username;
    }

    public void setTextView_time01(TextView textView_time01) {
        mTextView_time01 = textView_time01;
    }

    public void setTextView_num(TextView textView_num) {
        mTextView_num = textView_num;
    }

    public void setTextView_content(TextView textView_content) {
        mTextView_content = textView_content;
    }

    public void setTextView_time02(TextView textView_time02) {
        mTextView_time02 = textView_time02;
    }

    public void setLl(boolean isVisible) {
        if (isVisible) {
            ll.setVisibility(VISIBLE);
        } else {
            ll.setVisibility(GONE);
        }
    }

    public Custom_RMPL_XiangQing(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.custom_item_rmpl, this, true);
        mImageView_user = (ImageView) findViewById(R.id.custom_rmpl_useriamge);
        mTextView_username = (TextView) findViewById(R.id.custom_rmpl_username);
        mTextView_content = (TextView) findViewById(R.id.custom_rmpl_content);
        mTextView_time01 = (TextView) findViewById(R.id.custom_rmpl_time01);
        mTextView_time02 = (TextView) findViewById(R.id.custom_rmpl_time02);
        mTextView_num = (TextView) findViewById(R.id.custom_rmpl_num);
        mImageView_zan = (ImageView) findViewById(R.id.custom_rmpl_zanimage);
        mTextView_jubao = (TextView) findViewById(R.id.custom_rmpl_jubao);
        ll = (LinearLayout) findViewById(R.id.custom_rmpl_visible_ll);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.Custom_RMPL_XiangQing);
        int userimageId = typedArray.getResourceId(R.styleable.Custom_RMPL_XiangQing_rmpl_userimage_src, R.drawable.grzx_user);
        mImageView_user.setImageResource(userimageId);
        String username = typedArray.getString(R.styleable.Custom_RMPL_XiangQing_rmpl_username);
        mTextView_username.setText(username);
        String num = typedArray.getString(R.styleable.Custom_RMPL_XiangQing_rmpl_num);
        mTextView_num.setText(num);
        String time01 = typedArray.getString(R.styleable.Custom_RMPL_XiangQing_rmpl_time01);
        mTextView_time01.setText(time01);
        String time02 = typedArray.getString(R.styleable.Custom_RMPL_XiangQing_rmpl_time02);
        mTextView_time02.setText(time02);
        String content = typedArray.getString(R.styleable.Custom_RMPL_XiangQing_rmpl_content);
        mTextView_content.setText(content);
        boolean isvisible = typedArray.getBoolean(R.styleable.Custom_RMPL_XiangQing_rmpl_is_ll_visible, false);
        if (isvisible) {
            ll.setVisibility(VISIBLE);
        } else {
            ll.setVisibility(GONE);
        }
        typedArray.recycle();
    }
}
