package cn.com.cloudstar.rightcloud.framework.test.t001study.lambda;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import lombok.Data;

/**
 * @author Hong.Wu
 * @date: 21:37 2019/06/24
 *
 * https://www.cnblogs.com/new-life/p/11121599.html
 */
public class LambdaX1 {

    public static void main(String[] args) {
        //输入一个数，与100比较大小
        Comparable<Integer> comparable = new Comparable<Integer>() {
            @Override
            public int compareTo(Integer o1) {
                return Integer.compare(o1, 100);
            }
        };
        int r = comparable.compareTo(2);

        Comparable<Integer> comparable2 = (x) -> Integer.compare(x, 100);
        int r2 = comparable2.compareTo(2);

        /**
         * Java8内置的四大核心函数式接口
         *
         * Consumer<T> :消费型接口
         *              void acept(T t);
         *
         * Supplier<T> :供给型接口
         *              T get();
         *
         * Function<T,R> :函数型接口
         *              R apply(T t);
         *
         * Predicate<T> :断言型接口
         *              boolean test(T t);
         */
        //消费型接口Consumer，输入一个参数，对其进行打印输出
        Consumer<String> consumer = (s -> System.out.println("---"+s));
        //打印字符串
        consumer.accept("hehe");

        //供给型接口Supplier，返回指定字符串
        Supplier<String> supplier = () -> "rrrrrrrr";
        //获取字符串
        String s = supplier.get();
        System.out.println(s);


        //函数型接口Function，输入字符串，返回字符串长度
        Function<String, Integer> function = (str) -> {
            if (StringUtils.isBlank(str)) {
                return 0;
            }
            return str.length();
        };
        //获取字符串长度
        System.out.println(function.apply("hello world"));
        System.out.println(function.apply(null));



        //断言型接口Predicate，输入数字，判断是否大于0
        Predicate<Integer> predicate = (x) ->{return x > 0;};
        //获取判断结果
        System.out.println(predicate.test(100));
        System.out.println(predicate.test(-1));




    }


    @Data
    public static class User {

        private int id;
        private String name;
        private List<String> hobby;

        public User(int id, String name, List<String> hobby) {
            this.id = id;
            this.name = name;
            this.hobby = hobby;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return Objects.equals(id, user.id) &&
                    Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }


    }

    @Data
    public static class Book {

        private Integer id;
        private String name;
        private String type;
        private Double price;
        private LocalDate publishDate;

        public Book(Integer id, String name, String type, Double price, LocalDate publishDate) {
            this.id = id;
            this.name = name;
            this.type = type;
            this.price = price;
            this.publishDate = publishDate;
        }
    }
}
