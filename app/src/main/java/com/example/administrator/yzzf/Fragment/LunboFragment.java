package com.example.administrator.yzzf.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.yzzf.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Created by Administrator on 2017/3/22 0022.
 */

public class LunboFragment extends BaseFragment implements View.OnClickListener {
    private ImageView mImageView;
    private String url;
    private ImageLoader mImageLoader;
    private DisplayImageOptions mOptions;

    public static LunboFragment newInstance(String url, String pictureUrl) {
        LunboFragment fragment = new LunboFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        bundle.putString("pictureUrl", pictureUrl);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageLoader = ImageLoader.getInstance();

        mOptions = new DisplayImageOptions.Builder().showStubImage(R.drawable.zhuye_kongbai)
                .showImageForEmptyUri(R.drawable.zhuye_kongbai).showImageOnFail(R.drawable.zhuye_kongbai).cacheInMemory()
                .cacheOnDisc().displayer(new RoundedBitmapDisplayer(20)).displayer(new FadeInBitmapDisplayer(300))
                .build();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(mAppCompatActivity).inflate(R.layout.fragment_lunbo, container, false);
        mImageLoader.init(ImageLoaderConfiguration.createDefault(mAppCompatActivity));
        mImageView = (ImageView) view.findViewById(R.id.fragment_lunbo_image);
        url = getArguments().getString("url");
        mImageLoader.displayImage(getArguments().getString("pictureUrl"), mImageView, mOptions);
        mImageView.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_lunbo_image:
                Intent intent = new Intent();
                intent.putExtra("url", url);
                mAppCompatActivity.startActivity(intent);
                break;
        }
    }
}
