package com.example.administrator.yzzf.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.yzzf.Fragment.BKSY_Quanbu_Fragment;
import com.example.administrator.yzzf.Fragment.BKSY_Remen_Fragment;
import com.example.administrator.yzzf.Fragment.BKSY_Zuixin_Fragment;

import java.util.List;

/**
 * Created by Administrator on 2017/2/28 0028.
 */

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mList_fragment;
    private List<String> mList_title;

    public MainFragmentPagerAdapter(FragmentManager fm, List<Fragment> list_fragment, List<String> list_string) {
        super(fm);
        mList_fragment = list_fragment;
        mList_title = list_string;
    }

    @Override
    public Fragment getItem(int position) {
        return mList_fragment.get(position);
    }

    @Override
    public int getCount() {
        return mList_title.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    //用来显示tab标签上的名字

    @Override
    public CharSequence getPageTitle(int position) {
        return mList_title.get(position % mList_title.size());
    }
}
