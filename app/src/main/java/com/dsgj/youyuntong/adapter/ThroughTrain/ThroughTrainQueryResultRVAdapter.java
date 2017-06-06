package com.dsgj.youyuntong.adapter.ThroughTrain;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.adapter.GroupTrip.GroupTripRecycleViewAdapter;

/**
 * TODO:
 * Created by 张云浩  on 2017/6/5.
 * 邮箱：943332771@qq.com
 */

public class ThroughTrainQueryResultRVAdapter extends RecyclerView.Adapter {
    private Context mContext;
    public interface  OnItemClickListener{
        void onItemClick(View view, int position);
    }
    private  ThroughTrainQueryResultRVAdapter.OnItemClickListener  mOnItemClickListener;

    public void setOnItemClickListener(ThroughTrainQueryResultRVAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public ThroughTrainQueryResultRVAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_through_train_query_result, null);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        //强制禁止复用
        holder.setIsRecyclable(false);
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
        return 5;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);

        }
    }
}
