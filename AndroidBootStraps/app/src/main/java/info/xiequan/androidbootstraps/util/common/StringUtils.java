package info.xiequan.androidbootstraps.util.common;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * volley String callback
 * Created by spark on 23/8/14.
 * www.blueowls.net
 * i@xiequan.info
 */
public class StringUtils {
    public static final String PRODUCT_KIND_SEPERATOR = "|";
    public static final String VERSION_SEPERATOR = ".";
    public static final String PRODUCT_KIND_SEPERATOR_IN_JAVA = "//|";


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
    private final static Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    //private final static SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //private final static SimpleDateFormat dateFormater2 = new SimpleDateFormat("yyyy-MM-dd");





    /**
     * 判断给定字符串是否空白串。
     * 空白串是指由空格、制表符、回车符、换行符组成的字符串
     * 若输入字符串为null或空字符串，返回true
     * @param input
     * @return boolean
     */
    public static boolean isEmpty( String input )
    {
        if ( input == null || "".equals( input ) )
            return true;

        for ( int i = 0; i < input.length(); i++ )
        {
            char c = input.charAt( i );
            if ( c != ' ' && c != '\t' && c != '\r' && c != '\n' )
            {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是不是一个合法的电子邮件地址
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
        if(email == null || email.trim().length()==0)
            return false;
        return emailer.matcher(email).matches();
    }
    /**
     * 字符串转整数
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try{
            return Integer.parseInt(str);
        }catch(Exception e){}
        return defValue;
    }
    /**
     * 对象转整数
     * @param obj
     * @return 转换异常返回 0
     */
    public static int toInt(Object obj) {
        if(obj==null) return 0;
        return toInt(obj.toString(),0);
    }
    /**
     * 对象转整数
     * @param obj
     * @return 转换异常返回 0
     */
    public static long toLong(String obj) {
        try{
            return Long.parseLong(obj);
        }catch(Exception e){}
        return 0;
    }
    /**
     * 字符串转布尔值
     * @param b
     * @return 转换异常返回 false
     */
    public static boolean toBool(String b) {
        try{
            return Boolean.parseBoolean(b);
        }catch(Exception e){}
        return false;
    }

}
