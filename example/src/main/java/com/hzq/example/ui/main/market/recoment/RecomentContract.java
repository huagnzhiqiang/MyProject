package com.hzq.example.ui.main.market.recoment;


import com.hzq.baselibs.mvp.IModel;
import com.hzq.baselibs.mvp.IView;
import com.hzq.baselibs.net.BaseHttpResult;
import com.hzq.example.data.entity.RecomentEntity;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @author 小强
 * @time 2018/10/27  15:56
 * @desc 自营销推荐页面契约类
 */
public interface RecomentContract {


    interface View extends IView {

        /**
         * 自营销推荐数据
         *
         * @param dataBean 自营销推荐数据
         */
        void showRecomentData(RecomentEntity dataBean);


        /**
         * 自营销推荐加载更多数据
         *
         * @param dataBean 自营销推荐加载更多数据
         */
        void showRecomentLoadMoreData(RecomentEntity dataBean);


        /**
         * 自营销推荐页面
         *
         * @param msg 加载更多错误信息
         */
        void showLoadMoreError(String msg);
    }


    interface Model extends IModel {
        /**
         * 自营销推荐
         *
         * @param map 请求参数
         */
        Observable<BaseHttpResult<RecomentEntity>> requestRecomentData(Map<String, String> map);
    }
}
