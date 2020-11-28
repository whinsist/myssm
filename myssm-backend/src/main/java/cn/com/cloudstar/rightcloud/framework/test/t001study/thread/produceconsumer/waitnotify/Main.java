package cn.com.cloudstar.rightcloud.framework.test.t001study.thread.produceconsumer.waitnotify;

import java.util.LinkedList;

/**
 * @ClassName: Main
 * @Description: 测试类
 * @author: yunche
 * @date: 2018/08/26
 */
public class Main {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        Producer p = new Producer(list, 10);
        Consumer c1 = new Consumer(list);
        Consumer c2 = new Consumer(list);

        Thread producer = new Thread(p);
        producer.setName("生产者线程");
        Thread consumer1 = new Thread(c1);
        consumer1.setName("消费者1");
        Thread consumer2 = new Thread(c2);
        consumer2.setName("消费者2");

        producer.start();
        consumer1.start();
        consumer2.start();

    }
}
