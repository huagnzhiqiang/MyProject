package com.shangjie.itop.ui.main.home.homeCase;

import com.shangjie.baselibs.mvp.IModel;
import com.shangjie.baselibs.mvp.IView;
import com.shangjie.baselibs.net.BaseHttpResult;
import com.shangjie.itop.data.entity.TemplateReadesignEntity;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @author 小强
 * @time 2018/8/2  16:41
 * @desc 首页案例/模板页面契约类
 */
public interface HomeTemplateRedesignFragmentContract {

    interface View extends IView {

        /**
         * 案例数据
         *
         * @param data 案例数据
         */
        void showCaseData(TemplateReadesignEntity data);

        /**
         * 案例数据
         *
         * @param data 案例加载更多数据
         */
        void showCaseDataLoadMore(TemplateReadesignEntity data);


        /**
         * 模板数据
         *
         * @param data 模板更多数据
         */
        void showProductData(TemplateReadesignEntity data);

        /**
         * 模板数据
         *
         * @param data 模板加载更多数据
         */
        void showProductLoadMoreData(TemplateReadesignEntity data);


        /**
         * 加载更多
         *
         * @param msg 加载更多错误信息
         */
        void showLoadMoreError(String msg);
    }

    interface Model extends IModel {

        /**
         * 请求首页案例数据
         */
        Observable<BaseHttpResult<TemplateReadesignEntity>> requestDemandcaseData(Map<String, String> map);


        /**
         * 请求首页模板数据
         * @param map
         * @return
         */
        Observable<BaseHttpResult<TemplateReadesignEntity>> requestProductData(Map<String, String> map);


    }

}
