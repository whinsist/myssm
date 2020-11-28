package cn.com.cloudstar.rightcloud.framework.test.t001study.thread.produceconsumer.exam1;

import java.util.concurrent.BlockingQueue;

/**
 * @ClassName: Consumer
 * @Description: 消费者
 * @author: yunche
 * @date: 2018/08/26
 */
public class Consumer implements Runnable {

    private final BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue q) {
        this.queue = q;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //模拟耗时
                Thread.sleep(2000);
                consume(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void consume(Integer n) {
        System.out.println("Thread:" + Thread.currentThread().getName() + " consume: " + n);
    }
}