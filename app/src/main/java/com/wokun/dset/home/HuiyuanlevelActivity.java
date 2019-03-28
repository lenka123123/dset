package com.wokun.dset.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.itheima.roundedimageview.RoundedImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.DsetApp;
import com.wokun.dset.MainActivity;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseBindingActivity;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.model.UserBean;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.utils.ImageLoader;
import com.wokun.dset.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

public class HuiyuanlevelActivity extends BaseBindingActivity {
//USER_UPGRADE

    @BindView(R.id.user_head_img)
    RoundedImageView user_head_img;
    @BindView(R.id.my_id)
    TextView my_id;
    @BindView(R.id.star)
    LinearLayout star;
    @BindView(R.id.level_num1)
    TextView level_num1;
    @BindView(R.id.level_num2)
    TextView level_num2;
    @BindView(R.id.level_num3)
    TextView level_num3;

    @BindView(R.id.comit_shengji)
    TextView comit_shengji;
    @BindView(R.id.team_acount)
    TextView team_acount;
    @BindView(R.id.huiyuan_first)
    TextView huiyuan_first;
    @BindView(R.id.huiyuan_end)
    TextView huiyuan_end;


    @BindView(R.id.progress)
    ProgressBar progress;

    @BindView(R.id.accept_info)
    LinearLayout accept_info;

    @BindView(R.id.pro)
    LinearLayout pro;

    @BindView(R.id.top)
    TextView top;

    private LevelBean user;
    private String touxiang;

    @Override
    public int createView() {
        return R.layout.activity_huiyuanlevel;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("会员机制");
    }

    @Override
    public void init() {

        touxiang = getIntent().getStringExtra("touxiang");
        ImageLoader.loadImage(touxiang, user_head_img);
        lodaMessage();

    }

    private int pp = 0;

    @OnClick(R.id.user_head_img)
    public void user_head_img(View v) {

//        if (pp == 6) pp = -1;
//        pp++;
//        if (user != null) {
//            RxToast.showShort("当前等级" + pp);
//            user.setGrade(pp);
//            setuser();
//        }

    }

    private void lodaMessage() {
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);
        //  String sign = StringUtil.Md5Str(params, Constants.KEY);
        OkGo.<BaseResponse<LevelBean>>post(Constants.BASE_URL + Constants.USER_UPGRADE)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .execute(new JsonCallback<BaseResponse<LevelBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<LevelBean>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        Log.e("user", "进来了2!!!!");
                        if (body.getStatus().equals("0001")) {
//                            RxToast.showShort(body.getMessage());
                            user = (LevelBean) body.getData();
                            if (user == null || user.getId() == null) return;
                            my_id.setText("UID:" + user.getId());

                            team_acount.setText(user.getTeam_amount());


//                            if ([model.grade integerValue] ==0){
//                                self.gradeLb.text = @ "普通会员";
//                                self.upgradeLb.text = @ "一星会员";
//                                self.startLb.text = @ "普通";
//                            }else if ([model.grade integerValue] ==1){
//                                self.gradeLb.text = @ "一星会员";
//                                self.upgradeLb.text = @ "二星会员";
//                                self.startRateView.rating = 1;
//                            }else if ([model.grade integerValue] ==2){
//                                self.gradeLb.text = @ "二星会员";
//                                self.upgradeLb.text = @ "三星会员";
//                                self.startRateView.rating = 2;
//                            }else if ([model.grade integerValue] ==3){
//                                self.gradeLb.text = @ "三星会员";
//                                self.upgradeLb.text = @ "四星会员";
//                                self.startRateView.rating = 3;
//                            }else if ([model.grade integerValue] ==4){
//                                self.gradeLb.text = @ "四星会员";
//                                self.upgradeLb.text = @ "五星会员";
//                                self.startRateView.rating = 4;
//                            }else if ([model.grade integerValue] ==5){
//                                self.gradeLb.text = @ "五星会员";
//                                self.upgradeLb.text = @ "金冠会员";
//                                self.startRateView.rating = 5;
//                                self.crownImage.hidden = YES;
//                            }else if ([model.grade integerValue] ==6){
//                                self.gradeLb.text = @ "金冠会员";
//                                self.upgradeLb.hidden = YES;
//                                self.startRateView.hidden = YES;
//                                self.crownImage.hidden = NO;
//                                self.progress.hidden = YES;
//                            }


                            setuser();

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

    private void setuser() {
        Double team_amount = Double.valueOf(user.getTeam_amount());
        Double team = Double.valueOf(user.getTeam());
        if (team < team_amount) {
            accept_info.setVisibility(View.VISIBLE);
        } else {
            accept_info.setVisibility(View.GONE);
        }
        int state = user.getGrade();
        if (state == 0) {
            huiyuan_first.setText("普通会员");
            huiyuan_end.setText("一星会员");
            progress.setProgress(0);
        } else if (state == 1) {
            huiyuan_first.setText("一星会员");
            huiyuan_end.setText("二星会员");
            progress.setProgress(20);
        } else if (state == 2) {
            huiyuan_first.setText("二星会员");
            huiyuan_end.setText("三星会员");
            progress.setProgress(40);
        } else if (state == 3) {
            huiyuan_first.setText("三星会员");
            huiyuan_end.setText("四星会员");
            progress.setProgress(60);
        } else if (state == 4) {
            huiyuan_first.setText("四星会员");
            huiyuan_end.setText("五星会员");
            progress.setProgress(80);
        } else if (state == 5) {
            huiyuan_first.setText("五星会员");
            huiyuan_end.setText("金冠会员");
            progress.setProgress(100);
        } else if (state == 6) {
            huiyuan_first.setText("金冠会员");
            huiyuan_end.setVisibility(View.GONE);
            progress.setVisibility(View.GONE);

            top.setVisibility(View.VISIBLE);
            pro.setVisibility(View.GONE);

        }


        star.removeAllViews();
        TextView textView = new TextView(this);
        textView.setTextColor(getResources().getColor(R.color.yellow));
        textView.setTextSize(12);
        if (user.getGrade() == 0) {
            level_num1.setText("邀请人数:" + user.getInvite_num());
            level_num2.setText("达标人数:" + user.getInvite_target());
            textView.setText("等级：普通");
            star.addView(textView);
        } else if (user.getGrade() >= 1) {
            level_num2.setText("团体目标 :" + user.getTeam());
            level_num1.setText("个人目标 :" + user.getPersonal());

            textView.setText("等级：");
            if (user.getGrade() == 6) {
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(32, 32);
                ImageView imageView = new ImageView(this);
                imageView.setLayoutParams(layoutParams);
                imageView.setImageResource(R.drawable.shouye_huangguan);
                star.addView(textView);
                star.addView(imageView);
                return;
            }
            star.addView(textView);
            for (int i = 0; i < (user.getGrade()); i++) {
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(32, 32);
                ImageView imageView = new ImageView(this);
                imageView.setLayoutParams(layoutParams);
                imageView.setImageResource(R.drawable.shouye_xinxin);
                star.addView(imageView);
            }
        }

        if (state == 6) {
            level_num1.setText("申请开通超级节点");
            level_num2.setText("开通超级节点后转账不限额");
            comit_shengji.setVisibility(View.GONE);
        }
    }

    /**
     * 会员等级
     */
    @OnClick(R.id.home_say)
    public void action_home_say(View v) {
        if (R.id.home_say == v.getId()) {

            // 实例化一个Bundle
            Bundle bundle = new Bundle();
            Intent intent = new Intent(HuiyuanlevelActivity.this, HuiyuansayActivity.class);
            //设置数据
            String uid = user.getId();
            //把数据保存到Bundle里
            Log.e("123touxiang", touxiang + "");
            bundle.putString("touxiang", touxiang);
            bundle.putString("uid", uid);
            bundle.putString("grade", String.valueOf(user.getGrade()));
            //把bundle放入intent里
            intent.putExtra("Message", bundle);
            startActivity(intent);
         /*  // Intent intent = new Intent(HuiyuanlevelActivity.this, HuiyuansayActivity.class);
            intent.putExtra("touxiang",touxiang);
            intent.putExtra("uid",user.getId());

            intent.putExtra("grade",  String.valueOf(user.getGrade()));
            startActivity(intent);*/
        }
    }

    /**
     * 会员升级
     */

    @OnClick(R.id.comit_shengji)
    public void action_comit_shengji(View v) {
        if (R.id.comit_shengji == v.getId()) {
            comitlevel();
        }


    }

    private void comitlevel() {
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);
        //  String sign = StringUtil.Md5Str(params, Constants.KEY);
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.UP_LEVEL)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .execute(new JsonCallback<BaseResponse<Object>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        Log.e("user", "进来了2!!!!");
                        if (body.getStatus().equals("0001")) {
//                            RxToast.showShort(body.getMessage());
                            lodaMessage();
                        }
                    }
                });


    }


}
