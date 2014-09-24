package info.xiequan.androidbootstraps.util.common;

import java.util.Collection;
import java.util.Map;

/**
 * volley String callback
 * Created by spark on 23/8/14.
 * www.blueowls.net
 * i@xiequan.info
 */
public class CollectionUtils {
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmptyMap(Map<?, ?> map) {
        return map == null || isEmpty(map.keySet());
    }

}
