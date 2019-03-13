package com.wokun.dset.mainfragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima.roundedimageview.RoundedImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.view.OnPhotoUpLoadListener;
import com.shantoo.widget.view.PhotoSelector;
import com.sunfusheng.marqueeview.MarqueeView;
import com.wokun.dset.AppCache;
import com.wokun.dset.DsetApp;
import com.wokun.dset.R;
import com.wokun.dset.address.ui.AddressListActivity;
import com.wokun.dset.base.BaseFragment;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.home.HuiyuanlevelActivity;
import com.wokun.dset.login.LoginActivity;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.model.UserBean;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.ucenter.DsytYaoqingActivity;
import com.wokun.dset.ucenter.DuihuanDetailsActivity;
import com.wokun.dset.ucenter.FutouDetailsActivity;
import com.wokun.dset.ucenter.JifenDetailsActivity;
import com.wokun.dset.ucenter.JifenchangeActivity;
import com.wokun.dset.ucenter.PersonalInfoActivity;
import com.wokun.dset.ucenter.SaoyisaoActivity;
import com.wokun.dset.ucenter.SettingsActivity;
import com.wokun.dset.ucenter.ShouMoneyActivtity;
import com.wokun.dset.ucenter.XiaoxiDetailsActvitity;
import com.wokun.dset.ucenter.YueDetailsActvitity;
import com.wokun.dset.ucenter.ZhuanzhangActivity;
import com.wokun.dset.ucenter.addcards.BankTestActivity2;
import com.wokun.dset.ucenter.addcards.TixianMoneyActivity2;
import com.wokun.dset.ucenter.bean.MyBean;
import com.wokun.dset.ucenter.quanyi.QuanyiActivity;
import com.wokun.dset.ucenter.renamepassword.RenamepaypwdActvitity;
import com.wokun.dset.utils.ImageLoader;
import com.wokun.dset.utils.StringUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

/**
 * Created by Administrator on 2019\1\14 0014.
 */

public class MineFragment  extends BaseFragment{
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.ucenter_jifen)
    RelativeLayout ucenterJifen;
    @BindView(R.id.swipeRefreshLayout)SwipeRefreshLayout swipeRefreshLayout;
    private  MyBean user;
    @BindView(R.id.user_head_img)
    RoundedImageView userHeadImg;
    @BindView(R.id.star)
    LinearLayout star;
    @BindView(R.id.shouye_user_name)
    TextView shouye_user_name;
    @BindView(R.id.ucenter_num_futou)
    TextView ucenter_num_futou;
    @BindView(R.id.ucenter_money_futou)
    TextView ucenter_money_futou;
    @BindView(R.id.ucenter_yue)
    TextView ucenter_yue;
    @BindView(R.id.ucenter_jifen_total)
    TextView ucenter_jifen_total;
    @BindView(R.id.lin_futou)
    LinearLayout lin_futou;
    @BindView(R.id.ucenter_supper)
    RelativeLayout ucenter_supper;
    @BindView(R.id.uc_zhanghu_num)
    TextView uc_zhanghu_num;
    @BindView(R.id.my_user_star)
    LinearLayout my_user_star;

  //  private PhotoSelector mPhotoSelector;
    @Override
    public int createView() {
        return R.layout.activity_mine;
    }

    @Override
    public void initViews() {
      /*  mPhotoSelector = new PhotoSelector(getActivity());
        mPhotoSelector.setOnPhotoUpLoadListener(new OnPhotoUpLoadListener() {
            @Override
            public void onPhotoUpLoad(ImageView photoImage, File photoFile) {
                LoginMgr.getInstance().upLoadPicture(getActivity(),photoFile);
            }
        });*/

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
        OkGo.<BaseResponse<MyBean>>post(Constants.BASE_URL + Constants.MY_DATA)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .execute(new JsonCallback<BaseResponse<MyBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<MyBean>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        if(body.getMessage().equals("未登录")){
                            startActivity(LoginActivity.class);
                        }
                        Log.e("首页", "进来了2!!!!");
                        if (body.getStatus().equals("0001")) {
                            RxToast.showShort(body.getMessage());
                            user = (MyBean) body.getData();
                            Log.e("首页2", user + "!!!!");
                            if (user == null) {
                                return;
                            }
                             ucenter_num_futou.setText(user.getOpportunity()+"");
                             ucenter_money_futou.setText("/"+user.getQuota());
                        //    Double quanyi = user.getQuanyi();
                            if(user.getQuanyi() == null) {
                                uc_zhanghu_num.setText("0");
                            } else {
                                uc_zhanghu_num.setText(user.getQuanyi()+"");
                            }
                            ucenter_yue.setText(user.getBalance());
                            ucenter_jifen_total.setText(user.getIntegral());
                            DsetApp.getInstance().setUser(user);
                            //用户头像
                            Log.e("用户头像",user.getHead());
                            ImageLoader.loadImage(user.getHead(), userHeadImg);
                            //用户数据
                            shouye_user_name.setText("UID:" + user.getUserid());
                            setuser();
                        } else if (body.getStatus().equals("0002")) {
                            RxToast.showShort(body.getMessage());
                            startActivity(LoginActivity.class);
                        } else if (body.getStatus().equals("0003")) {
                            RxToast.showShort(body.getMessage());
                            startActivity(LoginActivity.class);

                        }else {
                            DsetApp.getInstance().clear();
                            startActivity(LoginActivity.class);
                        }
                    }

                        @Override
                        public void onError(Response response) {
                            super.onError(response);
                            Log.e("首页3",response+"!!!!");
                            DsetApp.getInstance().setRefreshShopCart(false);
                        }
                    });



                }

    private void setuser() {
        //  star.refreshDrawableState();
        star.removeAllViews();
        if (user.getStart() == 1){
            TextView textView = new TextView(getActivity());
            textView.setTextColor(getResources().getColor(R.color.yellow));
            textView.setTextSize(12);
            textView.setText("普通");
            star.addView(textView);
        }else if (user.getStart()>1){
            for (int j = 0;j<(user.getStart()-1)/5;j++){
                ViewGroup.LayoutParams layoutParams =  new ViewGroup.LayoutParams(32,32);
                ImageView imageView = new ImageView(getActivity());
                imageView.setLayoutParams(layoutParams);
                imageView.setImageResource(R.drawable.shouye_huangguan);
                star.addView(imageView);
            }
            for (int i =0;i<(user.getStart()-1)%5;i++){
                ViewGroup.LayoutParams layoutParams =  new ViewGroup.LayoutParams(32,32);
                ImageView imageView = new ImageView(getActivity());
                imageView.setLayoutParams(layoutParams);
                imageView.setImageResource(R.drawable.shouye_xinxin);
                star.addView(imageView);
            }
        }



    }
    /** 登录*/
    @OnClick(R.id.login)
    public void action_login(View v){
        if(R.id.login == v.getId()){
        startActivity(LoginActivity.class);
        }
    }

       /** 头像上传*/
    @OnClick(R.id.user_head_img)
    public void action_user_head_img(View v){
        if(R.id.user_head_img == v.getId()){
            startActivity(PersonalInfoActivity.class);
        }
    }


    /** 星星级别my_xinxin*/
    @OnClick(R.id.my_user_star)
    public void actionMyxinxin(View v){
        if(R.id.my_user_star == v.getId()){
            Intent intent = new Intent(getContext(), HuiyuanlevelActivity.class);
            intent.putExtra("touxiang",user.getHead());
            intent.putExtra("xinxin",String.valueOf(user.getStart()));
            startActivity(intent);
        }
    }

    /** 申请超级节点*/
    @OnClick(R.id.ucenter_supper)
    public void action_ucenter_supper(View v){
        if(R.id.ucenter_supper == v.getId()){
          comitSupper();
        }
    }

    private void comitSupper() {

        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.APPLY_SUPER_NODE)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .execute(new JsonCallback<BaseResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        RxToast.showShort(body.getMessage());
                    }

                    @Override
                    public void onError(Response response) {
                        super.onError(response);
                        Log.e("首页3",response+"!!!!");
                    }
                });










    }


    /**复投 兑换记录*/
    @OnClick(R.id.lin_futou)
      public void action_linfutou(View v){
        if(R.id.lin_futou == v.getId()){
            startActivity(DuihuanDetailsActivity.class);
        }
    }

    /**扫一扫*/
    @OnClick(R.id.saoma)
    public void action_saoma(View v){
        if(R.id.saoma == v.getId()){
            Intent intent = new Intent();
            intent.setClass(getContext(),SaoyisaoActivity.class);
            startActivity(intent);
        }
    }

    /**消息*/
    @OnClick(R.id.xiaoxi)
    public void action_xiaoxi(View v){
        if(R.id.xiaoxi == v.getId()){
            Intent intent = new Intent();
            intent.setClass(getContext(),XiaoxiDetailsActvitity.class);
            startActivity(intent);
        }
    }

    /**余额记录*/
    @OnClick(R.id.lin_uc_dsyue)
    public void action_lin_uc_dsyue(View v){
        if(R.id.lin_uc_dsyue == v.getId()){
            startActivity(YueDetailsActvitity.class);
        }
    }

    /**积分记录*/
    @OnClick(R.id.lin_uc_jifen)
    public void action_lin_uc_jifen(View v){
        if(R.id.lin_uc_jifen == v.getId()){
            startActivity(JifenDetailsActivity.class);
        }
    }


    /** 转账*/
    @OnClick(R.id.ucenter_zhuanzhang)
    public void action_ucenter_zhuanzhang(View v){
        if(R.id.ucenter_zhuanzhang == v.getId()){
            startActivity(ZhuanzhangActivity.class);
        }
    }
    /** 积分兑换*/
    @OnClick(R.id.ucenter_jifen)
    public void action_ucenter_jifen(View v){
        if(R.id.ucenter_jifen == v.getId()){
            startActivity(JifenchangeActivity.class);
        }
    }


    /** 收付款*/
    @OnClick(R.id.ucenter_shoumoney)
    public void action_ucenter_shoumoney(View v){
        if(R.id.ucenter_shoumoney == v.getId()){
            startActivity(ShouMoneyActivtity.class);
        }
    }

    /**修改支付密码 */
    @OnClick(R.id.ucenter_changepwd)
    public void action_ucenter_changepwd(View v){
        if(R.id.ucenter_changepwd == v.getId()){
            startActivity(RenamepaypwdActvitity.class);
        }
    }

    /**邀请注册 */
    @OnClick(R.id.ucenter_yaoqin)
    public void action_ucenter_yaoqin(View v){
        if(R.id.ucenter_yaoqin == v.getId()){
            startActivity(DsytYaoqingActivity.class);
        }
    }
    /**地址管理 */
    @OnClick(R.id.ucenter_place)
    public void action_ucenter_place(View v){
        if(R.id.ucenter_place == v.getId()){
            startActivity(AddressListActivity.class);
        }
    }
    /**账户设置 */

    @OnClick(R.id.ucenter_settings)
    public void action_ucenter_settings(View v){
        if(R.id.ucenter_settings == v.getId()){
            startActivity(SettingsActivity.class);
        }
    }
    /**添加银行卡
     *
     * 银行卡管理
     * */

    @OnClick(R.id.ucenter_addcards)
    public void action_ucenter_addcards(View v){
        if(R.id.ucenter_addcards == v.getId()){
            startActivity(TixianMoneyActivity2.class);
        }
    }

    /**
     * 权益转让
     *
     *
     * */

    @OnClick(R.id.ucenter_quanyi)
    public void action_ucenter_quanyi(View v){
        if(R.id.ucenter_quanyi == v.getId()){
            startActivity(QuanyiActivity.class);
        }
    }




   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPhotoSelector.onActivityResult(requestCode, resultCode, data);
    }*/
}
