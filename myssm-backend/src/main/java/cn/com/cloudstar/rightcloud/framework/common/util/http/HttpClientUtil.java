/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util.http;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import cn.com.cloudstar.rightcloud.framework.common.util.JsonUtil;

/**
 * The type Http client util.
 */
public class HttpClientUtil {

    private final static String USER_AGENT = "Mozilla/5.0";

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * Send get string.
     *
     * @param url the url
     * @return the string
     * @throws Exception the exception
     */
    public static String get(String url) throws Exception {
        return get(url, null);
    }

    /**
     * Send get string.
     *
     * @param url     the url
     * @param headers the headers
     * @return the string
     * @throws Exception the exception
     */
    public static String get(String url, Map<String, String> headers) throws Exception {
        CloseableHttpClient client = getHttpClient();
        HttpGet request = new HttpGet(url);

        // add request header
        request.addHeader("User-Agent", USER_AGENT);
        if (headers != null) {
            headers.forEach(request::addHeader);
        }
        HttpResponse response = client.execute(request);

        logger.debug("\nSending 'GET' request to URL : " + url);
        logger.debug("Response Code : " + response.getStatusLine().getStatusCode());


        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        logger.debug(result.toString());
        return result.toString();
    }


    /**
     *  HTTP GET
     * @param url 路径
     * @param headers
     * @param queryParams
     * @return
     * @throws Exception
     */
    public static String get(String url, Map<String, String> headers, Map<String, String> queryParams) throws Exception {
        CloseableHttpClient client = getHttpClient();
        HttpGet request = new HttpGet(url);

        // add request header
        request.addHeader("User-Agent", USER_AGENT);
        if (headers != null) {
            headers.forEach(request::addHeader);
        }
        if (queryParams != null) {
            url += generateQueryParams(queryParams);
        }
        HttpResponse response = client.execute(request);

        logger.debug("\nSending 'GET' request to URL : " + url);
        logger.debug("Response Code : " + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));

        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        logger.debug(result.toString());
        return result.toString();
    }

    /**
     * 根据queryMap生成query字符串
     */
    private static String generateQueryParams(Map<String, String> queryMap) {
        if (queryMap == null || queryMap.isEmpty()) {
            return "";
        }
        StringBuffer sb = new StringBuffer("?");
        Set<String> keySet = queryMap.keySet();
        Iterator it = keySet.iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            String value = queryMap.get(key);
            try {
                if (StringUtils.isNotEmpty(key)) {
                    key = URLEncoder.encode(key, "utf-8");
                }
                if (StringUtils.isNotEmpty(value)) {
                    value = URLEncoder.encode(value, "utf-8");
                } else {
                    value = "";
                }
            } catch (UnsupportedEncodingException e) {
                logger.error("URLEncoder.encode 异常", e);
            }
            sb.append(key).append("=").append(value);
            if (it.hasNext()) {
                sb.append("&");
            }
        }
        return sb.toString();
    }

    /**
     * 构造使用于Http、Https的Client
     *
     * @return HttpClient
     */
    private static CloseableHttpClient getHttpClient() {
        RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.create();
        ConnectionSocketFactory plainSF = new PlainConnectionSocketFactory();
        registryBuilder.register("http", plainSF);
        //指定信任密钥存储对象和连接套接字工厂
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            //信任任何链接
            TrustStrategy anyTrustStrategy = (x509Certificates, s) -> true;
            SSLContext sslContext = SSLContexts.custom().useTLS().loadTrustMaterial(trustStore, anyTrustStrategy)
                    .build();
            LayeredConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(sslContext,
                    SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER
            );
            registryBuilder.register("https", sslSF);
        } catch (KeyStoreException | KeyManagementException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        Registry<ConnectionSocketFactory> registry = registryBuilder.build();
        //设置连接管理器
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(registry);
        //构建客户端
        return HttpClientBuilder.create().setConnectionManager(connManager).build();
    }

    /**
     * Delete string.
     *
     * @param url the url
     * @return the string
     * @throws Exception the exception
     */
    public static String delete(String url) throws Exception {
        return delete(url, null);
    }

    /**
     * Delete string.
     *
     * @param url     the url
     * @param headers the headers
     * @return the string
     * @throws Exception the exception
     */
    public static String delete(String url, Map<String, String> headers) throws Exception {
        CloseableHttpClient client = getHttpClient();
        HttpDelete request = new HttpDelete(url);

        // add request header
        request.addHeader("User-Agent", USER_AGENT);
        if (headers != null) {
            headers.forEach(request::addHeader);
        }
        HttpResponse response = client.execute(request);

        logger.debug("\nSending 'DELETE' request to URL : " + url);
        logger.debug("Response Code : " + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        logger.debug(result.toString());
        return result.toString();
    }

    /**
     * Put string.
     *
     * @param url     the url
     * @param param   the param
     * @param headers the headers
     * @return the string
     * @throws Exception the exception
     */
    public static String put(String url, Map<String, Object> param, Map<String, String> headers) throws Exception {

        CloseableHttpClient client = getHttpClient();
        HttpPut httpPut = new HttpPut(url);

        // add header
        httpPut.setHeader("User-Agent", USER_AGENT);
        if (headers != null) {
            headers.forEach(httpPut::addHeader);
        }
        List<NameValuePair> urlParameters = new ArrayList<>();
        param.forEach((key, val) -> urlParameters.add(new BasicNameValuePair(key, val == null ? "" : val.toString())));

        httpPut.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse response = client.execute(httpPut);
        logger.debug("\nSending 'PUT' request to URL : " + url);
        logger.debug("Put parameters : " + httpPut.getEntity());
        logger.debug("Response Code : " + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        logger.debug(result.toString());
        return result.toString();
    }

    /**
     * Put string.
     *
     * @param url     the url
     * @param body    the body
     * @param headers the headers
     * @return the string
     * @throws Exception the exception
     */
    public static String put(String url, String body, Map<String, String> headers) throws Exception {

        CloseableHttpClient client = getHttpClient();
        HttpPut httpPut = new HttpPut(url);

        // add header
        httpPut.setHeader("User-Agent", USER_AGENT);
        if (headers != null) {
            headers.forEach(httpPut::addHeader);
        }
        StringEntity entity = new StringEntity(body, "UTF-8");
        httpPut.setEntity(entity);

        HttpResponse response = client.execute(httpPut);
        logger.debug("\nSending 'PUT' request to URL : " + url);
        logger.debug("Put parameters : " + httpPut.getEntity());
        logger.debug("Response Code : " + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        logger.debug(result.toString());
        return result.toString();
    }

    /**
     * Send post string.
     *
     * @param url   the url
     * @param param the param
     * @return the string
     * @throws Exception the exception
     */
    public static String post(String url, Map<String, Object> param) throws Exception {
        return post(url, param, null);
    }

    /**
     * Send post string.
     *
     * @param url     the url
     * @param param   the param
     * @param headers the headers
     * @return the string
     * @throws Exception the exception
     */
    public static String post(String url, Map<String, Object> param, Map<String, String> headers) throws Exception {

        CloseableHttpClient client = getHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);
        if (headers != null) {
            headers.forEach(post::addHeader);
        }
        List<NameValuePair> urlParameters = new ArrayList<>();
        param.forEach((key, val) -> urlParameters.add(new BasicNameValuePair(key, val == null ? "" : val.toString())));

        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(urlParameters, Charset.forName("UTF-8"));
        entity.setContentEncoding("UTF-8");
        post.setEntity(entity);

        HttpResponse response = client.execute(post);
        logger.debug("\nSending 'POST' request to URL : " + url);
        logger.debug("Post parameters : " + post.getEntity());
        logger.debug("Response Code : " + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        logger.debug(result.toString());
        return result.toString();
    }

    /**
     * send post
     *
     * @param url         the url
     * @param param       the param
     * @param contentType the content type
     * @param cookies     the cookies
     * @return string string
     * @throws Exception the exception
     */
    public static String sendPostCookies(String url, Map<String, Object> param, String contentType, String cookies)
            throws Exception {

        CloseableHttpClient client = getHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);
        post.setHeader("Content-Type", contentType);
        post.setHeader("Cookie", cookies);
        List<NameValuePair> urlParameters = new ArrayList<>();
        param.forEach((key, val) -> urlParameters.add(new BasicNameValuePair(key, val == null ? "" : val.toString())));

        post.setEntity(new UrlEncodedFormEntity(urlParameters, "UTF-8"));

        HttpResponse response = client.execute(post);
        logger.debug("\nSending 'POST' request to URL : " + url);
        logger.debug("Post parameters : " + post.getEntity());
        logger.debug("Response Code : " + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        logger.debug(result.toString());
        return result.toString();
    }

    /**
     * send post
     *
     * @param url         the url
     * @param param       the param
     * @param contentType the content type
     * @param cookies     the cookies
     * @return string string
     * @throws Exception the exception
     */
    public static String sendGetCookies(String url, Map<String, Object> param, String contentType, String cookies)
            throws Exception {

        CloseableHttpClient client = getHttpClient();
        HttpGet get = new HttpGet(url);

        // add header
        get.setHeader("User-Agent", USER_AGENT);
        get.setHeader("Content-Type", contentType);
        get.setHeader("Cookie", cookies);
        List<NameValuePair> urlParameters = new ArrayList<>();
        param.forEach((key, val) -> urlParameters.add(new BasicNameValuePair(key, val == null ? "" : val.toString())));

        HttpResponse response = client.execute(get);
        logger.debug("\nSending 'POST' request to URL : " + url);
        logger.debug("Response Code : " + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        logger.debug(result.toString());
        return result.toString();
    }


    /**
     * Send open falcon login post string.
     *
     * @param url   the url
     * @param param the param
     * @return the string
     * @throws Exception the exception
     */
    public static String sendOpenFalconLoginPost(String url, Map<String, Object> param) throws Exception {

        CloseableHttpClient client = getHttpClient();
        HttpPost post = new HttpPost(url);
        // add header
        post.setHeader("User-Agent", USER_AGENT);
        List<NameValuePair> urlParameters = new ArrayList<>();
        param.forEach((key, val) -> urlParameters.add(new BasicNameValuePair(key, val == null ? "" : val.toString())));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse response = client.execute(post);
        logger.debug("\nSending 'POST' request to URL : " + url);
        logger.debug("Post parameters : " + post.getEntity());
        logger.debug("Response Code : " + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        //
        logger.debug(result.toString());

        Map<String, String> openfalconCookies = getCookies(response);
        Map<String, Object> resultMap = JsonUtil.fromJson(result.toString(), Map.class);
        resultMap.put("cookies", openfalconCookies);
        return JsonUtil.toJson(resultMap);
    }

    /**
     * Gets cookies.
     *
     * @param httpResponse the http response
     * @return the cookies
     */
    public static Map<String, String> getCookies(HttpResponse httpResponse) {
        Map<String, String> cookies = new HashMap<>();
        Header[] headers = httpResponse.getHeaders("Set-Cookie");
        String headerstr = headers.toString();
        if (headers == null) {
            return cookies;
        }

        for (Header header : headers) {
            String cookie = header.getValue();
            String[] cookieValues = cookie.split(";");
            for (String cook : cookieValues) {
                String[] keyPair = cook.split("=");
                String key = keyPair[0].trim();
                String value = keyPair.length > 1 ? keyPair[1].trim() : "";
                cookies.put(key, value);
            }
        }
        return cookies;
    }

    /**
     * Send json post string.
     *
     * @param url  the url
     * @param body the body
     * @return the string
     * @throws Exception the exception
     */
    public static String post(String url, Object body) throws Exception {

        return post(url, JsonUtil.toJson(body), null);
    }
    /**
     * Send json post string.
     *
     * @param url  the url
     * @param body the body
     * @return the string
     * @throws Exception the exception
     */
    public static Map<String,Object> postWHeader(String url, Object body,Map<String,String> headers) throws Exception {

        Map<String,Object> resp = new HashMap<>();
        CloseableHttpClient client = getHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);
        if (headers != null) {
            headers.forEach(post::addHeader);
        }
        StringEntity entity = new StringEntity((String) body, "UTF-8");
        post.setEntity(entity);

        HttpResponse response = client.execute(post);
        Header header  = response.getFirstHeader("X-Subject-Token");

        logger.debug("\nSending 'POST' request to URL : " + url);
        logger.debug("Post parameters : " + post.getEntity());
        logger.debug("Response Code : " + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        logger.debug(result.toString());
        resp.put("body",result.toString());
        resp.put("header",header);
        return resp;
    }

    /**
     * Send post string.
     *
     * @param url     the url
     * @param body    the body
     * @param headers the headers
     * @return the string
     * @throws Exception the exception
     */
    public static String post(String url, String body, Map<String, String> headers) throws Exception {

        CloseableHttpClient client = getHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);
        if (headers != null) {
            headers.forEach(post::addHeader);
        }
        StringEntity entity = new StringEntity(body, "UTF-8");
        post.setEntity(entity);

        HttpResponse response = client.execute(post);
        logger.debug("\nSending 'POST' request to URL : " + url);
        logger.debug("Post parameters : " + post.getEntity());
        logger.debug("Response Code : " + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        logger.debug(result.toString());
        return result.toString();
    }

    /**
     * Send post string.
     *
     * @param url  the url
     * @param body the body
     * @return the string
     * @throws Exception the exception
     */
    public static String post(String url, String body) throws Exception {

        return post(url, body, null);
    }

    /**
     * Trans bean 2 map map.
     *
     * @param obj the obj
     * @return the map
     */
    public static Map<String, Object> transBean2Map(Object obj) {

        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);

                    map.put(key, value);
                }

            }
        } catch (Exception e) {
            logger.error("transBean2Map Error " + e);
        }

        return map;

    }

    /**
     * Gets basic authorization.
     *
     * @param user     the user
     * @param password the password
     * @return the basic authrization
     */
    public static String basicAuthorization(String user, String password) {
        Objects.requireNonNull(user);
        Objects.requireNonNull(password);
        return "Basic " + new String(Base64.getEncoder().encode((user + ":" + password).getBytes()));
    }

}
