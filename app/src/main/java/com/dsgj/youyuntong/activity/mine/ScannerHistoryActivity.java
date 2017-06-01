package com.dsgj.youyuntong.activity.mine;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.base.BaseActivity;

public class ScannerHistoryActivity extends BaseActivity {


    private ImageView mBack;
    private TextView mMiddleText;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_scanner_history;
    }

    @Override
    protected void initView() {
        mBack = (ImageView) findViewById(R.id.iv_back);
        mMiddleText = (TextView) findViewById(R.id.tv_middle_text);

    }

    @Override
    protected void initData() {
        mMiddleText.setText("浏览历史");

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
