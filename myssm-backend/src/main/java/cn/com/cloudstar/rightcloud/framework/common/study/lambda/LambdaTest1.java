package cn.com.cloudstar.rightcloud.framework.common.study.lambda;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author Hong.Wu
 * @date: 21:37 2019/06/24
 */
public class LambdaTest1 {
    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("100");
        BigDecimal b = a.add(new BigDecimal("200"));
        System.out.println("a=" + a);
        System.out.println("b=" + b);


        Date date = new Date();
        System.out.println("date="+date);
        date.setYear(date.getYear() +1);
        System.out.println("date="+date);

        //==>LocalDate  LocalDateTime
        //LocalDate localDate = new LocalDate();

        // lambda表达式 替换匿名内部类 抽象类只有一个方法
        // 函数式编程 参数类型的自动推断 代码量少、简洁 更容易并行
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("todo");
            }
        }).start();
        new Thread(() -> {
            System.out.println("todo");
        }).start();
        // 前提：熟悉泛型   函数式接口   多练   多用Stream API
        // 任何有函数式接口的地方都可以是lambda表达式
        // 函数式接口：只有一个抽象方法（Object的方法除外）的接口
        // 函数式接口：用FunctionalInterface注解标识
        // 函数式接口：如Runnable Callable Comparator ...
        // rt.jar java.util.function [Bi:同  Binary:二元化 Suppplier:供应商]
        // Suppplier：代表输出     Consumer：代表输入    BiConsumer：代表两个输入
        // Function：代表一个输入一个输出 UnaryOperator：输入输出相同类型
        // BinaryOperator 两个输入一个输出  BinaryOperator：两个输入一个输出 输入输出相同类型










    }

}
