package com.dsgj.youyuntong.base;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dsgj.youyuntong.Utils.log.LogUtils;

import java.util.Map;

/**
 * baseFragment
 * Created by zhangyunhao on 2017/4/19.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    private FragmentActivity ctx;
    protected BaseFragment frag;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutID(), container, false);
        dealCommonPart(view);
        initView(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
        initListener();
    }

    protected abstract int getLayoutID();

    /**
     * 初始化view  只能初始化 不能做view更新的操作
     *
     * @param
     */
    protected abstract void initView(View view);

    /**
     * 初始化数据
     *
     * @param
     */
    protected abstract void initData();

    /**
     * 初始化监听
     */
    protected abstract void initListener();


    /**
     * 处理一下逻辑关系
     *
     *
     */
    private void dealCommonPart(View view) {
        //上下文
        ctx = getActivity();
        frag = this;
    }


    /**
     * （可调用方法）跳转界面这个可以携带参数
     *
     * @param clz 跳转到的Activity
     */
    public void jumpToActivity(Class<? extends BaseActivity> clz) {
        jumpToActivity(clz, null);
    }

    /**
     * （可调用方法）跳转界面
     *
     * @param clz        跳转到的Activity
     * @param intentData 跳转的携带数据
     */
    public void jumpToActivity(Class<? extends BaseActivity> clz, Map<String, String> intentData) {
        Intent intent = new Intent(ctx, clz);
        LogUtils.writeLog("启动Activity:" + clz.getSimpleName() + "<-" + getClass().getSimpleName());
        if (intentData != null && intentData.size() > 0) {
            for (Map.Entry<String, String> entry : intentData.entrySet()) {
                intent.putExtra(entry.getKey(), entry.getValue());
                LogUtils.writeLog("携带参数：" + entry.getKey() + "=" + entry.getValue());
            }
        }
        startActivity(intent);
    }

    /**
     * （可调用方法）跳转界面
     *
     * @param clz         跳转到的Activity
     * @param requestCode 请求码
     */
    public void jumpToActivityForResult(Class<? extends BaseActivity> clz, int requestCode) {
        jumpToActivityForResult(clz, null, requestCode);
    }

    /**
     * （可调用方法）跳转界面
     *
     * @param clz         跳转到的Activity
     * @param intentData  跳转的携带数据
     * @param requestCode 请求码
     */
    public void jumpToActivityForResult(Class<? extends BaseActivity> clz, Map<String, String> intentData, int requestCode) {
        Intent intent = new Intent(ctx, clz);
        LogUtils.writeLog("启动Activity(requestCode:" + requestCode + "):"
                + clz.getSimpleName() + "<-" + getClass().getSimpleName());
        if (intentData != null && intentData.size() > 0) {
            for (Map.Entry<String, String> entry : intentData.entrySet()) {
                intent.putExtra(entry.getKey(), entry.getValue());
                LogUtils.writeLog("携带参数：" + entry.getKey() + "=" + entry.getValue());
            }
        }
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            LogUtils.writeLog("接收返回数据:" + ctx.getClass().getSimpleName()
                    + "|requestCode:" + requestCode + "|resultCode:" + resultCode);
        }
    }

}
