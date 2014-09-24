package info.xiequan.androidbootstraps.util.network;


import android.text.TextUtils;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

import io.ganguo.huoyun.util.cache.Cache;
import io.ganguo.huoyun.util.common.StringUtils;


/**
 * Created by spark on 15/9/14.
 * www.blueowls.net
 * i@xiequan.info
 */
public abstract class AbstractHttpService implements HttpService {
    private static final String TAG = AbstractHttpService.class.getName();
    private Map<String, String> headers = new HashMap<String, String>();
    private Map<String, Stack<HttpListener>> currentQequests;
    private Cache mCache;


    public AbstractHttpService() {
        currentQequests = new ConcurrentHashMap<String, Stack<HttpListener>>();
    }
    // Instruct the library to retry connection when this exception is raised.
    static {
        AsyncHttpClient.allowRetryExceptionClass(javax.net.ssl.SSLException.class);
    }

    /**
     * 获取请求头部信息
     *
     * @return
     */
    public Map<String, String> getHeadersMap() {
        return headers;
    }

    @Override
    public void addHeader(String key, String value) {
        headers.put(key, value);
    }


    /**
     * 缓存响应
     * 存在 true
     *
     * @param url
     * @param httpListener
     * @return
     */
    public boolean fireCache(String url, HttpListener<?> httpListener) {
        String cacheContent = mCache.getString(url);
        if (!TextUtils.isEmpty(cacheContent)) {
            httpListener.handleResponse(cacheContent);
            Log.d(TAG, "FoundCache: " + cacheContent);
            return true;
        }
        return false;
    }

    /**
     * 放到缓存中
     *
     * @param key
     * @param value
     * @param cacheTime
     */
    public void putCache(String key, String value, int cacheTime) {
        if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value) && cacheTime > 0) {
            mCache.put(key, value, cacheTime);
        }
    }

    /**
     * 添加一个请求，判断重复请求
     *
     * @param url
     * @param httpListener
     * @return
     */
    public boolean addQequest(String url, HttpListener<?> httpListener) {
        Log.d(TAG, "request: " + url);
        Stack<HttpListener> httpListeners = currentQequests.get(url);
        if (httpListeners == null) {
            httpListeners = new Stack<HttpListener>();
            currentQequests.put(url, httpListeners);
            httpListeners.push(httpListener);
            return false;
        }
        httpListeners.push(httpListener);
        return true;
    }

    /**
     * 响应一个请求的返回
     *
     * @param url
     * @param response
     * @param cacheTime
     */
    public void sendSuccessResponse(String url, String response, int cacheTime) {
        Stack<HttpListener> httpListeners = currentQequests.get(url);
        Log.d(TAG, httpListeners.size() + " response: " + response);
        if (httpListeners != null) {
            while (!httpListeners.isEmpty()) {
                httpListeners.pop().handleResponse(response);
            }
            currentQequests.remove(url);
        }
        // 放到缓存中
        putCache(url, response, cacheTime);
    }

    /**
     * 响应一个请求错误
     *
     * @param url
     * @param error
     * @param e
     */
    public void sendFailureResponse(String url, String error, Throwable e) {
        Stack<HttpListener> httpListeners = currentQequests.get(url);
        Log.d(TAG, httpListeners.size() + " error: " + error, e);
        if (httpListeners != null) {
            while (!httpListeners.isEmpty()) {
                HttpListener listener = httpListeners.pop();
                listener.onFailure(error, e);
                listener.onFinish();
            }
            currentQequests.remove(url);
        }
    }

    /**
     * Build URL & Parms
     *
     * @param baseUrl
     * @param parms
     * @return
     */
    public String buildUrlParams(String baseUrl, Map<String, String> parms) {
        if (parms != null && parms.size() > 0) {
            final StringBuffer sb = new StringBuffer();
            if (sb.indexOf("?") >= 0) {
                sb.append('&');
            } else {
                sb.append('?');
            }
            for (String key : parms.keySet()) {
                try {
                    sb.append(URLEncoder.encode(key, HttpConstants.DEFAULT_PARAMS_ENCODING));
                    sb.append('=');
                    sb.append(URLEncoder.encode(parms.get(key), HttpConstants.DEFAULT_PARAMS_ENCODING));
                    sb.append('&');
                } catch (UnsupportedEncodingException e) {
                    Log.e(TAG, "URLEncoder.encode ERROR", e);
                    return baseUrl;
                }
            }
            return baseUrl + sb.substring(0, sb.length() - 1);
        }
        return baseUrl;
    }

}
