package com.example.mvpdemo.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

public class ActivityUtils {
    private static Stack<Activity> activityStack;//管理站
    //饿汉模式
    /* private static  final ActivityUtils activityUtils=new ActivityUtils();
   public static  ActivityUtils getInstance(){
        return  activityUtils;
    }*/
    //懒汉模式
    private  static  ActivityUtils activityUtils=null;
    public  static synchronized  ActivityUtils getInstance(){
        /**
         * 会存在多线程不安全事件，可能当线程一进来判断后未实例化切换到二线程发现为空实例化对象，
         *   此时一二线程都会实例化，需要同步锁
         */
        if (activityUtils==null){
            activityUtils=new ActivityUtils();
        }
        return activityUtils;
    }
    //添加Activity到栈中
    public void addActivity(Activity activity){
        if (activityStack==null){
         activityStack=new Stack<>();
        }
        activityStack.add(activity);

    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        if (activity != null) {
            activity.finish();
            activity = null;
        }
    }


    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }


    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }



    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }
    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
   }
    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }

}
