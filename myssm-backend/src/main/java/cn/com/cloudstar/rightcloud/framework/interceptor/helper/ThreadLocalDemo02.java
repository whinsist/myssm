package cn.com.cloudstar.rightcloud.framework.interceptor.helper;


/**
 * 验证隔离性
 *
 *1.概述
 变量值的共享可以使用public 是static 变量的形式，所有的线程都使用同一个public static 变量。 如实现线程内的共享变量，jdk提供了ThreadLocal来解决这个问题。
 ThreadLocal主要解决就是每个线程绑定自己的值，可以将ThreadLocal类看成全局存放数据的盒子。
 */
public class ThreadLocalDemo02 {

    public static ThreadLocal demo = new ThreadLocal();

    public static void main(String[] args) {

        ThreadA a = new ThreadA();
        a.start();

        ThreadB b = new ThreadB();
        b.start();

        // 从上面执行的结果可以看的出来，每一个线程向ThreadLocal 中存值时，但是每个线程取出的都是自己线程的值。这也就验证的线程变量的隔离性。
        try {
            for (int i = 0; i < 100; i++) {
                demo.set("Main .." + (i + 1));
                System.out.println("Main  getValue ..." + demo.get());

                Thread.sleep(200);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

class ThreadA extends Thread {

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                ThreadLocalDemo02.demo.set("ThreadA" + (i + 1));
                System.out.println("ThreadA getValue " + ThreadLocalDemo02.demo.get());
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.run();
    }

}

class ThreadB extends Thread {

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {

                ThreadLocalDemo02.demo.set("ThreadB" + (i + 1));
                System.out.println("ThreadB getValue " + ThreadLocalDemo02.demo.get());
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.run();
    }

}
