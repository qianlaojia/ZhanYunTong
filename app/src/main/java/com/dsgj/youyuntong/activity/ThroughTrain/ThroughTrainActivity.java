package com.dsgj.youyuntong.activity.ThroughTrain;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.Utils.recyclerview.XBannerUtils;
import com.dsgj.youyuntong.Utils.view.GridViewTableLine;
import com.dsgj.youyuntong.Utils.view.ListViewForScrollView;
import com.dsgj.youyuntong.adapter.ThoughTrainRecycleViewAdapter;
import com.dsgj.youyuntong.base.BaseActivity;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThroughTrainActivity extends BaseActivity {


    private RelativeLayout mBack;
    private TextView mMiddleText;
    private XBanner mXBanner;
    private ListViewForScrollView mListView;

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
    private RecyclerView mRecyclerView;
    private ThoughTrainRecycleViewAdapter mMAdapter;
    private List<Integer> mMData;

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
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_tt_hot_spots);
    }

    @Override
    protected void initData() {
        RvImageAndText();//data
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器
        mMAdapter = new ThoughTrainRecycleViewAdapter(this, mMData);
        mMAdapter.setOnItemClickListener(new ThoughTrainRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtils.show(ThroughTrainActivity.this, "第" + position + "个被点击！");
            }
        });
        mRecyclerView.setAdapter(mMAdapter);
        XBannerUtils.setBannerHolder(this, mXBanner);
        mMiddleText.setText("直通车");
        mListView.setAdapter(new MyAdapter());
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.show(ThroughTrainActivity.this, "第" + position + "被点击！！！");
            }
        });
    }

    private void RvImageAndText() {
        mMData = new ArrayList<>(Arrays.asList(R.mipmap.shilitu,
                R.mipmap.shilitu, R.mipmap.shilitu,
                R.mipmap.shilitu, R.mipmap.shilitu,
                R.mipmap.shilitu, R.mipmap.shilitu,
                R.mipmap.shilitu, R.mipmap.shilitu));
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
