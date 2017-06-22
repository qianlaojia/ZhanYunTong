package com.dsgj.youyuntong.activity;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.base.BaseActivity;

public class OrderPayActivity extends BaseActivity {

    private RelativeLayout mBack;
    private TextView mMiddleText;
    private TextView mTotalPrice;
    private TextView mOrderNumber;
    private TextView mSecneryName;
    private TextView mVisitorName;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_order_pay;
    }

    @Override
    protected void initView() {
        mBack = (RelativeLayout) findViewById(R.id.rl_title_back);
        mMiddleText = (TextView) findViewById(R.id.tv_middle_text);
        //总价格
        mTotalPrice = (TextView) findViewById(R.id.tv_pay_money_total);
        //订单编号
        mOrderNumber = (TextView) findViewById(R.id.tv_order_number_pay);
        //景区名称
        mSecneryName = (TextView) findViewById(R.id.tv_pay_order_name);
        //出行人数
        mVisitorName = (TextView) findViewById(R.id.tv_visitor_number_detail);
        //出发时间
        mVisitorName = (TextView) findViewById(R.id.tv_start_time_detail_pay);
    }

    @Override
    protected void initData() {
        mMiddleText.setText("订单支付");
    }

    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_title_back:
                this.finish();
                break;
        }

    }
}
