package info.xiequan.androidbootstraps.exception;


import info.xiequan.androidbootstraps.constant.ExceptionType;

/**
 * 转换json出错
 */
public class HandleGsonException extends BaseException {

    public HandleGsonException() {
    }

    public HandleGsonException(String detailMessage) {
        super(detailMessage);
    }

    public HandleGsonException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public HandleGsonException(Throwable throwable) {
        super(throwable);
    }

    @Override
    public ExceptionType getType() {
        return ExceptionType.HANDLE_GSON_EXCEPTION;
    }
}
