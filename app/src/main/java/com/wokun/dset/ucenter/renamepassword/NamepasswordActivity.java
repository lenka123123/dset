package com.wokun.dset.ucenter.renamepassword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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
public class NamepasswordActivity extends BaseBindingActivity {
    @BindView(R.id.icv)
    VerificationCodeView icv;
    private  String  inputContent;
    private  String  oldpwd;
    private   String phoneNum;
    @Override
    public int createView() {
        return R.layout.activity_namepassword;
    }

    @Override
    public WidgetBar createToolBar() {
        return null;
    }

    @Override
    public void init() {
        Intent intent = getIntent();
         phoneNum = intent.getStringExtra("phoneNum");
        icv.setInputCompleteListener(new VerificationCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                inputContent = icv.getInputContent();
                if (inputContent.length() == 6) {
                    oldpwd = inputContent;
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
    @OnClick(R.id.txt_next)
    public void action_txt_next(View v){
        if(R.id.txt_next == v.getId()){
            if(inputContent.length()==6){
                Bundle bundle = new Bundle();
                Intent intent=new Intent(NamepasswordActivity.this, NamepasswordActivity2.class);
                //把数据保存到Bundle里
                bundle.putString("oldpwd", oldpwd);
                bundle.putString("phoneNum",phoneNum);
                //把bundle放入intent里
                intent.putExtra("Message",bundle);
                startActivity(intent);
            }else {
                RxToast.showShort("请输入6位数密码");
            }


        }
    }


}
