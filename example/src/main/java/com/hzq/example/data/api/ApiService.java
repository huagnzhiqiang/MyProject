package com.hzq.example.data.api;

import com.hzq.example.data.entity.LoginEntity;
import com.hzq.example.data.entity.MineEntity;
import com.hzq.example.data.entity.TestNews;
import com.hzq.baselibs.net.BaseHttpResult;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @author 小强
 * @date 2018/6/11 23:04
 * @desc 接口管理
 */
public interface ApiService {

    @GET("api/data/Android/10/1")
    Observable<BaseHttpResult<List<TestNews>>> getGankData();

    @POST("/api/order/getuserlist")
    Observable<BaseHttpResult<MineEntity>> getMineData();

    //登录
    @FormUrlEncoded
    @POST("/api/user/login")
    Observable<BaseHttpResult<LoginEntity>> getLoginData(@FieldMap Map<String,String> map);

}
