package com.hzq.baselibs.base;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
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
import com.hzq.baselibs.receiver.NetWorkChangeBroadcastReceiver;
import com.hzq.baselibs.view.MultipleStatusView;
import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

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
public abstract class BaseActivity extends RxAppCompatActivity {
    private Unbinder unBinder;

    //    private ProgressDialog mLoadingDialog = null;
    protected MultipleStatusView mLayoutStatusView = null;//布局显示(空布局/请求布局/无网络布局)
    private Dialog mLoadingDialog = null;

    public ImmersionBar mImmersionBar;//沉浸式状态栏和沉浸式

    private NetWorkChangeBroadcastReceiver receiver;//无网络广播


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化沉浸式
        if (isImmersionBarEnabled())
            initImmersionBar();

        //初始化EventBus
        if (useEventBus()) {
            EventBus.getDefault().register(this);//注册eventBus
        }


        setContentView(getLayoutId());
        Intent intent = getIntent();
        if (intent != null)
            getIntent(intent);

        unBinder = ButterKnife.bind(this);

        //无网络/请求出现了问题布局
        mLayoutStatusView = ButterKnife.findById(this, R.id.multipleStatusView);
        if (mLayoutStatusView != null) {
            mLayoutStatusView.setOnRetryClickListener(layoutStatusViewOnclick);
        }

        initData();
        initView();
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
    public void onEvent(MessageEvent messageEvent) {}


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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unBinder != null) {
            unBinder.unbind();
        }

        //在BaseActivity里销毁沉浸式
        if (mImmersionBar != null)
            mImmersionBar.destroy();

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
    }


    /**
     * 获取布局 Id
     */
    protected abstract @LayoutRes
    int getLayoutId();

    /**
     * 获取 Intent 数据
     */
    protected void getIntent(Intent intent) {}

    /**
     * 初始数据的代码写在这个方法中，用于从服务器获取数据
     */
    protected void initData() {}


    /**
     * 初始化View的代码写在这个方法中
     */
    protected void initView() {}

    /**
     * 初始化监听器的代码写在这个方法中
     */
    protected void initListener() {}


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
