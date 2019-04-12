package com.shangjie.itop.ui.main.market.recoment;

import com.shangjie.baselibs.mvp.BasePresenter;
import com.shangjie.baselibs.net.BaseHttpResult;
import com.shangjie.baselibs.net.BaseObserver;
import com.shangjie.baselibs.rx.RxSchedulers;
import com.shangjie.itop.data.entity.RecomentEntity;

import java.util.Map;

/**
 * @author 小强
 * @time 2018/10/27  16:15
 * @desc 自营销推荐页面View
 */
public class RecomentPresenter extends BasePresenter<RecomentContract.Model, RecomentContract.View> {

    /**
     * 获取 Model
     */
    @Override
    protected RecomentContract.Model createModel() {
        return new RecomenModel();
    }

    /**
     * 请求自营销推荐数据
     *
     * @param map 请求参数
     */
    public void requestRecomentData(Map<String, String> map) {
        getModel().requestRecomentData(map).compose(RxSchedulers.applySchedulers(getLifecycleProvider())).
                subscribe(new BaseObserver<RecomentEntity>(getView(),false) {
                    /**
                     * 请求成功返回
                     *
                     * @param result 服务器返回数据
                     */
                    @Override
                    public void onSuccess(BaseHttpResult<RecomentEntity> result) {
                        getView().showRecomentData(result.getData());
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
     * 请求自营销推荐更加更多数据
     *
     * @param map 请求参数
     */
    public void requestRecomentLoadMoreData(Map<String, String> map) {
        getModel().requestRecomentData(map).compose(RxSchedulers.applySchedulers(getLifecycleProvider())).
                subscribe(new BaseObserver<RecomentEntity>(getView(),false) {
                    /**
                     * 请求成功返回
                     *
                     * @param result 服务器返回数据
                     */
                    @Override
                    public void onSuccess(BaseHttpResult<RecomentEntity> result) {
                        getView().showRecomentLoadMoreData(result.getData());

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
