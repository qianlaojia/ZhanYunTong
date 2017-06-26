package com.dsgj.youyuntong.activity.Search;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
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
import com.dsgj.youyuntong.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity {
    private ImageView mBack;
    private EditText mInputSearchKey;

    private String mKeyToSearch;
    private RecyclerView mHistory;
    private List<String> mHistoryStrings;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        mBack = (ImageView) findViewById(R.id.iv_search_back);
        mInputSearchKey = (EditText) findViewById(R.id.et_all_search);
        mHistory = (RecyclerView) findViewById(R.id.rv_search_history);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String hotCity = intent.getStringExtra("hotCity");
        mInputSearchKey.setText(hotCity);
        mHistoryStrings = new ArrayList<>();


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
                        mHistoryStrings.add(mKeyToSearch);
                        SearchActivity.this.finish();
                        startActivity(intent);
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public void saveArray(List<String> list) {
        SPUtils.with(this).save("historyListSize", mHistoryStrings.size());
        for (int i = 0; i < mHistoryStrings.size(); i++) {
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

