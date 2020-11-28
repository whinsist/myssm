package cn.com.cloudstar.rightcloud.framework.test.t001study.thread.produceconsumer.exam1;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @ClassName: Producer
 * @Description: 生产者
 * @author: yunche
 * @date: 2018/08/26
 */
public class Producer implements Runnable {

    private final BlockingQueue<Integer> queue;

    public Producer(BlockingQueue q) {
        this.queue = q;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // 模拟耗时1s
                //Thread.sleep(1000);
                queue.put(produce());

                System.out.println("size=" + queue.size());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int produce() {
        int n = new Random().nextInt(10000);
        System.out.println("Thread: " + Thread.currentThread().getName() + " produce: " + n);
        return n;
    }

}