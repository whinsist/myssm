package cn.com.cloudstar.rightcloud.framework.test.t001study.ininitialException;

import java.util.ArrayList;
import java.util.List;

public class StaticParamsSingle {

    private static StaticParamsSingle sps = buildStaticParams();
    //private static StaticParamsSingle sps = new StaticParamsSingle();
    private static int NUM_A = getA();
    private static int NUM_B = getB();
    private static List<String> LIST_A = getListA();

    private StaticParamsSingle() {
        System.out.println("初始化构造方法");
    }

    private static StaticParamsSingle buildStaticParams() {
        if (sps == null) {
            sps = new StaticParamsSingle();
        }

        int result = NUM_A + NUM_B;
        System.out.println("result is:" + result);
        LIST_A.add("haha");
        return sps;
    }

    public static StaticParamsSingle getInstance() {
        return sps;
    }

    private static int getA() {
        System.out.println("初始化A");
        return 5;
    }

    private static int getB() {
        System.out.println("初始化B");
        return 10;
    }

    private static List<String> getListA() {
        System.out.println("初始化List");
        return new ArrayList<String>();
    }

    public static void main(String args[]) {
        StaticParamsSingle.getInstance();

//        sps变量初始化中使用到的其他的变量，但其他的静态变量还未初始化，故而在sps初始化时就会产生异常：
//
//        初始化构造方法
//        result is:0
//        Exception in thread "main" java.lang.ExceptionInInitializerError
//        Caused by: java.lang.NullPointerException
//        at com.lang.ininitialException.StaticParamsSingle.buildStaticParams(StaticParamsSingle.java:25)
//        at com.lang.ininitialException.StaticParamsSingle.<clinit>(StaticParamsSingle.java:8)

    }
}