package cn.com.cloudstar.rightcloud.framework.test.t003util;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;

import cn.com.cloudstar.rightcloud.framework.test.t003util.rest.RequestResponseLoggingInterceptor;

/**
 * @author Hong.Wu
 * @date: 16:10 2020/04/12
 */
public class Test20RestTemplate {
    public static void main(String[] args) {
        final String uri = "http://172.16.3.3:5240/MAAS/api/2.0/account/prefs/sshkeys/";
        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(clientHttpRequestFactory()));
//        restTemplate.setMessageConverters(Collections.singletonList(mappingJacksonHttpMessageConverter()));
        restTemplate.setInterceptors( Collections.singletonList(new RequestResponseLoggingInterceptor()) );






        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HashMap<Class, ParameterizedTypeReference> typesManaged = new HashMap<>();
        typesManaged.put(String.class, new ParameterizedTypeReference<String>() {
        });

//        String token = "";
//        String[] tokenParts = token.split(":");
//        String consumerKey = tokenParts[0];
//        String accessKey = tokenParts[1];
//        String accessSecret = tokenParts[2];
//        restTemplate = new OauthClientConfig().restTemplate(consumerKey,
//                                                            "",
//                                                            accessKey,
//                                                            accessSecret,
//                                                            false, 6000);
//
//        HttpEntity<String> httpEntity = new HttpEntity<>("parameters", headers);
//        ParameterizedTypeReference<String> responseType = new ParameterizedTypeReference<String>() {
//        };
//        ResponseEntity exchange = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, responseType, typesManaged.get(String.class));
//        System.out.println(exchange);
//
//        String result = restTemplate.postForObject(uri, new HttpEntity<String>(headers),String.class);
//        System.out.println(result);
    }

}
