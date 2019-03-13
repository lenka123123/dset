package com.wokun.dset.ucenter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itheima.view.BridgeWebView;
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
import com.wokun.dset.response.BaseResponse2;
import com.wokun.dset.ucenter.bean.AboutBean;
import com.wokun.dset.ucenter.bean.AboutusBean;
import com.wokun.dset.utils.ImageLoader;
import com.wokun.dset.utils.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

public class AboutUsActivity extends BaseBindingActivity {
/*   @BindView(R.id.bridge_web_view)
   BridgeWebView bridge_web_view;*/

    @Override
    public int createView() {
        return R.layout.activity_about_us;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("关于我们");
    }

    @Override
    public void init() {
       // bridge_web_view.loadUrl("http://api.dasether.com/user/about.html");
        loadData();
    }

    private void loadData() {
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
        OkGo.<BaseResponse<AboutusBean>>post("http://api.dasether.com/user/about-us.html")
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .execute(new JsonCallback<BaseResponse<AboutusBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<AboutusBean>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        Log.e("关于我们","进来了2!!!!");
                        if (body.getStatus().equals("0001")) {
                            RxToast.showShort(body.getMessage());
                            AboutusBean data = (AboutusBean) body.getData();
                            //   List<AboutusBean> aboutusBean =( List<AboutusBean>) body.getData();
                            List<AboutBean> aboutusBean = data.getAbout();
                            Log.e("关于我们",data.toString()+"!!!!");
                            if(aboutusBean == null){return;}

                            for (int i = 0; i < aboutusBean.size(); i++) {
                                LinearLayout view1 = (LinearLayout)findViewById(R.id.linearlayout2);

                                View view = LayoutInflater.from(AboutUsActivity.this).inflate(R.layout.item_about_us, null);
                                TextView mTitle = view.findViewById(R.id.item_aboutus_title);
                                LinearLayout mTitle2 =(LinearLayout) view.findViewById(R.id.my_linear);

                                mTitle.setText(aboutusBean.get(i).getTitle());
                                final String url = aboutusBean.get(i).getUrl();
                                final String title = aboutusBean.get(i).getTitle();

                                mTitle2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        // RxToast.showShort("dianjil");
                                        Intent intent = new Intent(AboutUsActivity.this, AboutWebActivity.class);
                                        intent.putExtra("url",url);
                                        intent.putExtra("title",title);
                                        startActivity(intent);
                                    }
                                });

                                view1.addView(view);

                            }





                        }
                    }
                    @Override
                    public void onError(Response response) {
                        dismissLP();
                        super.onError(response);
                        Log.e("关于我们",response+"!!!!");
                    }
                });





    }


    /**
     * 特别声明
     */
    @OnClick(R.id.about_us_shenming)
    public void actionService(View view) {

    }


    /**
     * 用户协议
     *
     */
    @OnClick(R.id.about_us_xieyi)
    public void actionXieyi(View view) {

    }

}
