package com.dsgj.youyuntong.activity.ThroughTrain;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.Utils.view.DividerItemDecoration;
import com.dsgj.youyuntong.adapter.ThroughTrain.ThroughTrainQueryResultRVAdapter;
import com.dsgj.youyuntong.base.BaseActivity;

public class ThroughTrainInquiryActivity extends BaseActivity {


    private RelativeLayout mBack;
    private TextView mMiddleText;
    private RecyclerView mQueryResult;
    private TextView mFrontDay;
    private TextView mNextDay;
    private TextView mMiddleTime;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_through_train_inquiry;
    }

    @Override
    protected void initView() {
        mBack = (RelativeLayout) findViewById(R.id.rl_title_back);
        mMiddleText = (TextView) findViewById(R.id.tv_middle_text);
        mQueryResult = (RecyclerView) findViewById(R.id.rv_through_train_query_result);
        mFrontDay = (TextView) findViewById(R.id.tv_front_day);
        mNextDay = (TextView) findViewById(R.id.tv_next_day);
        mMiddleTime = (TextView) findViewById(R.id.tv_middle_time);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String startLocation = intent.getStringExtra("startLocation");
        String endLocation = intent.getStringExtra("endLocation");
        mMiddleText.setText(startLocation + "-->" + endLocation);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mQueryResult.setLayoutManager(linearLayoutManager);
        ThroughTrainQueryResultRVAdapter throughTrainInquiry = new ThroughTrainQueryResultRVAdapter(this);
        throughTrainInquiry.setOnItemClickListener(new ThroughTrainQueryResultRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ThroughTrainInquiryActivity.this.finish();
                //跳转到详情页
                Intent intent = new Intent(ThroughTrainInquiryActivity.this, ThroughTrainDetailActivity.class);
                ThroughTrainInquiryActivity.this.startActivity(intent);
            }
        });
        mQueryResult.setAdapter(throughTrainInquiry);
        mQueryResult.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));

    }

    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);
        mFrontDay.setOnClickListener(this);
        mNextDay.setOnClickListener(this);
        mMiddleTime.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_title_back:
                this.finish();
                break;
            case R.id.tv_front_day:
                ToastUtils.show(this, "获得前一天的数据");
                break;

            case R.id.tv_next_day:
                ToastUtils.show(this, "获得后一天的数据");
                break;
            case R.id.tv_middle_time:
                ToastUtils.show(this, "弹出时间选择的对话框");
                break;
        }
    }
}
