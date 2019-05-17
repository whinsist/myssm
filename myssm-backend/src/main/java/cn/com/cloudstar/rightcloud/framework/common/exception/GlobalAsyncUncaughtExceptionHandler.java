/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved.
 */

package cn.com.cloudstar.rightcloud.framework.common.exception;

import cn.com.cloudstar.rightcloud.framework.common.util.JsonUtil;
import com.google.common.base.Throwables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * @author Chaohong.Mao
 * @date 2016/9/9
 */
public class GlobalAsyncUncaughtExceptionHandler extends SimpleAsyncUncaughtExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
        logger.error("Async Uncaught Exception - method[{}#{}], param[{}]\r\nException:{}",
                method.getDeclaringClass().getSimpleName(),
                method.getName(),
                JsonUtil.toJson(objects),
                Throwables.getStackTraceAsString(throwable)
        );
    }
}
