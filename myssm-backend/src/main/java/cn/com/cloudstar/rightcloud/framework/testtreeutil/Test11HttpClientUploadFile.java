package cn.com.cloudstar.rightcloud.framework.testtreeutil;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Test11HttpClientUploadFile {

    private static final String uploadUrl = "http://localhost:8081/api/v1/attach/upload";


    public static void main(String[] args) throws Exception {
//        upload("E:\\testfiles\\test.pdf");

//        https://www.cnblogs.com/foxting/p/7895724.html
//        upload2();
//        upload();
//        post();


//        InputStreamEntity reqEntity = new InputStreamEntity(new FileInputStream("E:\\testfiles\\test.pdf"), -1, ContentType.APPLICATION_OCTET_STREAM);
        InputStreamEntity reqEntity = new InputStreamEntity(new FileInputStream("E:\\testfiles\\test.pdf"), -1, ContentType.MULTIPART_FORM_DATA);
//        reqEntity.setContentType("application/x-www-form-urlencoded");
//        reqEntity.setChunked(true);
//        HttpEntity reqEntity = MultipartEntityBuilder.create()
//                                                     .addPart("file", new FileBody(new File("E:\\testfiles\\test.pdf")))
//                                                     .build();


        HttpPost httpPost = new HttpPost(uploadUrl);
        httpPost.setEntity(reqEntity);

        CloseableHttpClient client =  HttpClients.createDefault();
        HttpResponse response = client.execute(httpPost);
        System.out.println(response.getStatusLine().getStatusCode());

    }

    public static void upload2() throws Exception{
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse httpResponse = null;
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000000).build();
        HttpPost httpPost = new HttpPost(uploadUrl);
        httpPost.setConfig(requestConfig);
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        //multipartEntityBuilder.setCharset(Charset.forName("UTF-8"));

        //File file = new File("F:\\Ken\\1.PNG");
        //FileBody bin = new FileBody(file);

        File file = new File("E:\\testfiles\\test.pdf");

        //multipartEntityBuilder.addBinaryBody("file", file,ContentType.create("image/png"),"abc.pdf");
        //当设置了setSocketTimeout参数后，以下代码上传PDF不能成功，将setSocketTimeout参数去掉后此可以上传成功。上传图片则没有个限制
        //multipartEntityBuilder.addBinaryBody("file",file,ContentType.create("application/octet-stream"),"abd.pdf");
        multipartEntityBuilder.addBinaryBody("file",file);
        //multipartEntityBuilder.addPart("comment", new StringBody("This is comment", ContentType.TEXT_PLAIN));
        multipartEntityBuilder.addTextBody("comment", "this is comment");
        HttpEntity httpEntity = multipartEntityBuilder.build();
        httpPost.setEntity(httpEntity);

        httpResponse = httpClient.execute(httpPost);
        HttpEntity responseEntity = httpResponse.getEntity();
        int statusCode= httpResponse.getStatusLine().getStatusCode();
        System.out.println("statusCode="+statusCode);
        if(statusCode == 200){
            BufferedReader reader = new BufferedReader(new InputStreamReader(responseEntity.getContent()));
            StringBuffer buffer = new StringBuffer();
            String str = "";
            while(null != (str = reader.readLine())) {
                buffer.append(str);
            }

            System.out.println(buffer.toString());
        }

        httpClient.close();
        if(httpResponse!=null){
            httpResponse.close();
        }
    }

    public static void upload() {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        try {
            HttpPost httppost = new HttpPost(uploadUrl);

            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000).build();
            httppost.setConfig(requestConfig);

            FileBody bin = new FileBody(new File("E:\\testfiles\\test.pdf"));
            StringBody comment = new StringBody("This is comment", ContentType.TEXT_PLAIN);

            HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("file", bin).addPart("comment", comment).build();

            httppost.setEntity(reqEntity);

            System.out.println("executing request " + httppost.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                System.out.println(response.getStatusLine());
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    String responseEntityStr = EntityUtils.toString(response.getEntity());
                    System.out.println(responseEntityStr);
                    System.out.println("Response content length: " + resEntity.getContentLength());
                }
                EntityUtils.consume(resEntity);
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void upload(String localFile){
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClients.createDefault();

            // 把一个普通参数和文件上传给下面这个地址 是一个servlet
            HttpPost httpPost = new HttpPost(uploadUrl);

            // 把文件转换成流对象FileBody
            FileBody bin = new FileBody(new File(localFile));

            StringBody userName = new StringBody("Scott", ContentType.create(
                    "text/plain", Consts.UTF_8));
            StringBody password = new StringBody("你好", ContentType.create(
                    "text/plain", Consts.UTF_8));

            HttpEntity reqEntity = MultipartEntityBuilder.create()
                                                         // 相当于<input type="file" name="file"/>
                                                         .addPart("file", bin)

                                                         // 相当于<input type="text" name="userName" value=userName>
                                                         .addPart("userName", userName)
                                                         .addPart("saveDir", password)
                                                         .build();

            httpPost.setEntity(reqEntity);

            // 发起请求 并返回请求的响应
            response = httpClient.execute(httpPost);

            System.out.println("The response value of token:" + response.getFirstHeader("token"));

            // 获取响应对象
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                // 打印响应长度
                System.out.println("Response content length: " + resEntity.getContentLength());
                // 打印响应内容
                System.out.println(EntityUtils.toString(resEntity, Charset.forName("UTF-8")));
            }

            // 销毁
            EntityUtils.consume(resEntity);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(response != null){
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(httpClient != null){
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 普通POST请求
    public static String post() throws Exception{
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse httpResponse = null;
        HttpPost httpPost = new HttpPost(uploadUrl);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(20000).setSocketTimeout(22000).build();
        httpPost.setConfig(requestConfig);


        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("saveDir","令狐冲"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Charset.forName("UTF-8"));
        httpPost.setEntity(entity);
        httpResponse = httpClient.execute(httpPost);
        HttpEntity responseEntity = httpResponse.getEntity();
        if(responseEntity!=null){
            String content = EntityUtils.toString(responseEntity,"UTF-8");
            System.out.println(content);
        }

        if(httpResponse!=null){
            httpResponse.close();
        }
        if(httpClient!=null){
            httpClient.close();
        }
        return null;
    }

}
