package com.dsgj.youyuntong.activity.Search;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import android.support.design.widget.TabLayout;
import android.widget.TextView;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.adapter.TabLayoutViewPagerAdapter;
import com.dsgj.youyuntong.base.BaseActivity;
import com.dsgj.youyuntong.fragment.fragment.SearchResultFragments.SearchResultGroupFragment;
import com.dsgj.youyuntong.fragment.fragment.SearchResultFragments.SearchResultTicketFragment;
import com.dsgj.youyuntong.fragment.fragment.SearchResultFragments.SearchResultTrainFragment;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends BaseActivity {


    private ImageView mBack;
    private TextView mInputKey;
    private String mSearchKey;
    private TabLayout mTableLayout;
    private ViewPager mViewPager;
    private TabLayoutViewPagerAdapter mAdapter;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_search_result;
    }

    @Override
    protected void initView() {
        mBack = (ImageView) findViewById(R.id.iv_search_result_back);
        mInputKey = (TextView) findViewById(R.id.tv_all_search_result);
        mTableLayout = (TabLayout) findViewById(R.id.tl_search_result_table);
        mViewPager = (ViewPager) findViewById(R.id.vp_search_result);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mSearchKey = intent.getStringExtra("searchKey");//搜索的关键字
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new SearchResultGroupFragment());
        fragmentList.add(new SearchResultTrainFragment());
        fragmentList.add(new SearchResultTicketFragment());
        String[] strings = {"跟团游", "直通车", "门票"};
        mAdapter = new TabLayoutViewPagerAdapter(getSupportFragmentManager()
                , fragmentList,strings);
        mViewPager.setAdapter(mAdapter);
        mTableLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);
        mInputKey.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search_result_back:
                this.finish();
                break;
            case R.id.tv_all_search_result:
                jumpToActivity(SearchActivity.class);
                break;
        }

    }
}
