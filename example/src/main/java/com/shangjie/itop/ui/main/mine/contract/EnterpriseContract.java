package com.shangjie.itop.ui.main.mine.contract;

import com.shangjie.baselibs.mvp.IModel;
import com.shangjie.baselibs.mvp.IView;
import com.shangjie.baselibs.net.BaseHttpResult;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @author 小强
 * @time 2018/11/7  9:48
 * @desc 企业信息修改页面契约类
 */
public interface EnterpriseContract {


    interface View extends IView {
        /**
         * 上传图片返回数据
         * <p>
         * result 返回结果
         */
        void showUpImagerData(BaseHttpResult<String> result);
    }

    interface Model extends IModel {

        /**
         * 上传图片
         *
         * @param map 请求参数
         */
        Observable<BaseHttpResult<String>> requistUpImager(Map<String, String> map);
    }
}
