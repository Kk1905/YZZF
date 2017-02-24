package com.example.administrator.yzzf.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.yzzf.R;




/**
 * Created by Administrator on 2017/2/23 0023.
 */

public class Custom_WDZL_GRZX extends LinearLayout {
    private TextView mTextView01;
    private TextView mTextView02;
    private TextView mTextView03;

    public TextView getTextView02() {
        return mTextView02;
    }

    public TextView getTextView01() {
        return mTextView01;
    }

    public Custom_WDZL_GRZX(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_item_wdzl, this, true);
        mTextView01 = (TextView) findViewById(R.id.custom_wdzl_textview01);
        mTextView02 = (TextView) findViewById(R.id.custom_wdzl_textview02);
        mTextView03 = (TextView) findViewById(R.id.custom_wdzl_textview03);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Custom_WDZL_GRZX);
        String text01 = typedArray.getString(R.styleable.Custom_WDZL_GRZX_text01);
        mTextView01.setText(text01);
        String text02 = typedArray.getString(R.styleable.Custom_WDZL_GRZX_text02);
        mTextView02.setText(text02);
        boolean isvisible = typedArray.getBoolean(R.styleable.Custom_WDZL_GRZX_isvisible, true);
        if (isvisible) {
            mTextView03.setVisibility(VISIBLE);
        } else {
            mTextView03.setVisibility(INVISIBLE);
        }
        typedArray.recycle();
    }
}
