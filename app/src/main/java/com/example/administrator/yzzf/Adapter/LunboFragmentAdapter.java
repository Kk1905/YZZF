package com.example.administrator.yzzf.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.yzzf.Fragment.LunboFragment;

import java.util.List;

/**
 * Created by Administrator on 2017/3/22 0022.
 */

public class LunboFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mLunboFragments;

    public LunboFragmentAdapter(FragmentManager fm, List<Fragment> lunboFragments) {
        super(fm);
        mLunboFragments = lunboFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mLunboFragments.get(position % mLunboFragments.size());
    }

    @Override
    public int getCount() {
        return mLunboFragments.size();
    }
}
