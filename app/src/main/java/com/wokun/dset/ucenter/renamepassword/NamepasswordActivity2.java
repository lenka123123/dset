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
public class NamepasswordActivity2 extends BaseBindingActivity {
     private    String oldpwd;
      private  String  inputContent;
      private  String phoneNum;
      private  String  newpwd;
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
        Bundle bundle = intent.getBundleExtra("Message");
        //获取数据
            oldpwd = bundle.getString("oldpwd");
            phoneNum = bundle.getString("phoneNum");
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
                Bundle bundle = new Bundle();
                Intent intent=new Intent(NamepasswordActivity2.this, NamepasswordActivity3.class);
                //把数据保存到Bundle里
                bundle.putString("oldpwd", oldpwd);
                bundle.putString("phoneNum",phoneNum);
                bundle.putString("newpwd", newpwd);
                //把bundle放入intent里
                intent.putExtra("Message1",bundle);
                startActivity(intent);
            }else {
                RxToast.showShort("请输入6位数密码");
            }


        }
    }



}
