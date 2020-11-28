package cn.com.cloudstar.rightcloud.framework.test.t001study.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Hong.Wu
 * @date: 23:01 2020/04/02
 */
public class S1Source {
    public static void main(String[] args) {
        // 创建流
        // 1数组创建
        String[] arr = {"1", "2", "3"};
        Stream<String> arr1 = Stream.of(arr);
        // 2集合创建
        Stream<String> stream = Arrays.asList("1", "2", "3").stream();

        Stream<Integer> generate = Stream.generate(() -> 1);

        // UnaryOperator 一个输入一个输出
        Stream<Integer> iterate = Stream.iterate(1, x -> x + 1);


        IntStream chars = "xxx".chars();

        List<Integer> list = Arrays.asList(12, 2, 3);
//        Collections.sort(list, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1.compareTo(o2);
//            }
//        });
        Collections.sort(list,   Integer::compareTo);
        System.out.println(list);


    }   
}
