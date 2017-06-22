package com.dsgj.youyuntong.fragment.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.SPUtils;
import com.dsgj.youyuntong.Utils.ToastUtils;
import com.dsgj.youyuntong.activity.CommonVisitorActivity;
import com.dsgj.youyuntong.activity.LogOnAndRegisterActivity;
import com.dsgj.youyuntong.activity.Message.MessageActivity;
import com.dsgj.youyuntong.activity.mine.MineAllOrdersActivity;
import com.dsgj.youyuntong.activity.mine.MineSettingActivity;
import com.dsgj.youyuntong.activity.mine.MyCollectionActivity;
import com.dsgj.youyuntong.activity.mine.ScannerHistoryActivity;
import com.dsgj.youyuntong.activity.mine.WantCooperateActivity;
import com.dsgj.youyuntong.base.BaseFragment;
import com.jauker.widget.BadgeView;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 我的Fragment
 * Created by 张云浩  on 2017/4/19.
 * 邮箱：943332771@qq.com
 */

public class MineFragment extends BaseFragment {


    private RelativeLayout mSetting;
    private RelativeLayout mMineMessager;
    private CircleImageView mCircleImageView;
    private TextView mNickname;
    private TextView mAllOrder;
    private TextView mToBePaid;
    private TextView mWaitingTravel;
    private TextView mWaitingComment;
    private TextView mMyCollection;
    private TextView mCommonInformation;
    private TextView mBrowseHistory;
    private TextView mSafeSetting;
    private LinearLayout mMCooperation;
    private boolean mIsLogon;
    private String mURL;
    private String mMessage;
    private String mName;
    private BadgeView mMBadgeView;

    private ImageView mMineMessage;


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {
        mMBadgeView = new BadgeView(getActivity());
        //设置
        mMineMessage = (ImageView) view.findViewById(R.id.iv_mine_message);
        mSetting = (RelativeLayout) view.findViewById(R.id.mine_set);
        //消息
        mMineMessager = (RelativeLayout) view.findViewById(R.id.mine_my_message);
        //圆形头像
        mCircleImageView = (CircleImageView) view.findViewById(R.id.civ_head_image);
        //昵称
        mNickname = (TextView) view.findViewById(R.id.tv_nickname);
        //全部订单
        mAllOrder = (TextView) view.findViewById(R.id.tv_mine_all_order);
        //待支付
        mToBePaid = (TextView) view.findViewById(R.id.tv_mine_to_be_paid);
        //待出行
        mWaitingTravel = (TextView) view.findViewById(R.id.tv_mine_waiting_travel);
        //待点评
        mWaitingComment = (TextView) view.findViewById(R.id.tv_mine_waiting_comment);
        //我的收藏
        mMyCollection = (TextView) view.findViewById(R.id.tv_mine_my_collection);
        //常用信息
        mCommonInformation = (TextView) view.findViewById(R.id.tv_mine_common_information);
        //浏览历史
        mBrowseHistory = (TextView) view.findViewById(R.id.tv_mine_browse_history);
        //安全设置
        mSafeSetting = (TextView) view.findViewById(R.id.tv_mine_safe_setting);
        //我要合作
        mMCooperation = (LinearLayout) view.findViewById(R.id.cooperation);


    }


    @Override
    protected void initData() {

        mMBadgeView.setTargetView(mMineMessage);
        //判断是否登录：
        mIsLogon = SPUtils.with(getActivity()).get("IsLogoIn", false);
        if (mIsLogon) {
            mName = SPUtils.with(getActivity()).get("userName", "登录/注册");
            mNickname.setText(mName);
            mURL = SPUtils.with(getActivity()).get("HeadImageUrl", null);
            if (mURL == null) {
                mCircleImageView.setImageResource(R.mipmap.morentouxiang);
            } else {
                Glide.with(this).load(mURL).into(mCircleImageView);
            }
            mMessage = SPUtils.with(getActivity()).get("message_unread", "0");
            if (mMessage.equals("1")) {
                mMBadgeView.setBadgeCount(1);
            } else {
                mMBadgeView.setBadgeCount(0);
            }
        } else {
            mMBadgeView.setBadgeCount(0);
            mNickname.setText("登录/注册");
            mCircleImageView.setImageResource(R.mipmap.morentouxiang);
        }

    }

    @Override
    protected void initListener() {
        mSetting.setOnClickListener(this);
        mMineMessager.setOnClickListener(this);
        mCircleImageView.setOnClickListener(this);
        mNickname.setOnClickListener(this);
        mAllOrder.setOnClickListener(this);
        mToBePaid.setOnClickListener(this);
        mWaitingTravel.setOnClickListener(this);
        mWaitingComment.setOnClickListener(this);
        mMyCollection.setOnClickListener(this);
        mCommonInformation.setOnClickListener(this);
        mBrowseHistory.setOnClickListener(this);
        mSafeSetting.setOnClickListener(this);
        mMCooperation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_set:
                jumpToActivity(MineSettingActivity.class);
                break;
            case R.id.mine_my_message:
                //消息
                SPUtils.with(getActivity()).save("message_unread", "0");
                jumpToActivity(MessageActivity.class);
                break;
            case R.id.civ_head_image:
                //处理圆形头像的地方
                break;
            case R.id.tv_nickname:
                //登录
                if (!mIsLogon) {
                    jumpToActivity(LogOnAndRegisterActivity.class);
                }
                break;
            case R.id.tv_mine_all_order:
                //全部订单
                Map<String, String> allOrders = new HashMap<>();
                allOrders.put("style", "全部订单");
                jumpToActivity(MineAllOrdersActivity.class, allOrders);

                break;
            case R.id.tv_mine_to_be_paid:
                Map<String, String> toBeiPaid = new HashMap<>();
                toBeiPaid.put("style", "待支付");
                jumpToActivity(MineAllOrdersActivity.class, toBeiPaid);

                break;
            case R.id.tv_mine_waiting_comment:
                Map<String, String> waitingComment = new HashMap<>();
                waitingComment.put("style", "待点评");
                jumpToActivity(MineAllOrdersActivity.class, waitingComment);

                break;
            case R.id.tv_mine_waiting_travel:
                Map<String, String> waitingTravel = new HashMap<>();
                waitingTravel.put("style", "待出行");
                jumpToActivity(MineAllOrdersActivity.class, waitingTravel);

                break;
            case R.id.tv_mine_my_collection:
                jumpToActivity(MyCollectionActivity.class);

                break;
            case R.id.tv_mine_common_information:
                Map<String, String> map = new HashMap<>();
                map.put("type", "MineJump");
                jumpToActivity(CommonVisitorActivity.class,map);

                break;
            case R.id.tv_mine_browse_history:
                jumpToActivity(ScannerHistoryActivity.class);

                break;
            case R.id.tv_mine_safe_setting:
                // 实名的数据上传服务器及加密，保留本地加密数据；
                /*boolean RealNameOrNot = SPUtils.with(getActivity()).get("RealNameOrNot", false);
              //  SPUtils.with(getActivity()).remove("RealNameOrNot");
                Intent intent = new Intent(getActivity(), SecuritySettingActivity.class);
                intent.putExtra("RealNameOrNot", RealNameOrNot);
                getActivity().startActivity(intent);*/
                ToastUtils.show(getActivity(), "功能正在开发中，敬请期待");
                break;
            case R.id.cooperation:
                jumpToActivity(WantCooperateActivity.class);
                break;
        }
    }


}
