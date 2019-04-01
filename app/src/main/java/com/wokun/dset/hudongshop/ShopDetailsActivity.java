package com.wokun.dset.hudongshop;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itheima.loopviewpager.LoopViewPager;
import com.itheima.view.BridgeWebView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.DsetApp;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseBindingActivity;
import com.wokun.dset.callback.DialogCallback;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.home.LevelBean;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.response.BaseResponse2;
import com.wokun.dset.utils.ImageLoadUtils;
import com.wokun.dset.utils.StringUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

public class ShopDetailsActivity extends BaseBindingActivity {
    @BindView(R.id.shop_titlie)
    TextView shop_titlie;
    @BindView(R.id.shop_place)
    TextView shop_place;
    @BindView(R.id.shop_tel)
    TextView shop_tel;
    @BindView(R.id.shop_time)
    TextView shop_time;
    @BindView(R.id.shop_detail_url)
    BridgeWebView shop_detail_url;
    @BindView(R.id.shop_fen)
    TextView shop_fen;
    @BindView(R.id.shop_detail_xin)
    LinearLayout shop_detail_xin;
    //    @BindView(R.id.lvp_pager)
//    LoopViewPager lvp_pager;
    @BindView(R.id.banner)
    Banner mBanner;
    private ShopDetailBean user;

    @Override
    public int createView() {
        return R.layout.activity_shop_details;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("店铺详情");
    }

    @Override
    public void init() {
        String shop_id = getIntent().getStringExtra("shop_id");

        loadData(shop_id);

    }

    @OnClick({R.id.phone})
    public void onClickPhone(View view) {
        if (view.getId() == R.id.phone) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri data = Uri.parse("tel:" + user.getTel());
            intent.setData(data);
            startActivity(intent);
        }
    }

    private void loadData(String shop_id) {
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        params.put("id", shop_id);
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);
        OkGo.<BaseResponse<ShopDetailBean>>post(Constants.BASE_URL + Constants.BUSINESS_DETAIL)
                .tag(this)
                .params("sign", sign)
                .params("id", shop_id)
                .params("timestamp", StringUtil.getCurrentTime())
                .execute(new JsonCallback<BaseResponse<ShopDetailBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<ShopDetailBean>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        Log.e("商品详细", "进来了2!!!!");
                        if (body.getStatus().equals("0001")) {
//
                            user = (ShopDetailBean) body.getData();
                            Log.e("user", user + "!!!!");
                            if (user == null) return;
                            shop_titlie.setText(user.getName());
                            shop_place.setText(user.getAddress());
                            shop_tel.setText(user.getTel());
                            shop_time.setText(user.getBusiness_hours());
                            shop_detail_url.loadUrl(user.getContent_url());
                            shop_fen.setText(user.getScore() + "分");
                            List<String> banner = user.getBanner();
                            if (banner != null) {
                                startBar(mBanner, (ArrayList) banner);
//                                lvp_pager.setImgData(banner);
//                                lvp_pager.start();
                            }
                            for (int j = 0; j < Integer.valueOf(user.getScore()); j++) {
                                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(32, 32);
                                ImageView imageView = new ImageView(ShopDetailsActivity.this);
                                imageView.setLayoutParams(layoutParams);
                                imageView.setImageResource(R.drawable.ic_shop_xinxin2);
                                shop_detail_xin.addView(imageView);

                            }


                        } else {
                            RxToast.showShort(body.getMessage());
                        }
                    }

                    @Override
                    public void onError(Response response) {
                        dismissLP();
                        super.onError(response);
                        Log.e("user", response + "!!!!");
                        DsetApp.getInstance().setRefreshShopCart(false);
                    }
                });


    }

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
    public void onDestroy() {
        if (mBanner != null)
            mBanner.removeAllViews();
//        Glide.with(this).onDestroy();
        super.onDestroy();
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
}
