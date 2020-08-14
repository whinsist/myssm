package cn.com.cloudstar.rightcloud.framework.test.t001study.thread.mostuse;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author Hong.Wu
 * @date: 22:32 2020/07/22
 */
public class CallableTimeout {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> reqHttpTask = executorService.submit(() -> {
            // 发送http请求
            System.out.println("exec");
            Thread.sleep(50000);
            return "{status:200}";
        });
        // 10秒未返回任务超时
        System.out.println("wait...");
        String result = reqHttpTask.get(10, TimeUnit.SECONDS);
        System.out.println(result);
        executorService.shutdown();

    }


}
