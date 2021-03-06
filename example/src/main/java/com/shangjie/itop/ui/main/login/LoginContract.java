package com.shangjie.itop.ui.main.login;

import com.shangjie.baselibs.mvp.IModel;
import com.shangjie.baselibs.mvp.IView;
import com.shangjie.baselibs.net.BaseHttpResult;
import com.shangjie.itop.data.entity.LoginEntity;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @author 小强
 * @time 2018/7/31  17:10
 * @desc 登录契约类
 */
public interface LoginContract {

    interface View extends IView {

        /**
         *
         * @param data 返回登录成功后的数据
         */
        void showLoginData(LoginEntity data);
    }

    interface Model extends IModel {

        /**
         * 请求登录参数
         * @param map 账号 密码
         * @return
         */
       Observable<BaseHttpResult<LoginEntity>> requestLoginData(Map<String, String> map);
    }
}
