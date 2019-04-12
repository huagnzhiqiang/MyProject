package com.shangjie.itop.ui.main.myWork.presenter;

import com.shangjie.baselibs.mvp.BasePresenter;
import com.shangjie.baselibs.net.BaseHttpResult;
import com.shangjie.baselibs.net.BaseObserver;
import com.shangjie.baselibs.rx.RxSchedulers;
import com.shangjie.itop.data.entity.MyProductEntity;
import com.shangjie.itop.ui.main.myWork.contract.MyWorkContract;
import com.shangjie.itop.ui.main.myWork.model.MyWorkModel;
import com.orhanobut.logger.Logger;

import java.util.Map;

/**
 * @author 小强
 * @time 2018/11/2  15:22
 * @desc 我的作品Presenter
 */
public class MyPresenter extends BasePresenter<MyWorkContract.Model, MyWorkContract.View> {

    /**
     * 获取 Model
     */
    @Override
    protected MyWorkContract.Model createModel() {
        return new MyWorkModel();
    }


    /**
     * 请求我的作品数据
     *
     * @param map 请求参数
     */
    public void requestMyWorkData(Map<String, String> map) {

        getModel().requestMyWorktData(map).compose(RxSchedulers.applySchedulers(getLifecycleProvider())).
                subscribe(new BaseObserver<MyProductEntity>(getView(), false) {
                    /**
                     * 请求成功返回
                     *
                     * @param result 服务器返回数据
                     */
                    @Override
                    public void onSuccess(BaseHttpResult<MyProductEntity> result) {

                        getView().showMyWorkData(result.getData());

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

    /**
     * 请求我的作品更多数据
     *
     * @param map 请求参数
     */
    public void requestMyWorkLoadMoreData(Map<String, String> map) {
        getModel().requestMyWorktData(map).compose(RxSchedulers.applySchedulers(getLifecycleProvider())).
                subscribe(new BaseObserver<MyProductEntity>(getView(), false) {
                    /**
                     * 请求成功返回
                     *
                     * @param result 服务器返回数据
                     */
                    @Override
                    public void onSuccess(BaseHttpResult<MyProductEntity> result) {
                        getView().showMyWorkLoadMoreData(result.getData());

                    }

                    /**
                     * 请求失败返回
                     *
                     * @param errMsg     失败信息
                     * @param isNetError 是否是网络异常
                     */
                    @Override
                    public void onFailure(String errMsg, int errCode, boolean isNetError) {
                        getView().showLoadMoreError(errMsg);
                    }
                });
    }
}
