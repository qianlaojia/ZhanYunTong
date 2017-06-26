package com.dsgj.youyuntong.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * TODO:
 * Created by 张云浩  on 2017/6/23.
 * 邮箱：943332771@qq.com
 */

public class CollectViewPageAdapter extends FragmentPagerAdapter {
    private String[] titles;
    private List<Fragment> mFragmentList;

    public CollectViewPageAdapter(FragmentManager fm, String[] titles, List<Fragment> fragmentList) {
        super(fm);
        this.titles = titles;
        this.mFragmentList = fragmentList;

    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];

    }
}
