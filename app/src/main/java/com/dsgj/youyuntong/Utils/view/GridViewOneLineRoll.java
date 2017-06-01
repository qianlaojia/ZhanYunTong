package com.dsgj.youyuntong.Utils.view;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 能够横向滑动的，GridView的工具类
 * Created 张云浩 on 2017/5/10.
 */

public class GridViewOneLineRoll {
    /**
     * 通过构造方法获得相关的数据；
     *
     * @param context  上下文
     * @param gridView GridView控件
     * @param strings 图片下显示的字符串
     * @param images  图片的集合
     */
    private String[] mStrings;
    private GridView mGridView;
    private int[] mImages;
    private Context mContext;
    private List<oneLineItem> mOnLineItemList;

    public GridViewOneLineRoll(Activity context, GridView gridView, String[] strings, int[] images) {

        this.mContext = context;
        this.mGridView = gridView;
        this.mImages = images;
        this.mStrings = strings;
        mOnLineItemList = new ArrayList<>();
        for (String mString : mStrings) {
            oneLineItem mOneLineItem = new oneLineItem();
            mOneLineItem.setText(mString);
            mOnLineItemList.add(mOneLineItem);
        }
        setGridView();
    }

    private void setGridView() {
        int size = mOnLineItemList.size();
        int length = 100;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridViewWidth = (int) (size * length * density);
        int itemWidth = (int) (length * density);

        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridViewWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        mGridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        mGridView.setColumnWidth(itemWidth); // 设置列表项宽
        mGridView.setHorizontalSpacing(GridView.AUTO_FIT); // 设置列表项水平间距
        //  mGridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        mGridView.setNumColumns(size); // 设置列数量=列表集合数
        mGridView.setAdapter(new MyGridViewAdapter());
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.show(mContext, "第" + position + "被点击！！！");
            }
        });
    }

    private WindowManager getWindowManager() {
        return (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
    }

    private class MyGridViewAdapter extends BaseAdapter {
        private static final String TAG = "ha";

        @Override
        public int getCount() {
            return mStrings.length;//返回个数
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layout = LayoutInflater.from(mContext);
            convertView = layout.inflate(R.layout.item_one_line_roll_gridview, null);
            ImageView mImageView = (ImageView) convertView.findViewById(R.id.iv_one_line);
            TextView mText = (TextView) convertView.findViewById(R.id.tv_one_line);
            oneLineItem item = mOnLineItemList.get(position);
            mImageView.setImageResource(mImages[position]);

            Log.e(TAG, "显示一些东西" + "现在的位置" + position + "字符" + item.getText());

            mText.setText(item.getText());
            return convertView;
        }


    }

    private class oneLineItem {
        private ImageView image;
        private String text;

        public ImageView getImage() {
            return image;
        }

        public void setImage(ImageView image) {
            this.image = image;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }


}
