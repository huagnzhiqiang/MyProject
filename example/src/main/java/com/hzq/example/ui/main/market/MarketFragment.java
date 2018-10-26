package com.hzq.example.ui.main.market;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.hzq.baselibs.base.BaseFragment;
import com.hzq.example.R;
import com.hzq.example.adapter.PagerAdapter;
import com.hzq.example.ui.main.market.h5five.H5fiveFragment;
import com.hzq.example.ui.main.market.information.InformationFragment;
import com.hzq.example.ui.main.market.local.LocalFragment;
import com.hzq.example.ui.main.market.recoment.RecomentFragment;
import com.hzq.example.ui.main.market.video.VideoFragment;

import butterknife.BindView;

/**
 * @author 小强
 * @time 2018/6/12 22:57
 * @desc 自营销
 */
public class MarketFragment extends BaseFragment<MarketPresenter> {

    @BindView(R.id.tabs) TabLayout mTabs;
    @BindView(R.id.viewpager) ViewPager mViewpager;

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
    protected MarketPresenter createPresenter() {
        return new MarketPresenter();
    }

    @Override
    protected void initView() {

        if (mViewpager != null) {
            PagerAdapter adapter = new PagerAdapter(getChildFragmentManager());
            adapter.addFragment(new RecomentFragment(), "推荐");
            adapter.addFragment(new H5fiveFragment(), "H5");
            adapter.addFragment(new InformationFragment(), "资讯");
            adapter.addFragment(new VideoFragment(), "视频");
            adapter.addFragment(new LocalFragment(), "本地");
            mViewpager.setAdapter(adapter);
            mTabs.setupWithViewPager(mViewpager);
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

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
