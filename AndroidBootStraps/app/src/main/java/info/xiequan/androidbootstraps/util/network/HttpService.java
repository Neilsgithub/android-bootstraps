package info.xiequan.androidbootstraps.util.network;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by spark on 15/9/14.
 * www.blueowls.net
 * i@xiequan.info
 */
public interface HttpService {
    /**
     * GET 网络请求 无缓存
     *
     * @param url
     * @param params
     * @param httpListener
     */
    public void doGet(String url, Map<String, String> params, HttpListener<?> httpListener);

    /**
     * GET 网络请求
     *
     * @param url
     * @param params
     * @param httpListener
     * @param cacheTime
     */
    public void doGet(String url, Map<String, String> params, HttpListener<?> httpListener, int cacheTime);

    /**
     * POST 网络请求
     *
     * @param url
     * @param params
     * @param httpListener
     */
    public void doPost(String url, Map<String, String> params, HttpListener<?> httpListener);

    /**
     * POST 网络请求
     *
     * @param url
     * @param jsonObject
     * @param httpListener
     */
    public void doPost(String url, JSONObject jsonObject, HttpListener<?> httpListener);

    /**
     * 获取请求中的头部信息包
     *
     * @return
     */
    public Map<String, String> getHeaderMap();

    /**
     * 添加请求头信息
     *
     * @param key
     * @param value
     */
    public void addHeader(String key, String value);
}
