package com.shangjie.itop.ui.main.mine;

import com.shangjie.itop.data.entity.LoginEntity;
import com.shangjie.baselibs.mvp.BasePresenter;
import com.shangjie.baselibs.net.BaseHttpResult;
import com.shangjie.baselibs.net.BaseObserver;
import com.shangjie.baselibs.rx.RxSchedulers;
import com.orhanobut.logger.Logger;

import java.util.Map;

/**
 * @author 小强
 * @time 2018/6/12 22:57
 * @desc
 */
public class MinePresenter extends BasePresenter<MineContract.Model, MineContract.View> {
    @Override
    protected MineContract.Model createModel() {
        return new MineModel();
    }


    public void requestLoginData(Map<String, String> map) {
        getModel().getLoginData(map).compose(RxSchedulers.applySchedulers(getLifecycleProvider())).
                subscribe(new BaseObserver<LoginEntity>(getView(),false) {
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
                    public void onFailure(String errMsg, int errCode, boolean isNetError) {
                        Logger.d("onFailure--->:" + errMsg.toString());
                        if (isNetError) {
                            //无网络
                            getView().showNetworkError(errMsg, errCode);
                        } else {
                            //有网络
                            getView().showError(errMsg, errCode);
                        }

                    }
                });

    }
}
