package cn.com.cloudstar.rightcloud.framework.test.t001study.thread.produceconsumer.waitnotify;

import java.util.LinkedList;
import java.util.Random;


/**
 * @ClassName: Producer
 * @Description: 生产者
 * @author: yunche
 * @date: 2018/08/26
 */
public class Producer implements Runnable {

    private final LinkedList<Integer> list;

    /**
     * 缓冲区大小
     */
    private final int maxSize;

    public Producer(LinkedList list, int size) {
        this.list = list;
        maxSize = size;
    }

    @Override
    public void run() {
        try {
            while (true) {
                //模拟耗时1s
                Thread.sleep(1000);
                synchronized (list) {
                    if (list.size() == maxSize) {
                        System.out.println("缓冲区已满，正在等待消费者消费..." + System.currentTimeMillis());
                        list.wait();
                    } else {
                        list.add(produce());
                        list.notifyAll();
                    }
                }

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



