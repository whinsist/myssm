package cn.com.cloudstar.rightcloud.framework.test.t001study.lambda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Data;

/**
 * @author Hong.Wu
 * @date: 21:37 2019/06/24
 */
public class LambdaCommon {

    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "xx", "服务器", 134.2D, LocalDate.parse("2019-01-03")));
        books.add(new Book(2, "xx", "服务器", 53.2D, LocalDate.parse("2019-01-02")));
        books.add(new Book(3, "xx", "后端", 53.2D, LocalDate.parse("2019-01-03")));
        // 场景：有个User的List，需要将所有的用户ID取出来
        List<Integer> idList = books.stream().map(item -> item.getId()).collect(Collectors.toList());
        // 场景：有个User的List，需要将所有的用户ID取出来，还得注意去重
        books.stream().map(item -> item.getId()).distinct().collect(Collectors.toList());
        // 场景：有个User的List，需要将其放到HashMap里，key为用户ID，value为该User
        Map<Integer, Book> collect = books.stream().collect(Collectors.toMap(Book::getId, item -> item));
        // 场景：有个User的List，需要将其放到HashMap里，key为用户ID，value为用户名称
        Map<Integer, String> collect1 = books.stream().collect(Collectors.toMap(Book::getId, Book::getName));
        // 场景：有个User的List，需要将其放到HashMap里，key为用户ID，value为该User。User可能会重复，也就是ID会重复。按照上面的做法会导致如下错误：
        Map<Integer, String> collect2 = books.stream()
                                             .collect(Collectors.toMap(Book::getId, Book::getName,
                                                                       (oldV, newV) -> newV));
        // 场景：有个User的List，需要按照id的大小进行排序
        List<Book> collect3 = books.stream()
                                   .sorted((b1, b2) -> b1.getId().compareTo(b2.getId()))
                                   .collect(Collectors.toList());

        // flatMap示例 单词提取
        // 将集合中的字符串中单词提取出来，不考虑特殊字符
        List<String> words = Arrays.asList("hello c++", "hello java", "hello python");
        List<String> result = words.stream().map(word -> word.split(" ")).flatMap(Arrays::stream).distinct().collect(
                Collectors.toList());
        List<String> result2 = words.stream()
                                    .map(word -> word.split(" "))
                                    .flatMap(item -> Arrays.stream(item))
                                    .distinct()
                                    .collect(Collectors.toList());

        // 元素抽取
        // 初始化测试数据
        User user1 = new User(1, "张三", Arrays.asList("java", "c", "音乐"));
        User user2 = new User(2, "李四", Arrays.asList("c++", "c", "游戏"));
        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        List<String> result3 = users.stream().map(item -> item.hobby).flatMap(item -> item.stream()).distinct().collect(
                Collectors.toList());
        List<String> result4 = users.stream().map(item -> item.hobby).flatMap(Collection::stream).collect(
                Collectors.toList());
        System.out.println(result4);


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
