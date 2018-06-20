package com.sail.simonli.server.gary;

import com.sail.simonli.server.weixinapi.cache.DefaultAccessTokenCache;
import com.sail.simonli.server.weixinapi.cache.IAccessTokenCache;


 
public class ApiConfigKit {

    static IAccessTokenCache accessTokenCache = new DefaultAccessTokenCache();

    public static void setAccessTokenCache(IAccessTokenCache accessTokenCache) {
        ApiConfigKit.accessTokenCache = accessTokenCache;
    }

    public static IAccessTokenCache getAccessTokenCache() {
        return ApiConfigKit.accessTokenCache;
    }
}
