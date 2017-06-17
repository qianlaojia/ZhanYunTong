package com.dsgj.youyuntong.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dsgj.youyuntong.JavaBean.HomePage.HomePageBean;
import com.dsgj.youyuntong.JavaBean.HomePage.HomePagerImageUrlBean;
import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.activity.ProductDetailActivity;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;


/**
 * HomeFragment中recycle的适配器；
 * Created by 张云浩 on 2017/4/24.
 */

public class HomePageRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;


    private List<HomePageBean.ResultBean.ProductListBean> mProductListBeen;

    private final int MIDDLE_TITLE_VIEW_TYPE = 0;//中间的猜你喜欢item
    private final int NORMAL_VIEW_TYPE = 4;


    public HomePageRecyclerViewAdapter(Context context
            , List<Map<String, Object>> mGridViewList
            , List<HomePageBean.ResultBean.ProductListBean> productListBean) {
        this.mContext = context;
        this.mProductListBeen = productListBean;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return MIDDLE_TITLE_VIEW_TYPE;//图标
        } else
            return NORMAL_VIEW_TYPE;//剩下的都是布局相同的
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;//根据类型的不同创建不同的viewHolder
        if (viewType == MIDDLE_TITLE_VIEW_TYPE) {//这个是布局
            view = View.inflate(mContext, R.layout.item_homepage_middle_title, null);
            RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(params);
            params.setMargins(0, 9, 0, 0);
            return new MiddleTitleHolder(view);
        } else {
            view = View.inflate(mContext, R.layout.item_homepage_normal, null);
            RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(lp);
            return new NormalHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //强制禁止复用
        holder.setIsRecyclable(false);
        if (holder instanceof MiddleTitleHolder) {
            MiddleTitleHolder middletitleholder = (MiddleTitleHolder) holder;
        } else {//正常布局
            int i = position - 1;
            NormalHolder normalHolder = (NormalHolder) holder;
            normalHolder.Category.setText(mProductListBeen.get(i).getCategory());
            normalHolder.Name.setText(mProductListBeen.get(i).getTitle());
            normalHolder.introduce.setText(mProductListBeen.get(i).getSummary());
            normalHolder.price.setText("¥" + (int) mProductListBeen.get(i).getPrice());
            normalHolder.startLocation.setText(mProductListBeen.get(i).getCity());
            Gson gson = new Gson();
            HomePagerImageUrlBean urlBean = gson.fromJson(mProductListBeen.get(i).getSmeta(), HomePagerImageUrlBean.class);
            String url = urlBean.getThumb();
            Glide.with(mContext).load(url).into(normalHolder.backImage);
            normalHolder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String productId = mProductListBeen.get(position-1).getProduct_id();
                    String productCode = mProductListBeen.get(position-1).getProduct_code();
                    Intent intent = new Intent(mContext, ProductDetailActivity.class);
                    intent.putExtra("product_id", productId);
                    intent.putExtra("product_code", productCode);
                    mContext.startActivity(intent);

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mProductListBeen.size() + 1;
    }

    private static class NormalHolder extends RecyclerView.ViewHolder {
        RelativeLayout mRelativeLayout;
        TextView Category;
        TextView Name;
        ImageView backImage;
        TextView introduce;
        TextView price;
        TextView startLocation;

        NormalHolder(View itemView) {
            super(itemView);
            mRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.home_item_RelativeLayout);
            Category = (TextView) itemView.findViewById(R.id.tv_category);
            Name = (TextView) itemView.findViewById(R.id.tv_scenic_name);
            backImage = (ImageView) itemView.findViewById(R.id.iv_home_pager);
            introduce = (TextView) itemView.findViewById(R.id.tv_scenic_introduce);
            price = (TextView) itemView.findViewById(R.id.tv_home_pager_scenic_rv_price);
            startLocation = (TextView) itemView.findViewById(R.id.tv_start_location);
        }
    }

    private class MiddleTitleHolder extends RecyclerView.ViewHolder {
        RelativeLayout mRelativeLayout;

        MiddleTitleHolder(View itemView) {
            super(itemView);
            mRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.home_middle_title);


        }

    }


}
