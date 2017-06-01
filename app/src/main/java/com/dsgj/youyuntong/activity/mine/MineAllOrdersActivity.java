package com.dsgj.youyuntong.activity.mine;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.base.BaseActivity;

public class MineAllOrdersActivity extends BaseActivity {


    private RelativeLayout mBack;
    private TextView mMiddleText;
    private String mStyle;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_mine_all_orders;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        mStyle = intent.getStringExtra("style");
        mBack = (RelativeLayout) findViewById(R.id.rl_title_back);
        mMiddleText = (TextView) findViewById(R.id.tv_middle_text);

    }

    @Override
    protected void initData() {
        mMiddleText.setText(mStyle);
    }

    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mStyle.equals("全部订单")) {
            ToastUtils.show(this, "全部订单的啊");

        }else  if (mStyle.equals("待支付")) {
            ToastUtils.show(this, "待支付");

        }else  if (mStyle.equals("待出行")) {
            ToastUtils.show(this, "待出行");

        }else  if (mStyle.equals("待点评")) {
            ToastUtils.show(this, "待点评");

        }



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
