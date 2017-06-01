package com.dsgj.youyuntong.adapter;

import android.content.Context;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.activity.OrderDetailsActivity;

/**
 *订单文件的界面
 * Created by 张云浩  on 2017/5/3.
 *邮箱：943332771@qq.com
 */

public class OrderPagerRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    public OrderPagerRecyclerViewAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_order_fragment_bus_ticket, null);
        return new BusTicketHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BusTicketHolder busTicketHolder = (BusTicketHolder) holder;
        setBusTicketHolder(busTicketHolder, position);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    private class BusTicketHolder extends RecyclerView.ViewHolder {
        LinearLayout mLinearLayout;
        private BusTicketHolder(View view) {
            super(view);
            mLinearLayout = (LinearLayout) view.findViewById(R.id.ll_order_bus_ticket);
        }
    }

    private void setBusTicketHolder(BusTicketHolder itemViewHolder, final int position) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        itemViewHolder.mLinearLayout.setLayoutParams(params);
        params.setMargins(10, 10, 10, 10);
        itemViewHolder.mLinearLayout.findViewById(R.id.btn_bus_ticket_details).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, OrderDetailsActivity.class);
                mContext.startActivity(intent);

            }


        });
        itemViewHolder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, OrderDetailsActivity.class);
                mContext.startActivity(intent);
            }
        });
    }
}
