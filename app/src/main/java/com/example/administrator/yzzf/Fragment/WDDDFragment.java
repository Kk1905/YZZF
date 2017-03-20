package com.example.administrator.yzzf.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.yzzf.R;

import me.maxwin.view.XListView;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class WDDDFragment extends BaseFragment {
    private XListView mXListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(mAppCompatActivity).inflate(R.layout.wddd_fragment, container, false);
        mXListView = (XListView) view.findViewById(R.id.wddd_fragment_xlistview);
        TextView text = (TextView) view.findViewById(R.id.wddd_text);
        text.setText(getArguments().getString("text"));
        return view;
    }
}
