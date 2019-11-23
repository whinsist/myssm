package cn.com.cloudstar.rightcloud.framework.test.t001study.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Hong.Wu
 * @date: 23:10 2019/09/01
 * https://blog.csdn.net/qq_38270106/article/details/90168316
 *
 * 结论：不要新的Thread（），采用线程池
非线程池的缺点：
每次创建性能消耗大
无序，缺乏管理。容易无限制创建线程，引起OOM和死机
 */
public class WhyUsePool {

    public static int times = 10000;//100,1000,10000
    public static ArrayBlockingQueue arrayWorkQueue = new ArrayBlockingQueue(1000);

    public static ExecutorService threadPool = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, arrayWorkQueue,
                                                                      new ThreadPoolExecutor.DiscardOldestPolicy());
    public static void main(String[] args) {
        createNewThread();
        useThreadPool();
    }   

    public static void useThreadPool() {
         Long start = System.currentTimeMillis();
         for (int i = 0; i < times; i++) {
             threadPool.execute(new Runnable() {
                @Override
                public void run() {
                     //System.out.println("说点什么吧...");
                 }
             
            });
         }
        threadPool.shutdown();
        while (true) {
            // 若关闭后所有任务都已完成，则返回true。注意除非首先调用shutdown或shutdownNow，否则isTerminated永不为true。
             if (threadPool.isTerminated()) {
                 Long end = System.currentTimeMillis();
                 System.out.println("threadpool ---" +(end - start));
                 break;
             }
         }
     }
  
             

    public static void createNewThread() {
         Long start = System.currentTimeMillis();
         for (int i = 0; i < times; i++) {
             new Thread() {
                @Override
                public void run() {
                     //System.out.println("说点什么吧...");
                 }
            }.start();
         }
         Long end = System.currentTimeMillis();
        System.out.println("newThread ---" +(end - start));
     }


}

