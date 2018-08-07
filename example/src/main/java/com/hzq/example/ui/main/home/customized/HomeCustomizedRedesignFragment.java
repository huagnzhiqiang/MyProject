package com.hzq.example.ui.main.home.customized;

import android.support.v4.app.Fragment;

import com.hzq.baselibs.base.BaseFragment;
import com.hzq.baselibs.mvp.BasePresenter;
import com.hzq.example.R;
import com.orhanobut.logger.Logger;

/**
 * @author 小强
 * @time 2018/8/2  15:28
 * @desc 首页定制页面
 */
public class HomeCustomizedRedesignFragment extends BaseFragment {
    /**
     * 返回一个用于显示界面的布局id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.home_template_redesign_fragment;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    /**
     * 初始化View的代码写在这个方法中
     */
    @Override
    protected void initView() {

    }

    /**
     * 初始化监听器的代码写在这个方法中
     */
    @Override
    protected void initListener() {

    }

    /**
     * 初始数据的代码写在这个方法中，用于从服务器获取数据
     */
    @Override
    protected void initData() {
        Logger.d("首页定制页面initData--->:");

    }

    /**
     * 请求网络
     */
    @Override
    public void onLazyLoad() {

        Logger.d("首页定制页面--->:");
    }



    /** ==================初始化fragment===================== */
    public static Fragment newInstance() {
        return new HomeCustomizedRedesignFragment();
    }

    /**
     * 显示错误
     *
     * @param msg  错误信息
     * @param code 错误code
     */
    @Override
    public void showError(String msg, int code) {

    }

    /**
     * 显示网络错误
     *
     * @param msg  错误信息
     * @param code 错误code
     */
    @Override
    public void showNetworkError(String msg, int code) {

    }
}
