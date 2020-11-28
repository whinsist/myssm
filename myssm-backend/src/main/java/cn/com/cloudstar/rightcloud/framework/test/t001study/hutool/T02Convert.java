package cn.com.cloudstar.rightcloud.framework.test.t001study.hutool;

import java.util.Date;
import java.util.List;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.CharsetUtil;

/**
 * @author Hong.Wu
 * @date: 11:19 2020/08/26
 */
public class T02Convert {

    public static void main(String[] args) {
//        转换为字符串：
        int a = 1;
//aStr为"1"
        String aStr = Convert.toStr(a);

        long[] b = {1, 2, 3, 4, 5};
//bStr为："[1, 2, 3, 4, 5]"
        String bStr = Convert.toStr(b);
//        Copy to clipboardErrorCopied
//        转换为指定类型数组：
        String[] bx = {"1", "2", "3", "4"};
//结果为Integer数组
        Integer[] intArray = Convert.toIntArray(bx);

        long[] c = {1, 2, 3, 4, 5};
//结果为Integer数组
        Integer[] intArray2 = Convert.toIntArray(c);

//        转换为日期对象：
        String at = "2017-05-06";
        Date value = Convert.toDate(at);
//                转换为集合
        Object[] ax = {"a", "你", "好", "", 1};
        List<?> list = Convert.convert(List.class, ax);
//从4.1.11开始可以这么用
        List<?> listx = Convert.toList(a);


        Object[] aa = { "a", "你", "好", "", 1 };
        List<String> lists = Convert.convert(new TypeReference<List<String>>() {}, aa);
        System.out.println(lists);


        //半角转全角： //结果为："１２３４５６７８９"
        String sbc = Convert.toSBC("123456789");
        System.out.println(sbc);

        //全角转半角 结果为"123456789"
        String dbc = Convert.toDBC("１２３４５６７８９");
        System.out.println(dbc);


        // 转为16进制（Hex）字符串
        String hex = Convert.toHex("我是一个小小的可爱的字符串", CharsetUtil.CHARSET_UTF_8);
        // 将16进制（Hex）字符串转为普通字符串:
        String raw = Convert.hexToStr(hex, CharsetUtil.CHARSET_UTF_8);
        System.out.println(hex);
        System.out.println(raw);

        //Unicode和字符串转换
        String unicode = Convert.strToUnicode("我是一个小小的可爱的字符串");
        String rawx = Convert.unicodeToStr(unicode);
        System.out.println(unicode);
        System.out.println(rawx);

        // 编码转换
        //转换后result为乱码
        String result = Convert.convertCharset("我不是乱码", CharsetUtil.UTF_8, CharsetUtil.ISO_8859_1);
        String rawr = Convert.convertCharset(result, CharsetUtil.ISO_8859_1, "UTF-8");
        System.out.println(result);
        System.out.println(rawr);

        //结果为："陆万柒仟伍佰伍拾陆元叁角贰分"
        String digitUppercase = Convert.digitToChinese(67556.32D);


    }
}
