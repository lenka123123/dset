package com.wokun.dset.ucenter.addcards;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseBindingActivity;
import com.wokun.dset.callback.DialogCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.response.BaseResponse2;
import com.wokun.dset.utils.StringUtil;
import com.wokun.dset.utils.Test5;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.widget.WheelView;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

/**
 *
 * 添加银行卡
 */

public class BankTestActivity2 extends BaseBindingActivity {
    //添加银行卡
    @BindString(R.string.tysl_add_card)String title;
    @BindView(R.id.card_poople)
    EditText card_poople;
    @BindView(R.id.card_kaihu_place)
    EditText card_kaihu_place;
    @BindView(R.id.card_poople_place)
    TextView card_poople_place;
    @BindView(R.id.card_people_number)
    EditText card_people_number;
    private List<String> mytitle;

    @Override
    public int createView() {
        return R.layout.activity_zhihui_addcard;
    }

    @Override
    public WidgetBar createToolBar()  {
       return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        loadMessage();
        card_poople_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OptionPicker picker = new OptionPicker(BankTestActivity2.this, mytitle); //list为选择器中的选项

                picker.setCycleDisable(false);//不禁用循环
                picker.setTopBackgroundColor(0xFFEEEEEE);
                picker.setTopHeight(30);
                picker.setTopLineColor(0xFFEE0000);
                picker.setTopLineHeight(1);
              //  picker.setTitleText(isChinese ? "请选择" : "Please pick");
                picker.setTitleTextColor(0xFF999999);
                picker.setTitleTextSize(12);
                picker.setCancelTextColor(0xFFEE0000);
                picker.setCancelTextSize(13);
                picker.setSubmitTextColor(0xFFEE0000);
                picker.setSubmitTextSize(13);
                picker.setTextColor(0xFFEE0000, 0xFF999999);
                WheelView.DividerConfig config = new WheelView.DividerConfig();
                config.setColor(0xFFEE0000);//线颜色
                config.setAlpha(140);//线透明度
                config.setRatio((float) (1.0 / 8.0));//线比率
                picker.setDividerConfig(config);
                picker.setBackgroundColor(0xFFE1E1E1);
                //picker.setSelectedItem(isChinese ? "处女座" : "Virgo");
                picker.setSelectedIndex(7);
                picker.setCanceledOnTouchOutside(true);
                picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        card_poople_place.setText(item); //在文本框中显示选择的选项
                    }
                });
                picker.show();
            }
        });

    }


    private void loadMessage() {
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
        OkGo.<BaseResponse2<String>>post(Constants.BASE_URL + Constants.BANK_TYPE)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .execute(new DialogCallback<BaseResponse2<String>>(this) {
                    @Override
                    public void onSuccess(Response<BaseResponse2<String>> response) {
                        BaseResponse2 body = response.body();
                        if(body == null)return;
                        if (body.getStatus().equals("0001")) {
                            RxToast.showShort(body.getMessage());
                            mytitle= (List<String>) body.getData();
                            Log.e("进来来2e2","进来来dsa22"+title);
                        }
                    }
                });





    }

    private void lodeData() {
        String cardPoople = card_poople.getText().toString().trim();//持卡人
        String cardKaihuPhone = card_kaihu_place.getText().toString().trim();     //  手机号
        String cardPooplePlace = card_poople_place.getText().toString().trim(); //  开户行
        String cardPeopleNumber = card_people_number.getText().toString().trim(); // 卡号
        if(TextUtils.isEmpty(cardPoople)) {
            RxToast.showShort("持卡人姓名不能为空");
                 return;
             }
        if(TextUtils.isEmpty(cardKaihuPhone))
        {
            RxToast.showShort("手机号不能为空");
            return;
        }

        if(TextUtils.isEmpty(cardPooplePlace)) {
            RxToast.showShort("开户行不能为空");
            return;
        }

        if(Test5.checkBankCard(cardPeopleNumber)){
            RxToast.showShort("卡号不完整");

            return;
        }
        Map params = new HashMap();
        params.put("username", cardPoople);
        params.put("bank_number", cardPeopleNumber);
        params.put("phone", cardKaihuPhone);
        params.put("bank", cardPooplePlace);
        params.put("timestamp", StringUtil.getCurrentTime());
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);


        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.BANK_CARD_ADD)
                .tag(this)
                .params("sign",sign)
                .params("username", cardPoople)
                . params("bank_number", cardPeopleNumber)
                .params("phone", cardKaihuPhone)
                .params("bank", cardPooplePlace)
                . params("timestamp", StringUtil.getCurrentTime())
                .execute(new DialogCallback<BaseResponse<Object>>(this) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if (body.getStatus().equals("0001")) {
                            RxToast.showShort(body.getMessage());
                            finish();
                            startActivity(TixianMoneyActivity2.class);
                        }
                    }
                });


    }
   @OnClick(R.id.action_submit)
        public void Sumbitcard() {
      //  startActivity(HistoryKehuActivity.class);
           lodeData();



    }
}
