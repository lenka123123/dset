package com.wokun.dset.ucenter.quanyi.dashishop.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itheima.roundedimageview.RoundedImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.view.OnPhotoUpLoadListener;
import com.shantoo.widget.view.PhotoSelector;
import com.wokun.dset.R;
import com.wokun.dset.base.SimpleRefreshAndLoadMoreFragment;
import com.wokun.dset.base.SimpleRefreshAndLoadMoreFragment4;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.ucenter.bean.DsytDindanBean;
import com.wokun.dset.ucenter.bean.PostPictureBean;
import com.wokun.dset.ucenter.bean.TradeOrderListBean;
import com.wokun.dset.ucenter.myyijian.fragment.MyBean;
import com.wokun.dset.ucenter.quanyi.dashishop.PaydetailsActivity;
import com.wokun.dset.ucenter.quanyi.dashishop.adapter.Dsytchangealladapter;
import com.wokun.dset.ucenter.quanyi.dashishop.bean.GoodsOrderBean;
import com.wokun.dset.utils.ImageLoader;
import com.wokun.dset.utils.StringUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_CANCELED;
import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

public class GoodsOrderchuli extends SimpleRefreshAndLoadMoreFragment4<TradeOrderListBean> {

    private Dsytchangealladapter mAdapter;
    private       TradeOrderListBean bean;
    private  String src;
    public Request initRequest() {
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        params.put("page", "1");
        params.put("status", "qr");
        params.put("page_size", "10");
        params.put("order_type", "1");
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
        return OkGo.<BaseResponse<DsytDindanBean>>post(Constants.BASE_URL + Constants.TRADEORDER)
                .tag(this)
                .params("sign", sign)
                .params("status", "qr")
                .params("order_type", "1")
                .params("page", "1")
                .params("timestamp",  StringUtil.getCurrentTime())
                .params("page_size", "10");
    }

    @Override
    public BaseQuickAdapter<TradeOrderListBean, BaseViewHolder> initAdapter() {
        mAdapter = new Dsytchangealladapter(R.layout.item_dashi_all,  null);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                bean = (TradeOrderListBean) adapter.getData().get(position);
                if(R.id.action_order_detail == view.getId()){//订单详情
                    Log.e("订单详情11","订单详情11");
                    Intent intent = new Intent(getContext(), PaydetailsActivity.class);
                    intent.putExtra("id",bean.getId());
                    startActivity(intent);
                }else if(R.id.action_cancel_order == view.getId()){//取消订单
                    LoginMgr.getInstance().cancelDindan(bean.getId());
                }else if(R.id.action_sure_order == view.getId()){//确认收款
                    querenDialog();
                }else if(R.id.action_sure_myorder == view.getId()){//确认付款
                    diaolog();
                }

            }
        });

        return mAdapter;
    }
       private ImageView shangchuan_img;
    private void diaolog() {
        AlertDialog.Builder customizeDialog = new AlertDialog.Builder(getContext());
        View dialogView = LayoutInflater.from(getContext())
                .inflate(R.layout.pop_fukuan, null);
        customizeDialog.setView(dialogView);
        final AlertDialog myDialog = customizeDialog.show();
        ImageView delete_pop = (ImageView) dialogView.findViewById(R.id.delete_pop);
         shangchuan_img = (ImageView) dialogView.findViewById(R.id.shangchuan_img);


        TextView comit_txt = (TextView) dialogView.findViewById(R.id.comit_txt);

        delete_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });

        shangchuan_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onViewClicked();
            }
        });
        comit_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                commitData();
                myDialog.dismiss();
            }
        });


    }

    private void onViewClicked() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
       // builder.setIcon(R.drawable.ic_launcher_background);
        builder.setTitle("选择图片上传方式");
        //    指定下拉列表的显示数据
        final String[] cities = {"从相册中获取", "拍照"};
        //    设置一个下拉的列表选择项
        builder.setItems(cities, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            //    Toast.makeText(HeadActivity.this, "选择的方式为：" + cities[which], Toast.LENGTH_SHORT).show();

                if (cities[which].equals("从相册中获取")) {
                    Intent picture = new Intent(
                            Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(picture, 101);
                } else if (cities[which].equals("拍照")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 99);
                }

            }
        });
        builder.show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 99:
                if (resultCode == RESULT_CANCELED) {
                    RxToast.showShort("取消了拍照");
                    return;
                }
                Bitmap photo = data.getParcelableExtra("data");
                shangchuan_img.setImageBitmap(photo);
                upLoadPicture( compressImage(photo));
                break;
            case 101:
                Log.e("图片相册","图片相册");
                if (requestCode == 101 && resultCode == Activity.RESULT_OK && null != data) {
                    //获取图片路径
                        Uri selectedImage = data.getData();
                        String[] filePathColumns = {MediaStore.Images.Media.DATA};

                        Cursor c = getActivity().getContentResolver().query(selectedImage, filePathColumns, null, null, null);
                        c.moveToFirst();
                        int columnIndex = c.getColumnIndex(filePathColumns[0]);
                        String imagePath = c.getString(columnIndex);
                        showImage(imagePath);
                        c.close();
                    }


                break;

        }


    }

    public static File compressImage(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 500) {  //循环判断如果压缩后图片是否大于500kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            options -= 10;//每次都减少10
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            long length = baos.toByteArray().length;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date(System.currentTimeMillis());
        //图片名
       String   filename = format.format(date);

        File file = new File(Environment.getExternalStorageDirectory(), filename + ".png");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            try {
                fos.write(baos.toByteArray());
                fos.flush();
                fos.close();
            } catch (IOException e) {

                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

   //     Log.d(TAG, "compressImage: " + file);
        // recycleBitmap(bitmap);
        return file;
    }

    private void showImage(String imagePath) {
        Log.e("图片相册","图片相册2");
        Bitmap bm = BitmapFactory.decodeFile(imagePath);
        shangchuan_img.setImageBitmap(bm);

        upLoadPicture(compressImage(bm));
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
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
    private void commitData() {

        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        params.put("id", bean.getId());
        params.put("src", src);
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String    sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.BUYCONFIRM)
                .tag(this)
                .params("sign",sign)
                .params("timestamp",StringUtil.getCurrentTime())
                .params("id",bean.getId())
                .params("src", src)
                .execute(new JsonCallback<BaseResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        RxToast.showShort(body.getMessage());
                        if(body.getStatus().equals("0001")){

                            RxToast.showShort(body.getMessage());

                        }
                    }

                    @Override
                    public void onError(Response<BaseResponse<Object>> response) {
                        super.onError(response);

                        RxToast.showShort("文件上传出错");
                    }
                });







    }

    private void querenDialog() {
        AlertDialog.Builder customizeDialog = new AlertDialog.Builder(getContext());
        View dialogView = LayoutInflater.from(getContext())
                .inflate(R.layout.pop_queren_fukuan, null);
        customizeDialog.setView(dialogView);
        final AlertDialog myDialog = customizeDialog.show();
        ImageView delete_pop_sure = (ImageView) dialogView.findViewById(R.id.delete_pop_sure);
        final ImageView sure_img = (ImageView) dialogView.findViewById(R.id.sure_img);
        ImageLoader.loadImage(bean.getPicture(),sure_img);
        TextView comit_txt_sure = (TextView) dialogView.findViewById(R.id.comit_txt_sure);

        delete_pop_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });

        comit_txt_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comitsure();
                myDialog.dismiss();
            }
        });

    }

    private void comitsure() {
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        params.put("id", bean.getId());
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String    sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.SELLCONFIRM)
                .tag(this)
                .params("sign",sign)
                .params("timestamp",StringUtil.getCurrentTime())
                .params("id",bean.getId())
                .execute(new JsonCallback<BaseResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        RxToast.showShort(body.getMessage());
                        if(body.getStatus().equals("0001")){
                            RxToast.showShort(body.getMessage());
                        }
                    }

                    @Override
                    public void onError(Response<BaseResponse<Object>> response) {
                        super.onError(response);
                        RxToast.showShort("出错");
                    }
                });


    }




    @Override
    public void loadData(final boolean isRefresh) {
        mRequest.execute(new JsonCallback<BaseResponse<DsytDindanBean>>() {
            @Override
            public void onSuccess(Response<BaseResponse<DsytDindanBean>> response) {
                BaseResponse body = response.body();
                if(body == null)return;
                if(body.getStatus().equals("0001")){
                    DsytDindanBean data = (DsytDindanBean) body.getData();
                    if(data==null){return;}
                    List<TradeOrderListBean> list = data.getTradeOrderList();
                    setData(isRefresh, list);
                    initData();
                }

            }});
    }

    private void initData() {
     /*   if (!EventBus.getDefault().isRegistered(this))
        {
            EventBus.getDefault().register(this);
        }*/
        //  EventBus.getDefault().register(this);
        //   mMultiplePhotoSelector.setOnMultiplePhotoUpLoadListener(this);

  /*      mPhotoSelector.setOnPhotoUpLoadListener(new OnPhotoUpLoadListener() {
            @Override
            public void onPhotoUpLoad(ImageView photoImage, File photoFile) {
                upLoadPicture(photoFile);
                //  LoginMgr.getInstance().upLoadPicture(PersonalInfoActivity.this,photoFile);
            }
        });*/

    }
}