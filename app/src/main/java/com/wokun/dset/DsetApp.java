package com.wokun.dset;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.support.multidex.MultiDex;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.DBCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.shantoo.common.RxAndroid;
import com.shantoo.widget.RxWidget;
import com.wokun.dset.model.User;
import com.wokun.dset.model.UserBean;
import com.wokun.dset.model.UserInfo;
import com.wokun.dset.ucenter.bean.MyBean;
import com.wokun.dset.utils.LocationUtils;
import com.wokun.dset.utils.SpCommonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

public class DsetApp extends Application {

    private static DsetApp mInstance;
    // https://www.jianshu.com/p/e4da2f477cd8  友盟统计
    public static List<Activity> activityList = new ArrayList<>();
    private Location mLocation;
    private int tabPosition = 0; //MainActivity默认底部位置
    private boolean isRefreshUCenter = false; //是否刷新个人中心数据,默认不刷新
    private boolean isRefreshShopCart = false; //是否刷新购物车列表数据,默认不刷新
    private boolean login = false;
    private static boolean isdebug = true;

    public static Context getApplication() {
        return mInstance;
    }

    public void addAct(Activity activity) {
        activityList.add(activity);
    }

    public void delateAct(Activity activity) {
        if (activityList.size() > 0 && activityList.contains(activity)) {
            activity.finish();
        }
    }

    public void delateAll() {
        if (activityList.size() > 0) {
            for (int i = 0; i < activityList.size(); i++) {
                activityList.get(i).finish();
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        SpCommonUtils.init(this);

        RxAndroid.getInstance().init(this);
        RxWidget.getInstance().init(this);

        MultiDex.install(this);
        //初始化极光推送
       /* JPushInterface.setDebugMode(true);
        JPushInterface.init(this);*/
        //高德地图
        LocationUtils.getInstance().init(this);
        LocationUtils.getInstance().start(1000, true, null);
        //初始化ShareSDK
        //    MobSDK.init(this,"22548a396a7a8","4ef44b289875592a73db1de0b3fad939");

        //  RongIM.init(this);//初始化,然后记得在清单文件配置此类。

        initOkGo();
        //1710   = JJhLFKMdzASkez1LzK4ZvjioAYFz/59GtYu48gvqiwzcphAEc4b2ImIxLX08B0ho/H5Kb5BODx9Vcl5cw/Dpnw==
        //1407   = BLJYLHSWatwbxy9rpQgnajioAYFz/59GtYu48gvqiwzcphAEc4b2IoDqbeOOpundgdT1RCsR48pN2SAEwPws/A==
    }

    //private boolean hasAccount = false;//营养师是否设置过收益账号，默认没有
    //private List<AccountList.AccountListBean> accountList = new ArrayList<>();//營養師收益信息

    public static DsetApp getInstance() {
        return mInstance;
    }

    public static Context getContext() {
        return mInstance;
    }

    public Location getLocation() {
        return mLocation;
    }

    public void setLocation(Location mLocation) {
        this.mLocation = mLocation;
    }

    public int getTabPosition() {
        return tabPosition;
    }

    public void setTabPosition(int tabPosition) {
        this.tabPosition = tabPosition;
    }

    public void setLogin(boolean login) {
        AppCache.setLogin(login);
    }

    /**
     * 判断用户是否登录
     */
    public boolean isLogin() {
        return AppCache.getUser() != null && AppCache.getLogin();
    }

    public MyBean getUser() {
        return AppCache.getUser();
    }

    public void setUser(MyBean user) {
        AppCache.setUser(user);
    }

    public void setUserInfo(MyBean user) {
        AppCache.setUserInfo(user);
    }

    public UserInfo getUserInfo() {
        return AppCache.getUserInfo();
    }

  /*  public DieticianInfoBean getDieticianInfo() {
        return AppCache.getDieticianInfo();
    }*/

    public String getImToken() {
        return AppCache.getIMToken();
    }

    public void setImToken(String imToken) {
        AppCache.setIMToken(imToken);
    }
/*
    public void setDieticianInfo(DieticianInfoBean dieticianInfo) {
        AppCache.setDieticianInfo(dieticianInfo);
    }*/

    private void initOkGo() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //log相关
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.INFO);                               //log颜色级别，决定了log在控制台显示的颜色
        builder.addInterceptor(loggingInterceptor);                                 //添加OkGo默认debug日志

        //超时时间设置，默认60秒
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);      //全局的读取超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);     //全局的写入超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);   //全局的连接超时时间

        //自动管理cookie（或者叫session的保持），以下几种任选其一就行
        //builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));            //使用sp保持cookie，如果cookie不过期，则一直有效
        builder.cookieJar(new CookieJarImpl(new DBCookieStore(this)));              //使用数据库保持cookie，如果cookie不过期，则一直有效
        //builder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));            //使用内存保持cookie，app退出后，cookie消失

        // 其他统一的配置
        OkGo.getInstance()
                .init(this)                                       //必须调用初始化
                .setOkHttpClient(builder.build())                 //建议设置OkHttpClient，不设置会使用默认的
                .setCacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST) //缓存模式先使用缓存,然后使用网络数据
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)     //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3);                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
    }

    /**
     * 初始化SDK，包括Bugly，IMSDK，RTMPSDK等
     */
    /*public void initSDK() {
        //启动bugly组件，bugly组件为腾讯提供的用于crash上报和分析的开放组件，如果您不需要该组件，可以自行移除
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());
        strategy.setAppVersion(TXLiveBase.getSDKVersionStr());
        CrashReport.initCrashReport(getApplicationContext(), TCConstants.BUGLY_APPID, true, strategy);

        TCIMInitMgr.init(getApplicationContext());

        //设置rtmpsdk log回调，将log保存到文件
        TXLiveBase.getInstance().listener = new TCLog(getApplicationContext());

        //初始化httpengine
        TCHttpEngine.getInstance().initContext(getApplicationContext());

        Log.w("TCLog","app init sdk");
    }*/
    public void setRefreshShopCart(boolean refreshShopCart) {
        isRefreshShopCart = refreshShopCart;
    }

    public boolean isRefreshShopCart() {
        return isRefreshShopCart;
    }

    public void setRefreshUCenter(boolean refreshUCenter) {
        isRefreshUCenter = refreshUCenter;
    }

    public boolean isRefreshUCenter() {
        return isRefreshUCenter;
    }

    public void clear() {
        tabPosition = 0;
        AppCache.clearUser();
    }
}
