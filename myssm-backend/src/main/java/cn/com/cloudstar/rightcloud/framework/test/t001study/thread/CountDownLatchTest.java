package cn.com.cloudstar.rightcloud.framework.test.t001study.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CountDownLatchTest {

    public static void main(String[] args) throws Exception {

//        test1();
        test2();

    }

    private static void test2() throws Exception {

        long time1 = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        List<Callable<String>> tasks = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            tasks.add(() -> {
                Thread.sleep(1000);
                return "{code:200, id:" + Thread.currentThread() + "}";
            });
        }
        List<Future<String>> futures = executorService.invokeAll(tasks);
        List<String> result = new ArrayList<>();
        for (Future<String> future : futures) {
            String str = future.get();
            result.add(str);
        }
        long time2 = System.currentTimeMillis();
        System.out.println(result);
        System.out.println("用时" + (time2 - time1) / 1000 + "秒");
        executorService.shutdown();

        // 使用count
        long time3 = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(20);
        ExecutorService threadPool = Executors.newFixedThreadPool(4);
        List<String> result22 = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            Future<String> future = threadPool.submit(() -> {
                try {
                    Thread.sleep(1000);
                    return "{code:200, id:" + Thread.currentThread() + "}";
                } catch (Exception e) {
                    countDownLatch.countDown();
                }
                return "{code:200, id:" + Thread.currentThread() + "}";
            });
            String sss = future.get();
            result22.add(sss);
        }
        countDownLatch.await();

        long time4 = System.currentTimeMillis();
        System.out.println(result22);
        System.out.println("用时" + (time4 - time3) / 1000 + "秒");
        threadPool.shutdown();


    }

    private static void test1() {
        final CountDownLatch latch = new CountDownLatch(2);
        System.out.println("主线程开始执行…… ……");
        //第一个子线程执行
        ExecutorService es1 = Executors.newSingleThreadExecutor();
        es1.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println("子线程：" + Thread.currentThread().getName() + "执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        });
        es1.shutdown();

        //第二个子线程执行
        ExecutorService es2 = Executors.newSingleThreadExecutor();
        es2.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程：" + Thread.currentThread().getName() + "执行");
                latch.countDown();
            }
        });
        es2.shutdown();
        System.out.println("等待两个线程执行完毕…… ……");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("两个子线程都执行完毕，继续执行主线程");
    }
}