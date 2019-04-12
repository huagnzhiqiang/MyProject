package com.shangjie.baselibs.net.interceptor;

import com.shangjie.baselibs.app.BaseApplication;
import com.shangjie.baselibs.utils.NetworkUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CaheInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetworkUtils.isNetworkAvailable(BaseApplication.getContext())) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        Response originalResponse = chain.proceed(request);
        if (NetworkUtils.isNetworkAvailable(BaseApplication.getContext())) {
            //有网的时候也可以读接口上的@Headers里的配置，你可以在这里进行统一的设置
            int maxAge = 0 * 60;
            // 有网络时 设置缓存超时时间0个小时
            return originalResponse.newBuilder()
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, noCache,max-age=" + maxAge)

                    .removeHeader("Pragma")
                    .build();
        } else {
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                    .removeHeader("Pragma")
                    .build();
        }
    }


//    @Override
//    public Response intercept(Chain chain) throws IOException {
//        Request request = chain.request();
//        if (NetworkUtils.isNetworkAvailable(BaseApplication.getContext())) {
//            Response response = chain.proceed(request);
//            Logger.d("有网络数据--->:" );
//            // read from cache for 60 s
//            int maxAge = 60;
//            return response.newBuilder()
////                    .removeHeader("Cache-Control")
//                    .removeHeader("Pragma")
//                    .header("Cache-Control", "public, max-age=" + maxAge)
//                    .build();
//        } else {
//            Logger.d("当前无网络! 为你智能加载缓存--->:" );
//
////            ToastUtils.showShort("当前无网络! 为你智能加载缓存");
//            request = request.newBuilder()
//                    .cacheControl(CacheControl.FORCE_CACHE)
//                    .build();
//            Response response = chain.proceed(request);
//            //set cahe times is 3 days
//            int maxStale = 60 * 60 * 24 * 3;
//            return response.newBuilder()
////                    .removeHeader("Cache-Control")
//                    .removeHeader("Pragma")
//                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
//                    .build();
//        }
//    }
//

}