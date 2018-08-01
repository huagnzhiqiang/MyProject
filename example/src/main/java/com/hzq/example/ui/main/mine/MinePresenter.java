package com.hzq.example.ui.main.mine;

import com.hzq.example.data.entity.LoginEntity;
import com.hzq.baselibs.mvp.BasePresenter;
import com.hzq.baselibs.net.BaseHttpResult;
import com.hzq.baselibs.net.BaseObserver;
import com.hzq.baselibs.rx.RxSchedulers;
import com.orhanobut.logger.Logger;

import java.util.Map;

/**
 * @author 小强
 * @time 2018/6/12 22:57
 * @desc
 */
public class MinePresenter extends BasePresenter<MineContract.Model,MineContract.View> {
    @Override
    protected MineContract.Model createModel() {
        return new MineModel();
    }


    public void requestLoginData(Map<String,String> map) {
        getModel().getLoginData(map)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<LoginEntity>(getView()) {
                    /**
                     * 请求成功返回
                     *
                     * @param result 服务器返回数据
                     */
                    @Override
                    public void onSuccess(BaseHttpResult<LoginEntity> result) {
                        getView().showLoginData(result.getData());
                    }

                    /**
                     * 请求失败返回
                     *
                     * @param errMsg     失败信息
                     * @param isNetError 是否是网络异常
                     */
                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        Logger.d("onFailure--->:" + errMsg.toString());
                        getView().showError(errMsg);
                    }
                });

    }
}
