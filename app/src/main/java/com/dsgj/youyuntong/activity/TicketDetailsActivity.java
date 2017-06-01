package com.dsgj.youyuntong.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.base.BaseActivity;


public class TicketDetailsActivity extends BaseActivity {

    private TextView mTitle;
    private ImageView mPotdetails;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_ticket_details;
    }

    @Override
    protected void initView() {
        mTitle = (TextView) findViewById(R.id.tv_middle_text);
        mPotdetails = (ImageView) findViewById(R.id.tv_jump_to_details_page);

    }

    @Override
    protected void initData() {
        mTitle.setText("门票详情");
    }

    @Override
    protected void initListener() {
        mPotdetails.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.tv_jump_to_details_page:
                ToastUtils.show(this,"跳转门票详情页！！！");
                break;
        }

    }
}
