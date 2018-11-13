package com.hzq.example.ui.main.mine.presenter;

import com.hzq.baselibs.mvp.BasePresenter;
import com.hzq.baselibs.net.BaseHttpResult;
import com.hzq.baselibs.net.BaseObserver;
import com.hzq.baselibs.rx.RxSchedulers;
import com.hzq.example.ui.main.mine.contract.EnterpriseContract;
import com.hzq.example.ui.main.mine.model.EnterpriseModel;

import java.util.Map;

/**
 * @author 小强
 * @time 2018/11/7  16:27
 * @desc 企业信息修改页面Presenter
 */
public class EnterprisePresenter extends BasePresenter<EnterpriseContract.Model, EnterpriseContract.View> {

    /**
     * 获取 Model
     */
    @Override
    protected EnterpriseContract.Model createModel() {
        return new EnterpriseModel();
    }


    /**
     * 上传图片
     *
     * @param map 请求参数
     */
    public void requistUpImager(Map<String, String> map) {
        getModel().requistUpImager(map).
                compose(RxSchedulers.applySchedulers(getLifecycleProvider())).
                subscribe(new BaseObserver<String>(getView(), false) {
                    /**
                     * 请求成功返回
                     *
                     * @param result 服务器返回数据
                     */
                    @Override
                    public void onSuccess(BaseHttpResult<String> result) {
                        getView().showUpImagerData(result);
                    }

                    /**
                     * 请求失败返回
                     *
                     * @param errMsg     失败信息
                     * @param isNetError 是否是网络异常
                     */
                    @Override
                    public void onFailure(String errMsg, int errCode, boolean isNetError) {
                        getView().showError(errMsg, errCode);
                    }
                });
    }
}
