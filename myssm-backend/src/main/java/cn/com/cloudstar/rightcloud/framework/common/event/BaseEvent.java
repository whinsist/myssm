/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;

/**
 * @author ChaoHong.Mao
 * @date 2016/8/4
 */
public abstract class BaseEvent<T> extends ApplicationEvent {

    private static final Logger logger = LoggerFactory.getLogger(BaseEvent.class);

    private String fromClass;

    /**
     * event routing key
     */
    private String eventRoutingKey;

    public BaseEvent(T source) {
        super(source);

        // if debug enabled, record message create class by stacktrace
        if (logger.isDebugEnabled()) {
            StackTraceElement[] s = Thread.currentThread().getStackTrace();
            for (int i = 1; i < s.length; i++) {
                try {
                    Class clazz = Class.forName(s[i].getClassName());
                    if (!BaseEvent.class.isAssignableFrom(clazz)) {
                        fromClass = clazz.getName();
                        break;
                    }
                } catch (ClassNotFoundException e) {
                    logger.warn("unable to find class in stacktrace in DMEvent", e);
                }
            }
        }
    }

    public String getEventRoutingKey() {
        return eventRoutingKey;
    }

    public void setEventRoutingKey(String eventRoutingKey) {
        this.eventRoutingKey = eventRoutingKey;
    }

    @Override
    @SuppressWarnings("unchecked")
    public final T getSource() {
        return (T) source;
    }

    @Override
    public String toString() {
        if (logger.isDebugEnabled()) {
            return super.toString() + "[from class:" + fromClass + "]";
        } else {
            return super.toString();
        }
    }
}

