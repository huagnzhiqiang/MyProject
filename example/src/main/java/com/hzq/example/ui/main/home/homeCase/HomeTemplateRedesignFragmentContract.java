package com.hzq.example.ui.main.home.homeCase;

import com.hzq.baselibs.mvp.IModel;
import com.hzq.baselibs.mvp.IView;
import com.hzq.baselibs.net.BaseHttpResult;
import com.hzq.example.data.entity.TemplateReadesignEntity;

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
         * @param data 案例数据
         */
        void showCaseData(TemplateReadesignEntity data);
    }

    interface Model extends IModel {
        Observable<BaseHttpResult<TemplateReadesignEntity>> getCaseData(Map<String, String> map);
    }

}
