package cn.com.cloudstar.rightcloud.framework.testtreeutil;

import java.util.Date;
import java.util.List;

import cn.hutool.core.convert.Convert;

/**
 * @author Hong.Wu
 * @date: 21:54 2019/05/13
 *
 * http://hutool.mydoc.io/undefined#text_319389
 * 文档地址： https://hutool.cn/docs/
 */
public class Test3Hutool {
    public static void main(String[] args) {

        int a = 1;
        //aStr为"1"
        String aStr = Convert.toStr(a);

        long[] b = {1,2,3,4,5};
        //bStr为："[1, 2, 3, 4, 5]"
        String bStr = Convert.toStr(b);

        long[] c = {1,2,3,4,5};
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
        System.out.println("unicode="+unicode);

//结果为："我是一个小小的可爱的字符串"
        String raw = Convert.unicodeToStr(unicode);

    }
}
