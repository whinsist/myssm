package cn.com.cloudstar.rightcloud.framework.test.t001study.restemplate.error.handler;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 *
 //这种方式处理不了 ResponseEntity<Fabric> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<Fabric>() {});
 // 只能是返回String.class
 ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
 System.out.println(response.getBody());
 if (!responseEntity.getStatusCode().is2xxSuccessful()) {
 }
 */
public class ThrowErrorHandler implements ResponseErrorHandler {
 
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        // 返回false表示不管response的status是多少都返回没有错
        // 这里可以自己定义那些status code你认为是可以抛Error
        return false;
    }
 
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        // 这里面可以实现你自己遇到了Error进行合理的处理
    }
 
}