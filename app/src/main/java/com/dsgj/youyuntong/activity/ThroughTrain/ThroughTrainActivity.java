package com.dsgj.youyuntong.activity.ThroughTrain;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsgj.youyuntong.JavaBean.ThroughTrip.ThroughTripBean;
import com.dsgj.youyuntong.JavaBean.ThroughTrip.ThroughTripHotPotImageBean;
import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.Http.HttpUtils;
import com.dsgj.youyuntong.Utils.Http.RequestCallBack;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.Utils.view.DividerItemDecoration;
import com.dsgj.youyuntong.Utils.view.XBannerUtils;
import com.dsgj.youyuntong.activity.ProductDetailActivity;
import com.dsgj.youyuntong.activity.Search.SearchActivity;
import com.dsgj.youyuntong.activity.Ticket.TicketActivity;
import com.dsgj.youyuntong.adapter.ThroughTrain.ThroughTrainRecycleViewAdapter;
import com.dsgj.youyuntong.adapter.ThroughTrain.ThroughTrainVerticalRecycleViewAdapter;
import com.dsgj.youyuntong.base.BaseActivity;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThroughTrainActivity extends BaseActivity {
    private RelativeLayout mBack;
    private TextView mMiddleText;
    private XBanner mXBanner;
    private static final int GET_INTERNET_SUCCESS = 1;
    private static final int NET_OUT = 2;
    private RecyclerView mRecyclerView;
    private ThroughTrainRecycleViewAdapter mMAdapter;
    private TextView mSearch;
    private RecyclerView mVerticalRecycleView;
    private Map<String, String> mMap;
    private View mCoverView;
    private ProgressBar mProgressBar;
    private List<String> mXBananaUrl;
    private List<String> mMHotPotsName = new ArrayList<>();
    private List<String> mHotPotsPrice = new ArrayList<>();
    private List<String> mHotPotsImage = new ArrayList<>();
    private List<ThroughTripBean.ResultBean.ProductListBean> mProduct;
    private ThroughTripHotPotImageBean mBean;
    private List<ThroughTripBean.ResultBean.ScenicHotBean> mScenicHotBeen;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_through_train;
    }

    @Override
    protected void initView() {
        mBack = (RelativeLayout) findViewById(R.id.rl_title_back);
        mMiddleText = (TextView) findViewById(R.id.tv_middle_text);
        mXBanner = (XBanner) findViewById(R.id.XB_through_trip);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_tt_hot_spots);
        mSearch = (TextView) findViewById(R.id.tv_through_train_search);
        mVerticalRecycleView = (RecyclerView) findViewById(R.id.rv_through_train_vrv_location);
        mCoverView = findViewById(R.id.view_Through_trip);
        mProgressBar = (ProgressBar) findViewById(R.id.pb_through_trip_progress_bar);
    }

    @Override
    protected void initData() {
        mMiddleText.setText("直通车");
        getServerData();
    }

    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);
        mSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_title_back:
                this.finish();
                break;
            case R.id.tv_through_train_search:
                jumpToActivity(SearchActivity.class);
                break;

        }

    }

    private void getServerData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                mMap = new HashMap<>();
                mMap.put("page", "");
                mMap.put("city", "周口");
                HttpUtils.post(ThroughTrainActivity.this, new ThroughTripBean(), HttpUtils.URL_BASE_TOURISM + "through", mMap, new RequestCallBack() {


                    @Override
                    public void onOutNet() {
                        mHandler.sendEmptyMessage(NET_OUT);
                    }

                    @Override
                    public void onSuccess(String data) {
                        Gson gson = new Gson();
                        ThroughTripBean.ResultBean resultBean = gson.fromJson(data
                                , ThroughTripBean.ResultBean.class);
                        List<ThroughTripBean.ResultBean.SlideBean> slideBean = resultBean.getSlide();
                        //获取轮播图的图片：
                        mXBananaUrl = new ArrayList<>();
                        for (int i = 0; i < slideBean.size(); i++) {
                            mXBananaUrl.add("http://59.110.106.1" + slideBean.get(i).getSlide_pic());
                        }
                        mScenicHotBeen = resultBean.getScenic_hot();


                        for (int j = 0; j < mScenicHotBeen.size(); j++) {
                            mBean = gson.fromJson(mScenicHotBeen.get(j).getSmeta(), ThroughTripHotPotImageBean.class);
                            mHotPotsImage.add(mBean.getThumb());
                            mMHotPotsName.add(mScenicHotBeen.get(j).getTitle());
                            mHotPotsPrice.add(mScenicHotBeen.get(j).getPrice());
                        }
                        //当地直通车
                        mProduct = resultBean.getProduct_list();

                        mHandler.sendEmptyMessage(GET_INTERNET_SUCCESS);
                    }

                    @Override
                    public void onFailure(int code) {
                        mHandler.sendEmptyMessage(NET_OUT);
                    }

                    @Override
                    public void onError(Exception e) {
                        mHandler.sendEmptyMessage(NET_OUT);
                    }
                });


            }
        }.start();
    }


    private void nextStep() {
        //轮播图
        XBannerUtils.setBannerHolder(ThroughTrainActivity.this, mXBanner, mXBananaUrl);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mMAdapter = new ThroughTrainRecycleViewAdapter(this, mHotPotsImage, mMHotPotsName, mHotPotsPrice);
        mMAdapter.setOnItemClickListener(new ThroughTrainRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                String productId = mScenicHotBeen.get(position).getProduct_id();
                String productCode = mScenicHotBeen.get(position).getProduct_code();
                Intent intent = new Intent(ThroughTrainActivity.this, ProductDetailActivity.class);
                intent.putExtra("product_id", productId);
                intent.putExtra("product_code", productCode);
                startActivity(intent);

            }
        });
        mRecyclerView.setAdapter(mMAdapter);
        //当地直通车的recyclerView
        //设置布局
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(this);
        verticalLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mVerticalRecycleView.setLayoutManager(verticalLayoutManager);
        //设置适配器
        ThroughTrainVerticalRecycleViewAdapter verticalAdapter = new ThroughTrainVerticalRecycleViewAdapter(this, mProduct);
        mVerticalRecycleView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
        verticalAdapter.setOnItemClickListener(new ThroughTrainVerticalRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String productId = mProduct.get(position).getProduct_id();
                String productCode = mProduct.get(position).getProduct_code();
                Intent intent = new Intent(ThroughTrainActivity.this, ProductDetailActivity.class);
                intent.putExtra("product_id", productId);
                intent.putExtra("product_code", productCode);
                startActivity(intent);
            }
        });
        mVerticalRecycleView.setAdapter(verticalAdapter);
        mVerticalRecycleView.setNestedScrollingEnabled(false);

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case GET_INTERNET_SUCCESS:
                    nextStep();
                    ToastUtils.show(ThroughTrainActivity.this, "获取数据成功！");
                    mCoverView.setVisibility(View.GONE);
                    mProgressBar.setVisibility(View.GONE);
                    break;
                case NET_OUT:
                    ToastUtils.show(ThroughTrainActivity.this, "网络已断开！");
                    break;
            }
        }
    };
}
