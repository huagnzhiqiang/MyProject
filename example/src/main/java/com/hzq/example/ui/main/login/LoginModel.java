package com.hzq.example.ui.main.login;

import com.hzq.baselibs.mvp.BaseModel;
import com.hzq.baselibs.net.BaseHttpResult;
import com.hzq.example.data.entity.LoginEntity;
import com.hzq.example.data.repository.RetrofitUtils;

import java.util.Map;

/**
 * @author 小强
 * @time 2018/7/31  17:09
 * @desc 登录模型数据层进行操作
 */
public class LoginModel extends BaseModel implements LoginContract.Model {

    /**
     * 请求登录参数
     *
     * @param map 账号 密码
     */
    @Override
    public io.reactivex.Observable<BaseHttpResult<LoginEntity>> requestLoginData(Map<String, String> map) {
        return RetrofitUtils.getHttpService().requestLoginData(map);
    }
}
