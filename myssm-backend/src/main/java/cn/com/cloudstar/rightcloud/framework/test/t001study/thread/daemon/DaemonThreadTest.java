package cn.com.cloudstar.rightcloud.framework.test.t001study.thread.daemon;

import java.util.concurrent.TimeUnit;

/**
 * @author Hong.Wu
 * @date: 10:32 2020/02/16
 */
public class DaemonThreadTest {



    public static void main(String[] args) throws InterruptedException {

        testNotDaemon();



        //testDaemon();
    }

    private static void testNotDaemon() throws InterruptedException {
        // 在主函数new一个非守护线程
        Thread thread = new Thread(() -> {
            // 模拟非守护线程不退出的情况
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("非守护线程running ...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("The main thread exit...");
    }

    private static void testDaemon() throws InterruptedException {
        // 设置一个钩子线程 在jvm退出是输出日志
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("The JVM exit success!");
        }));

        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("非守护线程running ...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 通过 setDaemon(true)将该线程为守护线程；
        thread.setDaemon(true);
        thread.start();
        TimeUnit.SECONDS.sleep(2);

        // 主线程即将退出
        // 可以看到，当主线程退出时，JVM 会随之退出运行，守护线程同时也会被回收，即使你里面是个死循环也不碍事。
        System.out.println("The main thread exit...");
    }
}
