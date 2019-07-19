package cn.com.cloudstar.rightcloud.framework.common.study.lambda;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import lombok.Data;

/**
 * @author Hong.Wu
 * @date: 21:37 2019/06/24
 */
public class LambdaTest3 {
    public static void main(String[] args) {
        // 截取
        Set<Integer> collect = Stream.iterate(1, x -> x + 1)
                                     .limit(50)
                                     .sorted((a, b) -> a - b)
                                     .limit(10)
                                     .skip(10)
                                     .collect(Collectors.toSet());
        System.out.println(collect);

        //转换
        String str = "11,22,11";

        int sum = Arrays.stream(str.split(",")).map(x -> Integer.valueOf(x)).mapToInt(x -> x.intValue()).sum();
        int sum2 = Arrays.stream(str.split(",")).mapToInt(x -> Integer.valueOf(x)).sum();
        int sum3 = Arrays.stream(str.split(",")).mapToInt(Integer::valueOf).sum();
        System.out.println(sum);


        str = "admin,nginx,tomcat";

        List<Pu> collect1 = Arrays.stream(str.split(",")).map(Pu::new).collect(Collectors.toList());
        List<Pu> collect2 = Arrays.stream(str.split(",")).map(Pu::build).collect(Collectors.toList());
        //Arrays.stream(str.split(",")).peek(x->{}).forEach(System.out::print);

        Optional<Integer> any = Arrays.asList(11, 22, 3, 4, 6, 6, 7).stream().filter(x -> x % 2 == 0).sorted((a,b) -> b-a).findFirst();
        System.out.println(any.get());

        Map<String, Pu> map =  collect2.stream().collect(Collectors.toMap(Pu::getName, v->v, (k1,k2) -> k1));


        // 并行流 顺序流 parallel sequential
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "5");
        String pp = System.getProperty("java.util.concurrent.ForkJoinPool.common.parallelism");
        System.out.println("pp---------"+pp);


        Optional<Integer> max = Stream.iterate(1, x -> x + 1).limit(10).parallel().peek(x -> {
            System.out.println(Thread.currentThread().getName());
        }).max(Integer::compare);
        System.out.println("max=" + max);


        Instant start = Instant.now();
        LongStream.rangeClosed(0, 10000000000L).parallel().reduce(0, Long :: sum);
        Instant end = Instant.now();
        System.out.println("用时："+ Duration.between(start, end).getSeconds());

    }
    @Data
    public static class Pu {
        private String name;
        public static Pu build(String x) {
            return new Pu(x);
        }
        public Pu(String x){

        }
    }
}
