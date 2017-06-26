package com.dsgj.youyuntong.fragment.fragment;


import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dsgj.youyuntong.JavaBean.LogInBean;
import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.Http.HttpUtils;
import com.dsgj.youyuntong.Utils.Http.RequestCallBack;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.base.BaseFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * 出国游的fragment
 */

public class TravelAbordFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private static final int INTERNET_SUCCESS = 1;

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

    }


    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {

            Map<String, String> map = new HashMap<>();
            map.put("type", "国内游");
            map.put("page", "");
            map.put("city", "郑州市");
            map.put("page_size", "15");
            HttpUtils.post(getActivity(), new LogInBean(), HttpUtils.URL_BASE_TOURISM + "package_tour", map, new RequestCallBack() {
                @Override
                public void onOutNet() {

                }

                @Override
                public void onSuccess(String data) {
                    ToastUtils.show(getActivity(), "出国游的下边的列表数据获取成功！");
                }

                @Override
                public void onFailure(int code) {

                }

                @Override
                public void onError(Exception e) {

                }
            });


        }

    }



}



