package com.hzq.example.ui.main;


import com.hzq.baselibs.mvp.BasePresenter;

/**
 * @author 小强
 * @time  2018/6/12 16:18
 * @desc
 */
public class MainPresenter extends BasePresenter<MainContract.Model,MainContract.View> {

    @Override
    protected MainContract.Model createModel() {
        return new MainModel();
    }

    public void requestData() {

    }



}
