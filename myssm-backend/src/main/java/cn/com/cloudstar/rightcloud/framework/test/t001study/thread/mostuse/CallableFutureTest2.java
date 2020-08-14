package cn.com.cloudstar.rightcloud.framework.test.t001study.thread.mostuse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Hong.Wu
 * @date: 4:24 2020/07/11
 */
@Slf4j
public class CallableFutureTest2 {

    public static void main(String[] args) throws Exception {
//        test1();
        test2();

    }
    private static void test1() throws Exception {
        // 开启线程数量
        //定义固定长度的线程池  防止线程过多
        ExecutorService execservice = Executors.newFixedThreadPool(6);
        List<Future<List<FooItem>>> tasks = new ArrayList<>();//添加任务
        int limit = 139;
        int mythread = 2;
        for (int i = 0; i < mythread; i++) {
            log.info("线程：{}", i);
            int start = i * limit;
            SaleForceOrderThred saleForceOrderThred = new SaleForceOrderThred(start, limit);
            Future<List<FooItem>> submit = execservice.submit(saleForceOrderThred);
            tasks.add(submit);

        }

        for (Future<List<FooItem>> future : tasks) {
            List<FooItem> fooItems = future.get();
            System.out.println(fooItems);
        }
    }


    private static Map<String, String> getSplitMap(int total, int threadNum) {
        Map<String, String> map = new HashMap<>();
//        for (int i = 1; i <= 5; i++) {
//            map.put("1", "0:2");
//        }
        map.put("1", "1");
        map.put("1", "2");
        map.put("2", "3");
        map.put("3", "4");
        map.put("4", "5");
        map.put("5", "6");
        return map;
    }

    private static void test2() {
        //开始时间
        long start = System.currentTimeMillis();
        //返回结果
        List<List> result = new ArrayList<>();
        //查询数据库总数量 51条数据  每页10条数据  --->6页   5个线程:第一个线程2页  第2-第5每个线程1页
        int count = 51;
        Map<String, String> splitMap = getSplitMap(count, 5);

        //Callable用于产生结果
        List<Callable<List>> tasks = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            //不同的线程用户处理不同分段的数据量，这样就达到了平均分摊查询数据的压力
            String[] nums = splitMap.get(String.valueOf(i)).split(":");
            int startNum = Integer.valueOf(nums[0]);
            int endNum = Integer.valueOf(nums[1]);
            int end = endNum - startNum + 1;
            Callable<List> qfe = new ThredQuery(startNum, end);
            tasks.add(qfe);
        }
        try {
            //定义固定长度的线程池  防止线程过多，这个数量一般跟自己电脑的CPU核数进行匹配
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            //Future用于获取结果
            List<Future<List>> futures = executorService.invokeAll(tasks);
            //处理线程返回结果
            if (futures != null && futures.size() > 0) {
                for (Future<List> future : futures) {
                    List listx = future.get();
                    System.out.println(listx);
                    result.addAll(listx);
                }
            }
            //关闭线程池，一定不能忘记
            executorService.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("线程查询数据用时:" + (end - start) + "ms");

    }

    public static class ThredQuery implements Callable<List> {

        private int start;
        private int end;

        public ThredQuery(int start, int end) {
            System.out.println("start=" + start + " end=" + end);
            this.start = start;
            this.end = end;
        }

        //返回数据给Future
        @Override
        public List call() throws Exception {
            //每个线程查询出来的数据集合
            List<FooItem> list = new ArrayList<>();
            for (int i = start; i <= end; i++) {
                FooItem item = new FooItem();
                item.setName("name" + i);
                list.add(item);
            }
            return list;
        }
    }




    public static class SaleForceOrderThred implements Callable<List<FooItem>> {

        //分页index
        private int start;
        //数量
        private int end;

        @Override
        public List<FooItem> call() throws Exception {
            List<FooItem> list = new ArrayList<>();
            for (int i = start; i <= end; i++) {
                FooItem item = new FooItem();
                item.setName("name" + i);
                list.add(item);
            }
            System.out.println(Thread.currentThread() + "当前处理：" + list);
            return list;
        }

        public SaleForceOrderThred(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    @Data
    public static class FooItem {

        private String name;
    }

}
