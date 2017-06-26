package com.dsgj.youyuntong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsgj.youyuntong.R;

import java.util.List;

/**
 * TODO:
 * Created by 张云浩  on 2017/6/6.
 * 邮箱：943332771@qq.com
 */

public class CommonVisitorAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<String> newVisitorName;
    private List<String> newVisitorID;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private CommonVisitorAdapter.OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(CommonVisitorAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public CommonVisitorAdapter(Context context, List<String> newVisitorName, List<String> newVisitorID) {
        this.mContext = context;
        this.newVisitorID = newVisitorID;
        this.newVisitorName = newVisitorName;
    }

    public void removeItem(int position) {
        newVisitorName.remove(newVisitorName.size() - 1 - position);
        newVisitorID.remove(newVisitorName.size() - 1 - position);
        notifyItemRemoved(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_common_visitor, null);
        return new CommonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final CommonViewHolder commonViewHolder = (CommonViewHolder) holder;
        commonViewHolder.nameText.setText(newVisitorName.get(newVisitorName.size() - 1 - position));
        commonViewHolder.idText.setText(newVisitorID.get(newVisitorName.size() - 1 - position));
        commonViewHolder.chooseImageView.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return newVisitorName.size();
    }

    private class CommonViewHolder extends RecyclerView.ViewHolder {
        TextView nameText;
        TextView idText;
        ImageView chooseImageView;

        private CommonViewHolder(final View itemView) {
            super(itemView);
            final int[] state = {1};
            nameText = (TextView) itemView.findViewById(R.id.tv_item_common_visitor_name_details);
            idText = (TextView) itemView.findViewById(R.id.tv_item_common_visitor_id_details);
            chooseImageView = (ImageView) itemView.findViewById(R.id.iv_common_visitor_item_choose);
            chooseImageView.setImageResource(R.mipmap.changyongchengke_weixuanze);
            if (mOnItemClickListener != null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (state[0] == 1) {
                            mOnItemClickListener.onItemClick(itemView, getAdapterPosition());
                            chooseImageView.setImageResource(R.mipmap.changyongchengke_gouxuan);
                            state[0] = 2;
                        } else if (state[0] == 2) {
                            mOnItemClickListener.onItemClick(itemView, getAdapterPosition());
                            chooseImageView.setImageResource(R.mipmap.changyongchengke_weixuanze);
                            state[0] = 1;
                        }

                    }
                });
            }


        }
    }
}
