package com.dsgj.youyuntong.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.Utils.view.XBannerUtils;
import com.dsgj.youyuntong.activity.GroupTourActivity;
import com.dsgj.youyuntong.activity.ThroughTrain.ThroughTrainActivity;
import com.dsgj.youyuntong.activity.ThroughTrainInquiryActivity;
import com.dsgj.youyuntong.activity.TicketActivity;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;
import java.util.Map;


/**
 * HomeFragment中recycle的适配器；
 * Created by 张云浩 on 2017/4/24.
 */

public class HomePageRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //上下文
    private Context mContext;
    //轮播图
    private List<String> mImageList;
    //四个图片按钮
    private List<Map<String, Object>> mGridViewList;
    //常规的item
    private List<String> normalList;
    private final int X_BANNER_VIEW_TYPE = 0;//轮播图
    private final int FOUR_SELECT_VIEW_TYPE = 2;//四个选项的按钮
    private final int SEARCH_VIEW_TYPE = 1;//选择的item
    private final int MIDDLE_TITLE_VIEW_TYPE = 3;//中间的猜你喜欢item
    private final int NORMAL_VIEW_TYPE = 4;

    //构造方法获得相关的参数：
    public HomePageRecyclerViewAdapter(Context context
            , List<String> mImageList
            , List<Map<String, Object>> mGridViewList
            , List<String> normalList) {
        this.mContext = context;
        this.mImageList = mImageList;
        this.mGridViewList = mGridViewList;
        this.normalList = normalList;
    }

    /**
     * 设置item的类型和位置
     *
     * @param position 这个是recyclerView的位置
     * @return 返回的是要显示的类型的标志
     */
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return X_BANNER_VIEW_TYPE;//轮播图
        } else if (position == 1) {
            return SEARCH_VIEW_TYPE;//查询的item
        } else if (position == 2) {
            return FOUR_SELECT_VIEW_TYPE;//四个按钮
        } else if (position == 3) {
            return MIDDLE_TITLE_VIEW_TYPE;//图标
        } else
            return NORMAL_VIEW_TYPE;//剩下的都是布局相同的

    }


    /**
     * 返回不同的holder，由于这是不同的，根据不同的item的类型进行返回holder
     *
     * @param viewType 就是getItemViewType返回的type
     * @return 自己创建的viewHolder
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;//根据类型的不同创建不同的viewHolder
        if (viewType == X_BANNER_VIEW_TYPE) {
            view = View.inflate(mContext, R.layout.item_xbannar, null);
            return new XBannerHolder(view);
        } else if (viewType == SEARCH_VIEW_TYPE) {
            view = View.inflate(mContext, R.layout.item_home_search, null);
            return new SearchHolder(view);
        } else if (viewType == FOUR_SELECT_VIEW_TYPE) {
            view = View.inflate(mContext, R.layout.item_ll_four_select, null);
            return new FourSelectHolder(view);
        } else if (viewType == MIDDLE_TITLE_VIEW_TYPE) {//这个是布局
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

    /**
     * 绑定viewHolder
     *
     * @param holder   holder
     * @param position 位置
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //强制禁止复用
        holder.setIsRecyclable(false);
        if (holder instanceof XBannerHolder) {
            //这是绑定轮播框的
            XBanner xbanner = ((XBannerHolder) holder).banner;
            XBannerUtils.setBannerHolder(mContext, xbanner);
        } else if (holder instanceof SearchHolder) {
            SearchHolder Holder = (SearchHolder) holder;
            setSearch(Holder.linearlayout);
        } else if (holder instanceof FourSelectHolder) {
            FourSelectHolder fourselectholder = (FourSelectHolder) holder;
            setFourSelect(fourselectholder);
        } else if (holder instanceof MiddleTitleHolder) {
            MiddleTitleHolder middletitleholder = (MiddleTitleHolder) holder;
        } else if (holder instanceof NormalHolder) {//正常布局
            NormalHolder normalHolder = (NormalHolder) holder;

        }
    }

    /**
     * 设置个数要显示的总共要显示的item的个数
     *
     * @return 一共要显示的个数
     */
    @Override
    public int getItemCount() {
        return normalList.size() + 4;
    }

    /**
     * 轮播图的ViewHolder
     */
    private static class XBannerHolder extends RecyclerView.ViewHolder {
        XBanner banner;

        XBannerHolder(View itemView) {
            super(itemView);
            banner = (XBanner) itemView.findViewById(R.id.XBanner);
        }
    }

    /**
     * 四个gridView
     */
    private static class FourSelectHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;

        FourSelectHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_channel);

        }
    }

    /**
     * 正常布局的ViewHolder
     */
    private static class NormalHolder extends RecyclerView.ViewHolder {
        RelativeLayout mRelativeLayout;

        NormalHolder(View itemView) {
            super(itemView);
            mRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.home_item_RelativeLayout);
        }
    }

    /**
     * 查询直通车的viewHolder
     */
    private static class SearchHolder extends RecyclerView.ViewHolder {
        LinearLayout linearlayout;


        SearchHolder(View itemView) {
            super(itemView);
            linearlayout = (LinearLayout) itemView.findViewById(R.id.ll_home_search);
        }
    }

    /**
     * 中间的那个猜你喜欢
     */
    private class MiddleTitleHolder extends RecyclerView.ViewHolder {
        RelativeLayout mRelativeLayout;

        MiddleTitleHolder(View itemView) {
            super(itemView);
            mRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.home_middle_title);


        }

    }

    private void setFourSelect(FourSelectHolder holder) {
        //整个过程是配置一个LinearLayout
        for (int i = 0; i < mGridViewList.size(); i++) {
            //初始化view
            View view = View.inflate(mContext, R.layout.item_four_select, null);
            ImageView imageview = (ImageView) view.findViewById(R.id.iv_logo);
            TextView textView = (TextView) view.findViewById(R.id.tv_explain);
            //加载图片
            Glide.with(mContext)
                    .load(mGridViewList.get(i).get("pic"))
                    .into(imageview);
            //加载图片下的说明
            textView.setText(mGridViewList.get(i).get("title").toString());
            //布局设置一下
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,//设置宽
                    LinearLayout.LayoutParams.WRAP_CONTENT);//设置高
            view.setLayoutParams(params);
            params.setMargins(9, 18, 9, 0);
            params.gravity = Gravity.CENTER;
            view.setTag(i);
            final int finalI = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (finalI == 0) {
                        //门票
                        Intent intent = new Intent(mContext, TicketActivity.class);
                        mContext.startActivity(intent);
                    } else if (finalI == 1) {
                        //直通车
                        Intent intent1 = new Intent(mContext, ThroughTrainActivity.class);
                        mContext.startActivity(intent1);
                    } else if (finalI == 2) {
                        //跟团游
                        Intent intent2 = new Intent(mContext, GroupTourActivity.class);
                        mContext.startActivity(intent2);
                    } else
                        //导游
                        ToastUtils.show(mContext, "功能正在开发中，敬请期待！");

                }
            });

            holder.linearLayout.addView(view);
        }
    }

    private void setSearch(LinearLayout view) {
        TextView mBusTicket = (TextView) view.findViewById(R.id.bus_ticket);
        TextView fromLocation = (TextView) view.findViewById(R.id.from_location);
        TextView toLocation = (TextView) view.findViewById(R.id.to_location);
        TextView goOfTime = (TextView) view.findViewById(R.id.go_of_time);
        Button queryButton = (Button) view.findViewById(R.id.btn_home_query);
        mBusTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ToastUtils.show(mContext, "跳转到客车票查询Activity");

            }

        });
        fromLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(mContext, "出发位置被点击");
            }
        });
        toLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(mContext, "目的地被点击");
            }
        });
        goOfTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(mContext, "出发时间被点击");
            }
        });
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ThroughTrainInquiryActivity.class);
                mContext.startActivity(intent);
            }
        });

    }


}
