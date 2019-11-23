package cn.com.cloudstar.rightcloud.framework.test.t001study.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.thread.ThreadFactoryBuilder;

/**
 * @author Hong.Wu
 * @date: 10:38 2019/10/15
 */
public class ThreadPoolUseTest {

    public static void main(String[] args) {
//        正例1：
        //org.apache.commons.lang3.concurrent.BasicThreadFactory
//        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
//                                                                                   new Builder().namingPattern(
//                                                                                           "example-schedule-pool-%d")
//                                                                                                                   .daemon(true)
//                                                                                                                   .build());

//        正例2：
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNamePrefix("demo-pool-").build();

//Common Thread Pool
        ExecutorService pool = new ThreadPoolExecutor(5, 200,
                                                      0L, TimeUnit.MILLISECONDS,
                                                      new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory,
                                                      new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 10; i++) {
            pool.execute(() -> System.out.println(Thread.currentThread().getName()));
        }

        pool.shutdown();//gracefully shutdown


    }
}
