package com.hzq.example.ui.main.home.homeCase;

import com.hzq.baselibs.mvp.BasePresenter;
import com.hzq.baselibs.net.BaseHttpResult;
import com.hzq.baselibs.net.BaseObserver;
import com.hzq.baselibs.rx.RxSchedulers;
import com.hzq.example.data.entity.TemplateReadesignEntity;

import java.util.Map;

/**
 * @author 小强
 * @time 2018/8/2  16:54
 * @desc 首页案例/模板页面Presenter
 */
public class HomeTemplateRedesignFragmentPresenter extends BasePresenter<HomeTemplateRedesignFragmentContract.Model, HomeTemplateRedesignFragmentContract.View> {

    /**
     * 获取 Model
     */
    @Override
    protected HomeTemplateRedesignFragmentContract.Model createModel() {
        return new HomeTemplateRedesignFragmentModel();
    }

    /**
     * 请求首页案例数据
     */
    public void requestCaseData(Map<String, String> map) {

        getModel().getCaseData(map).compose(RxSchedulers.applySchedulers(getLifecycleProvider())).
                subscribe(new BaseObserver<TemplateReadesignEntity>(getView(), false) {
                    /**
                     * 请求成功返回
                     *
                     * @param result 服务器返回数据
                     */
                    @Override
                    public void onSuccess(BaseHttpResult<TemplateReadesignEntity> result) {
                        getView().showCaseData(result.getData());
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
     * 请求首页案例加载更多数据
     */
    public void requestCaseLoadMoreData(Map<String, String> map) {


        getModel().getCaseLoadMoreData(map).compose(RxSchedulers.applySchedulers(getLifecycleProvider())).
                subscribe(new BaseObserver<TemplateReadesignEntity>(getView(), false) {
                    /**
                     * 请求成功返回
                     *
                     * @param result 服务器返回数据
                     */
                    @Override
                    public void onSuccess(BaseHttpResult<TemplateReadesignEntity> result) {
                        getView().showCaseDataLoadMore(result.getData());
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
     * 请求首页模板数据
     */
    public void requestProductData(Map<String, String> map) {
        getModel().getProductData(map).compose(RxSchedulers.applySchedulers(getLifecycleProvider())).
                subscribe(new BaseObserver<TemplateReadesignEntity>(getView(), false) {
                    /**
                     * 请求成功返回
                     *
                     * @param result 服务器返回数据
                     */
                    @Override
                    public void onSuccess(BaseHttpResult<TemplateReadesignEntity> result) {
                        getView().showProductData(result.getData());
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
     * 请求首页模板更多数据
     */
    public void requestProductLoadMoreData(Map<String, String> map) {
        getModel().getProductData(map).compose(RxSchedulers.applySchedulers(getLifecycleProvider())).
                subscribe(new BaseObserver<TemplateReadesignEntity>(getView(), false) {
                    /**
                     * 请求成功返回
                     *
                     * @param result 服务器返回数据
                     */
                    @Override
                    public void onSuccess(BaseHttpResult<TemplateReadesignEntity> result) {
                        getView().showProductLoadMoreData(result.getData());
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
