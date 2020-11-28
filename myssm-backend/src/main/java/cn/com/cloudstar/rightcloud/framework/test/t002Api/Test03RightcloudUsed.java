package cn.com.cloudstar.rightcloud.framework.test.t002Api;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import cn.com.cloudstar.rightcloud.framework.common.pojo.rest.ResultObject;
import cn.com.cloudstar.rightcloud.framework.common.pojo.rest.ResultObjectUtil;

/**
 * @author Hong.Wu
 * @date: 16:32 2020/10/25
 */
@Slf4j
public class Test03RightcloudUsed {

    @GetMapping("/CountDownLatch")
    @ResponseBody
    public ResultObject cacheData(Long userId) {
        //Map<String, Object> returnMap = new HashMap<>(2);
        Map<String, Object> returnMap = new ConcurrentHashMap<>(2);

        final CountDownLatch maxCountDown = new CountDownLatch(3);
        ExecutorService singleThreadPool = new ThreadPoolExecutor(3, 3,
                                                                  10L, TimeUnit.MILLISECONDS,
                                                                  new LinkedBlockingQueue<>(1024),
                                                                  new ThreadFactoryBuilder()
                                                                          .setNameFormat("dashboard-time-pool-%d")
                                                                          .build(),
                                                                  new ThreadPoolExecutor.AbortPolicy());
        // ------------ 资源统计 # 基础信息-----------
        singleThreadPool.execute(() -> {
            try {
                Map<String, Object> data = envResStatistic(1,  "maas");

                Map map = (Map) returnMap.get("res");
                if (map == null) {
                    map = data;
                } else {
                    map.putAll(data);
                }
                returnMap.put("res", map);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            } finally {
                maxCountDown.countDown();
            }
        });
        // ------------ 告警统计 -----------
        singleThreadPool.execute(() -> {
            try {
                Map<String, Object> data = envAlarmStatistic(1,  "maas");
                Map map = (Map) returnMap.get("res");
                if (map == null) {
                    map = data;
                } else {
                    map.putAll(data);
                }
                returnMap.put("res", map);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            } finally {
                maxCountDown.countDown();
            }
        });

        // ------------ 资源统计 # CPU、内存、存储监控信息-----------
        singleThreadPool.execute(() -> {
            try {
                Map<String, Object> capacityMap = envMonitorStatistic(1,  "maas");
                returnMap.put("capacity", capacityMap);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            } finally {
                maxCountDown.countDown();
            }
        });
        singleThreadPool.shutdown();
        try {
            maxCountDown.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResultObjectUtil.success();
    }

    private Map<String,Object> envAlarmStatistic(int i, String maas) {
        return null;
    }

    private Map<String,Object> envMonitorStatistic(int i, String maas) {
        return null;
    }

    private Map<String,Object> envResStatistic(int cloudEnvId, String envType) {
        return null;
    }
}
