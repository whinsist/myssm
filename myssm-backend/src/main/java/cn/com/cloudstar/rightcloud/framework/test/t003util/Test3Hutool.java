package cn.com.cloudstar.rightcloud.framework.test.t003util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
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
//        testZip();
//        testUuid();

//        testStrUtil();
//        testIoUtil();

//        testUrlUtil();

//        testReflectUtil();

//        testRuntimeUtil();

//        testNumberUtil();
        
        testRandomUtil();
    }

    private static void testRandomUtil() {
        int randomInt = RandomUtil.randomInt(12, 100);
        RandomUtil.randomUUID();
        String s = RandomUtil.randomString("12323323ASDFSDFSDF", 5);
        System.out.println(s);

    }

    private static void testNumberUtil() {
        double te1 = 123456.123456;
        double te2 = 123456.128456;
        double add = NumberUtil.add(te1, te2);
        System.out.println(add);
        NumberUtil.round(te1, 4);//结果:123456.12
        NumberUtil.round(te2, 4);//结果:123456.13

        String s = NumberUtil.roundStr("23244.353434", 3);
        System.out.println(s);

        long c = 299792458;//光速
        String format = NumberUtil.decimalFormat(",###", c);//299,792,458
        System.out.println(format);

//        NumberUtil.isNumber //是否为数字
//        NumberUtil.isInteger //是否为整数
//        NumberUtil.isDouble //是否为浮点数
//        NumberUtil.isPrimes //是否为质数

        int[] ints = NumberUtil.generateRandomNumber(12, 3000, 10);
        Integer[] ints2 = NumberUtil.generateBySet(12, 3000, 10);
        System.out.println(ints);

        String s1 = NumberUtil.toStr(123.0023);
        System.out.println(s1);

        String binaryStr = NumberUtil.getBinaryStr(10);
        System.out.println(binaryStr);

        int xx = NumberUtil.binaryToInt(binaryStr);
        long ll = NumberUtil.binaryToLong (binaryStr);
        System.out.println(xx);
        System.out.println(ll);

        int compare = NumberUtil.compare(12.34, 12.44);
    }

    private static void testRuntimeUtil() {
        String str = RuntimeUtil.execForStr("ipconfig");
        System.out.println(str);
    }

    private static void testReflectUtil() {
        Method[] methods = ReflectUtil.getMethods(Test3Hutool.class);

        Method method = ReflectUtil.getMethod(Test3Hutool.class, "getId");
        Test3Hutool test3Hutool = ReflectUtil.newInstance(Test3Hutool.class);
        ReflectUtil.invoke(test3Hutool, "setA", 10);
    }

    private static void testUrlUtil() {
        String url = "http://www.hutool.cn//aaa/bbb";
        // 结果为：http://www.hutool.cn/aaa/bbb
        String normalize = URLUtil.normalize(url);
        System.out.println(normalize);

        url = "http://www.hutool.cn//aaa/\\bbb?a=1&b=2";
        // 结果为：http://www.hutool.cn/aaa/bbb?a=1&b=2
        normalize = URLUtil.normalize(url);
        System.out.println(normalize);

        String body = "366466 - 副本.jpg";
        // 结果为：366466%20-%20%E5%89%AF%E6%9C%AC.jpg
        String encode = URLUtil.encode(body);
        System.out.println(encode);

//        URLUtil.toURI();
//        ObjectUtil.isNull()
    }

    private static void testIoUtil() {
        BufferedInputStream in = FileUtil.getInputStream("d:/test.txt");
        BufferedOutputStream out = FileUtil.getOutputStream("d:/test2.txt");
        long copySize = IoUtil.copy(in, out, IoUtil.DEFAULT_BUFFER_SIZE);


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
        byte[] abyte = StrUtil.bytes("就像老鼠爱大米", "utf-8");
        byte[] bbyte = "就像老鼠爱大米".getBytes();
        System.out.println(abyte.length + "  " + bbyte.length);
        System.out.println(new String(abyte) + " ---" + new String(bbyte) + " -- " + new String("就像老鼠爱大米".getBytes()));

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
