package cn.com.cloudstar.rightcloud.framework.test.t001study.thread.threadlocal;

/**
 * 〈一句话功能简述〉;
 * 〈功能详细描述〉
 *
 * @author jxx
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class TestThreadLocal {

    public static void main(String[] args) {

        ThreadLocal<Integer> threadLocal = new MyThreadLocal();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    threadLocal.set(threadLocal.get() + 1);
                    System.out.println("线程1：" + threadLocal.get());
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    threadLocal.set(threadLocal.get() + 1);
                    System.out.println("线程2：" + threadLocal.get());
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    threadLocal.set(threadLocal.get() + 1);
                    System.out.println("线程3：" + threadLocal.get());
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
        // 有结果可知个线程之间对ThreadLocal的操作互不影响。
    }

    private static class MyThreadLocal extends ThreadLocal<Integer> {

        @Override
        protected Integer initialValue() {
            return 0;
        }
    }

}