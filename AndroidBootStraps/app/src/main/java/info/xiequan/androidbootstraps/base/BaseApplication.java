package info.xiequan.androidbootstraps.base;

import android.app.Application;
import android.os.Environment;
import android.os.Process;
import android.support.v4.app.FragmentActivity;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by spark on 22/8/14.
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


    /**
     * 应用启动
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler());
        File ext = Environment.getExternalStorageDirectory();
        File cacheDir = new File(ext, "app");
        activities = new ArrayList<FragmentActivity>();
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
