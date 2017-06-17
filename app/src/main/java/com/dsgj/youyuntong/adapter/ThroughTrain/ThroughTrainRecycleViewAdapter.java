package com.dsgj.youyuntong.adapter.ThroughTrain;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dsgj.youyuntong.R;

import java.util.List;

/**
 * TODO:
 * Created by 张云浩  on 2017/6/2.
 * 邮箱：943332771@qq.com
 */

public class ThroughTrainRecycleViewAdapter extends
        RecyclerView.Adapter<ThroughTrainRecycleViewAdapter.ViewHolder> {
    /**
     * ItemClick的回调接口
     *
     * @author
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private LayoutInflater mInflater;
    private List<String> image;
    private List<String> name;
    private List<String> price;
    private Activity mActivity;

    public ThroughTrainRecycleViewAdapter(Activity context, List<String> image, List<String> name, List<String> price) {
        mInflater = LayoutInflater.from(context);
        this.image = image;
        this.name = name;
        this.price = price;
        this.mActivity = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        ImageView mImg;
        TextView name;
        TextView price;
    }

    @Override
    public int getItemCount() {
        return image.size();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_though_train_rv_hot_spots,
                viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.mImg = (ImageView) view
                .findViewById(R.id.iv_though_train_rv_image);
        viewHolder.name = (TextView) view
                .findViewById(R.id.tv_though_train_rv_text);
        viewHolder.price = (TextView) view
                .findViewById(R.id.tv_though_train_rv_price);

        return viewHolder;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        Glide.with(mActivity).load(image.get(i)).into(viewHolder.mImg);
        viewHolder.name.setText(name.get(i));
        viewHolder.price.setText(price.get(i));
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
