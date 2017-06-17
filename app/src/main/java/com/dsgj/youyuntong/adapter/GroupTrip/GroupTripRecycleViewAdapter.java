package com.dsgj.youyuntong.adapter.GroupTrip;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dsgj.youyuntong.JavaBean.GroupTour.GroupTourBean;
import com.dsgj.youyuntong.JavaBean.GroupTour.HotPotImageBean;
import com.dsgj.youyuntong.R;
import com.google.gson.Gson;

import java.util.List;

/**
 * TODO:
 * Created by 张云浩  on 2017/6/2.
 * 邮箱：943332771@qq.com
 */

public class GroupTripRecycleViewAdapter extends
        RecyclerView.Adapter<GroupTripRecycleViewAdapter.ViewHolder> {
    /**
     * ItemClick的回调接口
     *
     * @author 张云浩
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private Context mContext;
    private List<GroupTourBean.ResultBean.ScenicHotBean> mScenicHotBeen;

    public GroupTripRecycleViewAdapter(Activity context, List<GroupTourBean.ResultBean.ScenicHotBean> mScenicHotBeen) {
        this.mContext = context;
        this.mScenicHotBeen = mScenicHotBeen;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_group_trip_hot_pots);
            mTextView = (TextView) itemView.findViewById(R.id.tv_group_trip_hot_pots);
        }
    }

    @Override
    public int getItemCount() {
        return mScenicHotBeen.size();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext, R.layout.item_group_trip_hot_pots, null);
        return new ViewHolder(view);
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {

        Gson gson = new Gson();
        HotPotImageBean groupTourHotPotImageBean = gson.fromJson(mScenicHotBeen.get(i).getSmeta()
                , HotPotImageBean.class);
        Glide.with(mContext).load(groupTourHotPotImageBean.getThumb()).into(viewHolder.mImageView);
        viewHolder.mTextView.setText(mScenicHotBeen.get(i).getTitle());
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
