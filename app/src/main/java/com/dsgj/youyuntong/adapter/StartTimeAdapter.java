package com.dsgj.youyuntong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dsgj.youyuntong.R;

import java.util.List;

/**
 * TODO:
 * Created by 张云浩  on 2017/6/3.
 * 邮箱：943332771@qq.com
 */

public class StartTimeAdapter extends RecyclerView.Adapter<StartTimeAdapter.MyHolder> {
    private Context mContext;
    private List<String> dates;
    private List<String> weeks;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private StartTimeAdapter.OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(StartTimeAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public StartTimeAdapter(Context context, List<String> date, List<String> week) {
        this.mContext = context;
        this.dates = date;
        this.weeks = week;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_start_time, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        holder.StartDate.setText(dates.get(position));
        holder.StartWeek.setText(weeks.get(position));


        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return 8;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView StartDate;
        TextView StartWeek;

        public MyHolder(View itemView) {
            super(itemView);
            StartDate = (TextView) itemView.findViewById(R.id.tv_start_time);
            StartWeek = (TextView) itemView.findViewById(R.id.tv_start_week);
        }


    }
}
