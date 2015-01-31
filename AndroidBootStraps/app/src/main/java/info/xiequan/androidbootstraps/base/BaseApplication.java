package info.xiequan.androidbootstraps.base;

import android.app.Application;
import android.os.Environment;
import android.os.Process;
import android.support.v4.app.FragmentActivity;

import com.baidu.mapapi.SDKInitializer;

import java.io.File;
import java.util.ArrayList;

import info.xiequan.androidbootstraps.util.AppConfig;
import info.xiequan.androidbootstraps.util.cache.CacheManager;
import info.xiequan.androidbootstraps.util.network.HttpFactory;

/**
 * Created by spark on 22/8/14.
 *
 * @author xiequan
 *         website: www.blueowls.net
 */
public class BaseApplication extends Application {
    private static final String TAG = BaseApplication.class.getName();
    private static BaseApplication baseApplication;
    private ArrayList<FragmentActivity> activities;

    public BaseApplication() {
        baseApplication = this;
    }

    public static BaseApplication getInstance() {
        return baseApplication;
    }

    private void registerModule() {
        AppConfig.register(this);
        HttpFactory.register(this);
        CacheManager.register(this);
    }

    /**
     * 应用启动
     */
    @Override
    public void onCreate() {
        super.onCreate();
        registerModule();
        Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler());
        File ext = Environment.getExternalStorageDirectory();
        File cacheDir = new File(ext, "app");
        activities = new ArrayList<FragmentActivity>();
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(this);
    }


    /**
     * 应用退出
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        onExit();
    }

    /**
     * 应用内存不足
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public void onExit() {

    }

    public void removeInstance(FragmentActivity instance) {
        activities.remove(instance);
    }

    public void addInstance(FragmentActivity instance) {
        activities.add(instance);
    }

    public void exit() {
        try {
            for (FragmentActivity activity : activities) {
                if (activity != null) {
                    activity.finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            android.os.Process.killProcess(Process.myPid());
            System.exit(1);
        }
    }

    private class MyExceptionHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            ex.printStackTrace();
            exit();
        }
    }
}
