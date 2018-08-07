package com.hzq.example.ui.main.home.homeCase;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView;
import com.hzq.baselibs.base.BaseFragment;
import com.hzq.baselibs.utils.ToastUtils;
import com.hzq.example.R;
import com.hzq.example.adapter.HomeTemplateRedesignAdapter;
import com.hzq.example.constants.Constant;
import com.hzq.example.data.entity.TemplateReadesignEntity;
import com.hzq.example.view.recycleView.MyDividerItemDecoration;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @author 小强
 * @time 2018/8/2  15:22
 * @desc 首页案例/模板页面
 */
public class HomeTemplateRedesignFragment extends BaseFragment<HomeTemplateRedesignFragmentPresenter> implements HomeTemplateRedesignFragmentContract.View, SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {

    private static final String FRAGMENT_TYPE = "fragment_type";//跳转页面Key
    public static final int HOME_CASE = 1;//案例type
    public static final int HOME_TEMPLATE = 2;//模板type

    @BindView(R.id.home_iv_viewSwitches) ImageView mHomeIvViewSwitches;
    @BindView(R.id.home_iv_viewScreening) LinearLayout mHomeIvViewScreening;
    @BindView(R.id.ll_head) LinearLayout mLlHead;
    @BindView(R.id.rl_home_CustomRequirements) RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout mSwipeRefreshLayout;

    private int mType;//页面跳转类型判断
    private int mCurrentPage = 1;//获取数据当前页数

    private HomeTemplateRedesignAdapter mAdapter;

    /**
     * 返回一个用于显示界面的布局id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.home_customized_redesign_fragment;
    }


    @Override
    protected HomeTemplateRedesignFragmentPresenter createPresenter() {
        return new HomeTemplateRedesignFragmentPresenter();
    }


    /**
     * 初始化View的代码写在这个方法中
     */
    @Override
    protected void initView() {

    }

    /**
     * 初始化监听器的代码写在这个方法中
     */
    @Override
    protected void initListener() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }


    /**
     * 初始数据的代码写在这个方法中，用于从服务器获取数据
     */
    @Override
    protected void initData() {

        //初始化adapter
        initAdapter();

    }

    /** ==================创建Fragment===================== */
    public static Fragment newInstance(int type) {
        HomeTemplateRedesignFragment fragment = new HomeTemplateRedesignFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(HomeTemplateRedesignFragment.FRAGMENT_TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    /** ==================初始化adapter===================== */
    private void initAdapter() {
        mAdapter = new HomeTemplateRedesignAdapter();
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRecyclerView.addItemDecoration(new MyDividerItemDecoration(getContext()));
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mAdapter.setLoadMoreView(new SimpleLoadMoreView());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(this);
    }

    /**
     * 请求网络
     */
    @Override
    public void onLazyLoad() {

        mCurrentPage = 1;

        Bundle arguments = getArguments();
        mType = arguments.getInt(HomeTemplateRedesignFragment.FRAGMENT_TYPE);
        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        requestNetwork();
    }

    /** ==================第一次请求网络===================== */
    private void requestNetwork() {

        Map<String, String> map = new HashMap<>();
        map.put("PageIndex", String.valueOf(mCurrentPage));
//        map.put("PageCount", Constant.PAGE_COUNT);
        map.put("PageCount", "0");
        map.put("OrderByValue", "create_datetime");
        map.put("OrderBy", "desc");

        if (mType == HOME_CASE) {
            //案例
            mPresenter.requestCaseData(map);
        } else {
            //模板
            mPresenter.requestProductData(map);
        }

    }


    /**
     * 案例数据
     *
     * @param data 案例数据
     */
    @Override
    public void showCaseData(TemplateReadesignEntity data) {

        mAdapter.setEnableLoadMore(true); //允许加载更多
        mSwipeRefreshLayout.setRefreshing(false); //禁止刷新

        setData(true, data);
    }

    /**
     * 案例数据
     *
     * @param data 案例加载更多数据
     */
    @Override
    public void showCaseDataLoadMore(TemplateReadesignEntity data) {
        setData(false, data);
    }

    /**
     * 模板数据
     *
     * @param data 模板数据
     */

    @Override
    public void showProductData(TemplateReadesignEntity data) {

        Logger.d("showProductData--->:" + data);
        mAdapter.setEnableLoadMore(true); //允许加载更多
        mSwipeRefreshLayout.setRefreshing(false); //禁止刷新
        setData(true, data);

    }

    /**
     * 模板数据
     *
     * @param data 模板加载更多数据
     */
    @Override
    public void showProductLoadMoreData(TemplateReadesignEntity data) {

        setData(false, data);
    }




    /**
     * 加载更多
     *
     * @param msg 加载更多错误信息
     */
    @Override
    public void showLoadMoreError(String msg) {
        mAdapter.loadMoreFail();
    }


    /** ==================设置数据===================== */

    private void setData(boolean isRefresh, TemplateReadesignEntity data) {

        mLayoutStatusView.showContent();//显示内容
        mCurrentPage++;

        List<TemplateReadesignEntity.RowsBean> beanList = new ArrayList<>();

        for (int i = 0; i < data.getRows().size(); i++) {
            beanList.add(data.getRows().get(i));
        }

        final int size = beanList == null ? 0 : beanList.size();

        if (isRefresh) {

            //第一次加载数据,发现没有就显示空布局
            if (size == 0) {
                mLayoutStatusView.showEmpty();
                return;
            }

            //有就设置新的数据
            mAdapter.setNewData(beanList);

        } else {

            //加载更多
            if (size > 0) {
                mAdapter.addData(beanList);
            }
        }

        //第一页如果不够一页就不显示没有更多数据布局
        if (size < Constant.PAGE_SIZE) {
            mAdapter.loadMoreEnd(isRefresh);
        } else {
            mAdapter.loadMoreComplete();
        }

    }


    //请求错误
    @Override
    public void showError(String msg, int code) {
        ToastUtils.showShort(msg);
        mLayoutStatusView.showError();
    }


    /**
     * 刷新
     */
    @Override
    public void onRefresh() {
        onLazyLoad();
    }

    /**
     * 加载更多
     */
    @Override
    public void onLoadMoreRequested() {
        requestLoadMoreNetwork();
    }

    /** ==================加载更多请求网络===================== */
    private void requestLoadMoreNetwork() {
        Map<String, String> map = new HashMap<>();
        map.put("PageIndex", String.valueOf(mCurrentPage));
        map.put("PageCount", Constant.PAGE_COUNT);
        map.put("OrderByValue", "create_datetime");
        map.put("OrderBy", "desc");

        if (mType == HOME_CASE) {
            //案例
            mPresenter.requestCaseLoadMoreData(map);
            Logger.d("requestLoadMoreNetwork--->:" + mType);
        } else {
            //模板
            mPresenter.requestProductLoadMoreData(map);
        }

    }


    @Override
    public void showNetworkError(String msg, int code) {
        ToastUtils.showShort(msg);
        mLayoutStatusView.showNoNetwork();
    }
}
