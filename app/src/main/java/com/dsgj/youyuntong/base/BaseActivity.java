package com.dsgj.youyuntong.base;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.dsgj.youyuntong.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.Map;

/**
 * Created by zhangyunhao on 2017/4/19.
 */

public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {
    private BaseActivity ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        //处理公共逻辑
        dealCommonPart();
        initView();
        initData();
        initListener();
    }

    /**
     * 获取布局的id
     *
     * @return 布局id
     */
    protected abstract int getLayoutID();

    /**
     * 初始化布局
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化监听
     */
    protected abstract void initListener();

    private void dealCommonPart() {
        this.ctx = this;

    }


    /**
     * 不带参数跳转到clz的Activity
     *
     * @param clz
     */
    public void jumpToActivity(Class<? extends BaseActivity> clz) {
        jumpToActivity(clz, null);
    }

    /**
     * （可调用方法）携带参数跳转界面
     *
     * @param clz        跳转到的Activity
     * @param intentData 跳转的携带数据
     */
    public void jumpToActivity(Class<? extends BaseActivity> clz, Map<String, String> intentData) {
        Intent intent = new Intent(ctx, clz);
        if (intentData != null && intentData.size() > 0) {
            //加强For循环
            for (Map.Entry<String, String> entry : intentData.entrySet()) {
                intent.putExtra(entry.getKey(), entry.getValue());

            }
        }
        startActivity(intent);
    }

    /**
     * （可调用方法）跳转界面，不携带数据，但是携带数据的返回码
     *
     * @param clz         跳转到的Activity
     * @param requestCode 请求码
     */
    public void jumpToActivityForResult(Class<? extends BaseActivity> clz, int requestCode) {
        jumpToActivityForResult(clz, null, requestCode);
    }

    /**
     * （可调用方法）携带数据跳转界面并要求返回数据
     *
     * @param clz         跳转到的Activity
     * @param intentData  跳转的携带数据
     * @param requestCode 请求码
     */
    public void jumpToActivityForResult(Class<? extends BaseActivity> clz, Map<String, String> intentData, int requestCode) {
        Intent intent = new Intent(ctx, clz);
        if (intentData != null && intentData.size() > 0) {
            for (Map.Entry<String, String> entry : intentData.entrySet()) {
                intent.putExtra(entry.getKey(), entry.getValue());
            }
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 接受返回的数据：
     * requestCode   请求码
     * resultCode    结果码
     * data   数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {

        }
    }

    /**
     * 摧毁
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
