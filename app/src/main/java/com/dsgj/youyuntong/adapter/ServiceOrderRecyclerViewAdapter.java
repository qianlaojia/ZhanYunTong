package com.dsgj.youyuntong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.Utils.log.LogUtils;

/**
 * Created by zhangyunhao on 2017/5/2.
 */

public class ServiceOrderRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Context mContext;
    public int mCount;


    public ServiceOrderRecyclerViewAdapter(Context context, int count) {
        this.mContext = context;
        this.mCount = count;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_service_order_concult, null);
        return new ItemViewHolder(view);


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemviewholder = (ItemViewHolder) holder;
        setItemholder(itemviewholder);
    }

    private void setItemholder(ItemViewHolder itemviewholder) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT ,LinearLayout.LayoutParams.WRAP_CONTENT);
        itemviewholder.mLinearLayout.setLayoutParams(params);
        params.setMargins(10,10,10,10);

        itemviewholder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ToastUtils.show(mContext, v.getId() + "被点击");
            }
        });
    }

    @Override
    public int getItemCount() {
        LogUtils.e("收到显示的个数为：" + mCount);
        return 3;
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        LinearLayout mLinearLayout;

        public ItemViewHolder(View view) {
            super(view);
            mLinearLayout = (LinearLayout) view.findViewById(R.id.ll_service_order);

        }
    }
}
