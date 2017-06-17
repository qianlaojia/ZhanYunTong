package com.dsgj.youyuntong.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
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
import com.dsgj.youyuntong.Utils.log.LogUtils;
import com.dsgj.youyuntong.Utils.view.XBannerUtils;
import com.dsgj.youyuntong.activity.ThroughTrain.ThroughTrainInputInfoActivity;
import com.dsgj.youyuntong.base.BaseActivity;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;

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
        mMiddleText =
                (TextView) findViewById(R.id.tv_middle_text);
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
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String product_id = intent.getStringExtra("product_id");
        String product_code = intent.getStringExtra("product_code");
        internetDataGet(product_id, product_code);
        mEndText.setText("我是有底线的");
        mMiddleText.setText("产品详情");
        mEndText.setTextColor(Color.GRAY);
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
                //todo 携带相关的参数    旅游名称 出发时间    预订人信息    出发地点等等；
                jumpToActivity(ThroughTrainInputInfoActivity.class);
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
        mProductCode.setText(mResultBean.getProduct_code());
        mMProductName.setText(mResultBean.getTitle());
        mProductPrice.setText("¥" + mResultBean.getPrice());
        mFromCity.setText(mResultBean.getCity());
        mWebView.loadDataWithBaseURL(null, mResultBean.getContent(), "text/html", "utf-8", null);
        mPb.setVisibility(View.GONE);
        mView.setVisibility(View.GONE);
        mNestedScrollView.setVisibility(View.VISIBLE);
        mBottom.setVisibility(View.VISIBLE);
    }
}
