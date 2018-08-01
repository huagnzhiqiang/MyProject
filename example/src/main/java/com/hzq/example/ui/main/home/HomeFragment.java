package com.hzq.example.ui.main.home;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.hzq.baselibs.Bean.MessageEvent;
import com.hzq.baselibs.base.BaseFragment;
import com.hzq.baselibs.rx.RxBus;
import com.hzq.baselibs.utils.ToastUtils;
import com.hzq.example.R;
import com.hzq.example.data.entity.TestNews;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;


/**
 * @author 小强
 * @time 2018/6/12 22:57
 * @desc 首页
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {

    @BindView(R.id.textView) TextView textView;
    @BindView(R.id.button) Button button;
    @BindView(R.id.button2) Button button2;


    private String mTitle;

    public static HomeFragment getInstance(String title) {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        fragment.mTitle = title;
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {
        button.setOnClickListener(v -> mPresenter.requestData());
        button2.setOnClickListener(v -> {
            RxBus.getDefault().postSticky(new MessageEvent("1","我爱你"));
    });
    }

    @Override
    protected void initData() {
        Logger.d("initData--->:"  );

    }

    @Override
    protected boolean useEventBus() {
        return false;
    }

    @Override
    public void showError(String msg) {
        ToastUtils.showShort(msg);
    }


    @Override
    public void showData(List<TestNews> testNews) {
        ToastUtils.showShort(testNews.get(0).toString());
        textView.setText(testNews.get(0).toString());
    }


    /**
     * 请求网络
     */
    @Override
    public void onLazyLoad() {

    }
}
