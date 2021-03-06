package com.shangjie.itop.ui.main.home.homeCase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shangjie.baselibs.base.BaseFragment;
import com.shangjie.baselibs.utils.DisplayUtils;
import com.shangjie.baselibs.utils.ToastUtils;
import com.shangjie.itop.adapter.HomeTemplateRedesignAdapter;
import com.shangjie.itop.constants.Constant;
import com.shangjie.itop.data.entity.TemplateReadesignEntity;
import com.shangjie.itop.view.recycleView.SpaceItemDecoration;
import com.shangjie1.itop.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @author 小强
 * @time 2018/8/2  15:22
 * @desc 首页案例/模板页面
 */
public class HomeTemplateRedesignFragment extends BaseFragment<HomeTemplateRedesignFragmentPresenter> implements HomeTemplateRedesignFragmentContract.View, BaseQuickAdapter.RequestLoadMoreListener, OnRefreshListener {

    private static final String FRAGMENT_TYPE = "fragment_type";//跳转页面Key
    public static final int HOME_CASE = 1;//案例type
    public static final int HOME_TEMPLATE = 2;//模板type

    @BindView(R.id.home_iv_viewSwitches) ImageView mHomeIvViewSwitches;
    @BindView(R.id.home_iv_viewScreening) LinearLayout mHomeIvViewScreening;
    @BindView(R.id.ll_head) LinearLayout mLlHead;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout) SmartRefreshLayout mRefreshLayout;

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
     * 初始化监听器的代码写在这个方法中
     */
    @Override
    protected void initListener() {
        Logger.d("lazyLoad---> 请求网络 initListener--->:");

        mRefreshLayout.setOnRefreshListener(this);
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
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(DisplayUtils.dip2px(getContext(), 20), SpaceItemDecoration.GRIDLAYOUT));
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mAdapter.setLoadMoreView(new SimpleLoadMoreView());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(this);
    }

    /**
     * 初始化懒加载的数据 (请求网络)
     */
    @Override
    public void onLazyLoad() {
        //初始化adapter
        initAdapter();

        mRefreshLayout.autoRefresh();
    }

    //刷新
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        requestNetwork();
    }


    /** ==================第一次请求网络===================== */
    private void requestNetwork() {

        mCurrentPage = 1;
        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        Map<String, String> map = new HashMap<>();
        map.put("PageIndex", String.valueOf(mCurrentPage));
        map.put("PageCount", Constant.PAGE_COUNT);
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
        mRefreshLayout.finishRefresh();
        setAdapterData(true, data);
    }

    /**
     * 案例数据
     *
     * @param data 案例加载更多数据
     */
    @Override
    public void showCaseDataLoadMore(TemplateReadesignEntity data) {
        setAdapterData(false, data);
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
        mRefreshLayout.finishRefresh();//关闭刷新
        setAdapterData(true, data);

    }

    /**
     * 模板数据
     *
     * @param data 模板加载更多数据
     */
    @Override
    public void showProductLoadMoreData(TemplateReadesignEntity data) {
        setAdapterData(false, data);
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


    /**
     * 设置Adapter数据
     *
     * @param isRefresh true:第一次刷新  false:加载更多数据
     * @param bean      Adapter填充的数据
     */
    private void setAdapterData(boolean isRefresh, TemplateReadesignEntity bean) {

        Logger.d("setAdapterData--->:" + isRefresh);


        mLayoutStatusView.showContent();//显示内容
        mCurrentPage++;

        final List<TemplateReadesignEntity.RowsBean> data = bean.getRows();
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {

            //第一次加载数据,发现没有就显示空布局
            if (size == 0) {
                mLayoutStatusView.showEmpty();
                return;
            }

            //有就设置新的数据
            mAdapter.setNewData(data);

        } else {

            //加载更多
            if (size > 0) {
                mAdapter.addData(data);
            }
        }
        //第一页如果不够一页就不显示没有更多数据布局
        if (size < Constant.PAGE_SIZE) {
            mAdapter.loadMoreEnd(true);
            ToastUtils.showShort(Constant.NO_LOAD_MORE);
        } else {
            //加载更多的触发
            mAdapter.loadMoreComplete();
            Logger.d("setAdapterDatasetAdapterData--->:"  );

        }

    }


    //请求错误
    @Override
    public void showError(String msg, int code) {
        ToastUtils.showShort(msg);
        mAdapter.setEnableLoadMore(true); //允许加载更多
        mRefreshLayout.finishRefresh(false);//关闭刷新-->刷新失败
        if (mAdapter.getItemCount() <= 0) {
            mLayoutStatusView.showError();
        }
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

        Logger.d("加载更多请求--->:");

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
        mAdapter.setEnableLoadMore(true); //允许加载更多
        mRefreshLayout.finishRefresh(false);//关闭刷新-->刷新失败
        if (mAdapter.getItemCount() <= 0) {
            mLayoutStatusView.showNoNetwork();
        }
    }



    /**
     * 获取 Bundle 数据
     */
    @Override
    protected void getBundle(Bundle arguments) {
        mType = arguments.getInt(HomeTemplateRedesignFragment.FRAGMENT_TYPE);
    }
}
