package com.wokun.dset.ucenter.myyijian.fragment;



import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.common.utils.Logger;
import com.shantoo.widget.dialog.loaddialog.LoadDialog;
import com.shantoo.widget.multiplephotoselector.OnMultiplePhotoUpLoadListener;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.view.OnPhotoUpLoadListener;
import com.shantoo.widget.view.PhotoSelector;
import com.wokun.dset.DsetApp;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseFragment;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.ucenter.bean.PostPictureBean;
import com.wokun.dset.ucenter.myyijian.adapter.ImageGridAdapter;
import com.wokun.dset.ucenter.myyijian.ui.MyMultiplePhotoSelector;
import com.wokun.dset.ucenter.myyijian.ui.MyyijianActivity;
import com.wokun.dset.utils.SignUtil;
import com.wokun.dset.utils.StringUtil;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

/**
 * Created by Administrator on 2018/7/9/009.
 */
//提交建议
public class SendMymessagefragment extends BaseFragment {
    private PhotoSelector mPhotoSelector;
   private  String src;
    @BindView(R.id.if_context)
    EditText ifContext;
    @BindView(R.id.yijian_img)
    ImageView yijian_img;

    private void initData() {
        EventBus.getDefault().register(this);
     //   mMultiplePhotoSelector.setOnMultiplePhotoUpLoadListener(this);
        mPhotoSelector = new PhotoSelector(getActivity());
        mPhotoSelector.setOnPhotoUpLoadListener(new OnPhotoUpLoadListener() {
            @Override
            public void onPhotoUpLoad(ImageView photoImage, File photoFile) {
                upLoadPicture(photoFile);
                //  LoginMgr.getInstance().upLoadPicture(PersonalInfoActivity.this,photoFile);
            }
        });

    }

/*

    */
/**
     * 单图片上传
     * */



    private void upLoadPicture(File file) {

            Map params = new HashMap();
            params.put("timestamp", StringUtil.getCurrentTime());
            Map<String, String> removeMap = removeEmptyData(params);
            Map<String, String> resultMap = sortMapByKey(removeMap);
             String    sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
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

                            }
                        }

                        @Override
                        public void onError(Response<BaseResponse<PostPictureBean>> response) {
                            super.onError(response);

                            RxToast.showShort("文件上传出错");
                        }
                    });

    }


    @Override
    public int createView() {
        return R.layout.fragment_my_sendmessage;
    }

    @Override
    public void initViews() {

    }


    @OnClick({R.id.yijian_img})
    public void action_yijian(View view) {
        if (R.id.yijian_img == view.getId()) {
            mPhotoSelector.showView(yijian_img);
        }
    }
    //提交图片
    @OnClick({R.id.action_submit})
    public void action_action_submit(View view) {
        if (R.id.action_submit == view.getId()) {//修改用户头像
            commitData();
        }
    }

    private void commitData() {
        String mifContext = ifContext.getText().toString().trim();
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        params.put("describe", mifContext);
        params.put("src", src);
        params.put("cs_type", "1");

        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String    sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.ADVICE_REQUEST)
                .tag(this)
                .params("sign",sign)
                .params("timestamp",StringUtil.getCurrentTime())
                .params("describe", mifContext)
                .params("src", src)
                .params("cs_type", "1")
                .execute(new JsonCallback<BaseResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        RxToast.showShort(body.getMessage());
                        if(body.getStatus().equals("0001")){
                            RxToast.showShort(body.getMessage());
                            startActivity(MyyijianActivity.class);
                              //  onFinish();
                        }
                    }

                    @Override
                    public void onError(Response<BaseResponse<Object>> response) {
                        super.onError(response);

                        RxToast.showShort("文件上传出错");
                    }
                });





    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void monooth(MyBean myBean){
    Log.e("图片进来12","图片进来2");
    mPhotoSelector.onActivityResult(myBean.requestCode, myBean.resultCode, myBean.data);
}
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPhotoSelector.onActivityResult(requestCode, resultCode, data);
        Log.e("图片进来2","图片进来2");
        mPhotoSelector.onActivityResult(requestCode, resultCode, data);
    }



    @Override
    public void loadData() {
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
