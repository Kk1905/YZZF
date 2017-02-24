package com.example.administrator.yzzf.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.yzzf.R;

/**
 * Created by Administrator on 2017/2/23 0023.
 */

public class CustomGRZXItem extends LinearLayout {
    private ImageView mImageView;
    private TextView mTextView;
    private LinearLayout mLinearLayout;
    private Button mButton;

    public Button getButton() {
        return mButton;
    }

    public ImageView getImageView() {
        return mImageView;
    }

    public TextView getTextView() {
        return mTextView;
    }

    public LinearLayout getLinearLayout() {
        return mLinearLayout;
    }

    public CustomGRZXItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        setClickable(true);
        setFocusable(true);
        //加载布局，并且获取里面需要操作的空间对象
        LayoutInflater.from(context).inflate(R.layout.custom_item_grzx, this, true);
        mImageView = (ImageView) findViewById(R.id.custom_grzx_imageview);
        mTextView = (TextView) findViewById(R.id.custom_grzx_textview);
        mLinearLayout = (LinearLayout) findViewById(R.id.custom_grzx_linearLayout);
        mButton = (Button) findViewById(R.id.grzx_button);
        //获取自定义的属性值，返回的是一个TypedArray集合,但要记得结束的时候手动回收它，不然内存很容易泄漏
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomGRZXItem);
        //非空判断
        if (typedArray != null) {
//            //处理整个自定义控件的背景,获取自定义属性中我们设置的资源id
//            int backGroundId = typedArray.getResourceId(R.styleable.CustomGRZXItem_CustomView_background, R.color.toolbar_background);
//                //然后相当于使用LinearLayout的方法去设置我们获取的属性
//            setBackgroundResource(backGroundId);

            //处理图片的宽
            float imageView_width = typedArray.getDimension(R.styleable.CustomGRZXItem_imageView_width, 0);
            mImageView.setMaxWidth((int) imageView_width);
            //处理图片的高
            float imageView_height = typedArray.getDimension(R.styleable.CustomGRZXItem_imageView_height, 0);
            mImageView.setMaxHeight((int) imageView_height);
            //处理图片的src
            int imageView_src = typedArray.getResourceId(R.styleable.CustomGRZXItem_imageView_src, 0);
            mImageView.setBackgroundResource(imageView_src);
            //处理文字的大小
//            float textView_textSize = typedArray.getDimension(R.styleable.CustomGRZXItem_textView_textSize, 15);
//            mTextView.setTextSize(textView_textSize);
//            mTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textView_textSize);
            //处理文字的颜色
            int textView_textColor = typedArray.getColor(R.styleable.CustomGRZXItem_textView_textColor, R.color.daohang);
            mTextView.setTextColor(textView_textColor);
            //处理文字内容
            String text = typedArray.getString(R.styleable.CustomGRZXItem_textView_text);
            mTextView.setText(text);
            //设置button是否可见
            boolean isVisible = typedArray.getBoolean(R.styleable.CustomGRZXItem_button_visible, false);
            if (isVisible) {
                //如果是true，就是button可见
                mButton.setVisibility(VISIBLE);
            } else {
                //否则，button不可见
                mButton.setVisibility(INVISIBLE);
            }

            //到此为止typedArray使用完毕，手动回收
            typedArray.recycle();

        }
    }

//    public void setOnClickListener(OnClickListener onClickListener) {
//        if (onClickListener != null) {
//            mLinearLayout.setOnClickListener(onClickListener);
//        }
//    }
}
