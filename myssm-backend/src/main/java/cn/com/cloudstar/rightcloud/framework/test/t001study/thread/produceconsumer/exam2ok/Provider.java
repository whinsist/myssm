package cn.com.cloudstar.rightcloud.framework.test.t001study.thread.produceconsumer.exam2ok;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import cn.hutool.core.util.RandomUtil;

/**
 * @author Hong.Wu
 * @date: 23:31 2020/09/23
 */
public class Provider implements Runnable {

    private String name;
    private BlockingQueue<DataInfo> queue;

    // 多线程间是否启动更新 有强制从主内存刷新的功能，即使返回线程状态
    private volatile boolean isRunning = true;

    // 一定是staic
    private static AtomicInteger count = new AtomicInteger();

    public Provider(BlockingQueue queue, String name) {
        this.queue = queue;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ":" + name + "===========" + isRunning);
        while (isRunning) {
            try {
                Thread.sleep(RandomUtil.randomInt(0, 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int id = count.getAndIncrement();
            DataInfo dataInfo = new DataInfo(String.valueOf(id), "数据" + id);
            System.out.println(Thread.currentThread().getName() + ":" + name + "生产信息：" + id + "放入缓存区..");
            try {
                if (!queue.offer(dataInfo, 2, TimeUnit.SECONDS)) {
                    System.out.println("缓冲区提交失败. [请检查是否有消费者或消费者消费速度比较慢]");
                    // do something 如重新提交
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        this.isRunning = false;
        System.out.println("isRunning--------" + ":" + name + isRunning);
    }
}
