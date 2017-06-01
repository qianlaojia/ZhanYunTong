package com.dsgj.youyuntong.activity.mine;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dsgj.youyuntong.R;
import com.dsgj.youyuntong.Utils.SPUtils;
import com.dsgj.youyuntong.Utils.log.LogUtils;
import com.dsgj.youyuntong.base.BaseActivity;
import com.dsgj.youyuntong.widget.MyPopuWindow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.R.attr.path;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class PersonalInformationActivity extends BaseActivity {


    private TextView mMiddleText;
    private RelativeLayout mRLHeadImage;
    private CircleImageView mHeadImage;
    private MyPopuWindow mMyPopuWindow;
    private View mBlack;
    private Uri mImageUri;
    public static final int TAKE_PHOTO = 1;
    public static final int TAKE_ALBUM = 2;
    private RelativeLayout mBack;
    private Bitmap mHeadImageFromCamera;
    private TextView mNickName;
    private TextView mRealName;
    private TextView mPhone;
    private TextView mEmail;
    private TextView mID;
    private RelativeLayout mRlnickName;
    private RelativeLayout mRlName;
    private RelativeLayout mRlPhone;
    private RelativeLayout mRlEmail;
    private RelativeLayout mAddress;
    private TextView mAlreadyAddress;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_personal_information;
    }

    @Override
    protected void initView() {
        mBack = (RelativeLayout) findViewById(R.id.rl_title_back);
        mMiddleText = (TextView) findViewById(R.id.tv_middle_text);
        mRLHeadImage = (RelativeLayout) findViewById(R.id.rl_personal_information_head_portrait);
        mHeadImage = (CircleImageView) findViewById(R.id.civ_setting_head_image);
        mBlack = findViewById(R.id.view);
        mNickName = (TextView) findViewById(R.id.tv_setting_nickname);
        mRealName = (TextView) findViewById(R.id.tv_setting_name);
        mPhone = (TextView) findViewById(R.id.tv_setting_phone);
        mEmail = (TextView) findViewById(R.id.tv_setting_email);
        mRlnickName = (RelativeLayout) findViewById(R.id.rl_personal_information_nickname);
        mRlName = (RelativeLayout) findViewById(R.id.rl_personal_information_name);
        mRlPhone = (RelativeLayout) findViewById(R.id.rl_personal_information_phone);
        mRlEmail = (RelativeLayout) findViewById(R.id.rl_personal_information_email);
        mAddress = (RelativeLayout) findViewById(R.id.rl_personal_information_address);
        mAlreadyAddress = (TextView) findViewById(R.id.tv_setting_address);

    }

    @Override
    protected void initData() {
       /* if (SPUtils.with(this).contains(this, "headImage")) {
            String headImagePath = SPUtils.with(this).get("headImage", "");
            Uri headImageUri = UriUtils.getUriFromFilePath(headImagePath);
            mHeadImage.setImageURI(headImageUri);
        } else {*/
        mHeadImage.setImageResource(R.mipmap.morentouxiang);
        // }
        mMiddleText.setText("个人资料");
        mNickName.setText(SPUtils.with(this).get("nickName", "未设置"));
        mRealName.setText(SPUtils.with(this).get("realName", "未设置"));
        mPhone.setText(SPUtils.with(this).get("userName", "未设置"));
        mEmail.setText(SPUtils.with(this).get("userEmail", "未设置"));
        mAlreadyAddress.setText(SPUtils.with(this).get("province", "未设置")
                + "." + SPUtils.with(this).get("city", "")
                + "." + SPUtils.with(this).get("district", "")
                + "." + SPUtils.with(this).get("addressDetail", ""));

    }

    @Override
    protected void initListener() {
        mBack.setOnClickListener(this);
        mRLHeadImage.setOnClickListener(this);
        mRlName.setOnClickListener(this);
        mRlEmail.setOnClickListener(this);
        mRlPhone.setOnClickListener(this);
        mRlnickName.setOnClickListener(this);
        mAddress.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_title_back:
                finish();
                break;
            case R.id.rl_personal_information_head_portrait:
                mMyPopuWindow = new MyPopuWindow(this, mOnClickListener);
                mBlack.setVisibility(View.VISIBLE);
                //设置出现的位置和方式
                mMyPopuWindow.showAtLocation(this.findViewById(R.id.ll_person_information), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                //控制透明变暗的view和popuWindow同步消失
                mMyPopuWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        mBlack.setVisibility(View.GONE);
                    }
                });
                break;
            case R.id.rl_personal_information_nickname://昵称
                Map<String, String> map = new HashMap<>();
                map.put("style", "nickName");
                jumpToActivity(EditPersonalInfoActivity.class, map);
                break;
            case R.id.rl_personal_information_name://姓名
                Map<String, String> map1 = new HashMap<>();
                map1.put("style", "name");
                jumpToActivity(EditPersonalInfoActivity.class, map1);
                break;
            case R.id.rl_personal_information_phone://电话
                jumpToActivity(ModifyPhoneActivity.class);
                break;
            case R.id.rl_personal_information_email://邮箱
                Map<String, String> map3 = new HashMap<>();
                map3.put("style", "email");
                jumpToActivity(EditPersonalInfoActivity.class, map3);
                break;
            case R.id.rl_personal_information_address://常用地址
                Map<String, String> map4 = new HashMap<>();
                map4.put("style", "address");
                jumpToActivity(EditPersonalInfoActivity.class, map4);
                break;

            default:
                break;
        }

    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_from_camera:
                    CameraGetImage();//调用相机去拍照同时显示
                    break;
                case R.id.tv_from_album:
                    AlbumGetImage();//调用手机的相册获得头像

                    break;
                case R.id.tv_cancel:
                    mBlack.setVisibility(View.GONE);
                    mMyPopuWindow.dismiss();
                    break;
                default:
                    break;
            }

        }
    };

    /**
     * 调用手机的相册获取头像；
     */
    private void AlbumGetImage() {
        if (ContextCompat.checkSelfPermission(PersonalInformationActivity.this, Manifest
                .permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(PersonalInformationActivity.this
                    , new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        } else {
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.setType("*image/*");
            startActivityForResult(intent, TAKE_ALBUM);

        }
    }


    private void CameraGetImage() {
        //创建文件对象用来存储拍照后的图片
        File outPutImage = new File(getExternalCacheDir(), "head_image");

        try {
            if (outPutImage.exists()) {
                outPutImage.delete();
            }
            outPutImage.createNewFile();
            LogUtils.e(outPutImage.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 24) {

            mImageUri = FileProvider.getUriForFile(this, "com.dsgj.zhanyuntong.fileprovider", outPutImage);

        } else {
            mImageUri = Uri.fromFile(outPutImage);
        }
        //启动相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);//知识传递一下图片的uri
        startActivityForResult(intent, TAKE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    mMyPopuWindow.dismiss();
                    mBlack.setVisibility(View.GONE);
                    try {
                        mHeadImageFromCamera = BitmapFactory
                                .decodeStream(getContentResolver()
                                        .openInputStream(mImageUri));
                        mHeadImage.setImageBitmap(mHeadImageFromCamera);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    saveImageToGallery(this, mHeadImageFromCamera);

                }
                break;
            case TAKE_ALBUM:
                break;
        }
    }

    public static void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE
                , Uri.parse("file://" + path)));
    }

}
