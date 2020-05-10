package cn.com.cloudstar.rightcloud.framework.test.t001study.restemplate;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

import cn.com.cloudstar.rightcloud.framework.test.t001study.restemplate.error.handler.CustomErrorHandler;
import cn.com.cloudstar.rightcloud.framework.test.t001study.restemplate.error.handler.ThrowErrorHandler;
import cn.com.cloudstar.rightcloud.framework.test.t001study.restemplate.helper.MaaUtil;
import cn.com.cloudstar.rightcloud.framework.test.t001study.restemplate.helper.RestErrorUtil;
import cn.com.cloudstar.rightcloud.framework.test.t001study.restemplate.helper.vo.Fabric;
import cn.com.cloudstar.rightcloud.framework.test.t001study.restemplate.log.handler.RequestResponseLoggingInterceptor;

/**
 * @author Hong.Wu
 * @date: 15:04 2020/05/02
 */
public class TestRestTemplate4Maas {

    private static final String APIURL = "http://172.16.3.3:5240/MAAS/api/2.0";

    public static void main(String[] args) {
//        testGet();
        testPost();
//        testPut();
//        testDelete();
    }

    private static void testDelete() {
        String uri = "http://172.16.3.3:5240/MAAS/api/2.0/fabrics/{id}/";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new RequestResponseLoggingInterceptor());
//        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(new LinkedMultiValueMap<>(),
//                                                                                MaaUtil.getPostMethodHeaders());
//        HttpEntity<String> httpEntity = new HttpEntity<>("parameters", MaaUtil.getPostMethodHeaders());
        HttpEntity<String> httpEntity = new HttpEntity<>(MaaUtil.getPostMethodHeaders());
        Map<String, Object> args = Maps.newHashMap();
        args.put("id", 10);
        ResponseEntity<String> exchange = restTemplate.exchange(uri, HttpMethod.DELETE, httpEntity,
                                                                new ParameterizedTypeReference<String>() {
                                                                }, args);
        System.out.println(exchange.getBody());

//        final String uri = "http://localhost:8080/springrestexample/employees/{id}";
//        Map<String, String> params = new HashMap<>();
//        params.put("id", "2");
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.delete(uri, params);
    }

    private static void testPut() {
        String uri = "http://172.16.3.3:5240/MAAS/api/2.0/fabrics/{id}/";

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new RequestResponseLoggingInterceptor());
        MultiValueMap<String, Object> vpcUpdateParam = new LinkedMultiValueMap<>();
        vpcUpdateParam.add("name", "whtest-vpc2");

        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(vpcUpdateParam,
                                                                                MaaUtil.getPostMethodHeaders());
        ParameterizedTypeReference parameterizedType = new ParameterizedTypeReference<Fabric>() {
        };
        Map<String, Object> args = Maps.newHashMap();
        args.put("id", 9);
        ResponseEntity<Fabric> exchange = restTemplate.exchange(uri, HttpMethod.PUT, httpEntity, parameterizedType,
                                                                args);
        System.out.println(exchange.getBody());

//        final String uri = "http://localhost:8080/springrestexample/employees/{id}";
//        Map<String, String> params = new HashMap<>();
//        params.put("id", "2");
//        EmployeeVO updatedEmployee = new EmployeeVO(2, "New Name", "Gilly", "test@email.com");
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.put(uri, updatedEmployee, params);

        // Simple PUT
//        Foo updatedInstance = new Foo("newName");
//        updatedInstance.setId(createResponse.getBody().getId());
//        String resourceUrl =
//                fooResourceUrl + '/' + createResponse.getBody().getId();
//        HttpEntity<Foo> requestUpdate = new HttpEntity<>(updatedInstance, headers);
//        restTemplate.exchange(resourceUrl, HttpMethod.PUT, requestUpdate, Void.class);
    }

    private static void testPost() {
        // //生成一个设置了连接超时时间、请求超时时间、异常重试次数3次
//        RequestConfig config = RequestConfig.custom()
//                                            .setConnectionRequestTimeout(10000)
//                                            .setConnectTimeout(10000)
//                                            .setSocketTimeout(30000)
//                                            .build();
//        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create()
//                                                                                                              .setDefaultRequestConfig(
//                                                                                                                      config)
//                                                                                                              .setRetryHandler(
//                                                                                                                      new DefaultHttpRequestRetryHandler(
//                                                                                                                              3,
//                                                                                                                              false))
//                                                                                                              .build());
//        RestTemplate restTemplate = new RestTemplate(requestFactory);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new RequestResponseLoggingInterceptor());
        //restTemplate.setErrorHandler(new ThrowErrorHandler());
        //restTemplate.setErrorHandler(new CustomErrorHandler());


        String uri = "http://172.16.3.3:5240/MAAS/api/2.0/fabrics/";

        MultiValueMap<String, Object> vpcParam = new LinkedMultiValueMap<>();
        vpcParam.add("name", "whtest-vpc3");
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(vpcParam, MaaUtil.getPostMethodHeaders());
        // 如果HttpEntity用Map的话 header的content-type为application/json
        //Map<String, Object> vpcParam = new HashMap<>();
        //vpcParam.put("name", "whtest-vpc3");
        //HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(vpcParam, MaaUtil.getPostMethodHeaders());

        ResponseEntity<Fabric> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, httpEntity,
                                                                      new ParameterizedTypeReference<Fabric>() {
                                                                      });
        System.out.println(JSON.toJSONString(responseEntity.getBody()));

//        ResponseEntity<Fabric> responseEntity = restTemplate.postForEntity(uri, httpEntity, Fabric.class, new HashMap<>());
//        System.out.println(JSON.toJSONString(responseEntity.getBody()));
        //
//        uri = "http://172.16.3.3:5240/MAAS/api/2.0/subnets/";
//        MultiValueMap<String, Object> subnetParam = new LinkedMultiValueMap<>();
//        subnetParam.add("cidr ", "193.1.1.1/24");
//        subnetParam.add("vlan", 5042);
//        //subnetParam.add("fabric", 8);
//        subnetParam.add("name", "whtest-sub2");
//        subnetParam.add("gateway_ip", "");
//        subnetParam.add("dns_servers", "");
//        httpEntity = new HttpEntity<>(subnetParam, MaaUtil.getPostMethodHeaders());
//        ResponseEntity<Subnet> subnetResponse = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<Subnet>() {});
//        System.out.println(JSON.toJSONString(subnetResponse.getBody()));

//
//        EmployeeVO newEmployee = new EmployeeVO(-1, "Adam", "Gilly", "test@email.com");
//        EmployeeVO result = restTemplate.postForObject(uri, newEmployee, EmployeeVO.class);
//        System.out.println(result);
//
//        // Submit Form Data
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//        map.add("id", "1");
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
//        EmployeeVO result2 = restTemplate.postForObject(uri, request, EmployeeVO.class);
//        System.out.println(result2);
    }

    private static void testGet() {
        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.setRequestFactory(newBufferingClientHttpRequestFactory(clientHttpRequestFactory()));
//        restTemplate.setMessageConverters(Collections.singletonList(mappingJacksonHttpMessageConverter()));
        //restTemplate.setInterceptors(Collections.singletonList(new RequestResponseLoggingInterceptor()));
        restTemplate.getInterceptors().add(new RequestResponseLoggingInterceptor());

        //获取RestTemplate默认配置好的所有转换器 默认的MappingJackson2HttpMessageConverter在第7个 先把它移除掉 添加上GSON的转换器
        //List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        //messageConverters.remove(6);
        //messageConverters.add(6, new GsonHttpMessageConverter());

        String uri = "http://172.16.3.3:5240/MAAS/api/2.0/fabrics/";

        // GET
        //String result = restTemplate.getForObject(uri, String.class);

        // 使用RestTemplate定制HTTP头文件
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        headers.add("authorization", "OAuth oauth_nonce=\"1813961148\", oauth_token=\"wz2Gmz48tsMYguKGkH\", oauth_consumer_key=\"GArwNwK3X2dStNde2Y\", oauth_signature_method=\"PLAINTEXT\", oauth_timestamp=\"1588040666\", oauth_version=\"1.0\", oauth_signature=\"%26CzputXvJuZy2L2ryB7hfBNn2MRzqRK35\"");
//        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
//        ResponseEntity<String> result2 = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

        HttpHeaders headers2 = MaaUtil.getGetMethodHeaders();
        HttpEntity<String> entity2 = new HttpEntity<>("parameters", headers2);
        //1、直接返回string
        //ResponseEntity<String> result2Str = restTemplate.exchange(uri, HttpMethod.GET, entity2, String.class);
        //2、返回自定义对象
        //ParameterizedTypeReference parameterizedType = new ParameterizedTypeReference<Fabric[]>() {};
        //ResponseEntity<Fabric[]> response = restTemplate.exchange(uri, HttpMethod.GET, entity2, parameterizedType);
        //3、返回Map数组也可以
        //ParameterizedTypeReference parameterizedType = new ParameterizedTypeReference<Map[]>() {};
        //ResponseEntity<Map[]> response = restTemplate.exchange(uri, HttpMethod.GET, entity2, parameterizedType);
        //4、List也可以
        ParameterizedTypeReference parameterizedType = new ParameterizedTypeReference<ArrayList<Fabric>>() {
        };
        ResponseEntity<ArrayList<Fabric>> response = restTemplate.exchange(uri, HttpMethod.GET, entity2,
                                                                           parameterizedType);
        System.out.println(RestErrorUtil.hasError(response.getStatusCode()));
        System.out.println(JSON.toJSONString(response.getBody()));
        for (Fabric fabric : response.getBody()) {
            System.out.println(JSON.toJSONString(fabric));
        }

        // Get请求获取响应为一个对象
//        EmployeeListVO result3 = restTemplate.getForObject(uri, EmployeeListVO.class);

        // URL 参数
//        Map<String, String> params = new HashMap<>();
//        params.put("id", "1");
//        EmployeeVO result4 = restTemplate.getForObject(uri, EmployeeVO.class, params);
    }


    public static class EmployeeListVO {

    }

    @Data
    @AllArgsConstructor
    public static class EmployeeVO {

        private Integer id;
        private String name;
        private String lk;
        private String email;

    }
}
