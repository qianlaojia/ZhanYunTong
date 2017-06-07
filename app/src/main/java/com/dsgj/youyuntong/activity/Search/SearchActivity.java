package com.dsgj.youyuntong.activity.Search;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.base.BaseActivity;

public class SearchActivity extends BaseActivity {


    private ImageView mBack;
    private EditText mInputSearchKey;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        mBack = (ImageView) findViewById(R.id.iv_search_back);
        mInputSearchKey = (EditText) findViewById(R.id.et_all_search);
    }

    @Override
    protected void initData() {


        watchSearch();
    }

    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search_back:
                this.finish();
                break;
        }

    }

    public void watchSearch() {
        mInputSearchKey.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    ((InputMethodManager) mInputSearchKey.getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(mInputSearchKey.getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    String keyToSearch = mInputSearchKey.getText().toString().trim();
                    if (TextUtils.isEmpty(keyToSearch)) {
                        ToastUtils.show(SearchActivity.this, "请输入要搜索的关键字！");
                        mInputSearchKey.setText("");
                        mInputSearchKey.requestFocus();
                    }else{
                    Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                    intent.putExtra("searchKey", keyToSearch);
                    startActivity(intent);
                    }
                    return true;
                }
                return false;
            }
        });
    }
}
