package com.dsgj.youyuntong.activity;


import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsgj.youyuntong.JavaBean.ChoosedVisitorBean;
import com.dsgj.youyuntong.JavaBean.InternetDataBean.OrderCreateBean;
import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.SPUtils;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.Utils.view.DividerItemDecoration;
import com.dsgj.youyuntong.adapter.CommonVisitorAdapter;
import com.dsgj.youyuntong.base.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
    private String mNumber;
    private String mMName;
    private RecyclerView mVisitorShowList;

    private List<ChoosedVisitorBean> mGetVisitors;
    private double mPrice;
    private OrderCreateBean mOrderCreateBean;
    private String mStrJson;


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
        mVisitorShowList = (RecyclerView) findViewById(R.id.rl_visitor);
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
        mPrice = Double.parseDouble(sceneryPrice);
        mTotal1 = mVisitorCount * mPrice;
        mTotal.setText(mTotal1 + "元");
        mMiddleText.setText("填写资料");
        //预订人信息
        mOrderCreateBean = new OrderCreateBean(OrderInputInfoActivity.this);
        mNumber = mOrderCreateBean.getUserName();
        mMName = mOrderCreateBean.getContact_name();
        mOrderPhone.setText(mNumber);
        mOrderName.setText(mMName);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //返回的时候获得新添加的游客数据用list保存
        mGetVisitors = getVisitors();
        mTotal1 = mPrice * mGetVisitors.size();

        mTotal.setText(mTotal1 + "元");
        List<String> newVisitorName = new ArrayList<>();
        List<String> newVisitorID = new ArrayList<>();
        for (int i = 0; i < mGetVisitors.size(); i++) {
            newVisitorName.add(mGetVisitors.get(i).getContact_name());
            newVisitorID.add(mGetVisitors.get(i).getIdnumber());
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(OrderInputInfoActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mVisitorShowList.setLayoutManager(layoutManager);
        CommonVisitorAdapter mAdapter = new CommonVisitorAdapter(OrderInputInfoActivity.this
                , newVisitorName, newVisitorID, false);
        mVisitorShowList.addItemDecoration(new DividerItemDecoration(OrderInputInfoActivity.this,
                DividerItemDecoration.VERTICAL_LIST));
        mVisitorShowList.setAdapter(mAdapter);
        mVisitorShowList.setNestedScrollingEnabled(false);

    }

    private List<ChoosedVisitorBean> getVisitors() {
        List<ChoosedVisitorBean> newVisitor = new ArrayList<>();
        boolean isReturn = SPUtils.with(OrderInputInfoActivity.this)
                .contains(OrderInputInfoActivity.this, "ChooseVisitor");
        if (isReturn) {
            mStrJson = SPUtils.with(OrderInputInfoActivity.this).get("ChooseVisitor", "");
            //下边是将json数据转换为list的操作。
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<ChoosedVisitorBean>>() {
            }.getType();
            newVisitor = gson.fromJson(mStrJson, type);
            return newVisitor;

        } else {
            return newVisitor;
        }
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
                if (mGetVisitors.size() == 0) {
                    ToastUtils.show(this, "请添加游客！");
                } else { //传递相关的参数到下一个界面：
                    Intent intentToPay = new Intent(this, OrderPayActivity.class);
                    intentToPay.putExtra("totalPrice", mTotal1);//总价格
                    intentToPay.putExtra("date", mStartTime);
                    intentToPay.putExtra("number", mGetVisitors.size() + "");
                    intentToPay.putExtra("traveler", mStrJson);
                    startActivity(intentToPay);
                    SPUtils.with(OrderInputInfoActivity.this)
                            .remove("ChooseVisitor");
                    this.finish();
                }
                break;
        }

    }
}
