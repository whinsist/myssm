package cn.com.cloudstar.rightcloud.framework.test.t001study.thread.produceconsumer.exam2ok;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Hong.Wu
 * @date: 23:34 2020/09/23
 */
public class Main {
    public static void main(String[] args) {
        // 生产者生产数据 -> 放入队列中 ->消费者一直等待消费数据
        BlockingQueue<DataInfo> queue = new ArrayBlockingQueue<>(100);
        // 生产者
        Provider provider1 = new Provider(queue, "生产者1");
        Provider provider2 = new Provider(queue, "生产者2");
        Provider provider3 = new Provider(queue, "生产者3");
        // 消费者
        Consumer consumer1 = new Consumer(queue);
        Consumer consumer2 = new Consumer(queue);
        Consumer consumer3 = new Consumer(queue);

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(provider1);
        executorService.execute(provider2);
        executorService.execute(provider3);

        executorService.execute(consumer1);
        executorService.execute(consumer2);
        executorService.execute(consumer3);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 生成者停止生产消息
        provider1.stop();
        provider2.stop();
//        provider3.stop();


    }


}
