package info.xiequan.androidbootstraps.util;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Wilson
 */
public class CollectionUtils {
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmptyMap(Map<?, ?> map) {
        return map == null || isEmpty(map.keySet());
    }

}
