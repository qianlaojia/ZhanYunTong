package com.dsgj.youyuntong.activity;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioGroup;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.Utils.view.NoSlidingViewPager;
import com.dsgj.youyuntong.adapter.MainViewPagerAdapter;
import com.dsgj.youyuntong.base.BaseActivity;
import com.dsgj.youyuntong.fragment.fragment.HomeFragment;
import com.dsgj.youyuntong.fragment.fragment.MineFragment;
import com.dsgj.youyuntong.fragment.fragment.ServiceFragment;
import com.dsgj.youyuntong.fragment.fragment.OrderFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private NoSlidingViewPager mMainViewPager;
    private RadioGroup mRadioGroup;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        mMainViewPager = (NoSlidingViewPager) findViewById(R.id.main_viewPager);
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);

    }

    @Override
    protected void initData() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        mMainViewPager.setCurrentItem(0, false);
                        break;
                    case R.id.rb_travel:
                        mMainViewPager.setCurrentItem(1, false);
                        break;
                    case R.id.rb_service:
                        mMainViewPager.setCurrentItem(2, false);
                        break;
                    case R.id.rb_mine:
                        mMainViewPager.setCurrentItem(3, false);
                        break;
                }
            }
        });

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new OrderFragment());
        fragments.add(new ServiceFragment());
        fragments.add(new MineFragment());
        mMainViewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager()
                , fragments));
        mMainViewPager.setCurrentItem(0);
        mMainViewPager.setOffscreenPageLimit(3);
        mMainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position
                    , float positionOffset
                    , int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mRadioGroup.check(R.id.rb_home);
                        break;
                    case 1:
                        mRadioGroup.check(R.id.rb_travel);
                        break;
                    case 2:
                        mRadioGroup.check(R.id.rb_service);
                        break;
                    case 3:
                        mRadioGroup.check(R.id.rb_mine);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    protected void initListener() {

    }


    @Override
    public void onClick(View v) {

    }

    private long first = 0;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long second = System.currentTimeMillis();
                if (second - first > 2000) {
                    ToastUtils.show(MainActivity.this, "再点一次返回键退出应用！");
                    first = second;
                    return true;
                } else {
                    this.finish();
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }




}
