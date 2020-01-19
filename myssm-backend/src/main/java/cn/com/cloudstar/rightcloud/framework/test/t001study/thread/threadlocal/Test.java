package cn.com.cloudstar.rightcloud.framework.test.t001study.thread.threadlocal;


/**
 * @author Hong.Wu
 * @date: 15:02 2020/01/08
 *
 * 假设每个线程都需要一个计数值记录自己做某件事做了多少次， 各线程运行时都需要改变自己的计数值而且相互不影响，
 * 那么ThreadLocal就是很好的选择，
 * 这里ThreadLocal里保存的当前线程的局部变量的副本就是这个计数值。
 *
 *
 * ThreadLocal不是用来解决共享变量的问题，也不是协调线程同步，他是为了方便各线程管理自己的状态而引用的一个机制。
    每个ThreadLocal内部都有一个ThreadLocalMap,他保存的key是ThreadLocal的实例，他的值是当前线程的局部变量的副本的值
 */
public class Test {
    public static void main(String [] args) {
        SeqCount seqCount = new SeqCount();

        SeqThread seqThread1 = new SeqThread(seqCount);
        SeqThread seqThread2 = new SeqThread(seqCount);
        SeqThread seqThread3 = new SeqThread(seqCount);
        SeqThread seqThread4 = new SeqThread(seqCount);

        seqThread1.start();
        seqThread2.start();
        seqThread3.start();
        seqThread4.start();


    }
}
