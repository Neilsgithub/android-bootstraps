package info.xiequan.androidbootstraps.exception;

/**
 * Created by spark on 18/9/14.
 * www.blueowls.net
 * i@xiequan.info
 */
public class NotWebDataException extends AppException {
    public NotWebDataException() {
        super("没有获得请求数据");
    }

    public NotWebDataException(String detailMessage) {
        super(detailMessage);
    }
}
