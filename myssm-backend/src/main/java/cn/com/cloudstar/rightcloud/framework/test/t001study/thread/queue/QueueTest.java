package cn.com.cloudstar.rightcloud.framework.test.t001study.thread.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author Hong.Wu
 * @date: 22:10 2020/09/23
 */
public class QueueTest {
    public static void main(String[] args) {
        // 异步
        ConcurrentLinkedDeque queue = new ConcurrentLinkedDeque();

        // 阻塞队列 有界
        BlockingQueue blockingQueue = new ArrayBlockingQueue(1024);
        // 阻塞队列 无界队列Integer.MAX_VALUE
        LinkedBlockingDeque linkedBlockingDeque = new LinkedBlockingDeque();

        List<String> list = new ArrayList();
        System.out.println(list.size());
    }


}
