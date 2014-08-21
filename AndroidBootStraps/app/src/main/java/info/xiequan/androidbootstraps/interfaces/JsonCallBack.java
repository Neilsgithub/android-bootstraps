package info.xiequan.androidbootstraps.interfaces;


import org.json.JSONObject;

import info.xiequan.androidbootstraps.constant.StringConstant;
import info.xiequan.androidbootstraps.exception.BaseException;
import info.xiequan.androidbootstraps.exception.WebDataException;


/**
 * Volley call back for json-object format
 * Created by Wilson on 14-6-23.
 */

public abstract class JsonCallBack {
    public void handle(JSONObject response) {
        if (response == null) {
            throw new WebDataException(StringConstant.EMPTY_RESPONSE);
        }

        success(response);
    }


    public abstract void success(JSONObject response);

    public abstract void error(BaseException e);

}