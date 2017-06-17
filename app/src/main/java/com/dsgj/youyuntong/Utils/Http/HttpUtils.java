package com.dsgj.youyuntong.Utils.Http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


import com.dsgj.youyuntong.Utils.APPContent;
import com.dsgj.youyuntong.Utils.JsonUtils;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.Utils.log.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import okhttp3.Call;

public class HttpUtils {

    /**
     * 基础链接地址
     */
    public static final String URL_BASE = "http://api.yezi6.com/dev/interface.php/v1/index/";
    public static final String URL_BASE_USER = "http://api.yezi6.com/dev/interface.php/v1/User/";
    public static final String URL_BASE_TOURISM = "http://api.yezi6.com/dev/interface.php/v1/Tourism/";
    /**
     * 基础上传文件链接地址
     */
    public static final String URL_FILE_BASE = "";

    private static final int CODE_SUCCESS = 200;// 请求成功后返回的resultCode
    private static final String STRING_ERROR_IGN = "Canceled;Socket closed"; // 异常为此之一时，不进行操作
    private static HashMap<Object, RequestCall> connMap = new HashMap<>();

    /**
     * 判断网络是否可用
     */
    public static boolean haveNet(Context context) {
        boolean isOk = false;
        ConnectivityManager connectionManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                isOk = true;
                LogUtils.writeLog("wifi可用");
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                isOk = true;
                LogUtils.writeLog("手机网络可用");
            } else {
                LogUtils.writeLog("网络不可用");
            }
        }
        return isOk;
    }

    /**
     * 中断某标记对应的连接（中断时报Canceled、Socket Closed异常）
     *
     * @param tag
     */
    public static void breakConnect(Objects tag) {
        RequestCall requestCall = connMap.get(tag);
        if (requestCall != null) {
            requestCall.cancel();
            connMap.remove(tag);
        }
    }

    /**
     * get方式请求Json数据（ret、msg、body）,并封装为bean
     *
     * @param ctx      上下文，只接受Activity或Application上下文
     * @param url
     * @param callBack
     */
    @Nullable
    public synchronized static void get(final Context ctx, @Nullable final Object tag, String url, @NonNull final RequestCallBack callBack) {
        if (!haveNet(ctx)) {
            callBack.onOutNet();
            return;
        }
        LogUtils.writeLog(url);
        RequestCall call = OkHttpUtils.get().url(url).build();
        if (tag != null) connMap.put(tag, call);
        call.execute(new StringCallback() {
            @Override
            public void onResponse(String json, int id) {
                if (tag != null) connMap.remove(tag);
                LogUtils.writeLog(json);
                int resultCode = JsonUtils.getInt(json, "resultCode");
                switch (resultCode) {
                    case CODE_SUCCESS:
                        String resultdata = JsonUtils.getString(json, "resultdata");
                        callBack.onSuccess(resultdata);
                        break;
                    default:
                        callBack.onFailure(resultCode);
                        break;
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                if (tag != null) connMap.remove(tag);
                String err = e.getMessage();
                LogUtils.e("http", err);
                LogUtils.writeLog("http", "报错：" + err);
                if (err != null && STRING_ERROR_IGN.contains(err.trim())) return;
                callBack.onError(e);
            }
        });
    }

    /**
     * post请求Json数据（ret、msg、body）,并封装为bean
     *
     * @param ctx      上下文，只接受Activity或Application上下文
     * @param url
     * @param params
     * @param callBack
     */
    @Nullable
    public synchronized static void post(final Context ctx, final Object tag, final String url, Map<String, String> params,
                                         @NonNull final RequestCallBack callBack) {
        if (!haveNet(ctx)) {
            callBack.onOutNet();
            return;
        }
        // 检查链接传参
        String urlWhole = null;
        for (Map.Entry<String, String> entry : params.entrySet())
            if (urlWhole == null) {
                urlWhole = url + "?" + entry.getKey() + "=" + entry.getValue();
            } else {
                urlWhole += "&" + entry.getKey() + "=" + entry.getValue();
            }


        // 网络连接
        RequestCall call;
        call = OkHttpUtils.post().url(url).params(params).build();

        if (tag != null) connMap.put(tag, call);
        call.execute(new StringCallback() {
            @Override
            public void onResponse(String json, int arg1) {
                LogUtils.e("请求的json数据：：-----------" + json + "-------");
                if (tag != null) connMap.remove(tag);
                int resultCode = JsonUtils.getInt(json, "retCode");
                switch (resultCode) {
                    case CODE_SUCCESS:
                        String resultData = JsonUtils.getString(json, "result");
                        callBack.onSuccess(resultData);
                        break;
                    default:
                        callBack.onFailure(resultCode);
                        break;
                }
            }

            @Override
            public void onError(Call arg0, Exception e, int arg2) {
                if (tag != null) connMap.remove(tag);
                String err = e.getMessage();
                LogUtils.e("http", err);
                LogUtils.writeLog("http", "报错：" + err);
                if (err != null && STRING_ERROR_IGN.contains(err.trim())) return;
                callBack.onError(e);
            }
        });
    }

    /**
     * 上传文件
     *
     * @param ctx
     * @param url      上传url
     * @param file     上传的文件
     * @param params
     * @param callBack
     */
    public synchronized static void upload(final Context ctx, @Nullable final Object tag, String url, final File file, Map<String, String> params,
                                           @NonNull final UploadCallBack callBack) {
        if (!haveNet(ctx)) {
            callBack.onOutNet();
            return;
        }
        final RequestCall call = OkHttpUtils.post().url(url).params(params)
                .addFile("file", file.getName(), file).build();
        if (tag != null) connMap.put(tag, call);
        call.execute(new StringCallback() {
            @Override
            public void inProgress(float progress, long total, int id) {
                callBack.inProgress(progress, total);
            }

            @Override
            public void onResponse(String json, int arg1) {
                if (tag != null) connMap.remove(tag);
                LogUtils.writeLog(json);
                int resultCode = JsonUtils.getInt(json, "resultCode");
                switch (resultCode) {
                    case CODE_SUCCESS:
                        String resultData = JsonUtils.getString(json, "resultdata");
                        callBack.onSuccess(resultData);
                        break;
                    default:
                        callBack.onFailure(resultCode);
                        break;
                }
                // 删除simple目录下的压缩图片
                if (file.getAbsolutePath().startsWith(APPContent.simpleDir)) {
                    try {
                        file.delete();
                    } catch (Exception e) {
                        LogUtils.e("image", "压缩图片删除异常" + e.getMessage());
                    }
                }
            }

            @Override
            public void onError(Call arg0, Exception e, int arg2) {
                if (tag != null) connMap.remove(tag);
                String err = e.getMessage();
                LogUtils.e("http", err);
                LogUtils.writeLog("http", "报错：" + err);
                if (err != null && STRING_ERROR_IGN.contains(err.trim())) return;
                callBack.onError(e);
            }
        });
    }


}
