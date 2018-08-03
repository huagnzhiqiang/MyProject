package com.hzq.example.ui.main.home.homeCase;

import com.hzq.baselibs.mvp.BaseModel;
import com.hzq.baselibs.net.BaseHttpResult;
import com.hzq.example.data.entity.TemplateReadesignEntity;
import com.hzq.example.data.repository.RetrofitUtils;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @author 小强
 * @time 2018/8/2  16:49
 * @desc 首页案例/模板页面model
 */
public class HomeTemplateRedesignFragmentModel extends BaseModel implements HomeTemplateRedesignFragmentContract.Model{

    @Override
    public Observable<BaseHttpResult<TemplateReadesignEntity>> getCaseData(Map<String, String> map) {
        return RetrofitUtils.getHttpService().getDemandcaseData(map);
    }
}
