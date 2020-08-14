package cn.com.cloudstar.rightcloud.framework.test.t001study.thread.mostuse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Hong.Wu
 * @date: 0:44 2020/07/22
 */
public class CallableFutureTest {

    public static void main(String[] args) throws Exception {
        // 1、创建一个线程
        RunnnerExc runnnerLx = new RunnnerExc("刘翔");

        // 2、创建线程池 里面有3个空线程
        ExecutorService executorService = Executors.newFixedThreadPool(3);

//        // 将对象放到线程池中，线程池会分配一个线程来执行对象的call方法
//        // Future接受线程返回值
//        Future<Integer> future = executorService.submit(runnnerLx);
//
//        // 还可以放2个线程
//        RunnnerExc runnnerLq = new RunnnerExc("laoqi");
//        RunnnerExc runnnerWg = new RunnnerExc("wg");
//        Future<Integer> resLq = executorService.submit(runnnerLq);
//        Future<Integer> resWg = executorService.submit(runnnerWg);
//
//        // 若任务数量大于 poolSize ，任务会被放在一个 queue 里顺序执行。
//        RunnnerExc runnnerAdd4 = new RunnnerExc("Add4");
//        Future<Integer> resAdd4 = executorService.submit(runnnerAdd4);
//        Integer resutl = future.get();
//        System.out.println("刘翔共跑：" + resutl);
//        Integer resut2 = resLq.get();
//        System.out.println("resLq共跑：" + resut2);
//        Integer resut3 = resWg.get();
//        System.out.println("resWg共跑：" + resut3);


        // 定义一个线程任务集合
        List<Callable<Integer>> tasks = new ArrayList<>();
        tasks.add(new RunnnerExc("laoqi"));
        tasks.add(new RunnnerExc("wg"));
        tasks.add(new RunnnerExc("Add4"));
        tasks.add(new RunnnerExc("Add11"));
        tasks.add(new RunnnerExc("Add22"));
        List<Future<Integer>> futures = executorService.invokeAll(tasks);
        // 这里提交的任务容器列表和返回的Future列表存在顺序对应的关系
        for (Future<Integer> future : futures) {
            System.out.println(future.get());
        }

        executorService.shutdown();
    }
}

class RunnnerExc implements Callable<Integer> {

    private String name;

    public RunnnerExc(String name) {
        this.name = name;
    }

    @Override
    public Integer call() throws Exception {
        int distince = 0;
        int speed = new Random().nextInt(100);
        for (int i = 1; i <= 5; i++) {
            Thread.sleep(1000);
            distince = speed * i;
            System.out.println(name + "已前进" + distince + "  speed=" + speed);
        }
        return distince;
    }
}
