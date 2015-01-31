package info.xiequan.androidbootstraps.util.cache;


import info.xiequan.androidbootstraps.base.BaseApplication;

/**
 * 缓存管理类
 * <p/>
 * Created by zhihui_chen on 14-8-14.
 */
public class CacheManager {
    private static BaseApplication context = null;
    private static Cache mCache = null;

    public static void register(BaseApplication context) {
        CacheManager.context = context;
    }

    public static Cache getInstance() {
        if (mCache == null) {
            mCache = new GCacheImpl(context.getCacheDir());
        }
        return mCache;
    }
}
