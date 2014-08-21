package info.xiequan.androidbootstraps.interfaces;


import info.xiequan.androidbootstraps.constant.StringConstant;
import info.xiequan.androidbootstraps.exception.BaseException;
import info.xiequan.androidbootstraps.exception.WebDataException;
import info.xiequan.androidbootstraps.util.StringUtils;

/**
 * Volley call back for string format
 * Created by Wilson on 14-6-23.
 */

public abstract class StringCallBack {

    public void handle(String response) {
        if (StringUtils.isEmpty(response)) {
            throw new WebDataException(StringConstant.EMPTY_RESPONSE);
        }

        success(response);
    }


    public abstract void success(String response);

    public abstract void error(BaseException e);

}