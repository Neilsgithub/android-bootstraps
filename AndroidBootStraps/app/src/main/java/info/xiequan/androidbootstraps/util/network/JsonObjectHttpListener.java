package info.xiequan.androidbootstraps.util.network;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import info.xiequan.androidbootstraps.exception.NotWebDataException;
import info.xiequan.androidbootstraps.util.common.StringUtils;


/**
 * Created by spark on 15/9/14.
 * www.blueowls.net
 * i@xiequan.info
 */
public abstract class JsonObjectHttpListener implements HttpListener<JSONObject> {
    private static final String TAG = JsonObjectHttpListener.class.getName();

    @Override
    public void handleResponse(String response) {
        try {
            if (StringUtils.isEmpty(response)) {
                onFailure("请求数据为空", new NotWebDataException());
                return;
            }
            onSuccess(new JSONObject(response));
        } catch (JSONException e) {
            Log.e(TAG, "转换JSON错误", e);
            onFailure("转换JSON错误", e);
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


}
