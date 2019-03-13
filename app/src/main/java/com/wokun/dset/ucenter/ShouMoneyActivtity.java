package com.wokun.dset.ucenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.DsetApp;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseBindingActivity;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.home.FindDsyueActivity;
import com.wokun.dset.login.LoginActivity;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.ucenter.bean.MyBean;
import com.wokun.dset.ucenter.bean.RollInBean;
import com.wokun.dset.utils.ImageLoader;
import com.wokun.dset.utils.QRCodeUtil;
import com.wokun.dset.utils.StringUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

public class ShouMoneyActivtity extends BaseBindingActivity {
    private static final int SAVE_SUCCESS = 0;//保存图片成功
    private static final int SAVE_FAILURE = 1;//保存图片失败
    private static final int SAVE_BEGIN = 2;//开始保存图片
    @BindView( R.id.erweima_im)
    ImageView erweima_im;
    @BindView( R.id.erweima_save)
    TextView erweima_save;

    @Override
    public int createView() {
        return R.layout.activity_shou_money_activtity;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("收付款");
    }

    @Override
    public void init() {

          loadData();
         erweima_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bitmap image = ((BitmapDrawable)erweima_im.getDrawable()).getBitmap();
                saveImageToPhotos(ShouMoneyActivtity.this, image  );
            }
        });


    }

    private void loadData() {
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);
        OkGo.<BaseResponse<RollInBean>>post(Constants.BASE_URL + Constants.ROLL_IN)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .execute(new JsonCallback<BaseResponse<RollInBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<RollInBean>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        Log.e("首页", "进来了2!!!!");
                        if (body.getStatus().equals("0001")) {
                            RxToast.showShort(body.getMessage());
                            RollInBean    shoufumoney = (RollInBean) body.getData();
                            Log.e("首页2", shoufumoney + "!!!!");
                            if (shoufumoney == null) {
                                return;
                            }
                            Log.e("二维码",""+shoufumoney.getQrcode_str());
                            Bitmap mBitmap = QRCodeUtil.createQRCodeBitmap(shoufumoney.getQrcode_str()+"", 180, 180);
                         //   Bitmap mBitmap = QRCodeUtil.createQRCodeBitmap(, 180, 180);


                            erweima_im.setImageBitmap(mBitmap);

                        } else if (body.getStatus().equals("0002")) {
                            RxToast.showShort(body.getMessage());
                            startActivity(LoginActivity.class);
                        } else if (body.getStatus().equals("0003")) {
                            RxToast.showShort(body.getMessage());
                            startActivity(LoginActivity.class);

                        }
                    }

                    @Override
                    public void onError(Response response) {
                        super.onError(response);
                        Log.e("首页3",response+"!!!!");
                        DsetApp.getInstance().setRefreshShopCart(false);
                    }
                });
    }




    /**
     * 保存二维码到本地相册
     */
    private void saveImageToPhotos(Context context, Bitmap bmp) {
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            mHandler.obtainMessage(SAVE_FAILURE).sendToTarget();
            return;
        }
        // 最后通知图库更新
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        context.sendBroadcast(intent);
        mHandler.obtainMessage(SAVE_SUCCESS).sendToTarget();
    }
    /**
     * 将URL转化成bitmap形式
     *
     * @param url
     * @return bitmap type
     */
    public final static Bitmap returnBitMap(String url) {
        URL myFileUrl;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(url);
            HttpURLConnection conn;
            conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SAVE_BEGIN:
                    RxToast.showShort("开始保存图片...");
                    erweima_im.setClickable(false);
                    break;
                case SAVE_SUCCESS:
                    RxToast.showShort("图片保存成功,请到相册查找");
                    erweima_im.setClickable(true);
                    break;
                case SAVE_FAILURE:
                    RxToast.showShort("图片保存失败,请稍后再试...");
                    erweima_im.setClickable(true);
                    break;
            }
        }
    };


}
