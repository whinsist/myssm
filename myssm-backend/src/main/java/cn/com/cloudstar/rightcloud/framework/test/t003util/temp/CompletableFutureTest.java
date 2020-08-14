package cn.com.cloudstar.rightcloud.framework.test.t003util.temp;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Hong.Wu
 * @date: 7:34 2020/08/02
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("异步任务执行了");
        });
        future.get();
        System.out.println("主线任务执行了");

        //打印结果是：异步任务执行了过后主线任务才执行。  就是因为get()在一直等待。
        //那么如何解决我想要拿到结果，可以对结果进行处理，又不想被阻塞呢？
        //CompletableFuture 使一切变得可能

        //无返回值
        CompletableFuture future1 = CompletableFuture.runAsync(() -> {
            System.out.println("runAsync无返回值");
        });
        future1.get();

        //有返回值
        CompletableFuture future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync有返回值");
            return "111";
        });
        String s = (String) future2.get();
        //2、 异步任务执行完时的回调方法  whenComplete 和 exceptionally
        CompletableFuture.supplyAsync(() -> {
            int a = 10 / 0;
            return 1;
        }).whenComplete((r, e) -> {

            System.out.println(r);

        }).exceptionally(e -> {
            System.out.println(e);
            return 2;
        });

    }

}
