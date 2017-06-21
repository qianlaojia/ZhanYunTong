package com.dsgj.youyuntong.activity;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.CalendarView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.SPUtils;
import com.dsgj.youyuntong.base.BaseActivity;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;


public class CalendarActivity extends BaseActivity {
    private RelativeLayout mRlTitleBack;
    private TextView mTvMiddleText;
    private CalendarView mCalendarView;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_calendar;
    }

    @Override
    protected void initView() {
        mRlTitleBack = (RelativeLayout) findViewById(R.id.rl_title_back);
        mTvMiddleText = (TextView) findViewById(R.id.tv_middle_text);

    }

    @Override
    protected void initData() {
        mTvMiddleText.setText("选择日期");
        mRlTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mCalendarView = (CalendarView) findViewById(R.id.calendar_view);
        mCalendarView.setMinDate(System.currentTimeMillis() - 1000);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                int Month = month + 1;
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = df.parse(year + "-" + Month + "-" + dayOfMonth);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                long timestamp = cal.getTimeInMillis();
                SPUtils.with(CalendarActivity.this).save("selectedDateStamp", timestamp);
                SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd  EEEE");
                String finalDate = df2.format(date);
                SPUtils.with(CalendarActivity.this).save("selectedDate", finalDate);
                CalendarActivity.this.finish();
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
