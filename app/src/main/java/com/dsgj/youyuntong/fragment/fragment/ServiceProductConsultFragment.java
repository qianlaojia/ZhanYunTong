package com.dsgj.youyuntong.fragment.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.adapter.ServiceRecyclerGridViewAdapter;
import com.dsgj.youyuntong.base.BaseFragment;


/**
 * Created by zhangyunhao on 2017/5/2.
 */

public class ServiceProductConsultFragment extends BaseFragment {

    public RecyclerView mRecyclerView;
    private String[] mStrings = {"景区直通车"
            , "跟团游", "周边游", "国内游"
            , "出境游", "门票", "汽车票", "随身导服"};
    private int[] mImages = {R.mipmap.service_zhitongche, R.mipmap.service_gentuanyou
            , R.mipmap.service_zhoubianyou, R.mipmap.service_guoneiyou
            , R.mipmap.service_chujingyou, R.mipmap.service_menpiao
            , R.mipmap.service_kechepiao, R.mipmap.service_daofu};
    private ServiceRecyclerGridViewAdapter mAdapter;


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_service_product_consult;
    }

    @Override
    protected void initView(View view) {

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_service_product_consult);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new ServiceRecyclerGridViewAdapter(getActivity(), mImages, mStrings);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {


    }
}

