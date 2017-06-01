package com.dsgj.youyuntong.activity.mine;

import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.platform.comapi.map.B;
import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.base.BaseActivity;

public class AboutDetailActivity extends BaseActivity {


    private ImageView mBack;
    private TextView mMiddleText;
    private RelativeLayout mRlFeedback;
    private Button mBtnSubmit;
    private EditText mEtFeedBack;
    private WebView mWvIntroduce;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_about_detail;
    }

    @Override
    protected void initView() {
        mBack = (ImageView) findViewById(R.id.iv_back);
        mMiddleText = (TextView) findViewById(R.id.tv_middle_text);
        mRlFeedback = (RelativeLayout) findViewById(R.id.rl_feedback);
        mBtnSubmit = (Button) findViewById(R.id.btn_feed_back_detail);
        mEtFeedBack = (EditText) findViewById(R.id.et_feedBackDetail);
        mWvIntroduce = (WebView) findViewById(R.id.wv_introduce_detail);

    }

    @Override
    protected void initData() {
        String type = getIntent().getStringExtra("type");
        if (type.equals("introduce")) {
            mMiddleText.setText("游运通介绍");
            mRlFeedback.setVisibility(View.GONE);
            mBtnSubmit.setVisibility(View.GONE);
            mWvIntroduce.loadUrl("http://www.baidu.com");
        } else if (type.equals("feedback")) {
            mMiddleText.setText("意见反馈");
            mWvIntroduce.setVisibility(View.GONE);

        }

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
