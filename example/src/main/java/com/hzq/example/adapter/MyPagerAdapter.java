package com.hzq.example.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @author 小强
 * @time 2018/10/29  22:43
 * @desc ViewPager适配器
 */
public class MyPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragments;
    private String[] mTitles ;

    public MyPagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] titles) {
        super(fm);
        mFragments = fragments;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size() > 0 ? mFragments.size() : 0;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }




}
