package info.xiequan.androidbootstraps.util.network;

/**
 * Created by spark on 15/9/14.
 * www.blueowls.net
 * i@xiequan.info
 */
public class HttpConstants {
    /**
     * 请求超时时间
     */
    public static final int REQUEST_TIMEOUT_MS = 8000;

    /**
     * 请求重试次数
     */
    public static final int REQUEST_MAX_RETRIES = 5;

    /**
     * 请求重试因子
     */
    public static final float REQUEST_BACKOFF_MULT = 2f;

    /**
     * 参数默认编码
     */
    public static final String DEFAULT_PARAMS_ENCODING = "UTF-8";
}
