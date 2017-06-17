package com.dsgj.youyuntong.adapter.Ticket;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dsgj.youyuntong.JavaBean.Ticket.TicketBean;
import com.dsgj.youyuntong.JavaBean.Ticket.TicketHotPotsImageBean;
import com.dsgj.youyuntong.R;
import com.google.gson.Gson;

import java.util.List;

/**
 * TODO:
 * Created by 张云浩  on 2017/6/2.
 * 邮箱：943332771@qq.com
 */

public class TicketRecycleViewAdapter extends
        RecyclerView.Adapter<TicketRecycleViewAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private LayoutInflater mInflater;
    private List<TicketBean.ResultBean.ScenicHotBean> mScenicHotBean;
    private Context mContext;

    public TicketRecycleViewAdapter(Context context, List<TicketBean.ResultBean.ScenicHotBean> mScenicHotBean) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mScenicHotBean = mScenicHotBean;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        ImageView mImg;
        TextView name;
        TextView prise;
    }

    @Override
    public int getItemCount() {
        return mScenicHotBean.size();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_ticket_rv_hot_spots,
                viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.mImg = (ImageView) view
                .findViewById(R.id.iv_ticket_rv_image);
        viewHolder.name = (TextView) view
                .findViewById(R.id.tv_ticket_rv_text);
        viewHolder.prise = (TextView) view
                .findViewById(R.id.tv_ticket_rv_price);

        return viewHolder;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        Gson gson = new Gson();
        Glide.with(mContext).load(gson.fromJson(mScenicHotBean.get(i).getSmeta(), TicketHotPotsImageBean.class).getThumb()).into(viewHolder.mImg);
        viewHolder.prise.setText("¥" + mScenicHotBean.get(i).getPrice());
        viewHolder.name.setText(mScenicHotBean.get(i).getTitle());
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

