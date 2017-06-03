package com.dsgj.youyuntong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dsgj.youyuntong.R;

/**
 * TODO:
 * Created by 张云浩  on 2017/6/3.
 * 邮箱：943332771@qq.com
 */

public class StartLocationAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private String[] mStrings;
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private StartLocationAdapter.OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(StartLocationAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public StartLocationAdapter(Context context, String[] startCity) {
        this.mContext = context;
        this.mStrings = startCity;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_location_city, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((ViewHolder)holder).getTextView().setText(mStrings[position]);
     if(mOnItemClickListener!=null)  {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(holder.itemView,position);
            }
        });
     }
    }


    @Override
    public int getItemCount() {
        return mStrings.length;
    }

  public class ViewHolder extends RecyclerView.ViewHolder {
        TextView LocationCity;
        public ViewHolder(View itemView) {
            super(itemView);
            LocationCity = (TextView) itemView.findViewById(R.id.tv_location_city);
        }
      private TextView getTextView() {
          return LocationCity;
      }

    }
}
