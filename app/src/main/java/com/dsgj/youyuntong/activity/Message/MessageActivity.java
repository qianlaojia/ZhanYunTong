package com.dsgj.youyuntong.activity.Message;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.base.BaseActivity;

public class MessageActivity extends BaseActivity {


    private RelativeLayout mBack;
    private LinearLayout mServiceMessage;
    private LinearLayout mOrderMessage;
    private LinearLayout mCheapMessage;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_message;
    }

    @Override
    protected void initView() {
        mBack = (RelativeLayout) findViewById(R.id.rl_title_back);
        //ll布局点击跳转
        mServiceMessage = (LinearLayout) findViewById(R.id.ll_message_service_message);
        mOrderMessage = (LinearLayout) findViewById(R.id.ll_message_order_message);
        mCheapMessage = (LinearLayout) findViewById(R.id.ll_message_cheap_message);

        // RedPotUtils.onlyShowPot(this, mImageCheap, 1,true);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);
        mCheapMessage.setOnClickListener(this);
        mOrderMessage.setOnClickListener(this);
        mServiceMessage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_title_back:
                finish();
                break;
            case R.id.ll_message_cheap_message:
                jumpToActivity(CheapMessageActivity.class);
                break;
            case R.id.ll_message_order_message:
                jumpToActivity(OrderMessageActivity.class);
                break;
            case R.id.ll_message_service_message:
                ToastUtils.show(MessageActivity.this, "处理客服消息！");
                break;
        }

    }
}
