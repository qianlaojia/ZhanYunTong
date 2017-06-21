package com.dsgj.youyuntong.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsgj.youyuntong.JavaBean.ProductDetail.ProductDetailBean;
import com.dsgj.youyuntong.JavaBean.ProductDetail.ProductDetailImageBean;
import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.Http.HttpUtils;
import com.dsgj.youyuntong.Utils.Http.RequestCallBack;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.Utils.view.XBannerUtils;
import com.dsgj.youyuntong.adapter.StartTimeAdapter;
import com.dsgj.youyuntong.base.BaseActivity;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDetailActivity extends BaseActivity {
    private TextView mService;
    private TextView mShare;
    private TextView mCollect;
    private TextView mDestine;
    private RelativeLayout mBack;
    private int overallXScroll = 0;
    private int height = 458;
    private static final int NET_OUT = 1;
    private static final int INTERNET_SUCCESS = 2;
    private static final int ERROR = 3;
    private ProductDetailBean.ResultBean mResultBean;
    private XBanner mXbImage;
    private List<ProductDetailImageBean.PhotoBean> mImageBeanList;
    private TextView mProductCode;
    private TextView mMProductName;
    private TextView mProductPrice;
    private TextView mFromCity;
    private WebView mWebView;
    private TextView mEndText;
    private View mView;
    private ProgressBar mPb;
    private NestedScrollView mNestedScrollView;
    private LinearLayout mBottom;
    private LinearLayout mTitle;
    private TextView mMiddleText;
    private RecyclerView mStartTime;
    private String mSceneryName;
    private String mPrice;
    private String mLocation;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_through_train_detail;
    }

    @Override
    protected void initView() {
        mService = (TextView) findViewById(R.id.tv_custom_service);
        mShare = (TextView) findViewById(R.id.tv_tt_detail_share);
        mCollect = (TextView) findViewById(R.id.tv_tt_detail_collect);
        mDestine = (TextView) findViewById(R.id.tv_tt_detail_destine);
        mMiddleText = (TextView) findViewById(R.id.tv_middle_text);
        mXbImage = (XBanner) findViewById(R.id.xb_product_detail);
        mProductCode = (TextView) findViewById(R.id.tv_item_number_detail);
        mMProductName = (TextView) findViewById(R.id.tv_detail_name);
        mProductPrice = (TextView) findViewById(R.id.tv_detail_price);
        mFromCity = (TextView) findViewById(R.id.tv_from_location);
        mWebView = (WebView) findViewById(R.id.wv_trip_introduce);
        mEndText = (TextView) findViewById(R.id.tv_middle_product_detail);
        mView = findViewById(R.id.view_product_detail);
        mPb = (ProgressBar) findViewById(R.id.pb_product_detail);
        mNestedScrollView = (NestedScrollView) findViewById(R.id.nsv_product_detail);
        mBottom = (LinearLayout) findViewById(R.id.bottom_product_detail);
        mTitle = (LinearLayout) findViewById(R.id.ll_product_detail_title);
        mBack = (RelativeLayout) findViewById(R.id.rl_title_back);
        mStartTime = (RecyclerView) findViewById(R.id.rv_departure_time);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String product_id = intent.getStringExtra("product_id");
        final String product_code = intent.getStringExtra("product_code");
        internetDataGet(product_id, product_code);
        mEndText.setText("我是有底线的");
        mMiddleText.setText("产品详情");
        mEndText.setTextColor(Color.GRAY);
        //获得时间：
        final List<String> dates = new ArrayList<>();
        List<String> weeks = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            long currentTimeMillis = System.currentTimeMillis() + 86400000 * i;
            SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat weekFm = new SimpleDateFormat("EEEE");
            String date = dateFm.format(currentTimeMillis);
            String week = weekFm.format(currentTimeMillis);
            dates.add(date);
            weeks.add(week);
        }
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put(ProductDetailActivity.RecyclerViewSpacesItemDecoration.TOP_DECORATION, 10);//top间距
        stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.RIGHT_DECORATION, 9);
        stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.LEFT_DECORATION, 9);
        stringIntegerHashMap.put(ProductDetailActivity.RecyclerViewSpacesItemDecoration.BOTTOM_DECORATION, 10);//底部间距
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        mStartTime.setLayoutManager(gridLayoutManager);
        StartTimeAdapter startTimeAdapter = new StartTimeAdapter(this, dates, weeks);
        startTimeAdapter.setOnItemClickListener(new StartTimeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(ProductDetailActivity.this, DateAndNumActivity.class);
                intent.putExtra("selectTime", dates.get(position));
                intent.putExtra("sceneryName", mSceneryName);
                intent.putExtra("sceneryPrice", mPrice);
                intent.putExtra("startLocation", mLocation);
                startActivity(intent);

            }
        });
        mStartTime.addItemDecoration(new ProductDetailActivity.RecyclerViewSpacesItemDecoration(stringIntegerHashMap));
        mStartTime.setAdapter(startTimeAdapter);
    }


    @Override
    protected void initListener() {
        mService.setOnClickListener(this);
        mShare.setOnClickListener(this);
        mCollect.setOnClickListener(this);
        mDestine.setOnClickListener(this);
        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_title_back:
                this.finish();
                break;
            case R.id.tv_custom_service:
                ToastUtils.show(this, "客服");
                break;
            case R.id.tv_tt_detail_share:
                ToastUtils.show(this, "分享");
                break;
            case R.id.tv_tt_detail_collect:
                ToastUtils.show(this, "收藏");
                break;
            case R.id.tv_tt_detail_destine:
                Intent intent = new Intent(ProductDetailActivity.this, DateAndNumActivity.class);
                intent.putExtra("selectTime", "");
                intent.putExtra("sceneryName", mSceneryName);
                intent.putExtra("sceneryPrice", mPrice);
                intent.putExtra("startLocation", mLocation);
                startActivity(intent);
                break;

        }

    }

    private void internetDataGet(String product_id, String product_code) {
        final Map<String, String> map = new HashMap<>();
        map.put("product_id", product_id);
        map.put("product_code", product_code);
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpUtils.post(ProductDetailActivity.this
                        , new ProductDetailBean()
                        , HttpUtils.URL_BASE_TOURISM + "product_detail"
                        , map
                        , new RequestCallBack() {
                            @Override
                            public void onOutNet() {
                                mHandle.sendEmptyMessage(NET_OUT);
                            }

                            @Override
                            public void onSuccess(String data) {
                                Gson gson = new Gson();
                                mResultBean = gson.fromJson(data
                                        , ProductDetailBean.ResultBean.class);
                                ProductDetailImageBean productDetailImageBean = gson.fromJson(mResultBean.getSmeta(), ProductDetailImageBean.class);
                                mImageBeanList = productDetailImageBean.getPhoto();


                                mHandle.sendEmptyMessage(INTERNET_SUCCESS);

                            }

                            @Override
                            public void onFailure(int code) {
                                mHandle.sendEmptyMessage(ERROR);

                            }

                            @Override
                            public void onError(Exception e) {

                            }
                        });
            }
        }.start();
    }

    private Handler mHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case NET_OUT:
                    ToastUtils.show(ProductDetailActivity.this, "网络已断开！");
                    break;
                case INTERNET_SUCCESS:
                    setDataIntoUI();
                    break;
                case ERROR:
                    ToastUtils.show(ProductDetailActivity.this, "返回数据失败，请稍后重试！");
                    break;
            }
        }
    };

    private void setDataIntoUI() {
        mNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v,
                                       int scrollX, int scrollY,
                                       int oldScrollX, int oldScrollY) {
                overallXScroll = overallXScroll + scrollY - oldScrollY;// 累加y值 解决滑动一半y值为0
                if (overallXScroll <= 0) {   //设置标题的背景颜色
                    mTitle.setBackgroundResource(R.drawable.shape_title_back);
                } else if (overallXScroll > 0 && overallXScroll <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
                    float scale = (float) overallXScroll / height;
                    float alpha = (255 * scale);
                    mTitle.setBackgroundColor(Color.argb((int) alpha, 0, 145, 242));
                } else {
                    mTitle.setBackgroundColor(Color.argb(255, 0, 145, 242));
                }
            }

        });
        List<String> xbImageList = new ArrayList<>();
        for (int i = 0; i < mImageBeanList.size(); i++) {
            xbImageList.add(mImageBeanList.get(i).getUrl());
        }
        XBannerUtils.setBannerHolder(ProductDetailActivity.this, mXbImage, xbImageList);
        mSceneryName = mResultBean.getTitle();
        mPrice = mResultBean.getPrice() ;
        mLocation = mResultBean.getCity();
        mProductCode.setText(mResultBean.getProduct_code());
        mMProductName.setText(mSceneryName);
        mProductPrice.setText("¥" + mPrice);
        mFromCity.setText(mLocation);
        mWebView.setFocusable(false);
        mWebView.setClickable(false);
        mWebView.loadDataWithBaseURL(null, mResultBean.getContent(), "text/html", "utf-8", null);
        mWebView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });


        mPb.setVisibility(View.GONE);
        mView.setVisibility(View.GONE);
        mNestedScrollView.setVisibility(View.VISIBLE);
        mBottom.setVisibility(View.VISIBLE);
    }

    private class RecyclerViewSpacesItemDecoration extends RecyclerView.ItemDecoration {
        private HashMap<String, Integer> mSpaceValueMap;
        static final String TOP_DECORATION = "top_decoration";
        static final String BOTTOM_DECORATION = "bottom_decoration";
        static final String LEFT_DECORATION = "left_decoration";
        static final String RIGHT_DECORATION = "right_decoration";

        RecyclerViewSpacesItemDecoration(HashMap<String, Integer> mSpaceValueMap) {
            this.mSpaceValueMap = mSpaceValueMap;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            if (mSpaceValueMap.get(TOP_DECORATION) != null)
                outRect.top = mSpaceValueMap.get(TOP_DECORATION);
            if (mSpaceValueMap.get(LEFT_DECORATION) != null)

                outRect.left = mSpaceValueMap.get(LEFT_DECORATION);
            if (mSpaceValueMap.get(RIGHT_DECORATION) != null)
                outRect.right = mSpaceValueMap.get(RIGHT_DECORATION);
            if (mSpaceValueMap.get(BOTTOM_DECORATION) != null)
                outRect.bottom = mSpaceValueMap.get(BOTTOM_DECORATION);
        }
    }
}
