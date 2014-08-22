package info.xiequan.androidbootstraps.util.volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import info.xiequan.androidbootstraps.base.BaseApplication;
import info.xiequan.androidbootstraps.constant.CacheTime;
import info.xiequan.androidbootstraps.constant.ConfigConstant;
import info.xiequan.androidbootstraps.constant.StringConstant;
import info.xiequan.androidbootstraps.exception.BaseException;
import info.xiequan.androidbootstraps.exception.HandleVolleyException;
import info.xiequan.androidbootstraps.exception.WebDataException;
import info.xiequan.androidbootstraps.interfaces.JsonCallBack;
import info.xiequan.androidbootstraps.interfaces.StringCallBack;
import info.xiequan.androidbootstraps.util.StringUtils;
import info.xiequan.androidbootstraps.util.cache.ACache;
import info.xiequan.androidbootstraps.util.network.NetworkUtils;

/**
 * Created by spark on 22/8/14.
 *
 * @author xiequan
 * website: www.blueowls.net
 */

public class VolleyUtil {
    private static final String TAG = VolleyUtil.class.getName();
    private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";
    private static final String DEFAULT_REQUEST_TAG = "VolleyRequest";

    private static VolleyUtil instance;
    private RequestQueue mRequestQueue;
    private DefaultRetryPolicy defaultRetryPolicy;
    private ACache aCache;

    private VolleyUtil(Context context) {
        //SSL 加密协议
//        InputStream keyStore =context.getResources().openRawResource();
//        mRequestQueue = Volley.newRequestQueue(context,new ExtHttpClientStack(new SslHttpClient(keyStore,"",443)));
        mRequestQueue = Volley.newRequestQueue(context);
        defaultRetryPolicy = new DefaultRetryPolicy(ConfigConstant.LOAD_TIMEOUT_MS, ConfigConstant.LOAD_MAX_RETRIES, ConfigConstant.LOAD_BACK_OFF);
        aCache = ACache.get(context);
        Log.e(TAG, "Volley init finish");

    }

    public static VolleyUtil getInstance() {
        if (instance == null) {
            instance = new VolleyUtil(BaseApplication.getInstance().getApplicationContext());
        }
        return instance;
    }

    private boolean checkNetwork() {
        Context context = BaseApplication.getInstance().getApplicationContext();
        if (!NetworkUtils.isNetworkAvailable(context)) {
            cancelAllRequest();
            return false;
        }
        return true;

    }

    /**
     * resolve map
     *
     * @param map
     * @return
     */
    private String parseMap(HashMap<String, String> map, String baseUrl) {
        final StringBuffer sb = new StringBuffer();
        if (map != null && map.size() > 0) {
            if (sb.indexOf("?") >= 0) {
                sb.append('&');
            } else {
                sb.append('?');
            }

            for (String key : map.keySet()) {
                try {
                    sb.append(URLEncoder.encode(key, DEFAULT_PARAMS_ENCODING));
                    sb.append('=');
                    sb.append(URLEncoder.encode(map.get(key), DEFAULT_PARAMS_ENCODING));
                    sb.append('&');
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return baseUrl;
                }
            }
            return baseUrl + sb.substring(0, sb.length() - 1);
        }
        return baseUrl;
    }

    /**
     * String GET METHOD
     *
     * @param url
     * @param map
     */
    public void doStringGet(final String url, HashMap<String, String> map, final StringCallBack callBack, final CacheTime cacheTime) {
        final String urlWithParams = parseMap(map, url);
        Log.e("GET_STRING", urlWithParams);

        //have cache or not
        if (findCacheString(urlWithParams, callBack, cacheTime)) {
            return;
        }

        if (!checkNetwork()) {
            callBack.error(new WebDataException(StringConstant.NETWORK_UNABLED));
            return;
        }

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("VolleyResponse", response);
                try {
                    callBack.handle(response);
                    //save cache
                    saveCacheWithJson(urlWithParams, response, cacheTime);
                } catch (BaseException e) {
                    callBack.error(e);
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VolleyError", "ERROR:" + error);
                callBack.error(new HandleVolleyException("VolleyError:" + error));
            }

        };
//        FakeX509TrustManager.allowAllSSL();
        StringRequest request = new StringRequest(Request.Method.GET, urlWithParams, responseListener, errorListener);
        request.setRetryPolicy(defaultRetryPolicy);
        request.setTag(DEFAULT_REQUEST_TAG);
        mRequestQueue.add(request);
    }

    private void saveCacheWithJson(final String key, final String value, CacheTime cacheTime) {
        if (cacheTime == null || cacheTime == CacheTime.NONE || StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return;
        }
        try {
            JSONObject jsonObject = new JSONObject(value);
            aCache.put(key, jsonObject, cacheTime.getTime());
        } catch (JSONException e) {
            aCache.put(key, value, cacheTime.getTime());
        }
    }


    /**
     * @param urlWithParams
     * @param callBack
     * @param cacheTime
     * @return true if found cache
     */
    private boolean findCacheString(final String urlWithParams, final StringCallBack callBack, final CacheTime cacheTime) {
        //handle cache
        if (cacheTime == null || cacheTime == CacheTime.NONE || StringUtils.isEmpty(urlWithParams)) {
            return false;
        }
        String cacheData = aCache.getAsString(urlWithParams);
        if (StringUtils.isEmpty(cacheData)) {
            return false;
        }
        try {
            Log.e("FoundCache", cacheData);
            callBack.handle(cacheData);
            return true;
        } catch (BaseException e) {
            callBack.error(e);
        }
        return false;
    }


    /**
     * JSON POST METHOD
     *
     * @param url
     * @param map
     * @param jsonObject
     * @param callBack
     */
    public void doJsonPost(final String url, HashMap<String, String> map, JSONObject jsonObject, final JsonCallBack callBack) {
        if (!checkNetwork()) {
            callBack.error(new WebDataException(StringConstant.NETWORK_UNABLED));
            return;
        }
        String urlWithParams = parseMap(map, url);
        Log.e("POST_JSON", urlWithParams);

        Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("VolleyResponse", response.toString());
                callBack.handle(response);
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VolleyError", "ERROR:" + error);
                callBack.error(new HandleVolleyException("VolleyError:" + error));
            }
        };

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlWithParams, jsonObject, responseListener, errorListener);

        jsonObjectRequest.setRetryPolicy(defaultRetryPolicy);
        jsonObjectRequest.setTag(DEFAULT_REQUEST_TAG);
        mRequestQueue.add(jsonObjectRequest);
    }


    /**
     * String GET METHOD
     *
     * @param url
     * @param map
     */
    public void doStringPost(final String url, final HashMap<String, String> map, final StringCallBack callBack) {
        Log.e("GET_STRING", url);

        if (!checkNetwork()) {
            callBack.error(new WebDataException(StringConstant.NETWORK_UNABLED));
            return;
        }

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("VolleyResponse", response);
                try {
                    callBack.handle(response);
                } catch (BaseException e) {
                    callBack.error(e);
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VolleyError", "ERROR:" + error);
                callBack.error(new HandleVolleyException("VolleyError:" + error));
            }

        };
//        FakeX509TrustManager.allowAllSSL();
        StringRequest request = new StringRequest(Request.Method.POST, url, responseListener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };
        request.setRetryPolicy(defaultRetryPolicy);
        request.setTag(DEFAULT_REQUEST_TAG);
        mRequestQueue.add(request);
    }


    public void cancelAllRequest() {
        mRequestQueue.cancelAll(DEFAULT_REQUEST_TAG);
    }


    /**
     * public save cache
     *
     * @param key
     * @param value
     * @param cacheTime
     */
    public void saveCache(final String key, final String value, CacheTime cacheTime) {
        if (cacheTime == null || cacheTime == CacheTime.NONE || StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return;
        }
        aCache.put(key, value, cacheTime.getTime());
    }


    public String getCache(final String key) {
        return aCache.getAsString(key);
    }
}
