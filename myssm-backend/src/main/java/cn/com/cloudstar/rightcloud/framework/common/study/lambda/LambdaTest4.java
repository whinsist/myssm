package cn.com.cloudstar.rightcloud.framework.common.study.lambda;

import com.google.common.cache.Weigher;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Data;

/**
 * @author Hong.Wu
 * @date: 21:37 2019/06/24
 */
public class LambdaTest4 {
    public static void main(String[] args) {
        
        String query = "item=1&userId=100&name=xx&token=12333";
        Map<String, String> params = Arrays.stream(query.split("&")).map(s -> s.split("="))
                                        .collect(Collectors.toMap(str -> str[0], str -> str[1]));
        System.out.println("params="+params);


        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "xx", "服务器",134.2D, LocalDate.parse("2019-01-03")));
        books.add(new Book(2, "xx", "服务器",53.2D, LocalDate.parse("2019-01-02")));
        books.add(new Book(3, "xx", "后端",53.2D, LocalDate.parse("2019-01-03")));

        List<Integer> ids = books.stream().map(book -> book.getId()).collect(Collectors.toList());

        String str1 = books.stream().map(book -> book.getId() + "").collect(Collectors.joining(","));
        String str2 = books.stream().map(book -> book.getId() + "").collect(Collectors.joining(",", "(", ")"));
        System.out.println(str1 +"  "+str2);

        List<String> types = books.stream().map(book -> book.getType()).distinct().collect(Collectors.toList());
        Set<String> types2 = books.stream().map(book -> book.getType()).distinct().collect(Collectors.toSet());
        System.out.println(types);
        System.out.println(types2);

        List<Book> sorts1 = books.stream()
//                                  .sorted((a, b) -> Double.compare(a.getPrice(), b.getPrice()))
                                  .sorted(Comparator.comparingDouble(Book::getPrice))
                                  .collect(Collectors.toList());
        System.out.println(sorts1);

        //Comparator<Book> comparator = (a, b) -> Double.compare(a.getPrice(), b.getPrice());
        Comparator<Book> comparator = Comparator.comparing(Book::getPrice);


        List<Book> sorts2 = books.stream()
                                .sorted(comparator.reversed())
                                .collect(Collectors.toList());
        System.out.println("sorts2="+sorts2);

        List<Book> sorts3 = books.stream()
                                 .sorted(comparator.thenComparing((a,b) -> a.getPublishDate().isAfter(b.getPublishDate()) ? -1 : -1))
                                 .collect(Collectors.toList());
        System.out.println("sorts3="+sorts3);

        List<Book> sorts11 = books.stream()
                                .sorted(Comparator.comparing(Book::getPrice))
                                .collect(Collectors.toList());
        System.out.println("sorts11="+sorts11);

        List<Book> sorts22 = books.stream()
                                  .sorted(Comparator.comparing(Book::getPrice).reversed())
                                  .collect(Collectors.toList());
        System.out.println("sorts22="+sorts22);

        List<Book> sorts33 = books.stream()
//                                  .sorted(Comparator.comparing(Book::getPrice).reversed().thenComparing((a,b) -> a.getPublishDate().isAfter(b.getPublishDate()) ? -1 : 1))
                                  .sorted(Comparator.comparing(Book::getPrice).reversed().thenComparing(Comparator.comparing(Book::getPublishDate).reversed()))
                                  .collect(Collectors.toList());
        System.out.println("sorts33="+sorts33);

        List<Map<Integer, Book>> mapList = books.stream().map(book -> {
            Map<Integer, Book> map = new HashMap<>();
            map.put(book.getId(), book);
            return map;
        }).collect(Collectors.toList());

        Map<Integer, Book> map1 = books.stream().collect(Collectors.toMap(book -> book.getId(), book -> book));
        Map<Integer, Book> map2 = books.stream().collect(Collectors.toMap(Book :: getId, book -> book));

        Double avg = books.stream().collect(Collectors.averagingDouble(Book::getPrice));
        System.out.println("avg="+avg);

        Optional<Book> max = books.stream().collect(Collectors.maxBy(Comparator.comparing(Book::getPrice)));
        System.out.println("max="+max.get());

        Optional<Book> min = books.stream().collect(Collectors.minBy(Comparator.comparing(Book::getPrice)));
        System.out.println("min="+min.get());

        Map<String, List<Book>> collect = books.stream().collect(Collectors.groupingBy(Book::getType));

        // 每种类型的数量
        Map<String, Long> counting = books.stream()
                                          .collect(Collectors.groupingBy(Book::getType, Collectors.counting()));
        System.out.println("counting="+counting);

        // 每种类型的总价格
        Map<String, Double> summingDouble = books.stream()
                                            .collect(Collectors.groupingBy(Book::getType,
                                                                           Collectors.summingDouble(Book::getPrice)));
        // 每种类型的平均价格
        Map<String, Double> averagingDouble = books.stream()
                                            .collect(Collectors.groupingBy(Book::getType,
                                                                           Collectors.averagingDouble(Book::getPrice)));
        System.out.println("averagingDouble="+averagingDouble);

        // 每种类型最贵的
        Map<String, Optional<Book>> typeMax = books.stream()
                                                    .collect(Collectors.groupingBy(Book::getType, Collectors.maxBy(
                                                            Comparator.comparing(Book::getPrice))));
        System.out.println("typeMax="+typeMax);

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
