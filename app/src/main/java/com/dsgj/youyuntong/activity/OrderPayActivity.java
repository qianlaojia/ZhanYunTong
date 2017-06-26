package com.dsgj.youyuntong.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.Http.HttpUtils;
import com.dsgj.youyuntong.Utils.Http.RequestCallBack;
import com.dsgj.youyuntong.Utils.Pay.PayResult;
import com.dsgj.youyuntong.Utils.SPUtils;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.base.BaseActivity;
import com.dsgj.youyuntong.base.BaseJavaBean;

import java.util.HashMap;
import java.util.Map;

public class OrderPayActivity extends BaseActivity {

    private RelativeLayout mBack;
    private TextView mMiddleText;
    private TextView mTotalPrice;
    private TextView mOrderNumber;
    private TextView mSceneryName;
    private TextView mVisitorNumber;
    private TextView startTime;
    private TextView mAll_money;
    private TextView mPay;
    private static final int SDK_PAY_FLAG = 1;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_order_pay;
    }

    @Override
    protected void initView() {
        mBack = (RelativeLayout) findViewById(R.id.rl_title_back);
        mMiddleText = (TextView) findViewById(R.id.tv_middle_text);
        //总价格
        mTotalPrice = (TextView) findViewById(R.id.tv_pay_money_total);
        //订单编号
        mOrderNumber = (TextView) findViewById(R.id.tv_order_number_pay);
        //景区名称
        mSceneryName = (TextView) findViewById(R.id.tv_pay_order_name);
        //出行人数
        mVisitorNumber = (TextView) findViewById(R.id.tv_visitor_number_detail);
        //出发时间
        startTime = (TextView) findViewById(R.id.tv_start_time_detail_pay);
        mAll_money = (TextView) findViewById(R.id.tv_total_money);
        mPay = (TextView) findViewById(R.id.tv_pay);
    }

    @Override
    protected void initData() {
        mMiddleText.setText("订单支付");
        Intent intent = getIntent();
        double totalMoney = intent.getDoubleExtra("totalPrice", 0.00);//总金额
        String sceneryName = intent.getStringExtra("spotName"); //景区名称
        int visitorCount = intent.getIntExtra("visitorCount", 0);//出行人数
        String time = intent.getStringExtra("startTime");//出行时间
        mTotalPrice.setText(totalMoney + "元");
        mAll_money.setText(totalMoney + "元");
        mSceneryName.setText(sceneryName);
        mVisitorNumber.setText(visitorCount + "人");
        startTime.setText(time);

    }

    @Override
    protected void initListener() {
        mPay.setOnClickListener(this);
        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_title_back:
                this.finish();
                break;
            case R.id.tv_pay:
                ToastUtils.show(OrderPayActivity.this, "支付宝付账！");
                String userName = SPUtils.with(OrderPayActivity.this).get("userName", "");
                String token = SPUtils.with(OrderPayActivity.this).get("token", "");
                Map<String, String> map = new HashMap<>();
                map.put("type", "phone");
                map.put("access_token", "");
                map.put("userName", userName);
                map.put("token", token);
                map.put("order_id", "phone");
                HttpUtils.post(OrderPayActivity.this,new BaseJavaBean(),HttpUtils.URL_BASE_PAY+"app_pay",map, new RequestCallBack() {
                    @Override
                    public void onOutNet() {

                    }

                    @Override
                    public void onSuccess(final String data) {
                        Runnable payRunnable = new Runnable() {
                            @Override
                            public void run() {
                                PayTask alipay = new PayTask(OrderPayActivity.this);
                                Map<String, String> result = alipay.payV2(data, true);
                                Log.i("msp", result.toString());
                                Message msg = new Message();
                                msg.what = SDK_PAY_FLAG;
                                msg.obj = result;
                                mHandler.sendMessage(msg);
                            }
                        };

                        Thread payThread = new Thread(payRunnable);
                        payThread.start();
                    }

                    @Override
                    public void onFailure(int code) {

                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });



                break;
        }

    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        ToastUtils.show(OrderPayActivity.this, "支付成功！");
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            ToastUtils.show(OrderPayActivity.this, "支付结果确认中！");

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            ToastUtils.show(OrderPayActivity.this, "支付失败！");

                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }

    };
}
