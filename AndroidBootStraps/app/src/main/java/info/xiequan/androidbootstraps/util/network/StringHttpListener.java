package info.xiequan.androidbootstraps.util.network;


import info.xiequan.androidbootstraps.exception.NotWebDataException;
import info.xiequan.androidbootstraps.util.common.StringUtils;

/**
 * Created by spark on 15/9/14.
 * www.blueowls.net
 * i@xiequan.info
 */
public abstract  class StringHttpListener implements HttpListener<String> {
    private static final String TAG = StringHttpListener.class.getName();

    @Override
    public void handleResponse(String response) {
        try {
            if (StringUtils.isEmpty(response)) {
                onFailure("请求数据为空", new NotWebDataException());
                return;
            }
            onSuccess(response);
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
