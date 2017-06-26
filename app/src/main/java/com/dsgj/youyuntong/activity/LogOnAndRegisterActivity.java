package com.dsgj.youyuntong.activity;

import android.content.Intent;
import android.graphics.Color;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsgj.youyuntong.JavaBean.ForgetPwdBean;
import com.dsgj.youyuntong.JavaBean.ForgetPwdSentCodeBean;
import com.dsgj.youyuntong.JavaBean.LogInBean;
import com.dsgj.youyuntong.JavaBean.PhoneVerificationBean;
import com.dsgj.youyuntong.JavaBean.RegisterSuccessBean;
import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.Http.HttpUtils;
import com.dsgj.youyuntong.Utils.Http.RequestCallBack;
import com.dsgj.youyuntong.Utils.MatchPhoneNum;
import com.dsgj.youyuntong.Utils.Md5Utils;
import com.dsgj.youyuntong.Utils.PassWordMarchUtils;
import com.dsgj.youyuntong.Utils.SPUtils;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.Utils.log.LogUtils;
import com.dsgj.youyuntong.base.BaseActivity;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

public class LogOnAndRegisterActivity extends BaseActivity {

    private TextView mForgetCode;
    private ImageView mWeiXinLogon;
    private Button mNewUser;
    private EditText mInputNumber;
    private EditText mMInputPwd;
    private Button mLogon;
    private boolean isLogSuccess;
    private TextView mMiddleText;
    private LinearLayout mLoginPart;
    private LinearLayout mRegisterPart;
    private LinearLayout mForgetCodePart;
    private EditText mRegisterPhone;
    private TextView mRegisterSendMessage;
    private EditText mIdentifyCode;
    private EditText mPwd;
    private EditText mPwdTwice;
    private Button mRegister;
    private EditText mFrogetNumber;
    private EditText mForgetCodeInsert;
    private EditText mNewPassWord;
    private EditText mNewPassWord2;
    private TextView mSendCode;
    private RelativeLayout mBack;
    private Button mForgetOK;
    private TextView mForgetPwdtoLog;
    private TextView mRegistertoLog;
    private TextView mMRegisterToLog;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_log_on;
    }

    @Override
    protected void initView() {
        mForgetCode = (TextView) findViewById(R.id.tv_log_on_forget_code);
        mWeiXinLogon = (ImageView) findViewById(R.id.weiXin_log_on);
        mNewUser = (Button) findViewById(R.id.btn_new_user_register);
        mInputNumber = (EditText) findViewById(R.id.logon_phone);
        mMInputPwd = (EditText) findViewById(R.id.logon_key_code);
        mLogon = (Button) findViewById(R.id.btn_logon);
        mMiddleText = (TextView) findViewById(R.id.tv_middle_text);
        mLoginPart = (LinearLayout) findViewById(R.id.ll_login_part);
        mRegisterPart = (LinearLayout) findViewById(R.id.ll_register_part);
        mForgetCodePart = (LinearLayout) findViewById(R.id.ll_forget_code_part);
        mRegisterPhone = (EditText) findViewById(R.id.et_register_tel_num);
        mRegisterSendMessage = (TextView) findViewById(R.id.tv_send_message);
        mIdentifyCode = (EditText) findViewById(R.id.et_register_code);
        mPwd = (EditText) findViewById(R.id.et_key_code);
        mPwdTwice = (EditText) findViewById(R.id.et_key_twice_code);
        mRegister = (Button) findViewById(R.id.btn_register);
        mFrogetNumber = (EditText) findViewById(R.id.et_forget_num);
        mForgetCodeInsert = (EditText) findViewById(R.id.et_get_code);
        mNewPassWord = (EditText) findViewById(R.id.et_new_pwd);
        mNewPassWord2 = (EditText) findViewById(R.id.et_new_pwd_two);
        mSendCode = (TextView) findViewById(R.id.tv_find_psw);
        mBack = (RelativeLayout) findViewById(R.id.rl_title_back);
        mForgetOK = (Button) findViewById(R.id.button_ok);
        mMRegisterToLog = (TextView) findViewById(R.id.tv_register_toLogin);
        mForgetPwdtoLog = (TextView) findViewById(R.id.tv_forget_pwd);
    }

    @Override
    protected void initData() {
        mLoginPart.setVisibility(View.VISIBLE);
        mRegisterPart.setVisibility(View.GONE);
        mForgetCodePart.setVisibility(View.GONE);
        mMiddleText.setText("登录");
    }

    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);
        mForgetCode.setOnClickListener(this);
        mWeiXinLogon.setOnClickListener(this);
        mNewUser.setOnClickListener(this);
        mInputNumber.setOnClickListener(this);
        mMInputPwd.setOnClickListener(this);
        mLogon.setOnClickListener(this);
        mRegisterSendMessage.setOnClickListener(this);
        mRegister.setOnClickListener(this);
        mSendCode.setOnClickListener(this);
        mForgetOK.setOnClickListener(this);
        mMRegisterToLog.setOnClickListener(this);
        mForgetPwdtoLog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.weiXin_log_on:
                jumpToActivity(ThirdLogonActivity.class);
                break;
            case R.id.logon_phone:
                mInputNumber.setCursorVisible(true);
                break;
            case R.id.logon_key_code:
                mMInputPwd.setCursorVisible(true);
                break;
            case R.id.btn_logon:
                logonOperation();
                break;
            case R.id.btn_new_user_register:
                mMiddleText.setText("注册");
                mLoginPart.setVisibility(View.GONE);
                mRegisterPart.setVisibility(View.VISIBLE);

                break;
            case R.id.tv_send_message:
                registerOperation operation = new registerOperation();
                operation.sendMessage();//发送验证码之前约束手机号码
                break;
            case R.id.rl_title_back:
                finish();
                break;
            case R.id.btn_register:
                registerOperation operationAll = new registerOperation();
                operationAll.sendAllMessage();

                break;
            case R.id.tv_log_on_forget_code://忘记密码跳转
                mMiddleText.setText("忘记密码");
                mLoginPart.setVisibility(View.GONE);
                mForgetCodePart.setVisibility(View.VISIBLE);
                break;

            case R.id.tv_find_psw://验证手机号码存在性
                forgetCodeOperation mForgetOperation = new forgetCodeOperation();
                mForgetOperation.sendCodeMessage();
                break;
            case R.id.button_ok://更新密码
                forgetCodeOperation mForgetOperation1 = new forgetCodeOperation();
                mForgetOperation1.sendAllCodeMessage();
                break;
            case R.id.tv_forget_pwd:
                mForgetCodePart.setVisibility(View.GONE);
                mLoginPart.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_register_toLogin://更新密码
                mRegisterPart.setVisibility(View.GONE);
                mLoginPart.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    //处理忘记密码逻辑
    private class forgetCodeOperation {
        private void sendCodeMessage() {
            String phoneNumber = mFrogetNumber.getText().toString().trim();
            Map<String, String> phoneMap = new HashMap<>();
            phoneMap.put("userName", phoneNumber);
            if (TextUtils.isEmpty(phoneNumber)) {
                ToastUtils.show(LogOnAndRegisterActivity.this, "手机号码不能为空，请输入手机号码!");
            } else if (MatchPhoneNum.isMobileNO(phoneNumber)) {
                ToastUtils.show(LogOnAndRegisterActivity.this, "请输入正确的手机号码！");
            } else {
                HttpUtils.post(LogOnAndRegisterActivity.this
                        , new ForgetPwdSentCodeBean()
                        , HttpUtils.URL_BASE + "checkUser"
                        , phoneMap
                        , new RequestCallBack() {
                            @Override
                            public void onOutNet() {
                                ToastUtils.show(LogOnAndRegisterActivity.this, "网络断开！");

                            }

                            @Override
                            public void onSuccess(String data) {
                                ToastUtils.show(LogOnAndRegisterActivity.this, "验证码发送成功！");
                            }

                            @Override
                            public void onFailure(int code) {
                                if (code == 300) {
                                    ToastUtils.show(LogOnAndRegisterActivity.this, "该手机号码未注册");
                                }

                            }

                            @Override
                            public void onError(Exception e) {

                            }
                        });
            }

        }

        private void sendAllCodeMessage() {

            String phoneNum = mFrogetNumber.getText().toString().trim();//手机号
            String code = mForgetCodeInsert.getText().toString().trim();
            String pwd1 = mNewPassWord.getText().toString().trim();
            String pwd2 = mNewPassWord2.getText().toString().trim();
            if (TextUtils.isEmpty(phoneNum)) {
                ToastUtils.show(LogOnAndRegisterActivity.this, "手机号码不能为空！");
            } else if (TextUtils.isEmpty(code)) {
                ToastUtils.show(LogOnAndRegisterActivity.this, "验证码不能为空！");
            } else if (TextUtils.isEmpty(pwd1)) {
                ToastUtils.show(LogOnAndRegisterActivity.this, "密码不能为空！");
            } else if (TextUtils.isEmpty(pwd2)) {
                ToastUtils.show(LogOnAndRegisterActivity.this, "密码不能为空！");
            } else if (MatchPhoneNum.isMobileNO(phoneNum)) {
                ToastUtils.show(LogOnAndRegisterActivity.this, "请输入正确的手机号！");
            } else if (!pwd1.equals(pwd2)) {
                ToastUtils.show(LogOnAndRegisterActivity.this, "两次输入的密码不一致，请查看！");
            } else {
                String pwd = Md5Utils.stringToMD5(pwd1);
                Map<String, String> messages = new HashMap<>();
                messages.put("userName", phoneNum);
                messages.put("code", code);
                messages.put("passWord", pwd);
                HttpUtils.post(LogOnAndRegisterActivity.this
                        , new ForgetPwdBean()
                        , HttpUtils.URL_BASE + "forgotPwd"
                        , messages, new RequestCallBack() {
                            @Override
                            public void onOutNet() {
                                ToastUtils.show(LogOnAndRegisterActivity.this, "网络已断开！");
                            }

                            @Override
                            public void onSuccess(String data) {
                                Gson gson = new Gson();
                                ForgetPwdBean bean = gson.fromJson(data, ForgetPwdBean.class);
                                ToastUtils.show(LogOnAndRegisterActivity.this, "密码修改成功，请登录！");
                                String phoneNumber = bean.getPhone();
                                mLoginPart.setVisibility(View.VISIBLE);
                                mForgetCodePart.setVisibility(View.GONE);
                                mInputNumber.setText(phoneNumber);
                                mFrogetNumber.setText("");
                                mForgetCodeInsert.setText("");
                                mNewPassWord.setText("");
                                mNewPassWord2.setText("");
                            }

                            @Override
                            public void onFailure(int code) {
                                if (code == 408) {
                                    ToastUtils.show(LogOnAndRegisterActivity.this, "验证码错误！");
                                }
                            }

                            @Override
                            public void onError(Exception e) {

                            }
                        });
            }

        }
    }

    //处理注册逻辑
    private class registerOperation {


        /**
         * 发送验证码
         */
        private void sendMessage() {
            String mPhoneNumber = mRegisterPhone.getText().toString().trim();
            if (TextUtils.isEmpty(mPhoneNumber)) {
                ToastUtils.show(LogOnAndRegisterActivity.this, "手机号码不能为空，请输入手机号码!");
            } else if (MatchPhoneNum.isMobileNO(mPhoneNumber)) {
                ToastUtils.show(LogOnAndRegisterActivity.this, "请输入正确的手机号码！");
            } else {

                OkHttpUtils.post()
                        .url(HttpUtils.URL_BASE + "checkAccount")
                        .addParams("userName", mPhoneNumber)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Gson gson = new Gson();
                                PhoneVerificationBean bean = gson.fromJson(response, PhoneVerificationBean.class);
                                if (bean.getRetCode() != 200) {
                                    ToastUtils.show(LogOnAndRegisterActivity.this, "该手机号码已经注册！");
                                    mRegisterPhone.setText("");
                                    mRegisterPhone.setFocusable(true);
                                } else
                                    ToastUtils.show(LogOnAndRegisterActivity.this, "验证码发送成功！");
                            }
                        });

            }

        }

        /**
         * 发送所有的数据注册
         */
        private void sendAllMessage() {
            String mPhoneNumber = mRegisterPhone.getText().toString().trim();
            String identifyCode = mIdentifyCode.getText().toString().trim();
            final String passWordOne = mPwd.getText().toString().trim();
            String passWordTwice1 = mPwdTwice.getText().toString().trim();
            String passWordTwice = Md5Utils.stringToMD5(passWordTwice1);
            InputFilter[] filters = {new InputFilter.LengthFilter(16)};
            mPwdTwice.setFilters(filters);
            mPwd.setFilters(filters);
            //初步判断数据
            if (TextUtils.isEmpty(mRegisterPhone.getText())) {
                ToastUtils.show(LogOnAndRegisterActivity.this, "请输入手机号！");
                mRegisterPhone.setText("");
                mRegisterPhone.setFocusable(true);
            } else if (TextUtils.isEmpty(mIdentifyCode.getText())) {
                ToastUtils.show(LogOnAndRegisterActivity.this, "请输入验证码！");
                mIdentifyCode.setText("");
                mIdentifyCode.setFocusable(true);
            } else if (TextUtils.isEmpty(mPwd.getText())) {
                ToastUtils.show(LogOnAndRegisterActivity.this, "密码不能为空！");
                mPwd.setText("");
                mPwd.setFocusable(true);
            } else if (TextUtils.isEmpty(mPwdTwice.getText())) {
                ToastUtils.show(LogOnAndRegisterActivity.this, "密码不能为空！");
                mPwdTwice.setText("");
                mPwdTwice.setFocusable(true);
            } else if (!passWordOne.equals(passWordTwice1)) {
                ToastUtils.show(LogOnAndRegisterActivity.this, "两次输入的密码不一致请重新输入！");
            } else if (PassWordMarchUtils.isPwd(passWordTwice)) {
                ToastUtils.show(LogOnAndRegisterActivity.this, "密码必须是英文字母或者数字！");
            } else {
                Map<String, String> AllMessage = new HashMap<>();
                AllMessage.put("userName", mPhoneNumber);
                AllMessage.put("code", identifyCode);
                AllMessage.put("passWord", passWordTwice);
                HttpUtils.post(LogOnAndRegisterActivity.this, new RegisterSuccessBean(), HttpUtils.URL_BASE + "register", AllMessage, new RequestCallBack() {
                    @Override
                    public void onOutNet() {
                        ToastUtils.show(LogOnAndRegisterActivity.this, "网络已断开！");
                    }

                    @Override
                    public void onSuccess(String data) {
                        ToastUtils.show(LogOnAndRegisterActivity.this, "注册成功！请登录！");
                        Gson gson = new Gson();
                        RegisterSuccessBean bean = gson.fromJson(data, RegisterSuccessBean.class);
                        String PhoneNumber = bean.getPhone();
                        mRegisterPart.setVisibility(View.GONE);
                        mLoginPart.setVisibility(View.VISIBLE);
                        mInputNumber.setText(PhoneNumber);
                        mMInputPwd.setText("");
                        //注册界面的全部清空
                        mRegisterPhone.setText("");
                        mIdentifyCode.setText("");
                        mPwd.setText("");
                        mPwdTwice.setText("");
                    }

                    @Override
                    public void onFailure(int code) {
                        if (code == 408) {
                            ToastUtils.show(LogOnAndRegisterActivity.this, "验证码错误！");
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                    }
                });
            }
        }
    }

    //处理登录逻辑
    private void logonOperation() {
        //获得数据：
        String phoneNumber = mInputNumber.getText().toString().trim();
        String pwd = mMInputPwd.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(pwd)) {
            ToastUtils.show(this, "手机号码和密码不能为空！");
        } else {
            if (MatchPhoneNum.isMobileNO(phoneNumber)) {
                ToastUtils.show(this, "请输入正确的手机号码！");
            } else {
                if (PassWordMarchUtils.isPwd(pwd)) {
                    ToastUtils.show(this, "密码必须是英文字母或者数字！");
                } else {
                    //密码使用md5加密
                    String passWord = Md5Utils.stringToMD5(pwd);
                    Map<String, String> params = new HashMap<>();
                    params.put("userName", phoneNumber);
                    params.put("passWord", passWord);
                    params.put("token", phoneNumber + passWord);
                    params.put("type", "phone");
                    params.put("access_token", "");
                    HttpUtils.post(this, new LogInBean(), HttpUtils.URL_BASE + "login", params, new RequestCallBack() {
                        @Override
                        public void onOutNet() {
                            ToastUtils.show(LogOnAndRegisterActivity.this, "请检查网络连接！");
                        }

                        @Override
                        public void onSuccess(String data) {
                            LogUtils.e(data);
                            Gson gson = new Gson();
                            LogInBean bean = gson.fromJson(data, LogInBean.class);
                            String URL = bean.getAvatar();
                            String Message = bean.getMassage_unread();
                            String token = bean.getToken();
                            ToastUtils.show(LogOnAndRegisterActivity.this, "登陆成功！");
                            SPUtils.with(LogOnAndRegisterActivity.this).save("userName", bean.getUser_login());
                            SPUtils.with(LogOnAndRegisterActivity.this).save("nickName", bean.getUser_nicename());
                            SPUtils.with(LogOnAndRegisterActivity.this).save("userEmail", bean.getUser_email());
                            SPUtils.with(LogOnAndRegisterActivity.this).save("realName", bean.getTruename());
                            SPUtils.with(LogOnAndRegisterActivity.this).save("ID", bean.getIdnumber());
                            SPUtils.with(LogOnAndRegisterActivity.this).save("IsLogoIn", true);
                            SPUtils.with(LogOnAndRegisterActivity.this).save("HeadImageUrl", URL);
                            SPUtils.with(LogOnAndRegisterActivity.this).save("message_unread", Message);
                            SPUtils.with(LogOnAndRegisterActivity.this).save("token", token);
                            //个人地址：
                            //省
                            SPUtils.with(LogOnAndRegisterActivity.this).save("province", bean.getProvince());
                            // 市
                            SPUtils.with(LogOnAndRegisterActivity.this).save("city", bean.getCity());
                            // 区
                            SPUtils.with(LogOnAndRegisterActivity.this).save("district", bean.getDistrict());
                            // 具体
                            SPUtils.with(LogOnAndRegisterActivity.this).save("addressDetail", bean.getAddress());

                            Intent intent = new Intent(LogOnAndRegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                            LogOnAndRegisterActivity.this.finish();
                        }

                        @Override
                        public void onFailure(int code) {

                            if (code == 401) {
                                ToastUtils.show(LogOnAndRegisterActivity.this, "密码错误，请确认密码！");
                                mForgetCode.setTextColor(Color.RED);
                            } else if (code == 403) {
                                ToastUtils.show(LogOnAndRegisterActivity.this, "用户被禁用！");
                            } else if (code == 404) {
                                ToastUtils.show(LogOnAndRegisterActivity.this, "用户未注册！");
                            }
                        }

                        @Override
                        public void onError(Exception e) {
                            ToastUtils.show(LogOnAndRegisterActivity.this, "出现错误！");
                        }
                    });
                }
            }
        }
    }

}

