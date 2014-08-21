package info.xiequan.androidbootstraps.exception;


import info.xiequan.androidbootstraps.constant.ExceptionType;

/**
 * Created by Wilson on 14-7-24.
 */
public abstract class BaseException extends RuntimeException {

    protected BaseException() {
    }

    protected BaseException(String detailMessage) {
        super(detailMessage);
    }

    protected BaseException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    protected BaseException(Throwable throwable) {
        super(throwable);
    }

    public abstract ExceptionType getType();
}
