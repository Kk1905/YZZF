package com.example.administrator.yzzf.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.yzzf.R;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by Administrator on 2017/2/24 0024.
 */

public class Custom_YSSZ_GRZX extends LinearLayout {
    private ImageView mImageView;
    private TextView mTextView;
    private CheckBox mCheckBox;

    public ImageView getImageView() {
        return mImageView;
    }

    public TextView getTextView() {
        return mTextView;
    }

    public CheckBox getCheckBox() {
        return mCheckBox;
    }

    public Custom_YSSZ_GRZX(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_item_yssz, this, true);
        mImageView = (ImageView) findViewById(R.id.custom_item_yssz_userimage);
        mTextView = (TextView) findViewById(R.id.custom_item_yssz_phone);
        mCheckBox = (CheckBox) findViewById(R.id.custom_item_yssz_checkbox);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Custom_YSSZ_GRZX);
        int imageId = typedArray.getResourceId(R.styleable.Custom_YSSZ_GRZX_yssz_image_src, R.drawable.grzx_user);
        mImageView.setImageResource(imageId);
        String phone = typedArray.getString(R.styleable.Custom_YSSZ_GRZX_yssz_phone);
        mTextView.setText(phone);
        boolean ischecked = typedArray.getBoolean(R.styleable.Custom_YSSZ_GRZX_yssz_ischecked, false);
        if (ischecked) {
            mCheckBox.setChecked(true);

        } else {
            mCheckBox.setChecked(false);

        }
        typedArray.recycle();
    }
}
