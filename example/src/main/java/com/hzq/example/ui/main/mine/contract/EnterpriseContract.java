package com.hzq.example.ui.main.mine.contract;

import com.hzq.baselibs.mvp.IModel;
import com.hzq.baselibs.mvp.IView;

/**
 * @author 小强
 * @time 2018/11/7  9:48
 * @desc 企业信息修改页面契约类
 */
public interface EnterpriseContract {


    interface View extends IView {

        /**
         *
         */
        void showEnterpriseData();
    }

    interface Model extends IModel {

        /**
         *
         */
        void showEnterpriseData();
    }
}
