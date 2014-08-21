package info.xiequan.androidbootstraps.exception;


import info.xiequan.androidbootstraps.constant.ExceptionType;

/**
 * 记录volley自身抛出的异常，连接服务器发生的异常，比如 404 500
 */
public class HandleVolleyException extends BaseException {
    public HandleVolleyException() {
    }

    public HandleVolleyException(String detailMessage) {
        super(detailMessage);
    }

    public HandleVolleyException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public HandleVolleyException(Throwable throwable) {
        super(throwable);
    }

    @Override
    public ExceptionType getType() {
        return ExceptionType.HANDLE_VOLLEY_EXCEPTION;
    }
}
