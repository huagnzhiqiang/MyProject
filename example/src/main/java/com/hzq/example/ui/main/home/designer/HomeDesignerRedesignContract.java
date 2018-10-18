package com.hzq.example.ui.main.home.designer;

import com.hzq.baselibs.mvp.IModel;
import com.hzq.baselibs.mvp.IView;
import com.hzq.baselibs.net.BaseHttpResult;
import com.hzq.example.data.entity.HomeDesignerEntity;

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
        void showHomeDesignerLoadMore(HomeDesignerEntity data);


        /**
         * 首页设计师加载更多错误
         *
         * @param msg 加载更多错误信息
         */
        void showLoadMoreError(String msg);
    }


    interface Model extends IModel {

        /**
         * 请求首页设计师数据
         */
        Observable<BaseHttpResult<HomeDesignerEntity>> getHomeDesignerData(Map<String, String> map);

        /**
         * 请求首页设计师更多数据
         */
        Observable<BaseHttpResult<HomeDesignerEntity>> getHomeDesignerLoadMoreData(Map<String, String> map);
    }
}
