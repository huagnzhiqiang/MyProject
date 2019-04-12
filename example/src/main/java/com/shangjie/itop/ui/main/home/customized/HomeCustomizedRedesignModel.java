package com.shangjie.itop.ui.main.home.customized;

import com.shangjie.baselibs.mvp.BaseModel;
import com.shangjie.baselibs.net.BaseHttpResult;
import com.shangjie.itop.data.entity.HomeCustomizeEntity;
import com.shangjie.itop.data.repository.RetrofitUtils;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @author 小强
 * @time 2018/10/16  14:11
 * @desc
 */
public class HomeCustomizedRedesignModel extends BaseModel implements HomeCustomizedRedesignContract.Model {

    /**
     * 请求首页定制数据
     */
    @Override
    public Observable<BaseHttpResult<HomeCustomizeEntity>> requestCustomizedData(Map<String, String> map) {
        return RetrofitUtils.getHttpService().requestCustomizedData(map);
    }


}
