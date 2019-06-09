/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author ChaoHong.Mao
 * @date 2016/8/9
 */
public class RetryUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(RetryUtil.class);

    /**
     * 不间隔重试
     *
     * @param retryCount retryCount
     * @param function   ExecuteFunction
     */
    public static void retry(int retryCount, ExecuteFunction function) {
        retry(retryCount, -1, null, function);
    }

    /**
     * 有间隔的重试
     *
     * @param retryCount retryCount
     * @param interval   interval
     * @param timeUnit   timeUnit
     * @param handler    handler
     */
    public static void retry(int retryCount, long interval, TimeUnit timeUnit, ExecuteFunction handler) {
        retry(retryCount, interval, timeUnit, false, handler);
    }

    /**
     * 重试执行
     *
     * @param retryCount  retryCount
     * @param interval    interval
     * @param timeUnit    timeUnit
     * @param throwIfFail throwIfFail
     * @param function    function
     */
    public static void retry(int retryCount, long interval, TimeUnit timeUnit, boolean throwIfFail,
                             ExecuteFunction function)  {
        if (function == null) {
            return;
        }

        for (int i = 0; i < retryCount; i++) {
            try {
                function.execute();
                break;
            } catch (Exception e) {
                if (i == retryCount - 1) {
                    if (throwIfFail) {
                        throw e;
                    } else {
                        break;
                    }
                } else {
                    if (timeUnit != null && interval > 0L) {
                        try {
                            timeUnit.sleep(interval);
                        } catch (InterruptedException e1) {
                            LOGGER.error(e1.getMessage(), e1);
                        }
                    }
                }
            }
        }
    }

    public interface ExecuteFunction {
        void execute() ;
    }

}
