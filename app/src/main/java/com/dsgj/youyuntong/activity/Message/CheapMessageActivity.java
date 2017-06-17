package com.dsgj.youyuntong.activity.Message;

import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.base.BaseActivity;

public class CheapMessageActivity extends BaseActivity {

    private RelativeLayout mBack;
    private TextView mMiddleText;
    private ListView mCheapInfor;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_cheap_message;
    }

    @Override
    protected void initView() {
        mBack = (RelativeLayout) findViewById(R.id.rl_title_back);
        mMiddleText = (TextView) findViewById(R.id.tv_middle_text);
        mCheapInfor = (ListView) findViewById(R.id.lv_cheap_message);

    }

    @Override
    protected void initData() {
        mMiddleText.setText("优惠活动");

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
