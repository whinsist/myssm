package cn.com.cloudstar.rightcloud.framework.test.t003util.review;

/**
 * @author Hong.Wu
 * @date: 20:59 2020/02/14
 */
public class Rev05ThreadExec {
    public static void main(String[] args) {
        test1();
        test2();


    }

    private static void test1() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("The JVM exit success!");
        }));
        System.out.println("main start...");
        Thread t = new Thread() {
            public void run() {
                System.out.println("thread run...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
                System.out.println("thread end.");
            }
        };
        // 会看到start()方法内部调用了一个private native void start0()方法，native修饰符表示这个方法是由JVM虚拟机内部的C代码实现的，不是由Java代码实现的。
        t.start();
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {}
        System.out.println("main end...");
    }

    private static void test2() {
    }

}
