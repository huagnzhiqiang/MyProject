package com.shangjie.itop.ui.main.market.recoment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shangjie.baselibs.base.BaseFragment;
import com.shangjie.baselibs.utils.ToastUtils;
import com.shangjie.itop.adapter.RecomentAdapter;
import com.shangjie.itop.constants.Constant;
import com.shangjie.itop.data.entity.RecomentEntity;
import com.shangjie.itop.view.TopCountView;
import com.shangjie1.itop.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @author 小强
 * @time 2018/10/26  18:20
 * @desc 自营销推荐页面
 */
public class RecomentFragment extends BaseFragment<RecomentPresenter> implements RecomentContract.View, OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {


    public static final int LOCAL = 4;//本地标识

    @BindView(R.id.tip_view) TopCountView mTipView;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout) SmartRefreshLayout mRefreshLayout;


    //跳转页面key
    public static final String STRAT_TYPE = "start_type";

    private int mCurrentPage = 1;
    private RecomentAdapter mAdapter;

    private int mStartType = -1;//页面标识 判断哪个页面跳转过来的

    /**
     * 返回一个用于显示界面的布局id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recoment;
    }

    @Override
    protected RecomentPresenter createPresenter() {
        return new RecomentPresenter();
    }

    /** ==================初始化适配器===================== */
    private void initAdapter() {
        mAdapter = new RecomentAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mAdapter.setLoadMoreView(new SimpleLoadMoreView());
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
    }


    /**
     * 初始化监听器的代码写在这个方法中
     */

    @Override
    protected void initListener() {
        mRefreshLayout.setOnRefreshListener(this);


    }

    /**
     * 初始化懒加载的数据 (请求网络)
     */
    @Override
    public void onLazyLoad() {
        initAdapter();
        Logger.d(" onResume  onLazyLoad初始化懒加载的数据 (请求网络)--->:");
        mRefreshLayout.autoRefresh();

    }


    @Override
    public void onResume() {
        super.onResume();
        Logger.d("onResume 初始化懒加载的数据 (请求网络)--->:");

    }

    /**
     * 刷新
     */
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        requestNetwork();
    }


    /** ==================请求数据参数===================== */
    @NonNull
    private Map<String, String> requestMap() {
        Map<String, String> map = new HashMap<>();
        map.put("Commend", "true");
        map.put("Show", "" + true);
        map.put("CheckStatus", String.valueOf(2));   //6.12修改
        map.put("PageIndex", String.valueOf(mCurrentPage));
        map.put("PageCount", Constant.PAGE_COUNT);
        //        map.put("PageCount", "1");
        map.put("OrderByValue", "publish_datetime");  //6.27修改
        map.put("OrderBy", "desc");//6.27修改

        switch (mStartType) {
            //H5
            case Constant.articleType.ARTICLE_H5:
                map.put("Article_type", String.valueOf(Constant.articleType.ARTICLE_H5));
                break;

            //资讯
            case Constant.articleType.ARTICLE_INFORMATION:
                map.put("Article_type", String.valueOf(Constant.articleType.ARTICLE_INFORMATION));
                break;

            //视频
            case Constant.articleType.ARTICLE_VIDEO:
                map.put("Article_type", String.valueOf(Constant.articleType.ARTICLE_VIDEO));
                break;

            //本地
            case LOCAL:
                map.put("city_code", "440100");
                break;
        }

        return map;
    }


    /** ==================第一次和刷新请求网络===================== */
    private void requestNetwork() {
        mCurrentPage = 1;
        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        mPresenter.requestRecomentData(requestMap());

        Logger.d("第一次和刷新请求网络requestNetwork--->:");
    }

    /**
     * 加载更多请求
     */
    @Override
    public void onLoadMoreRequested() {

        // 加载更多的触发
        mPresenter.requestRecomentLoadMoreData(requestMap());

    }


    /**
     * 自营销推荐数据
     *
     * @param dataBean 自营销推荐数据
     */
    @Override
    public void showRecomentData(RecomentEntity dataBean) {
        Logger.d("showRecomentData--->:" + dataBean.toString());
        mAdapter.setEnableLoadMore(true); //允许加载更多
        mRefreshLayout.finishRefresh();//关闭刷新
        setAdapterData(true, dataBean);
    }


    /**
     * 自营销推荐加载更多数据
     *
     * @param dataBean 自营销推荐加载更多数据
     */
    @Override
    public void showRecomentLoadMoreData(RecomentEntity dataBean) {
        setAdapterData(false, dataBean);
    }


    /**
     * 设置Adapter数据
     *
     * @param isRefresh true:第一次刷新  false:加载更多数据
     * @param bean      Adapter填充的数据
     */
    private void setAdapterData(boolean isRefresh, RecomentEntity bean) {
        mLayoutStatusView.showContent();//显示内容
        mCurrentPage++;
        Logger.d("setAdapterData--->:" + isRefresh);

        final List<RecomentEntity.RowsBean> data = bean.getRows();
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
        if (size < Constant.PAGE_SIZE) {
            mAdapter.loadMoreEnd(true);
            ToastUtils.showShort(Constant.NO_LOAD_MORE);

        } else {
            //加载更多的触发
            mAdapter.loadMoreComplete();
            Logger.d("setAdapterData--->:");
        }


        //这种也可以触发加载更多
        //        Logger.d("setAdapterData--->:" + mAdapter.getItemCount());
        //                //第一页如果不够一页就不显示没有更多数据布局
        //                if (mAdapter.getItemCount()-1 == bean.getTotal()) {
        //                    mAdapter.loadMoreEnd(false);
        //                } else {
        //        //            mAdapter.loadMoreComplete();
        //                    Logger.d("setAdapterData--->:");
        //                }
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
     * 自营销推荐页面
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
     * 初始化fragment
     *
     * @param type 页面标识
     * @return 当前Fragment
     */
    public static RecomentFragment newInstance(int type) {
        RecomentFragment fragment = new RecomentFragment();
        Bundle args = new Bundle();
        args.putInt(STRAT_TYPE, type);
        fragment.setArguments(args);
        fragment.mStartType = type;
        return fragment;
    }


}
