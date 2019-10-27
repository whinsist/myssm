package cn.com.cloudstar.rightcloud.framework.testtreeutil;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;

/**
 * @author Hong.Wu
 * @date: 21:54 2019/05/13
 *
 *         http://hutool.mydoc.io/undefined#text_319389 文档地址： https://hutool.cn/docs/
 */
public class Test3Hutool {

    public static void main(String[] args) {
//        testConvert();

//        testFile();

//        testStrUtil();

//        testZip();
        testUuid();
    }

    private static void testUuid() {
        //生成的UUID是带-的字符串，类似于：a5c8a5e8-df2b-4706-bea4-08d0939410e3
        String uuid = IdUtil.randomUUID();

        //生成的是不带-的字符串，类似于：b17f24ff026d40949c85a24f4f375d42
        String simpleUUID = IdUtil.simpleUUID();
        System.out.println(uuid);
        System.out.println(simpleUUID);

        //生成类似：5b9e306a4df4f8c54a39fb0c
        String id = ObjectId.next();

        //方法2：从Hutool-4.1.14开始提供
        String id2 = IdUtil.objectId();
        System.out.println(id);
        System.out.println(id2);

        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        long snowflakeId = snowflake.nextId();
        System.out.println(snowflakeId);

    }

    private static void testZip() {
        ZipUtil.zip("e:/1.jpg", "e:/bbb/aaa.zip");
    }

    private static void testStrUtil() {
        byte[] aaas = StrUtil.bytes("aaa", "utf-8");
        byte[] bytes = "aaa".getBytes();
        System.out.println(aaas.length + "  " + bytes.length);

        String template = "{}爱{}，就像老鼠爱大米";
        String str = StrUtil.format(template, "我", "你"); //str -> 我爱你，就像老鼠爱大米
        System.out.println(str);
    }

    private static void testFile() {
        File file = FileUtil.file("e:/1.jpg");
        String type = FileTypeUtil.getType(file);
        System.out.println(type);

        FileReader fileReader = new FileReader("e:/sn.txt");
        String result = fileReader.readString();
        System.out.println(result);
        byte[] bytes = fileReader.readBytes();

        FileWriter writer = new FileWriter("test.properties");
        writer.write("test");
    }

    private static void testConvert() {
        int a = 1;
        //aStr为"1"
        String aStr = Convert.toStr(a);

        long[] b = {1, 2, 3, 4, 5};
        //bStr为："[1, 2, 3, 4, 5]"
        String bStr = Convert.toStr(b);

        long[] c = {1, 2, 3, 4, 5};
//结果为Integer数组
        Integer[] intArray2 = Convert.toIntArray(c);

        String ax = "2017-05-06";
        Date value = Convert.toDate(ax);

        Object[] ay = {"a", "你", "好", "", 1};
        List<?> list = Convert.convert(List.class, ay);
//从4.1.11开始可以这么用
        List<?> list2 = Convert.toList(a);

        String az = "我是一个小小的可爱的字符串";

//结果为："\\u6211\\u662f\\u4e00\\u4e2a\\u5c0f\\u5c0f\\u7684\\u53ef\\u7231\\u7684\\u5b57\\u7b26\\u4e32"
        String unicode = Convert.strToUnicode(az);
        System.out.println("unicode=" + unicode);

//结果为："我是一个小小的可爱的字符串"
        String raw = Convert.unicodeToStr(unicode);
    }
}
