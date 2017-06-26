package com.dsgj.youyuntong.fragment.fragment;


import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dsgj.youyuntong.JavaBean.GroupTour.AroundTripBean;
import com.dsgj.youyuntong.JavaBean.LogInBean;
import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.Http.HttpUtils;
import com.dsgj.youyuntong.Utils.Http.RequestCallBack;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.Utils.log.LogUtils;
import com.dsgj.youyuntong.Utils.view.DividerItemDecoration;
import com.dsgj.youyuntong.activity.ProductDetailActivity;
import com.dsgj.youyuntong.adapter.GroupTrip.AroundTourAdapter;
import com.dsgj.youyuntong.base.BaseFragment;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TravelAroundFragment extends BaseFragment {


    private RecyclerView mRecyclerView;


    public static final int INTERNET_SUCCESS = 1;
    public static final int NET_OUT = 2;
    public static final int NET_ERROR = 3;
    public static final int FAILURE = 4;
    private List<AroundTripBean.ResultBean.ProductListBean> mProductListBeen;


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_travel_around;
    }

    @Override
    protected void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_Group_trip_travel_around);
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
    public void setUserVisibleHint(boolean isVisibleToUser) {//这个只能添加新线程获取网络
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //相当于Fragment的onResume
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    Map<String, String> map = new HashMap<>();
                    map.put("type", "周边游");
                    map.put("page", "");
                    map.put("city", "西安市");
                    map.put("page_size", "15");
                    HttpUtils.post(getActivity(), new LogInBean(), HttpUtils.URL_BASE_TOURISM + "package_tour", map, new RequestCallBack() {
                        @Override
                        public void onOutNet() {
                            mHandler.sendEmptyMessage(NET_OUT);
                        }

                        @Override
                        public void onSuccess(String data) {
                            Gson gson = new Gson();
                            AroundTripBean.ResultBean resultBean = gson.fromJson(data
                                    , AroundTripBean.ResultBean.class);
                            mProductListBeen = resultBean.getProduct_list();
                            LogUtils.e("下边是周边游的个数：：：：" + mProductListBeen.size());
                            ToastUtils.show(getActivity(), "下边的列表数据获取成功！");
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            mRecyclerView.setLayoutManager(linearLayoutManager);
                            AroundTourAdapter groupTripAroundRecyclerViewAdapter = new
                                    AroundTourAdapter(getActivity(), mProductListBeen);
                            groupTripAroundRecyclerViewAdapter.setOnItemClickListener(new AroundTourAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    String productId = mProductListBeen.get(position).getProduct_id();
                                    String productCode = mProductListBeen.get(position).getProduct_code();
                                    Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                                    intent.putExtra("product_id", productId);
                                    intent.putExtra("product_code", productCode);
                                    startActivity(intent);
                                }
                            });
                            mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
                            mRecyclerView.setAdapter(groupTripAroundRecyclerViewAdapter);
                            mRecyclerView.setNestedScrollingEnabled(false);
                            mHandler.sendEmptyMessage(INTERNET_SUCCESS);
                        }

                        @Override
                        public void onFailure(int code) {
                            mHandler.sendEmptyMessage(FAILURE);
                        }

                        @Override
                        public void onError(Exception e) {
                            mHandler.sendEmptyMessage(NET_ERROR);
                        }
                    });


                }
            }.start();
        }

    }

    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case INTERNET_SUCCESS:
                    ToastUtils.show(getActivity(), "下边的列表数据获取成功！");
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    mRecyclerView.setLayoutManager(linearLayoutManager);
                    AroundTourAdapter groupTripAroundRecyclerViewAdapter = new
                            AroundTourAdapter(getActivity(), mProductListBeen);
                    groupTripAroundRecyclerViewAdapter.setOnItemClickListener(new AroundTourAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            String productId = mProductListBeen.get(position).getProduct_id();
                            String productCode = mProductListBeen.get(position).getProduct_code();
                            Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                            intent.putExtra("product_id", productId);
                            intent.putExtra("product_code", productCode);
                            startActivity(intent);
                        }
                    });
                    mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
                    mRecyclerView.setAdapter(groupTripAroundRecyclerViewAdapter);
                    mRecyclerView.setNestedScrollingEnabled(false);
                    break;
                case NET_OUT:
                    ToastUtils.show(getActivity(), "网络已断开！");
                    break;
                case FAILURE:
                    ToastUtils.show(getActivity(), "获取数据失败请稍后再试！");
                    break;
                case NET_ERROR:
                    ToastUtils.show(getActivity(), "获取数据失败请稍后再试！");
                    break;
            }
        }
    };
}
