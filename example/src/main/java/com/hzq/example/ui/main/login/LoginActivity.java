package com.hzq.example.ui.main.login;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hzq.baselibs.base.BaseMvpActivity;
import com.hzq.baselibs.utils.RegularUtils;
import com.hzq.baselibs.utils.StringUtils;
import com.hzq.baselibs.utils.ToastUtils;
import com.hzq.example.R;
import com.hzq.example.data.entity.LoginEntity;
import com.hzq.example.ui.main.MainActivity;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 小强
 * @time 2018/7/31  14:53
 * @desc 登录页面
 */
public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.toolbar_title) TextView mToolbarTitle;

    @BindView(R.id.iv_login_phone) ImageView mIvLoginPhone;
    @BindView(R.id.login_phone_ed) TextInputEditText mLoginPhoneEd;

    @BindView(R.id.iv_login_pwd) ImageView mIvLoginPwd;
    @BindView(R.id.login_pwd_ed) TextInputEditText mLoginPwdEd;

    @BindView(R.id.look_pwd) ImageView mLookPwd;
    @BindView(R.id.tvbtn_to_resetpwd) TextView mTvbtnToResetpwd;
    @BindView(R.id.tvbtn_to_reg) TextView mTvbtnToReg;

    @BindView(R.id.tvbtn_login) TextView mTvbtnLogin;

    @BindView(R.id.WXlogin) ImageView mWXlogin;

    /**
     * 获取布局 Id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    /**
     * 获取 Intent 数据
     **/
    @Override
    protected void getIntent(Intent intent) {

    }

    /** 初始化View的代码写在这个方法中 */
    @Override
    protected void initView() {
        mImmersionBar.statusBarColor(R.color.color_61bef4).fullScreen(true).init();
    }

    /** 初始化监听器的代码写在这个方法中 */
    @Override
    protected void initListener() {

    }

    /** 初始数据的代码写在这个方法中，用于从服务器获取数据 */
    @Override
    protected void initData() {
        mToolbarTitle.setText("登录");
    }

    /**
     * 请求网络
     */
    @Override
    protected void networkRequest() {

    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }


    /**
     * 创建Presenter控制层
     */
    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    /**
     * @param data 返回登录成功后的数据
     */
    @Override
    public void showLoginData(LoginEntity data) {

        Logger.d("showLoginData--->:" + data.toString());
        startActivity(MainActivity.class);
        finish();

    }


    /**
     * 返回错误信息
     *
     * @param msg 错误信息
     */
    @Override
    public void showError(String msg) {
        ToastUtils.showShort(msg);
        Logger.d("showError--->:" + msg);
    }


    @OnClick(R.id.tvbtn_login)
    public void onClick(View view) {

        switch (view.getId()) {
            //登录
            case R.id.tvbtn_login:
                login();
                break;
        }
    }

    //登录操作
    private void login() {
        String phone = mLoginPhoneEd.getText().toString().trim();
        String pwd = mLoginPwdEd.getText().toString().trim();

        //        if(! StringUtils.isPhone(phone)) {
        //            ToastUtils.showShort("请输入手机号码");
        //            return;
        //        }


        if (StringUtils.isEmpty(phone)) {
            ToastUtils.showShort("手机号码不能为空");
            mLoginPhoneEd.setError("手机号码不能为空");
            return;
        }

        if (phone.length() != 11) {
            ToastUtils.showShort("手机号码长度不正确");
            mLoginPhoneEd.setError("手机号码长度不正确");

            return;
        }

        if (StringUtils.isEmpty(pwd)) {
            ToastUtils.showShort("密码不能为空");
            mLoginPwdEd.setError("密码不能为空");
            return;
        }

        if (!RegularUtils.isPattern(pwd)) {
            ToastUtils.showShort("密码必须为8-12位的字母加数字组合");
            mLoginPwdEd.setError("密码必须为8-12位的字母加数字组合");

            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("username", phone);
        map.put("password", pwd);
        mPresenter.requestLogin(map);

    }

}
