package cn.com.cloudstar.rightcloud.framework.test.t001study.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Hong.Wu
 * @date: 0:01 2019/09/02
 *
 *
 *         void shutdown() 启动一次顺序关闭，执行以前提交的任务，但不接受新任务。若已经关闭，则调用没有其他作用。 抛出：SecurityException - 如果安全管理器存在并且关闭，此
 *         ExecutorService 可能操作某些不允许调用者修改的线程（因为它没有保持RuntimePermission("modifyThread")）， 或者安全管理器的 checkAccess 方法拒绝访问。
 *
 *         boolean isTerminated() 若关闭后所有任务都已完成，则返回true。注意除非首先调用shutdown或shutdownNow，否则isTerminated永不为true。返回：若关闭后所有任务都已完成，则返回true。
 */
public class TerminatedTest {

    public static ExecutorService exe = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS,
                                                               new ArrayBlockingQueue(1000),
                                                               new ThreadPoolExecutor.DiscardOldestPolicy());

    public static void main(String[] args) {
        moreThread();
    }

    public static void moreThread() {
        try {
            int threadNum = 0;
            for (int i = 0; i < 10; i++) {
                threadNum++;

                final int currentThreadNum = threadNum;
                exe.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("子线程[" + currentThreadNum + "]开启");
                            Thread.sleep(1000 * 10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            System.out.println("子线程[" + currentThreadNum + "]结束");
                        }
                    }
                });
            }

            System.out.println("已经开启所有的子线程");
            exe.shutdown();
            System.out.println("shutdown()：启动一次顺序关闭，执行以前提交的任务，但不接受新任务。");
            while (true) {
                if (exe.isTerminated()) {
                    System.out.println("所有的子线程都结束了！");
                    break;
                }
                Thread.sleep(1000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("主线程结束");
        }
    }
}
