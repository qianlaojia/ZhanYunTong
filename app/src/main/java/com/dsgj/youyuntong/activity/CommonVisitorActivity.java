package com.dsgj.youyuntong.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsgj.youyuntong.JavaBean.newVisitorBean;
import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.Http.HttpUtils;
import com.dsgj.youyuntong.Utils.Http.RequestCallBack;
import com.dsgj.youyuntong.Utils.IDCardValidateUtils;
import com.dsgj.youyuntong.Utils.SPUtils;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.Utils.log.LogUtils;
import com.dsgj.youyuntong.Utils.view.DividerItemDecoration;
import com.dsgj.youyuntong.adapter.CommonVisitorAdapter;
import com.dsgj.youyuntong.base.BaseActivity;
import com.dsgj.youyuntong.base.BaseJavaBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

public class CommonVisitorActivity extends BaseActivity {


    private RelativeLayout mBack;
    private TextView mMiddleText;
    private TextView mInsertVisitor;
    private LinearLayout mInputVisitor;
    private TextView mConfirm;
    private RecyclerView mVisitorList;
    private LinearLayout mInsertVisitor1;
    private Button mReturn;
    private Button mSave;
    private EditText mInputName;
    private EditText mInputID;
    private CommonVisitorAdapter mAdapter;
    private String mNewVisitorName;
    private String mNewVisitorId;
    private List<String> mVisitorName;
    private List<String> mVisitorId;
    private List<newVisitorBean.ResultBean> mNewResultBeanList;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_common_visitor;
    }

    @Override
    protected void initView() {
        mBack = (RelativeLayout) findViewById(R.id.rl_title_back);
        mMiddleText = (TextView) findViewById(R.id.tv_middle_text);
        mInsertVisitor = (TextView) findViewById(R.id.tv_insert_visitor);
        mInputVisitor = (LinearLayout) findViewById(R.id.ll_id_insert_visitor);
        mInsertVisitor1 = (LinearLayout) findViewById(R.id.ll_insert_visitor);
        mConfirm = (TextView) findViewById(R.id.tv_common_visitor_ok);
        mVisitorList = (RecyclerView) findViewById(R.id.rv_common_visitor_list);
        mReturn = (Button) findViewById(R.id.btn_return);
        mSave = (Button) findViewById(R.id.btn_save_visitor);
        mInputName = (EditText) findViewById(R.id.et_insert_name_insert_visitor);
        mInputID = (EditText) findViewById(R.id.et_insert_ID_insert_visitor);


    }

    @Override
    protected void initData() {
        getServerData();
        mMiddleText.setText("常用游客");
        mInputVisitor.setVisibility(View.GONE);
        mConfirm.setVisibility(View.VISIBLE);
        mVisitorList.setVisibility(View.VISIBLE);
        mInsertVisitor1.setVisibility(View.VISIBLE);


    }

    private void getServerData() {
        String logName = SPUtils.with(CommonVisitorActivity.this)
                .get("userName", "13623717683");
        String token = SPUtils.with(CommonVisitorActivity.this)
                .get("token", "");
        Map<String, String> map = new HashMap<>();
        map.put("type", "phone");
        map.put("access_token", "");
        map.put("userName", logName);
        map.put("token", token);
        OkHttpUtils.post()
                .url(HttpUtils.URL_BASE_ORDER + "travelers")
                .params(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.e("后边是返回的数据结果！" + response);
                        Gson gson = new Gson();
                        newVisitorBean newVisitorBean = gson.fromJson(response
                                , newVisitorBean.class);
                        final List<newVisitorBean.ResultBean> resultBeanList = newVisitorBean.getResult();
                        mVisitorName = new ArrayList<>();
                        mVisitorId = new ArrayList<>();
                        for (int i = 0; i < resultBeanList.size(); i++) {
                            mVisitorName.add(resultBeanList.get(i).getContact_name());
                            mVisitorId.add(resultBeanList.get(i).getIdnumber());
                        }
                        LinearLayoutManager layoutManager = new LinearLayoutManager(CommonVisitorActivity.this);
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        mVisitorList.setLayoutManager(layoutManager);
                        mAdapter = new CommonVisitorAdapter(CommonVisitorActivity.this, mVisitorName, mVisitorId, true);
                        mNewResultBeanList = new ArrayList<>();
                        final List<Integer> positionList = new ArrayList<>();//下边的点击事件是对整型数据集合的操作
                        mAdapter.setOnItemClickListener(new CommonVisitorAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                newVisitorBean.ResultBean resultBean = resultBeanList.get(mVisitorName.size() - 1 - position);
                                if (!positionList.contains(position)) {
                                    mNewResultBeanList.add(resultBean);
                                    positionList.add(position);
                                } else {
                                    mNewResultBeanList.remove(resultBean);
                                    for (int i = 0; i < positionList.size(); i++) {
                                        if (positionList.get(i).equals(position))
                                            positionList.remove(i);
                                    }
                                }
                            }
                        });
                        mVisitorList.addItemDecoration(new DividerItemDecoration(CommonVisitorActivity.this,
                                DividerItemDecoration.VERTICAL_LIST));
                        mVisitorList.setAdapter(mAdapter);
                    }
                });
    }


    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);
        mInsertVisitor.setOnClickListener(this);
        mReturn.setOnClickListener(this);
        mSave.setOnClickListener(this);
        mConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_title_back:
                Gson ToJson = new Gson();
                //转换成json数据，再保存
                String strJson = ToJson.toJson(mNewResultBeanList);
                LogUtils.e("后边是转化后的字符串：" + strJson);
                SPUtils.with(CommonVisitorActivity.this).save("ChooseVisitor", strJson);
                this.finish();
                break;
            case R.id.tv_common_visitor_ok:
                Gson ToJson1 = new Gson();
                //转换成json数据，再保存
                String strJson1 = ToJson1.toJson(mNewResultBeanList);
                LogUtils.e("后边是转化后的字符串：" + strJson1);
                SPUtils.with(CommonVisitorActivity.this).save("ChooseVisitor", strJson1);
                this.finish();
                break;
            case R.id.tv_insert_visitor:
                mMiddleText.setText("添加乘客");
                mInputVisitor.setVisibility(View.VISIBLE);
                mConfirm.setVisibility(View.GONE);
                mVisitorList.setVisibility(View.GONE);
                mInsertVisitor1.setVisibility(View.GONE);
                ToastUtils.show(this, "跳转到添加乘客界面。");
                break;
            case R.id.btn_return:
                mMiddleText.setText("常用游客");
                mInputVisitor.setVisibility(View.GONE);
                mConfirm.setVisibility(View.VISIBLE);
                mVisitorList.setVisibility(View.VISIBLE);
                mInsertVisitor1.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_save_visitor://添加数据
                if (saveVisitor()) {
                    mMiddleText.setText("常用游客");
                    String logName = SPUtils.with(CommonVisitorActivity.this)
                            .get("userName", "13623717683");
                    String token = SPUtils.with(CommonVisitorActivity.this)
                            .get("token", "");

                    Map<String, String> map = new HashMap<>();
                    map.put("type", "phone");
                    map.put("access_token", "");
                    map.put("userName", logName);
                    map.put("name", mNewVisitorName);
                    map.put("idnumber", mNewVisitorId);
                    map.put("token", token);
                    HttpUtils.post(CommonVisitorActivity.this
                            , new BaseJavaBean()
                            , HttpUtils.URL_BASE_ORDER + "add_traveler", map, new RequestCallBack() {
                                @Override
                                public void onOutNet() {
                                    ToastUtils.show(CommonVisitorActivity.this, "网络已断开！");
                                }

                                @Override
                                public void onSuccess(String data) {
                                    ToastUtils.show(CommonVisitorActivity.this, "保存成功！");
                                    mInputVisitor.setVisibility(View.GONE);
                                    mConfirm.setVisibility(View.VISIBLE);
                                    mVisitorList.setVisibility(View.VISIBLE);
                                    mInsertVisitor1.setVisibility(View.VISIBLE);
                                    getServerData();
                                }

                                @Override
                                public void onFailure(int code) {
                                    LogUtils.e("如果我出现  那么就上传失败了，失败的代码是：" + code);
                                }

                                @Override
                                public void onError(Exception e) {
                                    LogUtils.e("如果我出现就出现错误了");
                                }
                            });

                }
                break;
        }
    }

    private boolean saveVisitor() {
        mNewVisitorName = mInputName.getText().toString().trim();
        mNewVisitorId = mInputID.getText().toString().trim();
        if (TextUtils.isEmpty(mNewVisitorName)) {
            ToastUtils.show(this, "添加乘客的姓名不能为空");
            mInputName.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(mNewVisitorId)) {
            ToastUtils.show(this, "添加乘客的身份证号不能为空");
            mInputID.requestFocus();
            return false;
        } else if (!new IDCardValidateUtils(this).IDCardValidate(mNewVisitorId)) {
            mInputID.requestFocus();
            return false;
        } else {
            mInputName.setText("");
            mInputID.setText("");
            return true;
        }
    }


}
