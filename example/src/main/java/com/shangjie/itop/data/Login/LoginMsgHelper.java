package com.shangjie.itop.data.Login;

import android.app.Activity;
import android.content.Intent;

import com.shangjie.baselibs.utils.GsonUtil;
import com.shangjie.baselibs.utils.SpUtil;
import com.shangjie.baselibs.utils.StringUtil;
import com.shangjie.itop.constants.SpKeyConstant;
import com.shangjie.itop.data.entity.LoginEntity;
import com.shangjie.itop.ui.main.login.LoginActivity;

/**
 * @author 小强
 * @time 2018/10/18  17:13
 * @desc 登录信息
 */
public class LoginMsgHelper {

    /**
     * 登录信息
     *
     * @return true:登录 false:未登录
     */
    public static boolean isLogin(Activity activity) {
        String loginMsgResult = SpUtil.getInstance().getString(SpKeyConstant.LOGIN_MSG);
        if (!StringUtil.isEmpty(loginMsgResult)) {
            return true;
        } else {
            Intent intent = new Intent(activity, LoginActivity.class);
            activity.startActivity(intent);
            activity.finish();
            return false;
        }

    }

    /**
     * 登录之后返回的结果
     * @return  返回登录后的数据    null:没有登录
     */
    public static LoginEntity getResult() {

        String result = SpUtil.getInstance().getString(SpKeyConstant.LOGIN_MSG);

        if (result != null)
            if (!StringUtil.isEmpty(result)) {
                LoginEntity loginEntity = GsonUtil.fromJson(result, LoginEntity.class);
                if (!StringUtil.isEmpty(loginEntity.getToken())) {
                    SpUtil.getInstance().putString(SpKeyConstant.TOKEN, loginEntity.getToken());
                }
                return loginEntity;
            }
        return null;
    }


    /**
     * 登录退出处理
     */
    public static void exitLogin() {

        SpUtil.getInstance().clearData();

    }

}
