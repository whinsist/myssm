/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.exception;

/**
 * cn.com.cloudstar.rightcloud.common.exception
 *
 * @author Chaohong.Mao
 */
public class RetryException extends RuntimeException {
    public RetryException(Throwable cause) {
        super(cause);
    }

    public RetryException(String message) {
        super(message);
    }
}
