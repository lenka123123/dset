package com.wokun.dset.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itheima.roundedimageview.RoundedImageView;
import com.itheima.view.BridgeWebView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.DsetApp;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseBindingActivity;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.utils.ImageLoader;
import com.wokun.dset.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

public class HuiyuansayActivity extends BaseBindingActivity {
/*     @BindView(R.id.bridge_web_view)
     BridgeWebView bridge_web_view;*/
    @BindView(R.id.web)
    WebView   webView;
    @BindView(R.id.user_head_img)
    RoundedImageView user_head_img;
    @BindView(R.id.my_id)
    TextView my_id;
    @BindView(R.id.star)
    LinearLayout star;

    @Override
    public int createView() {
        return R.layout.activity_huiyuansay;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("会员说明");
    }

    @Override
    public void init() {

        Intent intent = getIntent();
        //从intent取出bundle
        Bundle bundle = intent.getBundleExtra("Message");
        //获取数据
        String touxiang = bundle.getString("touxiang");
        String uid = bundle.getString("uid");
        String grade = bundle.getString("grade");
        Log.e("123touxiang1",touxiang+"");
        ImageLoader.loadImage(touxiang,user_head_img);
        my_id.setText("UID:"+uid);


        setUser(Integer.valueOf(grade));
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
        //  String sign = StringUtil.Md5Str(params, Constants.KEY);
        OkGo.<BaseResponse<LvealBean>>post(Constants.BASE_URL + Constants.USER_GRADE)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .execute(new JsonCallback<BaseResponse<LvealBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<LvealBean>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        Log.e("user","进来了2!!!!");
                        if (body.getStatus().equals("0001")) {
//                            RxToast.showShort(body.getMessage());
                            LvealBean data = (LvealBean) body.getData();
                          //  bridge_web_view.loadUrl(data.getContent());

                            WebSettings s = webView.getSettings();
                            s.setJavaScriptEnabled(true);
                            s.setAllowFileAccess(true);// 设置允许访问文件数据
                            s.setSupportZoom(true);
                            s.setBuiltInZoomControls(true);
                            s.setJavaScriptCanOpenWindowsAutomatically(true);
                            s.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
                            s.setDomStorageEnabled(true);
                            s.setDatabaseEnabled(true);
                            webView.setWebChromeClient(new WebChromeClient());
                            webView.setWebViewClient(new WebViewClient(){//避免调用外部浏览器
                                @Override
                                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                    view.loadUrl(url);
                                    return true;
                                }
                            });
                            Log.e("webview加载的url",data.getContent());

                            webView.loadUrl(data.getContent());



                        }
                    }
                    @Override
                    public void onError(Response response) {
                        dismissLP();
                        super.onError(response);
                        Log.e("user",response+"!!!!");
                        DsetApp.getInstance().setRefreshShopCart(false);
                    }
                });

    }

    private void setUser(int grade) {
        star.removeAllViews();
        if (grade == 0){
            TextView textView = new TextView(this);
            textView.setTextColor(getResources().getColor(R.color.yellow));
            textView.setTextSize(12);
            textView.setText("普通");
            star.addView(textView);
        }else if (grade>1){

            for (int j = 0;j<(grade)/5;j++){
                ViewGroup.LayoutParams layoutParams =  new ViewGroup.LayoutParams(32,32);
                ImageView imageView = new ImageView(this);
                imageView.setLayoutParams(layoutParams);
                imageView.setImageResource(R.drawable.shouye_huangguan);
                star.addView(imageView);
            }
            for (int i =0;i<(grade)%5;i++){
                ViewGroup.LayoutParams layoutParams =  new ViewGroup.LayoutParams(32,32);
                ImageView imageView = new ImageView(this);
                imageView.setLayoutParams(layoutParams);
                imageView.setImageResource(R.drawable.shouye_xinxin);
                star.addView(imageView);
            }
        }




    }


}
