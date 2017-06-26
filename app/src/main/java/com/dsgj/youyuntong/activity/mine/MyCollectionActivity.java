package com.dsgj.youyuntong.activity.mine;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsgj.youyuntong.JavaBean.CollectionBean;
import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.Http.HttpUtils;
import com.dsgj.youyuntong.Utils.Http.RequestCallBack;
import com.dsgj.youyuntong.Utils.SPUtils;
import com.dsgj.youyuntong.Utils.log.LogUtils;
import com.dsgj.youyuntong.base.BaseActivity;
import com.dsgj.youyuntong.adapter.CollectViewPageAdapter;
import com.dsgj.youyuntong.base.BaseJavaBean;
import com.dsgj.youyuntong.fragment.fragment.CollectGroupTourFragment;
import com.dsgj.youyuntong.fragment.fragment.CollectThroughTripFragment;
import com.dsgj.youyuntong.fragment.fragment.CollectTicketFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyCollectionActivity extends BaseActivity {


    private ImageView mBack;
    private TextView mMiddleText;
    private TabLayout mTableLayout;
    private ViewPager mViewPager;
    private String[] mTabtitle;
    private List<Fragment> mFragments;
    private List<CollectionBean.ResultBean.ThroughBean> mThroughBeanList;
    private List<CollectionBean.ResultBean.TicketBean> mTicketBeanList;
    private List<CollectionBean.ResultBean.TourismBean> mTourismBeanList;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_my_collection;
    }

    @Override
    protected void initView() {
        mBack = (ImageView) findViewById(R.id.iv_back);
        mMiddleText = (TextView) findViewById(R.id.tv_middle_text);
        mTableLayout = (TabLayout) findViewById(R.id.tl_collect);
        mViewPager = (ViewPager) findViewById(R.id.vp_collect);
    }

    @Override
    protected void initData() {
        mMiddleText.setText("我的收藏");
        String userName = SPUtils.with(this).get("userName", "");
        String token = SPUtils.with(this).get("token", "");
        Map<String, String> map = new HashMap<>();
        map.put("type", "phone");
        map.put("access_token", "");
        map.put("userName", userName);
        map.put("token", token);
        mTabtitle = new String[]{"跟团游", "直通车", "门票"};
        mFragments = new ArrayList<>();
        mFragments.add(new CollectGroupTourFragment(map));
        mFragments.add(new CollectThroughTripFragment(map));
        mFragments.add(new CollectTicketFragment(map));
        mViewPager.setAdapter(new CollectViewPageAdapter(getSupportFragmentManager(), mTabtitle, mFragments));
        mTableLayout.setupWithViewPager(mViewPager);

    }

    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                this.finish();
                break;
        }

    }
}
