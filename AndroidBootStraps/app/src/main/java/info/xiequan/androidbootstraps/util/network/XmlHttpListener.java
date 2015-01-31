package info.xiequan.androidbootstraps.util.network;

import android.util.Log;

import info.xiequan.androidbootstraps.exception.NotWebDataException;
import info.xiequan.androidbootstraps.util.common.StringUtils;


/**
 * Created by spark on 7/1/15.
 * www.blueowls.net
 * i@xiequan.info
 */
public abstract class XmlHttpListener implements HttpListener<String> {
    private static final String TAG = StringHttpListener.class.getName();

    @Override
    public void handleResponse(String response) {
        try {
            if (StringUtils.isEmpty(response)) {
                onFailure("请求数据为空", new NotWebDataException());
                return;
            }
            onSuccess(response);
        } catch (Exception e) {
            Log.e(TAG, "e:" + e);
        } finally {
            onFinish();
        }
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onFinish() {

    }

    @Override
    public void onFailure(String error, Throwable e) {
        Log.e(TAG, "msg:" + error + ", e:" + e);

    }
}
