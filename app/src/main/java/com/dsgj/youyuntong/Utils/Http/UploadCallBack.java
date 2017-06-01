package com.dsgj.youyuntong.Utils.Http;

/**
 * 上传回调
 *
 * @author 云浩
 */
public interface UploadCallBack {
    void onOutNet();

    /**
     * 上传过程中循环调用
     *
     * @param progress 已下载的百分比？
     * @param total    总大小
     */
    void inProgress(float progress, long total);

    /**
     * 上传完成，响应结束
     *
     * @param data 响应的数据主体
     */
    void onSuccess(String data);

    /**
     * 上传出错，响应结束
     *
     * @param code 响应的结果码
     */
    void onFailure(int code);

    /**
     * 连接出错
     *
     * @param e  错误
     */
    void onError(Exception e);
}
