package com.dsgj.youyuntong.activity.ThroughTrain;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsgj.youyuntong.JavaBean.ThroughTrip.SearchTrip.SearchResultBean;
import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.Http.HttpUtils;
import com.dsgj.youyuntong.Utils.SPUtils;
import com.dsgj.youyuntong.Utils.log.LogUtils;
import com.dsgj.youyuntong.activity.CalendarActivity;
import com.dsgj.youyuntong.activity.ProductDetailActivity;
import com.dsgj.youyuntong.adapter.ThroughTrain.ThroughTrainInquiryAdapter;
import com.dsgj.youyuntong.base.BaseActivity;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

public class ThroughTrainInquiryActivity extends BaseActivity {
    private RelativeLayout mBack;
    private TextView mMiddleText;
    private RecyclerView mQueryResult;
    private TextView mFrontDay;
    private TextView mNextDay;
    private TextView mMiddleTime;
    private String mTime;
    private String mStartLocation;
    private String mEndLocation;
    private static final int INTERNET_SUCCESS = 1;
    private static final int OUT_NET = 2;
    private static final int ERROR = 3;


    private List<SearchResultBean.ResultBean> mResultBeenList;
    private View mCover;
    private ProgressBar mPb;
    long mStartTime = SPUtils.with(ThroughTrainInquiryActivity.this).get("selectedDateStamp", System.currentTimeMillis()) / 1000;
    String realTime = Long.toString(mStartTime);
    private TextView mNoData;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_through_train_inquiry;
    }

    @Override
    protected void initView() {
        mBack = (RelativeLayout) findViewById(R.id.rl_title_back);
        mMiddleText = (TextView) findViewById(R.id.tv_middle_text);
        mQueryResult = (RecyclerView) findViewById(R.id.rv_through_train_query_result);
        mFrontDay = (TextView) findViewById(R.id.tv_front_day);
        mNextDay = (TextView) findViewById(R.id.tv_next_day);
        mMiddleTime = (TextView) findViewById(R.id.tv_middle_time);
        mCover = findViewById(R.id.view_tt_cover);
        mPb = (ProgressBar) findViewById(R.id.pb_tt_search_result);
        mNoData = (TextView) findViewById(R.id.tv_no_data);
    }

    @Override
    protected void initData() {
        mNoData.setVisibility(View.GONE);
        Intent intent = getIntent();
        mStartLocation = intent.getStringExtra("startLocation");//出发地点
        mEndLocation = intent.getStringExtra("endLocation");//目的地
        mTime = intent.getStringExtra("time");
        mMiddleTime.setText(mTime);//出发时间
        mMiddleText.setText(mStartLocation + "-" + mEndLocation);
        getInternetData(realTime);

    }

    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);
        mFrontDay.setOnClickListener(this);
        mNextDay.setOnClickListener(this);
        mMiddleTime.setOnClickListener(this);

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_title_back:
                this.finish();
                break;
            case R.id.tv_front_day:
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd EEEE");
                final long currentTimeMillis = System.currentTimeMillis();
                Long front = mStartTime - 86400;
                mStartTime = front;
                if ((currentTimeMillis - 86400000) > (front * 1000)) {
                    mFrontDay.setTextColor(Color.GRAY);
                    return;
                } else {
                    mFrontDay.setTextColor(Color.WHITE);
                    mPb.setVisibility(View.VISIBLE);
                    mCover.setVisibility(View.VISIBLE);
                    String frontDay = Long.toString(front);
                    getInternetData(frontDay);
                    mMiddleTime.setText(sdf.format(front * 1000));
                }
                break;
            case R.id.tv_next_day:
                mPb.setVisibility(View.VISIBLE);
                mCover.setVisibility(View.VISIBLE);
                Long next = mStartTime + 86400;
                mStartTime = next;
                String nextDay = Long.toString(next);
                getInternetData(nextDay);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd EEEE");
                mMiddleTime.setText(format.format(next * 1000));
                break;
            case R.id.tv_middle_time:
                Intent intent = new Intent(ThroughTrainInquiryActivity.this, CalendarActivity.class);
                startActivity(intent);
                break;
        }
    }

    //开启新线程获取相关数据
    private void getInternetData(final String time) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                Map<String, String> map = new HashMap<>();
                map.put("page", "2");
                map.put("from_city", mStartLocation);
                map.put("to_city", mEndLocation);
                map.put("date", time);
                OkHttpUtils.post()
                        .url(HttpUtils.URL_BASE_TOURISM + "through_search_list")
                        .params(map)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                mHandler.sendEmptyMessage(ERROR);
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Gson gson = new Gson();
                                SearchResultBean searchResultBean = gson.fromJson(response, SearchResultBean.class);
                                int code = searchResultBean.getRetCode();
                                if (code == 200) {
                                    mResultBeenList = searchResultBean.getResult();
                                    mHandler.sendEmptyMessage(INTERNET_SUCCESS);
                                } else {
                                    mHandler.sendEmptyMessage(ERROR);
                                }
                            }
                        });
            }
        }.start();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case INTERNET_SUCCESS:
                    mQueryResult.setVisibility(View.VISIBLE);
                    mNoData.setVisibility(View.GONE);
                    LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(ThroughTrainInquiryActivity.this);
                    verticalLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    mQueryResult.setLayoutManager(verticalLayoutManager);
                    ThroughTrainInquiryAdapter verticalAdapter = new ThroughTrainInquiryAdapter(ThroughTrainInquiryActivity.this, mResultBeenList);
                    verticalAdapter.setOnItemClickListener(new ThroughTrainInquiryAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            String productId = mResultBeenList.get(position).getProduct_id();
                            String productCode = mResultBeenList.get(position).getProduct_code();
                            Intent intent = new Intent(ThroughTrainInquiryActivity.this, ProductDetailActivity.class);
                            intent.putExtra("product_id", productId);
                            intent.putExtra("product_code", productCode);
                            startActivity(intent);
                        }
                    });
                    mQueryResult.setAdapter(verticalAdapter);
                    mPb.setVisibility(View.GONE);
                    mCover.setVisibility(View.GONE);
                    break;
                case OUT_NET:
                    mQueryResult.setVisibility(View.GONE);
                    mNoData.setVisibility(View.VISIBLE);
                    mPb.setVisibility(View.GONE);
                    mCover.setVisibility(View.GONE);
                    break;
                case ERROR:
                    mQueryResult.setVisibility(View.GONE);
                    mNoData.setVisibility(View.VISIBLE);
                    mPb.setVisibility(View.GONE);
                    mCover.setVisibility(View.GONE);
                    break;
            }
        }
    };
}
