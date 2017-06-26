package com.dsgj.youyuntong.activity;


import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.SPUtils;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.activity.CommonVisitorActivity;
import com.dsgj.youyuntong.base.BaseActivity;

public class OrderInputInfoActivity extends BaseActivity {


    private RelativeLayout mBack;
    private TextView mMiddleText;
    private TextView mTotal;
    private TextView mPayAtOnce;
    private TextView mCommonVisitor;
    private TextView mGoOffTime;
    private TextView mTicketPrice;
    private TextView mStartCity;
    private TextView mSceneryName;
    private TextView mVisitor;
    private TextView mOrderName;
    private TextView mOrderPhone;
    private double mTotal1;
    private String mSceneryName1;
    private int mVisitorCount;
    private String mStartTime;


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
        //出发时间
        mGoOffTime = (TextView) findViewById(R.id.tv_start_time_details);
        //门票价格
        mTicketPrice = (TextView) findViewById(R.id.tv_price);
        mStartCity = (TextView) findViewById(R.id.tv_start_city);
        mSceneryName = (TextView) findViewById(R.id.tv_input_info_spot_name);
        mVisitor = (TextView) findViewById(R.id.tv_visitor_counter_detail);
        mOrderName = (TextView) findViewById(R.id.tv_input_info_order_person_name);
        mOrderPhone = (TextView) findViewById(R.id.tv_input_info_phone_number);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void initData() {
        Intent intent = getIntent();
        mSceneryName1 = intent.getStringExtra("sceneryName");
        String sceneryPrice = intent.getStringExtra("sceneryPrice");
        String startCity = intent.getStringExtra("startLocation");
        mStartTime = intent.getStringExtra("finalDate");
        mVisitorCount = intent.getIntExtra("visitorCount", 1);
        mVisitor.setText(mVisitorCount + "人");
        mGoOffTime.setText(mStartTime);
        mTicketPrice.setText("¥" + sceneryPrice);
        mStartCity.setText(startCity);
        mSceneryName.setText(mSceneryName1);
        double i = Double.parseDouble(sceneryPrice);
        mTotal1 = mVisitorCount * i;
        mTotal.setText(mTotal1 + "元");
        mMiddleText.setText("填写资料");
        //预订人信息
        String number = SPUtils.with(OrderInputInfoActivity.this).get("userName", "");
        String name = SPUtils.with(OrderInputInfoActivity.this).get("realName", "");
        mOrderPhone.setText(number);
        mOrderName.setText(name);

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
                Intent intent = new Intent(OrderInputInfoActivity.this, CommonVisitorActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_pay_at_once:
                //传递相关的参数到下一个界面：
                Intent intentToPay = new Intent(this, OrderPayActivity.class);
                intentToPay.putExtra("totalPrice", mTotal1);//总价格
                intentToPay.putExtra("spotName",mSceneryName1);//景区名称
                intentToPay.putExtra("visitorCount",mVisitorCount);//出行人数
                intentToPay.putExtra("startTime", mStartTime);//出行时间
                startActivity(intentToPay);
                this.finish();
                break;
        }

    }
}
