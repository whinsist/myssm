package cn.com.cloudstar.rightcloud.framework.test.t001study.thread.volitile;

/**
 * @author Hong.Wu
 * @date: 9:44 2020/09/24
 */
public class TestVolite implements Runnable {
    private String name;
    // 如果又thread1 和thread2两个线程，thread1
    private static volatile boolean isRunning = true;
    //private volatile boolean isRunning = true;

    @Override
    public void run() {
        System.out.println("进入run了");
        while (isRunning == true) {
            System.out.println(name+" running..");
        }
        System.out.println("线程被停止了");
    }

    public void stop(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public TestVolite(String name){
        this.name = name;
    }

    public static void main(String[] args) throws InterruptedException {
        TestVolite mt = new TestVolite("线程1");
        Thread thread = new Thread(mt);
        thread.start();


        TestVolite mt2 = new TestVolite("线程2");
        Thread thread2 = new Thread(mt2);
        thread2.start();

        Thread.sleep(1000);
        //volatile修饰变量 可以实现多线程中可见性
        mt.stop(false);
        System.out.println("已赋值为false");
    }
}
