package com.dsgj.youyuntong.activity.mine;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsgj.youyuntong.JavaBean.LogInBean;
import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.Http.RequestCallBack;
import com.dsgj.youyuntong.Utils.Http.HttpUtils;
import com.dsgj.youyuntong.Utils.SPUtils;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

public class MineSettingActivity extends BaseActivity {

    private RelativeLayout mGoBack;
    private TextView mMiddleText;
    private RelativeLayout mPersonalInformation;
    private RadioButton mPushMessageLeft;
    private RadioButton mPushMessageRight;
    private RadioGroup mPushMessage;
    private RelativeLayout mPwd;
    private RelativeLayout mAbout;
    private RelativeLayout mClearCache;
    private Button mLogOff;
    // TODO: 2017/5/12   推动消息的上传和状态保持   是上传到服务器还是保存到本地
    private boolean pushMessageOrNot;//是否推送消息
    private boolean mIsLogIn;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_mine_setting;
    }

    @Override
    protected void initView() {
        mGoBack = (RelativeLayout) findViewById(R.id.rl_title_back);
        mMiddleText = (TextView) findViewById(R.id.tv_middle_text);
        mPersonalInformation = (RelativeLayout) findViewById(R.id.rl_personal_information);
        mPushMessage = (RadioGroup) findViewById(R.id.rg_on_off);
        mPushMessageLeft = (RadioButton) findViewById(R.id.rb_on_off_left);
        mPushMessageRight = (RadioButton) findViewById(R.id.rb_mine_setting_on_off_right);
        mPwd = (RelativeLayout) findViewById(R.id.rl_pwd);
        mAbout = (RelativeLayout) findViewById(R.id.rl_about);
        mClearCache = (RelativeLayout) findViewById(R.id.rl_clear_cache);
        mLogOff = (Button) findViewById(R.id.btn_log_off);

    }

    @Override
    protected void initData() {
        mMiddleText.setText("设置");
        //根据是否登录设置退出登录的现实与否/我的资料点击的事件
        mIsLogIn = SPUtils.with(this).get("IsLogoIn", false);
        if (!mIsLogIn) {
            mLogOff.setVisibility(View.GONE);
        } else {
            mLogOff.setVisibility(View.VISIBLE);
        }

        if (pushMessageOrNot) {
            mPushMessage.check(R.id.rb_on_off_left);
        } else {
            mPushMessage.check(R.id.rb_on_off_left);
        }

    }

    @Override
    protected void initListener() {
        mGoBack.setOnClickListener(this);
        mPersonalInformation.setOnClickListener(this);
        mPushMessageLeft.setOnClickListener(this);
        mPushMessageRight.setOnClickListener(this);
        mPwd.setOnClickListener(this);
        mAbout.setOnClickListener(this);
        mClearCache.setOnClickListener(this);
        mLogOff.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_title_back:
                finish();
                break;
            case R.id.rl_personal_information:
                if (mIsLogIn) {

                    jumpToActivity(PersonalInformationActivity.class);
                } else {
                    ToastUtils.show(MineSettingActivity.this, "尚未登录，相关信息请登录后获取！");
                }
                break;
            case R.id.rb_on_off_left:
                mPushMessage.check(R.id.rb_on_off_left);
                pushMessageOrNot = true;
                break;
            case R.id.rb_mine_setting_on_off_right:

                mPushMessage.check(R.id.rb_mine_setting_on_off_right);
                pushMessageOrNot = false;
                break;
            case R.id.rl_pwd:
                jumpToActivity(ModifyPassWordActivity.class);
                break;
            case R.id.rl_about:
                jumpToActivity(AboutActivity.class);
                break;
            case R.id.rl_clear_cache:
                ToastUtils.show(this, "跳转到清除缓存界面！");
                break;
            case R.id.btn_log_off:
                //设置参数
                Map<String, String> map = new HashMap<>();
                map.put("type", "phone");
                map.put("access_token", "");
                map.put("userName", SPUtils.with(this).get("userName", ""));
                map.put("token", SPUtils.with(this).get("token", ""));
                //联网
                HttpUtils.post(this, new LogInBean(), HttpUtils.URL_BASE_USER + "logOff", map, new RequestCallBack() {
                    @Override
                    public void onOutNet() {
                        ToastUtils.show(MineSettingActivity.this, "网络已断开！");
                    }

                    @Override
                    public void onSuccess(String data) {
                        ToastUtils.show(MineSettingActivity.this, "退出成功！");
                        SPUtils.with(MineSettingActivity.this).save("IsLogoIn", false);
                        SPUtils.with(MineSettingActivity.this).save("message_unread", "0");
                        MineSettingActivity.this.finish();
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
}
