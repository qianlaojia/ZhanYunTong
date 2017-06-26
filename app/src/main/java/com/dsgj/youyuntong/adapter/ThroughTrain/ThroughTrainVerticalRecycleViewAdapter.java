package com.dsgj.youyuntong.adapter.ThroughTrain;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dsgj.youyuntong.JavaBean.ThroughTrip.ThroughTripBean;
import com.dsgj.youyuntong.JavaBean.ThroughTrip.ThroughTripLocationImageBean;
import com.dsgj.youyuntong.R;
import com.google.gson.Gson;

import java.util.List;

/**
 * TODO:
 * Created by 张云浩  on 2017/6/2.
 * 邮箱：943332771@qq.com
 */

public class ThroughTrainVerticalRecycleViewAdapter extends
        RecyclerView.Adapter<ThroughTrainVerticalRecycleViewAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private LayoutInflater mInflater;
    private Activity mActivity;
    private List<ThroughTripBean.ResultBean.ProductListBean> mProduct;

    public ThroughTrainVerticalRecycleViewAdapter(Activity context, List<ThroughTripBean.ResultBean.ProductListBean> mProduct) {
        mInflater = LayoutInflater.from(context);
        this.mProduct = mProduct;
        this.mActivity = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        ImageView mImg;
        TextView mStart;
        TextView mEnd;
        TextView mPrice;
    }

    @Override
    public int getItemCount() {
        return mProduct.size();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_through_train_vertical_rv,
                viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.mImg = (ImageView) view
                .findViewById(R.id.iv_right_image);
        viewHolder.mStart = (TextView) view
                .findViewById(R.id.tv_though_trip_name);
        viewHolder.mEnd = (TextView) view
                .findViewById(R.id.tv_though_trip_introduce);
        viewHolder.mPrice = (TextView) view
                .findViewById(R.id.tv_though_trip_price);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        Gson gson = new Gson();
        ThroughTripLocationImageBean locationImageBean = gson.fromJson(mProduct.get(i).getSmeta()
                , ThroughTripLocationImageBean.class);

        Glide.with(mActivity).load(locationImageBean.getThumb()).into(viewHolder.mImg);
        viewHolder.mStart.setText(mProduct.get(i).getFrom_city());
        viewHolder.mEnd.setText(mProduct.get(i).getSummary());
        viewHolder.mPrice.setText("¥"+mProduct.get(i).getPrice());
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
