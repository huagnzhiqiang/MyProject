package com.hzq.example.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.hzq.example.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 小强
 * @time 2018/10/26  18:11
 * @desc ViewPager适配器
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    private Context mContext;
    private FragmentManager fragmentManager;
    private final List<Fragment> mFragments = new ArrayList<>();
    private final List<String> mFragmentTitles = new ArrayList<>();


    public PagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.fragmentManager = fragmentManager;
    }

    public PagerAdapter(Context mContext, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mContext = mContext;
        this.fragmentManager = fragmentManager;
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tab_item, null);
        TextView tv = (TextView) view.findViewById(R.id.view_title);
        tv.setText(mFragmentTitles.get(position));
        return view;
    }


    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    public void addFragment(Fragment fragment, String title) {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }
}
