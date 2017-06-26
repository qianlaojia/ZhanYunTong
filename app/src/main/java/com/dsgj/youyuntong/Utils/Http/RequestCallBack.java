package com.dsgj.youyuntong.Utils.Http;

public interface RequestCallBack {
    /**
     * 手机网络未打开情况下
     */
    void onOutNet();

    /**
     * 请求成功后返回json主体
     *
     * @param data 可直接编译bean的主体
     */
    void onSuccess(String data);

    /**
     * 请求数据失败后，返回json主体
     *
     * @param code 响应的结果码
     */
    void onFailure(int code);

    /**
     * 请求网络连接失败
     *
     * @param e 错误信息
     */
    void onError(Exception e);
    /**
     * token失效的解决方法
     */
    //void onTokenOutTime(int code);
}