package com.shangjie.itop.ui.main.home.designer;

import com.shangjie.baselibs.mvp.BasePresenter;
import com.shangjie.baselibs.net.BaseHttpResult;
import com.shangjie.baselibs.net.BaseObserver;
import com.shangjie.baselibs.rx.RxSchedulers;
import com.shangjie.itop.data.entity.HomeDesignerEntity;

import java.util.Map;

/**
 * @author 小强
 * @time 2018/10/18  14:21
 * @desc 首页设计师页面Presenter
 */
public class HomeDesignerRedesignPresenter extends BasePresenter<HomeDesignerRedesignContract.Model, HomeDesignerRedesignContract.View> {

    /**
     * 获取 Model
     */
    @Override
    protected HomeDesignerRedesignContract.Model createModel() {
        return new HomeDesignerRedesignModel();
    }


    /**
     * 请求首页设计市数据
     *
     * @param map 请求参数
     */
    public void requestHomeDesignerData(Map<String, String> map) {

        getModel().requestHomeDesignerData(map).compose(RxSchedulers.applySchedulers(getLifecycleProvider())).
                subscribe(new BaseObserver<HomeDesignerEntity>(getView(), false) {
                    /**
                     * 请求成功返回
                     *
                     * @param result 服务器返回数据
                     */
                    @Override
                    public void onSuccess(BaseHttpResult<HomeDesignerEntity> result) {
                        getView().showHomeDesignerData(result.getData());
                    }

                    /**
                     * 请求失败返回
                     *
                     * @param errMsg     失败信息
                     * @param isNetError 是否是网络异常
                     */
                    @Override
                    public void onFailure(String errMsg, int errCode, boolean isNetError) {
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
     * 请求首页设计市数据
     *
     * @param map 请求参数
     */
    public void requestHomeDesignerLoadMoreData(Map<String, String> map) {

        getModel().requestHomeDesignerData(map).compose(RxSchedulers.applySchedulers(getLifecycleProvider())).
                subscribe(new BaseObserver<HomeDesignerEntity>(getView(), false) {
                    /**
                     * 请求成功返回
                     *
                     * @param result 服务器返回数据
                     */
                    @Override
                    public void onSuccess(BaseHttpResult<HomeDesignerEntity> result) {
                        getView().showHomeDesignerLoadMoreData(result.getData());
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

    /**
     * 请求首页关注设计师
     *
     * @param id 设计师id
     */
    public void requestFollowDesignersDataData(int id) {

        getModel().requestFollowDesignersData(id).compose(RxSchedulers.applySchedulers(getLifecycleProvider())).
                subscribe(new BaseObserver<String>(getView(),false) {
                    /**
                     * 请求成功返回
                     *
                     * @param result 服务器返回数据
                     */
                    @Override
                    public void onSuccess(BaseHttpResult<String> result) {
                        getView().showFollowDesignersData(result.getStatus());
                    }

                    /**
                     * 请求失败返回
                     *
                     * @param errMsg     失败信息
                     * @param isNetError 是否是网络异常
                     */
                    @Override
                    public void onFailure(String errMsg, int errCode, boolean isNetError) {
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
     * 请求首页取消关注设计师
     *
     * @param id 设计师id
     */
    public void requestUnFollowDesignersDataData(int id) {

        getModel().requestUnFollowDesignersData(id).compose(RxSchedulers.applySchedulers(getLifecycleProvider())).
                subscribe(new BaseObserver<String>(getView(),false) {
                    /**
                     * 请求成功返回
                     *
                     * @param result 服务器返回数据
                     */
                    @Override
                    public void onSuccess(BaseHttpResult<String> result) {
                        getView().showUnFollowDesignersData(result.getStatus());
                    }

                    /**
                     * 请求失败返回
                     *
                     * @param errMsg     失败信息
                     * @param isNetError 是否是网络异常
                     */
                    @Override
                    public void onFailure(String errMsg, int errCode, boolean isNetError) {
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
