package cn.com.cloudstar.rightcloud.framework.test.t001study.thread.mostuse;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Hong.Wu
 * @date: 22:32 2020/07/22
 */
public class CallableTimeout {
    public static void main(String[] args)  {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> reqHttpTask = executorService.submit(() -> {
            // 发送http请求 （注意看控制台的Stop按钮是红色的，虽然主线程调用了shutdown(),但是子线程还未结束）
            System.out.println("后台查询中..");
            Thread.sleep(10000);
            return "{status:200}";
        });
        // 防止想线程池里面继续提交线程，
        executorService.shutdown();

        // 10秒未返回任务超时
        System.out.println("wait...");
        try {
            String result = reqHttpTask.get(3, TimeUnit.SECONDS);
            System.out.println("获取结果"+result);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("未获取到接口,因为已经超时.");
        }

    }


}
