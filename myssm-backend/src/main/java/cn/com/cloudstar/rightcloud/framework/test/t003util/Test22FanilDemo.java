package cn.com.cloudstar.rightcloud.framework.test.t003util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Hong.Wu
 * @date: 7:09 2020/08/02
 * https://www.jianshu.com/p/0ed8b186e5fc
 *
 *
 * final的作用
被final修饰的类不可以被继承
被final修饰的方法不可以被重写
被final修饰的变量不可变

用了map、list、数组、StringBuilder 、StringBuffer .它们的内容是可以修改的。
String和基本数据类型修饰的变量。同样final表示地址不能修改，但是地址的存储跟常量的值有关，给他重新赋值会指向另外一个对象，地址就改变了。

>>>>>>所以被final修饰的变量，不可变的是变量的引用，而不是变量的内容,
可以参考lambda表达式中使用的变量应该是final或者有效的final
https://blog.csdn.net/qq_36221788/article/details/100584500

 */
public class Test22FanilDemo {
    private static final Map<String, Object> NAME = new HashMap<>(16);
    private static final List<String> LIST = new ArrayList<>(10);
    private static final String[] TYPE = new String[15];
    private static final StringBuilder SB = new StringBuilder("22");
    private static final StringBuffer SBU = new StringBuffer("22");
    private static final String S = "44";
    private static final int num = 44;
    private static final String S1 = new String();



    public void setName() {
        NAME.put("1", "maomao");
        LIST.add("11");
        TYPE[0] = "1";
        SB.append("12");
        SBU.append("32");
        System.out.println(NAME);
        System.out.println(LIST);
        System.out.println(Arrays.asList(TYPE));
        System.out.println(SB.toString());
        System.out.println(SBU.toString());
        //LIST = new ArrayList<>();

        //S = "SSSS";
//        String x = new String("111");
//        S = x;
//        num = 44;
//        S1 = x;

    }

    public static void main(String[] args) {
        Test22FanilDemo finalDemo = new Test22FanilDemo();
        finalDemo.setName();
    }


}
