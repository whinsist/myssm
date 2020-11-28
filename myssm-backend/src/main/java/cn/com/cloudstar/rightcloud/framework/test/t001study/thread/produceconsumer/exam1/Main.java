package cn.com.cloudstar.rightcloud.framework.test.t001study.thread.produceconsumer.exam1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @ClassName: Main
 * @Description: 测试类
 * @author: yunche
 * @date: 2018/08/26
 */
public class Main {

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1000);
        Thread producer = new Thread(new Producer(queue));
        producer.setName("生产者线程");
        producer.start();


//        Thread consumer1 = new Thread(new Consumer(queue));
//        consumer1.setName("消费者1");
//        Thread consumer2 = new Thread(new Consumer(queue));
//        consumer2.setName("消费者2");
//        consumer1.start();
//        consumer2.start();

    }
}