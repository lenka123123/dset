package com.wokun.dset.mainfragment;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.itheima.roundedimageview.RoundedImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.maning.updatelibrary.InstallUtils;
import com.shantoo.widget.toast.RxToast;
import com.sunfusheng.marqueeview.MarqueeView;
import com.wokun.dset.AppCache;
import com.wokun.dset.DsetApp;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseFragment;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.home.AboutTyslActivity;
import com.wokun.dset.home.DShopHomeActivity;
import com.wokun.dset.home.HuiyuanlevelActivity;
import com.wokun.dset.home.NoticeWebActivity;
import com.wokun.dset.home.SignBean;
import com.wokun.dset.hudongshop.ChangeshopActivity;
import com.wokun.dset.login.LoginActivity;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.model.UserBean;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.store.bean.VersionBean;
import com.wokun.dset.ucenter.DsytYaoqingActivity;
import com.wokun.dset.ucenter.SaoyisaoActivity;
import com.wokun.dset.utils.AlertDialogUtil;
import com.wokun.dset.utils.ImageLoader;
import com.wokun.dset.utils.JosnFrom;
import com.wokun.dset.utils.SpCommonUtils;
import com.wokun.dset.utils.StringUtil;
import com.wokun.dset.utils.VsersionUtil;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.shantoo.widget.utils.ManagerTool.getPackageManager;
import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

/**
 * Created by Administrator on 2019\1\14 0014
 */

public class HomeFragment extends BaseFragment implements AlertDialogUtil.OnClickDialogListener, InstallUtils.DownloadCallBack {
    @BindView(R.id.ivLD_matchTip)
    ImageView ivLDMatchTip;
    @BindView(R.id.saoyisao)
    ImageView saoyisao;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;
    private UserBean user;
    @BindView(R.id.user_head_img)
    RoundedImageView userHeadImg;
    @BindView(R.id.shouye_user_name)
    TextView shouye_user_name;
    /*@BindView(R.id.evalution_score1)
       MyRatingBar xinxin;*/
    @BindView(R.id.star)
    LinearLayout star;
    private UserBean.ChecksignBean checksign;
    private String apkDownloadUrl = "";
    private String fileDir;
    private NotificationCompat.Builder builder;
    private Notification notification;
    private NotificationManager notificationManager;

    @Override
    public int createView() {
        return R.layout.activity_home;
    }

    @Override
    public void initViews() {
        Animation rotate = AnimationUtils.loadAnimation(getContext(), R.anim.ld_rotate);
        ivLDMatchTip.startAnimation(rotate);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //    RxToast.showShort("我刷新了");
                loadDataMessage();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        versionupDate();

    }

    @Override
    public void loadData() {
        loadDataMessage();
    }

    private void loadDataMessage() {
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);
        OkGo.<BaseResponse<UserBean>>post(Constants.BASE_URL + Constants.GET_MAIN)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .execute(new JsonCallback<BaseResponse<UserBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<UserBean>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        if (body.getStatus().equals("0001")) {
                            //   RxToast.showShort(body.getMessage());
                            user = (UserBean) body.getData();
                            if (user == null) {
                                return;
                            }
                            if (body.getMessage().equals("未登录")) {
                                startActivity(LoginActivity.class);
                            }
                            //  DsetApp.getInstance().setUser(user);
                            checksign = user.getChecksign();
                            //公告
                            List<UserBean.NoticBean> noticeBeanList = user.getNotic();
                            if (noticeBeanList != null) {
                                AppCache.saveNotice(noticeBeanList);
                            }
                            marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position, TextView textView) {
                                    String id = user.getNotic().get(position).getId();
                                    Intent intent = new Intent(getContext(), NoticeWebActivity.class);
                                    intent.putExtra("id", id);
                                    startActivity(intent);
                                }
                            });
                            marqueeView.startWithList(AppCache.getNotice());
                            //用户头像
                            Log.e("头像", user.getUser().getUser_img() + "");
                            if (TextUtils.isEmpty(user.getUser().getUser_img())) {
                                ImageLoader.loadImage("", userHeadImg);
                            } else {
                                ImageLoader.loadImage(user.getUser().getUser_img(), userHeadImg);
                            }
                            //用户数据
                            shouye_user_name.setText("UID:" + user.getUser().getUserid());
                            setuser();
                            if (checksign.getSignin_status() != null && !"0".equals(checksign.getSignin_status())) {
                                showHongbao();
                            }
                        } else if (body.getStatus().equals("0002")) {
                            RxToast.showShort(body.getMessage());
                            startActivity(LoginActivity.class);
                        } else if (body.getStatus().equals("0003")) {
                            RxToast.showShort(body.getMessage());
                            startActivity(LoginActivity.class);
                        } else {
                            startActivity(LoginActivity.class);
                        }

                    }

                    @Override
                    public void onError(Response response) {
                        super.onError(response);
                        Log.e("首页3", response + "!!!!");
                        DsetApp.getInstance().setRefreshShopCart(false);
                    }
                });


    }

    private void showHongbao() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_sign, null);
        final ImageView btnDialogSignOpen = (ImageView) view.findViewById(R.id.btnDialogSignOpen);
        final ImageView close = (ImageView) view.findViewById(R.id.close);
        final LinearLayout hongbao_lin = (LinearLayout) view.findViewById(R.id.hongbao_lin);
        final TextView hongbao_account = (TextView) view.findViewById(R.id.hongbao_account);
        final TextView hongbao_account_title = (TextView) view.findViewById(R.id.hongbao_account_title);
        // 实例化 AlertDialog
        final AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        // 获取 AlertDialog 的窗体
        Window window = alertDialog.getWindow();
        // 设置背景透明
        window.setBackgroundDrawableResource(android.R.color.transparent);
        // 设置点击系统返回可以关闭（默认）
        alertDialog.setCancelable(true);
        // 显示
        alertDialog.show();
        // 加载布局，必须在 show 之后
        window.setContentView(view);
        // 打开
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        btnDialogSignOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map params = new HashMap();
                params.put("timestamp", StringUtil.getCurrentTime());
                Map<String, String> removeMap = removeEmptyData(params);
                Map<String, String> resultMap = sortMapByKey(removeMap);
                String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);
                //  String sign = StringUtil.Md5Str(params, Constants.KEY);
                OkGo.<BaseResponse<SignBean>>post(Constants.BASE_URL + Constants.SIGN)
                        .tag(this)
                        .params("sign", sign)
                        .params("timestamp", StringUtil.getCurrentTime())
                        .execute(new JsonCallback<BaseResponse<SignBean>>() {
                            @Override
                            public void onSuccess(Response<BaseResponse<SignBean>> response) {
                                BaseResponse body = response.body();
                                if (body == null) return;
                                Log.e("user", "进来了2!!!!");
                                if (body.getStatus().equals("0001")) {
                                    //   RxToast.showShort(body.getMessage());
//                                    alertDialog.dismiss();
                                    hongbao_account.setVisibility(View.VISIBLE);
                                    hongbao_account_title.setVisibility(View.VISIBLE);
                                    SignBean data = (SignBean) body.getData();
                                    double amount = data.getAmount();

                                    DecimalFormat df = new DecimalFormat("#.00");
                                    String str = df.format(amount);
                                    hongbao_account.setText(str);

//                                    openhongbao(amounts);

                           /*         btnDialogSignOpen.setVisibility(View.GONE);
                                    hongbao_lin.setVisibility(View.VISIBLE);
                                    hongbao_account.setText((int) data.getAmount()+"");*/

                                } else {
                                    RxToast.showShort(body.getMessage());
                                }
                            }

                            @Override
                            public void onError(Response response) {
                                super.onError(response);
                                Log.e("user", response + "!!!!");
                                DsetApp.getInstance().setRefreshShopCart(false);
                            }
                        });
            }
        });
    }

    private void openhongbao(String amounts) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_sign_open, null);
        TextView hongbao_account = (TextView) view.findViewById(R.id.hongbao_account);
        String[] num = amounts.split("\\.");
        if (num.length > 1) {
            DecimalFormat df = new DecimalFormat("#.00");
            String str = df.format(amounts);
            hongbao_account.setText(str);
        } else {
            hongbao_account.setText(amounts + ".00");
        }


        // 实例化 AlertDialog
        final AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        // 获取 AlertDialog 的窗体
        Window window = alertDialog.getWindow();
        // 设置背景透明
        window.setBackgroundDrawableResource(android.R.color.transparent);
        // 设置点击系统返回可以关闭（默认）
        alertDialog.setCancelable(true);
        // 显示
        alertDialog.show();
        // 加载布局，必须在 show 之后
        window.setContentView(view);


    }

    private void setuser() {
        //  star.refreshDrawableState();
        star.removeAllViews();
        if (user.getUser().getStart() == 1) {
            TextView textView = new TextView(getActivity());
            textView.setTextColor(getResources().getColor(R.color.yellow));
            textView.setTextSize(12);
            textView.setText("等级：普通");
            star.addView(textView);
        } else if (user.getUser().getStart() > 1) {
            TextView textView = new TextView(getActivity());
            textView.setTextColor(getResources().getColor(R.color.yellow));
            textView.setTextSize(12);
            textView.setText("等级：");

            if (user.getUser().getStart() > 6) {
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(32, 32);
                ImageView imageView = new ImageView(getActivity());
                imageView.setLayoutParams(layoutParams);
                imageView.setImageResource(R.drawable.shouye_huangguan);
                star.addView(textView);
                star.addView(imageView);
                return;
            }
            star.addView(textView);
            for (int i = 0; i < (user.getUser().getStart() - 1); i++) {
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(32, 32);
                ImageView imageView = new ImageView(getActivity());
                imageView.setLayoutParams(layoutParams);
                imageView.setImageResource(R.drawable.shouye_xinxin);
                star.addView(imageView);
            }
        }
    }

    /**
     * 互动商户
     */

    @OnClick(R.id.dsyt_zengzhi)
    public void action_dsyt_zengzhi(View v) {
        if (R.id.dsyt_zengzhi == v.getId()) {
            startActivity(ChangeshopActivity.class);
        }
    }

    /**
     * 发现余额
     */

    @OnClick(R.id.dsyt_change_center)
    public void action_dsyt_change_centeri(View v) {
        if (R.id.dsyt_change_center == v.getId()) {
            startActivity(DsytYaoqingActivity.class);
        }
    }

    /**
     * 扫一扫
     */
    @OnClick(R.id.saoyisao)
    public void action_saoyisao(View v) {
        if (R.id.saoyisao == v.getId()) {
            Intent intent = new Intent();
            intent.setClass(getContext(), SaoyisaoActivity.class);
            startActivity(intent);
        }
    }

    /**
     * 太宜食聊
     * <p>
     * http://www.appchina.com/app/com.wokun.tysl
     */

    @OnClick(R.id.home_tysl)
    public void action_home_tysl(View v) {
        if (R.id.home_tysl == v.getId()) {
            Intent intent = getPackageManager().getLaunchIntentForPackage("com.wokun.tysl");
            if (intent != null) {
                startActivity(intent);
            } else {
                //没有安装要跳转的app应用，提醒一下
                startActivity(AboutTyslActivity.class);
            }
        }
    }

    /**
     * 达事商城
     */
    @OnClick(R.id.dsyt_findyue)
    public void action_dsyt_dsyt_findyue(View v) {
        if (R.id.dsyt_findyue == v.getId()) {
            startActivity(DShopHomeActivity.class);
        }
    }

    /**
     * 会员等级
     */
    @OnClick(R.id.shouye_user_huiyuan)
    public void action_homehuiyuan(View v) {
        if (R.id.shouye_user_huiyuan == v.getId()) {
            Intent intent = new Intent(getContext(), HuiyuanlevelActivity.class);
            intent.putExtra("touxiang", user.getUser().getUser_img());
            startActivity(intent);
        }
    }

    private static final int apkDownload = 100;
    private static final int apkUpdate = 101;

    private void versionupDate() {
        String token = (String) SpCommonUtils.get(getActivity(), Constants.TOKEN, "");
        String user_id = (String) SpCommonUtils.get(getActivity(), Constants.USERID, "");
        String timestamp = StringUtil.getCurrentTime();
        Map params = new HashMap();

        params.put("user_id", user_id);
        params.put("token", token);
        params.put("timestamp", timestamp);
        params.put("type", "1");

        final Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);
        OkGo.<JsonObject>post(Constants.BASE_URL + Constants.VENSION)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", timestamp)
                .params("user_id", user_id)
                .params("token", token)
                .params("type", "1")
                .execute(new JsonCallback<JsonObject>() {
                    @Override
                    public void onSuccess(Response<JsonObject> response) {
                        final VersionBean versionBean = (VersionBean) JosnFrom.getInstance().getObj(response.body().toString(), VersionBean.class);
                        if (versionBean != null && versionBean.getStatus().equals("0001")) {
                            if (versionBean.getData().getSwitchX() == 1) {//表示开启

                                apkDownloadUrl = versionBean.getData().getUrl();
                                AlertDialogUtil.getInstance().showCustomDialogFlag(getActivity(),
                                        apkDownload, "应用更新提示", "您好检测到新版本", HomeFragment.this);


                                Log.i(TAG, "onSuccess:版本 " + VsersionUtil.getLocalVersion(getActivity()));
                                Log.i(TAG, "onSuccess:版本12 " + Integer.valueOf(versionBean.getData().getVersion()));
                                Log.i(TAG, "onSuccess:版本url " + versionBean.getData().getUrl());
                                Constants.netVersion = Integer.valueOf(versionBean.getData().getVersion());
                                if (VsersionUtil.getLocalVersion(getActivity()) < Integer.valueOf(versionBean.getData().getVersion())) {// 本地版本  线上版本
                                    apkDownloadUrl = versionBean.getData().getUrl();
                                    AlertDialogUtil.getInstance().showCustomDialogFlag(getActivity(),
                                            apkDownload, "应用更新提示", "您好检测到新版本", HomeFragment.this);
                                }
                            }
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


    @Override
    public void OnOnClickDialogListener(boolean isUpdata, int flag) {
        Log.i(TAG, "OnOnClickDialogListener: " + isUpdata + "====00===" + flag);
        if (flag == apkDownload && isUpdata) {
            //路径
            fileDir = getActivity().getCacheDir().getAbsolutePath() + "/dsyt.apk";
            initNotification();
            downLoadManager(apkDownloadUrl);
        }

        if (flag == apkUpdate) {
        }

    }

    private void downLoadManager(String apkDownloadUrl) {

        //下载APK
        InstallUtils.with(getActivity())
                //必须-下载地址
                .setApkUrl(apkDownloadUrl)
                //非必须-下载保存的文件的完整路径+/name.apk，使用自定义路径需要获取读写权限
                .setApkPath(fileDir)
                //非必须-下载回调
                .setCallBack(HomeFragment.this)
                .startDownload();
    }

    //初始化通知
    private void initNotification() {
        notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(getActivity());
        builder.setContentTitle("正在更新...") //设置通知标题
                .setSmallIcon(R.mipmap.ic_log)
                .setLargeIcon(BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.ic_log)) //设置通知的大图标
                .setDefaults(Notification.DEFAULT_LIGHTS) //设置通知的提醒方式： 呼吸灯
                .setPriority(NotificationCompat.PRIORITY_MAX) //设置通知的优先级：最大
                .setAutoCancel(false)//设置通知被点击一次是否自动取消
                .setContentText("下载进度:" + "0%")
                .setProgress(100, 0, false);
        //构建通知对象
        notification = builder.build();
    }


    public void installApk() {
        //安装APK
        InstallUtils.installAPK(getActivity(), fileDir, new InstallUtils.InstallCallBack() {
            @Override
            public void onSuccess() {
                //onSuccess：表示系统的安装界面被打开
                //防止用户取消安装，在这里可以关闭当前应用，以免出现安装被取消
                Toast.makeText(getActivity(), "正在安装程序", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(Exception e) {
                //安装出现异常，这里可以提示用用去用浏览器下载安装
            }
        });
    }


    @Override
    public void onComplete(String path) {

        //下载完成
        //先判断有没有安装权限---适配8.0
        //如果不想用封装好的，可以自己去实现8.0适配
        InstallUtils.checkInstallPermission(getActivity(), new InstallUtils.InstallPermissionCallBack() {
            @Override
            public void onGranted() {
                //去安装APK
                installApk();
            }

            @Override
            public void onDenied() {
                //弹出弹框提醒用户
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                        .setTitle("温馨提示")
                        .setMessage("必须授权才能安装APK，请设置允许安装")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //打开设置页面
                                InstallUtils.openInstallPermissionSetting(getActivity(), new InstallUtils.InstallPermissionCallBack() {
                                    @Override
                                    public void onGranted() {
                                        //去安装APK
                                        installApk();
                                    }

                                    @Override
                                    public void onDenied() {
                                        //还是不允许咋搞？
//                                                        Toast.makeText(context, "不允许安装咋搞？强制更新就退出应用程序吧！", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .create();
                alertDialog.show();
            }
        });
    }

    @Override
    public void onLoading(long total, long current) {
        System.out.print("下载apk进度" + total);
        System.out.print("下载apk进度" + current);
        builder.setProgress(100, (int) (current / total * 100), false);
        builder.setContentText("下载进度:" + (int) (current / total * 100) + "%");
        notification = builder.build();
        notificationManager.notify(1, notification);
    }

    @Override
    public void onFail(Exception e) {
        //下载失败
    }

    @Override
    public void cancle() {
        //下载取消
    }
}
