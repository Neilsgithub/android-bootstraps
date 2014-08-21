package info.xiequan.androidbootstraps.util;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by spark on 9/7/14.
 */
public class StringUtils {
    public static final String PRODUCT_KIND_SEPERATOR = "|";
    public static final String VERSION_SEPERATOR = ".";
    public static final String PRODUCT_KIND_SEPERATOR_IN_JAVA = "//|";

    public static boolean isEmpty(String str) {
        return TextUtils.isEmpty(str) || TextUtils.isEmpty(str.trim());
    }

    /**
     * judge string equals or not
     *
     * @param str
     * @param other
     * @return false if anyone is empty
     */
    public static boolean equals(String str, String other) {
        if (isEmpty(str) || isEmpty(other)) {
            return false;
        }
        return str.equals(other);
    }

    /**
     * judge string equals or not
     *
     * @param str
     * @param other
     * @return false if anyone is empty
     */
    public static boolean equalsIgnoreCase(String str, String other) {
        if (isEmpty(str) || isEmpty(other)) {
            return false;
        }
        return str.equalsIgnoreCase(other);
    }

    public static List<String> stringToList(String str, String seperator) {
        List<String> itemList = new ArrayList<String>();
        if (isEmpty(str)) {
            return itemList;
        }
        StringTokenizer st = new StringTokenizer(str, seperator);
        while (st.hasMoreTokens()) {
            itemList.add(st.nextToken());
        }

        return itemList;
    }

    public static List<String> productKeyToList(String productKey) {
        return stringToList(productKey, PRODUCT_KIND_SEPERATOR);
    }

    public static String productKeyListToString(List<String> keyItemList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keyItemList.size(); i++) {
            if (i == keyItemList.size() - 1) {
                sb.append(keyItemList.get(i));
                break;
            }
            sb.append(keyItemList.get(i) + PRODUCT_KIND_SEPERATOR);
        }
        return sb.toString();
    }
}
