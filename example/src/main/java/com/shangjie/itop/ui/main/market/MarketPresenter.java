package com.shangjie.itop.ui.main.market;


import com.shangjie.baselibs.mvp.BasePresenter;

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
