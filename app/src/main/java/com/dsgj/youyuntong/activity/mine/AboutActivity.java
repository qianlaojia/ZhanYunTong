package com.dsgj.youyuntong.activity.mine;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

public class AboutActivity extends BaseActivity {


    private ImageView mBack;
    private TextView mTitleText;
    private RelativeLayout mRlIntroduce;
    private RelativeLayout mFeedback;
    private RelativeLayout mCheckForUpdate;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_about;
    }

    @Override
    protected void initView() {
        mBack = (ImageView) findViewById(R.id.iv_back);
        mTitleText = (TextView) findViewById(R.id.tv_middle_text);
        mRlIntroduce = (RelativeLayout) findViewById(R.id.rl_app_introduce);
        mFeedback = (RelativeLayout) findViewById(R.id.rl_feedback);
        mCheckForUpdate = (RelativeLayout) findViewById(R.id.rl_check_for_update);
    }

    @Override
    protected void initData() {
        mTitleText.setText("关于游运通");


    }

    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);
        mCheckForUpdate.setOnClickListener(this);
        mFeedback.setOnClickListener(this);
        mRlIntroduce.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                this.finish();
                break;
            case R.id.rl_app_introduce:
                Map<String, String> map = new HashMap<>();
                map.put("type", "introduce");
                jumpToActivity(AboutDetailActivity.class, map);


                break;
            case R.id.rl_feedback:
                Map<String, String> map1 = new HashMap<>();
                map1.put("type", "feedback");
                jumpToActivity(AboutDetailActivity.class, map1);

                break;
            case R.id.rl_check_for_update:
                //检查版本更新

                break;
        }

    }
}
