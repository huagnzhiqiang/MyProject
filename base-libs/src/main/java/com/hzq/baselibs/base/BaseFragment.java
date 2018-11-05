package com.hzq.baselibs.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.barlibrary.ImmersionBar;
import com.hzq.baselibs.Bean.MessageEvent;
import com.hzq.baselibs.R;
import com.hzq.baselibs.app.BaseApplication;
import com.hzq.baselibs.mvp.BasePresenter;
import com.hzq.baselibs.mvp.IView;
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
 * @desc fragment 基类
 */
public abstract class BaseFragment<T extends BasePresenter> extends BaseLazyFragment implements IView {

    /**
     * 将代理类通用行为抽出来
     */
    protected T mPresenter;

    private Unbinder unBinder;

    protected BaseActivity mActivity;
    protected MultipleStatusView mLayoutStatusView;

    protected ImmersionBar mImmersionBar;//沉浸式状态栏和沉浸式


    /**
     * 缓存Fragment view
     */
    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), null);
            unBinder = ButterKnife.bind(this, mRootView);


            //无网络/请求出现了问题布局
            mLayoutStatusView = ButterKnife.findById(mRootView, R.id.multipleStatusView);
            if (mLayoutStatusView != null) {
                mLayoutStatusView.setOnRetryClickListener(layoutStatusViewOnclick);
            }
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        initView();
        return mRootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = createPresenter();

        if (mPresenter != null) {
            mPresenter.attachView(this);
        }

        //初始化沉浸式
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }

        if (useEventBus()) {
            EventBus.getDefault().register(this);//注册eventBus
        }
        Bundle arguments = getArguments();
        if (arguments != null) {
            getBundle(arguments);
        }

        initData();
        initListener();
    }

    /**
     * 获取 Bundle 数据
     */
    protected void getBundle(Bundle arguments) {
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.mActivity = (BaseActivity) this.getActivity();
        }
    }


    /**
     * 获取根view
     */
    protected View getRootView() {
        return mRootView;
    }

    /**
     * 初始化沉浸式状态栏和沉浸式
     */
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.fitsSystemWindows(true);
        mImmersionBar.statusBarColor(R.color.color_61bef4);
        mImmersionBar.navigationBarWithKitkatEnable(false);
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (unBinder != null) {
            unBinder.unbind();
        }

        //销毁沉浸式
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }


        if (useEventBus()) {
            if (EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this);//注销eventBus
            }
        }

        //销毁检查内存泄露问题插件
        initLeakCanary();
    }

    /** ==================Fragment中使用沉浸式设置属性===================== */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && mImmersionBar != null)
            mImmersionBar.init();
    }


    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    @Override
    public void showLoading() {
        Logger.d("showLoading--->:");
        mActivity.showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        mActivity.hideLoadingDialog();
    }

    /**
     * 显示带消息的进度框
     *
     * @param msg 提示
     */
    protected void showLoading(String msg) {
        mActivity.showLoadingDialog(msg);
    }


    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        mActivity.startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        mActivity.startActivityForResult(cls, null, requestCode);
    }


    /**
     * 覆写finish方法，覆盖默认方法，加入切换动画
     */
    public void finishActivity() {
        mActivity.finishActivity();
    }


    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(getBaseActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getBaseActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 用来检测所有Fragment的内存泄漏
     */
    private void initLeakCanary() {
        RefWatcher refWatcher = BaseApplication.getRefWatcher(getContext());
        refWatcher.watch(this);
    }


    /**
     * 返回一个用于显示界面的布局id
     */
    protected abstract @LayoutRes
    int getLayoutId();

    /**
     * 创建Presenter
     *
     * @return 返回当前的Presenter
     */
    protected abstract T createPresenter();

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
     * 初始数据的代码写在这个方法中，用于从服务器获取数据
     */
    protected void initData() {
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
     * 错误信息页面点击事件(无网络/获取数据出错...)
     **/
    private View.OnClickListener layoutStatusViewOnclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mActivity.showLoadingDialog();
            onLazyLoad();

        }
    };


}
