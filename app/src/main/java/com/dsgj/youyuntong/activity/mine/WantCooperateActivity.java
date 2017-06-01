package com.dsgj.youyuntong.activity.mine;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.base.BaseActivity;

public class WantCooperateActivity extends BaseActivity {


    private RelativeLayout mBack;
    private TextView mMiddleText;
    private LinearLayout mApplier;
    private LinearLayout mDistributor;
    private LinearLayout mSpots;
    private LinearLayout mAdvertise;
    private LinearLayout mThoughTrain;
    private RelativeLayout mRlApplier;
    private RelativeLayout mRlDistributor;
    private RelativeLayout mRlSpots;
    private RelativeLayout mRlAdvertise;
    private RelativeLayout mRlThoughTrain;
    private ImageView mIvApplier;
    private ImageView mIvDistributor;
    private ImageView mIvAdvertise;
    private ImageView mIvSpot;
    private ImageView mIvThrough;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_want_cooperate;
    }

    @Override
    protected void initView() {
        mBack = (RelativeLayout) findViewById(R.id.rl_title_back);
        mMiddleText = (TextView) findViewById(R.id.tv_middle_text);
        mApplier = (LinearLayout) findViewById(R.id.ll_applier);
        mDistributor = (LinearLayout) findViewById(R.id.ll_distributor);
        mSpots = (LinearLayout) findViewById(R.id.ll_spot);
        mAdvertise = (LinearLayout) findViewById(R.id.ll_advertise);
        mThoughTrain = (LinearLayout) findViewById(R.id.ll__through_train);
        mRlApplier = (RelativeLayout) findViewById(R.id.rl_applier);
        mRlDistributor = (RelativeLayout) findViewById(R.id.rl_distributor);
        mRlSpots = (RelativeLayout) findViewById(R.id.rl_spot);
        mRlAdvertise = (RelativeLayout) findViewById(R.id.rl_advertise);
        mRlThoughTrain = (RelativeLayout) findViewById(R.id.rl_through_train);
        mIvApplier = (ImageView) findViewById(R.id.iv_applier_image);
        mIvDistributor = (ImageView) findViewById(R.id.iv_distributor);
        mIvAdvertise = (ImageView) findViewById(R.id.iv_advertise);
        mIvSpot = (ImageView) findViewById(R.id.iv_spot);
        mIvThrough = (ImageView) findViewById(R.id.iv_through_train);
    }

    @Override
    protected void initData() {
        mMiddleText.setText("我要合作");

    }

    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);
        mRlApplier.setOnClickListener(this);
        mRlDistributor.setOnClickListener(this);
        mRlSpots.setOnClickListener(this);
        mRlAdvertise.setOnClickListener(this);
        mRlThoughTrain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_title_back:
                finish();
                break;
            case R.id.rl_applier:
                setPullDown(mIvApplier, mApplier);
                break;
            case R.id.rl_distributor:
                setPullDown(mIvDistributor, mDistributor);
                break;
            case R.id.rl_advertise:
                setPullDown(mIvAdvertise, mAdvertise);
                break;
            case R.id.rl_spot:
                setPullDown(mIvSpot, mSpots);
                break;
            case R.id.rl_through_train:
                setPullDown(mIvThrough, mThoughTrain);
                break;
        }
    }

    private boolean isPull = false;
    /**
     * 联系详情的相关的操作
     * @param imageView    箭头
     * @param linearLayout    具体的提示内容
     */
    private void setPullDown(ImageView imageView, LinearLayout linearLayout) {
        if (!isPull) {
            imageView.setImageResource(R.mipmap.woyaohezuo_shangla);
            linearLayout.setVisibility(View.VISIBLE);
            isPull = true;
        } else {
            imageView.setImageResource(R.mipmap.woyaohezuo_xiala);
            linearLayout.setVisibility(View.GONE);
            isPull = false;

        }
    }
}
