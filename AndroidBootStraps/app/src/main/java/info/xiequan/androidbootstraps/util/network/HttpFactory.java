package info.xiequan.androidbootstraps.util.network;

import android.util.Log;

import info.xiequan.androidbootstraps.base.BaseApplication;
import info.xiequan.androidbootstraps.exception.NotRegistionContextException;


/**
 * Created by spark on 15/9/14.
 * www.blueowls.net
 * i@xiequan.info
 */
public class HttpFactory {
    private static final String TAG = HttpFactory.class.getName();

    private static BaseApplication context = null;
    private static HttpService httpService = null;

    public static void register(BaseApplication context) {
        HttpFactory.context = context;
    }

    public static void checkContext() {
        if (context == null) {
            try {
                throw new NotRegistionContextException("Config error!");
            } catch (NotRegistionContextException e) {
                Log.e(TAG, "配置类没有注册上AppContext(下文环境)", e);
            }
        }
    }

    public static HttpService getHttpService() {
        checkContext();
        if (httpService == null) {
            // 通过Volley进行实现
            httpService = new AsyncHttpImpl(context);
        }
        return httpService;
    }

    public static HttpService newHttpService() {
        checkContext();
        return new VolleyImpl(context);
    }

}
