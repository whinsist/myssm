package cn.com.cloudstar.rightcloud.framework.test.t003util.guavacache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.lang.UUID;

import cn.com.cloudstar.rightcloud.framework.common.util.DateUtil;

public enum PowerVcTokenCache {

    INSTANCE;

    volatile static Cache<String, TokensResponse> tokenCache = CacheBuilder.newBuilder()
                                                                           ////设置并发数为5，即同一时间最多只能有5个线程往cache执行写入操作
                                                                           .concurrencyLevel(5)
                                                                           //initialCapacity：初始容量配置，默认值是16 maximumSize：最大容量 maximumWeight：最大权重
                                                                           .maximumSize(10000)
                                                                           //设置cache中的数据在写入之后的存活时间为10秒
                                                                           .expireAfterWrite(2, TimeUnit.HOURS)
                                                                           .build();

    public TokensResponse getCacheOfTokens(BaseRequest base) {

        String key = getApiKey(base);
        Object object = tokenCache.getIfPresent(key);
        if (Objects.nonNull(object)) {
            return (TokensResponse) object;
        }

        TokensResponse tokensResponse = new TokensResponse();
        tokensResponse.setToken("token--" + DateUtil.dateFormat(new Date()));
        tokensResponse.setTenantId(UUID.randomUUID().toString());
        tokenCache.put(key, tokensResponse);
        return tokensResponse;
    }

    public TokensResponse getCacheOfTokens2(BaseRequest base) {
        try {
            String key = getApiKey(base);
            return tokenCache.get(key, new Callable<TokensResponse>() {
                @Override
                public TokensResponse call() throws ExecutionException {
                    return getTokens();
                }

                //获取tokens和tennant_id
                private TokensResponse getTokens() {
                    TokensResponse tokensResponse = new TokensResponse();
                    tokensResponse.setToken("token--" + DateUtil.dateFormat(new Date()));
                    tokensResponse.setTenantId(UUID.randomUUID().toString());
                    return tokensResponse;
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
            removeCache(getApiKey(base));
            return null;
        }
    }

    public void removeCache(BaseRequest baseRequest) {
        removeCache(getApiKey(baseRequest));
    }

    private void removeCache(String apiKey) {
        tokenCache.invalidate(apiKey);
    }

    private String getApiKey(BaseRequest base) {
        return base.getProviderUrl().trim() + "@" + base.getTenantName() + ":" +
                base.getTenantUserName() + "#" + base.getTenantUserPass();
    }

}
