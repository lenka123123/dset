package com.wokun.dset.store.dstore.dstoredetail;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.IInterface;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.itheima.loopviewpager.LoopViewPager;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.wokun.dset.BaseActivity;
import com.wokun.dset.DsetApp;
import com.wokun.dset.MainActivity;
import com.wokun.dset.R;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.home.DShopHomeActivity;
import com.wokun.dset.hudongshop.ShopDetailsActivity;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.store.adapter.SelectGoogsParamAdapter;
import com.wokun.dset.store.bean.CartList;
import com.wokun.dset.store.bean.DStoreGoodsDetail;
import com.wokun.dset.store.bean.DStoreImmediatelyPay;
import com.wokun.dset.store.bean.GoodsSKUList;
import com.wokun.dset.store.dstore.immediatelypay.DStoreImmediatelyPayActivity;
import com.wokun.dset.utils.ImageLoadUtils;
import com.wokun.dset.utils.ImageLoader;
import com.wokun.dset.utils.ImageLoaderUtils;
import com.wokun.dset.utils.ImageUtils;
import com.wokun.dset.utils.JosnFrom;
import com.wokun.dset.utils.ScreenUtils;
import com.wokun.dset.utils.SpCommonUtils;
import com.wokun.dset.utils.StringUtil;
import com.wokun.dset.utils.TextViewUtil;
import com.wokun.dset.utils.ToastUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import cn.iwgang.countdownview.CountdownView;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

public class DStoreDetailActivity extends BaseActivity implements View.OnClickListener {

    private String goods_id;
    private TextView goods_name;
    private TextView price;
    private TextView old_price;
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
    private List<DStoreGoodsDetail.DataBean.SpecListBean> listBean;
    private TextView action_buy_service;
    private String getGoods_spec_format;
    private DStoreGoodsDetail.DataBean dataBean;
    private List<GoodsSKUList> goodsSKUList;
    private String sku_id = "";
    private TextView pop_price;
    private TextView pop_store;
    private String promote_price = "";
    private boolean immediatelyPay = false;
    private LinearLayout price_linearlayout;
    private LoopViewPager loopViewPager;
    private TextView promotion_price;
    private CountdownView countdownView;


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
        findViewById(R.id.action_join_shopping_cart).setOnClickListener(this);
        action_buy_service = findViewById(R.id.action_buy_service);
        action_buy_service.setOnClickListener(this);

        search_textview = findViewById(R.id.search_textview);
        mBanner = findViewById(R.id.banner);
        goods_name = findViewById(R.id.goods_name);
        price = findViewById(R.id.price);
        old_price = findViewById(R.id.old_price);
        free_send = findViewById(R.id.free_send);
        search_goods_size = findViewById(R.id.search_goods_size);

        store_logo = findViewById(R.id.store_logo);
        store_name = findViewById(R.id.store_name);
        store_detail_point = findViewById(R.id.store_detail_point);
        //态度
        store_attitude_point = findViewById(R.id.store_attitude_point);
        store_send_point = findViewById(R.id.store_send_point);
        webView = findViewById(R.id.webview);
        price_linearlayout = findViewById(R.id.price_linearlayout);
        loopViewPager = findViewById(R.id.lvp_pager);
        promotion_price = findViewById(R.id.promotion_price);
        countdownView = findViewById(R.id.promotion_time);

        findViewById(R.id.action_service).setOnClickListener(this);
        findViewById(R.id.action_shopping_cart).setOnClickListener(this);
        findViewById(R.id.back).setOnClickListener(this);
        store_logo.setOnClickListener(this);
        webView.getSettings().setJavaScriptEnabled(true);
        Intent intent = getIntent();

        // 测试数据 51
        goods_id = intent.getStringExtra("goods_id");
//        if (Constants.isdebug)
//            goods_id = "51";
    }

    public void showTime() {
        long timeStamp = System.currentTimeMillis() / 1000;
        long endTime = Long.valueOf((String) SpCommonUtils.get(DStoreDetailActivity.this, Constants.ENDTIME, ""));
        if (endTime > timeStamp)
            countdownView.start((endTime - timeStamp) * 1000); // Millisecond
    }

    private void detailData(DStoreGoodsDetail goodsDetail) {
        if (goodsDetail == null || goodsDetail.getData() == null) return;
        dataBean = goodsDetail.getData();
        listBean = goodsDetail.getData().getSpec_list();

        goodsSKUList = JosnFrom.getInstance().getObjList(goodsDetail.getData().getGoods_spec_format());
        initSku();

        System.out.println("getMarket_price:sss223 " + goodsSKUList);
        System.out.println("getMarket_price:sss224 " + goodsSKUList.size());
        System.out.println("getMarket_price:sss 225" + dataBean.getGoods_sku().get(0).getStock());
        if (goodsSKUList != null && goodsSKUList.size() == 0 && dataBean.getGoods_sku().get(0) != null) {
            sku_id = dataBean.getGoods_sku().get(0).getSku_id();  //没有规格的时候的sku
            search_goods_size.setText(dataBean.getGoods_sku().get(0).getSku_name());
            System.out.println("getMarket_price:sss " + goodsDetail.getData().getPrice());
            System.out.println("getMarket_price:sss " + dataBean.getPromotion_type());
            if (dataBean.getPromotion_type().equals("0")) { //无促销
                price_linearlayout.setVisibility(View.VISIBLE);
                loopViewPager.setVisibility(View.GONE);
                promote_price = dataBean.getGoods_sku().get(0).getPrice();

                System.out.println("getMarket_price: " + goodsDetail.getData().getPrice());
                System.out.println("getMarket_price111: " + goodsDetail.getData().getMarket_price());

                price.setText("￥" + goodsDetail.getData().getPrice());
                old_price.setText("￥" + goodsDetail.getData().getMarket_price());
                old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线

                free_send.setText("运费：" + goodsDetail.getData().getFreight());
            } else {
                price_linearlayout.setVisibility(View.GONE);
                loopViewPager.setVisibility(View.VISIBLE);
                promote_price = dataBean.getGoods_sku().get(0).getPromote_price();
                promotion_price.setText("￥" + goodsDetail.getData().getPromotion_price());
                showTime();
            }
        }

        if (dataBean.getPromotion_type().equals("0")) { //无促销
            price_linearlayout.setVisibility(View.VISIBLE);
            loopViewPager.setVisibility(View.GONE);
            promote_price = dataBean.getGoods_sku().get(0).getPrice();


            price.setText("￥" + goodsDetail.getData().getPrice());
            old_price.setText("￥" + goodsDetail.getData().getMarket_price());
            old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线

            free_send.setText("运费：" + goodsDetail.getData().getFreight());
        } else {
            price_linearlayout.setVisibility(View.GONE);
            loopViewPager.setVisibility(View.VISIBLE);
            promote_price = dataBean.getGoods_sku().get(0).getPromote_price();
            promotion_price.setText("￥" + goodsDetail.getData().getPromotion_price());
            showTime();
        }

        search_textview.setText(goodsDetail.getData().getGoods_name());
        ImageUtils.setImgTv(context, goodsDetail.getData().getShop_id(), goods_name, goodsDetail.getData().getGoods_name());
//        goods_name.setText(goodsDetail.getData().getGoods_name());
//        if (goodsDetail.getData().getShop_id().equals("0")) {
//            Drawable d = getResources().getDrawable(R.drawable.self_sale);
//            //必须设置图片大小，否则不显示【0,0表示坐标x,y坐标，50,50表示宽高】
//            d.setBounds(0, 0, 60, 30);
//            //四个参数分别表示文本左、上、右、下四个方向上的图片，null表示没有图片
//            goods_name.setCompoundDrawables(d, null, null, null);
//            //   goods_name.setText(TextViewUtil.setSpanBgAndTvColor("自营" + goodsDetail.getData().getGoods_name(), 0, 2, "#ffffff", "#056198"));
//        }

        if (goodsDetail.getData().getShow_img().size() >= 1) {
            startBar(mBanner, (ArrayList) goodsDetail.getData().getShow_img());
        } else {
            mBanner.setVisibility(View.GONE);
        }

        if (goodsDetail.getData().getShop() != null && goodsDetail.getData().getShop().getShop_logo() != null) {
            ImageLoaderUtils.getInstance().load(context, store_logo, goodsDetail.getData().getShop().getShop_logo(), 0);
            store_name.setText(goodsDetail.getData().getShop().getShop_name());
            String desccredit = goodsDetail.getData().getShop().getShop_desccredit();
            store_detail_point.setText(TextViewUtil.setSpanColor(context, "描述相符 " + desccredit, 5, 5 + desccredit.length(), "#f08619", null));
            String servicecredit = goodsDetail.getData().getShop().getShop_servicecredit();
            store_attitude_point.setText(TextViewUtil.setSpanColor(context, "服务态度 " + servicecredit, 5, 5 + servicecredit.length(), "#f08619", null));
            String deliverycredit = goodsDetail.getData().getShop().getShop_deliverycredit();
            store_send_point.setText(TextViewUtil.setSpanColor(context, "发货速度 " + deliverycredit, 5, 5 + deliverycredit.length(), "#f08619", null));
        }
        if (goodsDetail.getData().getDescription_url() == null) return;
        webView.loadUrl(goodsDetail.getData().getDescription_url());
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

    }


    private Map<Integer, Integer> skuMap = new HashMap();

    private void initSku() {
        if (goodsSKUList == null) return;
        for (int i = 0; i < goodsSKUList.size(); i++) {
            skuMap.put(goodsSKUList.get(i).getSpec_id(), -1);
        }
    }


    @Override
    public void initData() {
        super.initData();
        getStoreInfo();
    }

    private void getStoreInfo() {
        String token = (String) SpCommonUtils.get(DStoreDetailActivity.this, Constants.TOKEN, "");
        String user_id = (String) SpCommonUtils.get(DStoreDetailActivity.this, Constants.USERID, "");
        String timestamp = StringUtil.getCurrentTime();
        Map params = new HashMap();
        params.put("goods_id", goods_id);
        params.put("user_id", user_id);
        params.put("token", token);
        params.put("timestamp", timestamp);
        final Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);

        OkGo.<JsonObject>post(Constants.BASE_URL + Constants.STORE_GOODS_DETAIL)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", timestamp)
                .params("user_id", user_id)
                .params("token", token)
                .params("goods_id", goods_id)
                .execute(new JsonCallback<JsonObject>() {
                    @Override
                    public void onSuccess(Response<JsonObject> response) {
                        DStoreGoodsDetail goodesDetail = (DStoreGoodsDetail) JosnFrom.getInstance().getObj(response.body().toString(), DStoreGoodsDetail.class);
                        if (goodesDetail != null && goodesDetail.getStatus().equals("0001")) {
                            System.out.println("getMarket_price  ");
                            System.out.println("=TypeBean设置===getMarket_price ");
                            detailData(goodesDetail);
                        } else {
                            RxToast.showShort(goodesDetail.getMsg());
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
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new ImageLoadUtils());
        //设置图片集合
        banner.setImages(bannerImageArray);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.RotateDown);
        //设置标题集合（当banner样式有显示title时）
        //  banner.setBannerTitles(adTitle);
        //设置轮播时间
        banner.setDelayTime(5000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);

        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    private LinearLayout popCancel;
    private TextView popConfirm;
    private PopupWindow popupWindow;
    private SelectGoogsParamAdapter selectGoogsParamAdapter;


    private void showChoiceSizePop() {
        if (listBean == null) return;

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(context).inflate(
                R.layout.choice_size, null);
        // 设置按钮的点击事件
        ImageView pop_img = contentView.findViewById(R.id.pop_img);
        pop_price = contentView.findViewById(R.id.pop_price);
        pop_store = contentView.findViewById(R.id.pop_store);

        ImageLoaderUtils.getInstance().load(context, pop_img, dataBean.getShow_img().get(0), 0);
        pop_price.setText("￥" + dataBean.getPrice());
        pop_store.setText("库存：" + dataBean.getStock());

        popCancel = contentView.findViewById(R.id.choice_size_cancel); //退出
        popConfirm = contentView.findViewById(R.id.choice_size_confirm); //确认加入
        if (immediatelyPay) {
            popConfirm.setText("立即购买");
        }
        RecyclerView recyclerView = contentView.findViewById(R.id.recyclerView); //确认加入
//      popBuyNum = contentView.findViewById(R.id.choice_size_num);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        // 当我们确定Item的改变不会影响RecyclerView的宽高的时候可以设置
        recyclerView.setHasFixedSize(false);

        selectGoogsParamAdapter = new SelectGoogsParamAdapter(DStoreDetailActivity.this, goodsSKUList);
        recyclerView.setAdapter(selectGoogsParamAdapter);
        selectGoogsParamAdapter.getData().addAll(listBean);
//        selectGoogsParamAdapter.notifyDataSetChanged();

        popCancel.setOnClickListener(this);
        popConfirm.setOnClickListener(this);

        Display display = getWindowManager().getDefaultDisplay();
        popupWindow = new PopupWindow(contentView,
                GridLayout.LayoutParams.MATCH_PARENT, display.getHeight() * 2 / 3, true);

        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("mengdd", "onTouch : ");
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
//        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        // 设置好参数之后再show
        popupWindow.showAtLocation(action_buy_service, Gravity.BOTTOM, 0, 0);

    }


    public void setArray(int spec_id, int spec_value_id) {
        boolean isShow = true;
        Log.i("规格选择完成之前", spec_id + "====" + spec_value_id);
        skuMap.put(spec_id, spec_value_id);

        for (int val : skuMap.values()) {
            if (val == -1) {
//                RxToast.showShort("请选择规格");
                isShow = false;
            }
        }
        if (isShow) {  //7:9;9:14
            StringBuilder attr = new StringBuilder();
            for (Integer key : skuMap.keySet()) {
//                attr.append( key+":"+skuMap.get(key));
                attr.append(key);
                attr.append(":");
                attr.append(skuMap.get(key));
                attr.append(";");
            }

            Log.i("规格选择完成之后", attr.toString());


            for (int i = 0; i < dataBean.getGoods_sku().size(); i++) {
                Log.i("规格选择完成之后=== ", dataBean.getGoods_sku().get(i).getAttr_value_items());
//                if ((attr.toString()).startsWith(dataBean.getGoods_sku().get(i).getAttr_value_items())) {
                if (isInclude(attr.toString(), dataBean.getGoods_sku().get(i).getAttr_value_items())) {

                    search_goods_size.setText(dataBean.getGoods_sku().get(i).getSku_name());

                    sku_id = dataBean.getGoods_sku().get(i).getSku_id();
                    if (dataBean.getPromotion_type().equals("0")) { //无促销
                        promote_price = dataBean.getGoods_sku().get(i).getPrice();
                    } else {
                        promote_price = dataBean.getGoods_sku().get(i).getPromote_price();
                    }

                    pop_price.setText("￥" + dataBean.getGoods_sku().get(i).getPromote_price());
                    pop_store.setText("库存：" + dataBean.getGoods_sku().get(i).getStock() + "件");
                    break;
                }
            }
        }
    }

    private boolean isInclude(String data, String attr_value_items) {
        // 9:14;7:10;    7:10;9:14
        String[] arr = data.split(";");
        String[] item = attr_value_items.split(";");
        Arrays.sort(arr);
        Arrays.sort(item);
        Log.i("相比arll", Arrays.toString(arr));
        Log.i("相比item", Arrays.toString(item));
        Log.i("相比item", "相比item " + Arrays.equals(item, arr));
        return Arrays.equals(item, arr);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                DStoreDetailActivity.this.finish();
                break;
            case R.id.choice_size_cancel:
                if (null != popupWindow) {
                    popupWindow.dismiss();
                }
                break;
            case R.id.action_join_shopping_cart:
                immediatelyPay = false;
                if (goodsSKUList != null && goodsSKUList.size() > 0) {
                    showChoiceSizePop(); //选规格
                } else {
                    join(); //商品没有规格可选
                }

                break;
            case R.id.choice_size_confirm: //确定加入
                if (immediatelyPay) {
                    immediatelyPay();
                } else {
                    join();
                }
                break;
            case R.id.action_buy_service: //立即付款
                immediatelyPay = true;
                if (sku_id.equals("")) {
                    showChoiceSizePop();
                } else {
                    immediatelyPay();
                }


                break;
            case R.id.action_service: //打电话
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + "400-025-2116");
                intent.setData(data);
                startActivity(intent);
                break;
            case R.id.action_shopping_cart: //跳到首页
                Intent mina = new Intent(DStoreDetailActivity.this, MainActivity.class);
                mina.putExtra("main", "joincart");
                DStoreDetailActivity.this.startActivity(mina);
                DStoreDetailActivity.this.finish();
                break;
            case R.id.store_logo:
                if (dataBean == null || dataBean.getShop_id() == null) return;
//                Intent storeInfo = new Intent(context, ShopDetailsActivity.class);
//                storeInfo.putExtra("shop_id", dataBean.getShop_id());
//                context.startActivity(storeInfo);
                break;
        }

    }


    private void join() {
        Log.i("", "join: " + sku_id);
        String token = (String) SpCommonUtils.get(DStoreDetailActivity.this, Constants.TOKEN, "");
        String user_id = (String) SpCommonUtils.get(DStoreDetailActivity.this, Constants.USERID, "");
        String timestamp = StringUtil.getCurrentTime();
        Map params = new HashMap();
        params.put("goods_id", goods_id);
        params.put("sku_id", sku_id);
        params.put("nums", "1");
        params.put("user_id", user_id);
        params.put("token", token);
        params.put("timestamp", timestamp);
        final Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);

        OkGo.<JsonObject>post(Constants.BASE_URL + Constants.JOIN_CAR)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", timestamp)
                .params("user_id", user_id)
                .params("token", token)
                .params("goods_id", goods_id)
                .params("sku_id", sku_id)
                .params("nums", "1")
                .execute(new JsonCallback<JsonObject>() {
                    @Override
                    public void onSuccess(Response<JsonObject> response) {
                        BaseResponse goodesDetail = (BaseResponse) JosnFrom.getInstance().getObj(response.body().toString(), BaseResponse.class);
                        if (goodesDetail != null && goodesDetail.getStatus().equals("0001")) {
                            RxToast.showShort("添加成功");
                            if (null != popupWindow) {
                                popupWindow.dismiss();
                            }
                        } else {
                            RxToast.showShort(goodesDetail.getMessage());
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

    //立即支付
    private void immediatelyPay() {
        String token = (String) SpCommonUtils.get(DStoreDetailActivity.this, Constants.TOKEN, "");
        String user_id = (String) SpCommonUtils.get(DStoreDetailActivity.this, Constants.USERID, "");
        String timestamp = StringUtil.getCurrentTime();
        Map params = new HashMap();
        params.put("goods_id", goods_id);
        params.put("sku_id", sku_id);

        params.put("user_id", user_id);
        params.put("token", token);
        params.put("timestamp", timestamp);
        final Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);

        OkGo.<JsonObject>post(Constants.BASE_URL + Constants.NOW_PAY)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", timestamp)
                .params("user_id", user_id)
                .params("token", token)
                .params("goods_id", goods_id)
                .params("sku_id", sku_id)
                .execute(new JsonCallback<JsonObject>() {
                    @Override
                    public void onSuccess(Response<JsonObject> response) {
                        DStoreImmediatelyPay goodesDetail = (DStoreImmediatelyPay) JosnFrom.getInstance().getObj(response.body().toString(), DStoreImmediatelyPay.class);
                        if (goodesDetail != null && goodesDetail.getStatus().equals("0001")) {
                            Intent intent = new Intent(DStoreDetailActivity.this, DStoreImmediatelyPayActivity.class);
                            List<CartList.DataBean.CartListInfoBean.GoodsItemBean> mGoPayList = new ArrayList<>();
                            CartList.DataBean.CartListInfoBean.GoodsItemBean bean = new CartList.DataBean.CartListInfoBean.GoodsItemBean();
                            bean.setGoods_picture(dataBean.getShow_img().get(0));
                            bean.setGoods_name(dataBean.getGoods_name());
                            bean.setSku_name(search_goods_size.getText().toString());
                            bean.setPrice(promote_price);
                            bean.setNum("1");
                            bean.setStore_name(dataBean.getShop().getShop_name());
//                            bean.setCart_id(dataBean.g);/

                            mGoPayList.add(bean);

                            intent.putExtra("list", (Serializable) mGoPayList);
                            intent.putExtra("doubleprice", promote_price);
                            intent.putExtra("cart_id_str", goodesDetail.getData().getCart_id_str());

                            DStoreDetailActivity.this.startActivity(intent);
                            DStoreDetailActivity.this.finish();
                        } else {
                            RxToast.showShort(goodesDetail.getMsg());
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


}
