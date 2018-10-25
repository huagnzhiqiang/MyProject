package com.hzq.example.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @author 小强
 * @time 2018/10/24  16:39
 * @desc 定时开启一个广播去开启服务去请求后台Token
 */
public class AlarmssReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, AlarmssService.class);
        context.startService(i);
    }
}
