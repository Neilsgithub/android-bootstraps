package info.xiequan.androidbootstraps.module;

import java.util.HashMap;

import info.xiequan.androidbootstraps.util.network.HttpFactory;
import info.xiequan.androidbootstraps.util.network.HttpService;
import info.xiequan.androidbootstraps.util.network.StringHttpListener;

/**
 * Created by spark on 31/1/15.
 * www.blueowls.net
 * i@xiequan.info
 */
public class SideChefModule extends BaseModule {
    /**
     * 获取sidechef数据
     */
    public static void getSideChefList(String url, int pageIndex, int pageSize, final StringHttpListener listener) {
        HashMap<String, Object> params = createParams();
        params.put("pageIndex", String.valueOf(pageIndex));
        params.put("pageSize", String.valueOf(pageSize));
        HttpService httpService = HttpFactory.getHttpService();
        httpService.doGet(url, params, listener);

    }
}
