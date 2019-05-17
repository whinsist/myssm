/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.exception;


import cn.com.cloudstar.rightcloud.framework.common.constants.RestConst;

/**
 * Biz exception 类.
 *
 * @author Chaohong.Mao
 */
public class BizException extends RuntimeException {

    /**
     * 错误类型
     */
    private RestConst.RestEnum restEnum;

    /**
     * 构造 Biz exception 的实例.
     *
     * @param restEnum   the error enum
     * @param frdMessage the frd message
     */
    public BizException(RestConst.RestEnum restEnum, String frdMessage) {
        super(frdMessage);
        this.restEnum = restEnum;
    }

    /**
     * 构造 Biz exception 的实例.
     *
     * @param restEnum  the error enum
     * @param throwable the throwable
     */
    public BizException(RestConst.RestEnum restEnum, Throwable throwable) {
        super(throwable);
        this.restEnum = restEnum;
    }

    /**
     * 构造 Biz exception 的实例.
     *
     * @param restEnum   the error enum
     * @param frdMessage the frd message
     * @param throwable  the throwable
     */
    public BizException(RestConst.RestEnum restEnum, String frdMessage, Throwable throwable) {
        super(frdMessage, throwable);
        this.restEnum = restEnum;
    }

    /**
     * 构造 Biz exception 的实例.
     *
     * @param frdMessage the frd message
     */
    public BizException(String frdMessage) {
        super(frdMessage);
        this.restEnum = RestConst.BizError.BIZ_ERROR;
    }

    /**
     * 构造 Biz exception 的实例.
     *
     * @param throwable the throwable
     */
    public BizException(Throwable throwable) {
        super(throwable);
        this.restEnum = RestConst.BizError.BIZ_ERROR;
    }

    /**
     * 构造 Biz exception 的实例.
     *
     * @param frdMessage the frd message
     * @param throwable  the throwable
     */
    public BizException(String frdMessage, Throwable throwable) {
        super(frdMessage, throwable);
        this.restEnum = RestConst.BizError.BIZ_ERROR;
    }

    public RestConst.RestEnum getRestEnum() {
        return restEnum;
    }

    public void setRestEnum(RestConst.RestEnum restEnum) {
        this.restEnum = restEnum;
    }

}
