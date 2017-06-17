package com.dsgj.youyuntong.activity.Search;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.SPUtils;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.Utils.log.LogUtils;
import com.dsgj.youyuntong.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity {
    private ImageView mBack;
    private EditText mInputSearchKey;
    private List<String> mHistoryKeys;
    private String mKeyToSearch;

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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onStart() {
        super.onStart();
        watchSearch();
        new Thread() {
            @Override
            public void run() {
                super.run();
                mHistoryKeys = new ArrayList<>();
                mHistoryKeys.add(mKeyToSearch);
                saveArray(mHistoryKeys);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.show(SearchActivity.this, "12345679876543" + loadArray().size() + "12324354675432");
                    }
                });

            }
        };


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
                    mKeyToSearch = mInputSearchKey.getText().toString().trim();
                    if (TextUtils.isEmpty(mKeyToSearch)) {
                        ToastUtils.show(SearchActivity.this, "请输入要搜索的关键字！");
                        mInputSearchKey.setText("");
                        mInputSearchKey.requestFocus();
                    } else {
                        Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                        intent.putExtra("searchKey", mKeyToSearch);
                        startActivity(intent);
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public void saveArray(List<String> list) {
        SPUtils.with(this).save("historyListSize", mHistoryKeys.size());
        for (int i = 0; i < mHistoryKeys.size(); i++) {
            SPUtils.with(this).save("historyList_" + i, list.get(1));
        }
    }

    public List<String> loadArray() {
        List<String> getHistory = new ArrayList<>();
        int historySize = SPUtils.with(this).get("historyListSize", 0);
        for (int i = 0; i < historySize; i++) {
            getHistory.add(SPUtils.with(this).get("historyList_" + i, ""));
        }
        return getHistory;
    }
}

