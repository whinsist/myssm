package cn.com.cloudstar.rightcloud.framework.test.t001study.lambda;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

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

        // 为什么不推荐使用Date  Date会发送变化
        Date date = new Date();
        System.out.println("date=" + date);
        date.setYear(date.getYear() + 1);
        System.out.println("源对象发生变化date=" + date);

        //==>LocalDate  LocalDateTime
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.plusDays(1));
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.plusDays(1);

        //获取秒数
        Long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        //获取毫秒数
        Long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println(second + " - " + milliSecond + "  - " + System.currentTimeMillis());

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

        // 对于集合比较使用Collections.sort();
        // 集合排序 注意原有两种方式不传比较器(是一个自然排序) 传比较器(new一个你匿名内部类)
        List<String> list = Arrays.asList("d", "b", "c", "a");
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                return str1.compareTo(str2);
            }
        });
        // lambada str1 str2并没有指定类型 自动推断为String
        Collections.sort(list, (str1, str2) -> str1.compareTo(str2));

        // 前提：1、熟悉泛型   2、函数式接口   3、多练  多用Stream API
        // 使用场景：任何有函数式接口的地方都可以是lambda表达式
        // 函数式接口：只有一个抽象方法（Object的方法除外）的接口
        // 函数式接口：用FunctionalInterface注解标识
        // 函数式接口：如：Runnable Callable Comparator ...
        // rt.jar java.util.function [Bi:同  Binary:二元化 Suppplier:供应商]
        // Suppplier：代表输出
        // Consumer：代表输入
        // BiConsumer：代表两个输入
        // Function：一个输入一个输出(一般输入输出类型不一样)
        // UnaryOperator：一个输入一个输出(输入输出类型一样)
        // BiFunction：两个输入一个输出(一般输入输出类型不一样)
        // BinaryOperator：两个输入一个输出(输入输出类型一样)

    }

}
