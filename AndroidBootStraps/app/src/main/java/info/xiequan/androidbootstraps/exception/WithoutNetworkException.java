package info.xiequan.androidbootstraps.exception;


import info.xiequan.androidbootstraps.constant.ExceptionType;

/**
 * 无法连接网络时候的异常
 */
public class WithoutNetworkException extends BaseException {
    public WithoutNetworkException() {
    }

    public WithoutNetworkException(String detailMessage) {
        super(detailMessage);
    }

    public WithoutNetworkException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public WithoutNetworkException(Throwable throwable) {
        super(throwable);
    }

    @Override
    public ExceptionType getType() {
        return null;
    }
}
