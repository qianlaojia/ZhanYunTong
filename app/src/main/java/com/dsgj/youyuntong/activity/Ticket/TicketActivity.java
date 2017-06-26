package com.dsgj.youyuntong.activity.Ticket;

import android.content.Intent;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dsgj.youyuntong.JavaBean.Ticket.TicketBean;
import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.Http.HttpUtils;
import com.dsgj.youyuntong.Utils.Http.RequestCallBack;
import com.dsgj.youyuntong.Utils.SPUtils;
import com.dsgj.youyuntong.Utils.ToastUtils;

import com.dsgj.youyuntong.Utils.view.DividerItemDecoration;
import com.dsgj.youyuntong.Utils.view.XBannerUtils;
import com.dsgj.youyuntong.activity.Message.MessageActivity;
import com.dsgj.youyuntong.activity.ProductDetailActivity;
import com.dsgj.youyuntong.activity.Search.SearchActivity;
import com.dsgj.youyuntong.adapter.GroupTrip.GroupTripRecycleViewAdapter;
import com.dsgj.youyuntong.adapter.Ticket.TicketRecycleViewAdapter;
import com.dsgj.youyuntong.base.BaseActivity;
import com.dsgj.youyuntong.adapter.Ticket.TicketVerticalRecycleViewAdapter;
import com.google.gson.Gson;
import com.jauker.widget.BadgeView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Handler;


public class TicketActivity extends BaseActivity {


    private ImageView mReturn;
    private TextView mSearchText;
    private ImageView mMessage;
    private XBanner mXBanner;
    private BadgeView mMBadgeView;
    private RecyclerView mRecyclerView;

    private TicketRecycleViewAdapter mMAdapter;
    private RecyclerView mVerticalRecycleView;
    private TicketVerticalRecycleViewAdapter mAdapter;
    private static final int GET_DATA_SUCCESS = 1;
    private static final int NET_OUT = 2;
    private View mBackground;
    private ProgressBar mPbBackground;
    private List<String> mImageList;
    private List<TicketBean.ResultBean.ScenicHotBean> mScenicHotBean = new ArrayList<>();
    private List<TicketBean.ResultBean.ProductListBean> mMProductListBean = new ArrayList<>();


    @Override
    protected int getLayoutID() {
        return R.layout.activity_ticket;
    }

    @Override
    protected void initView() {
        mMBadgeView = new BadgeView(this);
        mReturn = (ImageView) findViewById(R.id.iv_return);
        mSearchText = (TextView) findViewById(R.id.tv_search_tips);
        mMessage = (ImageView) findViewById(R.id.iv_news_Image);
        mXBanner = (XBanner) findViewById(R.id.XB_ticket);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_ticket_hot_spots);
        mVerticalRecycleView = (RecyclerView) findViewById(R.id.rv_ticket_detail);
        mBackground = findViewById(R.id.view_ticket);
        mPbBackground = (ProgressBar) findViewById(R.id.pb_ticket);
    }

    @Override
    protected void initData() {
        GetServerData();
        mMBadgeView.setTargetView(mMessage);//处理系统消息提示红点
        String MMessage = SPUtils.with(this).get("message_unread", "0");
        if (MMessage.equals("1")) {
            mMBadgeView.setBadgeCount(1);
        } else {
            mMBadgeView.setBadgeCount(0);
        }


    }


    private void GetServerData() {

        Map<String, String> map = new HashMap<>();
        map.put("page", "");
        map.put("city", "郑州");
        map.put("page_size", "15");
        HttpUtils.post(TicketActivity.this, new TicketBean(), HttpUtils.URL_BASE_TOURISM + "ticket", map, new RequestCallBack() {
            @Override
            public void onOutNet() {
                ToastUtils.show(TicketActivity.this, "网络错误，加载失败，请稍候重试");
            }

            @Override
            public void onSuccess(String data) {
                Gson gson = new Gson();
                TicketBean.ResultBean resultBean = gson.fromJson(data, TicketBean.ResultBean.class);
                //轮播图
                List<TicketBean.ResultBean.SlideBean> slideBean = resultBean.getSlide();
                mImageList = new ArrayList<>();

                for (int i = 0; i < slideBean.size(); i++) {
                    mImageList.add("http://59.110.106.1" + slideBean.get(i).getSlide_pic());
                }
                //热门景点
                mScenicHotBean = resultBean.getScenic_hot();
                mMProductListBean = resultBean.getProduct_list();
                DataFlash();
                mBackground.setVisibility(View.GONE);
                mPbBackground.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(int code) {
                ToastUtils.show(TicketActivity.this, "网络错误，加载失败，请稍候重试");
            }

            @Override
            public void onError(Exception e) {
                ToastUtils.show(TicketActivity.this, "网络错误，加载失败，请稍候重试");
            }
        });
    }


    @Override
    protected void initListener() {
        mReturn.setOnClickListener(this);
        mSearchText.setOnClickListener(this);
        mMessage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_return://返回
                finish();
                break;
            case R.id.tv_search_tips://标题的查询
                jumpToActivity(SearchActivity.class);
                break;
            case R.id.iv_news_Image://消息处理
                SPUtils.with(this).save("message_unread", "0");
                jumpToActivity(MessageActivity.class);
                break;
        }

    }


    /**
     * 网络请求数据成功处理数据。
     */
    private void DataFlash() {
        XBannerUtils.setBannerHolder(this, mXBanner, mImageList);//处理轮播图
        //热门景点
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);//处理横向的recyclerView
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mMAdapter = new TicketRecycleViewAdapter(this, mScenicHotBean);
        mMAdapter.setOnItemClickListener(new TicketRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //判断第几个被点击，获得被点击的产品的id和code
                String productId = mScenicHotBean.get(position).getProduct_id();
                String productCode = mScenicHotBean.get(position).getProduct_code();
                Intent intent = new Intent(TicketActivity.this, ProductDetailActivity.class);
                intent.putExtra("product_id", productId);
                intent.putExtra("product_code", productCode);
                startActivity(intent);

            }
        });
        mRecyclerView.setAdapter(mMAdapter);
        LinearLayoutManager LayoutManager = new LinearLayoutManager(this);
        LayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mVerticalRecycleView.setLayoutManager(LayoutManager);
        mAdapter = new TicketVerticalRecycleViewAdapter(this, mMProductListBean);
        mAdapter.setOnItemClickListener(new GroupTripRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String productId = mMProductListBean.get(position).getProduct_id();
                String productCode = mMProductListBean.get(position).getProduct_code();
                Intent intent = new Intent(TicketActivity.this, ProductDetailActivity.class);
                intent.putExtra("product_id", productId);
                intent.putExtra("product_code", productCode);
                startActivity(intent);
            }
        });
        mVerticalRecycleView.setAdapter(mAdapter);
        mVerticalRecycleView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
        mVerticalRecycleView.setNestedScrollingEnabled(false);
    }

}
