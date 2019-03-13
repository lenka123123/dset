package com.wokun.dset.ucenter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.dialog.loaddialog.LoadDialog;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.shantoo.widget.view.OnPhotoUpLoadListener;
import com.shantoo.widget.view.PhotoSelector;
import com.wokun.dset.DsetApp;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseBindingActivity;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.ucenter.bean.PostPictureBean;
import com.wokun.dset.utils.ImageLoader;
import com.wokun.dset.utils.StringUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

public class PersonalInfoActivity extends BaseBindingActivity {
    private PhotoSelector mPhotoSelector;
    private   String  src;
    private    String sign;
    @BindView(R.id.iv_image_header) ImageView ivImageHeader; //用户头像

    @Override
    public int createView() {
        return R.layout.activity_personal_info;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("修改头像");
    }

    @Override
    public void init() {
        ImageLoader.loadImage(DsetApp.getInstance().getUser().getHead(),ivImageHeader);
        mPhotoSelector = new PhotoSelector(this);
        mPhotoSelector.setOnPhotoUpLoadListener(new OnPhotoUpLoadListener() {
            @Override
            public void onPhotoUpLoad(ImageView photoImage, File photoFile) {
                upLoadPicture(photoFile);
              //  LoginMgr.getInstance().upLoadPicture(PersonalInfoActivity.this,photoFile);
            }
        });

    }


    /**
     * 头像上传
     * */
    public void upLoadPicture( File file){
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
          sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
        OkGo.<BaseResponse<PostPictureBean>>post(Constants.BASE_URL + Constants.POST_PICTURE)
                .tag(this)
                .params("sign",sign)
                .params("timestamp",StringUtil.getCurrentTime())
                .params("file", file) 	// 支持多文件同时添加上传
                .execute(new JsonCallback<BaseResponse<PostPictureBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<PostPictureBean>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        RxToast.showShort(body.getMessage());
                        if(body.getStatus().equals("0001")){
                            PostPictureBean    postpic = (PostPictureBean) body.getData();
                            Log.e("图片上传","图片上传了"+body.getMessage());
                             src = postpic.getSrc();
                            RxToast.showShort(body.getMessage());
                            loadData(src);
                        }
                    }

                    @Override
                    public void onError(Response<BaseResponse<PostPictureBean>> response) {
                        super.onError(response);

                        RxToast.showShort("文件上传出错");
                    }
                });


    }

    private void loadData(String src) {
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        params.put("filename", src);
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
         String sign2 = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);

        OkGo.<BaseResponse<PostPictureBean>>post(Constants.BASE_URL + Constants.CHANGE_HEAD)
                .tag(this)
                .params("sign",sign2)
                .params("timestamp",StringUtil.getCurrentTime())
                .params("filename", src) 	// 支持多文件同时添加上传
                .execute(new JsonCallback<BaseResponse<PostPictureBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<PostPictureBean>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        RxToast.showShort(body.getMessage());
                        if(body.getStatus().equals("0001")){
                            Log.e("图片上传","图片上传了"+body.getMessage());
                            RxToast.showShort(body.getMessage());
                        }
                    }

                    @Override
                    public void onError(Response<BaseResponse<PostPictureBean>> response) {
                        super.onError(response);
                        //    LoadDialog.dismiss(activity);
                        RxToast.showShort("文件上传出错");
                    }
                });
    }


    @OnClick({R.id.action_edit_user_head_img})
    public void action(View view) {
        if (R.id.action_edit_user_head_img == view.getId()) {//修改用户头像
            mPhotoSelector.showView(ivImageHeader);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPhotoSelector.onActivityResult(requestCode, resultCode, data);
    }

}
