package com.dsgj.zhanyuntong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.adapter.GroupTripRecycleViewAdapter;

import java.util.List;

/**
 * TODO:
 * Created by 张云浩  on 2017/6/2.
 * 邮箱：943332771@qq.com
 */

public class VerticalRecycleViewAdapter extends
        RecyclerView.Adapter<VerticalRecycleViewAdapter.ViewHolder>{
    /**
     * ItemClick的回调接口
     *
     * @author
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

    public VerticalRecycleViewAdapter(Context context, List<Integer> data) {
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
    public VerticalRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_though_train_rv_hot_spots,
                viewGroup, false);
        VerticalRecycleViewAdapter.ViewHolder viewHolder = new VerticalRecycleViewAdapter.ViewHolder(view);

        viewHolder.mImg = (ImageView) view
                .findViewById(R.id.iv_through_train_rv_image);
        return viewHolder;
    }



    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final VerticalRecycleViewAdapter.ViewHolder viewHolder, final int i) {
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
