package com.hzq.example.ui.main.home.designer;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView;
import com.hzq.baselibs.app.BaseApplication;
import com.hzq.baselibs.base.BaseFragment;
import com.hzq.baselibs.utils.NetworkUtils;
import com.hzq.baselibs.utils.ToastUtils;
import com.hzq.baselibs.view.MultipleStatusView;
import com.hzq.example.R;
import com.hzq.example.adapter.HomeDesignerRedesignAdapter;
import com.hzq.example.constants.Constant;
import com.hzq.example.data.entity.HomeDesignerEntity;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * @author 小强
 * @time 2018/8/2  15:31
 * @desc 首页设计师页面
 */
public class HomeDesignerRedesignFragment extends BaseFragment<HomeDesignerRedesignPresenter> implements HomeDesignerRedesignContract.View, OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.home_iv_viewSwitches) ImageView mIvViewSwitches;
    @BindView(R.id.home_iv_viewScreening) LinearLayout mIvViewScreening;
    @BindView(R.id.ll_head) LinearLayout mLlHead;
    @BindView(R.id.rl_home_CustomRequirements) RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout) SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.multipleStatusView) MultipleStatusView mMultipleStatusView;

    private HomeDesignerRedesignAdapter mAdapter;

    private int mCurrentPage = 1;

    //设计师数据集合
    private ArrayList<HomeDesignerEntity.RowsBean> mDataList = new ArrayList<>();

    /**
     * 返回一个用于显示界面的布局id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.home_customized_redesign_fragment;
    }

    @Override
    protected HomeDesignerRedesignPresenter createPresenter() {
        return new HomeDesignerRedesignPresenter();
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
        mRefreshLayout.setOnRefreshListener(this);
    }

    /**
     * 初始数据的代码写在这个方法中，用于从服务器获取数据
     */
    @Override
    protected void initData() {
        //初始化adapter
        initAdapter();
    }


    /** ==================初始化adapter===================== */
    private void initAdapter() {
        mAdapter = new HomeDesignerRedesignAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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

        //第一次进来就刷新页面
        mRefreshLayout.autoRefresh();

        mCurrentPage = 1;

        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载

        Map<String, String> map = new HashMap<>();
        //        map.put("city_code", mAdCode);//城市
        map.put("demand_type", "1");
        map.put("PageIndex", String.valueOf(mCurrentPage));
        map.put("PageCount", Constant.PAGE_COUNT);
        map.put("orderByValue", "create_datetime");//排序字段
        map.put("orderBy", "desc");//排序方式(asc正序,desc倒序)
        mPresenter.requestHomeDesignerData(map);
    }


    /** ==================初始化fragment===================== */
    public static Fragment newInstance() {
        return new HomeDesignerRedesignFragment();
    }


    /**
     * 刷新
     */

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

        if (NetworkUtils.isNetworkAvailable(BaseApplication.getContext())) {
            onLazyLoad();
        } else {
            ToastUtils.showShort("网络不可用");
            mRefreshLayout.finishRefresh();

        }
    }


    /**
     * 首页设计师数据
     *
     * @param data 设计师数据
     */
    @Override
    public void showHomeDesignerData(HomeDesignerEntity data) {
        mAdapter.setEnableLoadMore(true); //允许加载更多
        mRefreshLayout.finishRefresh();//关闭刷新
        setData(true, data);
    }

    /**
     * 首页设计师数据
     *
     * @param data 设计师加载更多数据
     */
    @Override
    public void showHomeDesignerLoadMore(HomeDesignerEntity data) {
        setData(false, data);
    }

    /** ==================设置数据===================== */

    private void setData(boolean isRefresh, HomeDesignerEntity data) {

        mLayoutStatusView.showContent();//显示内容
        mCurrentPage++;

        mDataList = new ArrayList<>();
        final int dataSize = data.getRows().size();
        for (int i = 0; i < dataSize; i++) {
            mDataList.add(data.getRows().get(i));
        }

        final int size = mDataList == null ? 0 : mDataList.size();

        Logger.d("setData--->:" + size);
        if (isRefresh) {

            //第一次加载数据,发现没有就显示空布局
            if (size == 0) {
                mLayoutStatusView.showEmpty();
                return;
            }

            //有就设置新的数据
            mAdapter.setNewData(mDataList);

        } else {

            //加载更多
            if (size > 0) {
                mAdapter.addData(mDataList);

            } else {
                ToastUtils.showShort("没有更多数据了");
            }
        }

        //第一页如果不够一页就不显示没有更多数据布局
        if (size < Constant.PAGE_SIZE) {
            mAdapter.loadMoreEnd(isRefresh);
        } else {
            mAdapter.loadMoreComplete();
        }
        if (size == 0) {
            mAdapter.loadMoreEnd(true);
        }
    }

    /**
     * 加载更多请求
     */
    @Override
    public void onLoadMoreRequested() {
        Map<String, String> map = new HashMap<>();
        //        map.put("city_code", mAdCode);//城市
        map.put("demand_type", "1");
        map.put("PageIndex", String.valueOf(mCurrentPage));
        map.put("PageCount", Constant.PAGE_COUNT);
        map.put("orderByValue", "create_datetime");//排序字段
        map.put("orderBy", "desc");//排序方式(asc正序,desc倒序)
        mPresenter.requestHomeDesignerLoadMoreData(map);
    }

        /**
         * 首页设计师加载更多错误
         *
         * @param msg 加载更多错误信息
         */
    @Override
    public void showLoadMoreError(String msg) {
        mAdapter.loadMoreFail();
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
        //        mAdapter.setEnableLoadMore(true); //允许加载更多
        mRefreshLayout.finishRefresh(false);//关闭刷新-->刷新失败
        if (mDataList.size() <= 0) {
            mLayoutStatusView.showError();
        }
    }




    /**
     * 显示网络错误
     *
     * @param msg  错误信息
     * @param code 错误code
     */
    @Override
    public void showNetworkError(String msg, int code) {
        ToastUtils.showShort(msg);
        mRefreshLayout.finishRefresh(false);//关闭刷新-->刷新失败
        if (mDataList.size() <= 0) {
            mLayoutStatusView.showNoNetwork();
        }
    }


}
