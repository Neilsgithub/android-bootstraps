package info.xiequan.androidbootstraps.util.network;


import android.util.Log;

import info.xiequan.androidbootstraps.Object.RawStatus;
import info.xiequan.androidbootstraps.exception.NotWebDataException;
import info.xiequan.androidbootstraps.exception.WebDataException;
import info.xiequan.androidbootstraps.util.common.GsonUtil;
import info.xiequan.androidbootstraps.util.common.StringUtils;


/**
 * Created by spark on 15/9/14.
 * www.blueowls.net
 * i@xiequan.info
 */
public abstract class StringHttpListener implements HttpListener<String> {
    private static final String TAG = StringHttpListener.class.getName();

    @Override
    public void handleResponse(String response) {
        try {
            if (StringUtils.isEmpty(response)) {
                onFailure("请求数据为空", new NotWebDataException());
                return;
            }
            //转换最外层json 检查是否存在后台返回的error
            RawStatus rawStatus;
            try {
                rawStatus = GsonUtil.fromJson(response, RawStatus.class);
//                if ((!"success".equals(rawStatus.getStatus())) || (!StringUtils.isEmpty(rawStatus.getMessage()))) {
//                    onFailure(rawStatus.getMessage(), new WebDataException(rawStatus.getMessage()));
//                    return;
//                }
            } catch (Exception e) {
                String msg = "外层json转换失败.";

                onFailure(msg, new WebDataException(msg));
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
