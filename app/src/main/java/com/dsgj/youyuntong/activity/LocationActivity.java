package com.dsgj.youyuntong.activity;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.activity.Search.SearchActivity;
import com.dsgj.youyuntong.base.BaseActivity;
import com.dsgj.youyuntong.adapter.StartLocationAdapter;

import java.util.HashMap;

public class LocationActivity extends BaseActivity {


    private ImageView mBack;
    private TextView mCurrentLocation;
    private RecyclerView mStartLocation;
    private String[] startLocation = {"郑州", "开封", "洛阳", "焦作", "新乡", "商丘", "许昌"
            , "濮阳", "平顶山", "南阳", "信阳", "安阳", "驻马店", "周口", "漯河", "鹤壁", "济源", "三门峡",};
    private TextView mSearch;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_location;
    }

    @Override
    protected void initView() {
        mBack = (ImageView) findViewById(R.id.iv_location_back);
        mCurrentLocation = (TextView) findViewById(R.id.tv_current_location);
        mStartLocation = (RecyclerView) findViewById(R.id.rv_start_location);
        mSearch = (TextView) findViewById(R.id.tv_Location_search);

    }

    @Override
    protected void initData() {
        mCurrentLocation.setText("河南郑州");
        //设置recyclerView的相关事项
        //配置布局方式GridView格式
        GridLayoutManager gridView = new GridLayoutManager(this, 4);//一行4个
        mStartLocation.setLayoutManager(gridView);
        StartLocationAdapter adapter = new StartLocationAdapter(this, startLocation);
        adapter.setOnItemClickListener(new StartLocationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtils.show(LocationActivity.this, "第" + startLocation[position] + "个被点击！");
            }
        });
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.TOP_DECORATION, 15);//top间距
        stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.BOTTOM_DECORATION, 15);//底部间距
        // stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.LEFT_DECORATION, 5);//左间距
        // stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.RIGHT_DECORATION, 5);//右间距
        mStartLocation.addItemDecoration(new RecyclerViewSpacesItemDecoration(stringIntegerHashMap));
        mStartLocation.setAdapter(adapter);


    }

    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);
        mSearch.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_location_back:
                this.finish();
                break;
            case R.id.tv_Location_search:
                jumpToActivity(SearchActivity.class);
                ToastUtils.show(LocationActivity.this, "跳转到搜索页面！");
                break;
        }

    }

    private class RecyclerViewSpacesItemDecoration extends RecyclerView.ItemDecoration {

        private HashMap<String, Integer> mSpaceValueMap;

        static final String TOP_DECORATION = "top_decoration";
        static final String BOTTOM_DECORATION = "bottom_decoration";
        static final String LEFT_DECORATION = "left_decoration";
        static final String RIGHT_DECORATION = "right_decoration";

        RecyclerViewSpacesItemDecoration(HashMap<String, Integer> mSpaceValueMap) {
            this.mSpaceValueMap = mSpaceValueMap;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            if (mSpaceValueMap.get(TOP_DECORATION) != null)
                outRect.top = mSpaceValueMap.get(TOP_DECORATION);
            if (mSpaceValueMap.get(LEFT_DECORATION) != null)

                outRect.left = mSpaceValueMap.get(LEFT_DECORATION);
            if (mSpaceValueMap.get(RIGHT_DECORATION) != null)
                outRect.right = mSpaceValueMap.get(RIGHT_DECORATION);
            if (mSpaceValueMap.get(BOTTOM_DECORATION) != null)
                outRect.bottom = mSpaceValueMap.get(BOTTOM_DECORATION);
        }
    }
}
