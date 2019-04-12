package com.shangjie.itop.ui.main.login;

import com.shangjie.baselibs.mvp.BasePresenter;
import com.shangjie.baselibs.net.BaseHttpResult;
import com.shangjie.baselibs.net.BaseObserver;
import com.shangjie.baselibs.rx.RxSchedulers;
import com.shangjie.itop.data.entity.LoginEntity;

import java.util.Map;

/**
 * @author 小强
 * @time 2018/7/31  17:09
 * @desc 登录中间层
 */
public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View> {
    /**
     * 获取 Model
     */
    @Override
    protected LoginContract.Model createModel() {
        return new LoginModel();
    }


    /**
     * 需要请求登录参数
     *
     * @param map 账号 密码
     */
    public void requestLogin(Map<String, String> map) {

        getModel().requestLoginData(map).
                compose(RxSchedulers.applySchedulers(getLifecycleProvider())).
                subscribe(new BaseObserver<LoginEntity>(getView()) {
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
