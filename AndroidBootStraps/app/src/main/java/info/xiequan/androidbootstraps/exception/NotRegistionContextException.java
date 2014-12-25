package info.xiequan.androidbootstraps.exception;

/**
 * Created by spark on 18/9/14.
 * www.blueowls.net
 * i@xiequan.info
 */
public class NotRegistionContextException extends AppException {
    public NotRegistionContextException() {
        super("没有注册上下文环境(Application)");
    }

    public NotRegistionContextException(String detailMessage) {
        super(detailMessage);
    }
}
