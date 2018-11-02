package com.hzq.example.ui.main.myWork;

import android.widget.EditText;
import android.widget.LinearLayout;

import com.gyf.barlibrary.ImmersionBar;
import com.hzq.baselibs.base.BaseFragment;
import com.hzq.baselibs.mvp.BasePresenter;
import com.hzq.example.R;
import com.hzq.example.data.Login.LoginMsgHelper;

import butterknife.BindView;

/**
 * @author 小强
 * @time 2018/8/1  16:45
 * @desc 我的作品页面
 */
public class MyWorkFragment extends BaseFragment {
    @BindView(R.id.tv) EditText mTv;
    @BindView(R.id.ll) LinearLayout ll;

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
    protected void initView() {

        LoginMsgHelper.isLogin(getActivity());
    }

    /**
     * 初始化监听器的代码写在这个方法中
     */
    @Override
    protected void initListener() {

    }

    /**
     * 初始数据的代码写在这个方法中，用于从服务器获取数据
     */
    @Override
    protected void initData() {
    }

    /**
     * 请求网络
     */
    @Override
    public void onLazyLoad() {

    }


    public static MyWorkFragment getInstance(String title) {

        return new MyWorkFragment();
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
