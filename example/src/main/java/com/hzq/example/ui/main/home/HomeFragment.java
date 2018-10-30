package com.hzq.example.ui.main.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.hzq.baselibs.base.BaseFragment;
import com.hzq.baselibs.utils.ToastUtils;
import com.hzq.example.R;
import com.hzq.example.adapter.MyPagerAdapter;
import com.hzq.example.data.entity.TabEntity;
import com.hzq.example.data.entity.TestNews;
import com.hzq.example.listener.SetMyOnPageChangeListener;
import com.hzq.example.ui.main.home.customized.HomeCustomizedRedesignFragment;
import com.hzq.example.ui.main.home.designer.HomeDesignerRedesignFragment;
import com.hzq.example.ui.main.home.homeCase.HomeTemplateRedesignFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author 小强
 * @time 2018/6/12 22:57
 * @desc 首页
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {


    @BindView(R.id.tv_location) TextView mTvLocation;//定位
    @BindView(R.id.icon_address) ImageView mIconAddress;
    @BindView(R.id.ll_location) LinearLayout mLlLocation;

    //案例
    @BindView(R.id.view_case) View mViewCase;

    //模板
    @BindView(R.id.view_template) View mViewTemplate;

    //定制
    @BindView(R.id.view_customized) View mViewCustomized;

    //设计师
    @BindView(R.id.view_designer) View mViewDesigner;


    @BindView(R.id.tab_layout) CommonTabLayout mTabLayout;

    @BindView(R.id.viewpager) ViewPager mViewpager;

    // 顶部滑动的标签栏
    private String[] mTitles = {"案例", "模板", "定制", "设计师"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

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

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i]));
        }
        mTabLayout.setTabData(mTabEntities);
        setupViewPager(mViewpager);
    }

    @Override
    protected void initListener() {

        //        button.setOnClickListener(v -> mPresenter.requestData());
        //        button2.setOnClickListener(v -> {
        //            RxBus.getDefault().postSticky(new MessageEvent("1","我爱你"));
        //    });
    }


    @Override
    protected void initData() {
        mTvLocation.setText("全国");

    }


    @Override
    protected boolean useEventBus() {
        return false;
    }


    @Override
    public void showData(List<TestNews> testNews) {
        ToastUtils.showShort(testNews.get(0).toString());
    }


    /**
     * 请求网络
     */
    @Override
    public void onLazyLoad() {

    }

    /**
     * ==================初始化ViewPager=====================
     */
    private void setupViewPager(ViewPager viewPager) {

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeTemplateRedesignFragment.newInstance(HomeTemplateRedesignFragment.HOME_CASE));
        fragments.add(HomeTemplateRedesignFragment.newInstance(HomeTemplateRedesignFragment.HOME_TEMPLATE));
        fragments.add(HomeCustomizedRedesignFragment.newInstance());
        fragments.add(HomeDesignerRedesignFragment.newInstance());

        mTabLayout.setCurrentTab(0);
        mViewpager.setOffscreenPageLimit(fragments.size());
        viewPager.setAdapter(new MyPagerAdapter(getChildFragmentManager(), fragments, mTitles));

        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });


        viewPager.addOnPageChangeListener(new SetMyOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mTabLayout.setCurrentTab(position);
            }
        });
    }


    @OnClick({R.id.ll_case, R.id.ll_template, R.id.ll_customized, R.id.ll_designer, R.id.ll_location, R.id.fl_search})
    public void onClick(View view) {
        switch (view.getId()) {
            //案例
            case R.id.ll_case:
                selectCurrentPager(0, mViewCase);
                break;

            //模板
            case R.id.ll_template:
                selectCurrentPager(1, mViewTemplate);
                break;

            //定制
            case R.id.ll_customized:
                selectCurrentPager(2, mViewCustomized);
                break;

            //设计师
            case R.id.ll_designer:
                selectCurrentPager(3, mViewDesigner);
                break;
            //定位
            case R.id.ll_location:
                //                CommonUtils.goActivity(getContext(), SelectCityActivity.class, null);
                break;
            //搜索
            case R.id.fl_search:
                //                CommonUtils.goActivity(getContext(), SearchActivity.class, null);
                break;
        }
    }

    /**
     * 选择当前页面
     *
     * @param postion 当前页面索引
     * @param view    当前页面字体
     */
    private void selectCurrentPager(int postion, View view) {
        mViewpager.setCurrentItem(postion);

        mViewCustomized.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_tab_nomal));
        mViewTemplate.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_tab_nomal));
        mViewCase.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_tab_nomal));
        mViewDesigner.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_tab_nomal));

    }


    /** ==================viwePager滑动监听===================== */
    private void viewPagerListener() {
        mViewpager.setOnPageChangeListener(new SetMyOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        selectCurrentPager(0, mViewCase);
                        break;
                    case 1:
                        selectCurrentPager(1, mViewTemplate);

                        break;
                    case 2:
                        selectCurrentPager(2, mViewCustomized);

                        break;
                    case 3:
                        selectCurrentPager(3, mViewDesigner);
                        break;
                }
            }
        });
    }

    /**
     * 显示错误
     *
     * @param msg  错误信息
     * @param code 错误code
     */
    @Override
    public void showError(String msg, int code) {
        ToastUtils.showShort(msg);

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
