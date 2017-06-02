package com.dsgj.youyuntong.activity.Message;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.base.BaseActivity;

public class OrderMessageActivity extends BaseActivity {


    private RelativeLayout mBack;
    private TextView mMiddleText;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_order_message;
    }

    @Override
    protected void initView() {
        mBack = (RelativeLayout) findViewById(R.id.rl_title_back);
        mMiddleText = (TextView) findViewById(R.id.tv_middle_text);
    }

    @Override
    protected void initData() {
        mMiddleText.setText("订单服务");

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
