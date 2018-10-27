package com.hzq.baselibs.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hzq.baselibs.mvp.BasePresenter;
import com.hzq.baselibs.mvp.IView;

/**
 * @author 小强
 * @time 2018/6/9 17:12
 * @desc 基类 BaseMvpActivity
 */
public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements IView {
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }


    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }

    /**
     * 创建Presenter
     *
     * @return 返回当前的Presenter
     */

    protected abstract T createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

}
