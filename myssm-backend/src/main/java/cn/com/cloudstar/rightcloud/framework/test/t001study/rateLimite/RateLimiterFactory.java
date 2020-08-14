package cn.com.cloudstar.rightcloud.framework.test.t001study.rateLimite;

import com.google.common.util.concurrent.RateLimiter;

import org.springframework.util.StringUtils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class RateLimiterFactory {

    private static ConcurrentHashMap<String, RateLimiter> limiterMap = new ConcurrentHashMap<>();

    //每秒许可证数量
    //private static double permitsPerSecond = 1000;
    private static double permitsPerSecond = 1;

    //等待超时时间
    //private static long timeout = 4L;
    private static long timeout = 60L;

    /**
     * @param apiId 对应接口的id
     *
     * @description 获取许可
     */
    public static boolean tryAcquire(String apiId) {
        //如果传入apiId为空则返回true
        if (StringUtils.isEmpty(apiId)) {
            return true;
        }
        limiterMap.putIfAbsent(apiId, RateLimiter.create(permitsPerSecond));
        RateLimiter rateLimiter = limiterMap.get(apiId);
        boolean tryAcquireFlag = rateLimiter.tryAcquire(timeout, TimeUnit.SECONDS);

        return tryAcquireFlag;
    }

}