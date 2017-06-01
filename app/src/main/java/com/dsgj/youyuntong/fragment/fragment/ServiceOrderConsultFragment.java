package com.dsgj.youyuntong.fragment.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.adapter.ServiceOrderRecyclerViewAdapter;
import com.dsgj.youyuntong.base.BaseFragment;

/**
 * Created by zhangyunhao on 2017/5/2.
 */
public class ServiceOrderConsultFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private ServiceOrderRecyclerViewAdapter mMAdapter;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_service_order_consult;
    }

    @Override
    protected void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_order_consult);

    }

    @Override
    protected void initData() {

        mMAdapter = new ServiceOrderRecyclerViewAdapter(getActivity(), 3);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mMAdapter);


    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
