package com.example.administrator.yzzf.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.yzzf.Bean.WdddItemBean;

import java.util.List;

import static com.sina.weibo.sdk.openapi.legacy.CommonAPI.CAPITAL.t;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class WDDDAdapter extends FragmentPagerAdapter {
    private ChangeView mListener;
    private List<WdddItemBean> mDatas;
    private List<String> titles;
    private List<Fragment> mFragments;

    public WDDDAdapter(FragmentManager fm, List<String> titles, List<Fragment> fragments) {
        super(fm);
        this.titles = titles;
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position % titles.size());
    }

    public interface ChangeView {
        void onChange();
    }

}
