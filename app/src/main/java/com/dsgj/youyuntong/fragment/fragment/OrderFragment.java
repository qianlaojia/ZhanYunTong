package com.dsgj.youyuntong.fragment.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.adapter.OrderPagerRecyclerViewAdapter;
import com.dsgj.youyuntong.base.BaseFragment;

/**
 * 订单的Fragment
 * Created by 张云浩  on 2017/4/19.
 * 邮箱：943332771@qq.com
 */

public class OrderFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private ImageView mBack;
    private TextView mMiddleText;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_trip;
    }


    @Override
    protected void initView(View view) {
        mBack = (ImageView) view.findViewById(R.id.iv_back);
        mMiddleText = (TextView) view.findViewById(R.id.tv_middle_text);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_order_fragment);
    }

    @Override
    protected void initData() {
        mMiddleText.setText("全部订单");
        mBack.setVisibility(View.GONE);
        OrderPagerRecyclerViewAdapter orderPagerRecyclerViewAdapter = new OrderPagerRecyclerViewAdapter(getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(orderPagerRecyclerViewAdapter);
    }

    @Override
    protected void initListener() {


    }


    @Override
    public void onClick(View v) {


    }


}

