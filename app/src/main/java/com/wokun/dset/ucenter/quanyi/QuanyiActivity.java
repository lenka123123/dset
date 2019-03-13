package com.wokun.dset.ucenter.quanyi;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.shantoo.common.utils.UIUtil;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.dset.R;
import com.wokun.dset.base.BaseBindingActivity;
import com.wokun.dset.ucenter.quanyi.dashishop.GoodsOrderActivity;
import com.wokun.dset.utils.PopWindow;

import java.util.ArrayList;

import butterknife.BindString;
import butterknife.BindView;

public class QuanyiActivity extends BaseBindingActivity {
    //@BindString(R.string.tysl_user_my_message)String title;
    @BindView(R.id.rl_more)ImageView rl_more;
    @BindView(R.id.rl_more1)RelativeLayout rl_more1;
    @BindView(R.id.back)ImageView back;
    @BindView(R.id.tab_layout)TabLayout mTabLayout;
    @BindView(R.id.view_pager)ViewPager mViewPager;
   // @BindView(R.id.right_button_icon1)ImageView right_button_icon1;

    PopupWindow popupWindow;
    @Override
    public int createView() {
        return R.layout.activity_quanyi;
    }

    @Override
    public WidgetBar createToolBar() {

        mWidgetBar.setVisibility(View.GONE);

      return  mWidgetBar;
    }

    private void showpop() {
        PopWindow popWindow = new PopWindow(this);
        popWindow.showPopupWindow(findViewById(R.id.rl_more));
    }

    private void showpopmum() {

        // 获取自定义的菜单布局文件
        View menu_view=getLayoutInflater().inflate(R.layout.pop_mum,null,false);
        // 创建PopupWindow实例,设置菜单宽度和高度为包裹其自身内容
        popupWindow=new PopupWindow(menu_view,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,true);
        //设置菜单显示在按钮的下面
        popupWindow.showAsDropDown(rl_more,0,10);

        menu_view.findViewById(R.id.pop_baojia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                //报价记录
                /** 报价记录
                 * */
                startActivity(BaijiaDetailsActivity.class);
            }
        });
        menu_view.findViewById(R.id.pop_dindan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                //订单状态
                startActivity(GoodsOrderActivity.class);
            }
        });



        // 点击其他地方消失
        menu_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //如果菜单存在并且为显示状态，就关闭菜单并初始化菜单
                if (popupWindow!=null&&popupWindow.isShowing()){
                    popupWindow.dismiss();
                    popupWindow=null;
                }
                return false;
            }
        });

    }

    @Override
    public void init() {
        // 创建一个集合,装填Fragment
        ArrayList<Fragment> fragments = new ArrayList<>();
        // 装填
        fragments.add(new Mairufragment());
        fragments.add(new Maichufragment());
        // 创建ViewPager适配器
        MyMessageFragmentAdapter myPagerAdapter = new MyMessageFragmentAdapter(getSupportFragmentManager());
        myPagerAdapter.setFragments(fragments);

        // 给ViewPager设置适配器
        mViewPager.setAdapter(myPagerAdapter);
        // TabLayout 指示器 (记得自己手动创建4个Fragment,注意是 app包下的Fragment 还是 V4包下的 Fragment)
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        // 使用 TabLayout 和 ViewPager 相关联
        mTabLayout.setupWithViewPager(mViewPager);
        // TabLayout指示器添加文本
        //商品收藏","店铺收藏","顾问收藏","文章收藏
        mTabLayout.getTabAt(0).setText("买入");
        mTabLayout.getTabAt(1).setText("卖出");


        rl_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  showpop();
                showpopmum();
            }
        });
        rl_more1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  showpop();
                showpopmum();
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
