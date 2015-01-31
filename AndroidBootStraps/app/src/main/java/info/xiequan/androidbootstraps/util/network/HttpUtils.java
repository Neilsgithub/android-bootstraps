package info.xiequan.androidbootstraps.util.network;

import android.util.Log;

import java.net.URLEncoder;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tony on 11/9/14.
 */
public class HttpUtils {
    private static final String TAG = HttpUtils.class.getName();

    /**
     * Converts <code>params</code> 使用于URL参数格式化，或者body参数格式化
     */
    public static String encodeParameters(Map<String, String> params, String paramsEncoding) {
        StringBuilder encodedParams = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                encodedParams.append(URLEncoder.encode(dealParamKey(entry.getKey()), paramsEncoding));
                encodedParams.append('=');
                //TODO: edited by wilson
                if (entry.getValue() != null) {
                    encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                }
                encodedParams.append('&');
            }
//            if (HttpConstants.DEBUG) Log.d(TAG, encodedParams.toString());
            return encodedParams.toString();
        } catch (Exception uee) {
            Log.e(TAG, "encodedParams: " + params, uee);
            throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
        }
    }

    /**
     * Converts <code>params</code> into an application/x-www-form-urlencoded encoded string.
     */
    public static byte[] encodeParametersToBytes(Map<String, String> params, String paramsEncoding) {
        try {
            return encodeParameters(params, paramsEncoding).getBytes(paramsEncoding);
        } catch (Exception uee) {
            throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
        }
    }

    /**
     * name[0]
     * to
     * name[]
     *
     * @param key
     * @return
     */
    public static String dealParamKey(String key) {
        String content = key;
        String regex = "\\[[0-9]\\d*\\]$";
        System.out.println(content);

        Matcher matcher = Pattern.compile(regex).matcher(content);
        if (matcher.find()) {
            content = content.replace(matcher.group(), "[]");
        }

        return content;
    }
}
