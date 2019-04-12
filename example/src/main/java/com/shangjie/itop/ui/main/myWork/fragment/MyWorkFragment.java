package com.shangjie.itop.ui.main.myWork.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shangjie.baselibs.base.BaseFragment;
import com.shangjie.baselibs.utils.DisplayUtils;
import com.shangjie.baselibs.utils.ToastUtils;
import com.shangjie.itop.adapter.MyWorkAdapter;
import com.shangjie.itop.constants.Constant;
import com.shangjie.itop.data.Login.LoginMsgHelper;
import com.shangjie.itop.data.entity.MyProductEntity;
import com.shangjie.itop.ui.main.myWork.contract.MyWorkContract;
import com.shangjie.itop.ui.main.myWork.presenter.MyPresenter;
import com.shangjie.itop.view.recycleView.SpaceItemDecoration;
import com.shangjie1.itop.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @author 小强
 * @time 2018/8/1  16:45
 * @desc 我的作品页面
 */
public class MyWorkFragment extends BaseFragment<MyPresenter> implements MyWorkContract.View, OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {


    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout) SmartRefreshLayout mRefreshLayout;
    private int mCurrentPage = 1;

    private MyWorkAdapter mAdapter;

    /**
     * 返回一个用于显示界面的布局id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.base_recycler_view;
    }

    @Override
    protected MyPresenter createPresenter() {
        return new MyPresenter();
    }

    /**
     * 初始化View的代码写在这个方法中
     */
    @Override
    protected void initView(View view) {
        Logger.d("onResume--->:initView");

    }

    /**
     * 初始化监听器的代码写在这个方法中
     */
    @Override
    protected void initListener() {
        mRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();


    }


    /**
     * 请求网络
     */
    @Override
    public void onLazyLoad() {
        initAdapter();
        Logger.d("onResume--->:请求网络");
        if (LoginMsgHelper.isLogin(getActivity())) {
            //用户类型
            mRefreshLayout.autoRefresh();
        }
    }

    //初始化适配器
    private void initAdapter() {
        mAdapter = new MyWorkAdapter();
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(DisplayUtils.dip2px(getContext(), 20), SpaceItemDecoration.GRIDLAYOUT));
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mAdapter.setLoadMoreView(new SimpleLoadMoreView());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(this);

    }

    //刷新
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

        if(LoginMsgHelper.isLogin(getActivity())) {
            requestNetwork();
        }
    }


    //加载更多
    @Override
    public void onLoadMoreRequested() {

        if(LoginMsgHelper.isLogin(getActivity())) {
            mPresenter.requestMyWorkLoadMoreData(getRequestMap());
        }
    }


    /** ==================第一次和刷新请求网络===================== */
    private void requestNetwork() {
        mCurrentPage = 1;
        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        mPresenter.requestMyWorkData(getRequestMap());
    }


    //请求参数
    @NonNull
    private Map<String, String> getRequestMap() {
        Map<String, String> map = new HashMap<>();
        map.put("PageCount", Constant.PAGE_COUNT);
//        map.put("PageCount", "0");
        map.put("PageIndex", String.valueOf(mCurrentPage));
        return map;
    }


    /**
     * 我的作品数据
     *
     * @param dataBean 我的作品数据
     */
    @Override
    public void showMyWorkData(MyProductEntity dataBean) {
        mAdapter.setEnableLoadMore(true); //允许加载更多
        mRefreshLayout.finishRefresh();//关闭刷新
        setAdapterData(true, dataBean);
    }

    /**
     * 我的作品更多数据
     *
     * @param dataBean 我的作品更多数据
     */
    @Override
    public void showMyWorkLoadMoreData(MyProductEntity dataBean) {
        setAdapterData(false, dataBean);
    }


    /**
     * 设置Adapter数据
     *
     * @param isRefresh true:第一次刷新  false:加载更多数据
     * @param bean      Adapter填充的数据
     */
    private void setAdapterData(boolean isRefresh, MyProductEntity bean) {
        mLayoutStatusView.showContent();//显示内容
        mCurrentPage++;
        Logger.d("setAdapterData--->:" + isRefresh);
        final List<MyProductEntity.RowsBean> data = bean.getRows();
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


        //没有数据就隐藏数据布局 否则触发加载更多
        if (size <Constant.PAGE_SIZE) {
            mAdapter.loadMoreEnd(true);
            ToastUtils.showShort(Constant.NO_LOAD_MORE);

        } else {
            //加载更多的触发
            mAdapter.loadMoreComplete();
            Logger.d("setAdapterData--->:");
        }


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
        mAdapter.setEnableLoadMore(true); //允许加载更多
        mRefreshLayout.finishRefresh(false);//关闭刷新-->刷新失败
        if (mAdapter.getItemCount() <= 0) {
            mLayoutStatusView.showError();
        }
    }

    /**
     * 我的作品加载更多错误信息
     *
     * @param msg 加载更多错误信息
     */
    @Override
    public void showLoadMoreError(String msg) {
        ToastUtils.showShort(msg);
        mAdapter.loadMoreFail();
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
        mAdapter.setEnableLoadMore(true); //允许加载更多
        mRefreshLayout.finishRefresh(false);//关闭刷新-->刷新失败
        if (mAdapter.getItemCount() <= 0) {
            mLayoutStatusView.showNoNetwork();
        }
    }


    /**
     * 加载过数据后，fragment变为不可见之后的需要执行的操作
     */
    @Override
    public void InVisibleEvent() {
        Logger.d("lazyLoad--->子类:" + "加载过数据");

    }
}
