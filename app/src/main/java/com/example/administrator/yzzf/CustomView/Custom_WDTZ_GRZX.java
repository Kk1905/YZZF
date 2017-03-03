package com.example.administrator.yzzf.CustomView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yzzf.R;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by Administrator on 2017/2/24 0024.
 */

public class Custom_WDTZ_GRZX extends LinearLayout implements View.OnClickListener{
    private ImageView mImageView_user;
    private TextView mTextView_user;
    private TextView mTextView_time;
    private TextView mTextView_num;
    private TextView mTextView_content;
    private ImageButton mImageButton_shanchu;
    private LinearLayout mLinearLayout;

    public LinearLayout getLinearLayout() {
        return mLinearLayout;
    }

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

    public void setImageView_user(int imageView_user) {
        mImageView_user.setImageResource(imageView_user);
    }

    public void setTextView_user(String textView_user) {
        mTextView_user.setText(textView_user);
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

    public Custom_WDTZ_GRZX(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_item_wdtz, this, true);
        mImageView_user = (ImageView) findViewById(R.id.custom_item_wdtz_user);
        mImageButton_shanchu = (ImageButton) findViewById(R.id.custom_item_wdtz_shanchu);
        mTextView_user = (TextView) findViewById(R.id.custom_item_wdtz_username);
        mTextView_time = (TextView) findViewById(R.id.custom_item_wdtz_time);
        mTextView_num = (TextView) findViewById(R.id.custom_item_wdtz_num);
        mTextView_content = (TextView) findViewById(R.id.custom_item_wdtz_content);
        mLinearLayout = (LinearLayout) findViewById(R.id.custom_wdtz_linearLayout);
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
        boolean is_shanchu_visible = typedArray.getBoolean(R.styleable.Custom_WDTZ_GRZX_is_shanchu_visible, false);
        if (is_shanchu_visible) {
            mImageButton_shanchu.setVisibility(VISIBLE);
        } else {
            mImageButton_shanchu.setVisibility(GONE);
        }
        typedArray.recycle();
        setOnLongClickListener();
    }
    public void setOnLongClickListener() {
        mLinearLayout.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mImageButton_shanchu.setVisibility(VISIBLE);
                mImageButton_shanchu.setOnClickListener(Custom_WDTZ_GRZX.this);
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.custom_item_wdtz_shanchu:
                AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                        .setCancelable(false).setMessage(R.string.shanchu_tiezi)
                        .setPositiveButton(R.string.queding, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mImageButton_shanchu.setVisibility(GONE);
                                Toast.makeText(getContext(),"你删除了帖子",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton(R.string.quxiao, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mImageButton_shanchu.setVisibility(GONE);
                                Toast.makeText(getContext(),"你取消了帖子的删除",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();
                alertDialog.show();
                break;
            case R.id.user_imageview:
                break;
        }
    }
}
