package com.dsgj.youyuntong.fragment.fragment;


import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dsgj.youyuntong.JavaBean.GroupTour.InCountryBean;
import com.dsgj.youyuntong.JavaBean.LogInBean;
import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.Http.HttpUtils;
import com.dsgj.youyuntong.Utils.Http.RequestCallBack;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.Utils.view.DividerItemDecoration;
import com.dsgj.youyuntong.activity.ProductDetailActivity;
import com.dsgj.youyuntong.adapter.GroupTrip.InCountryAdapter;
import com.dsgj.youyuntong.base.BaseFragment;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TravelInCountryFragment extends BaseFragment {


    private RecyclerView mRecyclerView;
    public static final int INTERNET_SUCCESS = 1;
    public static final int NET_OUT = 2;
    public static final int DATA_ERROR = 3;
    private List<InCountryBean.ResultBean.ProductListBean> mProductListBeanList;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_travel_in_country;
    }

    @Override
    protected void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_Group_trip_travel_in);

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
            //相当于Fragment的onResume
            new Thread() {

                @Override
                public void run() {
                    super.run();
                    Map<String, String> map = new HashMap<>();
                    map.put("type", "国内游");
                    map.put("page", "");
                    map.put("city", "郑州市");
                    map.put("page_size", "3");
                    HttpUtils.post(getActivity(), new LogInBean(), HttpUtils.URL_BASE_TOURISM + "package_tour", map, new RequestCallBack() {
                        @Override
                        public void onOutNet() {
                            mHandler.sendEmptyMessage(NET_OUT);
                        }

                        @Override
                        public void onSuccess(String data) {
                            Gson gson = new Gson();
                            InCountryBean.ResultBean resultBean = gson.fromJson(data
                                    , InCountryBean.ResultBean.class);
                            mProductListBeanList = resultBean.getProduct_list();

                                //设置格式
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器
        InCountryAdapter inCountryAdapter = new
                InCountryAdapter(getActivity(),mProductListBeanList);
                            inCountryAdapter.setOnItemClickListener(new InCountryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String productId = mProductListBeanList.get(position).getProduct_id();
                String productCode = mProductListBeanList.get(position).getProduct_code();
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("product_id", productId);
                intent.putExtra("product_code", productCode);
                startActivity(intent);
            }
        });
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(inCountryAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);

                            mHandler.sendEmptyMessage(INTERNET_SUCCESS);
                        }

                        @Override
                        public void onFailure(int code) {
                            mHandler.sendEmptyMessage(DATA_ERROR);
                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });


                }
            }.start();
        } else {
            //相当于Fragment的onPause
        }
    }

    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case INTERNET_SUCCESS:
                    ToastUtils.show(getActivity(), "国内游的下边的列表数据获取成功！");
                    break;
                case NET_OUT:
                    ToastUtils.show(getActivity(), "网络已断开！");
                    break;
                case DATA_ERROR:
                    ToastUtils.show(getActivity(), "数据异常，请稍候再试！");
                    break;
            }
        }
    };
}
