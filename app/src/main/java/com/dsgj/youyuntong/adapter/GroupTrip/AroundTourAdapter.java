package com.dsgj.youyuntong.adapter.GroupTrip;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dsgj.youyuntong.JavaBean.GroupTour.AroundTripImageBean;
import com.dsgj.youyuntong.JavaBean.GroupTour.AroundTripBean;
import com.dsgj.youyuntong.R;
import com.google.gson.Gson;

import java.util.List;

/**
 * TODO:
 * Created by 张云浩  on 2017/6/4.
 * 邮箱：943332771@qq.com
 */

public class AroundTourAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<AroundTripBean.ResultBean.ProductListBean> mProductListBeen;

    /**
     * ItemClick的回调接口
     *
     * @author 张云浩
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private AroundTourAdapter.OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(AroundTourAdapter
                                               .OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public AroundTourAdapter(Context context
            , List<AroundTripBean.ResultBean.ProductListBean> mProductListBeen) {
        this.mContext = context;
        this.mProductListBeen = mProductListBeen;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_group_trip_around, null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        viewHolder viewHolder = (viewHolder) holder;
        viewHolder.name.setText(mProductListBeen.get(position).getTitle());
        viewHolder.introduce.setText(mProductListBeen.get(position).getSummary());
        viewHolder.mFromCity.setText(mProductListBeen.get(position).getCity());
        viewHolder.price.setText("¥" + mProductListBeen.get(position).getPrice());
        viewHolder.type.setText(mProductListBeen.get(position).getCategory());
        Gson gson = new Gson();
        AroundTripImageBean aroundTripImageBean = gson.fromJson(mProductListBeen.get(position).getSmeta()
                , AroundTripImageBean.class);
        Glide.with(mContext).load(aroundTripImageBean.getThumb()).into(viewHolder.mImage);
        //如果设置了回调，则设置点击事件
        if (mOnItemClickListener != null) {
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
        return mProductListBeen.size();
    }

    private class viewHolder extends RecyclerView.ViewHolder {

        public ImageView mImage;
        public TextView mFromCity;
        public TextView type;
        public TextView price;
        public TextView name;
        public TextView introduce;
        public RelativeLayout mRelativeLayout;

        public viewHolder(View view) {
            super(view);
            mRelativeLayout = (RelativeLayout) view.findViewById(R.id.rl_gta);
            mImage = (ImageView) view.findViewById(R.id.iv_group_trip_around_image);
            mFromCity = (TextView) view.findViewById(R.id.tv_gt_from_city);
            type = (TextView) view.findViewById(R.id.tv_gt_type);
            price = (TextView) view.findViewById(R.id.tv_scenic_rv_price);
            name = (TextView) view.findViewById(R.id.tv_gta_scenic_name);
            introduce = (TextView) view.findViewById(R.id.tv_gta_scenic_introduce);

        }
    }

}
