package com.shangjie.itop.ui.main.home.customized;

import com.shangjie.baselibs.mvp.IModel;
import com.shangjie.baselibs.mvp.IView;
import com.shangjie.baselibs.net.BaseHttpResult;
import com.shangjie.itop.data.entity.HomeCustomizeEntity;

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
        Observable<BaseHttpResult<HomeCustomizeEntity>> requestCustomizedData(Map<String, String> map);


    }
}
