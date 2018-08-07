package com.hzq.example.ui.main.video;

import android.os.Bundle;
import android.widget.TextView;

import com.hzq.baselibs.Bean.MessageEvent;
import com.hzq.baselibs.base.BaseFragment;
import com.hzq.baselibs.rx.RxBus;
import com.hzq.example.R;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * @author 小强
 * @time 2018/6/12 22:57
 * @desc 自营销
 */
public class MarketFragment extends BaseFragment<VideoPresenter> {

    @BindView(R.id.textView) TextView textView;

    private String mTitle;

    public static MarketFragment getInstance(String title) {
        MarketFragment fragment = new MarketFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        fragment.mTitle = title;
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_market;
    }

    @Override
    protected VideoPresenter createPresenter() {
        return new VideoPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mPresenter.addDispose(RxBus.getDefault().toObservable(MessageEvent.class).subscribe(new Consumer<MessageEvent>() {
            @Override
            public void accept(MessageEvent testEvent) throws Exception {
                if (testEvent != null) {
                    Logger.d("accept--->:" + testEvent.getResult());
                    textView.setText((String) testEvent.getResult());

                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }));
    }



    /**
     * 请求网络
     */
    @Override
    public void onLazyLoad() {

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
