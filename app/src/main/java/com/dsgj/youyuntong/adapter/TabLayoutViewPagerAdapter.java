package com.dsgj.youyuntong.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * TabLayout+ViewPager+Fragment相结合布局。
 * Created by 张云浩  on 2017/6/7.
 * 邮箱：943332771@qq.com
 */

public class TabLayoutViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private String[] mStrings;

    /**
     * 通过构造函数将参数传递
     *
     * @param fm        Fragment管理器
     * @param fragments Fragment的集合
     * @param strings   String 数组
     */
    public TabLayoutViewPagerAdapter(FragmentManager fm
            , List<Fragment> fragments
            , String[] strings) {
        super(fm);
        this.mFragments = fragments;
        this.mStrings = strings;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
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
