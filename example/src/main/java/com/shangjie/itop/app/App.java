package com.shangjie.itop.app;

import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;

import com.mob.MobSDK;
import com.orhanobut.logger.Logger;
import com.shangjie.baselibs.app.BaseApplication;
import com.shangjie.baselibs.utils.CommonUtils;
import com.shangjie.itop.data.Login.LoginMsgHelper;
import com.shangjie.itop.service.AlarmssService;

/**
 * @author 小强
 * @date 2018/7/10 16:20
 * @desc Application
 */
public class App extends BaseApplication {


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Bugly
        initBugly();

        //向服务器发起心跳包 去续token
        initeartbeatH();

        MobSDK.init(this);
    }


    private void initBugly() {
        // 获取当前包名
        String packageName = getApplicationContext().getPackageName();
        // 获取当前进程名
        String processName = CommonUtils.getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        //        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());
        //        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        //        CrashReport.initCrashReport(getApplicationContext(), MyConstants.BUGLY_ID, false, strategy);
    }

    private void initeartbeatH() {
        Logger.i("initeartbeatH--->:" );
        if (LoginMsgHelper.getResult() != null) {
            Logger.i("启动服务--->:" );
            Intent intent = new Intent(this, AlarmssService.class);
            startService(intent);
        }
    }
}
