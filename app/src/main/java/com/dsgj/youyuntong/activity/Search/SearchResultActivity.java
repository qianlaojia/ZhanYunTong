package com.dsgj.youyuntong.activity.Search;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.base.BaseActivity;

public class SearchResultActivity extends BaseActivity {


    private ImageView mBack;
    private TextView mInputKey;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_search_result;
    }

    @Override
    protected void initView() {
        mBack = (ImageView) findViewById(R.id.iv_search_result_back);
        mInputKey = (TextView) findViewById(R.id.tv_all_search_result);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);
        mInputKey.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search_result_back:
                this.finish();
                break;
            case R.id.tv_all_search_result:
                jumpToActivity(SearchActivity.class);
                break;
        }

    }
}
