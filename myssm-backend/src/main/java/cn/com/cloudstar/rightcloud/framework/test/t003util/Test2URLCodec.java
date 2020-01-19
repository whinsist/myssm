package cn.com.cloudstar.rightcloud.framework.test.t003util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.net.URLCodec;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.SortedMap;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.URLUtil;

/**
 *这个要看你实现什么标准的URLEncode的了。--------------------------------------------------
 * 在1994年订立的RFC1738中。对字符串中除了“-”、“_”、“.”之外的所有非字母数字字符都替换成百分号(%)后跟两位十六进制数。
 * 十六进制数中字母必须为大写。http://tools.ietf.org/html/

 *
 * java.net.URLEncoder
 * java.net.URLDecoder
 *
 * org.apache.commons.codec.net.URLCodec.encode
 * org.apache.commons.codec.net.URLCodec.decode
 *
 * cn.hutool.core.net.URLEncoder.encode
 * cn.hutool.core.util.URLUtil.decode
 */
public class Test2URLCodec {

    public static void main(String[] args) throws Exception {

        String url = "http://baidu.com" + "?name=你好1&x=y";
        String str = java.net.URLEncoder.encode(url, "utf-8");
        System.out.println(str);
        String str1 = java.net.URLDecoder.decode(str, "UTF-8");
        System.out.println(str1);


        String encode = new URLCodec().encode(url);
        System.out.println("url编码--------" + encode);
        String decode = new URLCodec().decode(encode);
        System.out.println("url解码--------" + decode);


        String encode1 = cn.hutool.core.net.URLEncoder.createDefault().encode(url, StandardCharsets.UTF_8);
        System.out.println("hutoolencode---" + encode1);

        String decode1 = URLUtil.decode(encode1, StandardCharsets.UTF_8);
        System.out.println("hutooldecode1---" + decode1);


        // 如果url参数值含有特殊字符时，需要使用 url 编码。 https://www.jb51.net/article/36838.htm
        // url出现了有+，空格，/，?，%，#，&，=等特殊符号的时候，可能在服务器端无法获得正确的参数值，如何是好？
        // 解决办法 将这些字符转化成服务器可以识别的字符，对应关系如下：
        // URL字符转义 +    URL 中+号表示空格 %2B  空格 URL中的空格可以用+号或者编码           %20

        // 正常http://ip:8080/?k1=v1&k2=v2  需要编码的是v1 v2
        String name = "张三";
        String sex = "f";
        String query =
                "http://baidu.com?name=" + java.net.URLEncoder.encode(name, StandardCharsets.UTF_8.name()) + "&x=y"
                        + java.net.URLEncoder.encode(sex, StandardCharsets.UTF_8.name());
        System.out.println(query);
        // 然后服务端获取时：后台controller 就能用get方式获取值
        //String param = URLDecoder.decode(param, "utf-8");



        //注意事项：https://www.jb51.net/article/109017.htm
        //URLEncoder should be the way to go. You only need to keep in mind to encode only the individual query string parameter name and/or value, not the entire URL,
        // for sure not the query string parameter separator character & nor the parameter name-value separator character =
        //String q = "random word 拢500 bank $";
        //String url = "http://example.com/query?q=" + URLEncoder.encode(q, "UTF-8");
        //URLEncoder 必须 仅仅 编码 参数 或者参数的值，不能编码整个 url，也不能一起对 param=value 进行编码。而是应该： param=URLEncode(value, "utf-8")
        //或者 URLEncode(param, "utf-8")=URLEncode(value, "utf-8")
        //因为 url 中的 & 和 = 他们是作为参数之间 以及 参数和值之间的分隔符的。如果一起编码了，就无法区分他们了


        // 获取Java支持的全部字符集
        SortedMap<String, Charset> map = Charset.availableCharsets();
        for (String alias : map.keySet()) {
            // 输出字符集的别名
            //System.out.println(alias);
        }

//		String md5Hex = DigestUtils.md5Hex( "test中国".getBytes("UTF-8"));
//		System.out.println("md5Hex--------"+md5Hex);
//		String sha1Hex = DigestUtils.sha1Hex( "test中国".getBytes("UTF-8"));
//		System.out.println("sha1Hex--------"+sha1Hex);

    }
}
