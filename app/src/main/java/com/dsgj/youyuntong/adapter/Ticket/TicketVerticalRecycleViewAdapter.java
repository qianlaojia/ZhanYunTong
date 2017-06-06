package com.dsgj.youyuntong.adapter.Ticket;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.adapter.GroupTrip.GroupTripRecycleViewAdapter;

import java.util.List;

/**
 * TODO:
 * Created by 张云浩  on 2017/6/2.
 * 邮箱：943332771@qq.com
 */

public class TicketVerticalRecycleViewAdapter extends
        RecyclerView.Adapter<TicketVerticalRecycleViewAdapter.ViewHolder> {
    /**
     * ItemClick的回调接口
     *
     * @author 张云浩
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private GroupTripRecycleViewAdapter.OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(GroupTripRecycleViewAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private LayoutInflater mInflater;
    private List<Integer> mData;

    public TicketVerticalRecycleViewAdapter(Context context, List<Integer> data) {
        mInflater = LayoutInflater.from(context);
        mData = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        ImageView mImg;
        TextView mTxt;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public TicketVerticalRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_ticket_rv_ticket,
                viewGroup, false);
        TicketVerticalRecycleViewAdapter.ViewHolder viewHolder = new TicketVerticalRecycleViewAdapter.ViewHolder(view);

        viewHolder.mImg = (ImageView) view
                .findViewById(R.id.iv_ticket_vrv_item);
        return viewHolder;
    }


    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final TicketVerticalRecycleViewAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.mImg.setImageResource(mData.get(i));
        //如果设置了回调，则设置点击事件
        if (mOnItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(viewHolder.itemView, i);
                }
            });
        }
    }
}