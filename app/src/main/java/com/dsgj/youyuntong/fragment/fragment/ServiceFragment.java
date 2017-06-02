package com.dsgj.youyuntong.fragment.fragment;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.SPUtils;
import com.dsgj.youyuntong.Utils.system.callPhoneUtils;
import com.dsgj.youyuntong.activity.Message.MessageActivity;
import com.dsgj.youyuntong.adapter.MainViewPagerAdapter;
import com.dsgj.youyuntong.base.BaseFragment;
import com.jauker.widget.BadgeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyunhao on 2017/4/19.
 */

public class ServiceFragment extends BaseFragment {

    private ImageView mPhone;
    private ImageView mMessage;
    private RadioButton mLeftButton;
    private RadioButton mRightButton;
    private ViewPager mViewPager;
    private RadioGroup mRadioGroup;
    private BadgeView mMBadgeView;
    private String mMessage1;
    private boolean mMIsLogon;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_service;
    }

    @Override
    protected void initView(View view) {
        mMBadgeView = new BadgeView(getActivity());
        mPhone = (ImageView) view.findViewById(R.id.iv_service_phone);
        mMessage = (ImageView) view.findViewById(R.id.iv_service_message);
        mRadioGroup = (RadioGroup) view.findViewById(R.id.title_radioGroup);
        mLeftButton = (RadioButton) view.findViewById(R.id.rb_title_left);
        mRightButton = (RadioButton) view.findViewById(R.id.rb_title_right);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_service);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initData() {
        mMBadgeView.setTargetView(mMessage);
        mMessage1= SPUtils.with(getActivity()).get("message_unread", "0");
        if (mMessage1.equals("1")) {
            mMBadgeView.setBadgeCount(1);
        } else {
            mMBadgeView.setBadgeCount(0);
        }
        mLeftButton.setChecked(true);
        mViewPager.setCurrentItem(0);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ServiceProductConsultFragment());
        fragments.add(new ServiceOrderConsultFragment());
        mViewPager.setAdapter(new MainViewPagerAdapter(getChildFragmentManager(), fragments));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()

        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mRadioGroup.check(R.id.rb_title_left);
                        break;
                    case 1:
                        mRadioGroup.check(R.id.rb_title_right);
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
        mPhone.setOnClickListener(this);
        mMessage.setOnClickListener(this);
        mLeftButton.setOnClickListener(this);
        mRightButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_service_phone:
                callPhoneUtils.MakePhone(getActivity(), "13623717683");
                break;
            case R.id.iv_service_message:
                SPUtils.with(getActivity()).save("message_unread", "0");
                jumpToActivity(MessageActivity.class);
                break;
            case R.id.rb_title_left:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.rb_title_right:
                mViewPager.setCurrentItem(1);
                break;
        }

    }
}
