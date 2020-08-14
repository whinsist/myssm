package cn.com.cloudstar.rightcloud.framework.test.t001study.rateLimite;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import cn.hutool.core.io.FileUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import cn.com.cloudstar.rightcloud.framework.common.exception.RetryException;
import cn.com.cloudstar.rightcloud.framework.common.util.HttpClientUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.JsonUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.RetryUtil;

/**
 * @author Hong.Wu
 * @date: 15:59 2020/08/01
 */
@Slf4j
public class RateLimiterTest {

    private static final int RETRYCOUNT = 1;


    public static void main(String[] args) throws Exception {
//        testNoRateLimiter();
//        testWithRateLimiter();

        testWithRateLimiterVisitRest();

        ;

    }


    public static void testNoRateLimiter() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            System.out.println("call execute.." + i);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void testWithRateLimiter() {
        long start = System.currentTimeMillis();
        RateLimiter limiter = RateLimiter.create(10.0);
        // 每秒不超过10个任务被提交
        for (int i = 0; i < 20; i++) {
            limiter.acquire();
            // 请求RateLimiter, 超过permits会被阻塞
            System.out.println("call execute.." + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static void testWithRateLimiterVisitRest() throws Exception {
        // 开启线程数量
        //定义固定长度的线程池  防止线程过多
        ExecutorService execservice = Executors.newFixedThreadPool(10);
        List<Future<List<FooItem>>> tasks = new ArrayList<>();//添加任务

        for (int page = 1; page <= 20; page++) {
            SaleForceOrderThread saleForceOrderThred = new SaleForceOrderThread(page);
            Future<List<FooItem>> submit = execservice.submit(saleForceOrderThred);
            tasks.add(submit);
        }

        //
        List<FooItem> resultList = Lists.newCopyOnWriteArrayList();
        for (Future<List<FooItem>> future : tasks) {
            List<FooItem> fooItems = future.get();
            resultList.addAll(fooItems);
        }

        System.out.println(JSON.toJSONString(resultList));

        execservice.shutdown();
    }

    public static class SaleForceOrderThread implements Callable<List<FooItem>> {

        private int page;

        @Override
        public List<FooItem> call() throws Exception {
            //System.out.println(String.format("线程：%s 当前处理：第%s页", Thread.currentThread(), page));
//            lambda表达式是jdk8里针对代码简洁性做的一个优化方案，它能使我们原本冗余的代码变得更简洁，但是由于lambda底层是通过匿名内部类实现的，所以，
//            在lambda表达式内部,对于基本数据类型及包装类等的外部变量是不能够修改的，而对于引用类型的对象，只要保证引用地址不变，对象内的内容是可以修改的，
//            即基本数据类型可以封装一层实现数据修改的目的
            // Variable used in lambda expression should be final or effectively final
            // https://blog.csdn.net/qq_36221788/article/details/100584500
            List<FooItem> list = new ArrayList<>();
            try {
                AtomicInteger count = new AtomicInteger(0);

                RetryUtil.retry(RETRYCOUNT, 1, TimeUnit.SECONDS, () -> {
                    // 后面要用个这个变量则原始值为0,然后加一个数，如果放在方法末尾加抛出异常时 累加值不准确
                    count.incrementAndGet();
                    String execInfo = String.format("get cmdb baremetal data page :%s, retry [%s]", page,
                                                    (count.get()) + "/" + RETRYCOUNT);
                    //log.info(execInfo);

                    String url = "http://localhost:8081/api/v1/test/ratelimiter_anno?page=" + page;
                    String result = HttpClientUtil.getForCmdb(url, null);
                    boolean hasRetry = result.contains("系统繁忙");
                    if (hasRetry) {
                        if (count.get() == RETRYCOUNT) {
                            // 如果重试未能成功的要进行后续处理
                            log.warn(execInfo + ", retry not found");
                        }
                        throw new RetryException("trying");
                    }

                    String json = JSON.parseObject(result, String.class);
                    List<FooItem> fooItems = JSON.parseArray(json, FooItem.class);
                    list.addAll(fooItems);
                    //log.warn(execInfo + ", ok");
                });
            } catch (Exception e) {
                log.error(Throwables.getStackTraceAsString(e));
            }
            return list;
        }

        public SaleForceOrderThread(int page) {
            this.page = page;
        }
    }

    @Data
    @AllArgsConstructor
    public static class FooItem {

        private String name;
    }
}



