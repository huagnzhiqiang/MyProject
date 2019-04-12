package com.shangjie.baselibs.net.logcat;

import android.text.TextUtils;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URLDecoder;
import java.nio.charset.Charset;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * @author 小强
 * @time 2018/10/31  10:01
 * @desc 日志打印
 */
public class LoggerInterceptor implements Interceptor {


//    public static String TAG = "LogInterceptor";
//
//    @Override
//    public okhttp3.Response intercept(Chain chain) throws IOException {
//        Request request = chain.request();
//        long startTime = System.currentTimeMillis();
//        okhttp3.Response response = chain.proceed(chain.request());
//        long endTime = System.currentTimeMillis();
//        long duration=endTime-startTime;
//        okhttp3.MediaType mediaType = response.body().contentType();
//        String content = response.body().string();
//        Log.d(TAG,"\n");
//        Log.d(TAG,"----------Start----------------");
//        Log.d(TAG, "| "+request.toString());
//        String method=request.method();
//        if("POST".equals(method)){
//            StringBuilder sb = new StringBuilder();
//            if (request.body() instanceof FormBody) {
//                FormBody body = (FormBody) request.body();
//                for (int i = 0; i < body.size(); i++) {
//                    sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
//                }
//                sb.delete(sb.length() - 1, sb.length());
//                Log.d(TAG, "| RequestParams:{"+sb.toString()+"}");
//            }
//        }
//        Log.d(TAG, "| Response:" + content);
//        Log.d(TAG,"----------End:"+duration+"毫秒----------");
//        return response.newBuilder()
//                .body(okhttp3.ResponseBody.create(mediaType, content))
//                .build();
//    }




    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request().newBuilder().build();

        printRequestLog(originalRequest);
        Response response = null;
        try {
            //发送网络请求
            response = chain.proceed(originalRequest);
            printResult(response);
        } catch (SocketTimeoutException e) {
            //此处不抛异常  连接超时会crash 没有找到其他好的方法
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 打印请求日志
     */
    private void printRequestLog(Request originalRequest) throws IOException {
        FormBody.Builder formBuilder = new FormBody.Builder();
        String msg = originalRequest.url() + "\n";
        RequestBody oidBody = originalRequest.body();
        if (oidBody instanceof FormBody) {
            FormBody formBody = (FormBody) oidBody;
            for (int i = 0; i < formBody.size(); i++) {
                String name = URLDecoder.decode(formBody.encodedName(i), "utf-8");
                String value = URLDecoder.decode(formBody.encodedValue(i), "utf-8");
                if (!TextUtils.isEmpty(value)) {
                    formBuilder.add(name, value);
                    msg += name + "  =  " + value + "\n";
                }
            }
        }
        Logger.i(msg);

    }

    /**
     * 打印返回日志
     */
    private void printResult(Response response) throws IOException {
        ResponseBody responseBody = response.body();
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE);
        Buffer buffer = source.buffer();
        Charset UTF8 = Charset.forName("UTF-8");
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            UTF8 = contentType.charset(UTF8);
        }
        String json = buffer.clone().readString(UTF8);

        Logger.i(json);

    }

}