package com.dsgj.youyuntong.activity.mine;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.base.BaseActivity;

public class CommonInformationActivity extends BaseActivity {


    private TextView mMiddleText;
    private ImageView mBack;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_common_information;

    }

    @Override
    protected void initView() {
        mMiddleText = (TextView) findViewById(R.id.tv_middle_text);
        mBack = (ImageView) findViewById(R.id.iv_back);

    }

    @Override
    protected void initData() {
        mMiddleText.setText("常用信息");

    }

    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                this.finish();
                break;

        }

    }
}
