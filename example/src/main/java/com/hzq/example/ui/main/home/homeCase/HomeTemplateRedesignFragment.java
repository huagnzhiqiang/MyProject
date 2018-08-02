package com.hzq.example.ui.main.home.homeCase;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.hzq.baselibs.base.BaseFragment;
import com.hzq.baselibs.mvp.BasePresenter;
import com.hzq.baselibs.utils.ToastUtils;
import com.hzq.example.R;
import com.orhanobut.logger.Logger;

/**
 * @author 小强
 * @time 2018/8/2  15:22
 * @desc 首页案例/模板页面
 */
public class HomeTemplateRedesignFragment extends BaseFragment {
    private static final String FRAGMENT_TYPE = "fragment_type";//跳转页面Key
    public static final int HOME_CASE = 1;//案例type
    public static final int HOME_TEMPLATE = 2;//模板type
    private int mType;

    /**
     * 返回一个用于显示界面的布局id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.home_customized_redesign_fragment;
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
        Bundle arguments = getArguments();
        mType = arguments.getInt(HomeTemplateRedesignFragment.FRAGMENT_TYPE);
        Logger.d("initData--->:");
    }

    /**
     * 请求网络
     */
    @Override
    public void onLazyLoad() {
        Logger.d("onLazyLoad--->:");
        if (mType == 1) {
            ToastUtils.showShort("案例");
        } else {
            ToastUtils.showShort("模板");
        }
    }

    @Override
    public void showError(String msg) {

    }

    /** ==================创建Fragment===================== */
    public static Fragment newInstance(int type) {
        HomeTemplateRedesignFragment fragment = new HomeTemplateRedesignFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(HomeTemplateRedesignFragment.FRAGMENT_TYPE, type);
        fragment.setArguments(bundle);

        return fragment;
    }
}
