package cn.com.cloudstar.rightcloud.framework.test.t001study.restemplate.helper;

import com.google.common.collect.Maps;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author Hong.Wu
 * @date: 15:33 2020/05/02
 */
public class MaaUtil {

    private static final String TOKEN = "GArwNwK3X2dStNde2Y:wz2Gmz48tsMYguKGkH:CzputXvJuZy2L2ryB7hfBNn2MRzqRK35";

    public static Map<String, String> getHeaders() {
        String consumerKey = TOKEN.split(":")[0];
        String accessToken = TOKEN.split(":")[1];
        String tokenSecret = TOKEN.split(":")[2];
        String consumerSecret = "";

        Map<String, String> oauthParameters = Maps.newHashMap();
        oauthParameters.put("oauth_consumer_key", consumerKey);
        oauthParameters.put("oauth_signature_method", "PLAINTEXT");
        long timestamp = System.currentTimeMillis() / 1000;
        oauthParameters.put("oauth_timestamp", Long.toString(timestamp));
        oauthParameters.put("oauth_nonce", Long.toString(timestamp + new Random().nextInt()));
        oauthParameters.put("oauth_version", "1.0");
        oauthParameters.put("oauth_token", accessToken);
        String authorization = new SigningSupport().buildAuthorizationHeaderValueTest(oauthParameters, consumerSecret,
                                                                                      tokenSecret);

        Map<String, String> headers = Maps.newHashMap();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("authorization", authorization);
        return headers;
    }

    public static HttpHeaders getGetMethodHeaders() {
        HttpHeaders headers = new HttpHeaders();
        //headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        headers.add("authorization", getHeaders().get("authorization"));
        //headers.add("Content-Type", "application/json");
        return headers;
    }
    public static HttpHeaders getPostMethodHeaders() {
        HttpHeaders headers = new HttpHeaders();
        //headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        headers.add("authorization", getHeaders().get("authorization"));
        //headers.add("Content-Type", "application/json");
        // content-type为什么都可以?
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
