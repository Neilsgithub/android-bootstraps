package info.xiequan.androidbootstraps.util;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by spark on 22/8/14.
 */
public class LogUtils {
    public static void debug(String tag, Object obj){
        if(obj instanceof String){
            Log.d(tag, obj.toString());
        }else{
            Class<? extends Object> cls = obj.getClass();
            Method[] methods = cls.getDeclaredMethods();
            if(methods!=null){
                Method method = null;
                String methodName = null;
                Log.d(tag, cls.getSimpleName() + " start");
                for (int i = 0; i < methods.length; i++) {
                    method = methods[i];
                    methodName = method.getName();
                    if (methodName.startsWith("get")
                            && !methodName.equals("getClass")
                            && (method.getParameterTypes() == null || method
                            .getParameterTypes().length == 0)) {
                        String msg = "";
                        try {
                            msg = methodName.substring(3) + ":" + method.invoke(obj, new Object[0]).toString();
                        } catch (IllegalAccessException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IllegalArgumentException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        Log.d(tag, msg);
                    }
                }
                Log.d(tag, cls.getSimpleName() + " end");
            }
        }
    }


}
