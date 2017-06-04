package com.dsgj.youyuntong.adapter.GroupTrip;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.dsgj.youyuntong.R;

/**
 * TODO:
 * Created by 张云浩  on 2017/6/4.
 * 邮箱：943332771@qq.com
 */

public class GroupTripRecyclerViewAdapter extends RecyclerView.Adapter {
    private Context mContext;

    /**
     * ItemClick的回调接口
     *
     * @author 张云浩
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private GroupTripRecyclerViewAdapter.OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(GroupTripRecyclerViewAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public GroupTripRecyclerViewAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_group_trip_around, null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder( final RecyclerView.ViewHolder holder, final int position) {

        //如果设置了回调，则设置点击事件
        if (mOnItemClickListener != null)
        {
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
        return 6;
    }

    private class viewHolder extends RecyclerView.ViewHolder {
        public viewHolder(View view) {
            super(view);


        }
    }

}
