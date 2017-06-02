package com.dsgj.youyuntong.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsgj.youyuntong.JavaBean.GroupTourBean;
import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.Http.HttpUtils;
import com.dsgj.youyuntong.Utils.Http.RequestCallBack;
import com.dsgj.youyuntong.Utils.SPUtils;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.Utils.log.LogUtils;
import com.dsgj.youyuntong.Utils.recyclerview.XBannerUtils;
import com.dsgj.youyuntong.activity.Message.MessageActivity;
import com.dsgj.youyuntong.adapter.GroupTripRecycleViewAdapter;
import com.dsgj.youyuntong.adapter.TicketRecycleViewAdapter;
import com.dsgj.youyuntong.base.BaseActivity;
import com.dsgj.zhanyuntong.adapter.VerticalRecycleViewAdapter;
import com.jauker.widget.BadgeView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketActivity extends BaseActivity {


    private ImageView mReturn;
    private TextView mSearchText;
    private ImageView mMessage;
    private XBanner mXBanner;
    private BadgeView mMBadgeView;
    private RecyclerView mRecyclerView;
    private List<Integer> mMData;
    private List<Integer> mMData1;
    private TicketRecycleViewAdapter mMAdapter;
    private RecyclerView mVertacalRecycleView;
    private VerticalRecycleViewAdapter mAdapter;

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
        mVertacalRecycleView = (RecyclerView) findViewById(R.id.rv_ticket_detail);
    }

    @Override
    protected void initData() {
        GetServerData();
        RvData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mMAdapter = new TicketRecycleViewAdapter(this, mMData);
        mMAdapter.setOnItemClickListener(new TicketRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtils.show(TicketActivity.this, "第" + position + "个被点击！");
            }
        });
        mRecyclerView.setAdapter(mMAdapter);
        RVVertical();
        LinearLayoutManager LayoutManager = new LinearLayoutManager(this);
        LayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mVertacalRecycleView.setLayoutManager(LayoutManager);
        mAdapter = new VerticalRecycleViewAdapter(this, mMData1);
        mAdapter.setOnItemClickListener(new GroupTripRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtils.show(TicketActivity.this, "第" + position + "个被点击");
            }
        });
        mVertacalRecycleView.setAdapter(mAdapter);

        mVertacalRecycleView.setNestedScrollingEnabled(false);
        mMBadgeView.setTargetView(mMessage);
        String MMessage = SPUtils.with(this).get("message_unread", "0");
        if (MMessage.equals("1")) {
            mMBadgeView.setBadgeCount(1);
        } else {
            mMBadgeView.setBadgeCount(0);
        }
        XBannerUtils.setBannerHolder(this, mXBanner);

    }

    private void RVVertical() {
        mMData1 = new ArrayList<>(Arrays.asList(R.mipmap.shilitu,
                R.mipmap.shilitu, R.mipmap.shilitu,
                R.mipmap.shilitu, R.mipmap.shilitu,
                R.mipmap.shilitu, R.mipmap.shilitu,
                R.mipmap.shilitu, R.mipmap.shilitu));
    }

    private void RvData() {
        mMData = new ArrayList<>(Arrays.asList(R.mipmap.shilitu,
                R.mipmap.shilitu, R.mipmap.shilitu,
                R.mipmap.shilitu, R.mipmap.shilitu,
                R.mipmap.shilitu, R.mipmap.shilitu,
                R.mipmap.shilitu, R.mipmap.shilitu));

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
                HttpUtils.post(TicketActivity.this, new GroupTourBean(), HttpUtils.URL_BASE_TOURISM + "ticket", map, new RequestCallBack() {
                    @Override
                    public void onOutNet() {
                        LogUtils.e("网络出现错误");
                        ToastUtils.show(TicketActivity.this, "网络已断开!");
                    }

                    @Override
                    public void onSuccess(String data) {

                        LogUtils.e("ticketActivity    访问成功======================================================");


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
        mReturn.setOnClickListener(this);
        mSearchText.setOnClickListener(this);
        mMessage.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_return:
                finish();
                break;
            case R.id.tv_search_tips:
                ToastUtils.show(this, "跳转到搜索的界面！");
                jumpToActivity(TicketDetailsActivity.class);
                break;
            case R.id.iv_news_Image:
                SPUtils.with(this).save("message_unread", "0");
                jumpToActivity(MessageActivity.class);
                break;

        }

    }


}
