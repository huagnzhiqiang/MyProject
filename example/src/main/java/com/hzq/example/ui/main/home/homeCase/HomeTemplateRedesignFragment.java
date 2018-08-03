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

    //true表示刷新 false表示加载更多
    private boolean isRefresh;

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
        mAdapter.setOnLoadMoreListener(this);

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
    }

    /**
     * 请求网络
     */
    @Override
    public void onLazyLoad() {

        isRefresh = true;
        mCurrentPage = 1;

        Bundle arguments = getArguments();
        mType = arguments.getInt(HomeTemplateRedesignFragment.FRAGMENT_TYPE);
        if (mType == 1) {
            mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
            loadMore();
            ToastUtils.showShort("案例");
        } else {
            ToastUtils.showShort("模板");
        }
    }

    /** ==================加载更多===================== */
    private void loadMore() {
        Map<String, String> map = new HashMap<>();
        map.put("PageIndex", String.valueOf(mCurrentPage));
        map.put("PageCount", Constant.PAGE_COUNT);
        map.put("OrderByValue", "create_datetime");
        map.put("OrderBy", "desc");
        mPresenter.requestCaseData(map);
    }




    /**
     * 案例数据
     *
     * @param data 案例数据
     */
    @Override
    public void showCaseData(TemplateReadesignEntity data) {

        mLayoutStatusView.showContent();//显示内容
        mAdapter.setEnableLoadMore(true); //允许加载更多
        mSwipeRefreshLayout.setRefreshing(false); //禁止刷新

        List<TemplateReadesignEntity.RowsBean> list = new ArrayList<>();
        for (int i = 0; i < data.getRows().size(); i++) {
            list.add(data.getRows().get(i));
        }
        setData(list);
    }


    /** ==================设置数据===================== */

    private void setData(List<TemplateReadesignEntity.RowsBean> data) {
        mCurrentPage++;
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
            mAdapter.loadMoreEnd(isRefresh);
        } else {
            mAdapter.loadMoreComplete();
        }

        //表示加载更多标识
        isRefresh = false;

    }


    //请求错误
    @Override
    public void showError(String msg) {
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
        loadMore();
    }


}
