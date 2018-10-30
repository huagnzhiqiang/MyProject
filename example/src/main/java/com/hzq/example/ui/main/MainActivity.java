package com.hzq.example.ui.main;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.hzq.baselibs.base.BaseMvpActivity;
import com.hzq.example.R;
import com.hzq.example.data.entity.TabEntity;
import com.hzq.example.data.entity.TestNews;
import com.hzq.example.ui.main.home.HomeFragment;
import com.hzq.example.ui.main.mine.MineFragment;
import com.hzq.example.ui.main.myWork.MyWorkFragment;
import com.hzq.example.ui.main.market.MarketFragment;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View {


    private HomeFragment mHomeFragment;
    private MarketFragment mMarketFragment;
    private MyWorkFragment mMyWorkFragment;
    private MineFragment mMineFragment;

    // 顶部滑动的标签栏
    private String[] mTitles = {"首页", "自营销", "我的作品", "我的"};
    // 未被选中的图标
    private int[] mIconUnSelectIds = {R.drawable.icon_home, R.drawable.icon_ziyingxiao, R.drawable.icon_zuopin, R.drawable.icon_me};
    // 被选中的图标
    private int[] mIconSelectIds = {R.drawable.icon_home_red, R.drawable.icon_ziyingxiao_red, R.drawable.icon_zuopin_red, R.drawable.icon_me_red};

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    // 默认为0;
    private int mCurrIndex = 0;

    @BindView(R.id.fl_container) FrameLayout flContainer;
    @BindView(R.id.tab_layout) CommonTabLayout tabLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (savedInstanceState != null) {
            Logger.d("onRestore enter...." + mCurrIndex);
            mCurrIndex = savedInstanceState.getInt("currTabIndex");
        }
        tabLayout.setCurrentTab(mCurrIndex);
        switchFragment(mCurrIndex);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initView() {
        initTab();

    }

//    @Override
//    public boolean isInMultiWindowMode() {
//        return true;
//    }

    /**
     * 请求网络
     */
    @Override
    protected void networkRequest() {

    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }


    @Override
    public void showData(List<TestNews> testNews) {

    }


    /**
     * 初始化底部菜单
     */
    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnSelectIds[i]));

        }
        //为Tab赋值数据
        tabLayout.setTabData(mTabEntities);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                //切换Fragment
                switchFragment(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });


        //设备红点
        //        tabLayout.showDot(0);
        //        tabLayout.showMsg(1, 100);
        //        tabLayout.showDot(2);

    }

    /**
     * 切换Fragment
     *
     * @param position 下标
     */
    private void switchFragment(int position) {
        // Fragment事务管理器
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragments(transaction);
        Logger.d("current position tab" + position);
        switch (position) {
            //首页
            case 0:

                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.getInstance(mTitles[0]);
                    transaction.add(R.id.fl_container, mHomeFragment, "home");
                } else {
                    transaction.show(mHomeFragment);
                }
                break;
            //自营销人
            case 1:

                if (mMarketFragment == null) {
                    mMarketFragment = MarketFragment.getInstance(mTitles[1]);
                    transaction.add(R.id.fl_container, mMarketFragment, "video");
                } else {
                    transaction.show(mMarketFragment);
                }
                break;

            case 2:

                if (mMyWorkFragment == null) {
                    mMyWorkFragment = MyWorkFragment.getInstance(mTitles[2]);
                    transaction.add(R.id.fl_container, mMyWorkFragment, "myWork");
                } else {
                    transaction.show(mMyWorkFragment);
                }
                break;

            //我的
            case 3:
                if (mMineFragment == null) {
                    mMineFragment = MineFragment.getInstance(mTitles[3]);
                    transaction.add(R.id.fl_container, mMineFragment, "mine");
                } else {
                    transaction.show(mMineFragment);
                }
                break;
            default:
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.getInstance(mTitles[0]);
                    transaction.add(R.id.fl_container, mHomeFragment, "home");
                } else {
                    transaction.show(mHomeFragment);
                }
                break;
        }
        mCurrIndex = position;
        tabLayout.setCurrentTab(mCurrIndex);
        transaction.commitAllowingStateLoss();

    }

    /**
     * 隐藏所有的Fragment
     *
     * @param transaction transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (null != mHomeFragment) {
            transaction.hide(mHomeFragment);
        }
        if (null != mMarketFragment) {
            transaction.hide(mMarketFragment);
        }
        if (null != mMyWorkFragment) {
            transaction.hide(mMyWorkFragment);
        }
        if (null != mMineFragment) {
            transaction.hide(mMineFragment);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //记录fragment的位置,防止崩溃 activity被系统回收时，fragment错乱
        Logger.e("onSaveInstanceState crash..." + mCurrIndex);
        if (tabLayout != null) {
            outState.putInt("currTabIndex", mCurrIndex);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
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

