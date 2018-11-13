package com.hzq.example.data.api;

import com.hzq.baselibs.net.BaseHttpResult;
import com.hzq.example.data.entity.HomeCustomizeEntity;
import com.hzq.example.data.entity.HomeDesignerEntity;
import com.hzq.example.data.entity.LoginEntity;
import com.hzq.example.data.entity.MineEntity;
import com.hzq.example.data.entity.MyProductEntity;
import com.hzq.example.data.entity.RecomentEntity;
import com.hzq.example.data.entity.TemplateReadesignEntity;
import com.hzq.example.data.entity.TestNews;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.LifeCache;
import retrofit2.http.Field;
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
    @LifeCache(duration = 20, timeUnit = TimeUnit.MINUTES)
    Observable<BaseHttpResult<List<TestNews>>> getGankData();

    @POST("/api/order/getuserlist")
    Observable<BaseHttpResult<MineEntity>> getMineData();

    //登录
    @FormUrlEncoded
    @POST("/api/user/login")
    @LifeCache(duration = 20, timeUnit = TimeUnit.MINUTES)
    Observable<BaseHttpResult<LoginEntity>> requestLoginData(@FieldMap Map<String, String> map);

    //获取案例分页
    @FormUrlEncoded
    @LifeCache(duration = 20, timeUnit = TimeUnit.MINUTES)
    @POST("/api/demandcase/getpagelist")
    Observable<BaseHttpResult<TemplateReadesignEntity>> requestDemandcaseData(@FieldMap Map<String, String> map);

    //获取作品分页
    @FormUrlEncoded
    @POST("/api/product/getpagelist")
    Observable<BaseHttpResult<TemplateReadesignEntity>> requestProductData(@FieldMap Map<String, String> map);

    //获取定制分页
    @FormUrlEncoded
    @POST("/api/demand/getpagelist")
    Observable<BaseHttpResult<HomeCustomizeEntity>> requestCustomizedData(@FieldMap Map<String, String> map);

    //获取设计师分页
    @FormUrlEncoded
    @POST("/api/userdesigner/getpagelist")
    Observable<BaseHttpResult<HomeDesignerEntity>> requestHomeDesignerData(@FieldMap Map<String, String> map);

    //获取关注设计师
    @FormUrlEncoded
    @POST("/api/userfollow/follow")
    Observable<BaseHttpResult<String>> requestFollowDesignersData(@Field("id") int id);

    //获取关注设计师
    @FormUrlEncoded
    @POST("/api/userfollow/unfollow")
    Observable<BaseHttpResult<String>> requestUnFollowDesignersData(@Field("id") int id);

    //获取Toke信息
    @FormUrlEncoded
    @POST("/api/user/getuserstatus")
    Observable<BaseHttpResult<LoginEntity>> requestTokenData(@Field("token") String token);

    //获取自营销数据
    @FormUrlEncoded
    @POST("/api/article/getpagelist")
    Observable<BaseHttpResult<RecomentEntity>> requestRecomentData(@FieldMap Map<String, String> map);

    //获取我的作品数据
    @FormUrlEncoded
    @POST("/api/product/getuserproduct")
    Observable<BaseHttpResult<MyProductEntity>> requestMyWorktData(@FieldMap Map<String, String> map);

    //上传图片
    @FormUrlEncoded
    @POST("/api/common/uploadbase64")
    Observable<BaseHttpResult<String>> requistUpImager(@FieldMap Map<String, String> map);
}
