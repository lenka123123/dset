package com.wokun.dset.mainfragment;

import android.content.Intent;
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

import com.itheima.roundedimageview.RoundedImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
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
import com.wokun.dset.hudongshop.changeshopActivity;
import com.wokun.dset.login.LoginActivity;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.model.UserBean;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.ucenter.DsytYaoqingActivity;
import com.wokun.dset.ucenter.SaoyisaoActivity;
import com.wokun.dset.utils.ImageLoader;
import com.wokun.dset.utils.StringUtil;
import com.wokun.dset.utils.ToastUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

/**
 * Created by Administrator on 2019\1\14 0014
 */

public class HomeFragment extends BaseFragment {
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
    /*   @BindView(R.id.evalution_score1)
       MyRatingBar xinxin;*/
    @BindView(R.id.star)
    LinearLayout star;
    private UserBean.ChecksignBean checksign;

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
        final LinearLayout hongbao_lin = (LinearLayout) view.findViewById(R.id.hongbao_lin);
        final TextView hongbao_account = (TextView) view.findViewById(R.id.hongbao_account);
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
                                    alertDialog.dismiss();
                                    SignBean data = (SignBean) body.getData();
                                    double amount = data.getAmount();
                                    String amounts = (int) data.getAmount() + "";
                                    openhongbao(amounts);

                           /*         btnDialogSignOpen.setVisibility(View.GONE);
                                    hongbao_lin.setVisibility(View.VISIBLE);
                                    hongbao_account.setText((int) data.getAmount()+"");*/

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
        hongbao_account.setText(amounts);
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
            textView.setText("普通");
            star.addView(textView);
        } else if (user.getUser().getStart() > 1) {
            for (int j = 0; j < (user.getUser().getStart() - 1) / 5; j++) {
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(32, 32);
                ImageView imageView = new ImageView(getActivity());
                imageView.setLayoutParams(layoutParams);
                imageView.setImageResource(R.drawable.shouye_huangguan);
                star.addView(imageView);
            }
            for (int i = 0; i < (user.getUser().getStart() - 1) % 5; i++) {
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
            startActivity(changeshopActivity.class);
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
            startActivity(AboutTyslActivity.class);
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


}
