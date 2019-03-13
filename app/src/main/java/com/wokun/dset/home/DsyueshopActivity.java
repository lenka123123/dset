package com.wokun.dset.home;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.DsetApp;
import com.wokun.dset.MainActivity;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseBindingActivity;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginActivity;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.login.net.MyJsonCallback;
import com.wokun.dset.model.Constants;
import com.wokun.dset.model.UserBean;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.store.adapter.BeautyHomeAdapter;
import com.wokun.dset.store.adapter.HomeSGridViewAdapter;
import com.wokun.dset.store.adapter.HomoSecondAdapter;
import com.wokun.dset.store.adapter.HomoTopAdapter;
import com.wokun.dset.store.bean.DStoreHome;
import com.wokun.dset.store.rcycleview.RecyclerItemDecoration;
import com.wokun.dset.store.view.MyGridView;
import com.wokun.dset.utils.ImageLoadUtils;
import com.wokun.dset.utils.JosnFrom;
import com.wokun.dset.utils.SpCommonUtils;
import com.wokun.dset.utils.StringUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.iwgang.countdownview.CountdownView;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

public class DsyueshopActivity extends BaseBindingActivity {

    private SmartRefreshLayout mEasyRefreshLayout;
    private RecyclerView mRecyclerView;
    private BeautyHomeAdapter mAdapter;
    private MyGridView jingxuan_gridview;
    private MyGridView miaosha_gridview;
    private HomeSGridViewAdapter gridViewAdapter;
    private ImageView jingxuan_ad_img;
    private HomoSecondAdapter secondAdapter;
    private MyGridView top_gridView;
    private HomoTopAdapter topAdapter;
    private CountdownView countdownView;
    private Banner mBanner;
    private ArrayList bannerAgainImageArray = new ArrayList();


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
    }

    @Override
    public void onDestroy() {
        if (mBanner != null)
            mBanner.removeAllViews();
        super.onDestroy();
    }

    @Override
    public int createView() {
        return R.layout.activity_dsytshop;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("商城");
    }

    @Override
    public void init() {
        mEasyRefreshLayout = (SmartRefreshLayout) findViewById(R.id.easylayout);
        mEasyRefreshLayout.setEnableRefresh(false);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.addItemDecoration(new RecyclerItemDecoration(6, 2));
        //    GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new BeautyHomeAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        getStoreInfo();
        addHeaderView();
    }


    private void addHeaderView() {
        View header = LayoutInflater.from(this).inflate(R.layout.store_home_head_item, null);
        initBear(header);
        mAdapter.addHeaderView(header);
    }

    private void initBear(View header) {
        jingxuan_ad_img = header.findViewById(R.id.jingxuan_ad_img);
        jingxuan_gridview = header.findViewById(R.id.jingxuan_gridview);
        miaosha_gridview = header.findViewById(R.id.miaosha_gridview);
        top_gridView = header.findViewById(R.id.top_gridView);
        countdownView = header.findViewById(R.id.countdownView);
        mBanner = header.findViewById(R.id.banner);


        setGridView();
    }


    /**
     * 设置GirdView参数，绑定数据
     */
    private void setGridView() {
        int size = 5;
        int length = 100;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (size * (length + 4) * density);
        int itemWidth = (int) (length * density);

        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        jingxuan_gridview.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        jingxuan_gridview.setColumnWidth(itemWidth); // 设置列表项宽
        jingxuan_gridview.setHorizontalSpacing(3); // 设置列表项水平间距
        jingxuan_gridview.setStretchMode(GridView.STRETCH_SPACING_UNIFORM);
        jingxuan_gridview.setNumColumns(3); // 设置列数量=列表集合数

        gridViewAdapter = new HomeSGridViewAdapter(DsyueshopActivity.this);
        jingxuan_gridview.setAdapter(gridViewAdapter);

        jingxuan_gridview.setOnRadioItemClickListener(new MyGridView.OnRadioItemClickListener() {
            @Override
            public void onItemClick(int gridViewId, int position) {
                gridViewAdapter.setOnClick(position);
//                cate_id = cateList.get(position).category_id;
//                currentPage = 1;
//                getShopList();
                Log.i(TAG, "onItemClick: " + gridViewId + "点击位置" + position);
            }
        });

        final LinearLayout.LayoutParams miaoshaParams = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        miaosha_gridview.setLayoutParams(miaoshaParams); // 设置GirdView布局参数,横向布局的关键
        miaosha_gridview.setColumnWidth(itemWidth); // 设置列表项宽
        miaosha_gridview.setHorizontalSpacing(5); // 设置列表项水平间距
        miaosha_gridview.setStretchMode(GridView.STRETCH_SPACING_UNIFORM);
        miaosha_gridview.setNumColumns(5); // 设置列数量=列表集合数

        secondAdapter = new HomoSecondAdapter(DsyueshopActivity.this);
        miaosha_gridview.setAdapter(secondAdapter);

        miaosha_gridview.setOnRadioItemClickListener(new MyGridView.OnRadioItemClickListener() {
            @Override
            public void onItemClick(int gridViewId, int position) {
                secondAdapter.setOnClick(position);
//                cate_id = cateList.get(position).category_id;
//                currentPage = 1;
//                getShopList();
                Log.i(TAG, "onItemClick: " + gridViewId + "点击位置" + position);
            }
        });


        topAdapter = new HomoTopAdapter(DsyueshopActivity.this);
        top_gridView.setAdapter(topAdapter);
    }

    private void getStoreInfo() {

        String token = (String) SpCommonUtils.get(DsyueshopActivity.this, Constants.TOKEN, "");
        String user_id = (String) SpCommonUtils.get(DsyueshopActivity.this, Constants.USERID, "");

        Map params = new HashMap();
        params.put("user_id", user_id);
        params.put("token", token);
        params.put("timestamp", StringUtil.getCurrentTime());
        final Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);

        OkGo.<JsonObject>post(Constants.BASE_URL + Constants.STORE_HOME_URL)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .params("user_id", user_id)
                .params("token", token)
                .execute(new JsonCallback<JsonObject>() {
                    @Override
                    public void onSuccess(Response<JsonObject> response) {
//                        User user = gson.fromJson(userJson, User.class);
                        DStoreHome dStoreHome = (DStoreHome) JosnFrom.getInstance().getObj(response.body().toString(), DStoreHome.class);
                        Log.e("usergetMsg: ", dStoreHome.getMsg());
                        detailData(dStoreHome);

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

    public void detailData(DStoreHome dStoreHome) {

        DStoreHome.DataBean.JingxuanBean.AdBean jinxuanAd = dStoreHome.getData().getJingxuan().getAd();
        if (jinxuanAd != null && jinxuanAd.getPic_url() != null) {
            Glide.with(DsyueshopActivity.this).load(jinxuanAd.getPic_url()).into(jingxuan_ad_img);
        }


        if (!mAdapter.getData().isEmpty()) {
            mAdapter.getData().clear();
        }
        mAdapter.getData().addAll(dStoreHome.getData().getTuijian());
        mAdapter.notifyDataSetChanged();
        mEasyRefreshLayout.setLoadmoreFinished(true);


        if (dStoreHome.getData().getJingxuan().getGoods() != null && dStoreHome.getData().getJingxuan().getGoods().size() > 0)
            gridViewAdapter.setData(dStoreHome.getData().getJingxuan().getGoods());

        if (dStoreHome.getData().getPromotionInfo().getPromotionGoods() != null && dStoreHome.getData().getPromotionInfo().getPromotionGoods().size() > 0)
            secondAdapter.setData(dStoreHome.getData().getPromotionInfo().getPromotionGoods());

        if (dStoreHome.getData().getTop6() != null && dStoreHome.getData().getTop6().size() > 0)
            topAdapter.setData(dStoreHome.getData().getTop6());

        //倒计时控件
        if (dStoreHome.getData().getPromotionInfo().getStart_time() != null && dStoreHome.getData().getPromotionInfo().getEnd_time() != null)
            countdownView.start(5 * 60 * 1000L);

//        1、mCvCountdownView.start(long times);
//        2、mCvCountdownView.pause();
//        3、mCvCountdownView.stop();
//        4、mCvCountdownView.getHour();等等

        if (dStoreHome.getData().getAd() != null && dStoreHome.getData().getAd().size() > 0) {
            for (int i = 0; i < dStoreHome.getData().getAd().size(); i++) {
                bannerAgainImageArray.add(i, dStoreHome.getData().getAd().get(i).getPic_url());
            }
        }
        startBar(mBanner, bannerAgainImageArray);
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
        banner.setDelayTime(8000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }
}
