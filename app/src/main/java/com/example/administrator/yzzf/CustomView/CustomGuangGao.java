package com.example.administrator.yzzf.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.administrator.yzzf.R;

/**
 * Created by Administrator on 2017/3/30 0030.
 */

public class CustomGuangGao extends LinearLayout {
    private ImageView mImageView_guanggao;
    private ImageView mImageView_dianji;
    private TextView jifenzhi;
    private TextView yifengxiang_num;



    public ImageView getImageView_guanggao() {
        return mImageView_guanggao;
    }

    public ImageView getImageView_dianji() {
        return mImageView_dianji;
    }

    public TextView getJifenzhi() {
        return jifenzhi;
    }

    public TextView getYifengxiang_num() {
        return yifengxiang_num;
    }


    public void setJifenzhi(String jifen) {
        jifenzhi.setText(jifen);
    }

    public void setYifengxiang_num(String yifengxiang_num) {
        this.yifengxiang_num.setText(yifengxiang_num);
    }

    public CustomGuangGao(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_radion_button, this, true);
        mImageView_guanggao = (ImageView) findViewById(R.id.custom_radio_button_image);
        mImageView_dianji = (ImageView) findViewById(R.id.custom_radio_button_imageview_dianji);
        jifenzhi = (TextView) findViewById(R.id.jifenzhi);
        yifengxiang_num = (TextView) findViewById(R.id.yifenxiang_num);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomGuangGao);
        int src_guanggao = typedArray.getResourceId(R.styleable.CustomGuangGao_image_src_guanggao, R.drawable.activity_fenxiang02);
        mImageView_guanggao.setImageResource(src_guanggao);
        int src_dianji = typedArray.getResourceId(R.styleable.CustomGuangGao_image_src_dianji, R.drawable.dianjifenxiang01);
        mImageView_dianji.setImageResource(src_dianji);
        String jifenzhi = typedArray.getString(R.styleable.CustomGuangGao_jifenzhi);
        this.jifenzhi.setText(jifenzhi);
        String yifengxiang_num = typedArray.getString(R.styleable.CustomGuangGao_yifengxiang_num);
        this.yifengxiang_num.setText(yifengxiang_num);

        typedArray.recycle();
    }

}
