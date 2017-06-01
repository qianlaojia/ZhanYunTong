package com.dsgj.youyuntong.activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.Utils.recyclerview.XBannerUtils;
import com.dsgj.youyuntong.Utils.view.GridViewOneLineRoll;
import com.dsgj.youyuntong.Utils.view.GridViewTableLine;
import com.dsgj.youyuntong.Utils.view.ListViewForScrollView;
import com.dsgj.youyuntong.base.BaseActivity;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.XBannerUtil;

public class ThroughTrainActivity extends BaseActivity {


    private RelativeLayout mBack;
    private TextView mMiddleText;
    private XBanner mXBanner;
    private ListViewForScrollView mListView;
    private ScrollView mScrollView;
    private GridViewTableLine mGridview;
    private String[] mMstrings = new String[]{"张三", "李四", "王五", "赵六", "李四", "王五", "赵六"
            , "李四", "王五", "赵六", "李四", "王五", "赵六", "李四"
            , "王五", "赵六", "李四", "王五", "赵六", "李四", "王五", "赵六"};
    private int[] mImages = new int[]{R.mipmap.shilitu, R.mipmap.shilitu, R.mipmap.shilitu
            , R.mipmap.shilitu, R.mipmap.shilitu, R.mipmap.shilitu, R.mipmap.shilitu
            , R.mipmap.shilitu, R.mipmap.shilitu, R.mipmap.shilitu, R.mipmap.shilitu
            , R.mipmap.shilitu, R.mipmap.shilitu, R.mipmap.shilitu, R.mipmap.shilitu
            , R.mipmap.shilitu, R.mipmap.shilitu, R.mipmap.shilitu, R.mipmap.shilitu
            , R.mipmap.shilitu, R.mipmap.shilitu, R.mipmap.shilitu};

    @Override
    protected int getLayoutID() {
        return R.layout.activity_through_train;
    }

    @Override
    protected void initView() {
        mBack = (RelativeLayout) findViewById(R.id.rl_title_back);
        mMiddleText = (TextView) findViewById(R.id.tv_middle_text);
        mXBanner = (XBanner) findViewById(R.id.XB_through_trip);
        mListView = (ListViewForScrollView) findViewById(R.id.lv_local_though_trip);
        mScrollView = (ScrollView) findViewById(R.id.sv_through_trip);
        mGridview = (GridViewTableLine) findViewById(R.id.gv_through_trip_hot_pots);
    }

    @Override
    protected void initData() {
        XBannerUtils.setBannerHolder(this, mXBanner);
        new GridViewOneLineRoll(ThroughTrainActivity.this, mGridview, mMstrings, mImages);
        mScrollView.smoothScrollTo(0, 0);
        mMiddleText.setText("直通车");
        mListView.setAdapter(new MyAdapter());
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.show(ThroughTrainActivity.this, "第" + position + "被点击！！！");
            }
        });
    }

    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_title_back:
                this.finish();
                break;
        }

    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            return View.inflate(ThroughTrainActivity.this, R.layout.item_through_train_listview, null);
        }
    }
}
