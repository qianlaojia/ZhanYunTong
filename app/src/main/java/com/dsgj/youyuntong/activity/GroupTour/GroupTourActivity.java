package com.dsgj.youyuntong.activity.GroupTour;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dsgj.youyuntong.JavaBean.GroupTour.GroupTourBean;
import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.Http.RequestCallBack;
import com.dsgj.youyuntong.Utils.Http.HttpUtils;
import com.dsgj.youyuntong.Utils.SPUtils;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.Utils.view.NoSlidingViewPagerFit;
import com.dsgj.youyuntong.Utils.view.XBannerUtils;
import com.dsgj.youyuntong.activity.Message.MessageActivity;
import com.dsgj.youyuntong.activity.ProductDetailActivity;
import com.dsgj.youyuntong.activity.Search.SearchActivity;
import com.dsgj.youyuntong.adapter.GroupTrip.GroupTripRecycleViewAdapter;
import com.dsgj.youyuntong.adapter.GroupTrip.LineGridViewAdapter;
import com.dsgj.youyuntong.adapter.MainViewPagerAdapter;
import com.dsgj.youyuntong.base.BaseActivity;
import com.dsgj.youyuntong.fragment.fragment.TravelAbordFragment;
import com.dsgj.youyuntong.fragment.fragment.TravelAroundFragment;
import com.dsgj.youyuntong.fragment.fragment.TravelInCountryFragment;
import com.google.gson.Gson;
import com.jauker.widget.BadgeView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GroupTourActivity extends BaseActivity {

    private XBanner mXBanner;
    private GridView mLineGridView;

    private RadioButton mTravel_around;
    private RadioButton mInbound_tourism;
    private RadioButton mTravel_abroad;
    private RadioGroup mRadioGroup;
    private List<Fragment> mFragments;
    private NoSlidingViewPagerFit mViewPager;
    private ImageView mBack;
    private TextView mSearchTips;
    private ImageView mMessage;
    private BadgeView mMBadgeView;
    private RecyclerView mRecyclerView;

    private GroupTripRecycleViewAdapter mMAdapter;
    private TextView mSearchTitle;
    private List<String> mSlideListUrl = new ArrayList<>();
    private List<GroupTourBean.ResultBean.ScenicHotBean> mScenicHotBeen;
    private List<String> mMHotName;

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
        mLineGridView = (GridView) findViewById(R.id.lgv_group_trip_hot_city);
        mTravel_around = (RadioButton) findViewById(R.id.rb_travel_around);
        mInbound_tourism = (RadioButton) findViewById(R.id.rb_inbound_tourism);
        mTravel_abroad = (RadioButton) findViewById(R.id.rb_travel_abroad);
        mRadioGroup = (RadioGroup) findViewById(R.id.rg_Group_tour);
        mViewPager = (NoSlidingViewPagerFit) findViewById(R.id.vp_group_trip);
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
        mMBadgeView.setTargetView(mMessage);
        String MMessage = SPUtils.with(this).get("message_unread", "0");
        if (MMessage.equals("1")) {
            mMBadgeView.setBadgeCount(1);
        } else {
            mMBadgeView.setBadgeCount(0);
        }
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

    }

    private void GetServerData() {
        final Map<String, String> map = new HashMap<>();
        map.put("page", "");
        map.put("city", "郑州");
        map.put("page_size", "15");
        HttpUtils.post(GroupTourActivity.this, new GroupTourBean(), HttpUtils.URL_BASE_TOURISM + "home", map, new RequestCallBack() {
            @Override
            public void onOutNet() {
                ToastUtils.show(GroupTourActivity.this, "数据获取失败！");
            }

            @Override
            public void onSuccess(String data) {
                Gson gson = new Gson();
                GroupTourBean.ResultBean resultBean = gson.fromJson(data, GroupTourBean.ResultBean.class);
                List<GroupTourBean.ResultBean.SlideBean> slideListBeen = resultBean.getSlide();
                for (int i = 0; i < slideListBeen.size(); i++) {
                    mSlideListUrl.add("http://59.110.106.1" + slideListBeen.get(i).getSlide_pic());
                }
                mScenicHotBeen = resultBean.getScenic_hot();
                List<GroupTourBean.ResultBean.DestinationHotBean> destinationHotBeen = resultBean.getDestination_hot();
                mMHotName = new ArrayList<>();
                for (int i = 0; i < destinationHotBeen.size(); i++) {
                    mMHotName.add(destinationHotBeen.get(i).getName());
                }
                XBannerUtils.setBannerHolder(GroupTourActivity.this, mXBanner, mSlideListUrl);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(GroupTourActivity.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                mRecyclerView.setLayoutManager(linearLayoutManager);
                //设置适配器
                mMAdapter = new GroupTripRecycleViewAdapter(GroupTourActivity.this, mScenicHotBeen);
                mMAdapter.setOnItemClickListener(new GroupTripRecycleViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        String productId = mScenicHotBeen.get(position).getProduct_id();
                        String productCode = mScenicHotBeen.get(position).getProduct_code();
                        Intent intent = new Intent(GroupTourActivity.this, ProductDetailActivity.class);
                        intent.putExtra("product_id", productId);
                        intent.putExtra("product_code", productCode);
                        startActivity(intent);
                    }
                });
                mRecyclerView.setAdapter(mMAdapter);
                mRecyclerView.setNestedScrollingEnabled(false);


                //将相关的数据使用构造方法传递出去；
                LineGridViewAdapter lineGridViewAdapter = new LineGridViewAdapter(GroupTourActivity.this, mMHotName);
                mLineGridView.setAdapter(lineGridViewAdapter);
                mLineGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ToastUtils.show(GroupTourActivity.this, "第" + position + "被点击！！");
                    }
                });
                ToastUtils.show(GroupTourActivity.this, "成功的获取数据！");
            }

            @Override
            public void onFailure(int code) {
                ToastUtils.show(GroupTourActivity.this, "数据获取失败！");
            }

            @Override
            public void onError(Exception e) {
                ToastUtils.show(GroupTourActivity.this, "数据获取失败！");
            }
        });
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
