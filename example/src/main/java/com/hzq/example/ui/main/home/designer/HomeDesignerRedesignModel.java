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
    public Observable<BaseHttpResult<HomeDesignerEntity>> getHomeDesignerData(Map<String, String> map) {
        return RetrofitUtils.getHttpService().getHomeDesignerData(map);
    }


    /**
     * 请求首页关注设计师
     */
    @Override
    public Observable<BaseHttpResult<String>> getFollowDesignersData(int id) {
        return RetrofitUtils.getHttpService().getFollowDesignersData(id);
    }

    /**
     * 请求首页取消关注设计师
     */
    @Override
    public Observable<BaseHttpResult<String>> getUnFollowDesignersData(int id) {
        return  RetrofitUtils.getHttpService().getUnFollowDesignersData(id);
    }

}
