package com.dsgj.youyuntong.activity.ThroughTrain;


import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.activity.CommonVisitorActivity;
import com.dsgj.youyuntong.base.BaseActivity;

public class ThroughTrainInputInfoActivity extends BaseActivity {


    private RelativeLayout mBack;
    private TextView mMiddleText;
    private TextView mTotal;
    private TextView mPayAtOnce;
    private TextView mCommonVisitor;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_through_train_input_info;
    }

    @Override
    protected void initView() {
        mBack = (RelativeLayout) findViewById(R.id.rl_title_back);
        mMiddleText = (TextView) findViewById(R.id.tv_middle_text);
        mTotal = (TextView) findViewById(R.id.tv_input_info_total_price);
        mPayAtOnce = (TextView) findViewById(R.id.tv_pay_at_once);
        mCommonVisitor = (TextView) findViewById(R.id.tv_to_common_visitor);

    }

    @Override
    protected void initData() {
        mMiddleText.setText("填写资料");

    }

    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);
        mPayAtOnce.setOnClickListener(this);
        mCommonVisitor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_title_back:
                this.finish();
                break;
            case R.id.tv_to_common_visitor:
                jumpToActivity(CommonVisitorActivity.class);
                break;
            case R.id.tv_pay_at_once:
                ToastUtils.show(this, "跳转到支付页面！");
                break;
        }

    }
}
