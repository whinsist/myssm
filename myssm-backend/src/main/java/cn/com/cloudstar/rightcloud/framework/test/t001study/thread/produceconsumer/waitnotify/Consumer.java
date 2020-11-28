package cn.com.cloudstar.rightcloud.framework.test.t001study.thread.produceconsumer.waitnotify;

import java.util.LinkedList;

/**
 * @ClassName: Consumer
 * @Description: 消费者
 * @author: yunche
 * @date: 2018/08/26
 */
public class Consumer implements Runnable {

    private final LinkedList<Integer> list;

    public Consumer(LinkedList list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (list) {
                    //模拟耗时
                    Thread.sleep(1000);
                    if (list.isEmpty()) {
                        System.out.println(
                                "缓冲区已空，正在等待生产者生产..." + System.currentTimeMillis() + Thread.currentThread().getName());
                        list.wait();
                    } else {
                        consume(list.poll());
                        list.notifyAll();
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void consume(Integer n) {
        System.out.println("Thread:" + Thread.currentThread().getName() + " consume: " + n);
    }
}
