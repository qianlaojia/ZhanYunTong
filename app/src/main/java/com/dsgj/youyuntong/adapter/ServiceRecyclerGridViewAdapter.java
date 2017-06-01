package com.dsgj.youyuntong.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dsgj.youyuntong.R;

/**
 * Created by zhangyunhao on 2017/4/28.
 */

public class ServiceRecyclerGridViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private int[] mImages;
    private String[] mStrings;

    private ImageView mImageView;
    private TextView mMText;


    public ServiceRecyclerGridViewAdapter(Context context, int[] images, String[] strings) {
        this.mContext = context;
        this.mImages = images;
        this.mStrings = strings;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_service_product_consult, null);
        return new ProductViewHolder(view);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProductViewHolder mViewHolder = (ProductViewHolder) holder;
        setViewHolder(mViewHolder, position);
    }


    @Override
    public int getItemCount() {
        return mImages.length;
    }

    private static class ProductViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout mRelativeLayout;

        public ProductViewHolder(View itemView) {
            super(itemView);
            mRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.rl_service_product);

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setViewHolder(ProductViewHolder mViewHolder, final int position) {
        mImageView = (ImageView) mViewHolder.mRelativeLayout.findViewById(R.id.iv_product);
        mMText = (TextView) mViewHolder.mRelativeLayout.findViewById(R.id.tv_product);

       mImageView.setImageResource(mImages[position]);
        mMText.setText(mStrings[position]);


        //布局设置一下
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mViewHolder.mRelativeLayout.setLayoutParams(params);
        params.setMargins(42, 16, 0, 11);
        mViewHolder.mRelativeLayout.setTag(position);
        //设置监听事件
        mViewHolder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, mStrings[position], Toast.LENGTH_SHORT).show();
            }
        });


    }
}
