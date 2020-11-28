package cn.com.cloudstar.rightcloud.framework.test.t003util;

import cn.com.cloudstar.rightcloud.framework.test.t003util.guavacache.BaseRequest;
import cn.com.cloudstar.rightcloud.framework.test.t003util.guavacache.PowerVcTokenCache;
import cn.com.cloudstar.rightcloud.framework.test.t003util.guavacache.TokensResponse;

public class Test23GuavaCache {


    public static void main(String[] args) {
        BaseRequest request = new BaseRequest("http://oll.com");
        TokensResponse tokensResponse = PowerVcTokenCache.INSTANCE.getCacheOfTokens(request);
        System.out.println(tokensResponse);
        for (int i = 0; i < 100; i++) {
            TokensResponse response = PowerVcTokenCache.INSTANCE.getCacheOfTokens(request);
            System.out.println(i + "  " + response);
        }

    }


}



