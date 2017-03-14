package com.example.administrator.yzzf.Util;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.example.administrator.yzzf.Fragment.BanKuaiFragment;
import com.example.administrator.yzzf.Fragment.GRZXFragment;
import com.example.administrator.yzzf.Fragment.GXQMFragment;
import com.example.administrator.yzzf.Fragment.ZhuyeFragment;
import com.example.administrator.yzzf.R;

import static android.R.attr.fragment;


/**
 * Created by Administrator on 2017/3/11 0011.
 */

public class FragmentFactory {
    public static void replaceFragment(AppCompatActivity activity, Fragment fragment, int container) {
        activity.getSupportFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(container, fragment)
                .commit();
    }
}
