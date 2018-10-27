package com.hzq.example.ui.main.market.recoment;

import com.hzq.baselibs.base.BaseFragment;
import com.hzq.baselibs.mvp.BasePresenter;
import com.hzq.example.R;
import com.orhanobut.logger.Logger;

/**
 * @author 小强
 * @time 2018/10/26  18:20
 * @desc 自营销推荐页面
 */
public class RecomentFragment  extends BaseFragment {


    /**
     * 返回一个用于显示界面的布局id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recoment;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    /**
     * 初始化懒加载的数据 (请求网络)
     */
    @Override
    public void onLazyLoad() {

        Logger.d("初始化懒加载的数据 (请求网络)--->:"  );
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
