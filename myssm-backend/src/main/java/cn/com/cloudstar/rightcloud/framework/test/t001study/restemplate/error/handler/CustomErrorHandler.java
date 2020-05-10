package cn.com.cloudstar.rightcloud.framework.test.t001study.restemplate.error.handler;

import com.google.common.base.Charsets;

import org.apache.commons.io.IOUtils;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import cn.hutool.core.util.CharsetUtil;

import cn.com.cloudstar.rightcloud.framework.common.util.stream.StreamUtil;

/**
 * @author Hong.Wu
 * @date: 3:52 2020/05/04 如果想像 HttpClient 一样直接从 Response 获取 HttpStatus 和 body 中的报错信息 而不抛出异常，可以通过下面的代码实现
 */
public class CustomErrorHandler implements ResponseErrorHandler {

    private ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler();

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

        try {
            String body2 = StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8);
            System.out.println(body2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 对body 的处理 (inputStream)
        String body = StreamUtil.convertStreamToString(response.getBody(), true);
        try {
            errorHandler.handleError(response);
        } catch (RestClientException e) {
            //HttpClientErrorException
            throw new RuntimeException(e.getMessage() + " : " + body);
        }
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return errorHandler.hasError(response);
    }


}
