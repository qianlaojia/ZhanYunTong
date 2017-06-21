package com.dsgj.youyuntong.fragment.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bumptech.glide.Glide;
import com.dsgj.youyuntong.JavaBean.HomePage.HomePageBean;
import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.Http.RequestCallBack;
import com.dsgj.youyuntong.Utils.Http.HttpUtils;
import com.dsgj.youyuntong.Utils.SPUtils;
import com.dsgj.youyuntong.Utils.log.LogUtils;
import com.dsgj.youyuntong.Utils.recyclerview.LinearLayoutUtils;
import com.dsgj.youyuntong.Utils.view.XBannerUtils;
import com.dsgj.youyuntong.Utils.system.callPhoneUtils;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.activity.CalendarActivity;
import com.dsgj.youyuntong.activity.CitiesActivity;
import com.dsgj.youyuntong.activity.GroupTour.GroupTourActivity;
import com.dsgj.youyuntong.activity.Message.MessageActivity;
import com.dsgj.youyuntong.activity.Search.SearchActivity;
import com.dsgj.youyuntong.activity.ThroughTrain.ThroughTrainActivity;
import com.dsgj.youyuntong.activity.ThroughTrain.ThroughTrainInquiryActivity;
import com.dsgj.youyuntong.activity.Ticket.TicketActivity;
import com.dsgj.youyuntong.adapter.HomePageRecyclerViewAdapter;
import com.dsgj.youyuntong.base.BaseFragment;
import com.google.gson.Gson;
import com.jauker.widget.BadgeView;
import com.stx.xhb.xbanner.XBanner;

import java.text.SimpleDateFormat;
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

    private static final int MSG_UPDATE_TEXT = 1;
    private static final int INTERNET_ERROR = 2;
    public String s;
    public TextView mLocation;
    private TextView mSearch;
    private ImageView mPhone;
    private ImageView mMessages;
    private RecyclerView mRecyclerView;
    private List<Map<String, Object>> mGridViewList;
    private int height = 458;// 滑动开始变色的高,真实项目中此高度是由广告轮播或其他首页view高度决定
    private int overallXScroll = 0;
    private List<String> mImageUrl;
    //百度地图定位：
    public LocationClient mLocationClient;
    public BDLocationListener mBDLocationListener = new MyLocationListener();
    private String Location = " ";
    private BadgeView mMBadgeView;
    private LinearLayout mLlTitle;
    private XBanner mXBannerHomePage;
    private NestedScrollView mNsvHomePager;
    private ProgressBar mProgressBar;
    private Handler handler = new Handler() {
        // 该方法运行在主线程中
        // 接收到handler发送的消息，对UI进行操作
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_UPDATE_TEXT:
                    mSearch1.setVisibility(View.VISIBLE);
                    fourItem.setVisibility(View.VISIBLE);
                    mGridViewList = LinearLayoutUtils.getStringAndLogo();
                    setSearch(mSearch1);
                    setFourSelect(fourItem);
                    mGridViewList = new ArrayList<>();
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
                    XBannerUtils.setBannerHolder(getActivity(), mXBannerHomePage, mImageUrl);//处理轮播图
                    //获得门票等四个布局

                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    HomePageRecyclerViewAdapter adapter = new HomePageRecyclerViewAdapter(getActivity()
                            , mGridViewList
                            , mProductListBean);
                    mRecyclerView.setNestedScrollingEnabled(false);
                    mRecyclerView.setAdapter(adapter);

                    //标题栏变色
                    mNsvHomePager.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                        @Override
                        public void onScrollChange(NestedScrollView v,
                                                   int scrollX, int scrollY,
                                                   int oldScrollX, int oldScrollY) {
                            overallXScroll = overallXScroll + scrollY - oldScrollY;// 累加y值 解决滑动一半y值为0
                            if (overallXScroll <= 0) {   //设置标题的背景颜色
                                mLlTitle.setBackgroundResource(R.drawable.shape_title_back);
                            } else if (overallXScroll > 0 && overallXScroll <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
                                float scale = (float) overallXScroll / height;
                                float alpha = (255 * scale);
                                mLlTitle.setBackgroundColor(Color.argb((int) alpha, 0, 145, 242));
                            } else {
                                mLlTitle.setBackgroundColor(Color.argb(255, 0, 145, 242));
                            }
                        }

                    });
                    ProgressBarDismiss();
                    mCoverView.setVisibility(View.GONE);
                    break;
                case INTERNET_ERROR:
                    ToastUtils.show(getActivity(), "网络已断开!");
                    break;
                default:
                    break;
            }
        }
    };

    private List<HomePageBean.ResultBean.ProductListBean> mProductListBean;
    private View mCoverView;
    private LinearLayout mSearch1;
    private LinearLayout fourItem;
    private String mThatTime;

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
        mXBannerHomePage = (XBanner) view.findViewById(R.id.xb_homePage_xb);
        mNsvHomePager = (NestedScrollView) view.findViewById(R.id.nsv_home_pager);
        mProgressBar = (ProgressBar) view.findViewById(R.id.pb_load);
        mCoverView = view.findViewById(R.id.view_home_fragment);
        mSearch1 = (LinearLayout) view.findViewById(R.id.ll_home_page_search);
        fourItem = (LinearLayout) view.findViewById(R.id.ll_home_four_item);

    }

    @Override
    protected void initData() {

        mRecyclerView.setFocusable(false);
        GetInternetData();


    }

    private void GetInternetData() {
        mProgressBar.setVisibility(View.VISIBLE);
        new Thread() {

            @Override
            public void run() {
                super.run();
                Map<String, String> map = new HashMap<>();
                map.put("page", "");
                map.put("city", "西安");
                map.put("page_size", "15");
                HttpUtils.post(getActivity(), new HomePageBean(), HttpUtils.URL_BASE + "home", map, new RequestCallBack() {
                    @Override
                    public void onOutNet() {
                        handler.sendEmptyMessage(INTERNET_ERROR);//发送消息
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
                        }
                        mProductListBean = homepageBean.getProduct_list();
                        handler.sendEmptyMessage(MSG_UPDATE_TEXT);//发送消息
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

    private void ProgressBarDismiss() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_Location:
                jumpToActivity(CitiesActivity.class);

                break;
            case R.id.iv_phone://电话（完成）
                String phoneNumber = "13623717683";
                callPhoneUtils.MakePhone(getActivity(), phoneNumber);
                break;
            case R.id.iv_message://跳转到消息页面（完成）
                SPUtils.with(getActivity()).save("message_unread", "0");
                jumpToActivity(MessageActivity.class);
                break;
            case R.id.et_search://跳转到消息页面（完成）
                jumpToActivity(SearchActivity.class);
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

    private Activity mContext = getActivity();

    private void setSearch(LinearLayout view) {
        final TextView mBusTicket = (TextView) view.findViewById(R.id.bus_ticket);
        TextView fromLocation = (TextView) view.findViewById(R.id.from_location);
        final TextView toLocation = (TextView) view.findViewById(R.id.to_location);
        final TextView goOfTime = (TextView) view.findViewById(R.id.go_of_time);
        long today = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd EEEE");
        String now = sdf.format(today);
        mThatTime = SPUtils.with(mContext).get("selectedDate", now);

        goOfTime.setText(mThatTime);
        Button queryButton = (Button) view.findViewById(R.id.btn_home_query);
        final String start = SPUtils.with(mContext).get("出发位置", "郑州");
        final String end = SPUtils.with(mContext).get("目的位置", "郑州");
        fromLocation.setText(start);
        toLocation.setText(end);
        mBusTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e("汽车票");
            }
        });
        fromLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent StartLocation = new Intent(getActivity(), CitiesActivity.class);
                StartLocation.putExtra("Location", "出发位置");
                getActivity().startActivity(StartLocation);

            }
        });
        toLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ToLocation = new Intent(getActivity(), CitiesActivity.class);
                ToLocation.putExtra("Location", "目的位置");
                getActivity().startActivity(ToLocation);

            }
        });
        goOfTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent time = new Intent(getActivity(), CalendarActivity.class);
                getActivity().startActivity(time);

            }
        });

        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (start.equals(end)) {
                    ToastUtils.show(getActivity(), "出发地和目的地不能相同，请重新设置！");
                } else {
                    Intent intent = new Intent(getActivity(), ThroughTrainInquiryActivity.class);
                    intent.putExtra("startLocation", start);
                    intent.putExtra("endLocation", end);
                    intent.putExtra("time", mThatTime);
                    getActivity().startActivity(intent);
                    SPUtils.with(mContext).remove("selectedDate");
                    SPUtils.with(getActivity()).remove("出发位置");
                    SPUtils.with(getActivity()).remove("目的位置");
                }
            }
        });

    }

    private void setFourSelect(LinearLayout fourItem) {
        LinearLayout llTicket = (LinearLayout)
                fourItem.findViewById(R.id.ll_home_ticket);
        ImageView IvTicket = (ImageView)
                fourItem.findViewById(R.id.iv_home_ticket);
        TextView tvTicket = (TextView)
                fourItem.findViewById(R.id.tv_home_ticket);
        Glide.with(getActivity())
                .load(mGridViewList.get(0).get("pic"))
                .into(IvTicket);
        tvTicket.setText(mGridViewList.get(0).get("title").toString());
        llTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //门票
                Intent intent = new Intent(getActivity(), TicketActivity.class);
                getActivity().startActivity(intent);
            }
        });
        LinearLayout llThroughTrain = (LinearLayout)
                fourItem.findViewById(R.id.ll_home_through_train_search);
        ImageView ivThroughTrain = (ImageView)
                fourItem.findViewById(R.id.iv_home_through_train);
        TextView tvThroughTrain = (TextView)
                fourItem.findViewById(R.id.tv_home_through_train);
        Glide.with(getActivity())
                .load(mGridViewList.get(1).get("pic"))
                .into(ivThroughTrain);
        tvThroughTrain.setText(mGridViewList.get(1).get("title").toString());
        llThroughTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直通车
                Intent intent1 = new Intent(getActivity(), ThroughTrainActivity.class);
                getActivity().startActivity(intent1);
            }
        });
        LinearLayout llGroupTrip = (LinearLayout)
                fourItem.findViewById(R.id.ll_home_group_trip);
        ImageView ivGroupTrip = (ImageView)
                fourItem.findViewById(R.id.iv_home_group_trip);
        TextView tvGroupTrip = (TextView)
                fourItem.findViewById(R.id.tv_home_group_trip);
        Glide.with(getActivity())
                .load(mGridViewList.get(2).get("pic"))
                .into(ivGroupTrip);
        tvGroupTrip.setText(mGridViewList.get(2).get("title").toString());
        llGroupTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跟团游
                Intent intent2 = new Intent(getActivity(), GroupTourActivity.class);
                getActivity().startActivity(intent2);
            }
        });
        LinearLayout llGuide = (LinearLayout)
                fourItem.findViewById(R.id.ll_home_guide);
        ImageView ivGuide = (ImageView)
                fourItem.findViewById(R.id.iv_home_guide);
        TextView tvGuide = (TextView)
                fourItem.findViewById(R.id.tv_home_guide);
        Glide.with(getActivity())
                .load(mGridViewList.get(3).get("pic"))
                .into(ivGuide);
        tvGuide.setText(mGridViewList.get(3).get("title").toString());
        llGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //导游
                ToastUtils.show(getActivity(), "功能正在开发中，敬请期待！");
            }
        });
    }

}