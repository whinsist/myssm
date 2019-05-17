package cn.com.cloudstar.rightcloud.framework.common.util;


import cn.com.cloudstar.rightcloud.framework.common.cache.JedisUtil;
import cn.com.cloudstar.rightcloud.framework.common.constants.SysConfigConstants;
import cn.com.cloudstar.rightcloud.framework.common.util.http.HttpClientUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Demo HUAWEIAPIUtil
 * 华为API 调用工具
 * @author yangsen
 * @date 2018/08/13
 *
 */
public class HUAWEIAPIUtil {

    private static String HUAWEI_TOKEN_REDIS_KEY = "hw:api:token";
    private static String DCUC_HTTP_URL_PREFIX;
    private static String DCUC_HTTPS_URL_PREFIX;
    static {
        DCUC_HTTP_URL_PREFIX = PropertiesUtil.getProperty(SysConfigConstants.DCUC_API_HTTP_PREFIX);
        DCUC_HTTPS_URL_PREFIX = PropertiesUtil.getProperty(SysConfigConstants.DCUC_API_HTTPS_PREFIX);
    }

    public enum ValidateType{
        MONITOR("monitor"),ROLEPERMISSION("rolePermission");
        ValidateType(String type){
            this.type = type;
        }
        private String type;
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public enum CommonUrl{
        GET_TOKEN("/v1/apigw/oauth2/token");
        private String url;

        CommonUrl(String url){
            this.url = url;
        }
        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
    public interface HUAWEIUrlEnum{
        String getUrl();
        String getAppKey();
        String getAppSecret();
    }


    private static final Logger logger = LoggerFactory.getLogger(HUAWEIAPIUtil.class);

    private static void addToken(Map<String,String> headers,HUAWEIUrlEnum em) {
        addToken(headers,em.getAppKey(),em.getAppSecret());
    }
    private static void addToken(Map<String,String> headers,String appkey,String appsecret){
        String token = lazyGetHWToken(appkey, appsecret);
//        headers.put("X-Auth-Token",token);
        headers.put("Authorization","Bearer "+token);
    }
    public static String get(String url) throws Exception {
        return get(url, new HashMap<>());
    }
    public static String get(String url,HUAWEIUrlEnum em) throws Exception {
        return get(url, null,em.getAppKey(),em.getAppSecret());
    }
    public static String get(String url,String key,String secret) throws Exception {
        return get(url, null,key,secret);
    }
    public static String get(String url , Map<String,String> headers) throws Exception {
        return get(url,headers,null,null);
    }
    public static String get(String url , Map<String,String> headers,String key,String secret) throws Exception {
        if(headers==null){
            headers = new HashMap<>();
        }
        addToken(headers,key,secret);
        return HttpClientUtil.get(url, headers);
    }
    public static String get(String url , Map<String,String> headers,Map<String,String> query,String key,String value) throws Exception {
        if(headers==null){
            headers = new HashMap<>();
        }
        addToken(headers,key,value);
        return HttpClientUtil.get(url, headers, query);
    }
    public static String get(String url , Map<String,String> headers,Map<String,String> query) throws Exception {
        return get(url, headers, query,null,null);
    }


    public static String post(String url) throws Exception {
        return post(url,"", null);
    }
    public static String post(String url,Map<String,Object> parameters , Map<String,String> headers) throws Exception {
        return post(url,parameters,headers,null,null);
    }
    public static String post(String url,Map<String,Object> parameters , Map<String,String> headers,String key,String secret) throws Exception {
        if(headers==null){
            headers = new HashMap<>();
        }
        addToken(headers,key,secret);
        return HttpClientUtil.post(url, parameters, headers);
    }
    public static String post(String url,String body , Map<String,String> headers) throws Exception {
        return post(url,body,headers,null,null);
    }
    public static String post(String url,String body , Map<String,String> headers,String key,String secret) throws Exception {
        if(headers==null){
            headers = new HashMap<>();
        }
        addToken(headers,key,secret);
        return HttpClientUtil.post(url,body, headers);
    }
    public static String post(String url , Map<String,Object> parameters) throws Exception {
        return post(url,parameters,null,null);
    }
    public static String post(String url , Map<String,Object> parameters,String key,String secret) throws Exception {
        Map<String,String>  headers = new HashMap<>();
        addToken(headers,key,secret);
        return HttpClientUtil.post(url,parameters, headers);
    }

    private static String lazyGetHWToken(String appkey, String appSecret){
        String token = JedisUtil.instance().get(HUAWEI_TOKEN_REDIS_KEY+":"+appkey);
        if(StringUtils.isEmpty(token)){
            getHWToken(appkey,appSecret);
            token = JedisUtil.instance().get(HUAWEI_TOKEN_REDIS_KEY+":"+appkey);
        }
        return token;
    }
    private static void getHWToken(String appkey, String appSecret){
        Map<String,String> requestHeaders = new HashMap<>();
        Map<String,Object> requestParameters = new HashMap<>();
        requestHeaders.put("content-type","application/x-www-form-urlencoded");
        // 获取网关认证Token
        try {
            requestParameters.put("client_id",appkey);
            requestParameters.put("client_secret",appSecret);
            requestParameters.put("grant_type","client_credentials");

            String resp =  HttpClientUtil.post(DCUC_HTTPS_URL_PREFIX+ CommonUrl.GET_TOKEN.getUrl(), requestParameters, requestHeaders);
            logger.debug(resp);
            Map<String, Object> response = JsonUtil.fromJson(resp,Map.class);
            String accessToken = (String) response.get("access_token");
            String expiresIn = (String) response.get("expires_in");
            JedisUtil.instance().set(HUAWEI_TOKEN_REDIS_KEY+":"+appkey,accessToken,Integer.valueOf(expiresIn));
        } catch (Exception e) {
            logger.info("获取华为网关Token失败");
            e.printStackTrace();
        }
    }

    public static boolean validateUrl(String url) {
        return validateUrl(url, null);
    }
    /**
     * type字段来表示 此接口是 监控接口 还是服务权限同步接口
     * **/
    public static boolean validateUrl(String url,String type){
        String responseText = null;
        try {
            responseText = HUAWEIAPIUtil.get(url);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(url+"无法用get访问",e);
            try {
                responseText = HUAWEIAPIUtil.post(url);
            }catch (Exception ee){
                ee.printStackTrace();
                logger.info(url+"无法用POST访问",e);
            }
        }
        if(org.apache.commons.lang3.StringUtils.isEmpty(responseText)){
            return false;
        }
        try {
            JSONObject.parseObject(responseText);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("返回值转换Json失败", responseText, e);
            try {
                JSONArray.parseArray(responseText);
            }catch (Exception ee){
                logger.info("返回值转换JsonArray失败", responseText, e);
                return false;
            }
        }
        if(responseText.contains("\"error_code\"")){
            // 华为网关错误码
            return false;
        }
        if(ValidateType.ROLEPERMISSION.getType().equals(type)){
            // 检测是否满足 同步角色权限接口的格式
            JSONObject jsonObject = JSONObject.parseObject(responseText);
            JSONObject services =  jsonObject.getJSONObject("service_roles");
            if(services==null){
                return false;
            }
        }else if(ValidateType.MONITOR.getType().equals(type)){
            // 检测一下是否满足 监控接口格式
            JSONObject jsonObject = JSONObject.parseObject(responseText);
            JSONObject metrics =  jsonObject.getJSONObject("metrics");
            if(metrics==null){
                return false;
            }
        }
        return true;
    }



}
