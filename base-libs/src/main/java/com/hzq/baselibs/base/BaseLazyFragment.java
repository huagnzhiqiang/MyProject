package com.hzq.baselibs.base;

import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * @author 小强
 * @time 2018/10/27  10:03
 * @desc 懒加载 Fragment 基类
 */
public abstract class BaseLazyFragment extends RxFragment {

    /**
     * 是否对用户可见的标志位
     */
    private boolean isVisible;
    /**
     * 判断view是不是已经填充完毕的标记位
     */
    protected boolean isPrepared;
    /**
     * 是否已经加载过数据
     */
    private boolean isAlreadyLoadData ;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //可见
            isVisible = true;
            onVisible();
        } else {
            //不可见
            isVisible = false;
            onInVisible();
        }
    }

    /**
     * setUserVisibleHint为true时调用的方法
     */
    private void onVisible() {
        lazyLoad();
    }

    /**
     * setUserVisibleHint为false时调用的方法
     */
    private void onInVisible() {
        if (isAlreadyLoadData) {
            InVisibleEvent();
        }
    }

    protected void lazyLoad() {
        //确保View初始化完成
        if (!isVisible || !isPrepared) {
            Logger.d("lazyLoad--->:" + "确保View初始化完成");
            return;
        }

        //加载数据
        if (!isAlreadyLoadData) {//如果没有加载过数据
            Logger.d("lazyLoad--->:" + "如果没有加载过数据");
            onLazyLoad();
            isAlreadyLoadData = true;
            isVisible = false;
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isAlreadyLoadData = true;
        isVisible = false;
    }

    /**
     * 初始化懒加载的数据 (请求网络)
     */
    public abstract void onLazyLoad();


    /**
     * 加载过数据后，fragment变为不可见之后的需要执行的操作
     */
    public void InVisibleEvent() {
    }


}