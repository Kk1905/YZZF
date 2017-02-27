package com.example.administrator.yzzf.CustomView;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.yzzf.Activity.XiangQingActivity;
import com.example.administrator.yzzf.R;

/**
 * Created by Administrator on 2017/2/27 0027.
 */

public class Custom_HTJL_Item extends LinearLayout {
    private ImageView mImageView_userimage;
    private TextView mTextView_username;
    private TextView mTextView_time;
    private TextView mTextView_title;
    private TextView mTextView_zannum;
    private TextView mTextView_pinglunnum;
    private TextView mTextView_chakannum;
    private LinearLayout mLinearLayout_main;
    private LinearLayout mLinearLayout_fenxiang;
    private ImageView mImageView_pinglun;

    public ImageView getImageView_userimage() {
        return mImageView_userimage;
    }

    public TextView getTextView_username() {
        return mTextView_username;
    }

    public TextView getTextView_time() {
        return mTextView_time;
    }

    public TextView getTextView_title() {
        return mTextView_title;
    }

    public TextView getTextView_zannum() {
        return mTextView_zannum;
    }

    public TextView getTextView_pinglunnum() {
        return mTextView_pinglunnum;
    }

    public TextView getTextView_chakannum() {
        return mTextView_chakannum;
    }

    public LinearLayout getLinearLayout_main() {
        return mLinearLayout_main;
    }

    public LinearLayout getLinearLayout_fenxiang() {
        return mLinearLayout_fenxiang;
    }

    public ImageView getImageView_pinglun() {
        return mImageView_pinglun;
    }

    public void setImageView_userimage(int imageView_userimage) {
        mImageView_userimage.setImageResource(imageView_userimage);
    }

    public void setTextView_username(String textView_username) {
        mTextView_username.setText(textView_username);
    }

    public void setTextView_time(String textView_time) {
        mTextView_time.setText(textView_time);
    }

    public void setTextView_title(String textView_title) {
        mTextView_title.setText(textView_title);
    }

    public void setTextView_zannum(String textView_zannum) {
        mTextView_zannum.setText(textView_zannum);
    }

    public void setTextView_pinglunnum(String textView_pinglunnum) {
        mTextView_pinglunnum.setText(textView_pinglunnum);
    }

    public void setTextView_chakannum(String textView_chakannum) {
        mTextView_chakannum.setText(textView_chakannum);
    }

    public Custom_HTJL_Item(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_item_huatijiaoliu, this, true);
        mImageView_userimage = (ImageView) findViewById(R.id.custom_item_huatijiaoliu_userimage);
        mImageView_pinglun = (ImageView) findViewById(R.id.custom_item_huatijiaoliu_pinglun);
        mTextView_username = (TextView) findViewById(R.id.custom_item_huatijiaoliu_username);
        mTextView_time = (TextView) findViewById(R.id.custom_item_huatijiaoliu_time);
        mTextView_title = (TextView) findViewById(R.id.custom_item_huatijiaoliu_title);
        mTextView_chakannum = (TextView) findViewById(R.id.custom_item_huatijiaoliu_chakannum);
        mTextView_zannum = (TextView) findViewById(R.id.custom_item_huatijiaoliu_zannum);
        mTextView_pinglunnum = (TextView) findViewById(R.id.custom_item_huatijiaoliu_pinglunnum);
        mLinearLayout_main = (LinearLayout) findViewById(R.id.custom_item_huatijiaoliu_main);
        mLinearLayout_fenxiang = (LinearLayout) findViewById(R.id.custom_item_huatijiaoliu_fenxiang);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Custom_HTJL_Item);
        int userimage_src = typedArray.getResourceId(R.styleable.Custom_HTJL_Item_htjl_userimage_src, R.drawable.user);
        mImageView_userimage.setImageResource(userimage_src);
        String username = typedArray.getString(R.styleable.Custom_HTJL_Item_htjl_username);
        mTextView_username.setText(username);
        String time = typedArray.getString(R.styleable.Custom_HTJL_Item_htjl_time);
        mTextView_time.setText(time);
        String title = typedArray.getString(R.styleable.Custom_HTJL_Item_htjl_title);
        mTextView_title.setText(title);
        String chakannum = typedArray.getString(R.styleable.Custom_HTJL_Item_htjl_chakannum);
        mTextView_chakannum.setText(chakannum);
        String zannum = typedArray.getString(R.styleable.Custom_HTJL_Item_htjl_zannum);
        mTextView_zannum.setText(zannum);
        String pinglunnum = typedArray.getString(R.styleable.Custom_HTJL_Item_htjl_pinglunnum);
        mTextView_pinglunnum.setText(pinglunnum);
        typedArray.recycle();
    }
    public void setOnClickListener(View view,OnClickListener onClickListener) {
        switch (view.getId()) {
            case R.id.custom_item_huatijiaoliu_userimage:
                //可以跳转到好友资料Activity，但目前不确定不是好友能不能查看资料
                break;
            case R.id.custom_item_huatijiaoliu_main:
                //一般可以让其跳转到帖子的详情Activity
                mLinearLayout_main.setOnClickListener(onClickListener);
                break;
            case R.id.custom_item_huatijiaoliu_pinglun:
                //一般弹出输入文字的对话框
                mImageView_pinglun.setOnClickListener(onClickListener);
                break;
            case R.id.custom_item_huatijiaoliu_fenxiang:
                mLinearLayout_fenxiang.setOnClickListener(onClickListener);
                break;
        }
    }
}
