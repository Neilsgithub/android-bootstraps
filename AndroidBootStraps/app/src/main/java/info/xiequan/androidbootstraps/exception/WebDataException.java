package info.xiequan.androidbootstraps.exception;


import info.xiequan.androidbootstraps.constant.ExceptionType;

/**
 * 普通的网络数据异常
 */
public class WebDataException extends BaseException {
    public WebDataException() {
    }

    public WebDataException(String detailMessage) {
        super(detailMessage);
    }

    public WebDataException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public WebDataException(Throwable throwable) {
        super(throwable);
    }

    @Override
    public ExceptionType getType() {
        return ExceptionType.WEB_DATA_EXCEPTION;
    }
}
