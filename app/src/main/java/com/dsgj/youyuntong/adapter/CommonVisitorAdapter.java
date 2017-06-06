package com.dsgj.youyuntong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.adapter.ThroughTrain.ThroughTrainQueryResultRVAdapter;

import static com.dsgj.youyuntong.R.mipmap.changyongchengke_weixuanze;

/**
 * TODO:
 * Created by 张云浩  on 2017/6/6.
 * 邮箱：943332771@qq.com
 */

public class CommonVisitorAdapter extends RecyclerView.Adapter {
    private Context mContext;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private CommonVisitorAdapter.OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(CommonVisitorAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public CommonVisitorAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_common_visitor, null);
        return new CommonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final CommonViewHolder commonViewHolder = (CommonViewHolder) holder;
        commonViewHolder.nameText.setText("张云浩");
        if (mOnItemClickListener != null) {
            commonViewHolder.chooseImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick( commonViewHolder.chooseImageView, position);
                   // commonViewHolder.chooseImageView.setImageResource(R.mipmap.changyongchengke_gouxuan);

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    private class CommonViewHolder extends RecyclerView.ViewHolder {
        TextView nameText;
        ImageView chooseImageView;

        public CommonViewHolder(View itemView) {
            super(itemView);
            nameText = (TextView) itemView.findViewById(R.id.tv_item_common_visitor_name_details);
            chooseImageView = (ImageView) itemView.findViewById(R.id.iv_common_visitor_item_choose);
        }
    }
}
