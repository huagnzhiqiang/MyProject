package com.shangjie.itop.ui.main.home.homeCase;

import com.shangjie.baselibs.mvp.BaseModel;
import com.shangjie.baselibs.net.BaseHttpResult;
import com.shangjie.itop.data.entity.TemplateReadesignEntity;
import com.shangjie.itop.data.repository.RetrofitUtils;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @author 小强
 * @time 2018/8/2  16:49
 * @desc 首页案例/模板页面model
 */
public class HomeTemplateRedesignFragmentModel extends BaseModel implements HomeTemplateRedesignFragmentContract.Model{

    /**
     * 请求获取案例分页数据/案例更多数据
     */
    @Override
    public Observable<BaseHttpResult<TemplateReadesignEntity>> requestDemandcaseData(Map<String, String> map) {
        return RetrofitUtils.getHttpService().requestDemandcaseData(map);
//        return RetrofitUtils.getHttpService().requestDemandcaseData(map,new DynamicKey("getpagelist"), new EvictDynamicKey(true));
    }


    /**
     * 获取首页模板分页/模板更多数据
     */
    @Override
    public Observable<BaseHttpResult<TemplateReadesignEntity>> requestProductData(Map<String, String> map) {
        return RetrofitUtils.getHttpService().requestProductData(map);
    }

}
