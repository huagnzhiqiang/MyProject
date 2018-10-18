package com.hzq.example.ui.main.home.customized;

import com.hzq.baselibs.mvp.IModel;
import com.hzq.baselibs.mvp.IView;
import com.hzq.baselibs.net.BaseHttpResult;
import com.hzq.example.data.entity.HomeCustomizeEntity;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @author 小强
 * @time 2018/10/16  14:05
 * @desc 首页定制页面契约类
 */
public interface HomeCustomizedRedesignContract {

    interface View extends IView{

        /**
         * 首页定制数据
         *
         * @param data 定制数据
         */
        void showCustomizedData(HomeCustomizeEntity data);

        /**
         * 首页定制数据
         *
         * @param data 定制加载更多数据
         */
        void showCustomizedLoadMore(HomeCustomizeEntity data);


        /**
         * 首页定制加载更多错误
         *
         * @param msg 加载更多错误信息
         */
        void showLoadMoreError(String msg);
    }

    interface Model extends IModel {
        /**
         * 请求首页定制数据
         */
        Observable<BaseHttpResult<HomeCustomizeEntity>> getCustomizedData(Map<String, String> map);

        /**
         * 请求首页定制更多数据
         */
        Observable<BaseHttpResult<HomeCustomizeEntity>> getCustomizedLoadMoreData(Map<String, String> map);
    }
}
