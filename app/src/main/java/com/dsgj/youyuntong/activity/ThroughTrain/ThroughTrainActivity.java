package com.dsgj.youyuntong.activity.ThroughTrain;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.Utils.view.DividerItemDecoration;
import com.dsgj.youyuntong.Utils.view.GridViewTableLine;
import com.dsgj.youyuntong.Utils.view.XBannerUtils;
import com.dsgj.youyuntong.activity.Search.SearchActivity;
import com.dsgj.youyuntong.adapter.ThroughTrain.ThroughTrainRecycleViewAdapter;
import com.dsgj.youyuntong.adapter.ThroughTrain.ThroughTrainVerticalRecycleViewAdapter;
import com.dsgj.youyuntong.base.BaseActivity;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThroughTrainActivity extends BaseActivity {


    private RelativeLayout mBack;
    private TextView mMiddleText;
    private XBanner mXBanner;


    private GridViewTableLine mGridview;
    private String[] mMstrings = new String[]{"张三", "李四", "王五", "赵六", "李四", "王五", "赵六"
            , "李四", "王五", "赵六", "李四", "王五", "赵六", "李四"
            , "王五", "赵六", "李四", "王五", "赵六", "李四", "王五", "赵六"};
    private int[] mImages = new int[]{R.mipmap.shilitu, R.mipmap.shilitu, R.mipmap.shilitu
            , R.mipmap.shilitu, R.mipmap.shilitu, R.mipmap.shilitu, R.mipmap.shilitu
            , R.mipmap.shilitu, R.mipmap.shilitu, R.mipmap.shilitu, R.mipmap.shilitu
            , R.mipmap.shilitu, R.mipmap.shilitu, R.mipmap.shilitu, R.mipmap.shilitu
            , R.mipmap.shilitu, R.mipmap.shilitu, R.mipmap.shilitu, R.mipmap.shilitu
            , R.mipmap.shilitu, R.mipmap.shilitu, R.mipmap.shilitu};
    private RecyclerView mRecyclerView;
    private ThroughTrainRecycleViewAdapter mMAdapter;
    private List<Integer> mMData;
    private TextView mSearch;
    private RecyclerView mVerticalRecycleView;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_through_train;
    }

    @Override
    protected void initView() {
        mBack = (RelativeLayout) findViewById(R.id.rl_title_back);
        mMiddleText = (TextView) findViewById(R.id.tv_middle_text);
        mXBanner = (XBanner) findViewById(R.id.XB_through_trip);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_tt_hot_spots);
        mSearch = (TextView) findViewById(R.id.tv_through_train_search);
        mVerticalRecycleView = (RecyclerView) findViewById(R.id.rv_through_train_vrv_location);
    }

    @Override
    protected void initData() {
        RvImageAndText();//data
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mMAdapter = new ThroughTrainRecycleViewAdapter(this, mMData);
        mMAdapter.setOnItemClickListener(new ThroughTrainRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtils.show(ThroughTrainActivity.this, "第" + position + "个被点击！");
            }
        });
        mRecyclerView.setAdapter(mMAdapter);
        //当地直通车的recyclerView
        //设置布局
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(this);
        verticalLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mVerticalRecycleView.setLayoutManager(verticalLayoutManager);
        //设置适配器
        ThroughTrainVerticalRecycleViewAdapter verticalAdapter = new ThroughTrainVerticalRecycleViewAdapter(this, mMData);
        mVerticalRecycleView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
        mVerticalRecycleView.setAdapter(verticalAdapter);
        mVerticalRecycleView.setNestedScrollingEnabled(false);
        //轮播图
        XBannerUtils.setBannerHolder(this, mXBanner);
        mMiddleText.setText("直通车");

    }

    private void RvImageAndText() {
        mMData = new ArrayList<>(Arrays.asList(R.mipmap.shilitu,
                R.mipmap.shilitu, R.mipmap.shilitu,
                R.mipmap.shilitu, R.mipmap.shilitu,
                R.mipmap.shilitu, R.mipmap.shilitu,
                R.mipmap.shilitu, R.mipmap.shilitu));
    }

    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);
        mSearch.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_title_back:
                this.finish();
                break;
            case R.id.tv_through_train_search:
                jumpToActivity(SearchActivity.class);
                break;

        }

    }


}
