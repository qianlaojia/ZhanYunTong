package com.dsgj.youyuntong.fragment.fragment;

import android.view.View;
import android.widget.RelativeLayout;

import com.dsgj.youyuntong.JavaBean.CollectionBean;
import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.Http.HttpUtils;
import com.dsgj.youyuntong.Utils.Http.RequestCallBack;
import com.dsgj.youyuntong.Utils.log.LogUtils;
import com.dsgj.youyuntong.base.BaseFragment;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

/**
 * TODO:
 * Created by 张云浩  on 2017/6/23.
 * 邮箱：943332771@qq.com
 */

public class CollectGroupTourFragment extends BaseFragment {

    private RelativeLayout mNoInternet;
    private RelativeLayout mNoData;

    private Map<String, String> mMap;

    public CollectGroupTourFragment(Map<String, String> map) {
        this.mMap = map;


    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_collect_group_tour;
    }

    @Override
    protected void initView(View view) {
        mNoData = (RelativeLayout) view.findViewById(R.id.il_no_data);
        mNoInternet = (RelativeLayout) view.findViewById(R.id.il_no_internet);
    }

    @Override
    protected void initData() {
        HttpUtils.post(getActivity(), new CollectionBean()
                , HttpUtils.URL_BASE_USER + "collection_list"
                , mMap, new RequestCallBack() {
                    @Override
                    public void onOutNet() {
                    }

                    @Override
                    public void onSuccess(String data) {
                        Gson gson = new Gson();
                        CollectionBean.ResultBean resultBean = gson.fromJson(data
                                , CollectionBean.ResultBean.class);
                        List<CollectionBean.ResultBean.TourismBean> mTourismBeanList = resultBean.getTourism();
                        LogUtils.e("这是获得的集合的大小：", mTourismBeanList.size()+"");
                    }

                    @Override
                    public void onFailure(int code) {
                        LogUtils.e("请求失败！");

                    }

                    @Override
                    public void onError(Exception e) {
                        LogUtils.e("出现错误！");
                    }
                });
    }


    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {

    }


}
