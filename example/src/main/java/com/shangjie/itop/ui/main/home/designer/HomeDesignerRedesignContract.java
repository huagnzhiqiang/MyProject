package com.shangjie.itop.ui.main.home.designer;

import com.shangjie.baselibs.mvp.IModel;
import com.shangjie.baselibs.mvp.IView;
import com.shangjie.baselibs.net.BaseHttpResult;
import com.shangjie.itop.data.entity.HomeDesignerEntity;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @author 小强
 * @time 2018/10/18  14:11
 * @desc 首页设计师页面契约类
 */
public interface HomeDesignerRedesignContract {

    interface View extends IView {

        /**
         * 首页设计师数据
         *
         * @param data 设计师数据
         */
        void showHomeDesignerData(HomeDesignerEntity data);

        /**
         * 首页设计师数据
         *
         * @param data 设计师加载更多数据
         */
        void showHomeDesignerLoadMoreData(HomeDesignerEntity data);


        /**
         * 首页设计师加载更多错误
         *
         * @param msg 加载更多错误信息
         */
        void showLoadMoreError(String msg);

        /**
         * 首页关注设计师
         */
        void showFollowDesignersData(String msg);

        /**
         * 首页取消关注设计师
         */
        void showUnFollowDesignersData(String msg);

    }


    interface Model extends IModel {

        /**
         * 请求首页设计师数据和更多数据
         */
        Observable<BaseHttpResult<HomeDesignerEntity>> requestHomeDesignerData(Map<String, String> map);

        /**
         * 请求首页设计师
         */
        Observable<BaseHttpResult<String>> requestFollowDesignersData(int id);

        /**
         * 请求首页取消关注设计师
         */
        Observable<BaseHttpResult<String>> requestUnFollowDesignersData(int id);
    }
}
