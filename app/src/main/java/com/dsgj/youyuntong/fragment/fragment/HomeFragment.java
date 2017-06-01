package com.dsgj.youyuntong.fragment.fragment;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.dsgj.youyuntong.JavaBean.HomePageBean;
import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.Http.RequestCallBack;
import com.dsgj.youyuntong.Utils.Http.HttpUtils;
import com.dsgj.youyuntong.Utils.SPUtils;
import com.dsgj.youyuntong.Utils.log.LogUtils;
import com.dsgj.youyuntong.Utils.recyclerview.LinearLayoutUtils;
import com.dsgj.youyuntong.Utils.recyclerview.XBannerUtils;
import com.dsgj.youyuntong.Utils.system.callPhoneUtils;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.activity.MessageActivity;
import com.dsgj.youyuntong.adapter.HomePageRecyclerViewAdapter;
import com.dsgj.youyuntong.base.BaseFragment;
import com.google.gson.Gson;
import com.jauker.widget.BadgeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 主页
 * Created by 张云浩  on 2017/4/19.
 * 邮箱：943332771@qq.com
 */

public class HomeFragment extends BaseFragment {

    public String s;
    public TextView mLocation;
    private TextView mSearch;
    private ImageView mPhone;
    private ImageView mMessages;
    private RecyclerView mRecyclerView;
    private List<String> mImageList;
    private List<Map<String, Object>> mGridViewList;
    private List<String> normalList;
    private int height = 458;// 滑动开始变色的高,真实项目中此高度是由广告轮播或其他首页view高度决定
    private int overallXScroll = 0;
    private List<String> mImageUrl;
    //百度地图定位：
    public LocationClient mLocationClient;
    public BDLocationListener mBDLocationListener = new MyLocationListener();
    private String Location = " ";
    private BadgeView mMBadgeView;
    private LinearLayout mLlTitle;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        mMBadgeView = new BadgeView(getActivity());
        mLocation = (TextView) view.findViewById(R.id.tv_Location);
        mSearch = (TextView) view.findViewById(R.id.et_search);
        mPhone = (ImageView) view.findViewById(R.id.iv_phone);
        mMessages = (ImageView) view.findViewById(R.id.iv_message);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_recyclerView);
        mLlTitle = (LinearLayout) view.findViewById(R.id.home_fragment_title);

    }

    @Override
    protected void initData() {
        GetInternetData();
        mImageList = new ArrayList<>();
        mGridViewList = new ArrayList<>();
        normalList = new ArrayList<>();
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //初始化定位服务：
        mLocationClient = new LocationClient(getActivity());
        mLocationClient.registerLocationListener(mBDLocationListener);
        mLocationClient.start();
        initLocation();
        mMBadgeView.setTargetView(mMessages);
        String MMessage = SPUtils.with(getActivity()).get("message_unread", "0");
        if (MMessage.equals("1")) {
            mMBadgeView.setBadgeCount(1);
        } else {
            mMBadgeView.setBadgeCount(0);
        }

        mLocation.setText("郑州市");
        mImageList = XBannerUtils.getImageUrl();
        //获得门票等四个布局
        mGridViewList = LinearLayoutUtils.getStringAndLogo();
        //获得普通布局
        for (int i = 0; i < 20; i++) {
            normalList.add("正常布局" + i);
        }
        HomePageRecyclerViewAdapter adapter = new HomePageRecyclerViewAdapter(getActivity()
                , mImageList
                , mGridViewList
                , normalList);

        mRecyclerView.setAdapter(adapter);
        //标题栏变色
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                overallXScroll = overallXScroll + dy;// 累加y值 解决滑动一半y值为0
                if (overallXScroll <= 0) {   //设置标题的背景颜色
                    mLlTitle.setBackgroundColor(Color.argb(0, 41, 193, 246));
                } else if (overallXScroll > 0 && overallXScroll <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
                    float scale = (float) overallXScroll / height;
                    float alpha = (255 * scale);
                    mLlTitle.setBackgroundColor(Color.argb((int) alpha, 0, 145, 242));
                } else {
                    mLlTitle.setBackgroundColor(Color.argb(255, 0, 145, 242));
                }
            }

        });
    }

    private void GetInternetData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                Map<String, String> map = new HashMap<>();
                map.put("page", "");
                map.put("city", "郑州市");
                map.put("page_size", "6");
                HttpUtils.post(getActivity(), new HomePageBean(), HttpUtils.URL_BASE + "home", map, new RequestCallBack() {


                    @Override
                    public void onOutNet() {
                        LogUtils.e("网络出现错误");
                        ToastUtils.show(getActivity(), "网络已断开!");
                    }

                    @Override
                    public void onSuccess(String data) {
                        LogUtils.e(data);
                        Gson gson = new Gson();
                        HomePageBean.ResultBean homepageBean = gson.fromJson(data, HomePageBean.ResultBean.class);
                        List<HomePageBean.ResultBean.SlideListBean> slideListBean = homepageBean.getSlide_list();
                        mImageUrl = new ArrayList<>();
                        for (int i = 0; i < slideListBean.size(); i++) {
                            mImageUrl.add("http://59.110.106.1" + slideListBean.get(i).getSlide_pic());
                            LogUtils.e("这个是homepage的轮播图的地址：\n" + "http://59.110.106.1" + slideListBean.get(i).getSlide_pic() + "\n000000000000000000000000000000000000");
                        }


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


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initListener() {
        mLocation.setOnClickListener(this);
        mMessages.setOnClickListener(this);
        mPhone.setOnClickListener(this);
        mSearch.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_Location:

                ToastUtils.show(getActivity(), "点击了位置的控件");
                break;
            case R.id.iv_phone:
                String phoneNumber = "13623717683";
                callPhoneUtils.MakePhone(getActivity(), phoneNumber);
                break;
            case R.id.iv_message://跳转到消息页面（完成）
                SPUtils.with(getActivity()).save("message_unread", "0");
                jumpToActivity(MessageActivity.class);
                break;
        }
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps
        option.setLocationNotify(false);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        mLocationClient.setLocOption(option);

    }

    private class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (bdLocation.getLocType() == BDLocation.TypeGpsLocation) {
                Location = bdLocation.getCity();
            } else if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
                Location = bdLocation.getCity();
            } else if (bdLocation.getLocType() == BDLocation.TypeOffLineLocation) {
                Location = bdLocation.getCity();
            } else if (bdLocation.getLocType() == BDLocation.TypeServerError) {
                Location = bdLocation.getCity();
                ToastUtils.show(getActivity(), "服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (bdLocation.getLocType() == BDLocation.TypeNetWorkException) {
                ToastUtils.show(getActivity(), "网络不同导致定位失败，请检查网络是否通畅");
            } else if (bdLocation.getLocType() == BDLocation.TypeCriteriaException) {
                ToastUtils.show(getActivity(), "无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            if (s == null) {
                mLocation.setText(Location);
            }

            //  LogInBean.i(TAG, "onReceiveLocation: `````````````````" + locationText);
            mLocationClient.stop();
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {
        }
    }


}