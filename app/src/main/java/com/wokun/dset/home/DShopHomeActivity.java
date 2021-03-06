package com.wokun.dset.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wokun.dset.DsetApp;
import com.wokun.dset.R;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.store.adapter.BeautyHomeAdapter;
import com.wokun.dset.store.adapter.HomeSGridViewAdapter;
import com.wokun.dset.store.adapter.HomoClassAdapter;
import com.wokun.dset.store.adapter.HomoSecondAdapter;
import com.wokun.dset.store.adapter.HomoTopAdapter;
import com.wokun.dset.store.bean.DStoreHome;
import com.wokun.dset.store.dstore.dstoredetail.DStoreDetailActivity;
import com.wokun.dset.store.dstore.dstorelist.DStoreSearchListActivity;
import com.wokun.dset.store.rcycleview.RecyclerItemDecoration;
import com.wokun.dset.store.view.MyGridView;
import com.wokun.dset.utils.DisplayUtil;
import com.wokun.dset.utils.ImageLoadUtils;
import com.wokun.dset.utils.ImageLoaderUtils;
import com.wokun.dset.utils.JosnFrom;
import com.wokun.dset.utils.SpCommonUtils;
import com.wokun.dset.utils.StringUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.iwgang.countdownview.CountdownView;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

public class DShopHomeActivity extends FragmentActivity implements View.OnClickListener {

    private static final String TAG = "DShopHomeActivity";
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
    private HorizontalScrollView miaosha_horizontal;
    private LinearLayout miaosha_layout;
    private Intent mIntent;
    private long endTime;
    private DStoreHome.DataBean.JingxuanBean.AdBean jinxuanAd;
    private MyGridView class_gridView;
    private HomoClassAdapter classAdapter;

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsytshop);
        mIntent = new Intent(this, DStoreSearchListActivity.class);
        init();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.action_mp_enter:
                mIntent.putExtra("category_id", "6");
                startActivity(mIntent);
                break;

            case R.id.action_sj_enter:
                mIntent.putExtra("category_id", "7");
                startActivity(mIntent);
                break;

            case R.id.action_cn_enter:
                mIntent.putExtra("category_id", "8");
                startActivity(mIntent);
                break;

            case R.id.action_jy_enter:
                mIntent.putExtra("category_id", "9");
                startActivity(mIntent);
                break;

            case R.id.action_yz_enter:
                mIntent.putExtra("category_id", "10");
                startActivity(mIntent);
                break;

            case R.id.action_my_enter:
                mIntent.putExtra("category_id", "11");
                startActivity(mIntent);
                break;

            case R.id.action_cz_enter:
                mIntent.putExtra("category_id", "14");
                startActivity(mIntent);
                break;

            case R.id.action_zf_enter:
                mIntent.putExtra("category_id", "15");
                startActivity(mIntent);
                break;

            case R.id.back:
                DShopHomeActivity.this.finish();
                break;
            case R.id.search_textview:
                mIntent.putExtra("category_id", "");
                startActivity(mIntent);
                break;
            case R.id.jingxuan_ad_img:
                if (jinxuanAd == null) return;
                Intent intent = new Intent();
                intent.putExtra(Constants.GOODS_ID, jinxuanAd.getGoods_id());
                intent.setClass(DShopHomeActivity.this, DStoreDetailActivity.class);
                DShopHomeActivity.this.startActivity(intent);
                break;
        }
    }

    public void init() {
        findViewById(R.id.search_textview).setOnClickListener(this);
        findViewById(R.id.back).setOnClickListener(this);
        mEasyRefreshLayout = (SmartRefreshLayout) findViewById(R.id.easylayout);
        mEasyRefreshLayout.setEnableRefresh(false);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.addItemDecoration(new RecyclerItemDecoration(0, 0, 0, 0, 0, 0));

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setFocusableInTouchMode(false); //去除焦点即可：
        mRecyclerView.requestFocus();                 //去除焦点即可：
        mAdapter = new BeautyHomeAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        addHeaderView();

        getStoreInfo();

        //添加滑动监听
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();//获取LayoutManager
                if (manager != null && manager instanceof LinearLayoutManager) {
                    //第一个可见的位置
                    int firstPosition = ((LinearLayoutManager) manager).findFirstVisibleItemPosition();
                    Log.i(TAG, "onScrolled:firstpostion " + firstPosition);
                    if (firstPosition == 0) {
                        showTime();
                    }
                    //如果 dx>0 则表示 右滑 ,dx<0 表示 左滑,dy <0 表示 上滑, dy>0 表示下滑
                    if (dy < 0) {
                        //上滑监听
//                        topBar.setVisibility(firstPosition>1 ? View.VISIBLE : View.GONE);
                    } else {
                        //下滑监听
//                        topBar.setVisibility(firstPosition==0 ? View.GONE : View.VISIBLE);
                    }
                }
            }
        });
    }

    private void addHeaderView() {
        View header = LayoutInflater.from(this).inflate(R.layout.store_home_head_item, null);
        initBear(header);
        mAdapter.addHeaderView(header);
    }

    private void initBear(View header) {
        header.findViewById(R.id.action_mp_enter).setOnClickListener(this);
        header.findViewById(R.id.action_sj_enter).setOnClickListener(this);
        header.findViewById(R.id.action_cn_enter).setOnClickListener(this);
        header.findViewById(R.id.action_jy_enter).setOnClickListener(this);

        header.findViewById(R.id.action_yz_enter).setOnClickListener(this);
        header.findViewById(R.id.action_my_enter).setOnClickListener(this);
        header.findViewById(R.id.action_cz_enter).setOnClickListener(this);
        header.findViewById(R.id.action_zf_enter).setOnClickListener(this);

        class_gridView = header.findViewById(R.id.class_gridView);
        jingxuan_ad_img = header.findViewById(R.id.jingxuan_ad_img);
        jingxuan_ad_img.setOnClickListener(this);
        jingxuan_gridview = header.findViewById(R.id.jingxuan_gridview);
        miaosha_gridview = header.findViewById(R.id.miaosha_gridview);
        top_gridView = header.findViewById(R.id.top_gridView);

        countdownView = header.findViewById(R.id.countdownView);
//        countdownView.start(5 * 60 * 1000L); // Millisecond

        mBanner = header.findViewById(R.id.banner);

        miaosha_horizontal = header.findViewById(R.id.miaosha_horizontal);
        miaosha_layout = header.findViewById(R.id.miaosha_layout);

        setGridView();

        topAdapter = new HomoTopAdapter(DShopHomeActivity.this);
        top_gridView.setAdapter(topAdapter);

        top_gridView.setOnRadioItemClickListener(new MyGridView.OnRadioItemClickListener() {
            @Override
            public void onItemClick(int gridViewId, int position) {
                topAdapter.setOnClick(position);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void updataColumnWidth(int size, MyGridView gridView) {
        int length = 120;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;  //2.0
        Log.i(TAG, "updataColumnWidth:111 " + density);
        int gridviewWidth = (int) (size * (length + 4) * density);
        int itemWidth = (int) (length * density); //200
        itemWidth = DisplayUtil.dp2px(DShopHomeActivity.this, itemWidth);
        Log.i(TAG, "updataColumnWidth:222 " + itemWidth);

        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gridView.setColumnWidth(itemWidth); // 设置列表项宽
        gridView.setHorizontalSpacing(20); // 设置列表项水平间距
        gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        gridView.setNumColumns(size); // 设置列数量=列表集合数

    }

    /**
     * 设置GirdView参数，绑定数据
     */
    private void setGridView() {
        gridViewAdapter = new HomeSGridViewAdapter(DShopHomeActivity.this);
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


        secondAdapter = new HomoSecondAdapter(DShopHomeActivity.this);
        miaosha_gridview.setAdapter(secondAdapter);

        classAdapter = new HomoClassAdapter(DShopHomeActivity.this);
        class_gridView.setAdapter(classAdapter);

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

        class_gridView.setOnRadioItemClickListener(new MyGridView.OnRadioItemClickListener() {
            @Override
            public void onItemClick(int gridViewId, int position) {
                classAdapter.setOnClick(position);
//                cate_id = cateList.get(position).category_id;
//                currentPage = 1;
//                getShopList();
                Log.i(TAG, "onItemClick: " + gridViewId + "点击位置" + position);
            }
        });


    }

    private void getStoreInfo() {
        String token = (String) SpCommonUtils.get(DShopHomeActivity.this, Constants.TOKEN, "");
        String user_id = (String) SpCommonUtils.get(DShopHomeActivity.this, Constants.USERID, "");
        String currentTime = StringUtil.getCurrentTime();
        Map params = new HashMap();
        params.put("user_id", user_id);
        params.put("token", token);
        params.put("timestamp", currentTime);
        final Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);
        OkGo.<JsonObject>post(Constants.BASE_URL + Constants.STORE_HOME_URL)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", currentTime)
                .params("user_id", user_id)
                .params("token", token)
                .execute(new JsonCallback<JsonObject>() {
                    @Override
                    public void onSuccess(Response<JsonObject> response) {
//                        User user = gson.fromJson(userJson, User.class);
                        DStoreHome dStoreHome = (DStoreHome) JosnFrom.getInstance().getObj(response.body().toString(), DStoreHome.class);
                        if (dStoreHome != null && dStoreHome.getStatus().equals("0001")) {
                            detailData(dStoreHome);
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

    public void detailData(DStoreHome dStoreHome) {
        jinxuanAd = dStoreHome.getData().getJingxuan().getAd();
        if (jinxuanAd != null && jinxuanAd.getPic_url() != null) {
//            Glide.with(DShopHomeActivity.this).load(jinxuanAd.getPic_url()).into(jingxuan_ad_img);
            ImageLoaderUtils.getInstance().load(DShopHomeActivity.this, jingxuan_ad_img, jinxuanAd.getPic_url(), 4);
        }

        if (dStoreHome.getData().getJingxuan().getGoods() != null && dStoreHome.getData().getJingxuan().getGoods().size() > 0) {
//            jingxuan_gridview.setNumColumns(dStoreHome.getData().getJingxuan().getGoods().size());
            updataColumnWidth(dStoreHome.getData().getJingxuan().getGoods().size(), jingxuan_gridview);
            gridViewAdapter.setData(dStoreHome.getData().getJingxuan().getGoods());
        }

        if (dStoreHome.getData().getTop6() != null && dStoreHome.getData().getTop6().size() > 0)
            topAdapter.setData(dStoreHome.getData().getZuizhidang().getFenlei());

        classAdapter.setData(dStoreHome.getData().getClassX());
        if (dStoreHome.getData().getPromotionInfo().getPromotionGoods() != null && dStoreHome.getData().getPromotionInfo().getPromotionGoods().size() > 0) {
            updataColumnWidth(dStoreHome.getData().getPromotionInfo().getPromotionGoods().size(), miaosha_gridview);
            secondAdapter.setData(dStoreHome.getData().getPromotionInfo().getPromotionGoods());

            miaosha_horizontal.setVisibility(View.VISIBLE);
            miaosha_layout.setVisibility(View.VISIBLE);
        } else {
            miaosha_horizontal.setVisibility(View.VISIBLE);
            miaosha_layout.setVisibility(View.VISIBLE);
        }
        //倒计时控件
        if (dStoreHome.getData().getPromotionInfo().getEnd_time() != null) {
            endTime = Long.valueOf(dStoreHome.getData().getPromotionInfo().getEnd_time());
            showTime();
            SpCommonUtils.put(DShopHomeActivity.this, Constants.ENDTIME, dStoreHome.getData().getPromotionInfo().getEnd_time());
        }


        if (dStoreHome.getData().getAd() != null && dStoreHome.getData().getAd().size() > 0) {
            for (int i = 0; i < dStoreHome.getData().getAd().size(); i++) {
                bannerAgainImageArray.add(i, dStoreHome.getData().getAd().get(i).getPic_url());
            }
        }
        startBar(mBanner, bannerAgainImageArray, dStoreHome.getData().getAd());

        if (!mAdapter.getData().isEmpty()) {
            mAdapter.getData().clear();
        }
        mAdapter.getData().addAll(dStoreHome.getData().getTuijian());
        mEasyRefreshLayout.setLoadmoreFinished(true);
        mRecyclerView.scrollToPosition(0);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showTime();
    }


    public void showTime() {
        long timeStamp = System.currentTimeMillis() / 1000;
        Log.i(TAG, "showTime:qq  " + timeStamp);
        if (endTime > timeStamp)
            countdownView.start((endTime - timeStamp) * 1000); // Millisecond
    }

    public void startBar(Banner banner, ArrayList bannerImageArray, final List<DStoreHome.DataBean.AdBeanX> ad) {
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
//                Intent intent = new Intent();
//                intent.putExtra(Constants.GOODS_ID, ad.get(position).getGoods_id());
//                intent.setClass(DShopHomeActivity.this, DStoreDetailActivity.class);
//                DShopHomeActivity.this.startActivity(intent);
            }
        });
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new ImageLoadUtils());
        //设置图片集合
        banner.setImages(bannerImageArray);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        //  banner.setBannerTitles(adTitle);
        //设置轮播时间
        banner.setDelayTime(5000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }


}
