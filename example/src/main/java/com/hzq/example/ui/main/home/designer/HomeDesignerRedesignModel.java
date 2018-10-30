package com.hzq.example.ui.main.home.designer;

import com.hzq.baselibs.mvp.BaseModel;
import com.hzq.baselibs.net.BaseHttpResult;
import com.hzq.example.data.entity.HomeDesignerEntity;
import com.hzq.example.data.repository.RetrofitUtils;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @author 小强
 * @time 2018/10/18  14:17
 * @desc 首页设计师页面Model
 */
public class HomeDesignerRedesignModel  extends BaseModel implements HomeDesignerRedesignContract.Model{

    /**
     * 请求首页设计师数据
     */
    @Override
    public Observable<BaseHttpResult<HomeDesignerEntity>> requestHomeDesignerData(Map<String, String> map) {
        return RetrofitUtils.getHttpService().requestHomeDesignerData(map);
    }


    /**
     * 请求首页关注设计师
     */
    @Override
    public Observable<BaseHttpResult<String>> requestFollowDesignersData(int id) {
        return RetrofitUtils.getHttpService().requestFollowDesignersData(id);
    }

    /**
     * 请求首页取消关注设计师
     */
    @Override
    public Observable<BaseHttpResult<String>> requestUnFollowDesignersData(int id) {
        return  RetrofitUtils.getHttpService().requestUnFollowDesignersData(id);
    }

}
