package com.dsgj.youyuntong.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 *
 * Created by 张云浩  on 2017/6/7.
 * 邮箱：943332771@qq.com
 */

public class SearchResultViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private String[] mStrings;

    public SearchResultViewPagerAdapter(FragmentManager fm
            , List<Fragment> fragments
            , String[] strings) {
        super(fm);
        this.mFragments = fragments;
        this.mStrings = strings;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return mFragments.get(0);
        } else if (position == 1) {
            return mFragments.get(1);
        } else if (position == 2) {
            return mFragments.get(2);
        } else if (position == 3) {
            return mFragments.get(3);
        } else
            return mFragments.get(0);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mStrings[position];
    }
}
