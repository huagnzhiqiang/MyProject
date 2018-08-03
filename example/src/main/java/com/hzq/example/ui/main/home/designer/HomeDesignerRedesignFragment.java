package com.hzq.example.ui.main.home.designer;

import android.support.v4.app.Fragment;

import com.hzq.baselibs.base.BaseFragment;
import com.hzq.baselibs.mvp.BasePresenter;
import com.hzq.example.R;
import com.orhanobut.logger.Logger;

/**
 * @author 小强
 * @time 2018/8/2  15:31
 * @desc 首页设计师页面
 */
public class HomeDesignerRedesignFragment extends BaseFragment {
    /**
     * 返回一个用于显示界面的布局id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_designer;
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
        Logger.d("首页设计师页面initData--->:");

    }

    /**
     * 请求网络
     */
    @Override
    public void onLazyLoad() {

        Logger.d("首页设计师页面--->:");
    }

    @Override
    public void showError(String msg) {

    }

    /** ==================初始化fragment===================== */
    public static Fragment newInstance() {
        return new HomeDesignerRedesignFragment();
    }
}
