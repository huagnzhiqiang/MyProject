package com.hzq.example.ui.main.myWork.contract;

import com.hzq.baselibs.mvp.IModel;
import com.hzq.baselibs.mvp.IView;
import com.hzq.baselibs.net.BaseHttpResult;
import com.hzq.example.data.entity.MyProductEntity;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @author 小强
 * @time 2018/11/2  11:28
 * @desc 我的作品契约类
 */
public interface MyWorkContract {

    interface View extends IView {

        /**
         * 我的作品数据
         *
         * @param dataBean 我的作品数据
         */
        void showMyWorkData(MyProductEntity dataBean);

        /**
         * 我的作品更多数据
         *
         * @param dataBean 我的作品更多数据
         */
        void showMyWorkLoadMoreData(MyProductEntity dataBean);


        /**
         * 我的作品加载更多错误信息
         *
         * @param msg 加载更多错误信息
         */
        void showLoadMoreError(String msg);
    }

    interface Model extends IModel {

        /**
         * 我的作品
         *
         * @param map 请求参数
         */
        Observable<BaseHttpResult<MyProductEntity>> requestMyWorktData(Map<String, String> map);
    }
}
