package com.dsgj.youyuntong.activity.mine;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.IDCardValidateUtils;
import com.dsgj.youyuntong.Utils.SPUtils;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.base.BaseActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SecuritySettingActivity extends BaseActivity {


    private RelativeLayout mBack;
    private TextView mMiddleText;
    private RelativeLayout mMRealName;
    private EditText mInsertName;
    private EditText mInsertID;
    private TextView successText;
    private Button mOk;
    private LinearLayout mShowInsert;
    private boolean mIsAlreadyRealName;


    public SecuritySettingActivity() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_security_setting;
    }

    @Override
    protected void initView() {
        mBack = (RelativeLayout) findViewById(R.id.rl_title_back);
        mMiddleText = (TextView) findViewById(R.id.tv_middle_text);
        mMRealName = (RelativeLayout) findViewById(R.id.rv_real_name);
        mInsertName = (EditText) findViewById(R.id.et_insert_name);
        mInsertID = (EditText) findViewById(R.id.et_insert_id);
        successText = (TextView) findViewById(R.id.tv_success_text);
        mOk = (Button) findViewById(R.id.btn_realName_ok);
        mShowInsert = (LinearLayout) findViewById(R.id.ll_show_insert);
    }

    @Override
    protected void initData() {
        mMiddleText.setText("安全设置");
        mShowInsert.setVisibility(View.GONE);
        successText.setVisibility(View.GONE);
        mOk.setVisibility(View.GONE);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mIsAlreadyRealName = bundle.getBoolean("RealNameOrNot");

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mIsAlreadyRealName) {
            mMRealName.setVisibility(View.GONE);
            mMiddleText.setText("实名认证");
            mShowInsert.setVisibility(View.VISIBLE);
            successText.setVisibility(View.VISIBLE);
            successText.setText("账户已实名认证成功！");
            mInsertName.setText(SPUtils.with(this).get("real_name","尊敬的用户"));
            mInsertID.setText(SPUtils.with(this).get("real_id","412702********2771"));
            successText.setTextColor(Color.RED);
            mOk.setVisibility(View.GONE);
            mInsertName.setEnabled(false);
            mInsertID.setEnabled(false);
        }
    }

    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);
        mMRealName.setOnClickListener(this);
        mOk.setOnClickListener(this);
        mInsertName.setOnClickListener(this);
        mInsertID.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_title_back:
                finish();
                break;
            case R.id.rv_real_name:
                mMiddleText.setText("实名认证");
                mMRealName.setVisibility(View.GONE);
                mShowInsert.setVisibility(View.VISIBLE);
                mOk.setVisibility(View.VISIBLE);

                break;
            case R.id.btn_realName_ok:
                if (setUpInsertData()) {
                    successText.setVisibility(View.VISIBLE);
                    mOk.setVisibility(View.GONE);
                    mIsAlreadyRealName = true;

                    //TODO：保存本地或者网络
                    SPUtils.with(this).save("RealNameOrNot",true);
                    SPUtils.with(this).save("real_name",mInsertName.getText().toString().trim());
                    SPUtils.with(this).save("real_id",mInsertID.getText().toString().trim());

                }
                break;
            case R.id.et_insert_name:
                mInsertName.setCursorVisible(true);
                break;
            case R.id.et_insert_id:
                mInsertID.setCursorVisible(true);
                break;
        }

    }

    private boolean setUpInsertData() {
        String name = mInsertName.getText().toString().trim();
        String ID = mInsertID.getText().toString().trim();
        IDCardValidateUtils IDUtils = new IDCardValidateUtils(this);
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(ID)) {
            ToastUtils.show(this, "姓名或身份证号不能为空！请准确输入！");
            return false;
        } else if (!checkUsername(name)) {
            ToastUtils.show(this, "姓名输入错误！请准确输入！");
            return false;
        } else if (!IDUtils.IDCardValidate(ID)) {
            ToastUtils.show(this, "身份证格式输入错误！请准确输入！");
            return false;
        }
        return true;
    }

    public static boolean checkUsername(String username) {
        String regex = "[\\u4E00-\\u9FA5]{2,5}(?:·[\\u4E00-\\u9FA5]{2,5})*";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(username);
        return m.matches();
    }


}
