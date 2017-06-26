package com.dsgj.youyuntong.activity;

import android.annotation.SuppressLint;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.DesUtils;
import com.dsgj.youyuntong.Utils.SPUtils;
import com.dsgj.youyuntong.base.BaseActivity;

public class BusTicketActivity extends BaseActivity {


    private RelativeLayout mBack;
    private TextView mMiddleText;
    private WebView mWebView;
    private String mUser;
    private ProgressBar mPb;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_bus_ticket;
    }

    @Override
    protected void initView() {
        mBack = (RelativeLayout) findViewById(R.id.rl_title_back);
        mMiddleText = (TextView) findViewById(R.id.tv_middle_text);
        mWebView = (WebView) findViewById(R.id.wv_bus_ticket);
        mPb = (ProgressBar) findViewById(R.id.pb_bus_ticket);
    }

    @Override
    protected void initData() {
        mMiddleText.setText("汽车票");
        getInfo();
    }

    private void getInfo() {
        boolean isLogin = SPUtils.with(this).get("IsLogoIn", false);
        String m = "ds123!@#";
        String phoneNumber = SPUtils.with(this).get("userName", "");
        if (isLogin) {
            try {
                mUser = DesUtils.EncryptAsDoNet(phoneNumber, m).trim();
                loadWebView();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            jumpToActivity(LogOnAndRegisterActivity.class);
            getInfo();
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void loadWebView() {
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        mWebView.loadUrl("http://m.hn96520.com/Home/Index?f=dingshang&m=" + mUser);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        //设置WebChromeClient类
        mWebView.setWebChromeClient(new WebChromeClient() {
            //获取网站标题
            @Override
            public void onReceivedTitle(WebView view, String title) {

            }

            //获取加载进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    // 网页加载完成
                    mPb.setVisibility(View.GONE);
                } else {
                    // 加载中
                    mPb.setProgress(newProgress);
                }

            }
        });

    }

    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_title_back:
                this.finish();
                break;

        }

    }
}
