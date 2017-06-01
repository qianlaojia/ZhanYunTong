package com.dsgj.youyuntong.activity.mine;

import android.content.Intent;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsgj.youyuntong.JavaBean.LogInBean;
import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.Http.RequestCallBack;
import com.dsgj.youyuntong.Utils.Http.HttpUtils;
import com.dsgj.youyuntong.Utils.MatchEmailsUtils;
import com.dsgj.youyuntong.Utils.SPUtils;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

public class EditPersonalInfoActivity extends BaseActivity {


    private RelativeLayout mRlBack;
    private TextView mTvMiddleText;
    private EditText mEtInsertInfo;
    private TextView mTextLimit;
    private Button mBtnSave;
    private String mStyle;
    private String mInputText;
    private EditText mProvice;
    private EditText mCity;
    private EditText mAddressDetails;
    private Button mAddressSave;
    private LinearLayout mOneLine;
    private LinearLayout mAddressNormal;
    private EditText mDistrict;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_edit_personal_info;
    }

    @Override
    protected void initView() {
        mRlBack = (RelativeLayout) findViewById(R.id.rl_title_back);
        mTvMiddleText = (TextView) findViewById(R.id.tv_middle_text);
        mEtInsertInfo = (EditText) findViewById(R.id.et_information);
        mTextLimit = (TextView) findViewById(R.id.tv_text_limit);
        mBtnSave = (Button) findViewById(R.id.btn_save);
        mProvice = (EditText) findViewById(R.id.et_province);
        mCity = (EditText) findViewById(R.id.et_city);
        mAddressDetails = (EditText) findViewById(R.id.et_address_detail);
        mAddressSave = (Button) findViewById(R.id.btn_save_address);
        mOneLine = (LinearLayout) findViewById(R.id.ll_one_line);
        mAddressNormal = (LinearLayout) findViewById(R.id.ll_normal_address);
        mDistrict = (EditText) findViewById(R.id.et_district);

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mStyle = intent.getStringExtra("style");
        String hintText = "";
        switch (mStyle) {
            case "name":
                mOneLine.setVisibility(View.VISIBLE);
                mAddressNormal.setVisibility(View.GONE);
                mTvMiddleText.setText("修改姓名");
                hintText = "请输入姓名";
                mTextLimit.setText("可输入2-6个中文，不支持中英文搭配等！");
                break;
            case "nickName":
                mOneLine.setVisibility(View.VISIBLE);
                mAddressNormal.setVisibility(View.GONE);
                mTvMiddleText.setText("修改昵称");
                hintText = "请输入昵称";
                mTextLimit.setText("可输入2-16个字符！");
                break;
            case "email":
                mOneLine.setVisibility(View.VISIBLE);
                mAddressNormal.setVisibility(View.GONE);
                mTvMiddleText.setText("修改邮箱");
                hintText = "请输入邮箱！";
                mTextLimit.setText("请严格按照正确的邮箱格式输入!");
                break;
            case "address":
                mTvMiddleText.setText("常住地");
                mOneLine.setVisibility(View.GONE);
                mAddressNormal.setVisibility(View.VISIBLE);
                break;
        }
        SpannableString s = new SpannableString(hintText);
        mEtInsertInfo.setHint(s);
    }

    @Override
    protected void initListener() {
        mRlBack.setOnClickListener(this);
        mBtnSave.setOnClickListener(this);
        mAddressSave.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_title_back:
                this.finish();
                break;
            case R.id.btn_save: {
                //获得输入的文字
                mInputText = mEtInsertInfo.getText().toString().trim();
                switch (mStyle) {
                    case "name":
                        String mToastRealName = "新的姓名不能为空，请输入！";
                        String realNameKey = "newName";
                        String realNameUrl = HttpUtils.URL_BASE_USER + "setNewName";
                        String realNameParam = "realName";
                        dataInternet(mToastRealName, realNameParam, realNameUrl, realNameKey);
                        break;
                    case "nickName":
                        String mToastNickName = "新的昵称不能为空，请输入！";
                        String nickNameKey = "nickName";
                        String nickNameUrl = HttpUtils.URL_BASE_USER + "setNickName";
                        String nickNameParam = "nickName";
                        dataInternet(mToastNickName, nickNameKey, nickNameUrl, nickNameParam);
                        break;
                    case "email":
                        if (TextUtils.isEmpty(mInputText)) {
                            ToastUtils.show(EditPersonalInfoActivity.this, "新的邮箱不能为空，请输入！");
                            mEtInsertInfo.setText("");
                        } else if (!MatchEmailsUtils.isEmail(mInputText)) {
                            ToastUtils.show(EditPersonalInfoActivity.this, "请输入正确的邮箱格式！");
                            mEtInsertInfo.setText("");
                        } else {
                            String mToastEmail = "新的邮箱不能为空，请输入！";
                            String emailSPKey = "userEmail";
                            String emailUrl = HttpUtils.URL_BASE_USER + "setEmail";
                            String emailParam = "email";
                            dataInternet(mToastEmail, emailSPKey, emailUrl, emailParam);
                        }
                        break;
                }
                break;
            }
            case R.id.btn_save_address:
                final String Province = mProvice.getText().toString().trim();
                final String City = mCity.getText().toString().trim();
                final String district = mDistrict.getText().toString().trim();
                final String detail = mAddressDetails.getText().toString().trim();
                if (TextUtils.isEmpty(Province) || TextUtils.isEmpty(City) || TextUtils.isEmpty(detail)) {
                    ToastUtils.show(EditPersonalInfoActivity.this, "省、市、具体地址不能为空，请输入！");
                } else {
                    Map<String, String> map = new HashMap<>();
                    map.put("type", "phone");
                    map.put("access_token", "");
                    map.put("userName"
                            , SPUtils.with(EditPersonalInfoActivity.this).get("userName", ""));
                    map.put("token"
                            , SPUtils.with(EditPersonalInfoActivity.this).get("token", ""));
                    map.put("province", Province);
                    map.put("city", City);
                    map.put("district", district);
                    map.put("address", detail);
                    HttpUtils.post(EditPersonalInfoActivity.this, new LogInBean(), HttpUtils.URL_BASE_USER + "setAddress", map, new RequestCallBack() {
                        @Override
                        public void onOutNet() {
                            ToastUtils.show(EditPersonalInfoActivity.this, "网络已断开！");
                        }

                        @Override
                        public void onSuccess(String data) {
                            ToastUtils.show(EditPersonalInfoActivity.this, "修改成功！将在下一次登录时生效！");
                            SPUtils.with(EditPersonalInfoActivity.this).save("province", Province);
                            SPUtils.with(EditPersonalInfoActivity.this).save("city", City);
                            SPUtils.with(EditPersonalInfoActivity.this).save("district", district);
                            SPUtils.with(EditPersonalInfoActivity.this).save("addressDetail", detail);
                            EditPersonalInfoActivity.this.finish();
                        }

                        @Override
                        public void onFailure(int code) {
                            ToastUtils.show(EditPersonalInfoActivity.this, "修改失败！请稍候重试！");

                        }

                        @Override
                        public void onError(Exception e) {
                            ToastUtils.show(EditPersonalInfoActivity.this, "修改失败！请稍候重试！");
                        }
                    });

                }
                break;
        }
    }

    /**
     * 联网上传数据到服务器，本地保存一份
     *
     * @param mToastText EditView 为空的时候提示
     * @param spKey      本地保存的sp 的键
     * @param url        接口Url
     * @param param      改变数据的参数key值
     */
    private void dataInternet(String mToastText, final String spKey, String url, String param) {
        if (TextUtils.isEmpty(mInputText)) {
            ToastUtils.show(this, mToastText);
            mEtInsertInfo.setText("");
            mEtInsertInfo.setFocusable(true);
        } else {
            String phone = SPUtils.with(this).get("userName", "");
            String token = SPUtils.with(this).get("token", "");
            Map<String, String> map = new HashMap<>();
            map.put("type", "phone");
            map.put("access_token", "");
            map.put("token", token);
            map.put(param, mInputText);
            map.put("userName", phone);
            HttpUtils.post(this
                    , new LogInBean()
                    , url
                    , map, new RequestCallBack() {
                        @Override
                        public void onOutNet() {
                            ToastUtils.show(EditPersonalInfoActivity.this, "无网络，请检查网络！");
                        }

                        @Override
                        public void onSuccess(String data) {
                            ToastUtils.show(EditPersonalInfoActivity.this, "修改成功，下次登录时生效！");
                            SPUtils.with(EditPersonalInfoActivity.this).save(spKey, mInputText);
                            EditPersonalInfoActivity.this.finish();
                        }

                        @Override
                        public void onFailure(int code) {
                            ToastUtils.show(EditPersonalInfoActivity.this, "修改未成功！请稍候再试。。。" + code);
                        }

                        @Override
                        public void onError(Exception e) {
                            ToastUtils.show(EditPersonalInfoActivity.this, "修改未成功！请稍候再试。。。");
                        }
                    });
        }


    }
}
