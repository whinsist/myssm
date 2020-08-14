/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.net.ssl.SSLContext;

import lombok.Builder;
import lombok.Data;

/**
 * The type Http client util.
 */
public class HttpClientUtil {

    private static final String USER_AGENT = "Mozilla/5.0";

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    @Data
    @Builder
    public static final class HttpClientProxy {

        /**
         * 代理服务器
         **/
        private String proxyHost;

        /**
         * 代理端口
         **/
        private String proxyPort;
    }

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
        return get(url, headers, null);
    }

    /****/
    public static String get(String url, Map<String, String> headers, Integer timeout) throws Exception {
        return get(url, headers, timeout, null);
    }

    /****/
    public static String get(String url, Map<String, String> headers, Integer timeout, HttpClientProxy proxy)
            throws Exception {
        try (CloseableHttpClient client = getHttpClient();) {
            HttpGet request = new HttpGet(url);

            // add request header
            request.addHeader("User-Agent", USER_AGENT);
            if (headers != null) {
                headers.forEach(request::addHeader);
            }

            prepareConfig(request, timeout, proxy);

            HttpResponse response = client.execute(request);
            if(logger.isDebugEnabled()) {
                logger.debug("\nSending 'GET' request to URL : {}" , url);
                logger.debug("Response Code : {}" , response.getStatusLine().getStatusCode());
            }
            return getContentString(response);
        }
    }

    /**
     * 构造使用于Http、Https的Client
     *
     * @return HttpClient
     */
    private static CloseableHttpClient getHttpClient() {

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        //SOFATracer
        //SofaTracerHttpClientBuilder.clientBuilder(httpClientBuilder);

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
        // return HttpClientBuilder.create().setConnectionManager(connManager).build();
        return httpClientBuilder.setConnectionManager(connManager).disableAutomaticRetries()
                .build();
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

    public static String delete(String url, Map<String, String> headers) throws Exception {
        return delete(url, headers, null);
    }

    /**
     * Delete string.
     *
     * @param url     the url
     * @param headers the headers
     * @return the string
     * @throws Exception the exception
     */
    public static String delete(String url, Map<String, String> headers, HttpClientProxy proxy) throws Exception {
        try (CloseableHttpClient client = getHttpClient();) {
            HttpResponse response = deleteResponse(client, url, headers, null, proxy);
            if (logger.isDebugEnabled()) {
                logger.debug("\nSending 'DELETE' request to URL : {}", url);
                logger.debug("Response Code : {}", response.getStatusLine().getStatusCode());
            }
            return getContentString(response);
        }
    }

    public static int deleteWithoutContent(String url, Map<String, String> headers) throws Exception {
        return deleteWithoutContent(url, headers, null);
    }

    /**
     * Delete string.
     *
     * @param url     the url
     * @param headers the headers
     * @return the int
     * @throws Exception the exception
     */
    public static int deleteWithoutContent(String url, Map<String, String> headers, HttpClientProxy proxy)
            throws Exception {
        HttpResponse response = deleteResponse(url, headers, null, proxy);
        if(logger.isDebugEnabled()) {
            logger.debug("\nSending 'DELETE' request to URL : {}" , url);
            logger.debug("Response Code : {}" , response.getStatusLine().getStatusCode());
        }
        return response.getStatusLine().getStatusCode();
    }

    public static HttpResponse deleteResponse(String url, Map<String, String> headers) throws Exception {
        return deleteResponse(url, headers, null, null);
    }

    /**
     * deleteResponse
     **/
    public static HttpResponse deleteResponse(String url, Map<String, String> headers, Integer timeout,
                                              HttpClientProxy proxy) throws Exception {
        try (CloseableHttpClient client = getHttpClient();) {
            return deleteResponse(client, url, headers, timeout, proxy);
        }
    }

    private static HttpResponse deleteResponse(CloseableHttpClient client, String url, Map<String, String> headers,
                                               Integer timeout,
                                               HttpClientProxy proxy) throws Exception {
        HttpDelete request = new HttpDelete(url);

        // add request header
        request.addHeader("User-Agent", USER_AGENT);
        if (headers != null) {
            headers.forEach(request::addHeader);
        }

        prepareConfig(request, timeout, proxy);

        return client.execute(request);
    }

    public static String put(String url, Map<String, Object> param, Map<String, String> headers) throws Exception {
        return put(url, param, headers, null);
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
    public static String put(String url, Map<String, Object> param, Map<String, String> headers, HttpClientProxy proxy)
            throws Exception {
        try (CloseableHttpClient client = getHttpClient();) {
            HttpPut httpPut = new HttpPut(url);

            // add header
            httpPut.setHeader("User-Agent", USER_AGENT);
            if (headers != null) {
                headers.forEach(httpPut::addHeader);
            }
            List<NameValuePair> urlParameters = new ArrayList<>();
            param.forEach((key, val) -> urlParameters.add(new BasicNameValuePair(key, val == null ? "" : val.toString())));

            httpPut.setEntity(new UrlEncodedFormEntity(urlParameters));

            prepareConfig(httpPut, null, proxy);

            HttpResponse response = client.execute(httpPut);
            if(logger.isDebugEnabled()) {
                logger.debug("\nSending 'PUT' request to URL : {}" ,url);
                logger.debug("Put parameters : {}" , httpPut.getEntity());
                logger.debug("Response Code : {}" , response.getStatusLine().getStatusCode());
            }
            return getContentString(response);
        }
    }

    public static String put(String url, String body, Map<String, String> headers) throws Exception {
        return put(url, body, headers, null);
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
    public static String put(String url, String body, Map<String, String> headers, HttpClientProxy proxy)
            throws Exception {

        try (CloseableHttpClient client = getHttpClient();) {
            HttpPut httpPut = new HttpPut(url);

            // add header
            httpPut.setHeader("User-Agent", USER_AGENT);
            if (headers != null) {
                headers.forEach(httpPut::addHeader);
            }
            StringEntity entity = new StringEntity(body, "UTF-8");
            httpPut.setEntity(entity);

            prepareConfig(httpPut, null, proxy);

            HttpResponse response = client.execute(httpPut);
            if(logger.isDebugEnabled()) {
                logger.debug("\nSending 'PUT' request to URL : {}" , url);
                logger.debug("Put parameters : {}" , httpPut.getEntity());
                logger.debug("Response Code : {}" , response.getStatusLine().getStatusCode());
            }
            return getContentString(response);
        }
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

    public static String post(String url, Map<String, Object> param, Map<String, String> headers) throws
            Exception {
        return post(url, param, headers, null);
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
    public static String post(String url, Map<String, Object> param, Map<String, String> headers, HttpClientProxy proxy)
            throws Exception {

        try (CloseableHttpClient client = getHttpClient();) {
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

            prepareConfig(post, null, proxy);

            HttpResponse response = client.execute(post);
            if(logger.isDebugEnabled()) {
                logger.debug("\nSending 'POST' request to URL : {}" , url);
                logger.debug("Post parameters : {}" , post.getEntity());
                logger.debug("Response Code : {}" , response.getStatusLine().getStatusCode());
            }
            return getContentString(response);
        }
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
    public static String sendPostCookies(String url, Map<String, Object> param, String contentType, String
            cookies)
            throws Exception {

        try (CloseableHttpClient client = getHttpClient();) {
            HttpPost post = new HttpPost(url);

            // add header
            post.setHeader("User-Agent", USER_AGENT);
            post.setHeader("Content-Type", contentType);
            post.setHeader("Cookie", cookies);
            List<NameValuePair> urlParameters = new ArrayList<>();
            param.forEach((key, val) -> urlParameters.add(new BasicNameValuePair(key, val == null ? "" : val.toString())));

            post.setEntity(new UrlEncodedFormEntity(urlParameters, "UTF-8"));

            HttpResponse response = client.execute(post);
            if(logger.isDebugEnabled()) {
                logger.debug("\nSending 'POST' request to URL : {}" , url);
                logger.debug("Post parameters : {}" , post.getEntity());
                logger.debug("Response Code : {}" , response.getStatusLine().getStatusCode());
            }
            return getContentString(response);
        }
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
    public static String sendGetCookies(String url, Map<String, Object> param, String contentType, String
            cookies)
            throws Exception {

        try (CloseableHttpClient client = getHttpClient();) {
            HttpGet get = new HttpGet(url);

            // add header
            get.setHeader("User-Agent", USER_AGENT);
            get.setHeader("Content-Type", contentType);
            get.setHeader("Cookie", cookies);
            List<NameValuePair> urlParameters = new ArrayList<>();
            param.forEach((key, val) -> urlParameters.add(new BasicNameValuePair(key, val == null ? "" : val.toString())));

            HttpResponse response = client.execute(get);
            if(logger.isDebugEnabled()) {
                logger.debug("\nSending 'POST' request to URL : {}" , url);
                logger.debug("Response Code : {}" , response.getStatusLine().getStatusCode());
            }

            return getContentString(response);
        }
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

        try (CloseableHttpClient client = getHttpClient();) {
            HttpPost post = new HttpPost(url);
            // add header
            post.setHeader("User-Agent", USER_AGENT);
            List<NameValuePair> urlParameters = new ArrayList<>();
            param.forEach((key, val) -> urlParameters.add(new BasicNameValuePair(key, val == null ? "" : val.toString())));

            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            HttpResponse response = client.execute(post);
            if(logger.isDebugEnabled()) {
                logger.debug("\nSending 'POST' request to URL : {}" , url);
                logger.debug("Post parameters : {}" , post.getEntity());
                logger.debug("Response Code : {}" , response.getStatusLine().getStatusCode());
            }

            Map<String, String> openfalconCookies = getCookies(response);
            Map<String, Object> resultMap = JsonUtil.fromJson(getContentString(response), Map.class);
            resultMap.put("cookies", openfalconCookies);
            return JsonUtil.toJson(resultMap);
        }
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
     * Send post string.
     *
     * @param url the url
     * @param body the body
     *
     * @return the string
     *
     * @throws Exception the exception
     */
    public static String post(String url, String body) throws Exception {

        return post(url, body, null);
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

        return post(url, body, headers, null);
    }

    public static String post(String url, String body, Map<String, String> headers, Integer timeout) throws Exception {
        return post(url, body, headers, timeout, null);
    }

    /**
     * post
     **/
    public static String post(String url, String body, Map<String, String> headers, Integer timeout,
                              HttpClientProxy proxy) throws Exception {

        try (CloseableHttpClient client = getHttpClient();) {
            HttpResponse response = postResponse(client, url, body, headers, timeout, proxy);
            return getContentString(response);
        }
    }

    public static HttpResponse postResponse(String url, String body, Map<String, String> headers) throws Exception {
        return postResponse(url, body, headers, null, null);
    }

    /**
     * postResponse
     **/
    public static HttpResponse postResponse(String url, String body, Map<String, String> headers,
                                            Integer timeout,
                                            HttpClientProxy proxy) throws Exception {
        try (CloseableHttpClient client = getHttpClient();) {
            return postResponse(client, url, body, headers, timeout, proxy);
        }
    }

    private static HttpResponse postResponse(CloseableHttpClient client, String url, String body,
                                             Map<String, String> headers, Integer timeout,
                                             HttpClientProxy proxy) throws Exception {
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);
        if (headers != null) {
            headers.forEach(post::addHeader);
        }
        StringEntity entity = new StringEntity(body, "UTF-8");
        post.setEntity(entity);

        prepareConfig(post, timeout, proxy);

        HttpResponse response = client.execute(post);
        if (logger.isDebugEnabled()) {
            logger.debug("\nSending 'POST' request to URL : {}", url);
            logger.debug("Post parameters : {}", post.getEntity());
            logger.debug("Response Code : {}", response.getStatusLine().getStatusCode());
        }
        return response;
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
                if (!"class".equals(key)) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);

                    map.put(key, value);
                }

            }
        } catch (Exception e) {
            logger.error("transBean2Map Error {} ", e);
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

    public static String patch(String url, String bodyJsonStr, Map<String, String> headers) throws Exception {
        return patch(url, bodyJsonStr, headers, null);
    }

    /**
     * patch
     **/
    public static String patch(String url, String bodyJsonStr, Map<String, String> headers, HttpClientProxy proxy)
            throws Exception {

        try (CloseableHttpClient client = getHttpClient();) {
            HttpPatch patch = new HttpPatch(url);

            // add header
            patch.setHeader("User-Agent", USER_AGENT);
            if (headers != null) {
                headers.forEach(patch::addHeader);
            }

            patch.setEntity(new StringEntity(bodyJsonStr, Charset.forName("UTF-8")));

            prepareConfig(patch, null, proxy);

            HttpResponse response = client.execute(patch);
            if(logger.isDebugEnabled()) {
                logger.debug("\nSending 'PATCH' request to URL : {}" , url);
                logger.debug("Patch parameters : {}" , patch.getEntity());
                logger.debug("Response Code : {}" , response.getStatusLine().getStatusCode());
            }

            return getContentString(response);
        }
    }

    /**
     * 配置代理参数超时时间
     **/
    private static void prepareConfig(HttpRequestBase request, Integer timeout, HttpClientProxy proxy) {
        if (timeout != null || proxy != null) {
            RequestConfig.Builder builder = RequestConfig.custom();
            if (timeout != null && timeout > 0) {
                builder.setSocketTimeout(timeout)
                       .setConnectTimeout(timeout);
            }
            if (proxy != null) {
                HttpHost httpProxy = new HttpHost(proxy.getProxyHost(),
                                                  Integer.valueOf(proxy.getProxyPort()));
                builder.setProxy(httpProxy);
            }
            RequestConfig requestConfig = builder.build();
            request.setConfig(requestConfig);
        }
    }

    /**
     * 读取response数据
     **/
    private static String getContentString(HttpResponse response) throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        logger.debug(result.toString());
        return result.toString();
    }

    public static String getForCmdb(String url, Map<String, String> headers) {
        CloseableHttpClient client = getHttpClient();
        HttpGet request = new HttpGet(url);
        request.addHeader("User-Agent", USER_AGENT);
        if (headers != null) {
            headers.forEach(request::addHeader);
        }
        prepareConfig(request, null, null);
        BufferedReader reader = null;
        try {
            HttpResponse response = client.execute(request);
            reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (Exception e) {
            logger.error("getForCmdb error : {} ", e);
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
