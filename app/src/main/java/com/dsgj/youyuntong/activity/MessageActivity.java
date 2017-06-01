package com.dsgj.youyuntong.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.view.RedPotUtils;
import com.dsgj.youyuntong.base.BaseActivity;

public class MessageActivity extends BaseActivity {


    private RelativeLayout mBack;
    private ImageView mImageCheap;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_message;
    }

    @Override
    protected void initView() {
        mBack = (RelativeLayout) findViewById(R.id.rl_title_back);
        mImageCheap = (ImageView) findViewById(R.id.imageView_cheap);//优惠活动图片
        ImageView imageView = (ImageView) findViewById(R.id.imageView_s);
       // RedPotUtils.onlyShowPot(this, mImageCheap, 1,true);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_title_back:
                finish();
                break;
        }

    }
}
