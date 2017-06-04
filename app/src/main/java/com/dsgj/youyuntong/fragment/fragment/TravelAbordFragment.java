package com.dsgj.youyuntong.fragment.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.Utils.view.DividerItemDecoration;
import com.dsgj.youyuntong.adapter.GroupTrip.GroupTripRecyclerViewAdapter;
import com.dsgj.youyuntong.base.BaseFragment;

/**
 * 出国游的fragment
 */

public class TravelAbordFragment extends BaseFragment {
    private RecyclerView mRecyclerView;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_travel_abord;
    }

    @Override
    protected void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_Group_trip_travel_out);
    }

    @Override
    protected void initData() {
        //设置格式
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器
        GroupTripRecyclerViewAdapter groupTripAroundRecyclerViewAdapter = new
                GroupTripRecyclerViewAdapter(getActivity());
        groupTripAroundRecyclerViewAdapter.setOnItemClickListener(new GroupTripRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtils.show(getActivity(), "第" + position + "个被点击！");
            }
        });
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(groupTripAroundRecyclerViewAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
    }


    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
