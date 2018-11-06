package com.hzq.example.ui.main.myWork;

import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.hzq.baselibs.base.BaseFragment;
import com.hzq.baselibs.mvp.BasePresenter;
import com.hzq.example.R;
import com.hzq.example.constants.Constant;
import com.hzq.example.data.Login.LoginMsgHelper;
import com.hzq.example.ui.main.myWork.fragment.ExtensionFragment;
import com.hzq.example.ui.main.myWork.fragment.MyWorkFragment;
import com.orhanobut.logger.Logger;

/**
 * @author 小强
 * @time 2018/11/2  14:27
 * @desc 我的作品
 */
public class WorkFragment extends BaseFragment {

    /**
     * 返回一个用于显示界面的布局id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_work;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    /**
     * 初始化View的代码写在这个方法中
     */
    @Override
    protected void initView(View view) {

    }


    @Override
    public void onResume() {
        super.onResume();
        Logger.d("onResume--->: 我的作品" );
        if (LoginMsgHelper.isLogin(getActivity())) {
            //用户类型
            int userType = LoginMsgHelper.getResult().getUser_type();
            selectedFragment(userType);
        }

    }

    private void selectedFragment(int userType) {

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        switch (userType) {
            case Constant.UserType.USER_TYPE_0:
            case Constant.UserType.USER_TYPE_1:
            case Constant.UserType.USER_TYPE_2:
                transaction.replace(R.id.fragment_layout, new MyWorkFragment(), "MyWorkFragment");
                break;

            //自营销
            case Constant.UserType.USER_TYPE_3:
                transaction.replace(R.id.fragment_layout, new ExtensionFragment(), "ExtensionFragment");

                break;
        }

        transaction.commit();
    }


    /**
     * 请求网络
     */
    @Override
    public void onLazyLoad() {

    }


    public static WorkFragment getInstance(String title) {

        return new WorkFragment();
    }

    /**
     * 显示错误
     *
     * @param msg  错误信息
     * @param code 错误code
     */
    @Override
    public void showError(String msg, int code) {

    }

    /**
     * 显示网络错误
     *
     * @param msg  错误信息
     * @param code 错误code
     */
    @Override
    public void showNetworkError(String msg, int code) {

    }




}
