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

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.IDCardValidateUtils;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.Utils.view.DividerItemDecoration;
import com.dsgj.youyuntong.adapter.CommonVisitorAdapter;
import com.dsgj.youyuntong.base.BaseActivity;

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
        mMiddleText.setText("常用游客");
        mInputVisitor.setVisibility(View.GONE);
        mConfirm.setVisibility(View.VISIBLE);
        mVisitorList.setVisibility(View.VISIBLE);
        mInsertVisitor1.setVisibility(View.VISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mVisitorList.setLayoutManager(layoutManager);

        CommonVisitorAdapter adapter = new CommonVisitorAdapter(this);
        adapter.setOnItemClickListener(new CommonVisitorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        mVisitorList.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
        mVisitorList.setAdapter(adapter);

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
                this.finish();
                break;
            case R.id.tv_common_visitor_ok:
                this.finish();
             //// TODO: 2017/6/7  添加数据的保存和返回
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
            case R.id.btn_save_visitor:
                if (saveVisitor()) {
                    mMiddleText.setText("常用游客");
                    mInputVisitor.setVisibility(View.GONE);
                    mConfirm.setVisibility(View.VISIBLE);
                    mVisitorList.setVisibility(View.VISIBLE);
                    mInsertVisitor1.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    private boolean saveVisitor() {
        String newVisitorName = mInputName.getText().toString().trim();
        String newVisitorId = mInputID.getText().toString().trim();
        if (TextUtils.isEmpty(newVisitorName)) {
            ToastUtils.show(this, "添加乘客的姓名不能为空");
            mInputName.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(newVisitorId)) {
            ToastUtils.show(this, "添加乘客的身份证号不能为空");
            mInputID.requestFocus();
            return false;
        } else if (!new IDCardValidateUtils(this).IDCardValidate(newVisitorId)) {
            mInputID.requestFocus();
            return false;
        } else {
            mInputName.setText("");
            mInputID.setText("");
            return true;
        }
    }
}
