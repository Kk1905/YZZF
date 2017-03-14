package com.example.administrator.yzzf.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.yzzf.Fragment.BaseFragment;

import java.util.List;

/**
 * Created by Administrator on 2017/3/11 0011.
 */

public class MainAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> mFragments;

    public MainAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
