package com.hzq.example.ui.main.market;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.hzq.baselibs.base.BaseFragment;
import com.hzq.example.R;
import com.hzq.example.adapter.MyPagerAdapter;
import com.hzq.example.constants.Constant;
import com.hzq.example.ui.main.market.recoment.RecomentFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author 小强
 * @time 2018/6/12 22:57
 * @desc 自营销
 */
public class MarketFragment extends BaseFragment<MarketPresenter> {

    @BindView(R.id.tabs) SlidingTabLayout mTabLayout;
    @BindView(R.id.viewpager) ViewPager mViewpager;

    private String[] mTitles = {"推荐", "H5", "资讯", "视频", "本地"};


    private String mTitle;

    public static MarketFragment newInstance(String title) {
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

    /**
     * 初始化View的代码写在这个方法中
     */
    @Override
    protected void initView(View viwe) {

        if (mViewpager != null) {

            List<Fragment> fragments = new ArrayList<>();

            fragments.add(RecomentFragment.newInstance(Constant.articleType.ARTICLE_DEFAULT));
            fragments.add(RecomentFragment.newInstance(Constant.articleType.ARTICLE_H5));
            fragments.add(RecomentFragment.newInstance(Constant.articleType.ARTICLE_INFORMATION));
            fragments.add(RecomentFragment.newInstance(Constant.articleType.ARTICLE_VIDEO));
            fragments.add(RecomentFragment.newInstance(RecomentFragment.LOCAL));


            MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager(), fragments, mTitles);
            mViewpager.setAdapter(adapter);
            mViewpager.setOffscreenPageLimit(fragments.size());
            mTabLayout.setViewPager(mViewpager);


        }
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
