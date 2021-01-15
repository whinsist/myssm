package cn.com.cloudstar.rightcloud.framework.test.t003util.httpclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import cn.com.cloudstar.rightcloud.framework.common.util.stream.StreamUtil;

/**
 * @author Hong.Wu
 * @date: 15:02 2021/01/15
 */
public class TestJdkHttpURLConnection {

    public static void main(String[] args) {

        String s = doGet("http://www.baidu.com");
        System.out.println(s);
    }

    /**
     * POST请求
     *
     * @param requestUrl 请求地址
     * @param param 请求数据
     */
    public String doPost(String requestUrl, String param) {
        return doPost(requestUrl, param, "application/json");
    }

    /**
     *
     * application/x-www-form-urlencoded 对应参数格式:请求参数应该是 name1=value1&name2=value2的形式
     * application/json 对应参数格式:json字符串
     */
    public String doPost(String requestUrl, String param, String contentType) {
        HttpURLConnection connection = null;
        OutputStream os = null;
        String result = null;

        try {
            /** 创建远程url连接对象 */
            URL url = new URL(requestUrl);

            /** 通过远程url对象打开一个连接，强制转换为HttpUrlConnection类型 */
            connection = (HttpURLConnection) url.openConnection();

            /** 设置连接方式：POST */
            connection.setRequestMethod("POST");
            /** 设置连接主机服务器超时时间：15000毫秒 */
            connection.setConnectTimeout(15000);
            /** 设置读取远程返回的数据时间：60000毫秒 */
            connection.setReadTimeout(60000);

            /** 设置是否向httpUrlConnection输出，设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个 */
            // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
            // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
            connection.setDoInput(true);

            /** 设置通用的请求属性 */
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Content-Type", contentType);

            /** 通过连接对象获取一个输出流 */
            os = connection.getOutputStream();
            /** 通过输出流对象将参数写出去/传输出去，它是通过字节数组写出的 */
            // 若使用os.print(param);则需要释放缓存：os.flush();即使用字符流输出需要释放缓存，字节流则不需要
            if (param != null && param.length() > 0) {
                os.write(param.getBytes());
            }

            /** 请求成功：返回码为200 */
            if (connection.getResponseCode() == 200) {
                /** 通过连接对象获取一个输入流，向远程读取 */

                InputStream is = connection.getInputStream();
                result = StreamUtil.convertStreamToString(is, true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /** 关闭资源 */
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            /** 关闭远程连接 */
            // 断开连接，最好写上，disconnect是在底层tcp socket链接空闲时才切断。如果正在被其他线程使用就不切断。
            // 固定多线程的话，如果不disconnect，链接会增多，直到收发不出信息。写上disconnect后正常一些
            connection.disconnect();

            System.out.println("--------->>> POST request end <<<----------");
        }

        return result;
    }


    /**
     * GET请求
     *
     * @param requestUrl 请求地址
     */
    public static String doGet(String requestUrl) {
        HttpURLConnection connection = null;
        String result = null;

        try {
            /** 创建远程url连接对象 */
            URL url = new URL(requestUrl);

            /** 通过远程url对象打开一个连接，强制转换为HttpUrlConnection类型 */
            connection = (HttpURLConnection) url.openConnection();

            /** 设置连接方式：GET */
            connection.setRequestMethod("GET");
            /** 设置连接主机服务器超时时间：15000毫秒 */
            connection.setConnectTimeout(15000);
            /** 设置读取远程返回的数据时间：60000毫秒 */
            connection.setReadTimeout(60000);

            /** 设置通用的请求属性 */
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            /** 发送GET方式请求，使用connet方法建立和远程资源之间的实际连接即可 */
            connection.connect();

            /** 获取所有相应头字段 */
            Map<String, List<String>> map = connection.getHeaderFields();
            for (String key : map.keySet()) {
                System.out.println("header:" + key + "---------->" + map.get(key));
            }

            /** 请求成功：返回码为200 */
            if (connection.getResponseCode() == 200) {
                /** 通过connection连接，获取输入流 */
                InputStream is = connection.getInputStream();
                result = StreamUtil.convertStreamToString(is, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /** 关闭远程连接 */
            // 断开连接，最好写上，disconnect是在底层tcp socket链接空闲时才切断。如果正在被其他线程使用就不切断。
            // 固定多线程的话，如果不disconnect，链接会增多，直到收发不出信息。写上disconnect后正常一些
            connection.disconnect();
        }

        return result;
    }


}
