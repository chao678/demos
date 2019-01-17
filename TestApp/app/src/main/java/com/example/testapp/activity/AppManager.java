package com.example.testapp.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * Created by zgy
 * on 2016/11/20.
 * activity栈管理类
 */
public class AppManager {

	private static Stack<Activity> mStack;

	private static AppManager mInstance;

	/**
	 * 单一实例
	 * 
	 * @return AppManager 返回实例
	 */
	public static AppManager getAppManager() {
		if (mInstance == null)
			mInstance = new AppManager();
		return mInstance;
	}

	/**
	 * 添加activity到堆栈
	 * 
	 * @param activity
	 */
	public void addActivity(Activity activity) {
		if (mStack == null)
			mStack = new Stack<Activity>();
		mStack.add(activity);
	}

	/**
	 * 获取当前activity(堆栈中最后一个压入的)
	 * 
	 * @return 返回当前activity
	 */
	public Activity currentActivity() {
		Activity activity = mStack.lastElement();
		return activity;
	}

	/**
	 * 结束当前activity(堆栈中最后一个压入的)
	 */
	public void finishActivity() {
		Activity activity = mStack.lastElement();
		finishActivity(activity);
	}

	/**
	 * 结束指定activity
	 * 
	 * @param activity
	 *            指定的activity
	 */
	public void finishActivity(Activity activity) {
		if (activity != null) {
			mStack.remove(activity);
			activity.finish();
			activity = null;
		}
	}

	/**
	 * 结束指定类名的Activity
	 * 
	 * @param cls
	 *            <?> 指定结束的cls
	 */
	public void finishActivity(Class<?> cls) {

		for (Activity activity : mStack) {
			if (activity.getClass().equals(cls)) {
				finishActivity(activity);
			}
		}
	}

	/**
	 * 结束所有activity
	 */
	public void finishAllActivity() {
		for (int i = 0; i < mStack.size(); i++) {
			if (null != mStack.get(i)) {
				mStack.get(i).finish();
			}
		}
		mStack.clear();
	}

	/**
	 * 退出应用程序
	 * 
	 * @param context
	 *            上下文
	 */
	@SuppressWarnings("deprecation")
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
