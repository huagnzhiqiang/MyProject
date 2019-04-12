package com.shangjie.itop.ui.main.home.customized;

import com.shangjie.baselibs.mvp.BasePresenter;
import com.shangjie.baselibs.net.BaseHttpResult;
import com.shangjie.baselibs.net.BaseObserver;
import com.shangjie.baselibs.rx.RxSchedulers;
import com.shangjie.itop.data.entity.HomeCustomizeEntity;
import com.orhanobut.logger.Logger;

import java.util.Map;

/**
 * @author 小强
 * @time 2018/10/16  14:03
 * @desc 首页定制Presenter
 */
public class HomeCustomizedRedesignPersenter extends BasePresenter<HomeCustomizedRedesignContract.Model, HomeCustomizedRedesignContract.View> {
    /**
     * 获取 Model
     */
    @Override
    protected HomeCustomizedRedesignContract.Model createModel() {
        return new HomeCustomizedRedesignModel();
    }


    /**
     * 请求首页定制数据
     */

    public void requestCustomizedData(Map<String, String> map){

       getModel().requestCustomizedData(map).compose(RxSchedulers.applySchedulers(getLifecycleProvider())).
               subscribe(new BaseObserver<HomeCustomizeEntity>(getView(), false) {
                   /**
                    * 请求成功返回
                    *
                    * @param result 服务器返回数据
                    */
                   @Override
                   public void onSuccess(BaseHttpResult<HomeCustomizeEntity> result) {
                       getView().showCustomizedData(result.getData());
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
     * 请求首页定制加载更多数据
     */

    public void requestCustomizedloadMoerData(Map<String, String> map){

        getModel().requestCustomizedData(map).compose(RxSchedulers.applySchedulers(getLifecycleProvider())).
                subscribe(new BaseObserver<HomeCustomizeEntity>(getView(), false) {
                    /**
                     * 请求成功返回
                     *
                     * @param result 服务器返回数据
                     */
                    @Override
                    public void onSuccess(BaseHttpResult<HomeCustomizeEntity> result) {
                        Logger.d("onSuccess--->:" + result);
                        getView().showCustomizedLoadMore(result.getData());
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
