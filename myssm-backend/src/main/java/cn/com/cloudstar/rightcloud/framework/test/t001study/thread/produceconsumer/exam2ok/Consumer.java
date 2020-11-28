package cn.com.cloudstar.rightcloud.framework.test.t001study.thread.produceconsumer.exam2ok;

import java.util.concurrent.BlockingQueue;

import cn.hutool.core.util.RandomUtil;

/**
 * @author Hong.Wu
 * @date: 23:42 2020/09/23
 */
public class Consumer implements Runnable {

    private BlockingQueue<DataInfo> queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                DataInfo take = queue.take();
                // 数据处理
                Thread.sleep(RandomUtil.randomInt(0, 100));
                System.out.println(Thread.currentThread().getName() + "消费信息：" + take);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
