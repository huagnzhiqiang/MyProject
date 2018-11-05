package com.hzq.example.ui.main.myWork.fragment;

import com.hzq.baselibs.base.BaseFragment;
import com.hzq.baselibs.mvp.BasePresenter;
import com.hzq.example.R;
import com.orhanobut.logger.Logger;

/**
 * @author 小强
 * @time 2018/11/2  14:34
 * @desc 推广中的 全部 待接单 进行中 已取消 已完成 页面
 */
public class ExtensionFragment extends BaseFragment {
    /**
     * 返回一个用于显示界面的布局id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_extensions;
    }

    /**
     * 创建Presenter
     *
     * @return 返回当前的Presenter
     */
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    /**
     * 初始化懒加载的数据 (请求网络)
     */
    @Override
    public void onLazyLoad() {
        Logger.d("onResume onLazyLoad--->:请求网络"  );

    }


    @Override
    public void onResume() {
        super.onResume();
        Logger.d("onResume--->:自营销");

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
