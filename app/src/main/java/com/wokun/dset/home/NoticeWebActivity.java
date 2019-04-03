package com.wokun.dset.home;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.shantoo.widget.view.ProgressWebView;
import com.wokun.dset.DsetApp;
import com.wokun.dset.MainActivity;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseBindingActivity;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;

import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

/**
 * Created by Administrator on 2019\1\29 0029.
 */

public class NoticeWebActivity extends BaseBindingActivity {
    @BindView(R.id.notic_time)
    TextView notic_time;
    @BindView(R.id.notic_title)
    TextView notic_title;
    @BindView(R.id.notic_content)
    TextView notic_content;

    @Override
    public int createView() {
        return R.layout.layout_noticeweb;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("公告");
    }

    @Override
    public void init() {
        String id = getIntent().getStringExtra("id");
        Map params = new HashMap();
        params.put("id", id);
        params.put("timestamp", StringUtil.getCurrentTime());
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);
        //  String sign = StringUtil.Md5Str(params, Constants.KEY);
        Log.e("user", sign + "!!!!" + id);
        OkGo.<BaseResponse<NoticeDetailBean>>post(Constants.BASE_URL + Constants.NOTICE_DETAIL)
                .tag(this)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .params("id", id)
                .execute(new JsonCallback<BaseResponse<NoticeDetailBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<NoticeDetailBean>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        Log.e("user", "进来了2!!!!");
                        if (body.getStatus().equals("0001")) {
//                            RxToast.showShort(body.getMessage());
                            NoticeDetailBean user = (NoticeDetailBean) body.getData();
                            Log.e("user", user + "!!!!");
                            if (notic_time == null || user == null) return;
                            notic_time.setText(user.getCreated_at());
                            //   notic_content.setText(user.getContent());
                            if (user.getContent() == null) {
                                notic_content.setText("");
                            } else {
                                CharSequence charSequence = Html.fromHtml(user.getContent());//支持html
                                notic_content.setText(charSequence);
                                notic_content.setMovementMethod(LinkMovementMethod.getInstance());//可以链接
                            }
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
}
