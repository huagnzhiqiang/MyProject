package com.hzq.example.ui.main.home.customized;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView;
import com.hzq.baselibs.base.BaseFragment;
import com.hzq.baselibs.view.MultipleStatusView;
import com.hzq.example.R;
import com.hzq.example.adapter.HomeCustomizedRedesignAdapter;
import com.hzq.example.constants.Constant;
import com.hzq.example.data.entity.HomeCustomizeEntity;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * @author 小强
 * @time 2018/8/2  15:28
 * @desc 首页定制页面
 */
public class HomeCustomizedRedesignFragment extends BaseFragment<HomeCustomizedRedesignPersenter> implements HomeCustomizedRedesignContract.View, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.refreshLayout) SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.multipleStatusView) MultipleStatusView mMultipleStatusView;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    private HomeCustomizedRedesignAdapter mAdapter;
    private int mCurrentPage = 1;

    //适配器数据集合
    private ArrayList<HomeCustomizeEntity.RowsBean> mDataList = new ArrayList<>();

    /**
     * 返回一个用于显示界面的布局id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.home_template_redesign_fragment;
    }

    @Override
    protected HomeCustomizedRedesignPersenter createPresenter() {
        return new HomeCustomizedRedesignPersenter();
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

    }

    /**
     * 初始数据的代码写在这个方法中，用于从服务器获取数据
     */
    @Override
    protected void initData() {
        //初始化adapter
        initAdapter();

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
        map.put("orderByValue", "create_datetime");
        map.put("orderBy", "desc");
        map.put("PageIndex", mCurrentPage + "");
        map.put("PageCount", Constant.PAGE_COUNT);
        mPresenter.requestCustomizedData(map);
    }


    /** ==================初始化fragment===================== */
    public static Fragment newInstance() {
        return new HomeCustomizedRedesignFragment();
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

    /** ==================初始化adapter===================== */
    private void initAdapter() {
        mAdapter = new HomeCustomizedRedesignAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mAdapter.setLoadMoreView(new SimpleLoadMoreView());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(this);
    }

    /**
     * 首页定制数据
     *
     * @param data 定制数据
     */
    @Override
    public void showCustomizedData(HomeCustomizeEntity data) {
        mAdapter.setEnableLoadMore(true); //允许加载更多
        mRefreshLayout.finishRefresh();
        setData(true, data);
    }

    /** ==================设置数据===================== */

    private void setData(boolean isRefresh, HomeCustomizeEntity data) {

        mLayoutStatusView.showContent();//显示内容
        mCurrentPage++;

        mDataList = new ArrayList<>();
        final int dataSize = data.getRows().size();
        for (int i = 0; i < dataSize; i++) {
            mDataList.add(data.getRows().get(i));
        }

        final int size = mDataList == null ? 0 : mDataList.size();

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
     * 首页定制数据
     *
     * @param data 定制加载更多数据
     */
    @Override
    public void showCustomizedLoadMore(HomeCustomizeEntity data) {
        Logger.d("showCustomizedData--->:" + data);

    }

    @Override
    public void onLoadMoreRequested() {

    }
}
