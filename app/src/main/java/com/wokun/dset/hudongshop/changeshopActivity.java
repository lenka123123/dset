package com.wokun.dset.hudongshop;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v13.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.wokun.dset.R;
import com.wokun.dset.callback.DialogCallback;
import com.wokun.dset.callback.JsonCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.response.BaseResponse2;
import com.wokun.dset.ucenter.bean.BankCardBean;
import com.wokun.dset.ucenter.quanyi.dashishop.bean.BaojiaBean;
import com.wokun.dset.utils.AppLocationUtils;
import com.wokun.dset.utils.LocationUtils;
import com.wokun.dset.utils.StringUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

public class changeshopActivity extends AppCompatActivity implements View.OnClickListener {
    /*    @BindView( R.id.finish)
        ImageView myfinish;*/
    private TabLayout mTab;
    private List<String> title;
    private VpAdapter vpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near);
        loadTitle();
        initView();
       // LocationUtils.getInstance().start(100, true, null);

    }

    private void initView() {
        ImageView mFinish = findViewById(R.id.finish);
        EditText mSearch = findViewById(R.id.search);
        mTab = findViewById(R.id.tablayout);
        ViewPager mVp = findViewById(R.id.viewpager);
        mFinish.setOnClickListener(this);
        mSearch.setOnClickListener(this);
        vpAdapter = new VpAdapter(getSupportFragmentManager());
        mVp.setAdapter(vpAdapter);
        mTab.setupWithViewPager(mVp);

    }

    private void loadTitle() {
        Map params = new HashMap();
        params.put("timestamp", StringUtil.getCurrentTime());
        Map<String, String> removeMap = removeEmptyData(params);
        Map<String, String> resultMap = sortMapByKey(removeMap);
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap ,params);
        OkGo.<BaseResponse2<String>>post(Constants.BASE_URL + Constants.BUSINESS)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .execute(new DialogCallback<BaseResponse2<String>>(this) {
                    @Override
                    public void onSuccess(Response<BaseResponse2<String>> response) {
                        BaseResponse2 body = response.body();
                        if(body == null)return;
                        if (body.getStatus().equals("0001")) {
                            RxToast.showShort(body.getMessage());
                           title= (List<String>) body.getData();
                            vpAdapter.notifyDataSetChanged();
                            Log.e("进来来dsa22","进来来dsa22"+title);
                        }
                    }
                });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.search:

                break;
            default:
                break;
        }
    }

    class VpAdapter extends FragmentStatePagerAdapter {
        //String[] title={"全部","化妆品","电子产品","日用百货","服饰衣帽"};

        public VpAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return Fragment_near.getInstance(i);
        }

        @Override
        public int getCount() {
            return title==null?0:title.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return title.get(position);
        }

        //        @Override
//        public int getItemPosition(@NonNull Object object) {
//            return POSITION_NONE;
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocationUtils.getInstance().start(1000, true, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocationUtils.getInstance().stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocationUtils.getInstance().stop();
    }
}