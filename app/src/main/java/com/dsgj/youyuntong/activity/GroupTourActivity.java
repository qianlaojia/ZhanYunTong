package com.dsgj.youyuntong.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dsgj.youyuntong.JavaBean.GroupTourBean;
import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.Http.RequestCallBack;
import com.dsgj.youyuntong.Utils.Http.HttpUtils;
import com.dsgj.youyuntong.Utils.SPUtils;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.Utils.log.LogUtils;
import com.dsgj.youyuntong.Utils.view.XBannerUtils;
import com.dsgj.youyuntong.Utils.view.GridViewTableLine;
import com.dsgj.youyuntong.activity.Message.MessageActivity;
import com.dsgj.youyuntong.activity.Search.SearchActivity;
import com.dsgj.youyuntong.adapter.GroupTrip.GroupTripRecycleViewAdapter;
import com.dsgj.youyuntong.adapter.GroupTrip.LineGridViewAdapter;
import com.dsgj.youyuntong.adapter.MainViewPagerAdapter;
import com.dsgj.youyuntong.base.BaseActivity;
import com.dsgj.youyuntong.fragment.fragment.TravelAbordFragment;
import com.dsgj.youyuntong.fragment.fragment.TravelAroundFragment;
import com.dsgj.youyuntong.fragment.fragment.TravelInCountryFragment;
import com.jauker.widget.BadgeView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupTourActivity extends BaseActivity {

    private XBanner mXBanner;
    private GridViewTableLine mLineGridView;
    private String[] mStrings = {"张三", "张三", "李四", "王五", "赵六", "李四", "李四", "王五", "赵六", "李四", "王五", "赵六", "李四", "王五", "赵六", "更多"};
    private RadioButton mTravel_around;
    private RadioButton mInbound_tourism;
    private RadioButton mTravel_abroad;
    private RadioGroup mRadioGroup;
    private List<Fragment> mFragments;
    private ViewPager mViewPager;
    private ImageView mBack;
    private TextView mSearchTips;
    private ImageView mMessage;
    private BadgeView mMBadgeView;
    private RecyclerView mRecyclerView;
    private List<Integer> mImage;
    private GroupTripRecycleViewAdapter mMAdapter;
    private TextView mSearchTitle;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_group_tour;
    }

    @Override
    protected void initView() {
        mMBadgeView = new BadgeView(this);
        mBack = (ImageView) findViewById(R.id.iv_return);
        mSearchTips = (TextView) findViewById(R.id.tv_search_tips);
        mMessage = (ImageView) findViewById(R.id.iv_news_Image);
        mXBanner = (XBanner) findViewById(R.id.xb_group_trip_XBanner);
        mLineGridView = (GridViewTableLine) findViewById(R.id.lgv_group_trip_hot_city);
        mTravel_around = (RadioButton) findViewById(R.id.rb_travel_around);
        mInbound_tourism = (RadioButton) findViewById(R.id.rb_inbound_tourism);
        mTravel_abroad = (RadioButton) findViewById(R.id.rb_travel_abroad);
        mRadioGroup = (RadioGroup) findViewById(R.id.rg_Group_tour);
        mViewPager = (ViewPager) findViewById(R.id.vp_group_trip);
        mSearchTitle = (TextView) findViewById(R.id.tv_search_tips);
        mFragments = new ArrayList<>();
        mFragments.add(new TravelAroundFragment());
        mFragments.add(new TravelInCountryFragment());
        mFragments.add(new TravelAbordFragment());
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_Group_trip_hot_pots);

    }

    @Override
    protected void initData() {
        GetServerData();
        ImageAndData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器
        mMAdapter = new GroupTripRecycleViewAdapter(this, mImage);
        mMAdapter.setOnItemClickListener(new GroupTripRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtils.show(GroupTourActivity.this, "第" + position + "个被点击！");
            }
        });
        mRecyclerView.setAdapter(mMAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
        mMBadgeView.setTargetView(mMessage);
        String MMessage = SPUtils.with(this).get("message_unread", "0");
        if (MMessage.equals("1")) {
            mMBadgeView.setBadgeCount(1);
        } else {
            mMBadgeView.setBadgeCount(0);
        }
        XBannerUtils.setBannerHolder(this, mXBanner);
        //将相关的数据使用构造方法传递出去；
        LineGridViewAdapter lineGridViewAdapter = new LineGridViewAdapter(this, mStrings);
        mLineGridView.setAdapter(lineGridViewAdapter);
        mLineGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.show(GroupTourActivity.this, "第" + position + "被点击！！");
            }
        });

        //默认周边游被点击
        mRadioGroup.check(R.id.rb_travel_around);
        mViewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), mFragments));
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_travel_around:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.rb_inbound_tourism:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.rb_travel_abroad:
                        mViewPager.setCurrentItem(2);
                        break;
                }
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mRadioGroup.check(R.id.rb_travel_around);
                        break;
                    case 1:
                        mRadioGroup.check(R.id.rb_inbound_tourism);
                        break;
                    case 2:
                        mRadioGroup.check(R.id.rb_travel_abroad);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void ImageAndData() {
        mImage = new ArrayList<>(Arrays.asList(R.mipmap.shilitu, R.mipmap.shilitu
                , R.mipmap.shilitu, R.mipmap.shilitu, R.mipmap.shilitu
                , R.mipmap.shilitu, R.mipmap.shilitu, R.mipmap.shilitu
                , R.mipmap.shilitu, R.mipmap.shilitu, R.mipmap.shilitu));

    }

    private void GetServerData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                Map<String, String> map = new HashMap<>();
                map.put("page", "");
                map.put("city", "郑州市");
                map.put("page_size", "6");
                HttpUtils.post(GroupTourActivity.this, new GroupTourBean(), HttpUtils.URL_BASE_TOURISM + "home", map, new RequestCallBack() {


                    @Override
                    public void onOutNet() {
                        LogUtils.e("网络出现错误");
                        ToastUtils.show(GroupTourActivity.this, "网络已断开!");
                    }

                    @Override
                    public void onSuccess(String data) {
                        LogUtils.e("访问成功======================================================");


                    }

                    @Override
                    public void onFailure(int code) {
                        LogUtils.e("首页访问失败！");
                    }

                    @Override
                    public void onError(Exception e) {
                        LogUtils.e("网络连接出现错误！");
                    }
                });
            }
        }.start();

    }


    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);
        mMessage.setOnClickListener(this);
        mSearchTitle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_return:
                this.finish();
                break;
            case R.id.iv_news_Image:
                SPUtils.with(this).save("message_unread", "0");
                jumpToActivity(MessageActivity.class);
                break;
            case R.id.tv_search_tips:
                jumpToActivity(SearchActivity.class);
                break;
        }

    }
}
