package com.dsgj.youyuntong.adapter.Ticket;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dsgj.youyuntong.JavaBean.Ticket.TicketBean;
import com.dsgj.youyuntong.JavaBean.Ticket.TicketImageBean;
import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.adapter.GroupTrip.GroupTripRecycleViewAdapter;
import com.google.gson.Gson;

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
    private List<TicketBean.ResultBean.ProductListBean> mMProductListBean;
    private Context mContext;

    public TicketVerticalRecycleViewAdapter(Activity context, List<TicketBean.ResultBean.ProductListBean> mMProductListBean) {
        mInflater = LayoutInflater.from(context);
        this.mMProductListBean = mMProductListBean;
        this.mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        ImageView mImg;
        TextView name;
        TextView introduce;
        TextView price;
    }

    @Override
    public int getItemCount() {
        return mMProductListBean.size();
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
        viewHolder.name = (TextView) view
                .findViewById(R.id.tv_ticket_vrv_item_name);
        viewHolder.introduce = (TextView) view
                .findViewById(R.id.tv_ticket_vrv_item_introduce);
        viewHolder.price = (TextView) view
                .findViewById(R.id.tv_ticket_vrv_item_price);

        return viewHolder;
    }


    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final TicketVerticalRecycleViewAdapter.ViewHolder viewHolder, final int i) {
        Gson gson = new Gson();
        TicketImageBean ticketImageBean = gson.fromJson(mMProductListBean.get(i).getSmeta(), TicketImageBean.class);
        Glide.with(mContext).load(ticketImageBean.getThumb()).into(viewHolder.mImg);
        viewHolder.name.setText(mMProductListBean.get(i).getTitle());
        viewHolder.introduce.setText(mMProductListBean.get(i).getSummary());
        viewHolder.price.setText("¥" + mMProductListBean.get(i).getPrice());
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
