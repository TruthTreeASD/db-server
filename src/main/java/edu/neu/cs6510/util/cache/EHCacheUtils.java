package edu.neu.cs6510.util.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EHCacheUtils {

    public static void setCache(CacheManager cacheManager, CacheEnum cacheEnum,String key, Object object){
        Cache cache = cacheManager.getCache(cacheEnum.toString());
        Element element = new Element(key,object);
        cache.put(element);
    }

    public static Object getCache(CacheManager cacheManager, CacheEnum cacheEnum, String key){
        Object object = null;
        Cache cache = cacheManager.getCache(cacheEnum.toString());
        if(cache.get(key)!=null && !cache.get(key).equals("")){
            object = cache.get(key).getObjectValue();
        }
        return object;
    }

    public static boolean isExsit(CacheManager cacheManager, CacheEnum cacheEnum, String key){
        Cache cache = cacheManager.getCache(cacheEnum.toString());
        return cache.get(key)!=null && !cache.get(key).equals("");
    }
}