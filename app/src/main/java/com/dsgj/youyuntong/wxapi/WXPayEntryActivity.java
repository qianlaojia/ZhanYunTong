package com.dsgj.youyuntong.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.dsgj.youyuntong.Utils.ToastUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;
    private TextView tv_text_result;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.pay_result);
//        tv_text_result = (TextView) findViewById(R.id.tv_text_result);

        api = WXAPIFactory.createWXAPI(this, "wxf06c7839161ddd68");
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
        Log.i("onReq", "onReq: " + req.openId);
    }

    @Override
    public void onResp(BaseResp resp) {
        Log.i("onResp", "onResp: " + resp.errStr);
        Log.i("onResp", "onResp: " + resp.errCode);
        if (resp.errCode == ConstantsAPI.COMMAND_UNKNOWN) {
            ToastUtils.show(this,"支付成功");
            finish();

        } else {
            //支付失败
            ToastUtils.show(this,"支付失败");
            finish();
        }
    }
}