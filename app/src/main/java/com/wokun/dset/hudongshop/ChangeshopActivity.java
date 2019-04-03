package com.wokun.dset.hudongshop;

import android.content.Intent;
import android.support.annotation.Nullable;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.wokun.dset.utils.LocationUtils;
import com.wokun.dset.utils.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;

public class ChangeshopActivity extends AppCompatActivity implements View.OnClickListener {
    /*    @BindView( R.id.finish)
        ImageView myfinish;*/
    private TabLayout mTab;
    private List<String> title;
    private VpAdapter vpAdapter;
    private TextView mSearch;

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
        mSearch = findViewById(R.id.search);
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
        String sign = LoginMgr.getInstance().getSign(removeMap, resultMap, params);
        OkGo.<BaseResponse2<String>>post(Constants.BASE_URL + Constants.BUSINESS)
                .params("sign", sign)
                .params("timestamp", StringUtil.getCurrentTime())
                .execute(new DialogCallback<BaseResponse2<String>>(this) {
                    @Override
                    public void onSuccess(Response<BaseResponse2<String>> response) {
                        BaseResponse2 body = response.body();
                        if (body == null) return;
                        if (body.getStatus().equals("0001")) {
                            title = (List<String>) body.getData();
                            vpAdapter.notifyDataSetChanged();
                            Log.e("进来来dsa22", "进来来dsa22" + title);
                        } else {
                            RxToast.showShort(body.getMessage());
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
                startActivity(new Intent(ChangeshopActivity.this, changeshopSearchActivity.class));
                break;
            default:
                break;
        }
    }

    private int neetType = 0;

    class VpAdapter extends FragmentStatePagerAdapter {
        //String[] title={"全部","化妆品","电子产品","日用百货","服饰衣帽"};

        public VpAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            neetType = i;
            return Fragment_near.getInstance(i, "");
        }

        @Override
        public int getCount() {
            return title == null ? 0 : title.size();
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
