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
import com.dsgj.youyuntong.JavaBean.GroupTour.InCountryBean;
import com.dsgj.youyuntong.R;
import com.google.gson.Gson;

import java.util.List;

/**
 * TODO:
 * Created by 张云浩  on 2017/6/4.
 * 邮箱：943332771@qq.com
 */

public class InCountryAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<InCountryBean.ResultBean.ProductListBean> mProductListBeanList;

    /**
     * ItemClick的回调接口
     *
     * @author 张云浩
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private InCountryAdapter.OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(InCountryAdapter
                                               .OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public InCountryAdapter(Context context
            , List<InCountryBean.ResultBean.ProductListBean> mProductListBeanList) {
        this.mContext = context;
        this.mProductListBeanList = mProductListBeanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_group_trip_around, null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        viewHolder viewHolder = (viewHolder) holder;
        viewHolder.name.setText(mProductListBeanList.get(position).getTitle());
        viewHolder.introduce.setText(mProductListBeanList.get(position).getSummary());
        viewHolder.mFromCity.setText(mProductListBeanList.get(position).getCity());
        viewHolder.price.setText("¥" + mProductListBeanList.get(position).getPrice());
        viewHolder.type.setText(mProductListBeanList.get(position).getCategory());
        Gson gson = new Gson();
        AroundTripImageBean aroundTripImageBean = gson.fromJson(mProductListBeanList.get(position).getSmeta()
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
        return mProductListBeanList.size();
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
