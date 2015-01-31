package info.xiequan.androidbootstraps.util.network;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

import info.xiequan.androidbootstraps.util.cache.CacheTime;


/**
 * Created by spark on 15/9/14.
 * www.blueowls.net
 * i@xiequan.info
 */
public class AsyncHttpImpl extends AbstractHttpService {
    private static final String TAG = AsyncHttpImpl.class.getName();
    private static AsyncHttpClient client;
    private static File file;

    // The certificate's alias.
    private static final String STORE_ALIAS = "KuaiDanCA";

    // The certificate's password.
    private static final String STORE_PASS = "120521";

    static {
        AsyncHttpClient.allowRetryExceptionClass(javax.net.ssl.SSLException.class);
        client = new AsyncHttpClient();

    }

    public static AsyncHttpClient getInstance() {
        return client;
    }

    public AsyncHttpImpl(Context context) {
//        try {
//            InputStream is = null;
//            try {
//                KeyStore store = KeyStore.getInstance("BKS");
//                is = context.getResources().openRawResource(R.raw.huostore);
//                store.load(is, STORE_PASS.toCharArray());
//                client.setSSLSocketFactory(new SecureSocketFactory(store, STORE_ALIAS));
//            } catch (IOException e) {
//                throw new KeyStoreException(e);
//            } catch (CertificateException e) {
//                throw new KeyStoreException(e);
//            } catch (NoSuchAlgorithmException e) {
//                throw new KeyStoreException(e);
//            } catch (KeyManagementException e) {
//                throw new KeyStoreException(e);
//            } catch (UnrecoverableKeyException e) {
//                throw new KeyStoreException(e);
//            } finally {
//                AsyncHttpClient.silentCloseInputStream(is);
//            }
//        } catch (KeyStoreException e) {
//            Log.e(TAG, "Unable to initialize key store", e);
//        }
    }


    /**
     * GET 网络请求 无缓存
     *
     * @param url
     * @param params
     * @param httpListener
     */
    @Override
    public void doGet(String url, Map<String, Object> params, HttpListener<?> httpListener) {
        doGet(url, params, httpListener, CacheTime.NONE);
    }

    @Override
    public void doGet(String url, Map<String, Object> params, HttpListener<?> httpListener, int cacheTime) {
        String urlWithParams = buildUrlParams(url, params);
        Log.d(TAG, "doGet: " + urlWithParams);
        // 是否已经有缓存了，存在自动返回缓存数据
        if (cacheTime != 0 && fireCache(urlWithParams, httpListener)) return;
        // 已经有同一URL的请求了，等待自动响应
        if (addQequest(urlWithParams, httpListener)) return;
        doStringGet(urlWithParams, cacheTime);
    }

    /**
     * POST 网络请求
     *
     * @param url
     * @param httpListener
     */
    @Override
    public void doPost(String url, Map<String, Object> params, final HttpListener<?> httpListener) {
        RequestParams requestParams = new RequestParams();
        for (String key : params.keySet()) {
            if (params.get(key) instanceof File) {
                try {
                    requestParams.put(key, (File) params.get(key));
                } catch (FileNotFoundException e) {
                    Log.e(TAG, "file not found!!! " + params.get(key));
                }
            } else {
                requestParams.put(key, params.get(key));
            }
        }
        Log.d("PARAMS", requestParams.toString());

        client.post(url, requestParams, new TextHttpResponseHandler() {
            @Override
            public void onStart() {
                httpListener.onStart();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                httpListener.onFailure(responseString, throwable);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                httpListener.handleResponse(responseString);
            }

            @Override
            public void onFinish() {
                httpListener.onFinish();
            }
        });
    }

    /**
     * POST 网络请求
     *
     * @param url
     * @param jsonObject
     * @param httpListener
     */
    @Override
    public void doPost(String url, JSONObject jsonObject, HttpListener<?> httpListener) {
        RequestParams requestParams = new RequestParams();
    }

    @Override
    public void doPut(String url, Map<String, Object> params, final HttpListener<?> httpListener) {
        RequestParams requestParams = new RequestParams();
        for (String key : params.keySet()) {
            requestParams.put(key, params.get(key));

        }
        Log.e("TEST", requestParams.toString());
        client.put(url, requestParams, new TextHttpResponseHandler() {
            @Override
            public void onStart() {
                httpListener.onStart();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                httpListener.onFailure(responseString, throwable);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                httpListener.handleResponse(responseString);
            }

            @Override
            public void onFinish() {
                httpListener.onFinish();
            }
        });
    }

    @Override
    public Map<String, String> getHeaderMap() {
        return null;
    }

    /**
     * 添加请求头信息
     *
     * @param key
     * @param value
     */
    @Override
    public void addHeader(String key, String value) {
        super.addHeader(key, value);
        client.addHeader(key, value);
    }

    /**
     * GET String　请求
     *
     * @param urlWithParams
     */
    public void doStringGet(final String urlWithParams, final int cacheTime) {
        client.get(urlWithParams, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                // notify & add cache
                sendSuccessResponse(urlWithParams, new String(responseBody), cacheTime);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                // notify
                sendFailureResponse(urlWithParams, error.getMessage(), error);
            }
        });
    }
}
