package com.wokun.dset.ucenter.renamepassword;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseBindingActivity;
import com.wokun.dset.ucenter.zhifudiaolog.VerificationCodeView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 重置支付密码
 * */
public class NamepasswordActivity22 extends BaseBindingActivity {
      private  String  inputContent;
      private    String mobilecode;
      private  String  newpwd;
      private  String phoneNum;
    @BindView(R.id.icv)
    VerificationCodeView icv;
    @Override
    public int createView() {
        return R.layout.activity_namepassword2;
    }

    @Override
    public WidgetBar createToolBar() {
        return null;
    }

    @Override
    public void init() {
        Intent intent = getIntent();
        //从intent取出bundle
        mobilecode = intent.getStringExtra("mobilecode");
        phoneNum = intent.getStringExtra("phoneNum");

        icv.setInputCompleteListener(new VerificationCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                inputContent = icv.getInputContent();
                if (inputContent.length() == 6) {
                    newpwd = inputContent;
                }
                Log.i("icv_input", icv.getInputContent());
            }

            @Override
            public void deleteContent() {
                Log.i("icv_delete", icv.getInputContent());
            }
        });

    }

    /** 重置*/
    @OnClick(R.id.txt_next2)
    public void action_txt_next2(View v){
        if(R.id.txt_next2 == v.getId()){
            if(inputContent.length()==6){

                Intent intent=new Intent(NamepasswordActivity22.this, NamepasswordActivity33.class);
                intent.putExtra("mobilecode",mobilecode);
                intent.putExtra("newpwd",newpwd);
                intent.putExtra("phoneNum",phoneNum);

                startActivity(intent);
            }else {
                RxToast.showShort("请输入6位数密码");
            }


        }
    }



}
