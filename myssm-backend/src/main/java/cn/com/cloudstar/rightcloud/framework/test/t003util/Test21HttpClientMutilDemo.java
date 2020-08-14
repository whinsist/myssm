package cn.com.cloudstar.rightcloud.framework.test.t003util;

import com.alibaba.fastjson.JSON;
import com.jcraft.jsch.Session;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.extra.ssh.JschUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import cn.com.cloudstar.rightcloud.framework.common.util.ldap.ActiveDirectory.User;
import cn.com.cloudstar.rightcloud.framework.common.util.stream.StreamUtil;

/**
 * @author Hong.Wu
 * @date: 4:24 2020/07/11
 */
public class Test21HttpClientMutilDemo {

    public static void main(String[] args) {
//        test1HttpUrlConnection();
        
        test2Hutool();
        
    }

    private static void test2Hutool() {
        //https://www.bookstack.cn/read/hutool/c749f7a45b09ce9d.md
        //GET请求
//        String url = "http://sso-project.rc.com:8088/employee-mock.json";
//        String content = HttpUtil.get(url);
//        System.out.println(content);

//        String result1= HttpUtil.get("https://www.baidu.com", CharsetUtil.CHARSET_UTF_8);

        //POST请求
//        HashMap<String, Object> paramMap = new HashMap<>();
//        paramMap.put("city", "北京");
//        String result1 = HttpUtil.post(url, paramMap);

//        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
//        HashMap<String, Object> paramMap = new HashMap<>();
//        paramMap.put("city", "北京");
//        String result3= HttpUtil.get("https://www.baidu.com", paramMap);
//
//
//        HashMap<String, Object> paramMap = new HashMap<>();
//        paramMap.put("city", "北京");
//        String result= HttpUtil.post("https://www.baidu.com", paramMap);
//        //文件上传只需将参数中的键指定（默认file），值设为文件对象即可，对于使用者来说，文件上传与普通表单提交并无区别
//        paramMap.put("file", FileUtil.file("D:\\face.jpg"));
//        String result= HttpUtil.post("https://www.baidu.com", paramMap)

        HttpRequest httpRequest = HttpUtil
                .createGet("http://sso-project.rc.com:8088/" + "employee-mock.json");

        HttpResponse httpResponse = httpRequest.execute();
        System.out.println(JSON.toJSONString(httpResponse));

        boolean ok = httpResponse.isOk();
        String body = httpResponse.body();
        boolean json = JSONUtil.isJson(body);
        JSONObject jsonObject = JSONUtil.parseObj(body);
        Boolean sucess = jsonObject.getBool("sucess");

//        JSONObject data = jsonObject.getJSONObject("data");
//        JSONArray records = data.getJSONArray("records");
        JSONArray jsonArray = jsonObject.getByPath("data.records", JSONArray.class);

        List<User> users = JSONUtil.toList(jsonArray, User.class);
//        List list = JSONUtil.toBean(jsonArray.toString(), List.class);







        System.out.println("---------");
    }
    private static void test1HttpUrlConnection() {
        try {
            String request_url = "http://www.baidu.com";
            String postData = "{}";
            String method = "GET";
            HttpURLConnection connection = (HttpURLConnection) new URL(request_url).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("x-amz-date", "ss");
            connection.setRequestProperty("Authorization", "ss");
            // 设定传送的内容类型application/x-java-serialized-object是可序列化的java对象
            // (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
            connection.setRequestProperty("Content-Type", "application/json");
            // http正文内，因此需要设为true, 默认情况下是false;
            connection.setDoOutput(true);
            // post发送body数据
            if (postData != null) {
                OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
                wr.write(postData);
                wr.flush();
            }
            int code = connection.getResponseCode();
            System.out.println("code : " + code);
            if (code == HttpURLConnection.HTTP_OK) {
                String content = StreamUtil.convertStreamToString(connection.getInputStream(), true);
                System.out.println(content);
            } else {
                System.out.println(connection.getResponseMessage() + connection.getResponseCode());
                String content = StreamUtil.convertStreamToString(connection.getErrorStream(), true);
                System.out.println(content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
