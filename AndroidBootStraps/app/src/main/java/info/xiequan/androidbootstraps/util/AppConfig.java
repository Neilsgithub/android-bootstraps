package info.xiequan.androidbootstraps.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import info.xiequan.androidbootstraps.base.BaseApplication;
import info.xiequan.androidbootstraps.exception.NotRegistionContextException;


/**
 * volley String callback
 * Created by spark on 23/8/14.
 * www.blueowls.net
 * i@xiequan.info
 */
public class AppConfig {
    private static final String TAG = AppConfig.class.getName();

    /**
     * 上午开始的时间
     */
    public static int MORNING = 0;
    /**
     * 下午开始的时间
     */
    public static int NOON = 12;
    /**
     * 晚上开始的时间
     */
    public static int NIGHT = 18;

    public static final boolean IS_DEBUG = true;
    private static BaseApplication context = null;


    public static void register(BaseApplication context) {
        AppConfig.context = context;
    }

    /**
     * 写入配置信息，需要最后面进行 commit()
     *
     * @param key
     * @param value
     * @return
     */
    public static void putString(String key, String value) {
        SharedPreferences sharedPref = getSharedPreferences();
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 读取配置信息
     *
     * @param key
     * @return
     */
    public static String getString(String key) {
        return getSharedPreferences().getString(key, null);
    }

    /**
     * 清除配置信息
     */
    public static void clearString(String key) {
        SharedPreferences sharedPref = getSharedPreferences();
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.commit();
    }

    /**
     * 获取Preference设置
     */
    public static SharedPreferences getSharedPreferences() {
        if (AppConfig.context == null) {
            try {
                throw new NotRegistionContextException("Config error!");
            } catch (NotRegistionContextException e) {
                Log.e(TAG, "配置类没有注册上AppContext(下文环境)", e);
            }
        }
        return PreferenceManager.getDefaultSharedPreferences(AppConfig.context);
    }

}
