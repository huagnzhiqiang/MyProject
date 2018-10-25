package com.hzq.example.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.hzq.baselibs.net.BaseHttpResult;
import com.hzq.baselibs.net.BaseObserver;
import com.hzq.baselibs.utils.SpUtil;
import com.hzq.example.constants.SpKeyConstant;
import com.hzq.example.data.entity.LoginEntity;
import com.hzq.example.data.repository.RetrofitUtils;
import com.orhanobut.logger.Logger;

/**
 * @author 小强
 * @time 2018/10/24  11:17
 * @desc 心跳包服务 续Token 表示一直在线
 */
public class AlarmssService extends Service {

    private AlarmManager mManager;
    private PendingIntent mPi;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String token = SpUtil.getInstance().getString(SpKeyConstant.TOKEN);
        long anHour = 10 * 60 * 1000;//根据实际来 我这是根据自己服务器时间进行的 1小时

        mManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;//每隔多久请求一次 lo是时间 单位毫秒
        Intent i = new Intent(this, AlarmssReceiver.class);//开启广播
        mPi = PendingIntent.getBroadcast(this, 0, i, 0);
        mManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, mPi);//启动广播

        Logger.i("onStartCommand: 开始请求网络获取Token-->" + token);

        if (!TextUtils.isEmpty(token)) {
            //这里放请求网络的逻辑 请求很频繁 需要在子线线程调动
            new Thread(new Runnable() {
                @Override
                public void run() {
                    requestToken(token);
                }
            }).start();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void requestToken(String token) {

        RetrofitUtils.getHttpService().getTokenData(token).subscribe(new BaseObserver<LoginEntity>() {
            /**
             * 请求成功返回
             *
             * @param result 服务器返回数据
             */
            @Override
            public void onSuccess(BaseHttpResult<LoginEntity> result) {
                //保存Token 续Token
                SpUtil.getInstance().putString(SpKeyConstant.TOKEN,result.getData().getToken());
            }

            /**
             * 请求失败返回
             *
             * @param errMsg     失败信息
             * @param isNetError 是否是网络异常
             */
            @Override
            public void onFailure(String errMsg, int errCode, boolean isNetError) {
            }
        });

    }


    @Override
    public void onDestroy() {
        Logger.i("关闭广播注册者");
        mManager.cancel(mPi);//关闭的服务的时候同时关闭广播注册者
        super.onDestroy();
    }

}
