package com.dsgj.youyuntong.activity.ThroughTrain;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.base.BaseActivity;

public class ThroughTrainDetailActivity extends BaseActivity {


    private TextView mService;
    private TextView mShare;
    private TextView mCollect;
    private TextView mDestine;
    private RelativeLayout mBack;
    private TextView mMiddleText;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_through_train_detail;
    }

    @Override
    protected void initView() {
        mService = (TextView) findViewById(R.id.tv_custom_service);
        mShare = (TextView) findViewById(R.id.tv_tt_detail_share);
        mCollect = (TextView) findViewById(R.id.tv_tt_detail_collect);
        mDestine = (TextView) findViewById(R.id.tv_tt_detail_destine);
        mBack = (RelativeLayout) findViewById(R.id.rl_title_back);
        mMiddleText = (TextView) findViewById(R.id.tv_middle_text);


    }

    @Override
    protected void initData() {
        mMiddleText.setText("直通车详情");

    }

    @Override
    protected void initListener() {
        mService.setOnClickListener(this);
        mShare.setOnClickListener(this);
        mCollect.setOnClickListener(this);
        mDestine.setOnClickListener(this);
        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_title_back:
                this.finish();
                break;
            case R.id.tv_custom_service:
                ToastUtils.show(this, "客服");
                break;
            case R.id.tv_tt_detail_share:
                ToastUtils.show(this, "分享");
                break;
            case R.id.tv_tt_detail_collect:
                ToastUtils.show(this, "收藏");
                break;
            case R.id.tv_tt_detail_destine:
                //todo 携带相关的参数    旅游名称 出发时间    预订人信息    出发地点等等；
                jumpToActivity(ThroughTrainInputInfoActivity.class);
                break;

        }

    }
}
