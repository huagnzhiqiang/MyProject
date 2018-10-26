package com.hzq.example.ui.main.market;


import com.hzq.baselibs.mvp.BasePresenter;

/**
 * @author 小强
 * @time  2018/6/12 22:57
 * @desc
 */
public class MarketPresenter extends BasePresenter<MarketContract.Model,MarketContract.View> {
    @Override
    protected MarketContract.Model createModel() {
        return new MarketModel();
    }


    public void requestData(){

    }
}
