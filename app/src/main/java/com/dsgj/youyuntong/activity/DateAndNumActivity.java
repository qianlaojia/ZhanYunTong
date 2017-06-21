package com.dsgj.youyuntong.activity;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.SPUtils;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.base.BaseActivity;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class DateAndNumActivity extends BaseActivity {


    private RelativeLayout mBack;
    private TextView mMiddleText;
    private CalendarView mCalendar;
    private TextView mReduce;
    private TextView mAdd;
    private TextView mShow;
    private Button mNextStep;
    private String mMFinalTime;
    private String mSceneryName;
    private String mSceneryPrice;
    private String mStartCity;
    private int mA;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_date_and_num;
    }

    @Override
    protected void initView() {
        mBack = (RelativeLayout) findViewById(R.id.rl_title_back);
        mMiddleText = (TextView) findViewById(R.id.tv_middle_text);
        mCalendar = (CalendarView) findViewById(R.id.cv_date_number);
        mReduce = (TextView) findViewById(R.id.tv_reduce);
        mAdd = (TextView) findViewById(R.id.tv_add);
        mShow = (TextView) findViewById(R.id.tv_show_number);
        mNextStep = (Button) findViewById(R.id.Btn_next_step);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void initData() {
        mShow.setText(1 + "");
        GetDate();
        mMiddleText.setText("日期选择");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void GetDate() {
        Intent intent = getIntent();
        //上文获得的时间：
        String selectDate = intent.getStringExtra("selectTime");
        mSceneryName = intent.getStringExtra("sceneryName");
        mSceneryPrice = intent.getStringExtra("sceneryPrice");
        mStartCity = intent.getStringExtra("startLocation");
        if (selectDate.equals("")) {
            mCalendar.setDate(System.currentTimeMillis());
        } else {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = df.parse(selectDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            long timestamp = cal.getTimeInMillis();
            mCalendar.setDate(timestamp);
        }
        mMFinalTime = new SimpleDateFormat("yyyy-MM-dd").format(mCalendar.getDate());
        mCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                int Month = month + 1;
                mMFinalTime = year + "-" + Month + "-" + dayOfMonth;
            }
        });

    }

    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);
        mAdd.setOnClickListener(this);
        mReduce.setOnClickListener(this);
        mNextStep.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_title_back:
                this.finish();
                break;
            case R.id.tv_add:
                mShow.setText(Integer.valueOf(mShow.getText().toString()) + 1 + "");
                mReduce.setTextColor(Color.BLACK);
                break;
            case R.id.tv_reduce:
                if (mShow.getText().toString().equals("1")) {
                    mShow.setText(1 + "");
                    mReduce.setTextColor(Color.WHITE);
                } else {
                    mShow.setText(Integer.valueOf(mShow.getText().toString()) - 1 + "");
                    mReduce.setTextColor(Color.BLACK);
                }
                break;
            case R.id.Btn_next_step:
                Boolean isLogin = SPUtils.with(DateAndNumActivity.this)
                        .contains(DateAndNumActivity.this, "userName");
                if (isLogin) {
                    mA = Integer.parseInt(String.valueOf(mShow.getText()));
                    Intent intent = new Intent(this, OrderInputInfoActivity.class);
                    intent.putExtra("finalDate", mMFinalTime);
                    intent.putExtra("visitorCount", mA);
                    intent.putExtra("sceneryName", mSceneryName);
                    intent.putExtra("sceneryPrice", mSceneryPrice);
                    intent.putExtra("startLocation", mStartCity);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(this, LogOnAndRegisterActivity.class);
                    startActivity(intent);
                }

                break;
        }

    }
}
