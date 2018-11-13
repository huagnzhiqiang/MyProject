package com.hzq.baselibs.base;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.hzq.baselibs.Bean.MessageEvent;
import com.hzq.baselibs.R;
import com.hzq.baselibs.app.BaseApplication;
import com.hzq.baselibs.receiver.NetWorkChangeBroadcastReceiver;
import com.hzq.baselibs.view.MultipleStatusView;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.RefWatcher;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author 小强
 * @time 2018/6/9 17:12
 * @desc 基类 BaseMvpActivity
 */
public abstract class BaseActivity extends BaseSwipeBackActivity {
    private Unbinder unBinder;

    //    private ProgressDialog mLoadingDialog = null;
    protected MultipleStatusView mLayoutStatusView = null;//布局显示(空布局/请求布局/无网络布局)
    private Dialog mLoadingDialog = null;

    public ImmersionBar mImmersionBar;//沉浸式状态栏和沉浸式

    private NetWorkChangeBroadcastReceiver receiver;//无网络广播


    private TextView mToolbarTitle;//中间的title
    private TextView mToolbarRightTitle;//右边的title
    private ImageView mToolbarRigthImg;//右边的图片

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化沉浸式
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }

        //初始化EventBus
        if (useEventBus()) {
            EventBus.getDefault().register(this);//注册eventBus
        }


        setContentView(getLayoutId());
        Intent intent = getIntent();
        if (intent != null) {
            getIntent(intent);
        }

        unBinder = ButterKnife.bind(this);

        //初始化公用view
        initBaseView();

        initView();
        initData();
        initListener();
        networkRequest();
        //        registerNetChangeReceiver();
    }


    /**
     * 无网络广播
     */
    private void registerNetChangeReceiver() {
        receiver = new NetWorkChangeBroadcastReceiver(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, intentFilter);
    }

    /**
     * 初始化沉浸式状态栏和沉浸式
     */
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.fitsSystemWindows(true);
        mImmersionBar.statusBarColor(R.color.color_61bef4).init();
        mImmersionBar.init();
    }

    /**
     * 是否可以使用沉浸式
     *
     * @return ture-->使用 false-->不使用
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }


    /**
     * 是否使用eventBus
     */
    protected boolean useEventBus() {
        return false;
    }

    /**
     * 接受EventBus 广播
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent messageEvent) {
    }


    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
    }

    /**
     * 覆写finish方法，覆盖默认方法，加入切换动画
     */
    public void finishActivity() {
        super.finish();
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
    }


    /**
     * 显示带消息的进度框
     *
     * @param msg 提示
     */
    protected void showLoadingDialog(String msg) {
        Logger.d("showLoadingDialog--->:" + msg);
        createLoadingDialog(msg);
        if (!mLoadingDialog.isShowing())
            mLoadingDialog.show();
    }

    /**
     * 显示进度框
     */
    protected void showLoadingDialog() {
        createLoadingDialog("正在加载...");
        if (!mLoadingDialog.isShowing())
            mLoadingDialog.show();
    }

    /**
     * 创建LoadingDialog
     */
    private void createLoadingDialog(String msg) {
        if (mLoadingDialog == null) {
            LayoutInflater inflater = LayoutInflater.from(this);
            View v = inflater.inflate(R.layout.dialog_loading_view, null);
            RelativeLayout layout = (RelativeLayout) v.findViewById(R.id.dialog_view);
            ImageView mLoadImg = (ImageView) v.findViewById(R.id.loading_img);
            TextView mLoadText = (TextView) v.findViewById(R.id.loading_txt);
            AnimationDrawable mDrawable = (AnimationDrawable) mLoadImg.getDrawable();
            mDrawable.start();
            mLoadText.setText(msg);
            mLoadingDialog = new Dialog(this, R.style.loading_dialog);
            mLoadingDialog.setCancelable(true);
            mLoadingDialog.setCanceledOnTouchOutside(false);
            mLoadingDialog.setContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        }
    }

    public static Dialog createLoadingDialog(Context context, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_loading_view, null);
        RelativeLayout layout = (RelativeLayout) v.findViewById(R.id.dialog_view);
        ImageView mLoadImg = (ImageView) v.findViewById(R.id.loading_img);
        TextView mLoadText = (TextView) v.findViewById(R.id.loading_txt);
        AnimationDrawable mDrawable = (AnimationDrawable) mLoadImg.getDrawable();
        mDrawable.start();
        mLoadText.setText(msg);
        final Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);
        loadingDialog.setCancelable(true);
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        loadingDialog.show();
        return loadingDialog;
    }

    /**
     * 隐藏进度框
     */
    protected void hideLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }

    //初始化公用view
    private void initBaseView() {
        mToolbarTitle = (TextView) ButterKnife.findById(this, R.id.toolbar_title);
        mToolbarRightTitle = (TextView) ButterKnife.findById(this, R.id.tv_toolbar_right);
        mToolbarRigthImg = (ImageView) ButterKnife.findById(this, R.id.toolbar_right_img);


        //无网络/请求出现了问题布局
        mLayoutStatusView = ButterKnife.findById(this, R.id.multipleStatusView);
        if (mLayoutStatusView != null) {
            mLayoutStatusView.setOnRetryClickListener(layoutStatusViewOnclick);
        }
    }


    /**==================初始化Toolbar开始=====================*/

    /**
     * 设置标题
     *
     * @param text 标题
     */
    public void setToolbarTitle(CharSequence text) {
        if(mToolbarTitle!=null) {
            mToolbarTitle.setText(text);

        }
    }

    /**
     * 设置标题
     *
     * @param text  标题
     * @param color 颜色值
     */
    public void setToolbarTitle(CharSequence text, @ColorInt int color) {

        if(mToolbarTitle!=null) {
            mToolbarTitle.setText(text);

        }
        if (color != 0) {
            mToolbarTitle.setTextColor(color);
        }
    }


    /**
     * 设置右边的标题
     *
     * @param title 标题
     * @param color 颜色值
     */
    protected void setToolRightTitle(String title, @ColorInt int color) {

        if (mToolbarRightTitle != null) {
            mToolbarRigthImg.setVisibility(View.GONE);
            mToolbarRightTitle.setVisibility(View.VISIBLE);
            mToolbarRightTitle.setText(title);
        }
        if (color != 0) {
            mToolbarRightTitle.setTextColor(color);
        } else {
            mToolbarRightTitle.setTextColor(getResources().getColor(R.color.color_eb6ea5));

        }

    }

    /**
     * 设置右边的标题
     *
     * @param title 标题
     */
    protected void setToolRightTitle(String title) {

        if (mToolbarRightTitle != null) {
            mToolbarRigthImg.setVisibility(View.GONE);
            mToolbarRightTitle.setVisibility(View.VISIBLE);
            mToolbarRightTitle.setText(title);
            mToolbarRightTitle.setTextColor(getResources().getColor(R.color.color_eb6ea5));
        }

    }

    /**
     * 设置右边的图片
     *
     * @param resId 图片文件
     */
    protected void setToolRightImg(@DrawableRes int resId) {


        if (mToolbarRigthImg != null) {
            mToolbarRightTitle.setVisibility(View.GONE);
            mToolbarRigthImg.setVisibility(View.VISIBLE);
            mToolbarRigthImg.setImageResource(resId);
        }
    }

    /** ==================初始化Toolbar结束===================== */


    /**
     * 全局关闭页面监听
     */
    public void onBack(View view) {
        finishActivity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unBinder != null) {
            unBinder.unbind();
        }

        //在BaseActivity里销毁沉浸式
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }

        if (useEventBus()) {
            if (EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this);//注销eventBus
            }
        }

        //销毁广播(无网络通知广播)
        if (null != receiver) {
            receiver.onDestroy();
            unregisterReceiver(receiver);
            receiver = null;
        }

        //销毁检查内存泄露问题插件
        initLeakCanary();
    }

    /**
     * 用来检测所有Fragment的内存泄漏
     */
    private void initLeakCanary() {
        RefWatcher refWatcher = BaseApplication.getRefWatcher(this);
        refWatcher.watch(this);
    }


    /**
     * 获取布局 Id
     */
    protected abstract @LayoutRes
    int getLayoutId();

    /**
     * 获取 Intent 数据
     */
    protected void getIntent(Intent intent) {
    }

    /**
     * 初始数据的代码写在这个方法中，用于从服务器获取数据
     */
    protected void initData() {
    }


    /**
     * 初始化View的代码写在这个方法中
     */
    protected void initView() {
    }

    /**
     * 初始化监听器的代码写在这个方法中
     */
    protected void initListener() {
    }


    /**
     * 请求网络
     */
    protected abstract void networkRequest();


    /**
     * 错误信息页面点击事件(无网络/获取数据出错...)
     */
    private View.OnClickListener layoutStatusViewOnclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showLoadingDialog();
            networkRequest();
        }
    };

}
