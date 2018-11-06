package com.hzq.baselibs.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * @author 小强
 * @time 2018/11/5  16:27
 * @desc Activity管理类
 */
public class AppManager {

    private static Stack<Activity> activityStack;
    private static AppManager instance;

    private AppManager() {
    }

    public static AppManager get() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 从堆栈中移除该Activity
     */
    public void removeActivity(Activity activity) {
        activityStack.remove(activity);
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                activity.finish();
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (Activity activity : activityStack) {
            if (activity != null) {
                activity.finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 结束所有除目标外的Activity
     */
    public void finishActivitiesWithoutTarget(Class<?> cls) {
        Stack<Activity> tempActivityStack = new Stack<>();
        tempActivityStack.addAll(activityStack);
        for (Activity activity : tempActivityStack) {
            if (!activity.getClass().equals(cls)) {
                activity.finish();
            }
        }
        tempActivityStack.clear();
    }


    public void exit() {
        finishAllActivity();
        System.exit(0);
        System.gc();
    }

}
