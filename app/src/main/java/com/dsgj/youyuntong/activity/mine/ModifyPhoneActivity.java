package com.dsgj.youyuntong.activity.mine;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.Http.RequestCallBack;
import com.dsgj.youyuntong.Utils.Http.HttpUtils;
import com.dsgj.youyuntong.Utils.MatchPhoneNum;
import com.dsgj.youyuntong.Utils.SPUtils;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.base.BaseActivity;
import com.dsgj.youyuntong.base.BaseJavaBean;

import java.util.HashMap;
import java.util.Map;

public class ModifyPhoneActivity extends BaseActivity {


    private ImageView mBack;
    private TextView mMiddleText;
    private Button mSendCode;
    private EditText mOldPhone;
    private Button mNext;
    private EditText mCode;
    private Button mSendCode1;
    private EditText mOldPhone1;
    private EditText mCode1;
    private LinearLayout mLlOld;
    private LinearLayout mLlNew;
    private Button mBtnOk;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_modify_phone;
    }

    @Override
    protected void initView() {
        mBack = (ImageView) findViewById(R.id.iv_back);
        mMiddleText = (TextView) findViewById(R.id.tv_middle_text);
        mSendCode = (Button) findViewById(R.id.btn_send_code);
        mOldPhone = (EditText) findViewById(R.id.et_input_old_phone_number);
        mNext = (Button) findViewById(R.id.btn_next);
        mCode = (EditText) findViewById(R.id.et_code);
        mLlOld = (LinearLayout) findViewById(R.id.ll_old);
        mLlNew = (LinearLayout) findViewById(R.id.ll_new);
        mSendCode1 = (Button) findViewById(R.id.btn_send_code1);
        mOldPhone1 = (EditText) findViewById(R.id.et_input_old_phone_number1);
        mCode1 = (EditText) findViewById(R.id.et_code1);
        mBtnOk = (Button) findViewById(R.id.btn_phone_ok);

    }

    @Override
    protected void initData() {
        mMiddleText.setText("修改手机号");
        mBtnOk.setText("提交");
        mLlOld.setVisibility(View.VISIBLE);
        mLlNew.setVisibility(View.GONE);

    }

    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);
        mSendCode.setOnClickListener(this);
        mNext.setOnClickListener(this);
        mSendCode1.setOnClickListener(this);
        mBtnOk.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                ModifyPhoneActivity.this.finish();
                break;
            case R.id.btn_send_code:
                String oldPhone1 = mOldPhone.getText().toString().trim();
                //非空判断
                if (TextUtils.isEmpty(oldPhone1)) {
                    ToastUtils.show(this, "手机号码不能为空！");
                } else {
                    //控制手机号码格式
                    if (MatchPhoneNum.isMobileNO(oldPhone1)) {
                        ToastUtils.show(this, "手机号码不正确！");
                    } else {
                        Map<String, String> map = new HashMap<>();
                        map.put("userName", oldPhone1);
                        map.put("token", SPUtils.with(ModifyPhoneActivity.this).get("token", ""));
                        HttpUtils.post(this, new BaseJavaBean(), HttpUtils.URL_BASE + "checkOldPhone", map, new RequestCallBack() {
                            @Override
                            public void onOutNet() {
                                ToastUtils.show(ModifyPhoneActivity.this, "网络已断开，请检查网络！");
                            }

                            @Override
                            public void onSuccess(String data) {
                                ToastUtils.show(ModifyPhoneActivity.this, "验证码发送成功！");

                            }

                            @Override
                            public void onFailure(int code) {
                                if (code == 300) {
                                    ToastUtils.show(ModifyPhoneActivity.this, "该手机号码未注册用户！");
                                }
                            }

                            @Override
                            public void onError(Exception e) {
                                ToastUtils.show(ModifyPhoneActivity.this, "验证码发送失败，请稍候再试！");

                            }
                        });
                    }

                }

                break;
            case R.id.btn_next:
                String oldPhone2 = mOldPhone.getText().toString().trim();
                String code = mCode.getText().toString().trim();
                if (TextUtils.isEmpty(code)) {
                    ToastUtils.show(ModifyPhoneActivity.this, "验证码不能为空！");
                } else {
                    Map<String, String> map = new HashMap<>();
                    map.put("userName", oldPhone2);
                    map.put("type", "phone");
                    map.put("code", code);
                    map.put("token", SPUtils.with(ModifyPhoneActivity.this).get("token", ""));
                    HttpUtils.post(this, new BaseJavaBean()
                            , HttpUtils.URL_BASE_USER + "check_code", map, new RequestCallBack() {
                                @Override
                                public void onOutNet() {
                                    ToastUtils.show(ModifyPhoneActivity.this, "网络已断开，请查看！");
                                }

                                @Override
                                public void onSuccess(String data) {
                                    mLlOld.setVisibility(View.GONE);
                                    mLlNew.setVisibility(View.VISIBLE);

                                }

                                @Override
                                public void onFailure(int code) {
                                    ToastUtils.show(ModifyPhoneActivity.this, "验证码错误！");

                                }

                                @Override
                                public void onError(Exception e) {
                                    ToastUtils.show(ModifyPhoneActivity.this, "错误！");

                                }
                            });
                }

                break;
            case R.id.btn_send_code1:
                break;
            case R.id.btn_phone_ok:
                break;
        }

    }


}
