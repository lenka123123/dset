//package com.example.baselib;
//
//import android.graphics.Color;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.AdapterView;
//import android.widget.CheckBox;
//import android.widget.PopupWindow;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public abstract class BaseActivity extends AppCompatActivity {
//
//    protected BaseActivity context;
//    private CommonFilterPop mPopupWindow;
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(setActivityLayout());
//        StatusBarCompat.setStatusBarColor(this, Color.WHITE, true);
//        context = this;
//        initView();
//        initData();
//    }
//
//    public abstract int setActivityLayout();
//
//    public void initView() {
//    }
//
//    public void initData() {
//    }
//
//
//
//    /**
//     * Tab筛选栏切换
//     *
//     * @param isChecked         选中状态
//     * @param showView          展示pop的跟布局
//     * @param showMes           展示选择的数据源
//     * @param itemClickListener 点击回调
//     * @param tabs              所有的cb(需要几个输入几个就可以,cb1,cb2....)
//     */
//    public void filterTabToggleT(boolean isChecked, View showView, List<? extends BaseFilter> showMes,
//                                 AdapterView.OnItemClickListener itemClickListener, final CheckBox... tabs) {
//        if (isChecked) {
//            if (tabs.length <= 0) {
////                return;
//            }
//            // 第一个checkBox为当前点击选中的cb,其他cb进行setChecked(false);
//
//            if (tabs.length >= 1)
//                for (int i = 1; i < tabs.length; i++) {
//                    tabs[i].setChecked(false);
//                }
//            // 从数据源中提取出展示的筛选条件
//            List<BaseBean> showStr = new ArrayList<>();
//            for (BaseFilter baseFilter : showMes) {
//                BaseBean baseBean = new BaseBean();
//                baseBean.id = baseFilter.getFilterId();
//                baseBean.name = baseFilter.getFilterStr();
//                showStr.add(baseBean);
//            }
//            showFilterPopupWindow(showView, showStr, itemClickListener, new CustomerDismissListener() {
//                @Override
//                public void onDismiss() {
//                    super.onDismiss();
//                    // 当pop消失时对第一个cb进行.setChecked(false)操作
//                    if (tabs.length >= 1)
//                        tabs[0].setChecked(false);
//                }
//            });
//        } else {
//            // 关闭checkBox时直接隐藏popuwindow
//            hidePopListView();
//        }
//    }
//
//
//
//    /**
//     * 列表选择popupWindow
//     *
//     * @param parentView        父View
//     * @param itemTexts         列表项文本集合
//     * @param itemClickListener 列表项点击事件
//     */
//    public void showFilterPopupWindow(View parentView,
//                                      List<BaseBean> itemTexts,
//                                      AdapterView.OnItemClickListener itemClickListener,
//                                      CustomerDismissListener dismissListener) {
//        showFilterPopupWindow(parentView, itemTexts, itemClickListener, dismissListener, 0);
//    }
//
//    /**
//     * 自定义OnDismissListener
//     */
//    public   class CustomerDismissListener implements PopupWindow.OnDismissListener {
//        @Override
//        public void onDismiss() {
//            // 当pop消失的时候,重置背景色透明度
//            WindowManager.LayoutParams lp =  context.getWindow().getAttributes();
//            lp.alpha = 1.0f;
//            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//            context.getWindow().setAttributes(lp);
//        }
//    }
//
//    /**
//     * 列表选择popupWindow
//     *
//     * @param parentView        父View
//     * @param itemTexts         列表项文本集合
//     * @param itemClickListener 列表项点击事件
//     */
//    public void showFilterPopupWindow(View parentView,
//                                      List<BaseBean> itemTexts,
//                                      AdapterView.OnItemClickListener itemClickListener,
//                                      CustomerDismissListener dismissListener, float alpha) {
//
//        // 判断当前是否显示
//        if (mPopupWindow != null && mPopupWindow.isShowing()) {
//            mPopupWindow.dismiss();
//            mPopupWindow = null;
//        }
//        mPopupWindow = new CommonFilterPop(context, itemTexts);
//        mPopupWindow.setOnDismissListener(dismissListener);
//        // 绑定筛选点击事件
//        mPopupWindow.setOnItemSelectedListener(itemClickListener);
//        // 如果透明度设置为0的话,则默认设置为0.6f
//        if (0 == alpha) {
//            alpha = 0.6f;
//        }
//        // 设置背景透明度
//        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
//        lp.alpha = alpha;
//        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        context.getWindow().setAttributes(lp);
//        // 显示pop
//        mPopupWindow.showAsDropDown(parentView);
//
//    }
//
//    /**
//     * 隐藏pop
//     */
//    public void hidePopListView() {
//        // 判断当前是否显示,如果显示则dismiss
//        if (mPopupWindow != null && mPopupWindow.isShowing()) {
//            mPopupWindow.dismiss();
//            mPopupWindow = null;
//        }
//    }
//}
