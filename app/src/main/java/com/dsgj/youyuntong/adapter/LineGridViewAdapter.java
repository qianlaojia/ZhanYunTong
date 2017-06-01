package com.dsgj.youyuntong.adapter;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dsgj.youyuntong.R;


public class LineGridViewAdapter extends BaseAdapter {
    private String[] mStrings;
    private Context mContext;

    public LineGridViewAdapter(Context contexts, String[] strings) {
        this.mStrings = strings;
        this.mContext = contexts;
    }

    @Override
    public int getCount() {
        return mStrings.length;
    }

    @Override
    public Object getItem(int position) {
        return mStrings[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.item_line_grid_view, null);
            holder.textview = (TextView) convertView.findViewById(R.id.tv_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (position == 0) {
            Drawable drawable = ContextCompat.getDrawable(mContext,R.mipmap.gentuanyou_n1);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            holder.textview.setCompoundDrawables(drawable, null, null, null);
        } else if (position == 1) {
            Drawable drawable = ContextCompat.getDrawable(mContext,R.mipmap.gentuanyou_n2);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            holder.textview.setCompoundDrawables(drawable, null, null, null);
        } else if (position == 2) {
            Drawable drawable = ContextCompat.getDrawable(mContext,R.mipmap.gentuanyou_n3);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            holder.textview.setCompoundDrawables(drawable, null, null, null);
        }
        holder.textview.setText(mStrings[position]);
        return convertView;
    }

    private class ViewHolder {
        private TextView textview;
    }

}

