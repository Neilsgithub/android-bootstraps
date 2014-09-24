package info.xiequan.androidbootstraps.util.network;

/**
 * Created by spark on 15/9/14.
 * www.blueowls.net
 * i@xiequan.info
 */
public interface HttpListener<T>{
    public void handleResponse(String response);

    public void onStart();

    public void onSuccess(T response);

    public void onFailure(String error, Throwable e);

    public void onFinish();
}
