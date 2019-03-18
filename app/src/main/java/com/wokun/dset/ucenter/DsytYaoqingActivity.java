package com.wokun.dset.ucenter;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaActionSound;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v13.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseBindingActivity;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.ucenter.bean.PostPictureBean;
import com.wokun.dset.ucenter.bean.ShareCodeBean;
import com.wokun.dset.utils.ImageLoader;
import com.wokun.dset.utils.ImageUtils;
import com.wokun.dset.utils.QRCodeUtil;
import com.wokun.dset.utils.StringUtil;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

/**
 * Created by Administrator on 2018\11\28 0028.
 */

public class DsytYaoqingActivity extends BaseBindingActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.dsyt_yaoqin_img)
    ImageView mDsytYaoqinImg1;
    @BindView(R.id.dsyt_yaoqin_txt)
    TextView mDsytYaoqinTxt1;
    //  @BindView(R.id.yaoqin_share)ImageView yaoqin_share;
    @BindView(R.id.my_yaoqinma1)
    LinearLayout yaoqin_share;
    String picPath;
    //   private DsytyaoqinBean dsytyaoqinBean;
    private MediaActionSound mCameraSound;
    private Bitmap saveBitmap;
    private ImageView mDsytYaoqinImg;
    private TextView mDsytYaoqinTxt;

    @Override
    public int createView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

        return R.layout.activity_dsyt_yaoqin;
    }

    @Override
    public WidgetBar createToolBar() {
        mWidgetBar.setVisibility(View.GONE);
        return mWidgetBar;
    }

    @Override
    public void init() {

        loadData();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            return;
        }
        mCameraSound = new MediaActionSound();
        mCameraSound.load(MediaActionSound.SHUTTER_CLICK);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        loadData();
    }

    public void SharePhoto(String photoUri, final Activity activity) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        File file = new File(photoUri);
        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        shareIntent.setType("image/*");
        startActivity(Intent.createChooser(shareIntent, activity.getTitle()));
    }

    private void loadData() {

        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);

        OkGo.<BaseResponse<ShareCodeBean>>post(Constants.BASE_URL + Constants.SHARE_CODE)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .execute(new JsonCallback<BaseResponse<ShareCodeBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<ShareCodeBean>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        RxToast.showShort(body.getMessage());
                        if (body.getStatus().equals("0001")) {

                            RxToast.showShort(body.getMessage());

                            ShareCodeBean data = (ShareCodeBean) body.getData();
                            if (data == null) {
                                return;
                            }
                            Bitmap mBitmap11 = QRCodeUtil.createQRCodeBitmap(data.getShare_qrcode(), 97, 97);
                            Glide.with(DsytYaoqingActivity.this).load(mBitmap11).into(mDsytYaoqinImg1);
                            mDsytYaoqinTxt1.setText(data.getMycode());

                            Log.e("userInfo.getUserType()", "" + data.getShare_qrcode());
                            View v = LayoutInflater.from(DsytYaoqingActivity.this).inflate(R.layout.activity_dsyt_yaoqin, null, false);
                            mDsytYaoqinTxt = (TextView) v.findViewById(R.id.dsyt_yaoqin_txt);
                            mDsytYaoqinImg = (ImageView) v.findViewById(R.id.dsyt_yaoqin_img);
                            Bitmap mBitmap = QRCodeUtil.createQRCodeBitmap(data.getShare_qrcode(), 97, 97);
                            mDsytYaoqinImg.setImageBitmap(mBitmap);

                            mDsytYaoqinTxt.setText(data.getMycode());
                            //   Glide.with(DsytYaoqingActivity.this).load("http://api.tyitop.com/images/app_download.png").into(mDsytYaoqinImg);
                            //  ImageLoader.loadImage( data.getShare_qrcode(), mDsytYaoqinImg);
//                         mDsytYaoqinImg.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                Glide.with(DsytYaoqingActivity.this).load("http://api.tyitop.com/images/app_download.png").into(mDsytYaoqinImg);
//
//                            }
//                        });
                            DisplayMetrics metric = new DisplayMetrics();
                            getWindowManager().getDefaultDisplay().getMetrics(metric);
                            int width = metric.widthPixels;     // 屏幕宽度（像素）
                            int height = metric.heightPixels;   // 屏幕高度（像素）
                            ImageUtils.layoutView(v, width, height);
                            final ScrollView tv = (ScrollView) v.findViewById(R.id.my_yaoqinma);
                            final Runnable runnable = new Runnable() {
                                @Override
                                public void run() {
                                    picPath = ImageUtils.viewSaveToImage(tv, "makemone");
                                    //  Glide.with(DsytYaoqingActivity.this).load(picPath).into(mDsytYaoqinImg);
                                    Log.i("2333", picPath);
                                    Uri imageUri = Uri.fromFile(new File(picPath));
                                    Intent intent = new Intent();
                                    intent.setAction(Intent.ACTION_SEND);
                                    intent.putExtra(Intent.EXTRA_STREAM, imageUri);
                                    intent.setType("image/*");
                                    startActivity(Intent.createChooser(intent, "分享到 "));
                                }
                            };
                            yaoqin_share.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    picPath = ImageUtils.viewSaveToImage(tv, "makemone");
                                    //   Glide.with(DsytYaoqingActivity.this).load(picPath).into(mDsytYaoqinImg);
                                    new Handler().post(runnable);
                                    // new  Handler().postDelayed(runnable,1000);
                                }
                            });


                        }
                    }

                    @Override
                    public void onError(Response<BaseResponse<ShareCodeBean>> response) {
                        super.onError(response);
                        //    LoadDialog.dismiss(activity);
                        RxToast.showShort("文件上传出错");
                    }
                });

    }
}
