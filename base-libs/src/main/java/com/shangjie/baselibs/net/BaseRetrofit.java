package com.shangjie.baselibs.net;

import com.orhanobut.logger.Logger;
import com.shangjie.baselibs.app.AppConfig;
import com.shangjie.baselibs.app.BaseApplication;
import com.shangjie.baselibs.net.converter.GsonConverterBodyFactory;
import com.shangjie.baselibs.net.cookie.CookieJarImpl;
import com.shangjie.baselibs.net.cookie.store.PersistentCookieStore;
import com.shangjie.baselibs.net.logcat.LoggerInterceptor;
import com.shangjie.baselibs.utils.NetworkUtils;
import com.shangjie.baselibs.utils.cache.CacheManager;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author 小强
 * @time 2018/6/12 13:15
 * @desc 网络配置类
 */

public class BaseRetrofit {
    private static OkHttpClient client;
    private static volatile Retrofit retrofit;

    /**
     * 配置网络请求头
     */
    public static HashMap<String, Object> getRequestHeader() {
        HashMap<String, Object> parameters = new HashMap<>();
        // 为接口统一添加access_token参数
        CacheManager.getString("token");
//        parameters.put("token", SpUtil.getInstance().getString("token"));
        parameters.put("token", "j0Ikmd6RJxgPCpiYF4BNnsDK5zS9arft78WTXUVelybuvQchEL23oHAOGZqwM1");
//        Logger.d("getRequestHeader--->:" + SpUtil.getInstance().getString("token"));
        Logger.d("getRequestHeader--->:" + parameters);
        return parameters;
    }

    /**
     * 配置网络请求公共参数
     */
    public static HashMap<String, Object> getRequestParams() {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("_appversion", "");
        parameters.put("_systemtype", "");
        parameters.put("_systemversion", "");
        parameters.put("_deviceid", "");
        parameters.put("_memberid", "");
        return parameters;
    }

    public static Retrofit getRetrofit() {

        if (retrofit == null) {
            synchronized (BaseRetrofit.class) {
                if (retrofit == null) {

                    //添加一个log拦截器,打印所有的log
                    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                        @Override
                        public void log(String message) {
                            Logger.e("请求网络--->:" + message);
                        }
                    });

                    //可以设置请求过滤的水平,body,basic,headers
                    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);



                    //设置 请求的缓存的大小跟位置
                    File cacheFile = new File(BaseApplication.getContext().getCacheDir(), "hzq_cache");
                    //                    File cacheFile = Environment.getExternalStorageDirectory();
                    Cache cache = new Cache(cacheFile, 1024 * 1024 * 50); //50Mb 缓存的大小

                    client = new OkHttpClient.Builder().cookieJar(new CookieJarImpl(new PersistentCookieStore(BaseApplication.getContext()))) //cookie 相关
                            .addInterceptor(httpLoggingInterceptor) //日志,所有的请求响应
                            .addInterceptor(new LoggerInterceptor()) //日志,所有的请求响应
//                                                       .addInterceptor(new HeaderInterceptor(getRequestHeader())) // 配置网络请求头
                            //                            .addInterceptor(new ParameterInterceptor(getRequestParams()))  //公共参数添加
                            //不加以下两行代码,https请求不到自签名的服务器
                            .sslSocketFactory(createSSLSocketFactory())//创建一个证书对象
                            .hostnameVerifier(new TrustAllHostnameVerifier())//校验名称,这个对象就是信任所有的主机,也就是信任所有https的请求
                            //                            //有网络时的拦截器
                            //                            .addNetworkInterceptor(BaseRetrofit.REWRITE_RESPONSE_INTERCEPTOR)
                            //                            //没网络时的拦截器
                            //                            .addInterceptor(BaseRetrofit.REWRITE_RESPONSE_INTERCEPTOR_OFFLINE).

                            .cache(cache)  //添加缓存
                            .connectTimeout(60L, TimeUnit.SECONDS)//连接超时时间
                            .readTimeout(60L, TimeUnit.SECONDS)//读取超时时间
                            .writeTimeout(60L, TimeUnit.SECONDS)//写入超时时间
                            .retryOnConnectionFailure(true)//连接不上是否重连,false不重连
                            .build();


                    // 获取retrofit的实例
                    retrofit = new Retrofit.Builder().baseUrl(AppConfig.BASE_URL)  //baseUrl配置
                            .client(client).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                                    addConverterFactory(GsonConverterBodyFactory.create()).
                                    addConverterFactory(GsonConverterFactory.create()).build();
                    //
                    //
                    //                    Retrofit build1 = new Retrofit.Builder().client(client).baseUrl(AppConfig.BASE_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    //                            .addConverterFactory(GsonConverterFactory.create()).build();
                    //                    LoginEntity rJiekou = build1.create(LoginEntity.class);
                    //                    Observable<Bens> strig = rJiekou.getChannelList();//Rxjava链式调用并把我们的Okhttp的拦截器和build的对象放进去
                }
            }
        }
        return retrofit;
    }

    /**
     * 实现https请求
     */
    private static SSLSocketFactory createSSLSocketFactory() {


        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }

    private static class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    /**
     * 信任所有的服务器,返回true
     */
    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }


    private static final int TIMEOUT_CONNECT = 5; //5秒
    private static final int TIMEOUT_DISCONNECT = 60 * 60 * 24 * 7; //7天

    public static final Interceptor REWRITE_RESPONSE_INTERCEPTOR = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            //获取retrofit @headers里面的参数，参数可以自己定义，在本例我自己定义的是cache，跟@headers里面对应就可以了
            String cache = chain.request().header("cache");
            okhttp3.Response originalResponse = chain.proceed(chain.request());
            String cacheControl = originalResponse.header("Cache-Control");
            //如果cacheControl为空，就让他TIMEOUT_CONNECT秒的缓存，本例是5秒，方便观察。注意这里的cacheControl是服务器返回的
            if (cacheControl == null) {
                //如果cache没值，缓存时间为TIMEOUT_CONNECT，有的话就为cache的值
                if (cache == null || "".equals(cache)) {
                    cache = TIMEOUT_CONNECT + "";
                }
                originalResponse = originalResponse.newBuilder().header("Cache-Control", "public, max-age=" + cache).build();
                return originalResponse;
            } else {
                return originalResponse;
            }
        }
    };

    public static final Interceptor REWRITE_RESPONSE_INTERCEPTOR_OFFLINE = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            //离线的时候为7天的缓存。
            if (!NetworkUtils.isNetworkAvailable(BaseApplication.getContext())) {
                request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + TIMEOUT_DISCONNECT).build();
            }
            return chain.proceed(request);
        }
    };
}
