package com.wokun.dset.store.dstore.dstoredetail;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.wokun.dset.BaseActivity;
import com.wokun.dset.DsetApp;
import com.wokun.dset.R;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.store.bean.DStoreGoodsDetail;
import com.wokun.dset.utils.ImageLoadUtils;
import com.wokun.dset.utils.ImageLoaderUtils;
import com.wokun.dset.utils.JosnFrom;
import com.wokun.dset.utils.SpCommonUtils;
import com.wokun.dset.utils.StringUtil;
import com.wokun.dset.utils.TextViewUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

public class DStoreDetailActivity extends BaseActivity {

    private String goods_id;
    private TextView goods_name;
    private TextView price;
    private TextView free_send;
    private TextView search_goods_size;
    private ImageView store_logo;
    private TextView store_detail_point;
    private TextView store_attitude_point;
    private TextView store_send_point;
    private WebView webView;
    private Banner mBanner;
    private TextView search_textview;
    private TextView store_name;


    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        if (mBanner != null)
            mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        if (mBanner != null)
            mBanner.stopAutoPlay();
        Glide.with(this).pauseRequests();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Glide.with(this).resumeRequests();
    }

    @Override
    public void onDestroy() {
        if (mBanner != null)
            mBanner.removeAllViews();
//        Glide.with(this).onDestroy();
        Glide.get(context).clearMemory();

        super.onDestroy();
    }

    @Override
    public int setActivityLayout() {
        return R.layout.activity_goods_infoes;
    }

    @Override
    public void initView() {
        super.initView();
        search_textview = findViewById(R.id.search_textview);
        mBanner = findViewById(R.id.banner);
        goods_name = findViewById(R.id.goods_name);
        price = findViewById(R.id.price);
        free_send = findViewById(R.id.free_send);
        search_goods_size = findViewById(R.id.search_goods_size);

        store_logo = findViewById(R.id.store_logo);
        store_name = findViewById(R.id.store_name);
        store_detail_point = findViewById(R.id.store_detail_point);
        //态度
        store_attitude_point = findViewById(R.id.store_attitude_point);
        store_send_point = findViewById(R.id.store_send_point);
        webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        Intent intent = getIntent();
        goods_id = intent.getStringExtra("goods_id");
    }


    private void detailData(DStoreGoodsDetail goodsDetail) {
        if (goodsDetail == null || goodsDetail.getData() == null) return;
        search_textview.setText(goodsDetail.getData().getGoods_name());
        price.setText(goodsDetail.getData().getPrice());
        free_send.setText("运费：" + goodsDetail.getData().getFreight());

        if (goodsDetail.getData().getShop_id().equals("0")) {
            goods_name.setText(TextViewUtil.setSpanBgAndTvColor("自营" + goodsDetail.getData().getGoods_name(), 0, 2, "#ffffff", "#056198"));
        } else {
            goods_name.setText(goodsDetail.getData().getGoods_name());
        }

        if (goodsDetail.getData().getShow_img().size() >= 1) {
            startBar(mBanner, (ArrayList) goodsDetail.getData().getShow_img());
        } else {
            mBanner.setVisibility(View.GONE);
        }
        ImageLoaderUtils.load(context, store_logo, goodsDetail.getData().getShop().getShop_logo(), 0);
        store_name.setText(goodsDetail.getData().getShop().getShop_name());


        String desccredit = goodsDetail.getData().getShop().getShop_desccredit();
        store_detail_point.setText(TextViewUtil.setSpanColor(context, "描述相符 " + desccredit, 5, 5 + desccredit.length(), "#f08619", null));
        String servicecredit = goodsDetail.getData().getShop().getShop_servicecredit();
        store_attitude_point.setText(TextViewUtil.setSpanColor(context, "服务态度 " + servicecredit, 5, 5 + servicecredit.length(), "#f08619", null));
        String deliverycredit = goodsDetail.getData().getShop().getShop_deliverycredit();
        store_send_point.setText(TextViewUtil.setSpanColor(context, "发货速度 " + deliverycredit, 5, 5 + deliverycredit.length(), "#f08619", null));
        if (goodsDetail.getData().getDescription_url() == null) return;
        webView.loadUrl(goodsDetail.getData().getDescription_url());
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

    }


    @Override
    public void initData() {
        super.initData();
        getStoreInfo();
    }

    private void getStoreInfo() {
        String token = (String) SpCommonUtils.get(DStoreDetailActivity.this, Constants.TOKEN, "");
        String user_id = (String) SpCommonUtils.get(DStoreDetailActivity.this, Constants.USERID, "");

        Map params = new HashMap();
        params.put("goods_id", goods_id);
        params.put("user_id", user_id);
        params.put("token", token);
        params.put("timestamp", StringUtil.getCurrentTime());
        final Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);

        OkGo.<JsonObject>post(Constants.BASE_URL + Constants.STORE_GOODS_DETAIL)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .params("user_id", user_id)
                .params("token", token)
                .params("goods_id", goods_id)
                .execute(new JsonCallback<JsonObject>() {
                    @Override
                    public void onSuccess(Response<JsonObject> response) {
                        DStoreGoodsDetail goodesDetail = (DStoreGoodsDetail) JosnFrom.getInstance().getObj(response.body().toString(), DStoreGoodsDetail.class);
                        if (goodesDetail != null && goodesDetail.getStatus().equals("0001")) {
                            detailData(goodesDetail);
                        }
                    }

                    @Override
                    public void onError(Response response) {
//                        dismissLP();
                        super.onError(response);
                        Log.e("user", response + "!!!!");
                        DsetApp.getInstance().setRefreshShopCart(false);
                    }
                });


    }

    public void startBar(Banner banner, ArrayList bannerImageArray) {
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new ImageLoadUtils());
        //设置图片集合
        banner.setImages(bannerImageArray);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.RotateDown);
        //设置标题集合（当banner样式有显示title时）
        //  banner.setBannerTitles(adTitle);
        //设置轮播时间
        banner.setDelayTime(Integer.MAX_VALUE);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }
//
//    private LinearLayout popCancel;
//    private TextView popConfirm;
//    private ProdutInfoBean produtInfoBean;
//
//    private void showChoiceSizePop(View v) {
//        if (produtInfoBean == null) return;
//
//        // 一个自定义的布局，作为显示的内容
//        View contentView = LayoutInflater.from(context).inflate(
//                R.layout.choice_size, null);
//        // 设置按钮的点击事件
////        popColor = contentView.findViewById(R.id.choice_size_color);
////        popSize = contentView.findViewById(R.id.choice_size_size);
//
//        popCancel = contentView.findViewById(R.id.choice_size_cancel); //退出
//        popConfirm = contentView.findViewById(R.id.choice_size_confirm); //确认加入
//        RecyclerView recyclerView = contentView.findViewById(R.id.recyclerView); //确认加入
////        popBuyNum = contentView.findViewById(R.id.choice_size_num);
//
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
//
//        recyclerView.setLayoutManager(layoutManager);
//        // 当我们确定Item的改变不会影响RecyclerView的宽高的时候可以设置
//        recyclerView.setHasFixedSize(false);
//        SelectGoogsParamAdapter   selectGoogsParamAdapter = new SelectGoogsParamAdapter(array, MbGoodsDetailActivity.this);
//        recyclerView.setAdapter(selectGoogsParamAdapter);
//        selectGoogsParamAdapter.getData().addAll(produtInfoBean.getData().getSkuList());
////        selectGoogsParamAdapter.notifyDataSetChanged();
//
//        popCancel.setOnClickListener(this);
//        popConfirm.setOnClickListener(this);
//
//
////        GoodsColorAdapter colorAdapter = new GoodsColorAdapter(MbGoodsDetailActivity.this, popColor, mColorData);
////        GoodsSizeAdapter sizeAdapter = new GoodsSizeAdapter(MbGoodsDetailActivity.this, popSize, mSizeData);
////        popColor.setAdapter(colorAdapter);
////        popSize.setAdapter(sizeAdapter);
////
////        popColor.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
////            @Override
////            public boolean onTagClick(View view, int position, FlowLayout parent) {
////                colorIndex = position;
////                return false;
////            }
////        });
////        popSize.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
////            @Override
////            public boolean onTagClick(View view, int position, FlowLayout parent) {
////                sizeIndex = position;
////                return false;
////            }
////        });
//
//
//        Display display = getWindowManager().getDefaultDisplay();
//        popupWindow = new PopupWindow(contentView,
//                GridLayout.LayoutParams.MATCH_PARENT, display.getHeight() * 2 / 3, true);
//
//        popupWindow.setTouchable(true);
//
//        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                Log.i("mengdd", "onTouch : ");
//
//                return false;
//                // 这里如果返回true的话，touch事件将被拦截
//                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
//            }
//        });
//
//        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
//        // 我觉得这里是API的一个bug
////        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
//
//        // 设置好参数之后再show
//        popupWindow.showAtLocation(buyCar, Gravity.BOTTOM, 0, 0);
//
//    }

}
